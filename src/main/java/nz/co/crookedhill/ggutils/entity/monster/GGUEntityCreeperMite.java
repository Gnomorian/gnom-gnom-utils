package nz.co.crookedhill.ggutils.entity.monster;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class GGUEntityCreeperMite extends EntitySilverfish
{
	private int allySummonCooldown;

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
		return "mob.creeper.hit";
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	@Override
	protected String getDeathSound()
	{
		return "mob.creeper.kill";
	}
	@Override
	protected void updateEntityActionState()
	{
		super.updateEntityActionState();

		if (!this.worldObj.isRemote)
		{
			int i;
			int j;
			int k;
			int i1;
			this.allySummonCooldown = 1;
				if (this.allySummonCooldown > 0)
				{
					--this.allySummonCooldown;
					if (this.allySummonCooldown == 0 && this.entityToAttack != null)
					{
						findBlock:for(int y = -2; y <= 2; y++) {
							for (int x = -4; x <= 4; x++) {
								for(int z = -4; x <= 4; x++) {
									Block block = this.worldObj.getBlock(((int)this.posX)+x, ((int)this.posY)+y, ((int)this.posZ)+z);
									if(isBlockBurrowable(block)) {
										System.out.println("Block is a leaf");
										this.worldObj.setBlock(((int)this.posX)+x, ((int)this.posY)+y, ((int)this.posZ)+z, Blocks.air);
										Entity creeperMite = new GGUEntityCreeperMite(this.worldObj);
										creeperMite.setLocationAndAngles(((float)this.posX)+x, ((float)this.posY)+y, ((float)this.posZ)+z, 0.0F, 0.0F);
										this.worldObj.spawnEntityInWorld(creeperMite);
										this.allySummonCooldown = 20;
										break findBlock;
									}
								}
							}
						}
						
					}
				}

			else if (this.entityToAttack != null && !this.hasPath())
			{
				this.entityToAttack = null;
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

}