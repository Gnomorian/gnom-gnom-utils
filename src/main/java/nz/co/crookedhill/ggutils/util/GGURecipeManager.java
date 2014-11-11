package nz.co.crookedhill.ggutils.util;

import java.util.List;
import java.util.ArrayList;

import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;

public class GGURecipeManager {
	
	private static List<ShapedRecipes> shapedRecipes = new ArrayList<ShapedRecipes>();
	private static List<ShapelessRecipes> shapelessRecipes = new ArrayList<ShapelessRecipes>();
	
	/**
	 * gets the list of recipes from the crafting manager and sorts them into multiple lists to 
	 * reduse lookup time during the game.
	 * @param recipeList list of recipes from CraftingManager
	 */
	public static void init(List recipeList) {
		for(int i = 0; i<recipeList.size();i++) {
			if(recipeList.get(i) instanceof ShapedRecipes) {
				//if(((ShapedRecipes)recipeList.get(i)).getRecipeOutput() != null)
					shapedRecipes.add(((ShapedRecipes)recipeList.get(i)));
			}
			else if(recipeList.get(i) instanceof ShapelessRecipes) {
				if(((ShapelessRecipes)recipeList.get(i)).getRecipeOutput() != null)
					shapelessRecipes.add(((ShapelessRecipes)recipeList.get(i)));
			}
		}
	}
	
	public static List<ShapedRecipes> getShaped() {
		return shapedRecipes;
		
	}
	public static List<ShapelessRecipes> getShapeless() {
		return shapelessRecipes;
		
	}
}
