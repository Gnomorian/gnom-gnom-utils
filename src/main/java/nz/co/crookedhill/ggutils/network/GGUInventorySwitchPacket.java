/* 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. 
 */

package nz.co.crookedhill.ggutils.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class GGUInventorySwitchPacket  implements IMessage
{
	int altInventoryNum;
	ItemStack[] stacks = new ItemStack[36];
	
	public GGUInventorySwitchPacket() { }

	public GGUInventorySwitchPacket(int inventorySize, ItemStack[] stacks)
	{
		this.altInventoryNum = inventorySize;
		this.stacks = stacks;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		//this.altInventoryNum = ByteBufUtils.readVarShort(buf);
		this.altInventoryNum = buf.readInt();
		
		for(int i=0;i<this.altInventoryNum;i++)
		{
			this.stacks[i] = ByteBufUtils.readItemStack(buf);
		}
	}

	@Override
	public void toBytes(ByteBuf buf) {
		//ByteBufUtils.writeVarShort(buf, this.altInventoryNum);
		buf.writeInt(this.altInventoryNum);

		for(int i=0;i<this.altInventoryNum;i++)
		{
			ByteBufUtils.writeItemStack(buf, stacks[i]);
		}

	}
}
