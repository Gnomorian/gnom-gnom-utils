package nz.co.crookedhill.ggutils.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import nz.co.crookedhill.ggutils.GGUtils;
import nz.co.crookedhill.ggutils.item.GGUItems;

public class GGUBlocks {
	
	public static Block growthBlock;
	public static Block sortivator;
	public static Block eggTimer;
	public static Block lazyCrafter;
	
	public static void init() {
		
		//register Blocks	
		growthBlock = new GGUBlockGrowthBlock(Material.ground).setCreativeTab(GGUtils.ggutilsCreativeTab);
		sortivator = new GGUBlockSortivator(Material.wood).setCreativeTab(GGUtils.ggutilsCreativeTab);
		eggTimer = new GGUBlockEggTimer(Material.anvil).setCreativeTab(GGUtils.ggutilsCreativeTab);
		lazyCrafter = new GGULazyCrafter(Material.wood).setCreativeTab(GGUtils.ggutilsCreativeTab);
		
		
		GameRegistry.registerBlock(growthBlock, "GrowthBlock");
		GameRegistry.registerBlock(sortivator, "sortivator");
		GameRegistry.registerBlock(eggTimer, "eggTimer");
		GameRegistry.registerBlock(lazyCrafter, "lazyCrafter");
		
		//register Block Recipes

		//change speckled melon to bonemeal, cant find it in the list of items
		GameRegistry.addRecipe(new ItemStack(GGUBlocks.growthBlock), "ddd", "isi","iii",
		        'd', Blocks.dirt, 'i', Items.iron_ingot, 's', Items.speckled_melon);
		GameRegistry.addRecipe(new ItemStack(GGUBlocks.sortivator),"wgw","geg","wgw",
				'w',Blocks.planks,'g',GGUItems.woodenGear,'e',Items.ender_pearl);
		GameRegistry.addRecipe(new ItemStack(GGUBlocks.lazyCrafter)," c ","cec"," c ",
				'c',Blocks.crafting_table,'e',Items.ender_pearl);
				
	}
}
