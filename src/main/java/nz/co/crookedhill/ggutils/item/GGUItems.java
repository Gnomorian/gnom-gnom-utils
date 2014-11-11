package nz.co.crookedhill.ggutils.item;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import nz.co.crookedhill.ggutils.GGUtils;
import nz.co.crookedhill.ggutils.helper.GGUConfigManager;
import cpw.mods.fml.common.registry.GameRegistry;

public class GGUItems {
	public static Item blockFinder;
	public static Item woodenGear;
	public static Item arseTardis;
	
	public static void init() {
		
		blockFinder = new ItemBlockFinder().setUnlocalizedName("blockFinder").setCreativeTab(GGUtils.ggutilsCreativeTab);
		woodenGear = new GGUWoodenGear().setUnlocalizedName("woodenGear").setCreativeTab(GGUtils.ggutilsCreativeTab);
		//looks like your pulling shit out your ass
		arseTardis = new GGUArseTardis().setUnlocalizedName("arseTardis").setCreativeTab(GGUtils.ggutilsCreativeTab);
		//Register Items
		if(GGUConfigManager.blockFinderEnabled)
		GameRegistry.registerItem(blockFinder, "blockFinder");
		GameRegistry.registerItem(woodenGear, "woodenGear");
		if(GGUConfigManager.arseTardisEnabled)
		GameRegistry.registerItem(arseTardis, "arseTardis");
		
		//Register Item Recipies
		GameRegistry.addRecipe(new ItemStack(woodenGear),"sws","w w","sws"
				,'s',Items.stick,'w',Blocks.planks);
	}
}
