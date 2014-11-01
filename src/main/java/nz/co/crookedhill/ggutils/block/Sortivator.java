package nz.co.crookedhill.ggutils.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import nz.co.crookedhill.ggutils.GGUtils;

public class Sortivator extends Block {
	
	private IIcon[] icons = new IIcon[2];
	
	protected Sortivator(Material material) {
		super(material);
		this.setBlockName("sortivator");
		this.setCreativeTab(CreativeTabs.tabRedstone);
		this.setHardness(0.5f);
		this.setStepSound(Block.soundTypeWood);
		//this.setBlockTextureName("sortivator:sortivator_texture0");
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
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack item) {
		System.out.println("x: "+x);
		System.out.println("y: "+y);
		System.out.println("z: "+z);
		System.out.println("Entitie's: "+((EntityPlayer)player).cameraYaw);
		System.out.println("ItemStack: "+item.getDisplayName());
	}	
	
}
