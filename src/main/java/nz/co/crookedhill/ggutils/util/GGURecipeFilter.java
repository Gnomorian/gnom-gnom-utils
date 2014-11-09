package nz.co.crookedhill.ggutils.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.ShapedRecipes;

/**
 *	recipes needed to support are:
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
 *	net.minecraftforge.oredict.ShapedOreRecipe
 *	net.minecraft.item.crafting.RecipesMapExtending
 *	net.minecraft.item.crafting.RecipesArmorDyes
 *	net.minecraft.item.crafting.RecipeFireworks
 *	net.minecraft.item.crafting.RecipeBookCloning
 *	net.minecraft.item.crafting.RecipesMapCloning
 *	net.minecraft.item.crafting.ShapelessRecipes
 *	net.minecraftforge.oredict.ShapelessOreRecipe
 */

public class GGURecipeFilter {
	public static List filter(List<ItemStack> inventoryItems) {
		List avalableRecipes = new ArrayList();
		List recipes = CraftingManager.getInstance().getRecipeList();
		for(int i = 0; i < recipes.size(); i++) {
			Object currentRecipe = recipes.get(i);
			System.out.println("Shaped Recipes");
			if(currentRecipe instanceof ShapedRecipes) {
				
				ItemStack[] requiredItems = ((ShapedRecipes)recipes.get(i)).recipeItems;
				ItemStack output = ((ShapedRecipes)recipes.get(i)).getRecipeOutput();
				System.out.println("	"+output+"\nRecipe requires:");
				for(ItemStack item : requiredItems){
					if(item==null)
						continue;
					System.out.println("		"+item.getDisplayName());
				}
			}
		}
		return recipes;

	}
}
