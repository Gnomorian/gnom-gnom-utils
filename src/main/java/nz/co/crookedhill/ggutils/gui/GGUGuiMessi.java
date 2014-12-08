/* 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. 
 */

package nz.co.crookedhill.ggutils.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import nz.co.crookedhill.ggutils.GGUtils;
import nz.co.crookedhill.ggutils.inventory.GGUContainerMess;
import nz.co.crookedhill.ggutils.inventory.GGUInventoryMess;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly (Side.CLIENT)
public class GGUGuiMessi extends GuiContainer
{
	private static final int xTexture = 231;
	private static final int yTexture = 256;
	private static final ResourceLocation iconLocation = new ResourceLocation(GGUtils.MODID + ":" + "textures/gui/gui_mess"+".png");
	private final GGUInventoryMess inventory;

	@Override
	public void initGui() {
		xSize = xTexture;
		ySize = yTexture;
		super.initGui();
	}
	
	public GGUGuiMessi(EntityPlayer player, InventoryPlayer inv1, GGUInventoryMess inv2)
	{
		super(new GGUContainerMess(player, inv1, inv2));
		this.inventory = inv2;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float f) {
		super.drawScreen(mouseX, mouseY, f);
	}

	/**
	 * Draw the foreground layer for the GuiContainer (everything in front of the items)
	 */
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
			fontRendererObj.drawString("MESSI", xSize - fontRendererObj.getStringWidth("MESSI")/2-(174/2), -35, 0x00000000);
	}
	/**
	 * Draw the background layer for the GuiContainer (everything behind the items)
	 */
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int mouseX, int mouseY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(iconLocation);
		int k = (this.width - xTexture) / 2;
		int l = (this.height - yTexture) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, xTexture, yTexture);
	}
}
