package nz.co.crookedhill.ggutils.entity.monster;

import net.minecraft.entity.Entity;

public class GGUEntityMob {
	
	Entity creeperMite;
	
	public void init() {
		
		creeperMite = new GGUEntityCreeperMite(null);
		
	}
}
