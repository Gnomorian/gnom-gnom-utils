package nz.co.crookedhill.ggutils.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
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

		//for shaped recipes
		for(ShapedRecipes recipe : GGURecipeManager.getShaped()) {
			int gotRequiredItems = 0;
			for(ItemStack requiredItem : recipe.recipeItems) {
				if(requiredItem == null)
					continue;
				for(ItemStack invItem : inventoryItems) {
					if(invItem == null)
						continue;
					if(requiredItem.isItemEqual(invItem)&&requiredItem.stackSize <= invItem.stackSize)
						gotRequiredItems++;
				}
			}
			if(recipe.recipeItems.length == gotRequiredItems)
				avalableRecipes.add(recipe);
		}

		//for shapeless recipes
		for(ShapelessRecipes recipe : GGURecipeManager.getShapeless()) {
			int gotRequiredItems = 0;
			for(int i = 0; i < recipe.recipeItems.size(); i++) {
				ItemStack requiredItem = (ItemStack)recipe.recipeItems.get(i);
				if(requiredItem == null)
					continue;
				for(ItemStack invItem : inventoryItems) {
					if(invItem == null)
						continue;
					if(requiredItem.isItemEqual(invItem)&&requiredItem.stackSize <= invItem.stackSize)
						gotRequiredItems++;
				}
			}
			if(recipe.recipeItems.size() == gotRequiredItems)
				avalableRecipes.add(recipe);
		}
		System.out.println(avalableRecipes.size());
		return avalableRecipes;
	}
}
