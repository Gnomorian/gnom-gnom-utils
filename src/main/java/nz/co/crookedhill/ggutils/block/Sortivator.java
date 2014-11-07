package nz.co.crookedhill.ggutils.block;

import static net.minecraftforge.common.util.ForgeDirection.DOWN;

<<<<<<< HEAD
=======
import java.util.ArrayList;

>>>>>>> origin/master
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import nz.co.crookedhill.ggutils.GGUtils;
import nz.co.crookedhill.ggutils.util.GGUSort;

public class Sortivator extends Block {

	private IIcon[] icons = new IIcon[2];

	protected Sortivator(Material material) {
		super(material);
		this.setBlockName("sortivator");
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
				GGUSort.sort(iinventory);

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
	

}
