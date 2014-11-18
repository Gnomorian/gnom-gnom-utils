package nz.co.crookedhill.ggutils.network;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class GGUInventorySwitchPacket  implements IMessage
{
	int altInventoryNum;
	public GGUInventorySwitchPacket() { }
	
	public GGUInventorySwitchPacket(short altInventoryNumber)
	{
		this.altInventoryNum = altInventoryNumber;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.altInventoryNum = ByteBufUtils.readVarShort(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeVarShort(buf, this.altInventoryNum);
		
	}

}
