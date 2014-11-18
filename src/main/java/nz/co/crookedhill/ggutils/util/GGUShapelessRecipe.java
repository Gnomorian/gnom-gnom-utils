package nz.co.crookedhill.ggutils.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapelessRecipes;

public class GGUShapelessRecipe 
{
	ItemStack[] recipeItems;
	ItemStack recipeOutput;
	public GGUShapelessRecipe(ShapelessRecipes oldRecipe)
	{
		List<ItemStack> tempList = new ArrayList<ItemStack>();
		breakCurr:for(ItemStack currItem : (List<ItemStack>)oldRecipe.recipeItems) {
			/* if the temporary list is empty, add current itemstack */
			if(tempList.size()==0)
				tempList.add(currItem);
			else
				for(int i = 0; i < tempList.size(); i++){
					/* if the current item is in the temp list, add its stacksize
					 * to the templists stacksize and stop looping though the
					 * templist
					 */
					if(currItem == null)
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
		recipeOutput = oldRecipe.getRecipeOutput();
				
	}
	public ItemStack getRecipeOutput() 
	{
		return recipeOutput;
	}
}
