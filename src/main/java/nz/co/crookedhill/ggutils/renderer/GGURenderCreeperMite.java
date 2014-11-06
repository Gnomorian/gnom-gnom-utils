package nz.co.crookedhill.ggutils.renderer;

import net.minecraft.client.renderer.entity.RenderSilverfish;
import net.minecraft.entity.EntityLivingBase;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GGURenderCreeperMite extends RenderSilverfish{

	public GGURenderCreeperMite()
	{
		super();
	}
    
    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
	@Override
    protected void preRenderCallback(EntityLivingBase p_77041_1_, float p_77041_2_)
    {
        GL11.glColor3f(0, 255, 0);
    }
}
