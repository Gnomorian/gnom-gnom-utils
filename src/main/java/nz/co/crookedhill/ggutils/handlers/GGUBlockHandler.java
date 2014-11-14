/*
 * Copyright (c) 2014, William <w.cameron@crookedhill.co.nz>
 * 
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
 * ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
 * OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */

package nz.co.crookedhill.ggutils.handlers;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import nz.co.crookedhill.ggutils.entity.monster.GGUEntityCreeperMite;
import nz.co.crookedhill.ggutils.helper.GGUConfigManager;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;


public class GGUBlockHandler 
{

	/**
	 * event triggers on block break
	 * used for checking if the block is long grass,
	 * then spawning a creepermite in its place.
	 * @param event BreakEvent event
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
		
		if(!world.isRemote && GGUConfigManager.creeperMiteExist)
		{
			if(block instanceof BlockTallGrass || block instanceof BlockDoublePlant)
			{
				int creeperChance = GGUConfigManager.creeperMiteChance;
				
				//checks to see if config is not set to 0 then does random 10% check.
				if(creeperChance > 0 && rand.nextInt(100) <= creeperChance)
				{
					Entity creeperMite = new GGUEntityCreeperMite(world);
					creeperMite.setLocationAndAngles(x, y+1, z, 0.0F, 0.0F);
					world.spawnEntityInWorld(creeperMite);	
				}
			}
		}
	}
}
