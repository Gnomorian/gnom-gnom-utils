package nz.co.crookedhill.ggutils.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import nz.co.crookedhill.ggutils.GGUtils;

public class GGUBlocks {
	
	public static Block growthBlock;
	public static Block sortivator;
	
	public static void init() {
		
		//register Blocks
		
		growthBlock = new GrowthBlock(Material.ground).setCreativeTab(GGUtils.ggutilsCreativeTab);
		sortivator = new Sortivator(Material.wood).setCreativeTab(GGUtils.ggutilsCreativeTab);
		
		GameRegistry.registerBlock(growthBlock, "GrowthBlock");
		GameRegistry.registerBlock(sortivator, "sortivator");
		
		//register Block Recipes

		//change speckled melon to bonemeal, cant find it in the list of items
		GameRegistry.addRecipe(new ItemStack(GGUBlocks.growthBlock), "ddd", "isi","iii",
		        'd', Blocks.dirt, 'i', Items.iron_ingot, 's', Items.speckled_melon);
				
	}
}
