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
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import nz.co.crookedhill.ggutils.GGUtils;
import nz.co.crookedhill.ggutils.entity.item.GGUEntityEggTimer;

public class GGUBlockEggTimer extends Block 
{

	private IIcon[] icons = new IIcon[2];

	protected GGUBlockEggTimer(Material material) 
	{
		super(material);
		this.setBlockName("eggTimer");
		this.setHardness(2.5f);
		this.setStepSound(Block.soundTypeAnvil);
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister) 
	{
		for(int i = 0; i < icons.length; i++) 
		{
			icons[i] = iconRegister.registerIcon(GGUtils.MODID + ":" + "sortivator_texture"+ i);
		}
	}

	@Override
	public IIcon getIcon(int side, int meta) 
	{
		if(side == 1) 
		{
			return icons[1];
		}else return icons[0];

	}

	@Override
	public boolean hasTileEntity(int metadata) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata) 
	{	
		super.createTileEntity(world, metadata);
		return new GGUEntityEggTimer();
	}

	@Override
	public boolean onBlockActivated(World world, int x,
			int y, int z, EntityPlayer player,
			int par6, float f, float f2,
			float f3) {

		GGUEntityEggTimer entity = (GGUEntityEggTimer) world.getTileEntity(x, y, z);

		if(!world.isRemote && entity != null)
		{	
			int strength = world.getBlockPowerInput(x, y, z);
			switch(strength)
			{
			case 0:
				entity.setActive(false);
				entity.setDelay(0);
				break;
			default:
				entity.setDelay(strength*10);				
				entity.setActive(true);
			}
		}
		return true;
	}
}
