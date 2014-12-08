/* 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. 
 */

package nz.co.crookedhill.ggutils.helper;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapelessRecipes;
import nz.co.crookedhill.ggutils.util.GGUSort;

public class GGUShapelessRecipe
{
    public ItemStack[] recipeItems;
    ItemStack recipeOutput;

    public GGUShapelessRecipe(ShapelessRecipes oldRecipe)
    {
	List<ItemStack> tempList = oldRecipe.recipeItems;
	recipeItems = new ItemStack[oldRecipe.recipeItems.size()];
	recipeItems = ((List<ItemStack>) oldRecipe.recipeItems).toArray(recipeItems);
	GGUSort sorter = new GGUSort();
	sorter.sortItemArray(recipeItems);
	recipeOutput = oldRecipe.getRecipeOutput();
    }

    public ItemStack getRecipeOutput()
    {
	return recipeOutput;
    }
}
