/* 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. 
 */

package nz.co.crookedhill.ggutils.handlers;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import nz.co.crookedhill.ggutils.GGUtils;
import nz.co.crookedhill.ggutils.network.GGUInventorySwitchPacket;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;

public class GGUKeybindHandler
{

	Minecraft mc;
	KeyBinding arseTardis;

	@SubscribeEvent
	public void onKeyInput(KeyInputEvent event)
	{
		if (FMLClientHandler.instance().getClient().inGameHasFocus)
		{

			if (arseTardis.isPressed())
			{
				GGUtils.network.sendToServer(new GGUInventorySwitchPacket(36, Minecraft.getMinecraft().thePlayer.inventory.mainInventory));
			}
		}
	}
	
	public void init()
	{
		arseTardis = new KeyBinding("Arse Tardis", Keyboard.KEY_Z, "GG Utils");
		ClientRegistry.registerKeyBinding(arseTardis);
		FMLCommonHandler.instance().bus().register(this);
	}

}