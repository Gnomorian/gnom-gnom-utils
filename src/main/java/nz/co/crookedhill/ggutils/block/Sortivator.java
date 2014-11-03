package nz.co.crookedhill.ggutils.block;

import static net.minecraftforge.common.util.ForgeDirection.DOWN;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import nz.co.crookedhill.ggutils.GGUtils;

public class Sortivator extends Block {

	private IIcon[] icons = new IIcon[2];

	protected Sortivator(Material material) {
		super(material);
		this.setBlockName("sortivator");
		this.setCreativeTab(CreativeTabs.tabRedstone);
		this.setHardness(0.5f);
		this.setStepSound(Block.soundTypeWood);
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		for(int i = 0; i < icons.length; i++) {
			icons[i] = iconRegister.registerIcon(GGUtils.MODID + ":" + "sortivator_texture"+ i);
		}
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		if(side == 1) {
			return icons[1];
		}else return icons[0];

	}

	@Override
	public boolean onBlockActivated(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
	{
		if (p_149727_1_.isRemote)
		{
			return true;
		}
		else
		{
			IInventory iinventory = this.func_149951_m/*the method below*/(p_149727_1_, p_149727_2_, p_149727_3_+1, p_149727_4_);

			if (iinventory != null)
			{
				//displays the chest above the sortivator to the player who activated the block.
				//p_149727_5_.displayGUIChest(iinventory);
				sort(iinventory);

			}

			return true;
		}
	}
	//gets an inventory object from the coordernates specified and if its a double chest it will get the contents of both.
	public IInventory func_149951_m(World p_149951_1_, int p_149951_2_, int p_149951_3_, int p_149951_4_)
	{
		Object object = (TileEntityChest)p_149951_1_.getTileEntity(p_149951_2_, p_149951_3_, p_149951_4_);

		if (object == null)
		{
			return null;
		}
		else if (p_149951_1_.isSideSolid(p_149951_2_, p_149951_3_ + 1, p_149951_4_, DOWN))
		{
			return null;
		}
		else if (func_149953_o(p_149951_1_, p_149951_2_, p_149951_3_, p_149951_4_))
		{
			return null;
		}
		else if (p_149951_1_.getBlock(p_149951_2_ - 1, p_149951_3_, p_149951_4_) == this && (p_149951_1_.isSideSolid(p_149951_2_ - 1, p_149951_3_ + 1, p_149951_4_, DOWN) || func_149953_o(p_149951_1_, p_149951_2_ - 1, p_149951_3_, p_149951_4_)))
		{
			return null;
		}
		else if (p_149951_1_.getBlock(p_149951_2_ + 1, p_149951_3_, p_149951_4_) == this && (p_149951_1_.isSideSolid(p_149951_2_ + 1, p_149951_3_ + 1, p_149951_4_, DOWN) || func_149953_o(p_149951_1_, p_149951_2_ + 1, p_149951_3_, p_149951_4_)))
		{
			return null;
		}
		else if (p_149951_1_.getBlock(p_149951_2_, p_149951_3_, p_149951_4_ - 1) == this && (p_149951_1_.isSideSolid(p_149951_2_, p_149951_3_ + 1, p_149951_4_ - 1, DOWN) || func_149953_o(p_149951_1_, p_149951_2_, p_149951_3_, p_149951_4_ - 1)))
		{
			return null;
		}
		else if (p_149951_1_.getBlock(p_149951_2_, p_149951_3_, p_149951_4_ + 1) == this && (p_149951_1_.isSideSolid(p_149951_2_, p_149951_3_ + 1, p_149951_4_ + 1, DOWN) || func_149953_o(p_149951_1_, p_149951_2_, p_149951_3_, p_149951_4_ + 1)))
		{
			return null;
		}
		else
		{
			if (p_149951_1_.getBlock(p_149951_2_ - 1, p_149951_3_, p_149951_4_) == this)
			{
				object = new InventoryLargeChest("container.chestDouble", (TileEntityChest)p_149951_1_.getTileEntity(p_149951_2_ - 1, p_149951_3_, p_149951_4_), (IInventory)object);
			}

			if (p_149951_1_.getBlock(p_149951_2_ + 1, p_149951_3_, p_149951_4_) == this)
			{
				object = new InventoryLargeChest("container.chestDouble", (IInventory)object, (TileEntityChest)p_149951_1_.getTileEntity(p_149951_2_ + 1, p_149951_3_, p_149951_4_));
			}

			if (p_149951_1_.getBlock(p_149951_2_, p_149951_3_, p_149951_4_ - 1) == this)
			{
				object = new InventoryLargeChest("container.chestDouble", (TileEntityChest)p_149951_1_.getTileEntity(p_149951_2_, p_149951_3_, p_149951_4_ - 1), (IInventory)object);
			}

			if (p_149951_1_.getBlock(p_149951_2_, p_149951_3_, p_149951_4_ + 1) == this)
			{
				object = new InventoryLargeChest("container.chestDouble", (IInventory)object, (TileEntityChest)p_149951_1_.getTileEntity(p_149951_2_, p_149951_3_, p_149951_4_ + 1));
			}

			return (IInventory)object;
		}
	}
	//i think this one lock the chest if an ocelot is on it, but i added it becuase it was required in the function above.
	//a ruddy good cleanup will happen when i have a good idea of what all this mess means, and whats relervent.
	private static boolean func_149953_o(World p_149953_0_, int p_149953_1_, int p_149953_2_, int p_149953_3_)
	{
		Iterator iterator = p_149953_0_.getEntitiesWithinAABB(EntityOcelot.class, AxisAlignedBB.getBoundingBox((double)p_149953_1_, (double)(p_149953_2_ + 1), (double)p_149953_3_, (double)(p_149953_1_ + 1), (double)(p_149953_2_ + 2), (double)(p_149953_3_ + 1))).iterator();
		EntityOcelot entityocelot;

		do
		{
			if (!iterator.hasNext())
			{
				return false;
			}

			Entity entity = (Entity)iterator.next();
			entityocelot = (EntityOcelot)entity;
		}
		while (!entityocelot.isSitting());

		return true;
	}
	// sorts the inventory passed to the function
	public static void sort(IInventory inventory) {
		int maxInventoty = inventory.getSizeInventory();
		int maxSlot = inventory.getInventoryStackLimit();
		HashMap<Item, ItemStack> items = new HashMap<Item,ItemStack>();
		for(int i = 0; i < maxInventoty; i++) {
			ItemStack currentSlot = inventory.getStackInSlot(i);
			if(currentSlot != null) {
				if(!items.containsKey(currentSlot.getItem())) {
					items.put(currentSlot.getItem(), currentSlot);
				}else {
					items.replace(currentSlot.getItem(),currentSlot);
				}
			}
		}
		int invSlot = 0;
		for(Entry<Item,ItemStack> i : items.entrySet()) {
			while(invSlot < maxInventoty) {
				ItemStack item = i.getValue();
				if(item.stackSize > maxSlot) {
					while(item.stackSize > maxSlot){
						ItemStack setItem = item;
						setItem.stackSize = maxSlot;
						item.stackSize-= maxSlot;
						inventory.setInventorySlotContents(invSlot, item);
						invSlot++;
					}
					inventory.setInventorySlotContents(invSlot, item); //places the remander of invent


				}else{
					inventory.setInventorySlotContents(invSlot, item);
					invSlot++;
				}


			}
		}
		while(invSlot < maxInventoty) {
			inventory.setInventorySlotContents(invSlot, null);
			invSlot++;
		}
	}

}
