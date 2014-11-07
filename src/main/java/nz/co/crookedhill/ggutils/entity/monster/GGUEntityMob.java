package nz.co.crookedhill.ggutils.entity.monster;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import nz.co.crookedhill.ggutils.GGUtils;
import nz.co.crookedhill.ggutils.helper.GGUConfigManager;

public class GGUEntityMob {
	
	Entity creeperMite;
	
	public static void init() {
		
		/*if(GGUConfigManager.creeperMiteExist)
		creeperMite = new GGUEntityCreeperMite(null);*/
		
		if(GGUConfigManager.creeperMiteExist) {
			EntityRegistry.registerModEntity(GGUEntityCreeperMite.class, "creeper_mite", 0, GGUtils.instance, 64, 1, true);
			EntityRegistry.addSpawn(GGUEntityCreeperMite.class, 200, 1, 8, EnumCreatureType.monster);
			EntityList.addMapping(GGUEntityCreeperMite.class, "creeper_mite", 0, 113213, 3523523);
		}
		
	}
}
