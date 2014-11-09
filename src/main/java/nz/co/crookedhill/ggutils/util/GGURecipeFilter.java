package nz.co.crookedhill.ggutils.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.RecipeBookCloning;
import net.minecraft.item.crafting.RecipeFireworks;
import net.minecraft.item.crafting.RecipesArmorDyes;
import net.minecraft.item.crafting.RecipesMapCloning;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;

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
			}
			else if(currentRecipe instanceof ShapelessRecipes) {
				List requiredItems = ((ShapelessRecipes)recipes.get(i)).recipeItems;
				ItemStack output = ((ShapelessRecipes)recipes.get(i)).getRecipeOutput();
			}
			else if(currentRecipe instanceof RecipesArmorDyes) {
				ItemStack requiredItems = ((RecipesArmorDyes)recipes.get(i)).getRecipeOutput();
			}
			else if(currentRecipe instanceof RecipeFireworks) {
				//List requiredItems = ((RecipeFireworks)recipes.get(i)).;
				//ItemStack output = ((RecipeFireworks)recipes.get(i)).getRecipeOutput();
				System.out.println("Cant figure it out yet.");
			}
			else if(currentRecipe instanceof RecipeBookCloning) {
				List requiredItems = ((ShapelessRecipes)recipes.get(i)).recipeItems;
				ItemStack output = ((ShapelessRecipes)recipes.get(i)).getRecipeOutput();
			}
			else if(currentRecipe instanceof RecipesMapCloning) {
				List requiredItems = ((ShapelessRecipes)recipes.get(i)).recipeItems;
				ItemStack output = ((ShapelessRecipes)recipes.get(i)).getRecipeOutput();
			}
		}
		return recipes;

	}
	
	/**
	 * finds all the items you can craft with what you have in your inventory.
	 * @param recipetype reprisents the recipetype it extends from.
	 * @param items items in players inventory.
	 * @param recipes list of all game recipes.
	 * @return list of avalable recipes using items in players inventory.
	 */
	private List compare(String recipetype, List items, List recipes) {
		List avalableRecipes = new ArrayList();
		
		
		return avalableRecipes;
	}
}
