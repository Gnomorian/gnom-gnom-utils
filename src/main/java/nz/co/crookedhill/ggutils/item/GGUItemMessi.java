package nz.co.crookedhill.ggutils.item;

import net.minecraft.item.Item;
import nz.co.crookedhill.ggutils.GGUtils;

public class GGUItemMessi extends Item
{
	public GGUItemMessi()
	{
		this.setTextureName(GGUtils.MODID + ":" + "messi_texture");
		this.setMaxStackSize(1);
	}
}
