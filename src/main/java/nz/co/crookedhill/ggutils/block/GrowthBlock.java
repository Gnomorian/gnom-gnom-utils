package nz.co.crookedhill.ggutils.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import nz.co.crookedhill.ggutils.GGUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GrowthBlock extends Block {
	private int timesClicked;

	private IIcon[] icons = new IIcon[3];
	int stackHeight = GGUBlocks.stackHeight;

	public GrowthBlock(Material material) {
		super(material);
		this.setBlockName("GrowthBlock");
		this.setHardness(0.5f);
		this.setLightLevel(0.5f);
		this.setCreativeTab(CreativeTabs.tabMisc);
		this.setTickRandomly(true);
	}

	@Override
	public int damageDropped (int metadata) {
		return metadata;
	}


	/**
	 * 0=side icon(if GrowthBlock ontop)
	 * 1=top icon
	 * 2=side icon(if no GrowthBlock ontop)
	 * @param iconRegister
	 */
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		super.registerBlockIcons(iconRegister);
		for(int i = 0; i < icons.length; i++) {
			icons[i] = iconRegister.registerIcon(GGUtils.MODID + ":" + "growth_texture"+ i);
		}
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		if(meta>1) meta=0; //prevents crashing if block spawned with higher metadata
		if(side == 1) return icons[1];
		if(side == 0 ) {
			return icons[0];
		}else return icons[0];
	}
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int side) {
		int meta = blockAccess.getBlockMetadata(x, y, z);
		if(meta == 0) {
			if(meta>1) meta=0; //prevents crashing if block spawned with higher metadata
			if(side == 1) return icons[1];
			if(side == 0 ) {
				return icons[0];
			}else return icons[2]; 
		}
		else{
			if(meta>1) meta=0; //prevents crashing if block spawned with higher metadata
			if(side == 1) return icons[1];
			if(side == 0 ) {
				return icons[0];
			}else return icons[0];
		}

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
			for(int i=y;i>y-stackHeight;i--)
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
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block p_149695_5_) {
		updateMeta(world,x,y,z);
	}
	//if textures dont change when placing the block, clicking updates it
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
		updateMeta(world,x,y,z);
        return false;
    }
	
	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		updateMeta(world,x,y,z);
	}
	
		
	/**
	 * Updates the textures of all GrowthBlocks
	 * setBlockMeta parameter 5= Flag 2 = cause texture change (spent ages finding this)
	 * @param world
	 * @param x
	 * @param y
	 * @param z 
	 */
	@SideOnly(Side.CLIENT)
	private void updateMeta(World world, int x, int y, int z) {
		Minecraft mc;
		Block upperBlock = world.getBlock(x, y+1, z);
		if(upperBlock instanceof GrowthBlock)
			world.setBlockMetadataWithNotify(x, y, z, 1, 2);
		else world.setBlockMetadataWithNotify(x, y, z, 0, 2);
	}
	

}
