package nz.co.crookedhill.ggutils.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import nz.co.crookedhill.ggutils.GGUtils;

public class GGUBlockModularCore extends Block {

	IIcon icon;
	
	protected GGUBlockModularCore(Material material) 
	{
		super(material);
		this.setBlockName("modularCore"); 
		this.setHardness(2.5f);
		this.setStepSound(Block.soundTypeGlass);
	}
	
	
	@Override
	public void registerBlockIcons(IIconRegister iconRegister) 
	{	
		icon = iconRegister.registerIcon(GGUtils.MODID + ":" + "core_texture");
	}	
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) 
	{
		return icon;
	}
}
