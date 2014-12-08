/* 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. 
 */

package nz.co.crookedhill.ggutils.item;

import net.minecraft.item.ItemStack;

public class GGUItemStack 
{
	private ItemStack item;
	private int quantity;
	
	public GGUItemStack(ItemStack item) 
	{
		this.item = item;
		this.quantity = item.stackSize;
	}
	public void setQuantity(int quantity) 
	{
		this.quantity = quantity;
	}
	public int getQuantity() 
	{
		return this.quantity;
	}
	public ItemStack getItemStack() 
	{
		item.stackSize = 1;
		return item;
	}
	public boolean compareTo(ItemStack comparison) 
	{
		item.stackSize = comparison.stackSize;
		if(item.isItemEqual(comparison))
			return true;
		else
			return false;
		
	}
}
