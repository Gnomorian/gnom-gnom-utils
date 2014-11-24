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

package nz.co.crookedhill.ggutils.network;

import net.minecraft.block.Block;
import net.minecraft.inventory.IInventory;
import net.minecraft.world.World;
import nz.co.crookedhill.ggutils.block.GGUBlockSortivator;
import nz.co.crookedhill.ggutils.util.GGUSort;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class GGUSortPacketHandler implements IMessageHandler<GGUSortPacket, IMessage>
{

    /**
     * an example of sending a message is: String message =
     * ""+(char)x+(char)y+(char)z; GGUtils.network.sendToServer(new
     * GGUSortPacket(message));
     */

    @Override
    public IMessage onMessage(GGUSortPacket message, MessageContext ctx)
    {
	if (message.coords.length() == 3)
	{
	    int x = (int) message.coords.indexOf(0);
	    int y = (int) message.coords.indexOf(1);
	    int z = (int) message.coords.indexOf(2);
	    World world = ctx.getServerHandler().playerEntity.worldObj;
	    System.out.println(x + " " + y + " " + z + " " + ctx.getServerHandler().playerEntity.getDisplayName());
	    Block sortivator = world.getBlock(x, y, z);
	    if (sortivator instanceof GGUBlockSortivator)
	    {
		IInventory inventory = ((GGUBlockSortivator) sortivator).getInventory(world, x, y + 1, z);
		new GGUSort(inventory);
	    } else
		System.out.println("That didnt work");
	}
	return null;
    }

}
