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

package nz.co.crookedhill.ggutils.handlers;

import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import nz.co.crookedhill.ggutils.helper.GGUConfigManager;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class GGUToolTipHandler
{
    /**
     * called when a player hovers over an item. used to add descriptions to all
     * GGU items
     */
    @SubscribeEvent
    public void onTooltipEvent(ItemTooltipEvent event)
    {
	if (event.itemStack.getItem().getUnlocalizedName().equals("tile.GrowthBlock"))
	{
	    event.toolTip.add("Growing plants has");
	    event.toolTip.add("never been faster!");
	    event.toolTip.add("Stack these blocks up to ");
	    event.toolTip.add(GGUConfigManager.growthBlockStackHeight + " times to grow faster");
	}
	if (event.itemStack.getItem().getUnlocalizedName().equals("tile.sortivator"))
	{
	    event.toolTip.add("Giving the Sortivator a Redstone signal");
	    event.toolTip.add("when a chest is on the red dod side");
	    event.toolTip.add("will sort the inventory of that chest");
	}
	if (event.itemStack.getItem().getUnlocalizedName().equals("tile.eggTimer"))
	{
	    // if the metadata=1, talk about it definatly letting you know
	    // .getMetadata(int) why the int?
	    event.toolTip.add("You can set this block to ring");
	    event.toolTip.add("at a time you specify. alternativly");
	    event.toolTip.add("craft it to let you know in a more");
	    event.toolTip.add("\"Explosive\" way");
	}
	if (event.itemStack.getItem().getUnlocalizedName().equals("item.blockFinder"))
	{
	    event.toolTip.add("you can use it once in an area.");
	    event.toolTip.add("it will take your experience and");
	    event.toolTip.add("show you where the near by valuable");
	    event.toolTip.add("block is that your looking for.");
	}
	if (event.itemStack.getItem().getUnlocalizedName().equals("tile.lazyCrafter"))
	{
	    event.toolTip.add("shows you what you can craft with what");
	    event.toolTip.add("you have in your current inventory");
	    event.toolTip.add("and craft it with a single click!");
	}
	if (event.itemStack.getItem().getUnlocalizedName().equals("item.enderiumRebirth"))
	{
	    event.toolTip.add("allows you a second life when you");
	    event.toolTip.add("are mortaly wounded, with all your");
	    event.toolTip.add("inventory. But with a risk of being");
	    event.toolTip.add("reborn in odd places.");
	}
    }

}
