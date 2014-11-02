package nz.co.crookedhill.ggutils.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import nz.co.crookedhill.ggutils.GGUtils;

public class ItemBlockFinder extends Item {

	public ItemBlockFinder(){
		this.setMaxStackSize(1);
		this.setTextureName(GGUtils.MODID + ":blockFinder_texture");
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
		
		return super.onItemRightClick(itemStack, world, player);
	}
}
