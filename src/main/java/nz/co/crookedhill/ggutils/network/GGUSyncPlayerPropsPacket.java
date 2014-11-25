package nz.co.crookedhill.ggutils.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import nz.co.crookedhill.ggutils.extendedprops.GGUExtendedPlayer;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
/**
 *
 * A packet to send ALL data stored in your extended properties to the client.
 * This is handy if you only need to send your data once per game session or
 * all of your data needs to be synchronized together; it's also handy while
 * first starting, since you only need one packet for everything - however,
 * you should NOT use such a packet in your final product!!!
 *
 * Each packet should handle one thing and one thing only, in order to minimize
 * network traffic as much as possible. There is no point sending 20+ fields'
 * worth of data when you just need the current mana amount; conversely, it's
 * foolish to send 20 packets for all the data when the player first loads, when
 * you could send it all in one single packet.
 *
 * TL;DR - make separate packets for each piece of data, and one big packet for
 * those times when you need to send everything.
 *
 */
public class GGUSyncPlayerPropsPacket implements IMessage
{

	public NBTTagCompound data;
	// The basic, no-argument constructor MUST be included to use the new automated handling
	public GGUSyncPlayerPropsPacket() {}
	
	// We need to initialize our data, so provide a suitable constructor:
	public GGUSyncPlayerPropsPacket(EntityPlayer player) {
		// create a new tag compound
		data = new NBTTagCompound();
		// and save our player's data into it
		GGUExtendedPlayer.get(player).saveNBTData(data);
	}
	@Override
	public void fromBytes(ByteBuf buffer) {
		// luckily, ByteBufUtils provides an easy way to read the NBT
		data = ByteBufUtils.readTag(buffer);
	}
	@Override
	public void toBytes(ByteBuf buffer) {
		// ByteBufUtils provides a convenient method for writing the compound
		ByteBufUtils.writeTag(buffer, data);
	}
}

