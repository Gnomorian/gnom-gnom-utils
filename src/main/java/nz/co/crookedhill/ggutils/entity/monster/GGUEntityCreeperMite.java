package nz.co.crookedhill.ggutils.entity.monster;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.world.World;

public class GGUEntityCreeperMite extends EntitySilverfish
{
    public GGUEntityCreeperMite(World world)
    {
        super(world);
        this.setSize(0.3F, 0.7F);
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.6000000238418579D);
    }
    
    /**
     * Basic mob attack. Default to touch of death in EntityCreature. Overridden by each mob to define their attack.
     */
    protected void attackEntity(Entity entity, float f)
    {
    	World world = entity.worldObj;
    	
        if (this.attackTime <= 0 && f < 1.2F && entity.boundingBox.maxY > this.boundingBox.minY && entity.boundingBox.minY < this.boundingBox.maxY)
        {
			world.createExplosion(this, entity.posX, entity.posY, entity.posZ, 0.1F, false);
			this.setDead();
        }
    }
    
}