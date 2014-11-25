package nz.co.crookedhill.ggutils.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import nz.co.crookedhill.ggutils.extendedprops.GGUExtendedPlayer;


import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ExtendedPropertiesHandler {

	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event) {
		if (event.entity instanceof EntityPlayer) {
			if (GGUExtendedPlayer.get((EntityPlayer) event.entity) == null)
				GGUExtendedPlayer.register((EntityPlayer) event.entity);
		}
	}
	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent event) {
		if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer) {
			GGUExtendedPlayer.loadProxyData((EntityPlayer) event.entity);
		}
	}
	@SubscribeEvent
	public void onLivingDeathEvent(LivingDeathEvent event) {
		if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer) {
			GGUExtendedPlayer.saveProxyData((EntityPlayer) event.entity);
		}
	}
}
