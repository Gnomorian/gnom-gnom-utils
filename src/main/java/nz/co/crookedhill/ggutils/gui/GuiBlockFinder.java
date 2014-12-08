/* 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. 
 */

package nz.co.crookedhill.ggutils.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.util.ResourceLocation;
import nz.co.crookedhill.ggutils.GGUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly (Side.CLIENT)
public class GuiBlockFinder extends GuiContainer 
{

	ResourceLocation textureLoc = new ResourceLocation(GGUtils.MODID + ":" + "textures/gui/GUIBlockFinder"+".png");
	
	public GuiBlockFinder(EntityPlayer player, InventoryPlayer inventoryPlayer)
	{
		super(new ContainerPlayer(inventoryPlayer, true, player));
	}
	
	@Override
	public void drawScreen(int x, int y, float f) 
	{
		// TODO Auto-generated method stub
		super.drawScreen(x, y, f);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int x, int y) 
	{
		
	}
}
