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

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import nz.co.crookedhill.ggutils.GGUtils;
import nz.co.crookedhill.ggutils.util.GGUSort;

public class GGUBlockSortivator extends Block
{
    private IIcon[] icons = new IIcon[2];

    protected GGUBlockSortivator(Material material)
    {
	super(material);
	this.setBlockName("sortivator");
	this.setHardness(0.5f);
	this.setStepSound(Block.soundTypeWood);
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister)
    {
	for (int i = 0; i < icons.length; i++)
	{
	    icons[i] = iconRegister.registerIcon(GGUtils.MODID + ":" + "sortivator_texture" + i);
	}
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
	switch (meta)
	{
	case 0:
	    // System.out.println("east side");
	    if (side == 4)
	    {
		return icons[1];
	    } else
		return icons[0];
	case 1:
	    // System.out.println("west side");
	    if (side == 5)
	    { // 4
		return icons[1];
	    } else
		return icons[0];

	case 2:
	    // System.out.println("south side");
	    if (side == 2)
	    {
		return icons[1];
	    } else
		return icons[0];
	case 3:
	    // System.out.println("north side");
	    if (side == 3)
	    {
		return icons[1];
	    } else
		return icons[0];
	case 4:
	    // Facing Up!
	    if (side == 0)
	    {
		return icons[1];
	    } else
		return icons[0];
	case 5:
	    // Facing Down!
	    if (side == 1)
	    {
		return icons[1];
	    } else
		return icons[0];
	}

	return null;

    }

    // function exists for testing
    /*
     * @Override public boolean onBlockActivated(World world, int x, int y, int
     * z, EntityPlayer player, int meta, float float1, float float2, float
     * float3) { if (world.isRemote) { return true; } else { IInventory
     * iinventory = this.getInventory(world, x, y+1, z);
     * 
     * if (iinventory != null) { GGUSort.sort(iinventory);
     * 
     * }
     * 
     * return true; } }
     */
    // gets an inventory object from the coordernates specified and if its a
    // double chest it will get the contents of both.
    public IInventory getInventory(World world, int x, int y, int z)
    {
	Object object = (TileEntityChest) world.getTileEntity(x, y, z);

	if (object == null)
	{
	    return null;
	} else
	{
	    if (world.getBlock(x - 1, y, z) == Blocks.chest)
	    {
		object = new InventoryLargeChest("container.chestDouble", (TileEntityChest) world.getTileEntity(x - 1, y, z), (IInventory) object);
	    }

	    if (world.getBlock(x + 1, y, z) == Blocks.chest)
	    {
		object = new InventoryLargeChest("container.chestDouble", (IInventory) object, (TileEntityChest) world.getTileEntity(x + 1, y, z));
	    }

	    if (world.getBlock(x, y, z - 1) == Blocks.chest)
	    {
		object = new InventoryLargeChest("container.chestDouble", (TileEntityChest) world.getTileEntity(x, y, z - 1), (IInventory) object);
	    }

	    if (world.getBlock(x, y, z + 1) == Blocks.chest)
	    {
		object = new InventoryLargeChest("container.chestDouble", (IInventory) object, (TileEntityChest) world.getTileEntity(x, y, z + 1));
	    }

	    return (IInventory) object;
	}
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
    {
	boolean flag = world.isBlockIndirectlyGettingPowered(x, y, z);
	if (flag)
	{
	    int meta = world.getBlockMetadata(x, y, z);
	    switch (meta)
	    {
	    case 0:
		GGUSort sorter = new GGUSort(getInventory(world, x - 1, y, z));
		break;
	    case 1:
		new GGUSort(getInventory(world, x + 1, y, z));
		break;
	    case 2:
		new GGUSort(getInventory(world, x, y, z - 1));
		break;
	    case 3:
		new GGUSort(getInventory(world, x, y, z + 1));
		break;
	    /*
	     * case 4: if(getInventory(world, x, y-1, z).){
	     * 
	     * } GGUSort.sort(getInventory(world, x, y-1, z)); break;
	     */
	    case 5:
		new GGUSort(getInventory(world, x, y + 1, z));
		break;
	    }
	    world.playSound(x, y, z, "ggutils:block.sortivator.sort", 1f, 1f, false);
	}
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack itemstack)
    {
	int l = MathHelper.floor_double((double) (player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
	int i1 = world.getBlockMetadata(x, y, z) & 4;
	int meta = 0;
	if (l == 0)
	{
	    meta = 2;
	}

	if (l == 1)
	{
	    meta = 1;
	}

	if (l == 2)
	{
	    meta = 3;
	}

	if (l == 3)
	{
	    meta = 0;
	}
	if (player.rotationPitch > 60)
	{
	    meta = 5;
	}
	if (player.rotationPitch < -60)
	{
	    meta = 4;
	}
	world.setBlockMetadataWithNotify(x, y, z, meta | i1, 2);
    }

}
