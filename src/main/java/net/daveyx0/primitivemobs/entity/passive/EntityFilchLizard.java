package net.daveyx0.primitivemobs.entity.passive;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.Nullable;

import com.google.common.collect.Sets;

import net.daveyx0.multimob.entity.IMultiMobPassive;
import net.daveyx0.multimob.entity.ai.EntityAIGrabItemFromFloor;
import net.daveyx0.multimob.entity.ai.EntityAIStealFromPlayer;
import net.daveyx0.multimob.util.EntityUtil;
import net.daveyx0.primitivemobs.config.PrimitiveMobsConfigSpecial;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class EntityFilchLizard extends EntityCreature implements IMultiMobPassive {

	private int itemChance = 4;
	private EntityAIAvoidEntity<EntityPlayer> avoidEntity;
	
    public ArrayList<String> lootStealItems = new ArrayList<>();
    public ArrayList<ItemStack> lootStealItemStacks = new ArrayList<>();

    public ArrayList<String> lootSpawnItems = new ArrayList<>();
    public ArrayList<Integer> lootSpawnItemWeights = new ArrayList<>();
    public ArrayList<ItemStack> lootSpawnItemStacks = new ArrayList<>();
	
	public EntityFilchLizard(World worldIn) {
		super(worldIn);
		this.inventoryHandsDropChances[0] = 0f;
		this.inventoryHandsDropChances[1] = 0f;
		this.setSize(0.6f, 0.5f);
	}
	
    protected void initEntityAI()
    {
    	int prio = 0;

        this.tasks.addTask(prio++, new EntityAISwimming(this));
        this.tasks.addTask(prio++, new EntityAIPanic(this, 1.25D));
        this.tasks.addTask(prio++, new EntityFilchLizard.AIAvoidWhenNasty(this, EntityPlayer.class, 16.0F, 1.0D, 1.33D));
        this.tasks.addTask(prio++, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(prio++, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(prio++, new EntityAILookIdle(this));
    }
	
    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        if(!this.getHeldItemMainhand().isEmpty())
        {
        	this.setSize(0.6f, 0.75f);
        }
        else
        {
        	this.setSize(0.6f, 0.3f);
        }
        	
    }
    
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25000000417232513D);
    }
   
    protected void updateAITasks()
    {
        super.updateAITasks();
    }

	
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
    	int chance = PrimitiveMobsConfigSpecial.getFilchLizardLootChance();

