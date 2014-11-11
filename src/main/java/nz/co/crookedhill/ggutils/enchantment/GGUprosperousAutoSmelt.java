package nz.co.crookedhill.ggutils.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

public class GGUprosperousAutoSmelt extends Enchantment{

	protected GGUprosperousAutoSmelt(int effectID, int weight, EnumEnchantmentType enchantmentType) {
		super(effectID, weight, EnumEnchantmentType.digger);
		this.setName("ProsperousAutoSmelt");
		
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
        return 3;
    }
	
	/**
     * Determines if the enchantment passed can be applyied together with this enchantment.
     */
	@Override
    public boolean canApplyTogether(Enchantment enchantment)
    {
		return super.canApplyTogether(enchantment) && enchantment.effectId != fortune.effectId;
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
    public int getMinEnchantability(int level)
    {
        return 10 + 20 * (level - 1);
    }
    
    /**
     * Returns the maximum value of enchantability nedded on the enchantment level passed.
     */
    public int getMaxEnchantability(int level)
    {
        return super.getMinEnchantability(level) + 50;
    }

}
