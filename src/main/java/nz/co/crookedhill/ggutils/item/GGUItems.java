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
    public static Item enderiumRebirth;

    public static void init()
    {

	blockFinder = new ItemBlockFinder().setUnlocalizedName("blockFinder").setCreativeTab(GGUtils.ggutilsCreativeTab);
	woodenGear = new GGUWoodenGear().setUnlocalizedName("woodenGear").setCreativeTab(GGUtils.ggutilsCreativeTab);
	// looks like your pulling shit out your ass
	enderiumRebirth = new GGUEnderiumRebirth().setUnlocalizedName("enderiumRebirth").setCreativeTab(GGUtils.ggutilsCreativeTab);

	// Register Items
	if (GGUConfigManager.blockFinderEnabled)
	    GameRegistry.registerItem(blockFinder, "blockFinder");
	GameRegistry.registerItem(woodenGear, "woodenGear");
	if (GGUConfigManager.enderiumRebirthEnabled)
	    GameRegistry.registerItem(enderiumRebirth, enderiumRebirth.getUnlocalizedName());
    }

    public static void registerRecipes()
    {
	// Register Item Recipies
	GameRegistry.addRecipe(new ItemStack(woodenGear), "sws", "w w", "sws", 's', Items.stick, 'w', Blocks.planks);
	if (GGUConfigManager.enderiumRebirthEnabled)
	    GameRegistry.addRecipe(new ItemStack(enderiumRebirth), "pgp", "geg", "pgp", 'p', Items.ender_pearl, 'g', Items.gold_ingot, 'e', Items.ender_eye);

    }

}
