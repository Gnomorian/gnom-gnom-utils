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

import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;

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
    public GGURecipeManager(List recipeList)
    {
	for (int i = 0; i < recipeList.size(); i++)
	{
	    if (recipeList.get(i) instanceof ShapedRecipes)
	    {
		// if(((ShapedRecipes)recipeList.get(i)).getRecipeOutput() !=
		// null)
		shapedRecipes.add(new GGUShapedRecipe(
			(ShapedRecipes) recipeList.get(i)));
	    } else if (recipeList.get(i) instanceof ShapelessRecipes)
	    {
		if (((ShapelessRecipes) recipeList.get(i)).getRecipeOutput() != null)
		    shapelessRecipes.add(new GGUShapelessRecipe(
			    (ShapelessRecipes) recipeList.get(i)));
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
