package nz.co.crookedhill.ggutils.helper;

import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class GGUConfigManager {
	
	//BLOCKS
	public static int growthBlockStackHeight;
	public static int growthCactusReedMaxHeight;
	public static boolean growthBlockEnabled;
	public static boolean fabricatorEnabled;
	public static boolean sortivatorEnabled;
	public static boolean eggTimerEnabled;
	public static boolean lazyCrafterEnabled;
	public static boolean modularEnderStorageEnabled;
	
	//ITEMS
	public static boolean blockFinderEnabled;
	public static boolean arseTardisEnabled;
	public static boolean anticobbleEnabled;
	
	//ENTITIES
	public static boolean creeperMiteExist;
	public static int creeperMiteChance;
	
	//ENCHANTMENTS
	public static boolean autoSmeltEnabled;
	public static int autoSmeltid;
	
	public static void init(FMLPreInitializationEvent event) {
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		growthBlockStackHeight = config.getInt("growthBlockStackHeight", "Blocks", 16, 1, 255, "Edit the functional stack size of the Damara's Remedy block.");
		growthCactusReedMaxHeight = config.getInt("growthCactusReedMaxHeight", "Blocks", 4, 1, 32, "Set the max growth height of cacti/reeds on Damara's Remedy.");
		growthBlockEnabled = config.getBoolean("growthBlockEnabled", "Blocks", true, "is GrowthBlock enabled in game");
		fabricatorEnabled = config.getBoolean("fabricatorEnabled", "Blocks", true, "is Fabricator enabled in game");
		sortivatorEnabled = config.getBoolean("sortivatorEnabled", "Blocks", true, "is Sortivator enabled in game");
		eggTimerEnabled = config.getBoolean("eggTimerEnabled", "Blocks", true, "is Egg Timer enabled in game");
		modularEnderStorageEnabled = config.getBoolean("modularEnderStorageEnabled", "Blocks", true, "is the multiblock storage system enabled in game");
		lazyCrafterEnabled = config.getBoolean("lazyCrafterEnabled", "Blocks", true, "is lazy Crafter enabled in game");
		
		blockFinderEnabled = config.getBoolean("blockFinderEnabled", "Items", true, "is the block finder enabled in game");
		arseTardisEnabled = config.getBoolean("arseTardisEnabled", "Items", true, "is the Arse Tardis enabled in game");
		anticobbleEnabled = config.getBoolean("anticobbleEnabled", "Items", true, "is the AntiCobble item enabled in game");
		
		creeperMiteChance = config.getInt("creeperMiteChance", "Entities", 10, 0, 100, "Chance (out of 100) that a creeper mite will spawn from a tall grass block.");
		creeperMiteExist = config.getBoolean("creeperMiteExist", "Entities", true, "set to false to stop the creeper mite from existing in your game");
		
		autoSmeltEnabled = config.getBoolean("autoSmeltEnabled", "Enchantment", true, "is auto smelter enchantment enabled in game");
		autoSmeltid = config.getInt("autoSmeltid", "Enchantment", 103, 50, 256, "the id of the enchantment Prosperous Auto-Smelt");
		
		config.save();
	}
}
