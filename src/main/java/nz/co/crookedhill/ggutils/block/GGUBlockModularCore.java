package nz.co.crookedhill.ggutils.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import nz.co.crookedhill.ggutils.GGUtils;
import nz.co.crookedhill.ggutils.entity.item.GGUEntityModularCore;
import nz.co.crookedhill.ggutils.extendedprops.GGUExtendedPlayer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GGUBlockModularCore extends Block {

	IIcon icon;
	Random rand = new Random();

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
	public boolean onBlockActivated(World world, int x,
			int y, int z, EntityPlayer player,
			int meta, float par7, float par8,
			float par9) {
		
		GGUExtendedPlayer props = GGUExtendedPlayer.get(player);
		if(!world.isRemote){
			System.out.println("PLAYER BEFORE:"+props.getNumberOfLimbs());
			GGUEntityModularCore entity = (GGUEntityModularCore) world.getTileEntity(x, y, z);
			int[] coords = {x, y, z};

			entity.recalculateLimbs(coords);
			int numberOfLimbs = entity.getNumberOfLimbs();
			props.setNumberofLimbs(numberOfLimbs);
			
			System.out.println("PLAYER AFTER:"+props.getNumberOfLimbs());
		}
		return true;
	}
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) 
	{
		return icon;
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
		return new GGUEntityModularCore();
	}
}
