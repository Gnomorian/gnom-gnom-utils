/*
 * Copyright (c) 2014, William <w.cameron@crookedhill.co.nz>
 * 
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
 * ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
 * OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */

package nz.co.crookedhill.ggutils.helper;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import nz.co.crookedhill.ggutils.GGUtils;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class GGUConfigManager 
{
	private static File configFile;
	private static Configuration config;
	
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
	public static int creeperMiteGrassChance;
	public static int creeperMiteHelpChance;
	public static boolean creeperMiteNoAir;
	
	//ENCHANTMENTS
	public static boolean autoSmeltEnabled;
	public static int autoSmeltid;
	
	public static void init(FMLPreInitializationEvent event) 
	{
		configFile = new File(event.getModConfigurationDirectory().getAbsolutePath()+File.separator+GGUtils.MODID+File.separator+GGUtils.MODID+".cfg");
		config = new Configuration(configFile);
		try {
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
		
		creeperMiteGrassChance = config.getInt("creeperMiteGrassChance", "Entities", 10, 0, 100, "Chance (out of 100) that a creeper mite will spawn from a tall grass block.");
		creeperMiteHelpChance = config.getInt("creeperMiteHelpChance", "Entities", 70, 0, 100, "Chance (out of 100) that a creeper mite will spawn from a tall grass block when player hits another CreeperMite.");
		creeperMiteExist = config.getBoolean("creeperMiteExist", "Entities", true, "set to false to stop the creeper mite from existing in your game");
		creeperMiteNoAir = config.getBoolean("creeperMiteGettingAir", "Entities", true, "set to falce to reduce the height you are tossed when a creeper explodes");
		
		autoSmeltEnabled = config.getBoolean("autoSmeltEnabled", "Enchantment", true, "is auto smelter enchantment enabled in game");
		autoSmeltid = config.getInt("autoSmeltid", "Enchantment", 103, 50, 256, "the id of the enchantment Prosperous Auto-Smelt");
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			config.save();
		}
	}
}
