package nz.co.crookedhill.ggutils.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import nz.co.crookedhill.ggutils.GGUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly (Side.CLIENT)
public class GuiBlockFinder extends GuiScreen {

	private ResourceLocation textureLoc = new ResourceLocation(GGUtils.MODID + ":" + "textures/gui/GUIBlockFinder"+".png");
	private Minecraft mc;
	
	public GuiBlockFinder(Minecraft mc)
	{
		this.mc = mc;
	}
	
	@Override
	public void initGui() {
		// TODO Auto-generated method stub
		super.initGui();
	}
	
	@Override
	public void drawScreen(int x, int y, float z) {
		// TODO Auto-generated method stub
		super.drawScreen(x, y, z);
	}
}
