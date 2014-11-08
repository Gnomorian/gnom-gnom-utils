package nz.co.crookedhill.ggutils.entity.monster;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.world.World;

public class GGUEntityCreeperMite extends EntitySilverfish
{
	private int allySummonCooldown = 20;

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

	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	@Override
	protected String getLivingSound()
	{
		return "mob.creeper.say";
	}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	@Override
	protected String getHurtSound()
	{
		return "mob.creeper.say";
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	@Override
	protected String getDeathSound()
	{
		return "mob.creeper.kill";
	}
	
	/**
     * Called when the entity is attacked.
     */
	@Override
    public boolean attackEntityFrom(DamageSource p_70097_1_, float p_70097_2_)
    {
        if (this.isEntityInvulnerable())
        {
            return false;
        }
        else
        {
            if (this.allySummonCooldown <= 0 && (p_70097_1_ instanceof EntityDamageSource || p_70097_1_ == DamageSource.magic))
            {
                findFriend();
                this.allySummonCooldown += 20;
            }

            return super.attackEntityFrom(p_70097_1_, p_70097_2_);
        }
    }
	
	@Override
	protected void updateEntityActionState()
	{
		super.updateEntityActionState();
		
		if (!this.worldObj.isRemote)
		{
				if (this.allySummonCooldown > 0)
				{
					--this.allySummonCooldown;
					
					if (this.allySummonCooldown <= 0 && this.entityToAttack != null)
					{
						findFriend();
						this.allySummonCooldown = 20;
					}
				}

			else if (this.entityToAttack != null && !this.hasPath())
			{
				this.entityToAttack = null;
			}
            else
            {
                this.updateWanderPath();
            }
		}
	}

	/**
	 * returns true if the block is a grass plant
	 * @param block
	 * @return
	 */
	public static boolean isBlockBurrowable(Block block)
	{
		return block instanceof BlockTallGrass || block instanceof BlockDoublePlant;
	}
	
	/**
	 * loops through blocks near by, if it finds a compatable block defined by isBlock Burrowable
	 * it will replace it will a creeperMite.
	 */
    protected void findFriend() {
    	findBlock:for(int y = -2; y <= 2; y++) {
			for (int x = -4; x <= 4; x++) {
				for(int z = -4; z <= 4; z++) {
					Block block = this.worldObj.getBlock(((int)this.posX)+x, ((int)this.posY)+y, ((int)this.posZ)+z);
					if(isBlockBurrowable(block)) {
						System.out.println("Found Friend!");
						this.worldObj.setBlock(((int)this.posX)+x, ((int)this.posY)+y, ((int)this.posZ)+z, Blocks.air);
						Entity creeperMite = new GGUEntityCreeperMite(this.worldObj);
						creeperMite.setLocationAndAngles(((float)this.posX)+x, ((float)this.posY)+y, ((float)this.posZ)+z, 0.0F, 0.0F);
						this.worldObj.spawnEntityInWorld(creeperMite);
						break findBlock;
					}
				}
			}
		}
    }

}