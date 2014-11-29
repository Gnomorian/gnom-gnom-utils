package nz.co.crookedhill.ggutils.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import nz.co.crookedhill.ggutils.extendedprops.GGUExtendedPlayer;

public class GGUContainerMess extends Container
{

	/** The Item Inventory for this Container */
	private final GGUInventoryMess inventory;
	private static int numberOfLimbs;
	
	public int numberOfSlots;
	
	/** Avoid magic numbers! This will greatly reduce the chance of you making errors in 'transferStackInSlot' method */
	private static final int
	INV_START = GGUInventoryMess.INV_SIZE, INV_END = INV_START+26,
	HOTBAR_START = INV_END+1, HOTBAR_END = HOTBAR_START+8;

	public GGUContainerMess(EntityPlayer player, InventoryPlayer inventoryPlayer, GGUInventoryMess inventoryCustom) {

		GGUExtendedPlayer props = GGUExtendedPlayer.get(player);
		this.numberOfSlots = props.getNumberOfLimbs();
		
		if(this.numberOfSlots > 0)
		{
			GGUContainerMess.numberOfLimbs = numberOfSlots;
		}
		
		if(GGUContainerMess.numberOfLimbs > 0)
		{
			this.numberOfSlots = GGUContainerMess.numberOfLimbs;
		}
		this.inventory = inventoryCustom;

		int i;
		int j;
		int slotCount = 0;
		
		for (i = 0; i < 8; ++i) {
			for (j = 0; j < 12; ++j) {
				if(slotCount < numberOfSlots)
				{
					addSlotToContainer(new GGUSlotMess(this.inventory, j+i*9, 8 + j * 18, 21 + i * 18));
					System.out.println("Mess " + (j+i*12)); //DEBUG
				}
				else
				{
					addSlotToContainer(new GGUSlotDisabled(this.inventory, j+i*9, 8 + j * 18, 21 + i * 18));
					System.out.println("Disabled " + (j+i*12)); //DEBUG
				}
				slotCount++;
			}
		}
		// Add vanilla PLAYER INVENTORY - just copied/pasted from vanilla classes
		for (i = 0; i < 3; ++i) {
			for (j = 0; j < 9; ++j) {
				addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 171 + i * 18));
			}
		}
		// Add ACTION BAR - just copied/pasted from vanilla classes
		for (i = 0; i < 9; ++i) {
			addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 229));

		}
	}

	/**
	 * This should always return true, since custom inventory can be accessed from anywhere
	 */
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}
	/**
	 * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
	 * Basically the same as every other container I make, since I define the same constant indices for all of them
	 */
	public ItemStack transferStackInSlot(EntityPlayer player, int index)
	{
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(index);
		if (slot != null && slot.getHasStack())
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			// If item is in our custom Inventory or an ARMOR slot
			if (index < INV_START)
			{
				// try to place in player inventory / action bar
				if (!this.mergeItemStack(itemstack1, INV_START, HOTBAR_END+1, true))
				{
					return null;
				}
				slot.onSlotChange(itemstack1, itemstack);
			}
			if (itemstack1.stackSize == 0) {
				slot.putStack((ItemStack) null);
			} else {
				slot.onSlotChanged();
			}
			if (itemstack1.stackSize == itemstack.stackSize) {
				return null;
			}
			slot.onPickupFromSlot(player, itemstack1);
		}
		return itemstack;
	}

	@Override
	public ItemStack slotClick(int slot, int button, int flag, EntityPlayer player) {
		// this will prevent the player from interacting with the item that opened the inventory:
		if (slot >= 0 && getSlot(slot) != null && getSlot(slot).getStack() == player.getHeldItem()) {
			return null;
		}
		return super.slotClick(slot, button, flag, player);
	}

	@Override
	protected boolean mergeItemStack(ItemStack stack, int start, int end, boolean backwards)
	{
		boolean flag1 = false;
		int k = (backwards ? end - 1 : start);
		Slot slot;
		ItemStack itemstack1;
		if (stack.isStackable())
		{
			while (stack.stackSize > 0 && (!backwards && k < end || backwards && k >= start))
			{
				slot = (Slot) inventorySlots.get(k);
				itemstack1 = slot.getStack();
				if (!slot.isItemValid(stack)) {
					k += (backwards ? -1 : 1);
					continue;
				}
				if (itemstack1 != null && itemstack1.getItem() == stack.getItem() &&
						(!stack.getHasSubtypes() || stack.getItemDamage() == itemstack1.getItemDamage()) &&
						ItemStack.areItemStackTagsEqual(stack, itemstack1))
				{
					int l = itemstack1.stackSize + stack.stackSize;
					if (l <= stack.getMaxStackSize() && l <= slot.getSlotStackLimit()) {
						stack.stackSize = 0;
						itemstack1.stackSize = l;
						inventory.markDirty();
						flag1 = true;
					} else if (itemstack1.stackSize < stack.getMaxStackSize() && l < slot.getSlotStackLimit()) {
						stack.stackSize -= stack.getMaxStackSize() - itemstack1.stackSize;
						itemstack1.stackSize = stack.getMaxStackSize();
						inventory.markDirty();
						flag1 = true;
					}
				}
				k += (backwards ? -1 : 1);
			}
		}
		if (stack.stackSize > 0)
		{
			k = (backwards ? end - 1 : start);
			while (!backwards && k < end || backwards && k >= start) {
				slot = (Slot) inventorySlots.get(k);
				itemstack1 = slot.getStack();
				if (!slot.isItemValid(stack)) {
					k += (backwards ? -1 : 1);
					continue;
				}
				if (itemstack1 == null) {
					int l = stack.stackSize;
					if (l <= slot.getSlotStackLimit()) {
						slot.putStack(stack.copy());
						stack.stackSize = 0;
						inventory.markDirty();
						flag1 = true;
						break;
					} else {
						putStackInSlot(k, new ItemStack(stack.getItem(), slot.getSlotStackLimit(), stack.getItemDamage()));
						stack.stackSize -= slot.getSlotStackLimit();
						inventory.markDirty();
						flag1 = true;
					}
				}
				k += (backwards ? -1 : 1);
			}
		}
		return flag1;
	}
}

