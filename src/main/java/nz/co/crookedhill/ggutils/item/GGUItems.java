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

package nz.co.crookedhill.ggutils.item;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import nz.co.crookedhill.ggutils.GGUtils;
import nz.co.crookedhill.ggutils.helper.GGUConfigManager;
import cpw.mods.fml.common.registry.GameRegistry;

public class GGUItems
{
	public static Item blockFinder;
	public static Item woodenGear;
	public static Item arseTardis;
	public static Item enderiumRebirthStable;
	public static Item enderiumRebirthUnstable;
	public static Item boat;
	public static Item selfPlaceCart;
	public static Item messi;
	

	public static void init()
	{

		blockFinder = new ItemBlockFinder().setUnlocalizedName("blockFinder").setCreativeTab(GGUtils.ggutilsCreativeTab);
		woodenGear = new GGUWoodenGear().setUnlocalizedName("woodenGear").setCreativeTab(GGUtils.ggutilsCreativeTab);
		enderiumRebirthStable = new GGUEnderiumRebirth(true).setUnlocalizedName("stableEnderiumRebirth").setCreativeTab(GGUtils.ggutilsCreativeTab);
		enderiumRebirthUnstable = new GGUEnderiumRebirth(false).setUnlocalizedName("unstableEnderiumRebirth").setCreativeTab(GGUtils.ggutilsCreativeTab);
		selfPlaceCart = new GGUItemCart().setUnlocalizedName("selfPlaceCart").setCreativeTab(GGUtils.ggutilsCreativeTab);
		boat = new GGUItemBoat().setUnlocalizedName("betterBoat").setCreativeTab(GGUtils.ggutilsCreativeTab);
		messi = new GGUItemMessi().setUnlocalizedName("messi").setCreativeTab(GGUtils.ggutilsCreativeTab);

		registerItems();
		registerRecipes();
	}

	private static void registerItems()
	{
		// Register Items
//		if (GGUConfigManager.blockFinderEnabled)
//			GameRegistry.registerItem(blockFinder, "blockFinder");
		GameRegistry.registerItem(woodenGear, "woodenGear");
		if (GGUConfigManager.enderiumRebirthEnabled)
		{
			GameRegistry.registerItem(enderiumRebirthStable, enderiumRebirthStable.getUnlocalizedName());
			GameRegistry.registerItem(enderiumRebirthUnstable, enderiumRebirthUnstable.getUnlocalizedName());
		}

//		GameRegistry.registerItem(boat, boat.getUnlocalizedName());
//		GameRegistry.registerItem(selfPlaceCart, selfPlaceCart.getUnlocalizedName());
		GameRegistry.registerItem(messi, messi.getUnlocalizedName());
	}

	private static void registerRecipes()
	{
		// Register Item Recipies
		GameRegistry.addRecipe(new ItemStack(woodenGear), "sws", "w w", "sws", 's', Items.stick, 'w', Blocks.planks);
		if (GGUConfigManager.enderiumRebirthEnabled)
		{
			GameRegistry.addRecipe(new ItemStack(enderiumRebirthStable), "pgp", "geg", "pgp", 'p', Items.ender_pearl, 'g', Items.gold_ingot, 'e', Items.ender_eye);
			GameRegistry.addRecipe(new ItemStack(enderiumRebirthUnstable), "gpg", "gtg", "gpg", 'p', Items.ender_pearl, 't', Blocks.tnt, 'g', Items.gunpowder);
		}

		GameRegistry.addRecipe(new ItemStack(messi), "lll", "lel", "lll", 'l', new ItemStack(Items.dye, 1, 4), 'e', Items.ender_eye);
	}

}
