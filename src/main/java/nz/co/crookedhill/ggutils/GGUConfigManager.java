package nz.co.crookedhill.ggutils;

import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class GGUConfigManager {
	
	//BLOCKS
	public static int growthBlockStackHeight;
	public static int growthCactusReedMaxHeight;
	
	public static void init(FMLPreInitializationEvent event) {
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		growthBlockStackHeight = config.getInt("growthBlockStackHeight", "Blocks", 16, 1, 255, "Edit the functional stack size of the Damara's Remedy block.");
		growthCactusReedMaxHeight = config.getInt("growthCactusReedMaxHeight", "Blocks", 4, 1, 32, "Set the max growth height of cacti/reeds on Damara's Remedy.");

		config.save();
	}
	
}
