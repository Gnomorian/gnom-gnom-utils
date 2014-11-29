package nz.co.crookedhill.ggutils.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipes;

public class GGUShapedRecipe 
{
	ItemStack[] recipeItems;
	ItemStack recipeOutput;
	public GGUShapedRecipe(ShapedRecipes oldRecipe)
	{
		recipeOutput = oldRecipe.getRecipeOutput();
		List<ItemStack> tempList = new ArrayList<ItemStack>();
		breakCurr:for(ItemStack currItem : oldRecipe.recipeItems) {
			/* if the temporary list is empty, add current itemstack */
			if(tempList.size()==0)
				tempList.add(currItem);
			else
				for(int i = 0; i < tempList.size(); i++){
					/* if the current item is in the temp list, add its stacksize
					 * to the templists stacksize and stop looping though the
					 * templist
					 */
					if(currItem == null || tempList.get(i) == null)
						continue;
					if(tempList.get(i).getItem() == currItem.getItem()) {
						tempList.get(i).stackSize +=currItem.stackSize;
						break breakCurr;
					}
				}
			/* if current item isnt in the temp list, add it */
			tempList.add(currItem);
		}
		recipeItems = new ItemStack[tempList.size()];
		recipeItems =  tempList.toArray(recipeItems);
	}
	public ItemStack getRecipeOutput() 
	{
		return recipeOutput;
	}
}
