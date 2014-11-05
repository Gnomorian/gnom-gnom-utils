package nz.co.crookedhill.ggutils.network;

import net.minecraft.block.Block;
import net.minecraft.inventory.IInventory;
import net.minecraft.world.World;
import nz.co.crookedhill.ggutils.block.GGUBlocks;
import nz.co.crookedhill.ggutils.block.Sortivator;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class GGUSortPacketHandler implements IMessageHandler<GGUSortPacket, IMessage>{

	@Override
	public IMessage onMessage(GGUSortPacket message, MessageContext ctx) {
		if (message.coords.length() == 3) {
			int x = (int)message.coords.indexOf(0);
			int y = (int)message.coords.indexOf(1);
			int z = (int)message.coords.indexOf(2);
			World world = ctx.getServerHandler().playerEntity.worldObj;
			System.out.println(x+" "+y+" "+z+" "+ctx.getServerHandler().playerEntity.getDisplayName());
			Block sortivator = world.getBlock(x, y, z);
			if(sortivator instanceof Sortivator) {
				IInventory inventory = ((Sortivator)sortivator).getInventory(world, x, y+1, z);
				((Sortivator)sortivator).sortAlt(inventory);
			}
			else System.out.println("That didnt work");
		}
		return null;
	}
	
}
