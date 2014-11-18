package nz.co.crookedhill.ggutils.handlers;


import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import nz.co.crookedhill.ggutils.GGUtils;
import nz.co.crookedhill.ggutils.network.GGUInventorySwitchPacket;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;


public class GGUKeybindHandler {

	Minecraft mc;

	@SubscribeEvent
	public void onKeyInput(KeyInputEvent event) {

		EntityPlayer player = FMLClientHandler.instance().getClient().thePlayer;

		if(FMLClientHandler.instance().getClient().inGameHasFocus){

			if(GGUtils.arseTardis.isPressed()) {
				System.out.println("key was pressed");
				GGUtils.network.sendToServer(new GGUInventorySwitchPacket((short)1));
				//ItemStack[] inventory = player.inventory.mainInventory;
				//player.inventory.mainInventory = this.swapInventories(inventory);
			}
		}
	}

}