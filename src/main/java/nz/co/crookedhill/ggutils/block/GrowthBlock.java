package nz.co.crookedhill.ggutils.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import nz.co.crookedhill.ggutils.GGUtils;

public class GrowthBlock extends Block {

	private IIcon[] icons = new IIcon[2];

	public GrowthBlock(Material material) {
		super(material);
		this.setBlockName("GrowthBlock");
		this.setHardness(0.5f);
		this.setLightLevel(0.5f);
		this.setCreativeTab(CreativeTabs.tabMisc);
		this.setTickRandomly(true);
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		for(int i = 0; i < icons.length; i++) {
			icons[i] = iconRegister.registerIcon(GGUtils.MODID + ":" + "growth_texture"+ i);
		}
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		if(side == 1) {
			return icons[1];
		}else return icons[0];

	}
	@Override
	public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable)
	{
		return true;
	}
	
	@Override
	public boolean isFertile(World world, int x, int y, int z)
	{
		return true;
	}
	
	@Override
    public void updateTick(World world, int x, int y, int z, Random rand) {
		Block block = world.getBlock(x, y+1, z);
		
		//check if block is plantable
		if(block instanceof IPlantable)
		{
			int numberOfGrowthBlocks = 0;
			
			//check how many growth blocks under this one.
			for(int i=y;i>y-16;i--)
			{
				if(world.getBlock(x, i, z).equals(GGUBlocks.growthBlock)){
					numberOfGrowthBlocks++;
				}
			}
			
			//compare the number of growth blocks and test against random. Max test is 16 growth blocks.
			if(rand.nextInt(17 - numberOfGrowthBlocks)==0)
			{
				int metadata = world.getBlockMetadata(x, y+1, z);
				world.setBlockMetadataWithNotify(x, y+1, z, metadata+1, 2);
			}
		}
	}
}
