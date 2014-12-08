/* 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. 
 */

package nz.co.crookedhill.ggutils.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

public class GGUEnchantmentIcarus extends Enchantment
{

	protected GGUEnchantmentIcarus(int effectID, int weight, EnumEnchantmentType enchantmentType) 
	{
		super(effectID, weight, enchantmentType);
		this.setName("Icarus");	
	}
	
	/**
     * Returns the minimum level that the enchantment can have.
     */
	@Override
    public int getMinLevel()
    {
        return 1;
    }

    /**
     * Returns the maximum level that the enchantment can have.
     */
	@Override
    public int getMaxLevel()
    {
        return 2;
    }
    
    /**
     * Return the name of key in translation table of this enchantment.
     */
    @Override
    public String getName()
    {
        return "enchantment." + this.name;
    }
    
    /**
     * Returns the minimal value of enchantability needed on the enchantment level passed.
     */
    @Override
    public int getMinEnchantability(int level)
    {
        return 20 + level * 10;
    }
    
    /**
     * Returns the maximum value of enchantability nedded on the enchantment level passed.
     */
    @Override
    public int getMaxEnchantability(int level)
    {
        return this.getMinEnchantability(level)+50;
    }

}
