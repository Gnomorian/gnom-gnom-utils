/* 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. 
 */

package nz.co.crookedhill.ggutils.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import nz.co.crookedhill.ggutils.GGUtils;
import nz.co.crookedhill.ggutils.entity.GGUEntityWockyJab;

public class GGUItemWockyJab extends ItemSword
{
	public GGUItemWockyJab(ToolMaterial material)
	{
		super(material);
		this.setTextureName(GGUtils.MODID + ":wockyJab_texture");
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) 
	{
		player.setItemInUse(itemStack, getMaxItemUseDuration(itemStack));
		return itemStack;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack itemStack) {
		return EnumAction.bow;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack itemStack) {
		return 1000;
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack itemStack, World world, EntityPlayer player, int count) 
	{
		int time = this.getMaxItemUseDuration(itemStack) - count;

		if(time > 30)
		{
			world.spawnEntityInWorld(new GGUEntityWockyJab(world, player));		
		}
	}
}
