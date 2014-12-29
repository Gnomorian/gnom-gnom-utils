package nz.co.crookedhill.ggutils.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import nz.co.crookedhill.ggutils.handlers.GGUWockyJabHandler;

public class GGUEntityWockyJab extends EntityThrowable 
{
	private World world;
	
	public GGUEntityWockyJab(World world) 
	{
		super(world);
		this.world = world;
	}

	public GGUEntityWockyJab(World world, double x, double y, double z) 
	{
		super(world, x, y, z);
		this.world = world;
	}

	public GGUEntityWockyJab(World world, EntityLivingBase entity) 
	{
		super(world, entity);
		this.world = world;
	}

	@Override
	protected void onImpact(MovingObjectPosition mop) 
	{
		if(!worldObj.isRemote) {
			new GGUWockyJabHandler(mop.typeOfHit, mop.entityHit, world, posX, posY, posZ);
		}
		//remove entity.
		setDead();
	}

}
