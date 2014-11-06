package nz.co.crookedhill.ggutils.handlers;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;


public class GGUMobHandler {

	//
	@SubscribeEvent
	/**
	 * On living entity death. Might as well use this method if you want to handle any living entity deaths.
	 * 
	 * @param event The LivingDeathEvent
	 */
	public void onDeathHandler(LivingDeathEvent event)
	{
		Entity entity = event.entity;
		DamageSource source = event.source;
		Random rand = new Random();
		
		//checks whether creeper and was killed by player
		if(entity instanceof EntityCreeper && source.getEntity() instanceof EntityPlayer)
		{
			World world = event.entity.worldObj;
			//only on server
			if(!world.isRemote){
				double x = event.entity.posX;
				double y = event.entity.posY;
				double z = event.entity.posZ;
				
				//spawn a bunch of things
				for(int i=0;i<rand.nextInt(20);i++)
				{
					//TODO: change to entity creeperrmite
					Entity creeperMite = new EntityCreeper(world);
					creeperMite.addVelocity(0.2D, 0.8D, 0.2D); //TODO: Fix the client side in ground glitch.
					creeperMite.setLocationAndAngles(x, y, z, 0.0F, 0.0F);
					world.spawnEntityInWorld(creeperMite);
				}
			}
		}
	}

}
