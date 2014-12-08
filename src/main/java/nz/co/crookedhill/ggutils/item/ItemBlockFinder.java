/* 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. 
 */

package nz.co.crookedhill.ggutils.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import nz.co.crookedhill.ggutils.GGUtils;

public class ItemBlockFinder extends Item 
{

	public ItemBlockFinder()
	{
		this.setMaxStackSize(1);
		this.setTextureName(GGUtils.MODID + ":blockFinder_texture");
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) 
	{
		
		return super.onItemRightClick(itemStack, world, player);
	}
}
