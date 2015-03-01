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

public class GGUApatiteTools 
{
	private String apititeName = "apitite";
	/**
	 * checks if forestries apitite crystals are loaded in the game, 
	 * if they are, register recipes for apitite tools.
	 * @param material tool material to use on the apitite tools
	 */
	public GGUApatiteTools(ToolMaterial material)
	{
		if(GameData.getItemRegistry().containsKey(apititeName))
		{
			ItemStack apitite = new ItemStack(GameData.getItemRegistry().getObject(apititeName));
			GameRegistry.addRecipe(new ItemStack(this.new GGUApatitePickaxe(material)), "aaa", " s ", " s ", 'a', apitite, 's', Items.stick);
			GameRegistry.addRecipe(new ItemStack(this.new GGUApatiteAxe(material)), "aa ", "as ", " s ", 'a', apitite, 's', Items.stick);
			GameRegistry.addRecipe(new ItemStack(this.new GGUApatiteShovel(material)), " a ", " s ", " s ", 'a', apitite, 's', Items.stick);
			GameRegistry.addRecipe(new ItemStack(this.new GGUApatiteSword(material)), " a ", " a ", " s ", 'a', apitite, 's', Items.stick);
			GameRegistry.addRecipe(new ItemStack(this.new GGUApatiteHoe(material)), "aa ", " s ", " s ", 'a', apitite, 's', Items.stick);
		}
	}
	
	public class GGUApatitePickaxe extends ItemPickaxe
	{

		protected GGUApatitePickaxe(ToolMaterial material) {
			super(material);
			// TODO Auto-generated constructor stub
		}
		
	}
	
	public class GGUApatiteAxe extends ItemAxe
	{

		protected GGUApatiteAxe(ToolMaterial material) {
			super(material);
			// TODO Auto-generated constructor stub
		}
		
	}
	public class GGUApatiteShovel extends ItemSpade
	{

		public GGUApatiteShovel(ToolMaterial material) {
			super(material);
			// TODO Auto-generated constructor stub
		}
		
	}
	public class GGUApatiteSword extends ItemHoe
	{

		public GGUApatiteSword(ToolMaterial material) {
			super(material);
			// TODO Auto-generated constructor stub
		}
		
	}
	public class GGUApatiteHoe extends ItemSword
	{

		public GGUApatiteHoe(ToolMaterial material) {
			super(material);
			// TODO Auto-generated constructor stub
		}
		
	}
}
