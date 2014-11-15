package nz.co.crookedhill.ggutils.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import nz.co.crookedhill.ggutils.GGUtils;
import nz.co.crookedhill.ggutils.entity.item.GGUEntityModularLimb;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GGUBlockModularLimb extends Block {

	IIcon icon;
	Random rand = new Random();
	
	protected GGUBlockModularLimb(Material material) 
	{
		super(material);
		this.setBlockName("modularLimb"); 
		this.setHardness(2.5f);
		this.setStepSound(Block.soundTypeMetal);
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister) 
	{	
		icon = iconRegister.registerIcon(GGUtils.MODID + ":" + "limb_texture");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) 
	{
		return icon;
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess p_149646_1_,
			int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_) 
	{
		return true;
	}
	
	@Override
	public boolean renderAsNormalBlock() 
	{
		return false;
	}
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	@Override
	public boolean hasTileEntity(int metadata) 
	{
		return true;
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata) 
	{	
		super.createTileEntity(world, metadata);
		ItemStack stack = new ItemStack(Item.getItemById(rand.nextInt(Item.itemRegistry.getKeys().size())));
		
		return new GGUEntityModularLimb(stack);
	}
}
