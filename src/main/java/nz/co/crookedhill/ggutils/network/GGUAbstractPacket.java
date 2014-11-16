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

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public abstract class GGUAbstractPacket implements IMessage
{
	/**
	 * create a new constructor with the parameters of what you want to pass
	 * to the other end of the network.
	 * @return 
	 */
	public GGUAbstractPacket() { }
  
  /**
   * converting the encoded variables back into something useable.
   */
	@Override
	public abstract void fromBytes(ByteBuf buf);
  
  /**
   * encoding the variables passed to the constructor to be sent to
   * the other end of the network.
   */
	@Override
	public abstract void toBytes(ByteBuf buf);
	

}
