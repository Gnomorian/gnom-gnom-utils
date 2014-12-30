package nz.co.crookedhill.ggutils.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import nz.co.crookedhill.ggutils.handlers.GGUWockyJabHandler;

public class GGUEntityWockyJab extends EntityThrowable 
{
	public GGUEntityWockyJab(World world) 
	{
		super(world);
	}

	public GGUEntityWockyJab(World world, double x, double y, double z) 
	{
		super(world, x, y, z);
	}

	public GGUEntityWockyJab(World world, EntityLivingBase entity) 
	{
		super(world, entity);
	}

	@Override
	protected void onImpact(MovingObjectPosition mop) 
	{
		switch(mop.sideHit){
		case 0:
			new GGUWockyJabHandler(mop.typeOfHit, mop.entityHit, worldObj, posX, posY+1, posZ);
			break;
		case 1:
			new GGUWockyJabHandler(mop.typeOfHit, mop.entityHit, worldObj, posX, posY-1, posZ);
			break;
		case 2:
			new GGUWockyJabHandler(mop.typeOfHit, mop.entityHit, worldObj, posX, posY, posZ+1);
			break;
		case 3:
			new GGUWockyJabHandler(mop.typeOfHit, mop.entityHit, worldObj, posX, posY, posZ-1);
			break;
		case 4:
			new GGUWockyJabHandler(mop.typeOfHit, mop.entityHit, worldObj, posX+1, posY, posZ);
			break;
		case 5:
			new GGUWockyJabHandler(mop.typeOfHit, mop.entityHit, worldObj, posX-1, posY, posZ);
			break;
		default:
		}
			//remove entity.
			setDead();
	}

}
