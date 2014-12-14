/* 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. 
 */

package nz.co.crookedhill.ggutils.handlers;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import nz.co.crookedhill.ggutils.helper.GGUConfigManager;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class GGUToolTipHandler
{
	/**
	 * called when a player hovers over an item.
	 * used to add descriptions to all GGU items
	 */
	@SubscribeEvent
	public void onTooltipEvent(ItemTooltipEvent event) 
	{
		if(event.itemStack.getItem().getUnlocalizedName().equals("tile.GrowthBlock")) 
		{
			event.toolTip.add("Growing plants has");
			event.toolTip.add("never been faster!");
			event.toolTip.add("Stack these blocks up to ");
			event.toolTip.add(GGUConfigManager.growthBlockStackHeight+" times to grow faster");
			event.toolTip.add("Manual operation.");
			event.toolTip.add("Plant and harvest yourself.");
		}
		if(event.itemStack.getItem().getUnlocalizedName().equals("tile.GrowthBlockSemi")) 
		{
			event.toolTip.add("Growing plants has");
			event.toolTip.add("never been faster!");
			event.toolTip.add("Stack these blocks up to ");
			event.toolTip.add(GGUConfigManager.growthBlockStackHeight+" times to grow faster");
			event.toolTip.add("Semi-Auto operation.");
			event.toolTip.add("Plants and harvests with signal.");
		}
		if(event.itemStack.getItem().getUnlocalizedName().equals("tile.GrowthBlockAuto")) 
		{
			event.toolTip.add("Growing plants has");
			event.toolTip.add("never been faster!");
			event.toolTip.add("Stack these blocks up to ");
			event.toolTip.add(GGUConfigManager.growthBlockStackHeight+" times to grow faster");
			event.toolTip.add("Auto operation.");
			event.toolTip.add("Plants and harvests automatically.");
		}
		if(event.itemStack.getItem().getUnlocalizedName().equals("tile.sortivator")) 
		{
			event.toolTip.add("Right clicking on the the sortivator");
			event.toolTip.add("when a chest is on the red dod side");
			event.toolTip.add("will sort the inventory of that chest");
		}
		if(event.itemStack.getItem().getUnlocalizedName().equals("tile.eggTimer")) 
		{
			//if the metadata=1, talk about it definatly letting you know .getMetadata(int) why the int?
			event.toolTip.add("You can set this block to ring");
			event.toolTip.add("at a time you specify. alternativly");
			event.toolTip.add("craft it to let you know in a more");
			event.toolTip.add("\"Explosive\" way");
		}
		if(event.itemStack.getItem().getUnlocalizedName().equals("item.blockFinder")) 
		{
			event.toolTip.add("you can use it once in an area.");
			event.toolTip.add("it will take your experience and");
			event.toolTip.add("show you where the near by valuable");
			event.toolTip.add("block is that your looking for.");
		}
		if(event.itemStack.getItem().getUnlocalizedName().equals("tile.lazyCrafter")) 
		{
			event.toolTip.add("shows you what you can craft with what");
			event.toolTip.add("you have in your current inventory");
			event.toolTip.add("and craft it with a single click!");
		}
		if(event.itemStack.getItem().getUnlocalizedName().equals("tile.cobbleDegen")) 
		{
			event.toolTip.add("Power with redstone and activate");
			event.toolTip.add("to get rid of all cobblestone");
			event.toolTip.add("cluttering your inventory!");
		}
		if(event.itemStack.getItem().getUnlocalizedName().equals("tile.modularCore")) 
		{
			event.toolTip.add("Connect MESS Limbs to this to");
			event.toolTip.add("create a modular storage system");
			event.toolTip.add("accessible via the MESSI item.");
		}
		if(event.itemStack.getItem().getUnlocalizedName().equals("tile.modularLimb")) 
		{
			if(event.itemStack.getTagCompound() != null && event.itemStack.getTagCompound().getTagList("Items", Constants.NBT.TAG_COMPOUND) != null)
			{
				NBTTagList items = event.itemStack.getTagCompound().getTagList("Items", Constants.NBT.TAG_COMPOUND);
				NBTTagCompound item = items.getCompoundTagAt(0);
				ItemStack stack = ItemStack.loadItemStackFromNBT(item);
				int stackSize = event.itemStack.getTagCompound().getInteger("stackSize");

				if(stack != null)
				{
					event.toolTip.add("This MESS limb contains");
					event.toolTip.add("x" + stackSize + " " + stack.getDisplayName());
				}
				else
				{
					event.toolTip.add("This MESS limb contains");
					event.toolTip.add("absolutely nothing!");
				}
			}
			else
			{
				event.toolTip.add("This MESS limb contains");
				event.toolTip.add("absolutely nothing!");	
			}
		}
	}

}
