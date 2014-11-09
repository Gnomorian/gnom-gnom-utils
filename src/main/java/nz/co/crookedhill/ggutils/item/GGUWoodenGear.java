package nz.co.crookedhill.ggutils.item;

import net.minecraft.item.Item;
import nz.co.crookedhill.ggutils.GGUtils;

public class GGUWoodenGear extends Item{
	public GGUWoodenGear() {
		this.setMaxStackSize(64);
		this.setTextureName(GGUtils.MODID + ":woodenGear_texture");
	}
}
