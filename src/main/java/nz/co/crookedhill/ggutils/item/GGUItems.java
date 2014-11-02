package nz.co.crookedhill.ggutils.item;

import net.minecraft.item.Item;
import nz.co.crookedhill.ggutils.GGUtils;
import cpw.mods.fml.common.registry.GameRegistry;

public class GGUItems {
	public static Item blockFinder;
	
	public static void init() {
		
		blockFinder = new ItemBlockFinder().setUnlocalizedName("blockFinder").setCreativeTab(GGUtils.ggutilsCreativeTab);
		
		GameRegistry.registerItem(blockFinder, "blockFinder");
	}
}
