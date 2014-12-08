/* 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. 
 */

package nz.co.crookedhill.ggutils.item;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import nz.co.crookedhill.ggutils.GGUtils;

public class GGUEnderiumRebirth extends Item
{
	private boolean isStable;

	/* stable selects the texture for the stable or unstable erb */
	public GGUEnderiumRebirth(boolean isStable)
	{
		this.isStable = isStable;
		this.setTextureName(GGUtils.MODID + ":" + "enderiumrebirth_texture");
		this.setMaxStackSize(1);
	}

	public void onCreated(ItemStack itemstack, World world, EntityPlayer player)
	{
		if (!this.isStable)
			itemstack.addEnchantment(Enchantment.fireAspect, 1);
	}

	public boolean isStable()
	{
		return isStable;
	}
}
