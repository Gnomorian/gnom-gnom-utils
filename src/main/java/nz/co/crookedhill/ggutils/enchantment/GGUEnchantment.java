package nz.co.crookedhill.ggutils.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import nz.co.crookedhill.ggutils.helper.GGUConfigManager;

public class GGUEnchantment {
	
	public static Enchantment prosperousAutoSmelt;
	
	public static void init() {
		prosperousAutoSmelt = new GGUprosperousAutoSmelt(GGUConfigManager.autoSmeltid, 3, EnumEnchantmentType.digger);
	}

}
