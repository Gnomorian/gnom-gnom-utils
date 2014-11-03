package nz.co.crookedhill.ggutils.handlers;

import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import nz.co.crookedhill.ggutils.block.GGUBlocks;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class GGUEventHandler {
	@SubscribeEvent
	public void onTooltipEvent(ItemTooltipEvent event) {
		if(event.itemStack.getItem().getUnlocalizedName().equals("tile.GrowthBlock")) {
			event.toolTip.add("Growing plants has never been faster!");
			event.toolTip.add("Stack these blocks up to "+GGUBlocks.stackHeight+" times to grow faster");
		}
		if(event.itemStack.getItem().getUnlocalizedName().equals("tile.sortivator")) {
			event.toolTip.add("Right clicking on the the sortivator");
			event.toolTip.add("when a chest is on the red dod side");
			event.toolTip.add("will sort the inventory of that chest");
		}
	}

}
