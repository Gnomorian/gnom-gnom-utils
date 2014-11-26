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

package nz.co.crookedhill.ggutils.item;

import net.minecraft.block.BlockRailBase;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import nz.co.crookedhill.ggutils.GGUtils;

/**
 * @author william-cameron1994
 *
 */
public class GGUItemCart extends Item
{
    public int minecartType;

    public GGUItemCart()
    {
	this.setMaxStackSize(1);
	this.setTextureName(GGUtils.MODID + ":itemCart_texture");
	this.minecartType = 0;
    }

    /**
     * Callback for item usage. If the item does something special on right
     * clicking, he will have one of those. Return True if something happen and
     * false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
	if (BlockRailBase.func_150051_a(world.getBlock(p_77648_4_, p_77648_5_, p_77648_6_)))
	{
	    if (!world.isRemote)
	    {
		EntityMinecart entityminecart = EntityMinecart.createMinecart(world, (double) ((float) p_77648_4_ + 0.5F), (double) ((float) p_77648_5_ + 0.5F), (double) ((float) p_77648_6_ + 0.5F), this.minecartType);

		if (itemstack.hasDisplayName())
		{
		    entityminecart.setMinecartName(itemstack.getDisplayName());
		}

		world.spawnEntityInWorld(entityminecart);
	    }

	    --itemstack.stackSize;
	    return true;
	} else
	{
	    return false;
	}
    }
}
