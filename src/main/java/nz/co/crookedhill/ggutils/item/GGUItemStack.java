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
