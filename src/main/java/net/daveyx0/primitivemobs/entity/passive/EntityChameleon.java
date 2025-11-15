package net.daveyx0.primitivemobs.entity.passive;

import javax.annotation.Nullable;

import net.daveyx0.multimob.common.capabilities.CapabilityTameableEntity;
import net.daveyx0.multimob.common.capabilities.ITameableEntity;
import net.daveyx0.multimob.entity.IMultiMob;
import net.daveyx0.multimob.entity.IMultiMobPassive;
import net.daveyx0.multimob.util.ColorUtil;
import net.daveyx0.multimob.util.EntityUtil;
import net.daveyx0.primitivemobs.config.PrimitiveMobsConfigSpecial;
import net.daveyx0.primitivemobs.core.PrimitiveMobsItems;
import net.daveyx0.primitivemobs.core.PrimitiveMobsLootTables;
import net.daveyx0.primitivemobs.entity.ai.EntityAIBuffOwnerMultimob;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class EntityChameleon extends EntityAnimal implements IMultiMobPassive
{

	private float R;
	private float G;
	private float B;
	private float NewR;
	private float NewG;
	private float NewB;
	private int colorSpeed = 4;
    private int shedCooldown = PrimitiveMobsConfigSpecial.getChameleonShedCooldown() * 20;

	private IBlockState currentState;
	private int currentMultiplier;
	
	public EntityChameleon(World worldIn)
	{
		super(worldIn);
		this.setSize(0.7f, 0.5f);
		this.setSkinRGB(new int[]{0,125,25});
		this.stepHeight = 1.0f;
	}
	
	protected void initEntityAI()
    {
//Made panic lower priority just in case
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIFollowParent(this, 1.1D));
        this.tasks.addTask(3, new EntityAIPanic(this, 1.25D));
        this.tasks.addTask(4, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));


//If buff enabled and buff valid add buff owner task
        if(PrimitiveMobsConfigSpecial.getChameleonBuffEnabled()
        && ForgeRegistries.POTIONS.getValue(new ResourceLocation(PrimitiveMobsConfigSpecial.getChameleonBuffID())) != null) 
        {
//Get Potion object of config buff
            Potion chameleonBuff = ForgeRegistries.POTIONS.getValue(new ResourceLocation(PrimitiveMobsConfigSpecial.getChameleonBuffID()));
//Get buff distance from config
            double chameleonBuffDistance = PrimitiveMobsConfigSpecial.getChameleonBuffDistance();
//Get Multimob tameable mob capability
            ITameableEntity chameleonTameableCapability = this.getTameableCapability();

//Add the arguments for the task
            this.tasks.addTask(2, new EntityAIBuffOwnerMultimob(this, chameleonBuff, 
            PrimitiveMobsConfigSpecial.getChameleonBuffStrength(),
            chameleonBuffDistance, chameleonTameableCapability));
        } 
    }
	
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(PrimitiveMobsConfigSpecial.getChameleonHealth());
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
    }
   
    
    /**
     * Returns the volume for the sounds this mob makes.
     */
    protected float getSoundVolume()
    {
        return 0.4F;
    }

	@Override
	public EntityAgeable createChild(EntityAgeable ageable) 
    {
		return new EntityChameleon(this.getEntityWorld());
	}
	
    @Nullable
    protected ResourceLocation getLootTable()
    {
        return PrimitiveMobsLootTables.ENTITIES_CHAMELEON;
    }
	
	@Override
	public void onUpdate() 
	{
		
		if (this.isInWater() && !collidedHorizontally) {
			motionY = 0.02D;
		}
		
		if(getEntityWorld().isRemote)
		{
			changeColor(this);
		}

//Clever, each update,
//it checks its own color and
//loops until the color timer is reached,
//adjusting the R, G, and B to the assigned new values
		if(R != NewR || G != NewG || B != NewB)
		{
			for(int i = 0; i < colorSpeed; i++)
			{
				if(R > NewR)
				{
					R--;
				}
				else if (R < NewR)
				{
					R++;
				}
			
				if(G > NewG)
				{
					G--;
				}
				else if (G < NewG)
				{
					G++;
				}
			
				if(B > NewB)
				{
					B--;
				}
				else if (B < NewB)
				{
					B++;
				}
			}
		}

        if(shedCooldown <= 0) {
            this.dropItem(PrimitiveMobsItems.CAMOUFLAGE_DYE, 1);            
            shedCooldown = PrimitiveMobsConfigSpecial.getChameleonShedCooldown() * 20;
        }
        
		super.onUpdate();
	}

//Get the multimob tameable mob capability
    public ITameableEntity getTameableCapability()
    {
//Get capability by entity, capability type, and facing direction (nullable)
		ITameableEntity tameable = EntityUtil.getCapability(this, CapabilityTameableEntity.TAMEABLE_ENTITY_CAPABILITY, null);
		return tameable;
    }


    
    public boolean isBreedingItem(ItemStack stack)
    {
        return stack.getItem() == Items.FERMENTED_SPIDER_EYE;
    }
	
    public float[] getSkinRGB()
	{
		return new float[]{R,G,B};
	}
	
	public void setSkinRGB(int[] RGB)
	{
		R = (float)RGB[0];
		G = (float)RGB[1];
		B = (float)RGB[2];
	}
	
	public float[] getNewSkinRGB()
	{
		return new float[]{NewR,NewG,NewB};
	}
	
	public void setNewSkinRGB(int[] RGB)
	{
		NewR = (float)RGB[0];
		NewG = (float)RGB[1];
		NewB = (float)RGB[2];
	}

	public void changeColor(Entity entity)
	{
		int i = MathHelper.floor(entity.posX);
        int j = MathHelper.floor(entity.getEntityBoundingBox().minY);
        int k = MathHelper.floor(entity.posZ);
        
		if(entity.getEntityWorld().getBlockState(new BlockPos(i, j, k)).getBlock() == Blocks.AIR)
		{
			j = MathHelper.floor(entity.getEntityBoundingBox().minY - 0.1);
		}
		
		BlockPos pos = new BlockPos(i, j, k);
		IBlockState state = entity.getEntityWorld().getBlockState(pos);
		
		int colorMultiplier = Minecraft.getMinecraft().getBlockColors().colorMultiplier(state, getEntityWorld(), pos, 0);
		
		//PrimitiveMobsLogger.info(worldObj, state + " " + colorMultiplier);
		if(state.getBlock() != Blocks.AIR && (currentState != state || currentMultiplier != colorMultiplier))
		{
			currentState = state;
			currentMultiplier = colorMultiplier;
			
			int[] newColor = ColorUtil.getBlockStateColor(state, pos, getEntityWorld(), true);
			if(newColor != null)
			{
				if(ColorUtil.isColorInvalid(newColor))
				{
					newColor = new int[]{63,118,42,255};
				}
				setNewSkinRGB(newColor);
			}
		}
	}
	
    public boolean isCreatureType(EnumCreatureType type, boolean forSpawnCount)
    {
    	if(type == EnumCreatureType.CREATURE){return false;}
    	return super.isCreatureType(type, forSpawnCount);
    }

}
