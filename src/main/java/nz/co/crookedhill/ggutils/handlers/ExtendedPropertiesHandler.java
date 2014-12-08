/* 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. 
 */

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
