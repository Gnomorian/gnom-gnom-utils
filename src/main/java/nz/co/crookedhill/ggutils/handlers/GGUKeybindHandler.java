package nz.co.crookedhill.ggutils.handlers;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import nz.co.crookedhill.ggutils.GGUtils;
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
				ItemStack[] inventory = player.inventory.mainInventory;
				player.inventory.mainInventory = this.swapInventories(inventory);
			}
		}
	}

	/**
	 * Swaps the inventories with alot of finese
	 * @param inventory
	 * @return
	 */
	private ItemStack[] swapInventories(ItemStack[] inventory)
	{
		//all the rows
		List<ItemStack> hotbarItems = new ArrayList<ItemStack>();
		List<ItemStack> rowItems = new ArrayList<ItemStack>();
		List<ItemStack> otherRowItems = new ArrayList<ItemStack>();
		List<ItemStack> otherOtherRowItems = new ArrayList<ItemStack>();
		List<ItemStack> fullInventory = new ArrayList<ItemStack>();
		
		//temp row
		List<ItemStack> tempRowItems = new ArrayList<ItemStack>();

		//always need a random
		Random rand = new Random();
		int i;
		int randomRowStart = ((1+rand.nextInt(3))*9);

		randomRowStart = (randomRowStart < 0) ? 0 : randomRowStart;

		//random check
		switch(randomRowStart)
		{
		case 9:
			//fill necessary inventories
			for(i=0;i<9;i++)
			{
				hotbarItems.add(inventory[i]);
				rowItems.add(inventory[randomRowStart+i]);
				tempRowItems.add(inventory[randomRowStart+i]);
				
				otherRowItems.add(inventory[i+18]);
				otherOtherRowItems.add(inventory[i+27]);
			}
			
			//swap inventories using temp
			rowItems.clear();
			rowItems.addAll(hotbarItems);
			hotbarItems.clear();
			hotbarItems.addAll(tempRowItems);
			
			//create the full inventory
			fullInventory.addAll(hotbarItems);
			fullInventory.addAll(rowItems);
			fullInventory.addAll(otherRowItems);
			fullInventory.addAll(otherOtherRowItems);
			
			//set to inventory as itemstack array to return
			inventory = (ItemStack[]) fullInventory.toArray(new ItemStack[fullInventory.size()]);
			break;
		case 18:

			for(i=0;i<9;i++)
			{
				hotbarItems.add(inventory[i]);
				rowItems.add(inventory[randomRowStart+i]);
				tempRowItems.add(inventory[randomRowStart+i]);
				
				otherRowItems.add(inventory[i+9]);
				otherOtherRowItems.add(inventory[i+27]);
			}

			rowItems.clear();
			rowItems.addAll(hotbarItems);
			hotbarItems.clear();
			hotbarItems.addAll(tempRowItems);
			
			fullInventory.addAll(hotbarItems);
			fullInventory.addAll(otherRowItems);
			fullInventory.addAll(rowItems);
			fullInventory.addAll(otherOtherRowItems);
			
			inventory = (ItemStack[]) fullInventory.toArray(new ItemStack[fullInventory.size()]);

			break;
		case 27:

			for(i=0;i<9;i++)
			{
				hotbarItems.add(inventory[i]);
				rowItems.add(inventory[randomRowStart+i]);
				tempRowItems.add(inventory[randomRowStart+i]);
				
				otherRowItems.add(inventory[i+9]);
				otherOtherRowItems.add(inventory[i+18]);
			}

			rowItems.clear();
			rowItems.addAll(hotbarItems);
			hotbarItems.clear();
			hotbarItems.addAll(tempRowItems);
			
			fullInventory.addAll(hotbarItems);
			fullInventory.addAll(otherRowItems);
			fullInventory.addAll(otherOtherRowItems);
			fullInventory.addAll(rowItems);
			
			inventory = (ItemStack[]) fullInventory.toArray(new ItemStack[fullInventory.size()]);

			break;
		default: System.out.println("SHIT!");
		break;
		}

		return inventory;
	}
}