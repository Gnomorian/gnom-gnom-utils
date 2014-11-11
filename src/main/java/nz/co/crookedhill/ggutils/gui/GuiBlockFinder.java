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
public class GuiBlockFinder extends GuiContainer {

	ResourceLocation textureLoc = new ResourceLocation(GGUtils.MODID + ":" + "textures/gui/GUIBlockFinder"+".png");
	
	public GuiBlockFinder(EntityPlayer player, InventoryPlayer inventoryPlayer){
		super(new ContainerPlayer(inventoryPlayer, true, player));
	}
	
	@Override
	public void drawScreen(int x, int y, float f) {
		// TODO Auto-generated method stub
		super.drawScreen(x, y, f);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
		
	}
}
