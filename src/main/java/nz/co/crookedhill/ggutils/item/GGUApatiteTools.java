package nz.co.crookedhill.ggutils.item;

import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import nz.co.crookedhill.ggutils.GGUtils;

public class GGUApatiteTools 
{
	public Item sword;
	public Item pickaxe;
	public Item shovel;
	public Item axe;
	public Item hoe;
	
	private String apititeName = "Forestry:apatite";
	/**
	 * checks if forestries apitite crystals are loaded in the game,
	 * if they are, register recipes for apitite tools.
	 * @param material tool material to use on the apitite tools
	 */
	public GGUApatiteTools(ToolMaterial material)
	{
		if(GameData.getItemRegistry().containsKey(apititeName))
		{
			// RECIPES
			ItemStack apitite = new ItemStack(GameData.getItemRegistry().getObject(apititeName));
			GameRegistry.addRecipe(new ItemStack(pickaxe = this.new GGUApatitePickaxe(material)), "aaa", " s ", " s ", 'a', apitite, 's', Items.stick);
			GameRegistry.addRecipe(new ItemStack(axe = this.new GGUApatiteAxe(material)), "aa ", "as ", " s ", 'a', apitite, 's', Items.stick);
			GameRegistry.addRecipe(new ItemStack(shovel = this.new GGUApatiteShovel(material)), " a ", " s ", " s ", 'a', apitite, 's', Items.stick);
			GameRegistry.addRecipe(new ItemStack(sword = this.new GGUApatiteSword(material)), " a ", " a ", " s ", 'a', apitite, 's', Items.stick);
			GameRegistry.addRecipe(new ItemStack(hoe = this.new GGUApatiteHoe(material)), "aa ", " s ", " s ", 'a', apitite, 's', Items.stick);
			
			//REGISTERY
			GameRegistry.registerItem(sword, sword.getUnlocalizedName().replace("item.", ""));
			GameRegistry.registerItem(pickaxe, pickaxe.getUnlocalizedName().replace("item.", ""));
			GameRegistry.registerItem(shovel, shovel.getUnlocalizedName().replace("item.", ""));
			GameRegistry.registerItem(axe, axe.getUnlocalizedName().replace("item.", ""));
			GameRegistry.registerItem(hoe, hoe.getUnlocalizedName().replace("item.", ""));
		}
	}
	
	public class GGUApatitePickaxe extends ItemPickaxe
	{

		protected GGUApatitePickaxe(ToolMaterial material) {
			super(material);
			this.setUnlocalizedName("apitite_pickaxe");
			this.setCreativeTab(GGUtils.ggutilsCreativeTab);
		}
		
	}
	
	public class GGUApatiteAxe extends ItemAxe
	{
		protected GGUApatiteAxe(ToolMaterial material) {
			super(material);
			this.setUnlocalizedName("apitite_axe");
			this.setCreativeTab(GGUtils.ggutilsCreativeTab);
		}
		
	}
	public class GGUApatiteShovel extends ItemSpade
	{

		public GGUApatiteShovel(ToolMaterial material) {
			super(material);
			this.setUnlocalizedName("apitite_spade");
			this.setCreativeTab(GGUtils.ggutilsCreativeTab);
		}
		
	}
	public class GGUApatiteSword extends ItemHoe
	{

		public GGUApatiteSword(ToolMaterial material) {
			super(material);
			this.setUnlocalizedName("apitite_hoe");
			this.setCreativeTab(GGUtils.ggutilsCreativeTab);
		}
		
	}
	public class GGUApatiteHoe extends ItemSword
	{

		public GGUApatiteHoe(ToolMaterial material) {
			super(material);
			this.setUnlocalizedName("apitite_sword");
			this.setCreativeTab(GGUtils.ggutilsCreativeTab);
		}
		
	}
}
