/* 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. 
 */

package nz.co.crookedhill.ggutils.item;

import net.minecraft.block.BlockRailBase;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemMinecart;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import nz.co.crookedhill.ggutils.GGUtils;
import nz.co.crookedhill.ggutils.entity.tile.GGUEntityAutoCart;

/**
 * @author william-cameron1994
 *
 */
public class GGUItemAutoCart extends ItemMinecart
{
	public GGUItemAutoCart(int minecartType) {
		super(minecartType);

		this.setTextureName(GGUtils.MODID + ":" + "autoCart_Texture");
	}

	@Override
	public boolean onItemUse(ItemStack p_77648_1_, EntityPlayer p_77648_2_,
			World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_,
			int p_77648_7_, float p_77648_8_, float p_77648_9_,
			float p_77648_10_) {

		if (BlockRailBase.func_150051_a(p_77648_3_.getBlock(p_77648_4_, p_77648_5_, p_77648_6_)))
		{
			if (!p_77648_3_.isRemote)
			{
				GGUEntityAutoCart entityminecart = new GGUEntityAutoCart(p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_);

				if (p_77648_1_.hasDisplayName())
				{
					entityminecart.setMinecartName(p_77648_1_.getDisplayName());
				}

				p_77648_3_.spawnEntityInWorld(entityminecart);
			}

			--p_77648_1_.stackSize;
			return true;
		}
		else
		{
			return false;
		}
	}
}
