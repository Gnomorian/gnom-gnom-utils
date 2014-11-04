package nz.co.crookedhill.ggutils;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;
import nz.co.crookedhill.ggutils.block.GGUBlocks;

public class GGUConfigManager {
	
	//BLOCKS
	public static int GrowthBlockStackHeight;
	
	public static void init(FMLPreInitializationEvent event) {
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		GrowthBlockStackHeight = config.getInt("growthBlockStackHeight", "Blocks", 16, 1, 255, "Edit the functional stack size of the Damara's Remedy block");
		config.save();
	}
	
}
