/* 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. 
 */

package nz.co.crookedhill.ggutils.block;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.world.World;
import nz.co.crookedhill.ggutils.achievements.GGUAchievements;
import nz.co.crookedhill.ggutils.util.GGUSort;

public class GGULazyCrafter extends Block
{

	protected GGULazyCrafter(Material material)
	{
		super(material);
		this.setBlockName("lazyCrafter");
		this.setHardness(0.5f);
		this.setStepSound(Block.soundTypeWood);
		// side textures will the crafting table textures, top will be
		// an edited one with an enderpearl crammed in.

		// when an item is crafted, ender particles will be spawned around
		// the block, preferably ontop where the enderpearl is.
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float float1, float float2, float float3)
	{
		player.addStat(GGUAchievements.usedlazyCrafter, 1);
		// open the gui.
		// get the players inventory.
		// display the inventory and the items avalable
		// to craft with current items.
		if (world.isRemote)
		{
			IInventory inventory = player.inventory;
			List<ItemStack> invItems = new ArrayList<ItemStack>();
			// 36-39=armour
			for (int i = 0; i < 35; i++)
			{
				ItemStack items = inventory.getStackInSlot(i);
				if (items == null)
					continue;
				invItems.add(items);
			}

			List recipes = (new GGUSort()).sortRecipes(invItems);
			for (int i = 0; i < recipes.size(); i++)
			{
				if (recipes.get(i) instanceof ShapedRecipes)
				{
					// System.out.println(player.getDisplayName()+" can craft a shaped "+((ShapedRecipes)recipes.get(i)).getRecipeOutput().getDisplayName());
				} else if (recipes.get(i) instanceof ShapelessRecipes)
				{
					// System.out.println(player.getDisplayName()+" can craft a shapeless "+((ShapelessRecipes)recipes.get(i)).getRecipeOutput().getDisplayName());
				}

			}

			return true;
		}
		return false;
	}

}
