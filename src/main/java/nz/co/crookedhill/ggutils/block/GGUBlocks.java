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

package nz.co.crookedhill.ggutils.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import nz.co.crookedhill.ggutils.GGUtils;
import nz.co.crookedhill.ggutils.helper.GGUConfigManager;
import nz.co.crookedhill.ggutils.item.GGUItems;
import cpw.mods.fml.common.registry.GameRegistry;

public class GGUBlocks
{

	public static Block growthBlock;
	public static Block growthBlockSemi;
	public static Block growthBlockAuto;
	public static Block sortivator;
	public static Block eggTimer;
	public static Block lazyCrafter;
	public static Block fabricator;
	public static Block modularCore;
	public static Block modularLimb;

	public static void init() 
	{	
		//register Blocks	
		growthBlock = new GGUBlockGrowthBlock(Material.ground).setCreativeTab(GGUtils.ggutilsCreativeTab);
		growthBlockSemi = new GGUBlockGrowthBlockSemi(Material.ground).setCreativeTab(GGUtils.ggutilsCreativeTab);
		growthBlockAuto = new GGUBlockGrowthBlockAuto(Material.ground).setCreativeTab(GGUtils.ggutilsCreativeTab);
		sortivator = new GGUBlockSortivator(Material.wood).setCreativeTab(GGUtils.ggutilsCreativeTab);
		eggTimer = new GGUBlockEggTimer(Material.anvil).setCreativeTab(GGUtils.ggutilsCreativeTab);
		lazyCrafter = new GGULazyCrafter(Material.wood).setCreativeTab(GGUtils.ggutilsCreativeTab);
		fabricator = new GGUFabricator(Material.wood).setCreativeTab(GGUtils.ggutilsCreativeTab);
		modularCore = new GGUBlockModularCore(Material.iron).setCreativeTab(GGUtils.ggutilsCreativeTab);
		modularLimb = new GGUBlockModularLimb(Material.packedIce).setCreativeTab(GGUtils.ggutilsCreativeTab);


		registerItems();
		registerRecipes();

	}

	
	private static void registerItems()
	{
		if(GGUConfigManager.growthBlockEnabled)
		{
			GameRegistry.registerBlock(growthBlock, "GrowthBlock");
			GameRegistry.registerBlock(growthBlockSemi, "GrowthBlockSemi");
			GameRegistry.registerBlock(growthBlockAuto, "GrowthBlockAuto");
		}
		
		if(GGUConfigManager.sortivatorEnabled)
			GameRegistry.registerBlock(sortivator, "sortivator");
		//if(GGUConfigManager.eggTimerEnabled)
		//GameRegistry.registerBlock(eggTimer, "eggTimer");
		//if(GGUConfigManager.lazyCrafterEnabled)
		//GameRegistry.registerBlock(lazyCrafter, "lazyCrafter");
		//if(GGUConfigManager.fabricatorEnabled)
		//GameRegistry.registerBlock(fabricator, "fabricator");

		GameRegistry.registerBlock(modularCore, "modularCore");
		GameRegistry.registerBlock(modularLimb, "modularLimb");

	}
	
	private static void registerRecipes()
	{
		//register Block Recipes
		if(GGUConfigManager.growthBlockEnabled)
		{
			GameRegistry.addRecipe(new ItemStack(GGUBlocks.growthBlock, 1, 0), "ddd", "isi","iii",
					'd', Blocks.dirt, 'i', Items.iron_ingot, 's', Items.speckled_melon);

			GameRegistry.addRecipe(new ItemStack(GGUBlocks.growthBlockSemi, 1, 0), "rrr", "rgr","rrr",
					'g', new ItemStack(GGUBlocks.growthBlock, 1, 0), 'r', Items.redstone);

			GameRegistry.addRecipe(new ItemStack(GGUBlocks.growthBlockAuto, 1, 0), "rrr", "dgd","rrr",
					'g', new ItemStack(GGUBlocks.growthBlock, 1, 0), 'd', Items.diamond, 'r', Items.redstone);
		}

		if(GGUConfigManager.sortivatorEnabled)
			GameRegistry.addRecipe(new ItemStack(GGUBlocks.sortivator),"wgw","geg","wgw",
					'w',Blocks.planks,'g',GGUItems.woodenGear,'e',Items.ender_pearl);
		if(GGUConfigManager.lazyCrafterEnabled)
			GameRegistry.addRecipe(new ItemStack(GGUBlocks.lazyCrafter)," c ","cec"," c ",
					'c',Blocks.crafting_table,'e',Items.ender_pearl);
		if(GGUConfigManager.fabricatorEnabled)
			GameRegistry.addRecipe(new ItemStack(GGUBlocks.fabricator),"ici","cec","ici",
					'i',Items.iron_ingot,'c',Blocks.crafting_table,'e',Items.ender_eye);

		GameRegistry.addRecipe(new ItemStack(GGUBlocks.modularCore),"opo","pep","opo",
				'o',Blocks.obsidian,'p',Items.ender_pearl,'e',Items.ender_eye);

		GameRegistry.addRecipe(new ItemStack(GGUBlocks.modularLimb),"igi","gpg","igi",
				'i',Items.iron_ingot,'g',Blocks.glass_pane,'p',Items.ender_pearl);
	}
}
