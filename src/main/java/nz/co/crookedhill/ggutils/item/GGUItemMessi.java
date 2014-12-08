/* 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. 
 */

package nz.co.crookedhill.ggutils.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import nz.co.crookedhill.ggutils.GGUtils;
import nz.co.crookedhill.ggutils.entity.tile.GGUEntityModularCore;
import nz.co.crookedhill.ggutils.extendedprops.GGUExtendedPlayer;

public class GGUItemMessi extends Item
{
	public GGUItemMessi()
	{
		this.setTextureName(GGUtils.MODID + ":" + "messi_texture");
		this.setMaxStackSize(1);
	}

	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if (!world.isRemote) 
		{
			if (!player.isSneaking()) 
			{
				GGUExtendedPlayer props = GGUExtendedPlayer.get(player);
				int[] coords = props.getMessCoords();
				GGUEntityModularCore te = (GGUEntityModularCore)player.worldObj.getTileEntity(coords[0], coords[1], coords[2]);
				if(te != null)
				{
					te.reCalculateLimbs(coords, true);	
					props.setNumberofLimbs(te.getNumberOfLimbs());
					
					player.openGui(GGUtils.instance, GGUtils.GUI_MESS_INV, player.worldObj, (int) player.posX, (int) player.posY, (int) player.posZ);
				}
				else
				{
					player.addChatMessage(new ChatComponentText("No MESS Core found."));
				}
			}
		}
		return stack;
	}


	// Without this method, your inventory will NOT work!!!
	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 1; // return any value greater than zero
	}
}
