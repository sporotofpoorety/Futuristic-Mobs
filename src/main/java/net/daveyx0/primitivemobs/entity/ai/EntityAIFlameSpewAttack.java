package net.daveyx0.primitivemobs.entity.ai;

import net.daveyx0.primitivemobs.entity.monster.EntityFlameSpewer;
import net.daveyx0.primitivemobs.entity.item.EntityFlameSpit;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

	public class EntityAIFlameSpewAttack extends EntityAIBase
    {
        private final EntityFlameSpewer spewer;
        private float attackSignal;
//How many attacks so far
        private int attackStep;
        private int attackTime;
        private boolean performingAttack;
        private boolean hasSeenPlayerThisAttack;

        protected double smallFireballAcceleration;

//Exclusive with movement and look tasks
        public EntityAIFlameSpewAttack(EntityFlameSpewer spewerIn, double smallFireballAcceleration)
        {
            this.spewer = spewerIn;
            this.setMutexBits(3);

            this.smallFireballAcceleration = smallFireballAcceleration;
        }

//Executes if it has target
        public boolean shouldExecute()
        {
            EntityLivingBase entitylivingbase = this.spewer.getAttackTarget();
            return entitylivingbase != null && entitylivingbase.isEntityAlive();
        }

        public void startExecuting()
        {
            this.attackSignal = 0;
            this.attackStep = 10;
//Delay until next attack
            this.attackTime = 100;
            performingAttack = false;
            hasSeenPlayerThisAttack = false;
        }
        
        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting()
        {
            return this.shouldExecute();
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void resetTask()
        {
//I'm presuming the creator of the mod put this here twice by mistake, but eh don't fix what isn't broken
            this.spewer.setOnFire(false);
            this.attackSignal = 0;
            this.attackStep = 10;
            this.attackTime = 100;
            spewer.setAttackTime(attackStep);
            spewer.setAttackSignal(attackSignal);
            this.spewer.setInDanger(false);
            performingAttack = false;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void updateTask()
        {
//Decrements attack delay
            --this.attackTime;
            EntityLivingBase entitylivingbase = this.spewer.getAttackTarget();

//If in attack conditions but not attacking yet
            if(!this.performingAttack && this.spewer.isInLava() && this.spewer.canEntityBeSeen(entitylivingbase))
            {
            	if(attackTime <= 50)
            	{
            		this.spewer.setOnFire(false);
            		hasSeenPlayerThisAttack = false;
            	}
            	
                if(!spewer.isOnFire())
                {
                    if(this.spewer.canEntityBeSeen(entitylivingbase))
                    {
                    	hasSeenPlayerThisAttack = true;
                    }
                    
                    attackStep = (attackTime * 2) /10;
                    if(attackTime <= 3)
                    {
                        attackSignal += 0.05f;
                    }
                }
            }

            double d0 = this.spewer.getDistanceSq(entitylivingbase);

//If target is close and spewer is not in lava
            if (d0 < 5.0D && !this.spewer.isInLava())
            {
//In danger
            	this.spewer.setInDanger(true);
//Melee attack every 20 ticks
                if (this.attackTime <= 0)
                {
                    this.attackTime = 20;
                    this.spewer.attackEntityAsMob(entitylivingbase);
                }
            }

//Attack logic is here
            else if (d0 < (this.getFollowDistance() * this.getFollowDistance())  && this.spewer.isInLava())
            {
            	this.spewer.setInDanger(false);
                double d1 = entitylivingbase.posX - this.spewer.posX;
                double d2 = entitylivingbase.getEntityBoundingBox().minY + (double)(entitylivingbase.height / 2.0F + 0.25f) - (this.spewer.posY + (double)(this.spewer.height / 2.0F));
                double d3 = entitylivingbase.posZ - this.spewer.posZ;

//Attack only executes after attackTime reaches 0
                if (this.attackTime <= 0)
                {
                    ++this.attackStep;
                    attackSignal -= 0.05f;
                    
                    if (this.attackStep == 1)
                    {
                        this.attackTime = 30;
                        this.spewer.setOnFire(true);
                        performingAttack = true;
                    }
                    else if (this.attackStep <= 10)
                    {
                        this.attackTime = 3;
                        this.spewer.setOnFire(true);
                        performingAttack = true;
                    }
                    else
                    {
                        this.attackTime = 100;
                        this.attackStep = 10;
                        performingAttack = false;
                    }

                    if (this.attackStep > 1 && hasSeenPlayerThisAttack)
                    {
                        float f = MathHelper.sqrt(MathHelper.sqrt(d0) * 0.1F);
                        this.spewer.world.playEvent((EntityPlayer)null, 1018, new BlockPos((int)this.spewer.posX, (int)this.spewer.posY, (int)this.spewer.posZ), 0);

                        for (int i = 0; i < 1; ++i)
                        {
                            EntityFlameSpit entitysmallfireball = new EntityFlameSpit(this.spewer.world, this.spewer, d1 + this.spewer.getRNG().nextGaussian() * this.smallFireballAcceleration * (double)f, d2 - this.spewer.getRNG().nextGaussian() * this.smallFireballAcceleration * (double)f, d3 + this.spewer.getRNG().nextGaussian() * this.smallFireballAcceleration * (double)f);
                            entitysmallfireball.posY = this.spewer.posY + (double)(this.spewer.height / 2.0F) -0.5F;
                            this.spewer.world.spawnEntity(entitysmallfireball);
                        }
                    }
                }
            }
            else if(this.spewer.isInLava()  && this.spewer.canEntityBeSeen(entitylivingbase))
            {
                double d1 = entitylivingbase.posX - this.spewer.posX;
                double d3 = entitylivingbase.posZ - this.spewer.posZ;
                spewer.motionX = d1 * 0.01;
                spewer.motionZ = d3 * 0.01;
            	this.spewer.setInDanger(false);
                spewer.setOnFire(false);
            }
            else
            {
            	this.spewer.getNavigator().clearPath();
            	this.spewer.setInDanger(false);
                spewer.setOnFire(false);
            }
            
            this.spewer.getLookHelper().setLookPositionWithEntity(entitylivingbase, 10.0F, 10.0F);
            
            if(attackSignal < 0)
            {
            	attackSignal = 0; 
            }
            else if (attackSignal > 0.4)
            {
            	attackSignal = 0.4f;
            }
            
            spewer.setAttackSignal(attackSignal);
            spewer.setAttackTime(attackStep);

            super.updateTask();
        }

        private double getFollowDistance()
        {
            IAttributeInstance iattributeinstance = this.spewer.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE);
            return iattributeinstance == null ? 16.0D : iattributeinstance.getAttributeValue();
        }
    }
