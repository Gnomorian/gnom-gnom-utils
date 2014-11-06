package nz.co.crookedhill.ggutils.block;

import static net.minecraftforge.common.util.ForgeDirection.DOWN;
import ibxm.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import nz.co.crookedhill.ggutils.GGUtils;
import nz.co.crookedhill.ggutils.item.GGUItemStack;
import nz.co.crookedhill.ggutils.network.GGUSortPacket;

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
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float float1, float float2, float float3)
	{
		if (world.isRemote)
		{
			return true;
		}
		else
		{
			IInventory iinventory = this.getInventory(world, x, y+1, z);

			if (iinventory != null)
			{
				sort(iinventory);

			}

			return true;
		}
	}
	//gets an inventory object from the coordernates specified and if its a double chest it will get the contents of both.
	public IInventory getInventory(World world, int x, int y, int z)
	{
		Object object = (TileEntityChest)world.getTileEntity(x, y, z);

		if (object == null)
		{
			return null;
		}
		else if (world.isSideSolid(x, y + 1, z, DOWN))
		{
			return null;
		}
		else if (world.getBlock(x - 1, y, z) == this && (world.isSideSolid(x - 1, y + 1, z, DOWN)))
		{
			return null;
		}
		else if (world.getBlock(x + 1, y, z) == this && (world.isSideSolid(x + 1, y + 1, z, DOWN)))
		{
			return null;
		}
		else if (world.getBlock(x, y, z - 1) == this && (world.isSideSolid(x, y + 1, z - 1, DOWN)))
		{
			return null;
		}
		else if (world.getBlock(x, y, z + 1) == this && (world.isSideSolid(x, y + 1, z + 1, DOWN)))
		{
			return null;
		}
		else
		{
			if (world.getBlock(x - 1, y, z) == this)
			{
				object = new InventoryLargeChest("container.chestDouble", (TileEntityChest)world.getTileEntity(x - 1, y, z), (IInventory)object);
			}

			if (world.getBlock(x + 1, y, z) == this)
			{
				object = new InventoryLargeChest("container.chestDouble", (IInventory)object, (TileEntityChest)world.getTileEntity(x + 1, y, z));
			}

			if (world.getBlock(x, y, z - 1) == this)
			{
				object = new InventoryLargeChest("container.chestDouble", (TileEntityChest)world.getTileEntity(x, y, z - 1), (IInventory)object);
			}

			if (world.getBlock(x, y, z + 1) == this)
			{
				object = new InventoryLargeChest("container.chestDouble", (IInventory)object, (TileEntityChest)world.getTileEntity(x, y, z + 1));
			}

			return (IInventory)object;
		}
	}
	public void sort(IInventory inventory) {
		/*amount of slots in the inventory*/
		int maxInventory = inventory.getSizeInventory();
		
		/*max stack size in one slot in the inventory*/
		int maxSlot = inventory.getInventoryStackLimit();
		
		/*the list of found unique itemstacks in the inventory*/
		ArrayList<GGUItemStack> items = new ArrayList<GGUItemStack>();
		
		
		/*get a copy of all the unique itemstacks within the inventory and quantity of items*/
		for(int i = 0; i < maxInventory; i++) {
			ItemStack currentSlot = inventory.getStackInSlot(i);
			if(currentSlot != null) {
				if(items.size()>0){
					boolean isExistant = false;
				loopItems:for(GGUItemStack inItem : items) {
					//if item is already in the array, change stacksize
					if(inItem.compareTo(currentSlot)) {
						inItem.setQuantity(inItem.getQuantity()+currentSlot.stackSize);
						isExistant = true;
						break loopItems;
						
					}
				}
					/*if the item doesnt exist, add it*/
				if(isExistant == false){
					items.add(new GGUItemStack(currentSlot));
				}
				}else
					/*if the item array is empty, add the itemstack to the array*/
					items.add(new GGUItemStack(currentSlot));
			}
		}
		/*currInventory is the current inventory slot we are looking at.
		 * i is the item in the items list that we are looking at.*/
		int currInventory = 0;
		/*add the items array to the inventory in order so it is sorted*/
		for(int i = 0; i<items.size(); i++) {
			if(items.get(i).getQuantity() > maxSlot){
				while(items.get(i).getQuantity() > maxSlot) {
					items.get(i).setQuantity(items.get(i).getQuantity()-maxSlot);
					ItemStack newitemstack = items.get(i).getItemStack();
					newitemstack.stackSize = maxSlot;
					inventory.setInventorySlotContents(currInventory, newitemstack);
					currInventory++;
				}
				/*when the overflow is done, place the rest of the items*/
				items.get(i).getItemStack().stackSize = items.get(i).getQuantity();
				inventory.setInventorySlotContents(currInventory, items.get(i).getItemStack());
				currInventory++;
				//i=currInventory;
			}else {
				ItemStack setItem = items.get(i).getItemStack();
				setItem.stackSize = items.get(i).getQuantity();
				inventory.setInventorySlotContents(currInventory, setItem);
				currInventory++;
			}
		}
		/*when all items from the list have been added, set the 
		 * remaining inventory slots to null to delete duplicates*/
		
		for(int i = currInventory; i < maxInventory; i++) {
			inventory.setInventorySlotContents(i, null);
		}
	}

}
