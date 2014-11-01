package nz.co.crookedhill.ggutils.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class GGUBlocks {
	
	public static Block growthBlock;
	
	public static void init() {
		
		growthBlock = new GrowthBlock(Material.carpet);
		
		
		GameRegistry.registerBlock(growthBlock, "GrowthBlock");
	}
}
