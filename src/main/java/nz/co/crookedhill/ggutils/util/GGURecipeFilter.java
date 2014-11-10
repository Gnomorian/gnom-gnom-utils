package nz.co.crookedhill.ggutils.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
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

			if(currentRecipe instanceof ShapedRecipes) {
				int gotRequiredItems = 0;
				ItemStack[] requiredItems = ((ShapedRecipes)recipes.get(i)).recipeItems;
				ItemStack output = ((ShapedRecipes)recipes.get(i)).getRecipeOutput();
				if(output == null)
					continue;
				for(int k = 0; k < requiredItems.length; k++) {
					for(int l = 0; l<inventoryItems.size();l++) {
						if(inventoryItems.get(l) != null) {
							if(requiredItems[k].isItemEqual(inventoryItems.get(l))) {
								if(inventoryItems.get(l).stackSize >= requiredItems[k].stackSize) {
									gotRequiredItems++;
								}
							}
						}
					}
				}
				if(gotRequiredItems == requiredItems.length) {
					avalableRecipes.add(recipes.get(i));
				}
			}

			else if(currentRecipe instanceof ShapelessRecipes) {
				int gotRequiredItems = 0;
				List<ItemStack> requiredItems = ((ShapelessRecipes)recipes.get(i)).recipeItems;
				ItemStack output = ((ShapelessRecipes)recipes.get(i)).getRecipeOutput();
				if(output == null)
					continue;

				for(int k = 0; k < requiredItems.size(); k++) {
					for(int l = 0; l<inventoryItems.size();l++) {
						if(requiredItems.get(k).isItemEqual(inventoryItems.get(l))) {
							if(inventoryItems.get(l).stackSize >= requiredItems.get(k).stackSize) {
								gotRequiredItems++;
							}
						}
					}
				}
				if(gotRequiredItems == requiredItems.size()) {
					avalableRecipes.add(recipes.get(i));
				}
			}
		}
		return avalableRecipes;

	}

	/**
	 * finds all the items you can craft with what you have in your inventory.
	 * @param recipetype reprisents the recipetype it extends from.
	 * @param items items in players inventory.
	 * @param recipes list of all game recipes.
	 * @return list of avalable recipes using items in players inventory.
	 */
	private List compare(String recipetype, List items, Object recipe) {
		List avalableRecipes = new ArrayList();
		if(recipetype.equals("ShapedRecipes")) {

		}
		if(recipetype.equals("ShapelessRecipes")) {

		}
		if(recipetype.equals("RecipesArmorDyes")) {

		}
		if(recipetype.equals("RecipeBookCloning")) {

		}
		if(recipetype.equals("RecipesMapCloning")) {

		}



		return avalableRecipes;
	}
}
