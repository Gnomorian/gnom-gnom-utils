package nz.co.crookedhill.ggutils.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import nz.co.crookedhill.ggutils.GGUtils;

public class GGUBlocks {
	
	public static Block growthBlock;
	public static Block sortivator;
	
	//growthblock configuration
	public static int stackHeight = 16;
	
	public static void init() {
		
		growthBlock = new GrowthBlock(Material.ground).setCreativeTab(GGUtils.ggutilsCreativeTab);
		sortivator = new Sortivator(Material.wood).setCreativeTab(GGUtils.ggutilsCreativeTab);
		
		GameRegistry.registerBlock(growthBlock, "GrowthBlock");
		GameRegistry.registerBlock(sortivator, "sortivator");
	}
}
