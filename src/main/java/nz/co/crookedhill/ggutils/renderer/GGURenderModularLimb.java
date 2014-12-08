package nz.co.crookedhill.ggutils.renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import nz.co.crookedhill.ggutils.entity.tile.GGUEntityModularLimb;

import org.lwjgl.opengl.GL11;


public class GGURenderModularLimb extends TileEntitySpecialRenderer {

	private ItemStack randomItem;
	private EntityItem entityItem;
	
	public GGURenderModularLimb() {

	}

	@Override
	public void renderTileEntityAt(TileEntity entity, double x,
			double y, double z, float f	) {

		this.randomItem = ((GGUEntityModularLimb)entity).getStack();	
		this.entityItem = new EntityItem(Minecraft.getMinecraft().thePlayer.worldObj, x, y, z, randomItem);

		float rotateDeg = ((GGUEntityModularLimb)entity).getRotation();
		
		if(this.entityItem != null)
		{
			GL11.glPushMatrix();
			//Without the below line, the item will spazz out
			this.entityItem.hoverStart = 0.0F;
			//RenderItem.renderInFrame = true;
			GL11.glTranslatef((float)x + 0.5F, (float)y + 0.25F, (float)z + 0.5F);
			GL11.glRotatef(rotateDeg, 0, 1, 0);
			RenderManager.instance.renderEntityWithPosYaw(this.entityItem, 0.0D, 0.0D, 0.0D, 10.0F, 0.0F);
			//RenderItem.renderInFrame = false;
			GL11.glPopMatrix();
		}
	}

}
