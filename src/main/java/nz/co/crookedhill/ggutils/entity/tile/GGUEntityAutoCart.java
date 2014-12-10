package nz.co.crookedhill.ggutils.entity.tile;

import net.minecraft.entity.item.EntityMinecartFurnace;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class GGUEntityAutoCart extends EntityMinecartFurnace {

	public GGUEntityAutoCart(World world) {
		super(world);

	}
	
    public GGUEntityAutoCart(World p_i1715_1_, double p_i1715_2_, double p_i1715_4_, double p_i1715_6_)
    {
        super(p_i1715_1_, p_i1715_2_, p_i1715_4_, p_i1715_6_);
    }
    
	@Override
	public int getMinecartType() {
		return 10;
	}

	@Override
	public void onUpdate() {

		super.onUpdate();
		
		int l = MathHelper.floor_double((double) (this.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

		if (l == 0)
		{
			boolean isBlockAir = this.worldObj.isAirBlock((int)this.posX+1, (int)this.posY, (int)this.posZ+1);

			if(isBlockAir)
			{
				this.worldObj.setBlock((int)this.posX, (int)this.posY, (int)this.posZ+1, Blocks.rail);
			}
		}

		if (l == 1)
		{
			boolean isBlockAir = this.worldObj.isAirBlock((int)this.posX-1, (int)this.posY, (int)this.posZ);

			if(isBlockAir)
			{
				this.worldObj.setBlock((int)this.posX-1, (int)this.posY, (int)this.posZ, Blocks.rail);

			}
		}

		if (l == 2)
		{
			boolean isBlockAir = this.worldObj.isAirBlock((int)this.posX, (int)this.posY, (int)this.posZ-1);

			if(isBlockAir)
			{
				this.worldObj.setBlock((int)this.posX, (int)this.posY, (int)this.posZ-1, Blocks.rail);

			}
		}

		if (l == 3)
		{
			boolean isBlockAir = this.worldObj.isAirBlock((int)this.posX+1, (int)this.posY, (int)this.posZ);

			if(isBlockAir)
			{
				this.worldObj.setBlock((int)this.posX+1, (int)this.posY, (int)this.posZ, Blocks.rail);

			}
		}
	}
}
