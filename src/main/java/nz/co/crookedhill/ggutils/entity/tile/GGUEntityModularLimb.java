/* 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. 
 */

package nz.co.crookedhill.ggutils.entity.tile;

import java.util.Random;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;

public class GGUEntityModularLimb extends TileEntity 
{
	Random rand = new Random();
	ItemStack stack;
	ItemStack actualStack;
	int count = 0;
	int actualStackSize;
	float rotateDeg = 0;

	//need this for some reason...
	public GGUEntityModularLimb(){}

	/**
	 * Constructor with initial itemstack to render
	 * @param stack
	 */
	public GGUEntityModularLimb(ItemStack stack)
	{
		this.stack = stack;
	}
	

	public GGUEntityModularLimb(ItemStack stack2, ItemStack actualStack2) {

		this.stack = stack2;
		this.actualStack = actualStack2;	
	}

	@Override
	public void updateEntity()
	{
		if(rand.nextInt(10000) == 0)
		{
			int maxItems = Item.itemRegistry.getKeys().size();
			int randomItem = rand.nextInt(maxItems);
			Item itemToStack = Item.getItemById(randomItem);
			
			//to make sure random item is never null
			while(itemToStack == null)
			{
				maxItems = Item.itemRegistry.getKeys().size();
				randomItem = rand.nextInt(maxItems);
				itemToStack = Item.getItemById(randomItem);
			}
			this.stack = new ItemStack(itemToStack);
			this.stack.stackSize = rand.nextInt(64);
		}

		if(this.rotateDeg <= 100.0f)
		{
			this.rotateDeg+=0.5f;
		}
		else
		{
			this.rotateDeg = -100.0f;
		}

	}

	@Override
	public void writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		NBTTagList items = new NBTTagList();

		NBTTagCompound item1 = new NBTTagCompound();
		NBTTagCompound item2 = new NBTTagCompound();

		if(this.stack != null)
		{
			this.stack.writeToNBT(item1);
			items.appendTag(item1);	
		}

		if(this.actualStack != null)
		{
			this.actualStack.writeToNBT(item2);
			compound.setInteger("stackSize", this.actualStack.stackSize);
			items.appendTag(item2);
		}
		compound.setTag("Items", items);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);

		NBTTagList items = compound.getTagList("Items", Constants.NBT.TAG_COMPOUND);
		NBTTagCompound item = items.getCompoundTagAt(0);
		this.stack = ItemStack.loadItemStackFromNBT(item);

		NBTTagCompound item2 = items.getCompoundTagAt(1);
		this.actualStack = ItemStack.loadItemStackFromNBT(item2);	
		this.actualStackSize = compound.getInteger("stackSize");
		
		if(this.actualStack != null)
		{
			this.actualStack.stackSize = this.actualStackSize;	
		}
	}

	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, nbt);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
	{
		readFromNBT(packet.func_148857_g());
	}

	/**
	 * Public method to get the stack to render.
	 * @return itemstack to render.
	 */
	public ItemStack getStack()
	{
		return this.stack;
	}

	/**
	 * Public method to get the rotation of the itemstack to render at
	 * @return angle as float
	 */
	public float getRotation()
	{
		return this.rotateDeg;
	}

	/**
	 * Public method to set the actual itemstack of the stored in block.
	 * @param stack - the itemstack to save to the block
	 */
	public void setActualStack(ItemStack stack)
	{
		this.actualStack = stack;
	}

	/**
	 * Public method to get the actual item stack stored in block.
	 * @return itemstack in block
	 */
	public ItemStack getActualStack()
	{
		return this.actualStack;
	}
		
	public int getActualStackSize()
	{
		return this.actualStackSize;
	}
}
