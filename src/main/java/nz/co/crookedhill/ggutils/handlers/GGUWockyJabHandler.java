package nz.co.crookedhill.ggutils.handlers;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.init.Blocks;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;

public class GGUWockyJabHandler {

	public GGUWockyJabHandler(MovingObjectType type, Entity entity, World world, double x, double y, double z)
	{
		//entity hit.
		if(type == MovingObjectType.ENTITY) 
		{
			entityCollision(entity, x, y, z);
		}
		else if(type == MovingObjectType.BLOCK) 
		{
			blockCollision(world, x, y, z);
		}
	}
	
	private void entityCollision(Entity entity, double x, double y, double z)
	{
		if(entity instanceof EntityCow)
		{
			entity.setDead();
		}
	}
	
	private void blockCollision(World world, double x, double y, double z)
	{
		Block block = world.getBlock((int)x, (int)y, (int)z);
		if(block == Blocks.stone)
		{
			world.setBlock((int)x, (int)y, (int)z, Blocks.diamond_ore);
		}
	}
}
