package nz.co.crookedhill.ggutils.network;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class GGUSortPacket implements IMessage{
	String coords;
	
	public GGUSortPacket() { }
	
	public GGUSortPacket(String coords) {
        this.coords = coords;
    }

	@Override
	public void fromBytes(ByteBuf buf) {
		coords = ByteBufUtils.readUTF8String(buf);
		
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, coords);
		
	}
	

}
