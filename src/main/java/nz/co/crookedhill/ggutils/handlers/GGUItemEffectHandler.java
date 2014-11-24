package nz.co.crookedhill.ggutils.handlers;

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
		if (event.ammount > ((EntityPlayer) event.entity).getHealth())
		{
		    EntityPlayer player = (EntityPlayer) event.entity;
		    ItemStack[] inv = player.inventory.mainInventory;
		    for (int i = 0; i < inv.length; i++)
		    {
			ItemStack item = inv[i];
			if (item == null)
			    continue;
			if (item.getItem() instanceof GGUEnderiumRebirth)
			{
			    event.setCanceled(true);
			    player.setHealth(player.getMaxHealth());
			    ChunkCoordinates spawn = player.getBedLocation(player.dimension);
			    if (spawn != null)
			    {
				player.setPositionAndUpdate(spawn.posX, spawn.posY, spawn.posZ);
			    } else
			    {
				ChunkCoordinates defaultSpawn = player.worldObj.getSpawnPoint();
				player.setPositionAndUpdate(defaultSpawn.posX, defaultSpawn.posY, defaultSpawn.posZ);
				player.addPotionEffect(new PotionEffect(Potion.blindness.id, 120, 1));
				player.addPotionEffect(new PotionEffect(Potion.confusion.id, 250, 3));
				player.addPotionEffect(new PotionEffect(Potion.hunger.id, 5000, 0));

			    }
			    inv[i] = null;
			}
		    }
		}
	    }
	}
    }
}