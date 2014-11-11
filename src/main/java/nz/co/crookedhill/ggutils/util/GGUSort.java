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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class GGUSort 
{

	public static void sort(IInventory inventory) 
	{
		/*amount of slots in the inventory*/
		int maxInventory = inventory.getSizeInventory();
		/*max stack size in one slot in the inventory*/
		int maxSlot = inventory.getInventoryStackLimit();
		/*the list of found unique itemstacks in the inventory*/
		ArrayList<ItemStack> items = new ArrayList<ItemStack>();


		/*get a copy of all the unique itemstacks within the inventory and quantity of items*/
		for(int i = 0; i < maxInventory; i++) 
		{
			ItemStack currentSlot = inventory.getStackInSlot(i);
			if(currentSlot != null) 
			{
				if(items.size()>0)
				{
					if(currentSlot.isStackable())
					{
						boolean isExistant = false;
						loopItems:for(ItemStack inItem : items) 
						{
							//if item is already in the array, change stacksize
							if(inItem.isItemEqual(currentSlot)) 
							{
								inItem.stackSize += currentSlot.stackSize;
								isExistant = true;
								break loopItems;

							}
						}
						/*if the item doesnt exist, add it*/
						if(isExistant == false)
						{
							ItemStack itemstack = currentSlot;
							items.add(itemstack);
						}
					}else
					{
						ItemStack newitemstack = currentSlot;
						items.add(newitemstack);
					}
				}else
				{
					/*if the item array is empty, add the itemstack to the array*/
					ItemStack newitemstack = currentSlot;
					items.add(newitemstack);
				}
			}
		}
		
		sortByUnlocName(items);
		
		/*currInventory is the current inventory slot we are looking at.
		 * i is the item in the items list that we are looking at.*/
		int currInventory = 0;
		/*add the items array to the inventory in order so it is sorted*/
		for(int i = 0; i<items.size(); i++) 
		{

			/*if the itemstack stacksize is bigger than the stacksizes limit,
			 * overflow the exess items to the next slot while its bigger*/
			if(items.get(i).getMaxStackSize() > maxSlot) 
			{
				List<ItemStack> newStack = splitItemStackByInvStack(items.get(i), maxSlot);
				for(ItemStack stack : newStack) 
				{
					inventory.setInventorySlotContents(currInventory, stack);
					currInventory++; 
				}
			}else 
			{
				List<ItemStack> newStack = splitItemStackByMaxStack(items.get(i));
				newStack = splitItemStackByInvStack(newStack, maxSlot);
				for(ItemStack stack : newStack) 
				{
					inventory.setInventorySlotContents(currInventory, stack);
					currInventory++;
				}
			}
			for(int j = currInventory; j < maxInventory; j++) 
			{
				inventory.setInventorySlotContents(j, null);
			}
		}
	}
	/**
	 * gets an itemstack and splits it into multiple stacks that
	 * fit its itemStack limit.
	 * @param itemstack
	 * @return list of split itemstacks
	 */
	private static List<ItemStack> splitItemStackByMaxStack(ItemStack itemstack) 
	{
		List<ItemStack> splitStacks = new ArrayList<ItemStack>();
		if(itemstack.getMaxStackSize() < itemstack.stackSize) 
		{
			float exess = itemstack.stackSize%itemstack.getMaxStackSize();
			float fullstacks = itemstack.stackSize/itemstack.getMaxStackSize();
			for(int i = 0; i < fullstacks; i++) 
			{
				ItemStack copyItemstack = itemstack;
				copyItemstack.stackSize = itemstack.getMaxStackSize();
				splitStacks.add(copyItemstack);
			}
			if(exess != 0){
				ItemStack exessItemStack = itemstack; 
				exessItemStack.stackSize = (int) exess;
				splitStacks.add(exessItemStack);
			}
		}
		else
			splitStacks.add(itemstack);
		return splitStacks;

	}
	/**
	 * gets a list of itemstacks and splits them into multiple stacks that
	 * fit their itemStack limit.
	 * @param itemstack
	 * @return list of split itemstacks
	 */
//	private static List<ItemStack> splitItemStackByMaxStack(List<ItemStack> itemstacks) {
//		List<ItemStack> splitStacks = new ArrayList<ItemStack>();
//		for(ItemStack itemstack : itemstacks){
//			if(itemstack.getMaxStackSize() < itemstack.stackSize) {
//				float exess = itemstack.stackSize%itemstack.getMaxStackSize();
//				float fullstacks = itemstack.stackSize/itemstack.getMaxStackSize();
//				for(int i = 0; i < fullstacks; i++) {
//					ItemStack copyItemstack = itemstack;
//					copyItemstack.stackSize = itemstack.getMaxStackSize();
//					splitStacks.add(copyItemstack);
//				}
//				if(exess != 0){
//					ItemStack exessItemStack = itemstack; 
//					exessItemStack.stackSize = (int) exess;
//					splitStacks.add(exessItemStack);
//				}
//			}
//			else
//				splitStacks.add(itemstack);
//		}
//		return splitStacks;
//
//	}
	/**
	 * gets an itemstack and splits it into multiple stacks that fit the 
	 * inventories slot limit
	 * @param itemstack
	 * @param maxSlot
	 * @return list of split itemstacks
	 */
	private static List<ItemStack> splitItemStackByInvStack(ItemStack itemstack, int maxSlot) 
	{
		List<ItemStack> splitStacks = new ArrayList<ItemStack>();
		if(maxSlot < itemstack.stackSize)
		{
			float exess = itemstack.stackSize%maxSlot;
			float fullstacks = itemstack.stackSize/maxSlot;
			for(int i = 0; i < fullstacks; i++) 
			
			{
				ItemStack copyItemstack = itemstack;
				copyItemstack.stackSize = maxSlot;
				splitStacks.add(copyItemstack);
			}
			if(exess != 0){
				ItemStack exessItemStack = itemstack; 
				exessItemStack.stackSize = (int) exess;
				splitStacks.add(exessItemStack);
			}
		}
		else
			splitStacks.add(itemstack);
		return splitStacks;

	}
	/**
	 * gets a list of  itemstacks and splits them into multiple stacks that fit the 
	 * inventories slot limit
	 * @param itemstacks
	 * @param maxSlot
	 * @return list of split item stacks
	 */
	private static List<ItemStack> splitItemStackByInvStack(List<ItemStack> itemstacks, int maxSlot) 
	{
		List<ItemStack> splitStacks = new ArrayList<ItemStack>();
		for(ItemStack itemstack : itemstacks) 
		{
			if(maxSlot < itemstack.stackSize) 
			{
				float exess = itemstack.stackSize%maxSlot;
				float fullstacks = itemstack.stackSize/maxSlot;
				for(int i = 0; i < fullstacks; i++) 
				{
					ItemStack copyItemstack = itemstack;
					copyItemstack.stackSize = maxSlot;
					splitStacks.add(copyItemstack);
				}
				if(exess != 0)
				{
					ItemStack exessItemStack = itemstack; 
					exessItemStack.stackSize = (int) exess;
					splitStacks.add(exessItemStack);
				}
			}
			else
				splitStacks.add(itemstack);
		}
		return splitStacks;

	}

	/**
	 * Sort the itemstack array by unlocalized name
	 * @param itemStack itemstack array to sort
	 * @return sorted itemstack
	 */
	private static List<ItemStack> sortByUnlocName(ArrayList<ItemStack> itemStack)
	{
		//Sort by unloc name.
		Collections.sort(itemStack, new Comparator<ItemStack>() {
		        @Override
		        public int compare(ItemStack  stack1, ItemStack  stack2)
		        {

		            return  stack1.getDisplayName().compareTo(stack2.getDisplayName());
		        }
		    });

		return itemStack;
	}
}
