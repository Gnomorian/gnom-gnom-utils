/* 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. 
 */

package nz.co.crookedhill.ggutils.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import nz.co.crookedhill.ggutils.helper.GGUConfigManager;

public class GGUEnchantment
{

	public static Enchantment prosperousAutoSmelt;
	public static Enchantment icarus;

	public static void init()
	{
		prosperousAutoSmelt = new GGUEnchantmentProsperity(GGUConfigManager.autoSmeltid, 3, EnumEnchantmentType.digger);
		icarus = new GGUEnchantmentIcarus(GGUConfigManager.icarusID, 1, EnumEnchantmentType.armor_torso);
	}

}
