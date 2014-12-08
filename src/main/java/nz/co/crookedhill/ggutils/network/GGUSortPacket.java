/* 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. 
 */

package nz.co.crookedhill.ggutils.network;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class GGUSortPacket implements IMessage
{
	String coords;
	
	public GGUSortPacket() { }
	
	public GGUSortPacket(String coords) 
	{
        this.coords = coords;
    }

	@Override
	public void fromBytes(ByteBuf buf) 
	{
		coords = ByteBufUtils.readUTF8String(buf);
		
	}

	@Override
	public void toBytes(ByteBuf buf) 
	{
		ByteBufUtils.writeUTF8String(buf, coords);
		
	}
	

}
