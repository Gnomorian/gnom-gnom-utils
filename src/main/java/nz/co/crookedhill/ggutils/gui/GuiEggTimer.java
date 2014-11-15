package nz.co.crookedhill.ggutils.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import nz.co.crookedhill.ggutils.GGUtils;
import nz.co.crookedhill.ggutils.entity.item.GGUEntityEggTimer;

public class GuiEggTimer extends GuiScreen {

    public static final int GUI_ID = 20;

	private static final ResourceLocation iconLocation = new ResourceLocation(GGUtils.MODID + ":textures/gui/eggTimer.png");
    private GGUEntityEggTimer tileEggTimer;
    private int xSize, ySize;
    private int buttonCount = 0;
    
    private GuiButton buttonDone;

    public GuiEggTimer(GGUEntityEggTimer tileEntity)
    {
        this.tileEggTimer = tileEntity;
    }
    
    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    @Override
    public void initGui()
    {
    	super.initGui();
    	
        this.buttonList.clear();
    	this.buttonDone = new GuiButton(buttonCount, 40, 20, 40, 20, "Done");
    	this.buttonList.add(this.buttonDone);
    }
    

    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat events
     */
    @Override
    public void onGuiClosed()
    {
//        Keyboard.enableRepeatEvents(false);
//        NetHandlerPlayClient nethandlerplayclient = this.mc.getNetHandler();
//
//        if (nethandlerplayclient != null)
//        {
//            nethandlerplayclient.addToSendQueue(new C12PacketUpdateSign(this.tileSign.xCoord, this.tileSign.yCoord, this.tileSign.zCoord, this.tileSign.signText));
//        }
    }
    
    @Override
    protected void actionPerformed(GuiButton button)
    {
//        if (p_146284_1_.enabled)
//        {
//            if (p_146284_1_.id == 0)
//            {
//                this.tileSign.markDirty();
//                this.mc.displayGuiScreen((GuiScreen)null);
//            }
//        }
    }
    
    public void drawScreen(int x, int y, float f)
    {
        super.drawScreen(x, y, f);

        xSize = 256;
        ySize = 159;
        this.drawDefaultBackground();
		this.mc.getTextureManager().bindTexture(iconLocation);
		drawTexturedModalRect((this.width-xSize)/2, (this.height-ySize)/2, 0, 0, xSize, ySize);
    }
    
    @Override
    public boolean doesGuiPauseGame() {
    	return false;
    }
}
