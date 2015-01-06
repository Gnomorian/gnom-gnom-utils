package nz.co.crookedhill.ggutils.handlers;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityTracker;
import net.minecraft.entity.EntityTrackerEntry;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;
import nz.co.crookedhill.ggutils.helper.GGUHelper;

public class GGUWockyJabHandler {

	private static Random rand = new Random();

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
		if(!world.isRemote)
		{
			int xPos = (int) x;
			int yPos = (int) y;
			int zPos = (int) z;
			Block block = world.getBlock(xPos, yPos, zPos);
			
			if(!world.isAirBlock(xPos, yPos, zPos));
			{
				int randomNum = rand.nextInt(1000);

				//set to random block
				if(isBetween(randomNum, 0, 100))
				{
					world.setBlock(xPos, yPos, zPos, GGUHelper.getRandomBlock());			
				}
				//spawn lightning
				else if(isBetween(randomNum, 101, 200))
				{
					world.spawnEntityInWorld(new EntityLightningBolt(world, x, y+1, z));
				}
				//spawn random tree
				else if(isBetween(randomNum, 201, 300))
				{
					GGUHelper.spawnRandomTree(world, (int)x, (int)y, (int)z);
				}
				//spawn random entity
				else if(isBetween(randomNum, 301, 400))
				{
					GGUHelper.spawnRandomEntity(world, x, y, z);
				}
				//set world to random time
				else if(isBetween(randomNum, 401, 500))
				{
					world.setWorldTime(rand.nextInt(23999));
				}
				else if(isBetween(randomNum, 501, 600))
				{

				}
				else if(isBetween(randomNum, 601, 700))
				{

				}
				else if(isBetween(randomNum, 701, 800))
				{

				}
				else if(isBetween(randomNum, 801, 900))
				{

				}
				else if(isBetween(randomNum, 901, 1000))
				{

				}
			}
		}
	}

	private static boolean isBetween(int x, int lower, int upper) 
	{
		return lower <= x && x <= upper;
	}
}
