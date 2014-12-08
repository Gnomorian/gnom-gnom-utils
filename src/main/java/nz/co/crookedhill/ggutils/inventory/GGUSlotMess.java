package nz.co.crookedhill.ggutils.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class GGUSlotMess extends Slot
{
	public GGUSlotMess(IInventory inventory, int slotIndex, int x, int y) {
		super(inventory, slotIndex, x, y);
	}
	
	@Override
	public int getSlotStackLimit() {
		return Integer.MAX_VALUE;
	}
	
	@Override
	public boolean isItemValid(ItemStack p_75214_1_) {
		return true;
	}
}