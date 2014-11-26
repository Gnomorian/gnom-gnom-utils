package nz.co.crookedhill.ggutils.handlers;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChunkCoordinates;
import net.minecraftforge.common.DimensionManager;
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
		    findERB: for (int i = 0; i < inv.length; i++)
		    {
			ItemStack item = inv[i];
			if (item == null)
			    continue;
			if (item.getItem() instanceof GGUEnderiumRebirth && !item.getUnlocalizedName().contains("unstableEnderiumRebirth"))
			{
			    event.setCanceled(true);
			    player.setHealth(player.getMaxHealth());
			    Integer[] dimIDs = DimensionManager.getIDs();
			    ChunkCoordinates spawn = player.worldObj.getSpawnPoint();
			    for (int j = 0; i < dimIDs.length; i++)
			    {
				if (player.getBedLocation(dimIDs[j]) == null)
				{
				    continue;
				}
				spawn = player.getBedLocation(dimIDs[j]);
				System.out.println("player has spawn in " + dimIDs[j] + " dimention.");
				break;

			    }
			    player.setPositionAndUpdate(spawn.posX, spawn.posY, spawn.posZ);
			    player.addPotionEffect(new PotionEffect(Potion.blindness.id, 120, 1));
			    player.addPotionEffect(new PotionEffect(Potion.confusion.id, 250, 3));
			    player.addPotionEffect(new PotionEffect(Potion.hunger.id, 2500, 0));
			    inv[i] = null;
			    break findERB; // find enderium rebirth crystal
					   // (ERB)
			} else if (item.getItem() instanceof GGUEnderiumRebirth)
			{
			    event.setCanceled(true);
			    player.setHealth(player.getMaxHealth() / 2);
			    Random rand = new Random();
			    ChunkCoordinates coords = new ChunkCoordinates();
			    coords.posX = rand.nextInt(16) + (int) player.posX;
			    coords.posY = rand.nextInt(5) + (int) player.posY;
			    coords.posZ = rand.nextInt(16) + (int) player.posZ;
			    player.worldObj.createExplosion(player, coords.posX, coords.posY, coords.posZ, 1f, true);
			    player.setPositionAndUpdate((double) coords.posX, (double) coords.posY, (double) coords.posZ);
			    player.addPotionEffect(new PotionEffect(Potion.blindness.id, 120, 1));
			    player.addPotionEffect(new PotionEffect(Potion.confusion.id, 250, 3));
			    player.addPotionEffect(new PotionEffect(Potion.hunger.id, 500, 0));

			    inv[i] = null;
			    break findERB; // find enderium rebirth crystal
					   // (ERB)
			}
		    }
		}
	    }
	}
    }
}