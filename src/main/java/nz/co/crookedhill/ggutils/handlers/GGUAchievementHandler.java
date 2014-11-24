package nz.co.crookedhill.ggutils.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import nz.co.crookedhill.ggutils.GGUtils;
import nz.co.crookedhill.ggutils.block.GGUBlocks;
import nz.co.crookedhill.ggutils.entity.monster.GGUEntityCreeperMite;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;

public class GGUAchievementHandler
{
    /**
     * a function that is called everytime a player is falling. it is used to
     * check how far the player has fallen, then award them an achievement if
     * they fell 500 blocks and where attacked by a creepermite.
     */
    @SubscribeEvent
    public void onFallhandler(LivingFallEvent event)
    {
	if (event.entityLiving instanceof EntityPlayer)
	{
	    if (event.distance > 500 && event.entityLiving.getLastAttacker() instanceof GGUEntityCreeperMite)
		((EntityPlayer) event.entityLiving).addStat(GGUtils.achievements.creepMiteExplosion, 1);
	}
    }

    /**
     * a function subscribed to an event that is called when a player pulls a
     * crafting result out of the crafting bench. this functiono currently
     * doesnt work, and needs fixing.
     * 
     * this function exists to enable achievements from crafting sortivator etc.
     */
    @SubscribeEvent
    public void onCraft(PlayerEvent.ItemCraftedEvent event)
    {
	System.out.println("craft event is being called");
	if (event.crafting.getUnlocalizedName().equals(GGUBlocks.sortivator.getUnlocalizedName()))
	{
	    System.out.println("crafting sucseeded");
	    event.player.addStat(GGUtils.achievements.craftedSortivator, 1);
	} else
	{
	    System.out.println(event.crafting.getUnlocalizedName());
	    System.out.println(GGUtils.blocks.sortivator.getUnlocalizedName());
	}
    }
}
