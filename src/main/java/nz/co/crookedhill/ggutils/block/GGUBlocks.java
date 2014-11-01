package nz.co.crookedhill.ggutils.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class GGUBlocks {
	
	public static Block growthBlock;
	public static Block sortivator;
	
	public static void init() {
		
		growthBlock = new GrowthBlock(Material.carpet);
		sortivator = new Sortivator(Material.wood);
		
		
		GameRegistry.registerBlock(growthBlock, "GrowthBlock");
		GameRegistry.registerBlock(sortivator, "sortivator");
	}
}
