/* 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. 
 */

package nz.co.crookedhill.ggutils.handlers;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockOre;
import net.minecraft.block.BlockSand;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import nz.co.crookedhill.ggutils.enchantment.GGUEnchantment;
import nz.co.crookedhill.ggutils.helper.GGUConfigManager;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

/**
 * if the player has a tool inhand that is enchanted when a block is broken,
 * cancel the block break. get a list of what the block drops. check what the
 * ore smelts into and replace it in the list of droped items with what it
 * smelts into then set the block to air and spawn an itemstack, with a
 * stacksize of normal drop+(1 to i) (i being the level of the enchantment)
 */

public class GGUEnchantmentHandler
{
	int icarusTimer = 0;

	@SubscribeEvent
	/**
	 * Event for breaking blocks while using a tool with the prosperity enchant.
	 * @param event The BreakBlock event
	 */
	public void prosperityOnBreakBlock(BreakEvent event) 
	{
		World world = event.world;
		EntityPlayer player = event.getPlayer();
		ItemStack heldItemStack = player.getHeldItem();

		if(!world.isRemote && heldItemStack != null) {

			Block block = event.block;
			int metaData = event.blockMetadata;
			int x = event.x;
			int y = event.y;
			int z = event.z;
			Item heldItem = heldItemStack.getItem();
			ArrayList<ItemStack> items;
			int enchantLevel = hasItemstackEnchantment(heldItemStack, GGUEnchantment.prosperousAutoSmelt.effectId);

			if(heldItemStack != null && heldItemStack.getEnchantmentTagList() != null && enchantLevel != 0) 
			{
				if(heldItem.getToolClasses(heldItemStack).contains(block.getHarvestTool(metaData)) 
						&& heldItem.getHarvestLevel(heldItemStack, block.getHarvestTool(metaData)) >= block.getHarvestLevel(metaData)) 
				{
					if(block instanceof BlockOre || block instanceof BlockLog || block instanceof BlockSand)
					{
						event.setCanceled(true);
						world.setBlockToAir(x, y, z);
						items = getItemsToDrop(world, block, heldItemStack,x, y, z);

						for(int j = 0; j < items.size(); j++)
						{
							items.get(j).stackSize = items.get(j).stackSize + world.rand.nextInt(enchantLevel);
							world.spawnEntityInWorld(new EntityItem(world, (float)x, (float)y, (float)z, items.get(j)));
						}
					}
				}
			}
		}
	}

	@SubscribeEvent
	/**
	 * Event for checking if the player is wearing armor with the Icarus enchantment
	 * @param event The LivingUpdateEvent event
	 */
	public void icarusOnUpdate(LivingUpdateEvent event)
	{
		int enchantmentLevel = 0;

		if(event.entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.entity;
			ItemStack[] inventory = player.inventory.armorInventory;

			ItemStack armor = inventory[2];

			//check if armor is being worn
			if(armor != null)
			{
				enchantmentLevel =  EnchantmentHelper.getEnchantmentLevel(GGUEnchantment.icarus.effectId, armor);
			}
			if(enchantmentLevel != 0)
			{
				if(enchantmentLevel == 1)
				{
					player.capabilities.allowFlying = true;
					player.capabilities.setFlySpeed(0.05F);

					if(this.icarusTimer >= 40 && !player.worldObj.isRemote && !player.onGround)
					{
						armor.damageItem(1, (EntityLivingBase)player);	
						if (armor.stackSize == 0)
						{
							armor = null;
							player.inventory.setInventorySlotContents(38, null);
						}
						this.icarusTimer = 0;
					}

				}
				else if(enchantmentLevel == 2)
				{
					player.capabilities.allowFlying = true;
					player.capabilities.setFlySpeed(0.1F);
					if(this.icarusTimer >= 80 && !player.worldObj.isRemote && !player.onGround)
					{
						armor.damageItem(1, (EntityLivingBase)player);	
						if (armor.stackSize == 0)
						{
							armor = null;
							player.inventory.setInventorySlotContents(38, null);
						}
						this.icarusTimer = 0;
					}
				}
				else if(!player.capabilities.isCreativeMode)
				{
					player.capabilities.allowFlying = false;
					player.capabilities.isFlying = false;
					this.icarusTimer = 0;
				}

				if(!player.onGround)
				{
					this.icarusTimer++;
				}
			}
		}
	}

	/**
	 * Gets an arraylist of dropped itemstacks
	 * 
	 * @param world
	 *            world object
	 * @param block
	 *            block mined
	 * @param itemStack
	 *            held itemstack
	 * @param x
	 *            x of the block
	 * @param y
	 *            y of the block
	 * @param z
	 *            z of the block
	 * @return the items that should get dropped.
	 */
	private ArrayList<ItemStack> getItemsToDrop(World world, Block block, ItemStack itemStack, int x, int y, int z)
	{
		Random rand = world.rand;

		int enchantmentLevel = EnchantmentHelper.getEnchantmentLevel(GGUEnchantment.prosperousAutoSmelt.effectId, itemStack);

		FurnaceRecipes recipes = FurnaceRecipes.smelting();
		ArrayList<ItemStack> items = block.getDrops(world, x, y, z, 0, enchantmentLevel);
		for (int i = 0; i < items.size(); i++)
		{
			if (recipes.getSmeltingResult(items.get(i)) != null)
			{
				items.set(i, recipes.getSmeltingResult(items.get(i)));
				int addedRand = rand.nextInt(enchantmentLevel + 1);
				int dropCount = items.get(i).stackSize + addedRand;
				items.get(i).stackSize = dropCount;
			}
		}

		return items;
	}
	/**
	 * checks if an itemstack has an enchantment id, returns 0 if doesnt else it reuturns the enchantment level.
	 * @param itemstack the itemstack to check for enchantments.
	 * @param enchantmentID the id of the enchantment to check for
	 * @return 0 if no enchantment or the enchantment level.
	 */
	private int hasItemstackEnchantment(ItemStack itemstack, int enchantmentID)
	{
		Map<Integer, Integer> enchants = EnchantmentHelper.getEnchantments(itemstack);
		if(enchants != null)
		{
			for(Entry<Integer, Integer> entry : enchants.entrySet())
			{
				if(entry.getKey().equals(enchantmentID))
				{
					return entry.getValue();
				}
			}
		}
		return 0;
	}
}
