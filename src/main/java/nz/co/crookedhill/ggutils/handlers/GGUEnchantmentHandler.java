package nz.co.crookedhill.ggutils.handlers;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.BlockOre;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class GGUEnchantmentHandler {
	@SubscribeEvent
	public void onBlockBreak(BreakEvent event) {
		if(!event.world.isRemote) {
			Random rand = new Random();
			for(int i = 0; i < event.getPlayer().getHeldItem().getEnchantmentTagList().tagCount(); i++){
				if(event.getPlayer().getHeldItem().getEnchantmentTagList().getStringTagAt(i).equals("{lvl:3s,id:103s,}")) {
					System.out.println("this is an autosmelt enchantment, at level 3");
					if(event.block instanceof BlockOre) {
						event.setCanceled(true);
						FurnaceRecipes recipes = FurnaceRecipes.smelting();
						ArrayList<ItemStack> items = event.block.getDrops(event.world, event.x, event.y, event.z, 0, 3);
						for(int j = 0; j < items.size(); j++){
							if(recipes.getSmeltingResult(items.get(j)) != null) {
								items.set(j, recipes.getSmeltingResult(items.get(j)));
								int dropCount = items.get(i).stackSize + rand.nextInt(3);
								items.get(i).stackSize = dropCount;
							}
						}
						event.world.setBlockToAir(event.x, event.y, event.z);
						for(int j = 0; j < items.size(); j++){
							event.world.spawnEntityInWorld(new EntityItem(event.world, (float)event.x, (float)event.y, (float)event.z, items.get(j)));
						}

					}
				}
			}
			for(int i = 0; i < event.getPlayer().getHeldItem().getEnchantmentTagList().tagCount(); i++){
				if(event.getPlayer().getHeldItem().getEnchantmentTagList().getStringTagAt(i).equals("{lvl:2s,id:103s,}")) {
					System.out.println("this is an autosmelt enchantment, at level 3");
					if(event.block instanceof BlockOre) {
						event.setCanceled(true);
						FurnaceRecipes recipes = FurnaceRecipes.smelting();
						ArrayList<ItemStack> items = event.block.getDrops(event.world, event.x, event.y, event.z, 0, 3);
						for(int j = 0; j < items.size(); j++){
							if(recipes.getSmeltingResult(items.get(j)) != null) {
								items.set(j, recipes.getSmeltingResult(items.get(j)));
								int dropCount = items.get(i).stackSize + rand.nextInt(3);
								items.get(i).stackSize = dropCount;
							}
						}
						event.world.setBlockToAir(event.x, event.y, event.z);
						for(int j = 0; j < items.size(); j++){
							event.world.spawnEntityInWorld(new EntityItem(event.world, (float)event.x, (float)event.y, (float)event.z, items.get(j)));
						}

					}
				}
			}
			for(int i = 0; i < event.getPlayer().getHeldItem().getEnchantmentTagList().tagCount(); i++){
				if(event.getPlayer().getHeldItem().getEnchantmentTagList().getStringTagAt(i).equals("{lvl:1s,id:103s,}")) {
					System.out.println("this is an autosmelt enchantment, at level 3");
					if(event.block instanceof BlockOre) {
						event.setCanceled(true);
						FurnaceRecipes recipes = FurnaceRecipes.smelting();
						ArrayList<ItemStack> items = event.block.getDrops(event.world, event.x, event.y, event.z, 0, 3);
						for(int j = 0; j < items.size(); j++){
							if(recipes.getSmeltingResult(items.get(j)) != null) {
								items.set(j, recipes.getSmeltingResult(items.get(j)));
								int dropCount = items.get(i).stackSize + rand.nextInt(3);
								items.get(i).stackSize = dropCount;
							}
						}
						event.world.setBlockToAir(event.x, event.y, event.z);
						for(int j = 0; j < items.size(); j++){
							event.world.spawnEntityInWorld(new EntityItem(event.world, (float)event.x, (float)event.y, (float)event.z, items.get(j)));
						}

					}
				}
			}
		}
	}
}
