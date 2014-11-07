package nz.co.crookedhill.ggutils.util;

import java.util.ArrayList;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import nz.co.crookedhill.ggutils.item.GGUItemStack;

public class GGUSort {
	
	public static void sort(IInventory inventory) {
		/*amount of slots in the inventory*/
		int maxInventory = inventory.getSizeInventory();
		
		/*max stack size in one slot in the inventory*/
		int maxSlot = inventory.getInventoryStackLimit();
		
		/*the list of found unique itemstacks in the inventory*/
		ArrayList<GGUItemStack> items = new ArrayList<GGUItemStack>();
		
		
		/*get a copy of all the unique itemstacks within the inventory and quantity of items*/
		for(int i = 0; i < maxInventory; i++) {
			ItemStack currentSlot = inventory.getStackInSlot(i);
			if(currentSlot != null) {
				if(items.size()>0){
					boolean isExistant = false;
				loopItems:for(GGUItemStack inItem : items) {
					//if item is already in the array, change stacksize
					if(inItem.compareTo(currentSlot)) {
						inItem.setQuantity(inItem.getQuantity()+currentSlot.stackSize);
						isExistant = true;
						break loopItems;
						
					}
				}
					/*if the item doesnt exist, add it*/
				if(isExistant == false){
					items.add(new GGUItemStack(currentSlot));
				}
				}else
					/*if the item array is empty, add the itemstack to the array*/
					items.add(new GGUItemStack(currentSlot));
			}
		}
		/*currInventory is the current inventory slot we are looking at.
		 * i is the item in the items list that we are looking at.*/
		int currInventory = 0;
		/*add the items array to the inventory in order so it is sorted*/
		for(int i = 0; i<items.size(); i++) {
			
			/*if the itemstack stacksize is bigger than the stacksizes limit,
			 * overflow the exess items to the next slot while its bigger*/
			if(items.get(i).getQuantity() > items.get(i).getItemStack().getMaxStackSize()){
				while(items.get(i).getQuantity() > items.get(i).getItemStack().getMaxStackSize()) {
					items.get(i).setQuantity(items.get(i).getQuantity()-items.get(i).getItemStack().getMaxStackSize( ));
					ItemStack newitemstack = items.get(i).getItemStack();
					newitemstack.stackSize = items.get(i).getItemStack().getMaxStackSize();
					inventory.setInventorySlotContents(currInventory, newitemstack);
					currInventory++;
				}
				/*when the overflow is done, place the rest of the items*/
				items.get(i).getItemStack().stackSize = items.get(i).getQuantity();
				inventory.setInventorySlotContents(currInventory, items.get(i).getItemStack());
				currInventory++;
			}
			
			
			/*if the itemstack is bigger than the slot limit in the inventory,
			 * overflow the excess items to the next slot while its bigger*/
			if(items.get(i).getQuantity() > maxSlot){
				while(items.get(i).getQuantity() > maxSlot) {
					items.get(i).setQuantity(items.get(i).getQuantity()-maxSlot);
					ItemStack newitemstack = items.get(i).getItemStack();
					newitemstack.stackSize = maxSlot;
					inventory.setInventorySlotContents(currInventory, newitemstack);
					currInventory++;
				}
				/*when the overflow is done, place the rest of the items*/
				items.get(i).getItemStack().stackSize = items.get(i).getQuantity();
				inventory.setInventorySlotContents(currInventory, items.get(i).getItemStack());
				currInventory++;

			}else {
				ItemStack setItem = items.get(i).getItemStack();
				setItem.stackSize = items.get(i).getQuantity();
				inventory.setInventorySlotContents(currInventory, setItem);
				currInventory++;
			}
		}
		/*when all items from the list have been added, set the 
		 * remaining inventory slots to null to delete duplicates*/
		
		for(int i = currInventory; i < maxInventory; i++) {
			inventory.setInventorySlotContents(i, null);
		}
	}

}
