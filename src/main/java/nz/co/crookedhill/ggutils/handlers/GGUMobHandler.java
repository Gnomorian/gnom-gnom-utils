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

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import nz.co.crookedhill.ggutils.entity.monster.GGUEntityCreeperMite;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class GGUMobHandler
{

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

	// checks whether creeper and was killed by player
	if (entity instanceof EntityCreeper && source.getEntity() instanceof EntityPlayer)
	{
	    World world = event.entity.worldObj;
	    // only on server
	    if (!world.isRemote)
	    {
		double x = event.entity.posX;
		double y = event.entity.posY;
		double z = event.entity.posZ;

		// spawn a bunch of things
		for (int i = 0; i < rand.nextInt(20); i++)
		{
		    Entity creeperMite = new GGUEntityCreeperMite(world);
		    creeperMite.addVelocity(0.2D, 0.8D, 0.2D); // TODO: Fix the
							       // client side in
							       // ground glitch.
		    creeperMite.setLocationAndAngles(x, y, z, 0.0F, 0.0F);
		    world.spawnEntityInWorld(creeperMite);
		}
	    }
	}
    }

}
