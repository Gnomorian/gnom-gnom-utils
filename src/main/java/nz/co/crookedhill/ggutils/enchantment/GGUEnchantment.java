/* 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. 
 */

package nz.co.crookedhill.ggutils.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

public class GGUEnchantment
{

	public static Enchantment prosperousAutoSmelt;
	public static Enchantment icarus;

	public static void init()
	{
		prosperousAutoSmelt = new GGUEnchantmentProsperity(getNextEnchantmentSlot(), 3, EnumEnchantmentType.digger);
		icarus = new GGUEnchantmentIcarus(getNextEnchantmentSlot(), 1, EnumEnchantmentType.armor_torso);
	}

	
	private static int getNextEnchantmentSlot()
	{
		int j = 200;
		
		for(int i=100; i<Enchantment.enchantmentsList.length-100; i++)
		{
			if(Enchantment.enchantmentsList[i] == null)
			{
				return i;
			}
		}
		
		return j;
	}
}
