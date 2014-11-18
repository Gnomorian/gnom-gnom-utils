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

package nz.co.crookedhill.ggutils.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;

/**
 *	recipe instances:
 * 	net.minecraft.item.crafting.ShapedRecipes
 *	net.minecraftforge.oredict.ShapedOreRecipe
 *	net.minecraft.item.crafting.RecipesMapExtending
 *	net.minecraft.item.crafting.RecipesArmorDyes
 *	net.minecraft.item.crafting.RecipeFireworks
 *	net.minecraft.item.crafting.RecipeBookCloning
 *	net.minecraft.item.crafting.RecipesMapCloning
 *	net.minecraft.item.crafting.ShapelessRecipes
 *	net.minecraftforge.oredict.ShapelessOreRecipe
 *	net.minecraft.item.crafting.ShapedRecipes
 */

/**
 * new packet needs to be added that sends the itemstack 
 * the player wants to make to the server. possibly as well
 * as the itemstacks used to make it as the items used to
 * make it to avoid the server looping through all the recipes.
 * @author william-cameron1994
 *
 */

public class GGURecipeFilter 
{
	public static List filter(List<ItemStack> inventoryItems) 
	{
		List avalableRecipes = new ArrayList();

		//for shaped recipes
		for(GGUShapedRecipe recipe : GGURecipeManager.getShaped()) 
		{
			/*
			 * if the player has an item required for the recipe, 
			 * incriment by one, if its the size of required items 
			 * at the end, player has all items
			 */
			int gotRequiredItems = 0;
			for(ItemStack requiredItem : recipe.recipeItems) 
			{
				if(requiredItem == null)
					continue;
				for(ItemStack invItem : inventoryItems) 
				{
					if(invItem == null)
						continue;
					if(requiredItem.getUnlocalizedName().equals(invItem.getUnlocalizedName()) && requiredItem.stackSize <= invItem.stackSize)
						gotRequiredItems++;
				}
			}
			if(recipe.recipeItems.length == gotRequiredItems) {
				avalableRecipes.add(recipe);
				System.out.println("can make a "+recipe.getRecipeOutput().getDisplayName()+"X"+recipe.getRecipeOutput().stackSize);
				for(ItemStack item : recipe.recipeItems)
				System.out.println("	requires a:"+item.getDisplayName()+"X"+item.stackSize);
			}
		}

		//for shapeless recipes
		for(GGUShapelessRecipe recipe : GGURecipeManager.getShapeless()) 
		{
			/*
			 * if the player has an item required for the recipe, 
			 * incriment by one, if its the size of required items 
			 * at the end, player has all items
			 */
			int gotRequiredItems = 0;
			for(int i = 0; i < recipe.recipeItems.length; i++) {
				ItemStack requiredItem = recipe.recipeItems[i];
				if(requiredItem == null)
					continue;
				for(ItemStack invItem : inventoryItems) 
				{
					if(invItem == null)
						continue;
					if(requiredItem.getUnlocalizedName().equals(invItem.getUnlocalizedName())&&requiredItem.stackSize <= invItem.stackSize)
						gotRequiredItems++;
				}
			}
			if(recipe.recipeItems.length == gotRequiredItems)
				avalableRecipes.add(recipe);
		}
		System.out.println(avalableRecipes.size());
		return avalableRecipes;
	}
}
