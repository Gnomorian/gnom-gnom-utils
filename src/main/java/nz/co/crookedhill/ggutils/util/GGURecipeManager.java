/* 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. 
 */

package nz.co.crookedhill.ggutils.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import nz.co.crookedhill.ggutils.helper.GGUShapedRecipe;
import nz.co.crookedhill.ggutils.helper.GGUShapelessRecipe;

public class GGURecipeManager
{
    /**
     * list of all shaped recipes found in the CraftingManager's Recipe List.
     */
    private static List<GGUShapedRecipe> shapedRecipes = new ArrayList<GGUShapedRecipe>();
    /**
     * list of all shapeless recipes found in the CraftingManager's Recipe List.
     */
    private static List<GGUShapelessRecipe> shapelessRecipes = new ArrayList<GGUShapelessRecipe>();

    /**
     * gets the list of recipes from the crafting manager and sorts them into
     * multiple lists to reduse lookup time during the game.
     * 
     * @param recipeList
     *            list of recipes from CraftingManager
     */
    public static void init(List recipeList)
    {
	for (int i = 0; i < recipeList.size(); i++)
	{
	    if (recipeList.get(i) instanceof ShapedRecipes)
	    {
		// if(((ShapedRecipes)recipeList.get(i)).getRecipeOutput() !=
		// null)
		shapedRecipes.add(new GGUShapedRecipe((ShapedRecipes) recipeList.get(i)));
	    } else if (recipeList.get(i) instanceof ShapelessRecipes)
	    {
		if (((ShapelessRecipes) recipeList.get(i)).getRecipeOutput() != null)
		    shapelessRecipes.add(new GGUShapelessRecipe((ShapelessRecipes) recipeList.get(i)));
	    }
	}
    }

    /**
     * returns the list of shaped recipes.
     */
    public static List<GGUShapedRecipe> getShaped()
    {
	return shapedRecipes;

    }

    /**
     * returns the list of shaped recipes.
     */
    public static List<GGUShapelessRecipe> getShapeless()
    {
	return shapelessRecipes;

    }
}
