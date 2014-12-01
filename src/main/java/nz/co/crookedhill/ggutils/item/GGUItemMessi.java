package nz.co.crookedhill.ggutils.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import nz.co.crookedhill.ggutils.GGUtils;

public class GGUItemMessi extends Item
{
	public GGUItemMessi()
	{
		this.setTextureName(GGUtils.MODID + ":" + "messi_texture");
		this.setMaxStackSize(1);
	}

	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if (!world.isRemote) 
		{
			if (!player.isSneaking()) 
			{
				player.openGui(GGUtils.instance, GGUtils.GUI_MESS_INV, player.worldObj, (int) player.posX, (int) player.posY, (int) player.posZ);
			}
		}
		return stack;
	}


	// Without this method, your inventory will NOT work!!!
	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 1; // return any value greater than zero
	}
}
