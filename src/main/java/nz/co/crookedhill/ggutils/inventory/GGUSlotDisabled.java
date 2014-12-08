package nz.co.crookedhill.ggutils.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class GGUSlotDisabled extends Slot
{
	public GGUSlotDisabled(IInventory inventory, int slotIndex, int x, int y) {
		super(inventory, slotIndex, x, y);
	}
	
	@Override
	public boolean isItemValid(ItemStack p_75214_1_) {
		return false;
	}
}