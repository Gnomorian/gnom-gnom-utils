package nz.co.crookedhill.ggutils.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import nz.co.crookedhill.ggutils.GGUtils;
import nz.co.crookedhill.ggutils.entity.tile.GGUEntityModularCore;
import nz.co.crookedhill.ggutils.extendedprops.GGUExtendedPlayer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GGUBlockModularCore extends Block {

	IIcon icon;
	Random rand = new Random();

	public GGUBlockModularCore(Material material) 
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
	public void onBlockPlacedBy(World world, int x,
			int y, int z, EntityLivingBase entity,
			ItemStack stack) {
		super.onBlockPlacedBy(world, x, y, z,
				entity, stack);

		if(entity instanceof EntityPlayer && !world.isRemote)
		{
			GGUEntityModularCore te = (GGUEntityModularCore)world.getTileEntity(x, y, z);
			te.setOwner(entity.getCommandSenderName());
			this.setBlockUnbreakable();
		}
	}

	@Override
	public void onBlockDestroyedByPlayer(World world, int x,
			int y, int z, int meta) {
		super.onBlockDestroyedByPlayer(world, x, y,
				z, meta);
		
		GGUEntityModularCore te = (GGUEntityModularCore)world.getTileEntity(x, y, z);
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		if(!world.isRemote){
			if(!te.isOwner(player.getCommandSenderName()))
			{
				GGUExtendedPlayer props = GGUExtendedPlayer.get(player);
				props.setMess(false);
				props.setMessCoords(null);
			}
		}
	}
	@Override
	public void onBlockClicked(World world, int x,
			int y, int z, EntityPlayer player) {
		super.onBlockClicked(world, x, y, z,
				player);

		GGUEntityModularCore te = (GGUEntityModularCore)world.getTileEntity(x, y, z);
		if(!world.isRemote){
			if(!te.isOwner(player.getCommandSenderName()))
			{
				player.attackEntityFrom(new DamageSource("thorns"), 2.0f);
				player.addChatComponentMessage(new ChatComponentText("No! Not yours!"));
			}
		}
	}



	@Override
	public boolean onBlockActivated(World world, int x,
			int y, int z, EntityPlayer player,
			int meta, float par7, float par8,
			float par9) {
		GGUEntityModularCore te = (GGUEntityModularCore)world.getTileEntity(x, y, z);
		
		if(!world.isRemote){
			if(te.isOwner(player.getCommandSenderName()))
			{
				GGUExtendedPlayer props = GGUExtendedPlayer.get(player);
				int[] coords = {x, y, z};

				te.reCalculateLimbs(coords, true);
				int numberOfLimbs = te.getNumberOfLimbs();
				props.setNumberofLimbs(numberOfLimbs);

				player.addChatComponentMessage(new ChatComponentText("There are "+props.getNumberOfLimbs() + " limbs attached to this core."));
				
				return true;
			}
			else
			{
				player.attackEntityFrom(new DamageSource("thorns"), 0.5f);
				player.addChatComponentMessage(new ChatComponentText("No! Not yours!"));
				return true;
			}
		}

		return false;
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
