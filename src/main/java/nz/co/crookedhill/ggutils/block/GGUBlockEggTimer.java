package nz.co.crookedhill.ggutils.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import nz.co.crookedhill.ggutils.GGUtils;

public class GGUBlockEggTimer extends Block {

	private IIcon[] icons = new IIcon[2];

	protected GGUBlockEggTimer(Material material) {
		super(material);
		this.setBlockName("eggTimer");
		this.setHardness(2.5f);
		this.setStepSound(Block.soundTypeAnvil);
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		for(int i = 0; i < icons.length; i++) {
			icons[i] = iconRegister.registerIcon(GGUtils.MODID + ":" + "sortivator_texture"+ i);
		}
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		if(side == 1) {
			return icons[1];
		}else return icons[0];

	}
}
