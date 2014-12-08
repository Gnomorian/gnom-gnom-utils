/* 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. 
 */

package nz.co.crookedhill.ggutils.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import nz.co.crookedhill.ggutils.GGUtils;
import nz.co.crookedhill.ggutils.entity.tile.GGUEntityEggTimer;

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
