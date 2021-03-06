/* 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. 
 */

package nz.co.crookedhill.ggutils.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import nz.co.crookedhill.ggutils.entity.tile.GGUEntityModularCore;
import nz.co.crookedhill.ggutils.extendedprops.GGUExtendedPlayer;
import nz.co.crookedhill.ggutils.item.GGUItemMessi;

public class GGUInventoryMess implements IInventory
{
	/** The name your custom inventory will display in the GUI, possibly just "Inventory" */
	private final String name = "MESSI";
	/** Define the inventory size here for easy reference */
	// This is also the place to define which slot is which if you have different types,
	// for example SLOT_SHIELD = 0, SLOT_AMULET = 1;
	public static final int INV_SIZE = 96;
	/** Inventory's size must be same as number of slots you add to the Container class */
	private ItemStack[] inventory = new ItemStack[INV_SIZE];
	private final ItemStack invStack;
	private EntityPlayer player;

	public GGUInventoryMess(ItemStack stack, EntityPlayer entityPlayer) {
		this.invStack = stack;
		this.player = entityPlayer;
		this.inventory = getInventory(entityPlayer);
	}

	@Override
	public int getSizeInventory() {
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inventory[slot];		
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		ItemStack stack = getStackInSlot(slot);
		if (stack != null) {
			if (stack.stackSize > amount) {
				stack = stack.splitStack(amount);
				markDirty();
			} else {
				setInventorySlotContents(slot, null);
			}
		}
		return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		ItemStack stack = getStackInSlot(slot);
		setInventorySlotContents(slot, null);
		return stack;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		if(this.inventory == null)
		{
			this.inventory = new ItemStack[INV_SIZE];
		}
		this.inventory[slot] = stack;
		if (stack != null && stack.stackSize > getInventoryStackLimit()) {
			stack.stackSize = getInventoryStackLimit();
		}
		markDirty();
	}

	@Override
	public String getInventoryName() {
		return name;
	}
	@Override
	public boolean hasCustomInventoryName() {
		return name.length() > 0;
	}


	@Override
	public int getInventoryStackLimit() {
		return Integer.MAX_VALUE;
	}

	@Override
	public void markDirty() {
		for (int i = 0; i < getSizeInventory(); ++i) {
			if (getStackInSlot(i) != null && getStackInSlot(i).stackSize == 0)
				inventory[i] = null;
		}
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		// this will close the inventory if the player tries to move
		// the item that opened it, but you need to return this method
		// from the Container's canInteractWith method
		// an alternative would be to override the slotClick method and
		// prevent the current item slot from being clicked
		return player.getHeldItem() == invStack;
	}
	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return !(stack.getItem() instanceof GGUItemMessi);
	}

	public void readFromNBT(NBTTagCompound compound) {

	}

	public void writeToNBT(NBTTagCompound compound) {

	}

	private ItemStack[] getInventory(EntityPlayer player)
	{
		ItemStack[] inv = new ItemStack[GGUInventoryMess.INV_SIZE];
		if(!player.worldObj.isRemote)
		{
			GGUExtendedPlayer props = GGUExtendedPlayer.get(player);
			int[] coords = props.getMessCoords();
			GGUEntityModularCore te = (GGUEntityModularCore)player.worldObj.getTileEntity(coords[0], coords[1], coords[2]);

			inv = te.getItems();	
		}
		return inv;
	}
}