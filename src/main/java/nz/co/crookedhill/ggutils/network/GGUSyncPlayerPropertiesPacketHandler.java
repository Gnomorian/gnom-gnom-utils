package nz.co.crookedhill.ggutils.network;

import net.minecraft.entity.player.EntityPlayer;
import nz.co.crookedhill.ggutils.GGUtils;
import nz.co.crookedhill.ggutils.extendedprops.GGUExtendedPlayer;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class GGUSyncPlayerPropertiesPacketHandler implements IMessageHandler<GGUSyncPlayerPropsPacket, IMessage> {
	
	@Override
	public IMessage onMessage(GGUSyncPlayerPropsPacket message, MessageContext ctx) {
		EntityPlayer player = GGUtils.proxy.getPlayerEntity(ctx);
		GGUExtendedPlayer.get(player).loadNBTData(message.data);
		return null;
	}
}
