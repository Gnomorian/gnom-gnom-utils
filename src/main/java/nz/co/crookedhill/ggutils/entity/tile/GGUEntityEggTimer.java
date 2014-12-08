/* 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. 
 */

package nz.co.crookedhill.ggutils.entity.tile;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GGUEntityEggTimer extends TileEntity 
{
	private int delay;
	private boolean isActive = false;

	public GGUEntityEggTimer()
	{

	}


	@Override
	public void updateEntity()
	{
		World world = this.worldObj;
		if(worldObj.getBlockPowerInput(xCoord, yCoord, zCoord) <= 0)
		{
			this.isActive = false;
			this.delay = 0;
		}
		
		if(isActive){
			if(this.delay <= 0){
				world.spawnParticle("hugeexplosion", xCoord, yCoord+1, zCoord, 0.0f, 0.0f, 0.0f);
				world.createExplosion((Entity)null, xCoord, yCoord+1, zCoord, 5.0F, true);
				world.removeTileEntity(xCoord, yCoord, zCoord);
				world.setBlockToAir(xCoord, yCoord, zCoord);
				invalidate();
				this.setActive(false);
				this.setDelay(0);
			}
			this.delay--; 		
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		compound.setInteger("delay", this.delay);
		compound.setBoolean("isActive", this.isActive);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.delay = compound.getInteger("delay");
		this.isActive = compound.getBoolean("isActive");
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
	 * Sets the delay
	 * @param delay
	 */
	public void setDelay(int delay){
		this.delay = delay;
	}

	/**
	 * Gets the delay
	 * @return
	 */
	public int getDelay(){
		return this.delay;
	}

	/**
	 * Activates or decatives the timer
	 * @param active boolean to check if active
	 */
	public void setActive(boolean active){
		this.isActive = (active==true) ? true: false;
	}

	/**
	 * Get if timer is active.
	 * @return boolean if active or not
	 */
	public boolean getActive(){
		return this.isActive;
	}
}
