/* 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. 
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
		GGUSort sorter = new GGUSort();
		sorter.sortInventory(inventory);
	    } else
		System.out.println("That didnt work");
	}
	return null;
    }

}
