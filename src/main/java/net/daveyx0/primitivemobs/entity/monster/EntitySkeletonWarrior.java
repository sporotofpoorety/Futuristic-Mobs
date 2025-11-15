package net.daveyx0.primitivemobs.entity.monster;

import javax.annotation.Nullable;

import net.daveyx0.primitivemobs.config.PrimitiveMobsConfigSpecial;
import net.daveyx0.primitivemobs.entity.ai.EntityAISwitchBetweenRangedAndMelee;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIRestrictSun;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntitySpectralArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class EntitySkeletonWarrior extends EntitySkeleton {

    protected String skeletonWarriorEffectName;
    protected Potion skeletonWarriorEffect;
    protected int skeletonWarriorEffectDuration;
    protected int skeletonWarriorEffectAmplifier;
    protected double skeletonWarriorStrafeThreshold;
    protected double skeletonWarriorSwitchToMelee;
    protected double skeletonWarriorSwitchToRanged;
    protected int skeletonWarriorShootDelayTime;
    protected int skeletonWarriorShootDrawTime;
    protected double skeletonWarriorShootVelocity;
    protected double skeletonWarriorShootInaccuracyFactor;
    
	public EntitySkeletonWarrior(World worldIn) {
		super(worldIn);
        skeletonWarriorEffectName = PrimitiveMobsConfigSpecial.getSkeletonWarriorEffect();
        skeletonWarriorEffect = ForgeRegistries.POTIONS.getValue(new ResourceLocation(skeletonWarriorEffectName));
        skeletonWarriorEffectDuration = PrimitiveMobsConfigSpecial.getSkeletonWarriorEffectDuration();
        skeletonWarriorEffectAmplifier = PrimitiveMobsConfigSpecial.getSkeletonWarriorEffectAmplifier();
        skeletonWarriorStrafeThreshold = PrimitiveMobsConfigSpecial.getSkeletonWarriorStrafeThreshold(); 
        skeletonWarriorSwitchToMelee = PrimitiveMobsConfigSpecial.getSkeletonWarriorSwitchToMelee();
        skeletonWarriorSwitchToRanged = PrimitiveMobsConfigSpecial.getSkeletonWarriorSwitchToRanged();
        skeletonWarriorShootDelayTime = PrimitiveMobsConfigSpecial.getSkeletonWarriorShootDelayTime();
        skeletonWarriorShootDrawTime = PrimitiveMobsConfigSpecial.getSkeletonWarriorShootDrawTime();
        skeletonWarriorShootVelocity = PrimitiveMobsConfigSpecial.getSkeletonWarriorShootVelocity();
        skeletonWarriorShootInaccuracyFactor = PrimitiveMobsConfigSpecial.getSkeletonWarriorShootInaccuracyFactor();
	}
	
    protected void initEntityAI()
    {
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIRestrictSun(this));
        this.tasks.addTask(3, new EntityAIFleeSun(this, 1.0D));
        this.tasks.addTask(3, new EntityAIAvoidEntity(this, EntityWolf.class, 6.0F, 1.0D, 1.2D));
        //this.tasks.addTask(6, new EntityAIBackOffFromEnemy(this, 5D, false));
        this.tasks.addTask(6, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityIronGolem.class, true));
    }
    
    
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(25.0D);
    }
    
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).applyModifier(new AttributeModifier("Random spawn bonus", this.rand.nextGaussian() * 0.05D, 1));

        if (this.rand.nextFloat() < 0.05F)
        {
            this.setLeftHanded(true);
        }
        else
        {
            this.setLeftHanded(false);
        }

        this.setCanPickUpLoot(this.rand.nextFloat() < 0.55F * difficulty.getClampedAdditionalDifficulty());

        if (this.getItemStackFromSlot(EntityEquipmentSlot.HEAD).isEmpty())
        {
            this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(Items.LEATHER_HELMET));
        }
        
        if (this.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND).isEmpty())
        {
            this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.BOW));
        }

        this.tasks.addTask(4, new EntityAISwitchBetweenRangedAndMelee(this, 1.35D, this.skeletonWarriorShootDelayTime, (float) this.skeletonWarriorStrafeThreshold, this.skeletonWarriorShootDrawTime));
        this.tasks.addTask(5, new EntitySkeletonWarrior.EntityAISwitchWeapons(this, this.skeletonWarriorSwitchToMelee, this.skeletonWarriorSwitchToRanged, new ItemStack(Items.IRON_SWORD), new ItemStack(Items.BOW)));
        
        return livingdata;
    }
    
    public ItemStack getBackItem()
    {
    	if(this.getHeldItemMainhand().getItem() == Items.IRON_SWORD)
    	{
    		return new ItemStack(Items.BOW);
    	}
    	else
    	{
    		return new ItemStack(Items.IRON_SWORD);
    	}
    }
    
    protected EntityArrow getArrow(float p_190726_1_)
    {
        EntityArrow entityarrow = super.getArrow(p_190726_1_);

        if (!this.skeletonWarriorEffectName.isEmpty())
        {
            if(entityarrow instanceof EntityTippedArrow)
            {
                ( (EntityTippedArrow) entityarrow ).addEffect(new PotionEffect(skeletonWarriorEffect, skeletonWarriorEffectDuration, skeletonWarriorEffectAmplifier));
            }
        }

        return entityarrow;
    }
    
    public class EntityAISwitchWeapons extends EntityAIBase
    {
    	EntitySkeleton mob;
    	EntityLivingBase target;
    	double minDistance;
    	double maxDistance;
    	ItemStack weaponOne;
    	ItemStack weaponTwo;
    	
    	public EntityAISwitchWeapons(EntitySkeleton entitymob, double minDistance, double maxDistance, ItemStack weaponOne, ItemStack weaponTwo) {
    		mob = entitymob;
    		this.minDistance = minDistance;
    		this.maxDistance = maxDistance;
    		this.weaponOne = weaponOne;
    		this.weaponTwo = weaponTwo;
		}

		/**
		* Returns whether the EntityAIBase should begin execution.
		*/
		public boolean shouldExecute()
		{
	        this.target = this.mob.getAttackTarget();

	        if (target == null)
	        {
	            return false;
	        }
	        else if (!target.isEntityAlive())
	        {
	            return false;
	        }
	        else
	        {
	        	if(((this.mob.getDistance(this.target) < minDistance && this.mob.getHeldItemMainhand() != weaponOne) || 
	        			(this.mob.getDistance(this.target) > maxDistance && this.mob.getHeldItemMainhand() != weaponTwo)) && this.mob.canEntityBeSeen(this.target))
	        	{
	        		return true;
	        	}
	        	
	        	return false;
	        }
		}
		
		/**
	    * Returns whether an in-progress EntityAIBase should continue executing
		*/
		public boolean continueExecuting()
	    {
			return shouldExecute();
	    }

	    /**
	     * Resets the task
	     */
	    public void resetTask()
	    {
	    	target = null;
	    }
	    
	    /**
	     * Updates the task
	     */
	    public void updateTask()
	    {
	    	if(this.mob.getDistance(this.target) < minDistance)
	    	{
	    		this.mob.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, weaponOne);
	    	}
	    	else if(this.mob.getDistance(this.target) > maxDistance)
	    	{
	    		this.mob.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, weaponTwo);
	    	}
	    }
    }
    
    /**
     * sets this entity's combat AI.
     */
    @Override
    public void setCombatTask()
    {
        
    }

    
    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor)
    {
        EntityArrow entityarrow = this.getArrow(distanceFactor);
        double d0 = target.posX - this.posX;
        double d1 = target.getEntityBoundingBox().minY + (double)(target.height / 3.0F) - entityarrow.posY;
        double d2 = target.posZ - this.posZ;
        double d3 = (double) MathHelper.sqrt(d0 * d0 + d2 * d2);
//Trying to adjust arrow shoot height to speed
        entityarrow.shoot(d0, d1 + d3 * 0.20000000298023224D * (skeletonWarriorShootInaccuracyFactor / 1.6F), d2, 
                            (float) this.skeletonWarriorShootVelocity, 
        (float) this.skeletonWarriorShootInaccuracyFactor * (float) (14 - this.world.getDifficulty().getDifficultyId() * 4));
        this.playSound(SoundEvents.ENTITY_SKELETON_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.world.spawnEntity(entityarrow);
    }
}
