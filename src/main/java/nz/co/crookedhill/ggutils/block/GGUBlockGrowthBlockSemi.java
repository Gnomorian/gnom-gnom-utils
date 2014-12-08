/* 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. 
 */

package nz.co.crookedhill.ggutils.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCake;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockOre;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import nz.co.crookedhill.ggutils.helper.GGUConfigManager;

public class GGUBlockGrowthBlockSemi extends GGUBlockGrowthBlock 
{
	private int stackHeight = GGUConfigManager.growthBlockStackHeight;
	private int growthHeight = GGUConfigManager.growthCactusReedMaxHeight;
	private boolean flag2 = false;

	public GGUBlockGrowthBlockSemi(Material material) 
	{
		super(material);
		this.setBlockName("GrowthBlockSemi");
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) 
	{
		Block block = world.getBlock(x, y+1, z);		

		//check if block is plantable
		if(block instanceof IPlantable)
		{
			int numberOfGrowthBlocks = 0;

			//check how many growth blocks under this one.
			for(int i=y;i>y-stackHeight;i--)
			{
				if(world.getBlock(x, i, z) instanceof GGUBlockGrowthBlock){
					numberOfGrowthBlocks++;
				}
				else
				{
					break;
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
					int numberOfBlocks = 0;

					for(int i=1;i<=growthHeight;i++)
					{
						if(world.getBlock(x, y+i, z) == Blocks.reeds)
						{
							numberOfBlocks++;
						}
						else
						{
							break;
						}
					}
					if(numberOfBlocks<growthHeight){
						if(world.isAirBlock(x, y+numberOfBlocks+1, z))
						{
							world.setBlock(x, y+numberOfBlocks+1, z, Blocks.reeds, 2, 2);	
						}
					}
				}
				else if(block == Blocks.cactus)
				{
					int numberOfBlocks = 0;

					for(int i=1;i<=growthHeight;i++)
					{
						if(world.getBlock(x, y+i, z) == Blocks.cactus)
						{
							numberOfBlocks++;
						}
						else
						{
							break;
						}
					}
					if(numberOfBlocks<growthHeight){
						if(world.isAirBlock(x, y+numberOfBlocks+1, z))
						{
							world.setBlock(x, y+numberOfBlocks+1, z, Blocks.cactus, 2, 2);	
						}
					}
				}
				else if(block instanceof BlockCrops)
				{
					if(metadata < 7)
					{
						world.setBlockMetadataWithNotify(x, y+1, z, metadata+1, 2);
					}
					if(metadata == 7)
					{
						world.func_147480_a(x, y+1, z, true);
						world.setBlock(x, y, z, block, 0, 2);

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
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
	{
		boolean flag = world.isBlockIndirectlyGettingPowered(x, y, z);

		Block topBlock = world.getBlock(x, y+1, z);

		if(flag && !flag2) {
			harvest(world, topBlock, x, y, z);
		}
		else
		{
			flag2 = false;
		}
	}

	/**
	 * Harvest the blocks
	 * @param world The world object.
	 * @param block The block to harvest
	 * @param x x coord of the growth block
	 * @param y y coords of the growth block
	 * @param z z coord of the growth block
	 */
	private void harvest(World world, Block block, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y+1, z);
		flag2 = true;

		//check if block is plantable
		if(block instanceof IPlantable)
		{
			if(block == Blocks.nether_wart && meta == 3)
			{
				world.func_147480_a(x, y+1, z, true);
				world.setBlock(x, y+1, z, block, 0, 2);	
			}
			else if(block == Blocks.reeds)
			{
				int numberOfBlocks = 0;

				for(int i=1;i<=growthHeight;i++)
				{
					if(world.getBlock(x, y+i, z) == Blocks.reeds)
					{
						numberOfBlocks++;
					}
					else
					{
						break;
					}
				}

				for(int j=numberOfBlocks;j>1;j--)
				{
					world.func_147480_a(x, y+j, z, true);
				}
			}
			else if(block == Blocks.cactus)
			{
				int numberOfBlocks = 0;

				for(int i=1;i<=growthHeight;i++)
				{
					if(world.getBlock(x, y+i, z) == Blocks.cactus)
					{
						numberOfBlocks++;
					}
					else
					{
						break;
					}
				}

				for(int j=numberOfBlocks;j>1;j--)
				{
					world.func_147480_a(x, y+j, z, true);
				}
			}
			else if(block instanceof BlockCrops && meta == 7)
			{
				world.func_147480_a(x, y+1, z, true);
				world.setBlock(x, y+1, z, block, 0, 2);

			}
		}
	}
}

