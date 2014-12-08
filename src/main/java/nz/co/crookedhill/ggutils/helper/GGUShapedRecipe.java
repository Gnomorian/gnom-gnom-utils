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
