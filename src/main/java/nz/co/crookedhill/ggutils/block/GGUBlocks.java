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
		
		/**
		 * having trouble with these recipes, they both are valid to eclipse, but when running they both crash.
		 * iv tried running it in init() and the block registration in preint but to no avail either.
		 */
		
		/*  RECIPE OPTION 1
		GameRegistry.addRecipe(new ItemStack(GGUBlocks.growthBlock), "ddd", "isi","iii",
		        'd', Blocks.dirt, 'i', Items.iron_ingot, "s", Items.speckled_melon);
		*/
		
		/*  RECIPE OPTION 2 (adapted from shaoks code)
		GameRegistry.addRecipe(new ItemStack(GGUBlocks.growthBlock), 
				new Object[]{"ddd","isi","iii",'d', Blocks.dirt, 'i', Items.iron_ingot, "s", Items.speckled_melon});
				*/
	}
}
