/* 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. 
 */

package nz.co.crookedhill.ggutils.helper;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipes;
import nz.co.crookedhill.ggutils.util.GGUSort;

public class GGUShapedRecipe
{
    public ItemStack[] recipeItems;
    ItemStack recipeOutput;

    public GGUShapedRecipe(ShapedRecipes oldRecipe)
    {
	recipeItems = oldRecipe.recipeItems;
	GGUSort sorter = new GGUSort();
	sorter.sortItemArray(recipeItems);
	recipeOutput = oldRecipe.getRecipeOutput();
    }

    public ItemStack getRecipeOutput()
    {
	return recipeOutput;
    }
}
