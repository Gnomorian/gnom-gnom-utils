package nz.co.crookedhill.ggutils.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import nz.co.crookedhill.ggutils.extendedprops.GGUExtendedPlayer;
import nz.co.crookedhill.ggutils.item.GGUItemMessi;

public class GGUInventoryMess implements IInventory
{
	/** The name your custom inventory will display in the GUI, possibly just "Inventory" */
	private final String name = "MESSI";
	/** The key used to store and retrieve the inventory from NBT */
	private final String tagName = "MessInvTag";
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
		//		if (!invStack.hasTagCompound()) {
		//			invStack.setTagCompound(new NBTTagCompound());
		//		}
		this.inventory = getInventory(entityPlayer);
	}

	@Override
	public int getSizeInventory() {
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		if(slot >= inventory.length)
		{
			ItemStack stack = new ItemStack(Items.bow);
			stack = null;
			return stack;
		}
		else
		{
			return inventory[slot];		
		}
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

		//setInventory(this.player);
		//writeToNBT(invStack.getTagCompound());
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
		//		NBTTagList items = compound.getTagList(tagName, Constants.NBT.TAG_COMPOUND);
		//		for (int i = 0; i < items.tagCount(); ++i) {
		//			NBTTagCompound item = items.getCompoundTagAt(i);
		//			byte slot = item.getByte("Slot");
		//			if (slot >= 0 && slot < getSizeInventory()) {
		//				inventory[slot] = ItemStack.loadItemStackFromNBT(item);
		//			}
		//		}
	}

	public void writeToNBT(NBTTagCompound compound) {
		//		NBTTagList items = new NBTTagList();
		//		for (int i = 0; i < getSizeInventory(); ++i) {
		//			if (getStackInSlot(i) != null) {
		//				NBTTagCompound item = new NBTTagCompound();
		//				item.setByte("Slot", (byte) i);
		//				getStackInSlot(i).writeToNBT(item);
		//				items.appendTag(item);
		//			}
		//		}
		//		compound.setTag(tagName, items);
	}

	private ItemStack[] getInventory(EntityPlayer player)
	{
		GGUExtendedPlayer props = GGUExtendedPlayer.get(player);
		return props.getInventory();
	}
}