/* 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. 
 */

package nz.co.crookedhill.ggutils.proxy;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import nz.co.crookedhill.ggutils.GGUtils;
import nz.co.crookedhill.ggutils.gui.GGUGuiMessi;
import nz.co.crookedhill.ggutils.inventory.GGUContainerMess;
import nz.co.crookedhill.ggutils.inventory.GGUInventoryMess;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class CommonProxy implements IGuiHandler
{
	private static final Map<String, NBTTagCompound> extendedEntityData = new HashMap<String, NBTTagCompound>();

	public void registerRenderers()
	{
	}

	/**
	 * Returns a side-appropriate EntityPlayer for use during message handling
	 */
	public EntityPlayer getPlayerEntity(MessageContext ctx)
	{
		return ctx.getServerHandler().playerEntity;
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if (ID == GGUtils.GUI_MESS_INV) {
			return new GGUContainerMess(player, player.inventory, new GGUInventoryMess(player.getHeldItem(), player));
			}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if (ID == GGUtils.GUI_MESS_INV) {
			return new GGUGuiMessi(player, player.inventory, new GGUInventoryMess(player.getHeldItem(), player));
		}
		return null;
	}

	/**
	 * Adds an entity's custom data to the map for temporary storage
	 * 
	 * @param compound
	 *            An NBT Tag Compound that stores the IExtendedEntityProperties
	 *            data only
	 */
	public static void storeEntityData(String name, NBTTagCompound compound)
	{
		extendedEntityData.put(name, compound);
	}

	/**
	 * Removes the compound from the map and returns the NBT tag stored for name
	 * or null if none exists
	 */
	public static NBTTagCompound getEntityData(String name)
	{
		return extendedEntityData.remove(name);
	}

	public void init()
	{

	}

	public void postInit()
	{
		// TODO Auto-generated method stub

	}
}
