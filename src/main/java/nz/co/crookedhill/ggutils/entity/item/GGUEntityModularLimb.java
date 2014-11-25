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

package nz.co.crookedhill.ggutils.entity.item;

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
}
