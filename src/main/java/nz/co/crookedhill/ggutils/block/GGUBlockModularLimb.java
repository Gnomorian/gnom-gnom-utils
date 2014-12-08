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
import nz.co.crookedhill.ggutils.entity.tile.GGUEntityModularLimb;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GGUBlockModularLimb extends Block {

	IIcon icon;
	Random rand = new Random();

	public GGUBlockModularLimb(Material material) 
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
	public boolean shouldSideBeRendered(IBlockAccess world,
			int x, int y, int z, int side) 
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

			int maxItems = Item.itemRegistry.getKeys().size();
			int randomItem = rand.nextInt(maxItems);
			Item itemToStack = Item.getItemById(randomItem);
			
			//to make sure random item is never null
			while(itemToStack == null)
			{
				maxItems = Item.itemRegistry.getKeys().size();
				randomItem = rand.nextInt(maxItems);
				itemToStack = Item.getItemById(randomItem);
			}
			ItemStack stack = new ItemStack(itemToStack);
			stack.stackSize = rand.nextInt(64);
			return new GGUEntityModularLimb(stack);
	}
}
