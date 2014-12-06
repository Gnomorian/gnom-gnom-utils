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

package nz.co.crookedhill.ggutils.entity.tile;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import nz.co.crookedhill.ggutils.inventory.GGUInventoryMess;

public class GGUEntityModularCore extends TileEntity 
{
	private Random rand = new Random();
	private HashMap<String, ItemStack> numberOfLimbs = new HashMap<String, ItemStack>();
	private String owner;

	//need this for some reason...
	public GGUEntityModularCore(){}

	@Override
	public void updateEntity()
	{
		int[] coords = {xCoord, yCoord, zCoord};
		if(rand.nextInt(1000)==0)
		{
			this.numberOfLimbs.clear();
			calculateLimbs(coords, true);
		}
	}

	/**
	 * recursive method to calculate number of limbs
	 * @param coords array of ints of coords of the current limb
	 * @param shouldRetrieve should the call retrieve all itemstacks from limbs or put all itemstacks into limbs
	 */
	private void calculateLimbs(int[] coords, boolean shouldRetrive)
	{
		int numbOfSide = 6;
		int i;
		World world = this.worldObj;
		TileEntity tileEntity;
		String stringCoords = String.format("%s:%s:%s", Integer.toString(coords[0]), Integer.toString(coords[1]), Integer.toString(coords[2]));

		if(!world.isRemote)
		{
			/**
			 * Check all 6 sides if has limb.
			 */
			for(i=0; i<numbOfSide; i++)
			{ 
				int[] intCoords = new int[3];
				switch(i)
				{
				case 0:
					tileEntity = world.getTileEntity(coords[0]+1, coords[1], coords[2]);
					if(tileEntity != null && tileEntity instanceof GGUEntityModularLimb)
					{
						GGUEntityModularLimb entity = (GGUEntityModularLimb)tileEntity;

						stringCoords = String.format("%s:%s:%s", Integer.toString(coords[0]+1), Integer.toString(coords[1]), Integer.toString(coords[2]));
						if(!shouldRetrive)
						{
							entity.setActualStack(this.numberOfLimbs.get(stringCoords));
						}
						if(!this.numberOfLimbs.containsKey(stringCoords))
						{
							intCoords[0] = Integer.parseInt(stringCoords.split(":")[0]);
							intCoords[1] = Integer.parseInt(stringCoords.split(":")[1]);
							intCoords[2] = Integer.parseInt(stringCoords.split(":")[2]);
							if(shouldRetrive)
							{
								this.numberOfLimbs.put(stringCoords, entity.getActualStack());
								calculateLimbs(intCoords, true);
							}
							else
							{
								calculateLimbs(intCoords, false);
							}
						}
					}
					break;
				case 1:
					tileEntity = world.getTileEntity(coords[0]-1, coords[1], coords[2]);
					if(tileEntity != null && tileEntity instanceof GGUEntityModularLimb)
					{
						GGUEntityModularLimb entity = (GGUEntityModularLimb)tileEntity;

						stringCoords = String.format("%s:%s:%s", Integer.toString(coords[0]-1), Integer.toString(coords[1]), Integer.toString(coords[2]));
						if(!shouldRetrive)
						{
							entity.setActualStack(this.numberOfLimbs.get(stringCoords));
						}
						if(!this.numberOfLimbs.containsKey(stringCoords))
						{
							intCoords[0] = Integer.parseInt(stringCoords.split(":")[0]);
							intCoords[1] = Integer.parseInt(stringCoords.split(":")[1]);
							intCoords[2] = Integer.parseInt(stringCoords.split(":")[2]);
							if(shouldRetrive)
							{
								this.numberOfLimbs.put(stringCoords, entity.getActualStack());
								calculateLimbs(intCoords, true);
							}
							else
							{
								calculateLimbs(intCoords, false);
							}
						}

					}
					break;
				case 2:
					tileEntity = world.getTileEntity(coords[0], coords[1]+1, coords[2]);
					if(tileEntity != null && tileEntity instanceof GGUEntityModularLimb)
					{
						GGUEntityModularLimb entity = (GGUEntityModularLimb)tileEntity;

						stringCoords = String.format("%s:%s:%s", Integer.toString(coords[0]), Integer.toString(coords[1]+1), Integer.toString(coords[2]));
						intCoords[0] = Integer.parseInt(stringCoords.split(":")[0]);
						intCoords[1] = Integer.parseInt(stringCoords.split(":")[1]);
						intCoords[2] = Integer.parseInt(stringCoords.split(":")[2]);
						if(!shouldRetrive)
						{
							ItemStack stack = this.numberOfLimbs.get(stringCoords);
							entity.setActualStack(stack);
						}
						if(!this.numberOfLimbs.containsKey(stringCoords))
						{
							if(shouldRetrive)
							{
								this.numberOfLimbs.put(stringCoords, entity.getActualStack());
								calculateLimbs(intCoords, true);
							}
							else
							{
								calculateLimbs(intCoords, false);
							}
						}
					}
					break;
				case 3:
					tileEntity = world.getTileEntity(coords[0], coords[1]-1, coords[2]);
					if(tileEntity != null && tileEntity instanceof GGUEntityModularLimb)
					{
						GGUEntityModularLimb entity = (GGUEntityModularLimb)tileEntity;

						stringCoords = String.format("%s:%s:%s", Integer.toString(coords[0]), Integer.toString(coords[1]-1), Integer.toString(coords[2]));
						if(!shouldRetrive)
						{
							entity.setActualStack(this.numberOfLimbs.get(stringCoords));
						}
						if(!this.numberOfLimbs.containsKey(stringCoords))
						{
							intCoords[0] = Integer.parseInt(stringCoords.split(":")[0]);
							intCoords[1] = Integer.parseInt(stringCoords.split(":")[1]);
							intCoords[2] = Integer.parseInt(stringCoords.split(":")[2]);
							if(shouldRetrive)
							{
								this.numberOfLimbs.put(stringCoords, entity.getActualStack());
								calculateLimbs(intCoords, true);
							}
							else
							{
								calculateLimbs(intCoords, false);
							}
						}
					}
					break;
				case 4:
					tileEntity = world.getTileEntity(coords[0], coords[1], coords[2]+1);
					if(tileEntity != null && tileEntity instanceof GGUEntityModularLimb)
					{
						GGUEntityModularLimb entity = (GGUEntityModularLimb)tileEntity;


						stringCoords = String.format("%s:%s:%s", Integer.toString(coords[0]), Integer.toString(coords[1]), Integer.toString(coords[2]+1));
						if(!shouldRetrive)
						{
							entity.setActualStack(this.numberOfLimbs.get(stringCoords));
						}
						if(!this.numberOfLimbs.containsKey(stringCoords))
						{
							intCoords[0] = Integer.parseInt(stringCoords.split(":")[0]);
							intCoords[1] = Integer.parseInt(stringCoords.split(":")[1]);
							intCoords[2] = Integer.parseInt(stringCoords.split(":")[2]);
							if(shouldRetrive)
							{
								this.numberOfLimbs.put(stringCoords, entity.getActualStack());
								calculateLimbs(intCoords, true);
							}
							else
							{
								calculateLimbs(intCoords, false);
							}
						}
					}
					break;
				case 5:
					tileEntity = world.getTileEntity(coords[0], coords[1], coords[2]-1);
					if(tileEntity != null && tileEntity instanceof GGUEntityModularLimb)
					{
						GGUEntityModularLimb entity = (GGUEntityModularLimb)tileEntity;


						stringCoords = String.format("%s:%s:%s", Integer.toString(coords[0]), Integer.toString(coords[1]), Integer.toString(coords[2]-1));
						if(!shouldRetrive)
						{
							entity.setActualStack(this.numberOfLimbs.get(stringCoords));
						}
						if(!this.numberOfLimbs.containsKey(stringCoords))
						{
							intCoords[0] = Integer.parseInt(stringCoords.split(":")[0]);
							intCoords[1] = Integer.parseInt(stringCoords.split(":")[1]);
							intCoords[2] = Integer.parseInt(stringCoords.split(":")[2]);
							if(shouldRetrive)
							{
								this.numberOfLimbs.put(stringCoords, entity.getActualStack());
								calculateLimbs(intCoords, true);
							}
							else
							{
								calculateLimbs(intCoords, false);
							}
						}
					}
					break;
				default:
				}
			}
			return;
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);

		compound.setString("owner", this.owner);

		//		Iterator it = this.numberOfLimbs.entrySet().iterator();
		//		NBTTagList items = new NBTTagList();
		//		int i=0;
		//		
		//		while (it.hasNext()) {
		//			Map.Entry pairs = (Map.Entry)it.next();
		//			NBTTagCompound item = new NBTTagCompound();
		//			item.setByte("Slot", (byte) i);
		//			((ItemStack)pairs.getValue()).writeToNBT(item);
		//			items.appendTag(item);
		//			
		//			i++;
		//		}
		//		
		//		compound.setTag("messInv", items);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.owner = compound.getString("owner");

		//		Iterator it = this.numberOfLimbs.entrySet().iterator();
		//		int i=0;
		//
		//		NBTTagList items = compound.getTagList("messInv", Constants.NBT.TAG_COMPOUND);
		//		
		//		while (it.hasNext()) {
		//			Map.Entry pairs = (Map.Entry)it.next();
		//			NBTTagCompound item = items.getCompoundTagAt(i);
		//			byte slot = item.getByte("Slot");
		//			if (slot >= 0) {
		//				pairs.setValue(ItemStack.loadItemStackFromNBT(item));
		//			}
		//		}
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
	 * public method to force recalculation of limbs.
	 * @param coords of the core
	 */
	public void reCalculateLimbs(int[] coords, boolean getFromEntity)
	{
		if(getFromEntity)
		{
			this.numberOfLimbs.clear();
		}
		calculateLimbs(coords, getFromEntity);
	}

	/**
	 * Public method that returns the number of limbs.
	 * @return the number of limbs
	 */
	public int getNumberOfLimbs()
	{
		return this.numberOfLimbs.size();
	}

	/**
	 * Public method to check whether the player name is the owner of this block.
	 * @param player name to check
	 * @return true if owner
	 */
	public boolean isOwner(String player)
	{	
		return this.owner.equals(player) ? true : false;
	}

	/**
	 * Public method to set the owner of this block to the player
	 * @param player name to become owner
	 */
	public void setOwner(String player)
	{
		this.owner = player;
	}

	public ItemStack[] getItems()
	{
		ItemStack[] inv = new ItemStack[GGUInventoryMess.INV_SIZE];

		Iterator it = this.numberOfLimbs.entrySet().iterator();
		int i=0;

		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry)it.next();
			inv[i] = ((ItemStack)pairs.getValue());
			i++;
		}

		return inv;
	}

	public void setItems(ItemStack[] items)
	{
		ItemStack[] inv = items;
		int[] coords = {xCoord, yCoord, zCoord};
		Iterator<Entry<String, ItemStack>> it = this.numberOfLimbs.entrySet().iterator();
		int i=0;

		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry)it.next();
			pairs.setValue(items[i]);
			i++;
		}

		this.reCalculateLimbs(coords, false);
	}
}
