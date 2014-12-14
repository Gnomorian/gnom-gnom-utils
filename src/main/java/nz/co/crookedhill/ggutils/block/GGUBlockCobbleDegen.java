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
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import nz.co.crookedhill.ggutils.GGUtils;

public class GGUBlockCobbleDegen extends Block
{
	private IIcon icon;

	public GGUBlockCobbleDegen(Material material)
	{
		super(material);
		this.setBlockName("cobbleDegen");
		this.setHardness(0.5f);
		this.setStepSound(Block.soundTypeStone);
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister) 
	{
		icon = iconRegister.registerIcon(GGUtils.MODID + ":" + "cobbleDegen_texture");
	}

	@Override
	public IIcon getIcon(int side, int meta) 
	{
		return icon;

	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float float1, float float2, float float3)
	{
		Random rand = new Random();

		if(world.isBlockIndirectlyGettingPowered(x, y, z))
		{
			ItemStack[] inventory = player.inventory.mainInventory;
			String cobbleStone = new ItemStack(Blocks.cobblestone, 1, 0).getDisplayName();
			boolean hasCobble = false;

			for(int i=0; i < inventory.length; i++)
			{
				if(inventory[i] != null)
				{
					String inventoryDisplayName = inventory[i].getDisplayName();

					if(inventoryDisplayName.equals(cobbleStone))
					{
						hasCobble = true;
						inventory[i] = null;
					}
				}
				else
				{
					inventory[i] = null;
				}
			}
			player.inventory.mainInventory = inventory;
			if(hasCobble)
			{
				for(int i=0;i<10;i++)
				{
					world.spawnParticle("angryVillager", x+rand.nextFloat(), y+1, z+rand.nextFloat(), 0, 1, 0);			
				}
			}
		}

		//coz it ain't free!
		if(rand.nextInt(20) == 0)
		{
			player.setFire(5);
		}
		return true;
	}

}
