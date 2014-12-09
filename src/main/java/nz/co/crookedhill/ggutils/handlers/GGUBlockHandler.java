/* 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. 
 */

package nz.co.crookedhill.ggutils.handlers;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.event.world.BlockEvent.PlaceEvent;
import nz.co.crookedhill.ggutils.block.GGUBlockModularCore;
import nz.co.crookedhill.ggutils.entity.monster.GGUEntityCreeperMite;
import nz.co.crookedhill.ggutils.entity.tile.GGUEntityModularCore;
import nz.co.crookedhill.ggutils.extendedprops.GGUExtendedPlayer;
import nz.co.crookedhill.ggutils.helper.GGUConfigManager;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class GGUBlockHandler
{

	/**
	 * event triggers on block break used for checking if the block is long
	 * grass, then spawning a creepermite in its place.
	 * 
	 * @param event
	 *            BreakEvent event
	 */
	@SubscribeEvent
	public void onBlockBreak(BreakEvent event)
	{
		Random rand = new Random();
		Block block = event.block;
		World world = event.world;
		int x = event.x;
		int y = event.y;
		int z = event.z;

		if (!world.isRemote && GGUConfigManager.creeperMiteExist)
		{
			if (block instanceof BlockTallGrass || block instanceof BlockDoublePlant)
			{
				int creeperChance = GGUConfigManager.creeperMiteGrassChance;

				// checks to see if config is not set to 0 then does random 10%
				// check.
				if (creeperChance > 0 && rand.nextInt(100) <= creeperChance)
				{
					Entity creeperMite = new GGUEntityCreeperMite(world);
					creeperMite.setLocationAndAngles(x, y + 1, z, 0.0F, 0.0F);
					world.spawnEntityInWorld(creeperMite);
				}
			}
		}
		
		if(block instanceof GGUBlockModularCore)
		{
			GGUEntityModularCore te = (GGUEntityModularCore)world.getTileEntity(x, y, z);
				if(te.isOwner(event.getPlayer().getCommandSenderName()))
				{
					GGUExtendedPlayer props = GGUExtendedPlayer.get(event.getPlayer());
					props.setMess(false);
					int[] coords = {-1, -1, -1};
					props.setMessCoords(coords);
				}
		}
	}
	
	@SubscribeEvent
	public void onBlockPlaced(PlaceEvent event)
	{
		if(event.player instanceof EntityPlayer && event.placedBlock instanceof GGUBlockModularCore)
		{
			GGUExtendedPlayer props = GGUExtendedPlayer.get(event.player);
			if(props.hasMess())
			{
				event.player.addChatComponentMessage(new ChatComponentText("Currently, you can only have one MESS."));
				event.setCanceled(true);
			}
			else
			{
				int[] coords = {event.x, event.y, event.z};
				props.setMess(true);
				props.setMessCoords(coords);
			}
		}
	}
}
