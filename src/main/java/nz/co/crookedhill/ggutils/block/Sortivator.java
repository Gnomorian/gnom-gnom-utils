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
				//displays the chest above the sortivator to the player who activated the block.
				player.displayGUIChest(iinventory);
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
		int maxInventory = inventory.getSizeInventory();
		int maxSlot = inventory.getInventoryStackLimit();
		ArrayList<ItemStack> items = new ArrayList<ItemStack>();
		//get a copy of all items
		for(int i = 0; i < maxInventory; i++) {
			ItemStack currentSlot = inventory.getStackInSlot(i);
			if(currentSlot != null) {
				//if the array isnt empty
				if(items.size()>0){
					//loop through itemstacks in the array
					boolean isExistant = false;
				loopItems:for(ItemStack inItem : items) {
					//if item is already in the array, change stacksize
					if(inItem.getItem().equals(currentSlot.getItem())) {
						inItem.stackSize += currentSlot.stackSize;
						isExistant = true;
						break loopItems;
						
					}
				}
				if(isExistant == false){
					items.add(currentSlot);
				}
				}else
					//if array is empty
					items.add(currentSlot);
			}
		}
		//set inventory to copy of items
		int currInventory = 0;
		for(int i = 0; i<items.size(); i++) {
			if(items.get(i).stackSize > maxSlot){
				while(items.get(i).stackSize > maxSlot) {
					items.get(i).stackSize -= maxSlot;
					ItemStack newitemstack = items.get(i);
					newitemstack.stackSize = maxSlot;
					inventory.setInventorySlotContents(currInventory, newitemstack);
					currInventory++;
				}
				//i=currInventory;
			}else {
				inventory.setInventorySlotContents(currInventory, items.get(i));
				currInventory++;
			}
		}
		//set rest of inventory to null
		
		for(int i = currInventory; i < maxInventory; i++) {
			inventory.setInventorySlotContents(i, null);
		}
	}

}
