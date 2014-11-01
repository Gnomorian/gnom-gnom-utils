package nz.co.crookedhill.ggutils.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class GrowthBlock extends Block {
	
	public GrowthBlock(Material material) {
		super(material);
		this.setBlockName("GrowthBlock");
		this.setHardness(0.5f);
		this.setLightLevel(0.5f);
		this.setCreativeTab(CreativeTabs.tabMisc);
		//this.setBlockTextureName("ggutils:GrowthBlock"); no texture yet
		
	}
}
