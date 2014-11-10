package nz.co.crookedhill.ggutils.entity.item;


import cpw.mods.fml.common.registry.GameRegistry;

public class GGUEntityTile {

	public static void init(){
		GameRegistry.registerTileEntity(GGUEntityEggTimer.class, "eggTimer");
		
	}
}
