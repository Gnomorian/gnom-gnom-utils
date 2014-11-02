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
		Block blockToTick = world.getBlock(x, y+1, z);
		if(blockToTick != null && blockToTick instanceof IPlantable)
		{
			blockToTick.updateTick(world, x, y+1, z, rand);
		}
	}

    /**
     * How many world ticks before ticking
     */
	@Override
    public int tickRate(World world)
    {
        return 2;
    }
}