//Only set the tasks if it passes chance
    	if(chance > 0 && (chance >= 100 || (rand.nextInt(100/chance) == 0)))
    	{
//Item steal Ids converted from array to list
    	    lootStealItems = new ArrayList<>(Arrays.asList(PrimitiveMobsConfigSpecial.getFilchStealLoot()));
//Turn the list into an ItemStack list to provide to the tasks
            lootStealItemStacks = itemIdsToItemStacks(lootStealItems);    

//Do the same for the spawn items and weights
        	lootSpawnItems = new ArrayList<>(Arrays.asList(PrimitiveMobsConfigSpecial.getFilchSpawnLoot()));
            lootSpawnItemStacks = itemIdsToItemStacks(lootSpawnItems);
//My first time using streams, still need to understand them better  
            lootSpawnItemWeights = new ArrayList<>(Arrays.stream(PrimitiveMobsConfigSpecial.getFilchSpawnLootWeights()).boxed().collect(Collectors.toList()));

            this.tasks.addTask(2, new EntityAIGrabItemFromFloor(this, 1.2D, Sets.newHashSet(lootStealItemStacks), true));
            this.tasks.addTask(3, new EntityAIStealFromPlayer(this, 0.8D, Sets.newHashSet(lootStealItemStacks), true));

    		while(this.getHeldItemMainhand().isEmpty() && !getEntityWorld().isRemote)
    		{
    			this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, getRandomWeightedItemInstance(this.lootSpawnItemStacks, this.lootSpawnItemWeights));
    		}
    	}
    		
        return super.onInitialSpawn(difficulty, livingdata);
    }

    public EntityItem dropItemStack(ItemStack itemIn, float offsetY)
    {
        return this.entityDropItem(itemIn, offsetY);
    }
    
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
	    if (par1DamageSource.getTrueSource() != null)
	    {
	    	this.setLastAttackedEntity(par1DamageSource.getTrueSource());
	    }
	    
        ItemStack stack = this.getHeldItemMainhand();

        if (!stack.isEmpty() && !getEntityWorld().isRemote)
        {
            ItemStack newStack = stack.copy();
            this.dropItemStack(newStack, 1);
            this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, ItemStack.EMPTY);
        }

	    return super.attackEntityFrom(par1DamageSource, par2);
	}
    
    static class AIAvoidWhenNasty extends EntityAIAvoidEntity
    {
        public AIAvoidWhenNasty(EntityCreature theEntityIn, Class classToAvoidIn, float avoidDistanceIn, double farSpeedIn,
				double nearSpeedIn) {
			super(theEntityIn, classToAvoidIn, avoidDistanceIn, farSpeedIn, nearSpeedIn);
		}

		/**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute()
        {
        	return !entity.getHeldItemMainhand().isEmpty() && super.shouldExecute();
        }	
    }
    
    protected Block spawnableBlock = Blocks.SAND;
    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    public boolean getCanSpawnHere()
    {
        int i = MathHelper.floor(this.posX);
        int j = MathHelper.floor(this.getEntityBoundingBox().minY);
        int k = MathHelper.floor(this.posZ);
        BlockPos blockpos = new BlockPos(i, j, k);
        return this.getEntityWorld().getBlockState(blockpos.down()).getBlock() == this.spawnableBlock && this.getEntityWorld().getLight(blockpos) > 8 && super.getCanSpawnHere();
    }
    
    public boolean isCreatureType(EnumCreatureType type, boolean forSpawnCount)
    {
    	return super.isCreatureType(type, forSpawnCount);
    }

//Converts a list of item Ids to a set of ItemStacks
    public static ArrayList<ItemStack> itemIdsToItemStacks(ArrayList<String> itemIds)
    {
//ItemStack list to add stuff to
        ArrayList<ItemStack> itemStacks = new ArrayList<>();

//If item Ids not empty
        if(!itemIds.isEmpty())
        {
//For each id
            for(String id : itemIds)
            {
//Fetch Item object from Id then fetch default ItemStack
                itemStacks.add(ForgeRegistries.ITEMS.getValue(new ResourceLocation(id)).getDefaultInstance());                
            }
            return itemStacks;
        } 
        else 
        {
            return null;
        }
    }

//To generalize for later
//Should take a list of ItemStacks and a list of weights then return a random one    
    public static ItemStack getRandomWeightedItemInstance(ArrayList<ItemStack> items, ArrayList<Integer> weights)
    {
//Ranges for picking weighted items 
        ArrayList<Integer> probabilityStops = new ArrayList<>();
//Total current weight value
        int probabilityStop = 0;

//For each weight make a list of probability stops
        for(int weight : weights)
        {
            probabilityStop += weight;
            probabilityStops.add(probabilityStop);
        }

//Pick a random value
        int randomPickedValue = ThreadLocalRandom.current().nextInt(probabilityStop) + 1;

//Use to check picked value's index amongst the probability stops
        int pickedValIndex = -1;

//If probability stops list not empty
        if(!probabilityStops.isEmpty())
        {
//For each probability stop
            for(int i = 0; i < probabilityStops.size(); i++)
            {
//If picked value falls into current range
                if(randomPickedValue <= probabilityStops.get(i))
                {
//Set picked value's index and stop search
                    pickedValIndex = i;
                    break;
                }
            }
        }

//Exception handling for no valid index
        if(pickedValIndex >= 0)
        {
            return items.get(pickedValIndex);        
        }
        else
        {
            return ItemStack.EMPTY;
        }
    }
}
