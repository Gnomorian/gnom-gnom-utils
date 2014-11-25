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

import java.util.HashMap;
import java.util.Random;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

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
			calculateLimbs(coords);
		}

	}

	/**
	 * recursive method to calculate number of limbs
	 * @param coords array of ints of coords of the current limb
	 */
	private void calculateLimbs(int[] coords)
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
						if(!this.numberOfLimbs.containsKey(stringCoords))
						{
							this.numberOfLimbs.put(stringCoords, entity.getStack());
							intCoords[0] = Integer.parseInt(stringCoords.split(":")[0]);
							intCoords[1] = Integer.parseInt(stringCoords.split(":")[1]);
							intCoords[2] = Integer.parseInt(stringCoords.split(":")[2]);
							
							calculateLimbs(intCoords);
						}
					}
					break;
				case 1:
					tileEntity = world.getTileEntity(coords[0]-1, coords[1], coords[2]);
					if(tileEntity != null && tileEntity instanceof GGUEntityModularLimb)
					{
						GGUEntityModularLimb entity = (GGUEntityModularLimb)tileEntity;
						
						stringCoords = String.format("%s:%s:%s", Integer.toString(coords[0]-1), Integer.toString(coords[1]), Integer.toString(coords[2]));
						if(!this.numberOfLimbs.containsKey(stringCoords))
						{
							this.numberOfLimbs.put(stringCoords, entity.getStack());
							intCoords[0] = Integer.parseInt(stringCoords.split(":")[0]);
							intCoords[1] = Integer.parseInt(stringCoords.split(":")[1]);
							intCoords[2] = Integer.parseInt(stringCoords.split(":")[2]);
							
							calculateLimbs(intCoords);
						}
					}
					break;
				case 2:
					tileEntity = world.getTileEntity(coords[0], coords[1]+1, coords[2]);
					if(tileEntity != null && tileEntity instanceof GGUEntityModularLimb)
					{
						GGUEntityModularLimb entity = (GGUEntityModularLimb)tileEntity;
						
						stringCoords = String.format("%s:%s:%s", Integer.toString(coords[0]), Integer.toString(coords[1]+1), Integer.toString(coords[2]));
						if(!this.numberOfLimbs.containsKey(stringCoords))
						{
							this.numberOfLimbs.put(stringCoords, entity.getStack());
							intCoords[0] = Integer.parseInt(stringCoords.split(":")[0]);
							intCoords[1] = Integer.parseInt(stringCoords.split(":")[1]);
							intCoords[2] = Integer.parseInt(stringCoords.split(":")[2]);
							
							calculateLimbs(intCoords);
						}
					}
					break;
				case 3:
					tileEntity = world.getTileEntity(coords[0], coords[1]-1, coords[2]);
					if(tileEntity != null && tileEntity instanceof GGUEntityModularLimb)
					{
						GGUEntityModularLimb entity = (GGUEntityModularLimb)tileEntity;
						
						stringCoords = String.format("%s:%s:%s", Integer.toString(coords[0]), Integer.toString(coords[1]-1), Integer.toString(coords[2]));
						if(!this.numberOfLimbs.containsKey(stringCoords))
						{
							this.numberOfLimbs.put(stringCoords, entity.getStack());
							intCoords[0] = Integer.parseInt(stringCoords.split(":")[0]);
							intCoords[1] = Integer.parseInt(stringCoords.split(":")[1]);
							intCoords[2] = Integer.parseInt(stringCoords.split(":")[2]);
							
							calculateLimbs(intCoords);
						}
					}
					break;
				case 4:
					tileEntity = world.getTileEntity(coords[0], coords[1], coords[2]+1);
					if(tileEntity != null && tileEntity instanceof GGUEntityModularLimb)
					{
						GGUEntityModularLimb entity = (GGUEntityModularLimb)tileEntity;

						
						stringCoords = String.format("%s:%s:%s", Integer.toString(coords[0]), Integer.toString(coords[1]), Integer.toString(coords[2]+1));
						if(!this.numberOfLimbs.containsKey(stringCoords))
						{
							this.numberOfLimbs.put(stringCoords, entity.getStack());
							intCoords[0] = Integer.parseInt(stringCoords.split(":")[0]);
							intCoords[1] = Integer.parseInt(stringCoords.split(":")[1]);
							intCoords[2] = Integer.parseInt(stringCoords.split(":")[2]);
							
							calculateLimbs(intCoords);
						}
					}
					break;
				case 5:
					tileEntity = world.getTileEntity(coords[0], coords[1], coords[2]-1);
					if(tileEntity != null && tileEntity instanceof GGUEntityModularLimb)
					{
						GGUEntityModularLimb entity = (GGUEntityModularLimb)tileEntity;

						
						stringCoords = String.format("%s:%s:%s", Integer.toString(coords[0]), Integer.toString(coords[1]), Integer.toString(coords[2]-1));
						if(!this.numberOfLimbs.containsKey(stringCoords))
						{
							this.numberOfLimbs.put(stringCoords, entity.getStack());
							intCoords[0] = Integer.parseInt(stringCoords.split(":")[0]);
							intCoords[1] = Integer.parseInt(stringCoords.split(":")[1]);
							intCoords[2] = Integer.parseInt(stringCoords.split(":")[2]);
							
							calculateLimbs(intCoords);
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
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.owner = compound.getString("owner");
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
	public void recalculateLimbs(int[] coords)
	{
		this.numberOfLimbs.clear();
		calculateLimbs(coords);
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
}
