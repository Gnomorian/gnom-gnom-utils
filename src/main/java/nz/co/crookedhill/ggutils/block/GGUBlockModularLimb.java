/* 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. 
 */

package nz.co.crookedhill.ggutils.block;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import nz.co.crookedhill.ggutils.GGUtils;
import nz.co.crookedhill.ggutils.entity.tile.GGUEntityModularLimb;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GGUBlockModularLimb extends Block {

	IIcon icon;
	Random rand = new Random();
	ItemStack tileEntityStack;
	int tileEntityStackSize;

	public GGUBlockModularLimb(Material material) 
	{
		super(material);
		this.setBlockName("modularLimb"); 
		this.setHardness(2.5f);
		this.setStepSound(Block.soundTypeMetal);
		this.tileEntityStack = null;
		this.tileEntityStackSize = 0;
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
	public void onBlockPreDestroy(World world, int x,
			int y, int z, int meta) {
		super.onBlockPreDestroy(world, x, y, z,
				meta);

		GGUEntityModularLimb te = (GGUEntityModularLimb) world.getTileEntity(x, y, z);
		if(te != null)
		{
			if(te.getActualStack() != null)
			{
				this.tileEntityStack = te.getActualStack();
				this.tileEntityStackSize = this.tileEntityStack.stackSize;	
			}
		}
	}

	@Override
	public void onBlockPlacedBy(World world, int x,
			int y, int z, EntityLivingBase entity,
			ItemStack stack) 
	{
		super.onBlockPlacedBy(world, x, y, z,
				entity, stack);

		GGUEntityModularLimb te = (GGUEntityModularLimb) world.getTileEntity(x, y, z);

		if (!stack.hasTagCompound()) {
			stack.setTagCompound(new NBTTagCompound());
		}

		if(stack != null)
		{
			NBTTagList items = stack.getTagCompound().getTagList("Items", Constants.NBT.TAG_COMPOUND);

			NBTTagCompound item = items.getCompoundTagAt(0);
			ItemStack actualStack = ItemStack.loadItemStackFromNBT(item);	
			
			if(stack.getTagCompound().getInteger("stackSize") != 0)
			{
				actualStack.stackSize = stack.getTagCompound().getInteger("stackSize");
			}
			
			te.setActualStack(actualStack);

		}
	} 

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world,
			int x, int y, int z) {
		return new ItemStack(GGUBlocks.modularLimb);
	}
	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z,
			int metadata, int fortune) {


		ArrayList<ItemStack> items = new ArrayList<ItemStack>();
		ItemStack thisItemStack = new ItemStack(GGUBlocks.modularLimb, 1, 0);

		if (!thisItemStack.hasTagCompound()) {
			thisItemStack.setTagCompound(new NBTTagCompound());
		}

		NBTTagList nbtItems = new NBTTagList();
		NBTTagCompound item1 = new NBTTagCompound();

		if(this.tileEntityStack != null)
		{
			nbtItems.appendTag(item1);
			this.tileEntityStack.writeToNBT(item1);
			thisItemStack.getTagCompound().setInteger("stackSize", this.tileEntityStack.stackSize);
			thisItemStack.getTagCompound().setTag("Items", nbtItems);		
		}

		items.add(thisItemStack);

		return items;
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
