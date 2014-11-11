/*
 * Copyright (c) 2014, William <w.cameron@crookedhill.co.nz>
 * 
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
 * ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
 * OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */

package nz.co.crookedhill.ggutils.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCake;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockOre;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import nz.co.crookedhill.ggutils.GGUtils;
import nz.co.crookedhill.ggutils.helper.GGUConfigManager;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GGUBlockGrowthBlock extends Block {

	private IIcon[] icons = new IIcon[3];
	private int stackHeight = GGUConfigManager.growthBlockStackHeight;
	private int growthHeight = GGUConfigManager.growthCactusReedMaxHeight;
	
	/*0=manual,1=redstone,2=automatic*/
	int mode = 0;

	public GGUBlockGrowthBlock(Material material) {
		super(material);
		this.setBlockName("GrowthBlock");
		this.setHardness(0.5f);
		this.setLightLevel(0.5f);
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

			//compare the number of growth blocks and test against random. Max test is now editable in config.
			if(rand.nextInt((stackHeight+1) - numberOfGrowthBlocks)==0)
			{
				int metadata = world.getBlockMetadata(x, y+1, z);

				if(block == Blocks.nether_wart)
				{
					if(metadata < 3)
					{
						world.setBlockMetadataWithNotify(x, y+1, z, metadata+1, 2);
					}
				}
				else if(block == Blocks.reeds)
				{
					int i = 1;
					while(world.getBlock(x, y+i, z) == Blocks.reeds)
					{						
						i++;
					}
					if(i<growthHeight){
						if(world.isAirBlock(x, y+i, z))
						{
							world.setBlock(x, y+i, z, Blocks.reeds, 2, 2);	
						}
					}
				}
				else if(block == Blocks.cactus)
				{
					int i = 1;
					while(world.getBlock(x, y+i, z) == Blocks.cactus)
					{						
						i++;
					}
					if(i<growthHeight){
						if(world.isAirBlock(x, y+i, z))
						{
							world.setBlock(x, y+i, z, Blocks.cactus, 2, 2);	
						}
					}
				}
				else if(block instanceof BlockCrops)
				{
					if(metadata < 7)
					{
						world.setBlockMetadataWithNotify(x, y+1, z, metadata+1, 2);
					}
				}
			}
		}
		else if(block instanceof BlockCake)
		{
			if(!world.isRemote)
			{
				world.spawnParticle("hugeexplosion", x, y+1, z, 0.0f, 0.0f, 0.0f);
				world.createExplosion((Entity)null, x, y+1, z, 2.0F, false);
				for(int j=0;j<world.loadedEntityList.size();j++)
				{
					Entity entity = (Entity) world.loadedEntityList.get(j);
					if(entity.getDistance(x, y, z) < 10 && entity instanceof EntityPlayer)
					{
						EntityPlayer player = (EntityPlayer) entity;
						player.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "The cake is a lie."));
					}
				}
				world.setBlockToAir(x, y+1, z);
			}
		}
		else if(block instanceof BlockOre)
		{
			checkIfOre(world, block, x, y, z);
		}
	}


	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block p_149695_5_) {
		updateMeta(world,x,y,z);
		
		boolean flag = world.isBlockIndirectlyGettingPowered( x, y, z);
		if(flag) {
			System.out.println(world.getBlockMetadata(x, y+1, z));
			Block block = world.getBlock(x, y+1, z);
			if(block instanceof IPlantable) {
				//TODO: harvest plant when fully grown
			}
		}
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
		Block upperBlock = world.getBlock(x, y+1, z);
		if(upperBlock instanceof GGUBlockGrowthBlock)
			world.setBlockMetadataWithNotify(x, y, z, 1, 2);
		else world.setBlockMetadataWithNotify(x, y, z, 0, 2);
	}

	private void checkIfOre(World world, Block block, int x, int y, int z)
	{
		if(block == Blocks.coal_ore)
		{
			world.setBlock(x, y+1, z, Blocks.iron_ore, 0, 2);	
		}
		else if(block == Blocks.iron_ore)
		{
			world.setBlock(x, y+1, z, Blocks.gold_ore, 0, 2);	
		}
		else if(block == Blocks.gold_ore)
		{
			world.setBlock(x, y+1, z, Blocks.diamond_ore, 0, 2);	
		}
		else if(block == Blocks.diamond_ore)
		{
			world.setBlock(x, y+1, z, Blocks.cake, 2, 2);	
		}
	}
}
