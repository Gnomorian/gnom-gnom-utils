package nz.co.crookedhill.ggutils.item;

import net.minecraft.item.ItemStack;

public class GGUItemStack {
	private ItemStack item;
	private int quantity;
	
	public GGUItemStack(ItemStack item) {
		this.item = item;
		this.quantity = item.stackSize;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getQuantity() {
		return this.quantity;
	}
	public ItemStack getItemStack() {
		item.stackSize = 1;
		return item;
	}
	public boolean compareTo(ItemStack comparison) {
		item.stackSize = comparison.stackSize;
		if(item.isItemEqual(comparison))
			return true;
		else
			return false;
		
	}
}
