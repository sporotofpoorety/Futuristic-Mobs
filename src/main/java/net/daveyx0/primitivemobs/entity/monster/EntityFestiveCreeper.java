package net.daveyx0.primitivemobs.entity.monster;


import javax.annotation.Nullable;

import net.daveyx0.multimob.entity.IMultiMob;
import net.daveyx0.multimob.entity.ai.EntityAIBackOffFromEntity;
import net.daveyx0.primitivemobs.config.PrimitiveMobsConfigSpecial;
import net.daveyx0.primitivemobs.core.PrimitiveMobsLootTables;
import net.daveyx0.primitivemobs.entity.item.EntityPrimitiveTNTPrimed;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityFestiveCreeper extends EntityPrimitiveCreeper implements IMultiMob {
	
	public EntityFestiveCreeper(World worldIn) {
		super(worldIn);
		isImmuneToFire = true;
	}

    protected void initEntityAI()
    {
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityFestiveCreeper.EntityAIThrowTNT(this));
        this.tasks.addTask(3, new EntityAIBackOffFromEntity(this, PrimitiveMobsConfigSpecial.getFestiveCreeperRetreatDistance(), true));
        this.tasks.addTask(4, new EntityAIAvoidEntity(this, EntityOcelot.class, 6.0F, 1.0D, 1.2D));
        this.tasks.addTask(5, new EntityAIAttackMelee(this, 1.0D, false));
        this.tasks.addTask(6, new EntityAIWander(this, 0.8D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false, new Class[0]));
    }
    
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35D);
    }
    
    public class EntityAIThrowTNT extends EntityAIBase
    {
    	EntityFestiveCreeper creeper;
    	EntityLivingBase target;
    	float power;
    	int attackCooldown;
        int ringAttackCooldown;
        int ringAttackAmount;
    	
    	public EntityAIThrowTNT(EntityFestiveCreeper entityFestiveCreeper) {
    		creeper = entityFestiveCreeper;
    		power = (float) PrimitiveMobsConfigSpecial.getFestiveCreeperPowerBase();
    		attackCooldown = 0;
//Lower initial cooldown for ring attack
            ringAttackCooldown = 100;
            ringAttackAmount = PrimitiveMobsConfigSpecial.getFestiveCreeperRingAttackAmount();
		}

		/**
		* Returns whether the EntityAIBase should begin execution.
		*/
		public boolean shouldExecute()
		{
	        target = this.creeper.getAttackTarget();

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
//Another example of short circuit
//being useful, this time to save some computation.
//Condition for this one is for the festive creeper to have sight and range to the target
	        	if(this.creeper.canEntityBeSeen(target) && 
                (PrimitiveMobsConfigSpecial.getFestiveCreeperRangeIgnore() || 
                (this.creeper.getDistance(target) > 2.0D && this.creeper.getDistanceSq(target) < Math.pow(PrimitiveMobsConfigSpecial.getFestiveCreeperRange(), 2))))
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
	    	attackCooldown = 0;
//Won't instantly do ring attack
            ringAttackCooldown = 100;
	    }
	    
	    /**
	     * Updates the task
	     */
	    public void updateTask()
	    {
	    	if(this.creeper.getPowered())
            { 
                power = (float) PrimitiveMobsConfigSpecial.getFestiveCreeperPowerCharged();
            }
	    	
	    	if(target != null)
	    	{
//If TNT ring is allowed and decremented cooldown is 0
                if((PrimitiveMobsConfigSpecial.getFestiveCreeperRingAttackBase() 
                    || (PrimitiveMobsConfigSpecial.getFestiveCreeperRingAttackCharged() && this.creeper.getPowered())) 
                    && --ringAttackCooldown <= 0) 
                {
                    if(!getEntityWorld().isRemote) 
                    {
//Get hypotenuse to the target
                        double horizontalDistance = Math.sqrt(Math.pow(this.target.posX - creeper.posX, 2) + Math.pow(this.target.posZ - creeper.posZ, 2));
//8 TNTs at the target's horizontal distance for each angle
                        int angles = this.ringAttackAmount;
                        float angleVal = (360 / angles);
                        
                        for(int angleAt = 0; angleAt < angles; angleAt++) 
                        {
	    			        EntityPrimitiveTNTPrimed tnt = new EntityPrimitiveTNTPrimed(this.creeper.getEntityWorld(), creeper.posX, creeper.posY, creeper.posZ, this.creeper, power, 30);
	    			        tnt.setLocationAndAngles(this.creeper.posX, this.creeper.posY, this.creeper.posZ, this.creeper.rotationYaw, 0.0F);
//Use cos and sine to rotate horizontal aim vectors
	    			        tnt.motionX = (horizontalDistance * Math.sin(Math.toRadians(angleVal * angleAt))) / 18D;
	    			        tnt.motionY = (this.target.posY - tnt.posY) / 18D + 0.5D;
	    			        tnt.motionZ = (horizontalDistance * Math.cos(Math.toRadians(angleVal * angleAt))) / 18D;
	    			        this.creeper.getEntityWorld().spawnEntity(tnt);
                        }                           
                    }
	    		    this.creeper.playSound(SoundEvents.ENTITY_TNT_PRIMED, this.creeper.getSoundVolume(), this.creeper.getSoundPitch());
                    ringAttackCooldown = 250;
                } 
//Regular old attack
                if(--attackCooldown <= 0)    
                {                
                    if(!getEntityWorld().isRemote) 
                    {
    			        EntityPrimitiveTNTPrimed tnt = new EntityPrimitiveTNTPrimed(this.creeper.getEntityWorld(), creeper.posX, creeper.posY, creeper.posZ, this.creeper, power, 30);
    			        tnt.setLocationAndAngles(this.creeper.posX, this.creeper.posY, this.creeper.posZ, this.creeper.rotationYaw, 0.0F);
    			        tnt.motionX = (this.target.posX - tnt.posX) / 18D;
    			        tnt.motionY = (this.target.posY - tnt.posY) / 18D + 0.5D;
    			        tnt.motionZ = (this.target.posZ - creeper.posZ) / 18D;
    			        this.creeper.getEntityWorld().spawnEntity(tnt);
    		        }
    		        this.creeper.playSound(SoundEvents.ENTITY_TNT_PRIMED, this.creeper.getSoundVolume(), this.creeper.getSoundPitch());
    		        attackCooldown = 60;
                }
	    	}
	    }
    }
    
    @Nullable
    protected ResourceLocation getLootTable()
    {
        return PrimitiveMobsLootTables.ENTITIES_FESTIVECREEPER;
    }
    
    public boolean isCreatureType(EnumCreatureType type, boolean forSpawnCount)
    {
    	if(type == EnumCreatureType.MONSTER){return false;}
    	return super.isCreatureType(type, forSpawnCount);
    }
}
