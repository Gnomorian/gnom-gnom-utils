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
	private int harvestHieght = GGUConfigManager.growthBlockAutoHarvestHeight;

	public GGUBlockGrowthBlockSemi(Material material) 
	{
		super(material);
		this.setBlockName("GrowthBlockSemi");
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) 
	{
		Block block = world.getBlock(x, y+1, z);		
		int meta = world.getBlockMetadata(x, y, z);

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
					if(i==harvestHieght && (meta == 2 || meta == 7))
					{
						for(int j=0;j<i;j++)
						{
							world.func_147480_a(x, y+j, z, true);
						}
						world.setBlock(x, y+1, z, Blocks.cactus, 2, 2);	
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
}
