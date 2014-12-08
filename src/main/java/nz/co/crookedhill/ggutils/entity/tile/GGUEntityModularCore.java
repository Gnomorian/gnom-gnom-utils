/* 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. 
 */

package nz.co.crookedhill.ggutils.entity.tile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

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
	private ArrayList<String> tempCoordArray = new ArrayList<String>();
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
							intCoords[0] = Integer.parseInt(stringCoords.split(":")[0]);
							intCoords[1] = Integer.parseInt(stringCoords.split(":")[1]);
							intCoords[2] = Integer.parseInt(stringCoords.split(":")[2]);
							if(shouldRetrive)
							{
								this.numberOfLimbs.put(stringCoords, entity.getActualStack());
								calculateLimbs(intCoords, true);
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

						if(!this.numberOfLimbs.containsKey(stringCoords))
						{
							if(shouldRetrive)
							{
								this.numberOfLimbs.put(stringCoords, entity.getActualStack());
								calculateLimbs(intCoords, true);
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
							intCoords[0] = Integer.parseInt(stringCoords.split(":")[0]);
							intCoords[1] = Integer.parseInt(stringCoords.split(":")[1]);
							intCoords[2] = Integer.parseInt(stringCoords.split(":")[2]);
							if(shouldRetrive)
							{
								this.numberOfLimbs.put(stringCoords, entity.getActualStack());
								calculateLimbs(intCoords, true);
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
	public void reCalculateLimbs(int[] coords, boolean getFromEntity)
	{
		if(getFromEntity)
		{
			this.numberOfLimbs.clear();
		}

		this.tempCoordArray.clear();
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

		Iterator<Entry<String, ItemStack>> it = this.numberOfLimbs.entrySet().iterator();
		int i=0;

		while (it.hasNext()) {
			Map.Entry<String, ItemStack> pairs = (Entry<String, ItemStack>)it.next();
			inv[i] = ((ItemStack)pairs.getValue());
			i++;
		}

		return inv;
	}

	public void setItems(ItemStack[] items)
	{
		World world = this.worldObj;
		int[] intCoords = new int[3];
		Iterator<Entry<String, ItemStack>> it = this.numberOfLimbs.entrySet().iterator();
		int i=0;

		while (it.hasNext()) {
			Map.Entry<String, ItemStack> pairs = (Entry<String, ItemStack>)it.next();
			pairs.setValue(items[i]);
			intCoords[0] = Integer.parseInt(((String)pairs.getKey()).split(":")[0]);
			intCoords[1] = Integer.parseInt(((String)pairs.getKey()).split(":")[1]);
			intCoords[2] = Integer.parseInt(((String)pairs.getKey()).split(":")[2]);
			GGUEntityModularLimb te = (GGUEntityModularLimb) world.getTileEntity(intCoords[0], intCoords[1], intCoords[2]);
			te.setActualStack((ItemStack)pairs.getValue());
			i++;
		}
	}
}
