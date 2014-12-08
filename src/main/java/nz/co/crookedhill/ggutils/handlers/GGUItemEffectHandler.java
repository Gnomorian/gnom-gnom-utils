/* 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. 
 */

package nz.co.crookedhill.ggutils.handlers;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChunkCoordinates;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import nz.co.crookedhill.ggutils.item.GGUEnderiumRebirth;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class GGUItemEffectHandler
{
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onHurtEvent(LivingHurtEvent event)
	{
		if (!event.entity.worldObj.isRemote)
		{
			if (event.entity instanceof EntityPlayer)
			{
				if (event.ammount >= ((EntityPlayer) event.entity).getHealth())
				{
					EntityPlayer player = (EntityPlayer) event.entity;
					ItemStack[] inv = player.inventory.mainInventory;
					boolean ercFoundFlag = false;
					int slotNumber = 0;
					ItemStack ercStack = null;

					//find erc
					for (int i = 0; i < inv.length; i++)
					{
						ItemStack item = inv[i];
						if (item != null && item.getItem() instanceof GGUEnderiumRebirth)
						{
							ercFoundFlag = true;
							slotNumber = i;
							ercStack = item;
							break;
						}
					}

					if (ercFoundFlag)
					{
						event.setCanceled(true);
						this.performRespawn(ercStack, player, slotNumber);	
					}
				}
			}
		}
	}

	private void performRespawn(ItemStack stack, EntityPlayer player, int slot)
	{
		if(((GGUEnderiumRebirth)stack.getItem()).isStable())
		{
			ChunkCoordinates spawn;
			if(player.getBedLocation(0) != null)
			{
				spawn = player.getBedLocation(0);
			}
			else
			{
				spawn = player.worldObj.getSpawnPoint();
			}

			player.setPositionAndUpdate(spawn.posX, spawn.posY, spawn.posZ);
			player.addPotionEffect(new PotionEffect(Potion.blindness.id, 600, 1));
			player.addPotionEffect(new PotionEffect(Potion.confusion.id, 150, 3));
			player.addPotionEffect(new PotionEffect(Potion.hunger.id, 250, 0));
			player.inventory.setInventorySlotContents(slot, null);
			player.setHealth(1);
		}
		else
		{
			Random rand = new Random();
			ChunkCoordinates coords = new ChunkCoordinates();
			
			coords.posX = (16 - rand.nextInt(31)) + (int) player.posX;
			coords.posY = (5 - rand.nextInt(9)) + (int) player.posY;
			coords.posZ = (16 - rand.nextInt(31)) + (int) player.posZ;
			
			player.worldObj.createExplosion(player, coords.posX, coords.posY, coords.posZ, 2.0f, true);
			player.setPositionAndUpdate((double) coords.posX, (double) coords.posY, (double) coords.posZ);
			
			player.addPotionEffect(new PotionEffect(Potion.blindness.id, 600, 1));
			player.addPotionEffect(new PotionEffect(Potion.confusion.id, 150, 3));
			player.addPotionEffect(new PotionEffect(Potion.hunger.id, 250, 0));
			player.inventory.setInventorySlotContents(slot, null);
			player.setHealth(1);
		}
	}
}
