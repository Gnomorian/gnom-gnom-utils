/* 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. 
 */

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