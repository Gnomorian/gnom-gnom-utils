/* 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. 
 */

package nz.co.crookedhill.ggutils.helper;

import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class GGUConfigManager
{

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
	 public static boolean enderiumRebirthEnabled;
	 
	//ENTITIES
	public static boolean creeperMiteExist;
	public static int creeperMiteGrassChance;
	public static int creeperMiteHelpChance;
	public static boolean creeperMiteNoAir;

	//ENCHANTMENTS
	public static boolean autoSmeltEnabled;
	public static int autoSmeltid;	
	public static boolean icarusEnabled;
	public static int icarusID;

	//OTHER
	public static int growthBlockAutoHarvestHeight;

	public static void init(FMLPreInitializationEvent event) 
	{
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());

		try
		{
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
			enderiumRebirthEnabled = config.getBoolean("enderiumRebirthEnabled", "Items", true, "is the Enderium Rebirth Crystal item enabled in game");

			creeperMiteGrassChance = config.getInt("creeperMiteGrassChance", "Entities", 10, 0, 100, "Chance (out of 100) that a creeper mite will spawn from a tall grass block.");
			creeperMiteHelpChance = config.getInt("creeperMiteHelpChance", "Entities", 70, 0, 100, "Chance (out of 100) that a creeper mite will spawn from a tall grass block when player hits another CreeperMite.");
			creeperMiteExist = config.getBoolean("creeperMiteExist", "Entities", true, "set to false to stop the creeper mite from existing in your game");
			creeperMiteNoAir = config.getBoolean("creeperMiteGettingAir", "Entities", true, "set to falce to reduce the height you are tossed when a creeper explodes");

			autoSmeltEnabled = config.getBoolean("autoSmeltEnabled", "Enchantment", true, "is Prosperity enhcantment enabled in game");
			autoSmeltid = config.getInt("autoSmeltid", "Enchantment", 103, 100, 256, "the id of the Prosperity enchantment");
			icarusEnabled = config.getBoolean("icarusEnabled", "Enchantment", true, "is Icarus enchantment enabled in game");
			icarusID = config.getInt("icarusID", "Enchantment", 104, 100, 256, "the id of the Icarus enchantment");
			
			
			growthBlockAutoHarvestHeight = config.getInt("growthBlockAutoHarvestHeight", "Other", 4, 1, 32, "Set height of cacti/reeds to harvest with the growth block in auto mode.");

		} 
		catch (Exception e)
		{
			e.printStackTrace();
		} 
		finally
		{
			config.save();
		}

	}
}
