/*
 * Copyright (c) 2014, William <w.cameron@crookedhill.co.nz>
 * 
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
 * ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
 * OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */

package nz.co.crookedhill.ggutils.handlers;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockOre;
import net.minecraft.block.BlockSand;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

/**
 * if the player has a tool inhand that is enchanted when a block is broken,
 * cancel the block break. get a list of what the block drops. check what the
 * ore smelts into and replace it in the list of droped items with what it
 * smelts into then set the block to air and spawn an itemstack, with a
 * stacksize of normal drop+(1 to i) (i being the level of the enchantment)
 * 
 */

public class GGUEnchantmentHandler
{
    @SubscribeEvent
    public void onBlockBreak(BreakEvent event)
    {
	World world = event.world;

	if (!world.isRemote)
	{

	    Block block = event.block;
	    int metaData = event.blockMetadata;
	    int x = event.x;
	    int y = event.y;
	    int z = event.z;
	    EntityPlayer player = event.getPlayer();
	    ItemStack heldItemStack = player.getHeldItem();
	    // Item heldItem = heldItemStack.getItem();
	    ArrayList<ItemStack> items;

	    if (heldItemStack != null && heldItemStack.getEnchantmentTagList() != null)
	    {
		if (heldItemStack.getItem().getToolClasses(heldItemStack).contains(block.getHarvestTool(metaData)) && heldItemStack.getItem().getHarvestLevel(heldItemStack, block.getHarvestTool(metaData)) >= block.getHarvestLevel(metaData))
		{
		    if (block instanceof BlockOre || block instanceof BlockLog || block instanceof BlockSand)
		    {
			event.setCanceled(true);
			world.setBlockToAir(x, y, z);
			items = getItemsToDrop(world, block, heldItemStack, x, y, z);

			for (int j = 0; j < items.size(); j++)
			{
			    world.spawnEntityInWorld(new EntityItem(world, (float) x, (float) y, (float) z, items.get(j)));
			}
		    }
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
	Random rand = new Random();

	int enchantmentLevel = 0;
	// check what level the enchant is.
	for (int i = 0; i < itemStack.getEnchantmentTagList().tagCount(); i++)
	{
	    if (itemStack.getEnchantmentTagList().getStringTagAt(i).equals("{lvl:3s,id:103s,}"))
		enchantmentLevel = 3;
	    else if (itemStack.getEnchantmentTagList().getStringTagAt(i).equals("{lvl:2s,id:103s,}"))
		enchantmentLevel = 2;
	    else if (itemStack.getEnchantmentTagList().getStringTagAt(i).equals("{lvl:1s,id:103s,}"))
		enchantmentLevel = 1;
	}

	FurnaceRecipes recipes = FurnaceRecipes.smelting();
	ArrayList<ItemStack> items = block.getDrops(world, x, y, z, 0, 3);
	for (int i = 0; i < items.size(); i++)
	{
	    if (recipes.getSmeltingResult(items.get(i)) != null)
	    {
		items.set(i, recipes.getSmeltingResult(items.get(i)));
		int dropCount = items.get(i).stackSize + rand.nextInt(enchantmentLevel + 1);
		items.get(i).stackSize = dropCount;
	    }
	}

	return items;
    }
}
