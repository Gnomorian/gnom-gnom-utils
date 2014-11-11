package nz.co.crookedhill.ggutils.handlers;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.BlockOre;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

/**
 * if the player has a tool inhand that is enchanted
 * when a block is broken, cancel the block break.
 * get a list of what the block drops.
 * check what the ore smelts into and replace it in the
 * list of droped items with what it smelts into
 * then set the block to air and spawn an itemstack, with a stacksize of
 * normal drop+(1 to i) (i being the level of the enchantment)
 * 
 * this class needs to be cleaned up wil, very hackey :)
 * @author william-cameron1994
 *
 */

public class GGUEnchantmentHandler {
	@SubscribeEvent
	public void onBlockBreak(BreakEvent event) {
		if(!event.world.isRemote) {
			Random rand = new Random();
			if(event.getPlayer().getHeldItem() != null && event.getPlayer().getHeldItem().getEnchantmentTagList() != null) {
				for(int i = 0; i < event.getPlayer().getHeldItem().getEnchantmentTagList().tagCount(); i++){
					if(event.getPlayer().getHeldItem().getEnchantmentTagList().getStringTagAt(i).equals("{lvl:3s,id:103s,}")) {
						if(event.block instanceof BlockOre) {
							event.setCanceled(true);
							FurnaceRecipes recipes = FurnaceRecipes.smelting();
							ArrayList<ItemStack> items = event.block.getDrops(event.world, event.x, event.y, event.z, 0, 3);
							for(int j = 0; j < items.size(); j++){
								if(recipes.getSmeltingResult(items.get(j)) != null) {
									items.set(j, recipes.getSmeltingResult(items.get(j)));
									int dropCount = items.get(i).stackSize + rand.nextInt(3);
									items.get(i).stackSize = dropCount;
								}
							}
							event.world.setBlockToAir(event.x, event.y, event.z);
							for(int j = 0; j < items.size(); j++){
								event.world.spawnEntityInWorld(new EntityItem(event.world, (float)event.x, (float)event.y, (float)event.z, items.get(j)));
							}

						}
					}
				}
				for(int i = 0; i < event.getPlayer().getHeldItem().getEnchantmentTagList().tagCount(); i++){
					if(event.getPlayer().getHeldItem().getEnchantmentTagList().getStringTagAt(i).equals("{lvl:2s,id:103s,}")) {
						if(event.block instanceof BlockOre) {
							event.setCanceled(true);
							FurnaceRecipes recipes = FurnaceRecipes.smelting();
							ArrayList<ItemStack> items = event.block.getDrops(event.world, event.x, event.y, event.z, 0, 2);
							for(int j = 0; j < items.size(); j++){
								if(recipes.getSmeltingResult(items.get(j)) != null) {
									items.set(j, recipes.getSmeltingResult(items.get(j)));
									int dropCount = items.get(i).stackSize + rand.nextInt(3);
									items.get(i).stackSize = dropCount;
								}
							}
							event.world.setBlockToAir(event.x, event.y, event.z);
							for(int j = 0; j < items.size(); j++){
								event.world.spawnEntityInWorld(new EntityItem(event.world, (float)event.x, (float)event.y, (float)event.z, items.get(j)));
							}

						}
					}
				}
				for(int i = 0; i < event.getPlayer().getHeldItem().getEnchantmentTagList().tagCount(); i++){
					if(event.getPlayer().getHeldItem().getEnchantmentTagList().getStringTagAt(i).equals("{lvl:1s,id:103s,}")) {
						if(event.block instanceof BlockOre) {
							event.setCanceled(true);
							FurnaceRecipes recipes = FurnaceRecipes.smelting();
							ArrayList<ItemStack> items = event.block.getDrops(event.world, event.x, event.y, event.z, 0, 1);
							for(int j = 0; j < items.size(); j++){
								if(recipes.getSmeltingResult(items.get(j)) != null) {
									items.set(j, recipes.getSmeltingResult(items.get(j)));
									int dropCount = items.get(i).stackSize + rand.nextInt(3);
									items.get(i).stackSize = dropCount;
								}
							}
							event.world.setBlockToAir(event.x, event.y, event.z);
							for(int j = 0; j < items.size(); j++){
								event.world.spawnEntityInWorld(new EntityItem(event.world, (float)event.x, (float)event.y, (float)event.z, items.get(j)));
							}

						}
					}
				}
			}
		}
	}
}
