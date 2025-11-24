package net.daveyx0.primitivemobs.config;

import net.minecraftforge.common.config.Configuration;

public class PrimitiveMobsConfigSpecial {
	
	public static String[] hauntedToolLoot;

    public static int blazingJuggernautMeleeCooldownMax;
    public static double blazingJuggernautMeleeDistanceMax;
    public static int blazingJuggernautChargeCooldownMax;
    public static double blazingJuggernautChargeDistanceMax;
    public static boolean blazingJuggernautChargeDistanceIgnore;
    public static double blazingJuggernautChargePropelFactor;
    public static double blazingJuggernautChargeSpeedFactor;
    public static boolean brainSlimeIgnoresHelmets;
    public static boolean camouflageOverride;
    public static boolean camouflageAttackerDeny;
    public static boolean camouflageVictimDeny;
    public static double camouflageOverrideValue;
    public static double camouflageDivisorValue;
    public static double chameleonHealth;
    public static double chameleonHealthTamed;
    public static boolean chameleonBuffEnabled; 
    public static String chameleonBuffID;
    public static int chameleonBuffStrength;
    public static double chameleonBuffDistance;
    public static int chameleonShedCooldown;
	public static boolean dodoMycelium;
    public static String[] dodoBreedingItems;
	public static boolean minerInVillage;
	public static boolean festiveCreeperDestruction;
	public static double festiveCreeperPowerBase;
	public static double festiveCreeperPowerCharged;
	public static double festiveCreeperRetreatDistance;
    public static double festiveCreeperRange;
	public static boolean festiveCreeperRangeIgnore;
    public static boolean festiveCreeperRingAttackBase;
    public static boolean festiveCreeperRingAttackCharged;
    public static int festiveCreeperRingAttackAmount;
	public static int filchLizardLootChance;
	public static String[] filchStealLoot;
	public static String[] filchSpawnLoot;
	public static int[] filchSpawnLootWeights;
    public static int flameSpewerAttackStepMax;
    public static int flameSpewerAttackTimeMax;
    public static int flameSpewerInbetweenVulnerableTime;
    public static int flameSpewerSmallFireballPreparationMax;
    public static int flameSpewerSmallFireballInterval;
    public static double flameSpewerSmallFireballSpread;
	public static boolean lostMinerSounds;
	public static int maxSpiderFamilySize;
    public static double harpyAttackDamage;
    public static double harpyFlyingSpeed;
    public static double harpyMoveSpeed;
    public static double harpyLiftingSpeed;
    public static int harpyReleaseTimeNeeded;
    public static double harpyGrabbingMinimumReleaseDamage;
    public static int harpyGrabbingDamagedTimesNeeded;
	public static int tameableSlimeChance;
	public static boolean mimicGenerates;
	public static boolean rocketCreeperAlwaysJump;
    public static double rocketCreeperExplosionPower;
    public static double rocketCreeperChargedMultiplier;
    public static double rocketCreeperDetonationDistance;
    public static int rocketCreeperDetonationTimer;
    public static boolean rocketCreeperSpecialEnabled;
    public static int rocketCreeperSpecialCooldownInitial;
    public static int rocketCreeperSpecialCooldownInterrupted;
    public static int rocketCreeperSpecialCooldownAttacked;
    public static int rocketCreeperSpecialCooldownFrustrated;
    public static int rocketCreeperSpecialCooldownStunned;
    public static int rocketCreeperSpecialIgnitedTimeMax;
    public static double rocketCreeperSpecialInterruptedDamage;
    public static int rocketCreeperSpecialInterruptedMax;
    public static double rocketCreeperSpecialStartSpeed;
    public static double rocketCreeperSpecialAcceleration;
    public static double rocketCreeperSpecialExplosionPower;
    public static double rocketCreeperSpecialExplodeDistance;
    public static int rocketCreeperSpecialMaxTicks;
    public static String skeletonWarriorEffect; 
    public static int skeletonWarriorEffectDuration;
    public static int skeletonWarriorEffectAmplifier;
    public static double skeletonWarriorStrafeThreshold; 
    public static double skeletonWarriorSwitchToMelee; 
    public static double skeletonWarriorSwitchToRanged; 
    public static int skeletonWarriorShootDelayTime; 
    public static int skeletonWarriorShootDrawTime;
    public static double skeletonWarriorShootDamage; 
    public static double skeletonWarriorShootVelocity;
    public static double skeletonWarriorShootInaccuracyFactor; 
    public static String[] supportCreeperBuffList;
    public static int[] supportCreeperBuffLengthList;
    public static int[] supportCreeperBuffStrengthList;
    public static int[] supportCreeperBuffStrengthListPowered;
    public static boolean supportCreeperSpecialEnabled;
    public static int supportCreeperSpecialCooldownInitial;
    public static int supportCreeperSpecialCooldownInterrupted;
    public static int supportCreeperSpecialCooldownAttacked;
    public static int supportCreeperSpecialCooldownFrustrated;
    public static int supportCreeperSpecialCooldownStunned;
    public static int supportCreeperSpecialIgnitedTimeMax;
    public static double supportCreeperSpecialInterruptedDamage;
    public static int supportCreeperSpecialInterruptedMax;
    public static String[] supportCreeperSpecialBuffList;
    public static int[] supportCreeperSpecialBuffLengthList;
    public static int[] supportCreeperSpecialBuffStrengthList;
    public static int[] supportCreeperSpecialBuffStrengthListPowered;
	public static String[] treasureSlimeSpawnLoot;
	public static int[] treasureSlimeSpawnLootWeights;
    public static double trollMeleeDistance;
    public static double trollSmashDistance;
	public static boolean trollCanSmashWhileRidden;
	public static boolean trollCanThrowWhileRidden;
	public static boolean trollDestruction;
	public static double trollDestructionPower;
	public static double trollNondestructionPower;
	public static double trollThrownScale;
	public static boolean trollTurnsToStone;
	public static boolean hauntedToolFullDurability;
	public static boolean groveSpritesPlant;
	public static boolean voidEyeIgnoresWalls;
	public static int voidEyeAttackDuration;
	public static int mimicSpawnRate;
	public static int[] lostMinerLootRange;

	public static void load(Configuration config) {
		String category1 = "Mob Specific Settings";

//Adds config category
		config.addCustomCategoryComment(category1, "Settings specific to certain mobs.");
//Format is category, key, default value, comment
        blazingJuggernautMeleeCooldownMax = config.get(category1, "Blazing Juggernaut melee cooldown", 20, "Blazing Juggernaut can melee once this many ticks").getInt();
        blazingJuggernautMeleeDistanceMax = config.get(category1, "Blazing Juggernaut melee distance", 1.5, "Blazing Juggernaut can melee from this far").getDouble();
        blazingJuggernautChargeCooldownMax = config.get(category1, "Blazing Juggernaut charge cooldown", 5, "Blazing Juggernaut charge has a cooldown of this many ticks").getInt();
        blazingJuggernautChargeDistanceMax = config.get(category1, "Blazing Juggernaut charge max distance", 6.0, "Blazing Juggernaut can only charge if it's this close").getDouble();
        blazingJuggernautChargeDistanceIgnore = config.get(category1, "Blazing Juggernaut charge max distance ignore", true, "Blazing Juggernaut charge ignores max distance").getBoolean();
        blazingJuggernautChargePropelFactor = config.get(category1, "Blazing Juggernaut charge force", 0.32, "Blazing Juggernaut charge propels it with this much force").getDouble();
        blazingJuggernautChargeSpeedFactor = config.get(category1, "Blazing Juggernaut charge speed multiplier", 1.25, "Blazing Juggernaut charge multiplies its speed this much").getDouble();
        brainSlimeIgnoresHelmets = config.get(category1, "Brain Slime ignores helmets", false, "Brain Slime hits through helmets or not").getBoolean();
        camouflageOverride = config.get(category1, "Camouflage armor overrides mob sight range", true, "If true, camouflage armor overrides mob sight range, if false, it divides it instead").getBoolean();
        camouflageAttackerDeny = config.get(category1, "Camouflage armor fails on prior attacker", true, "Whether the camouflage armor fails if a mob remembers attacking you").getBoolean();
        camouflageVictimDeny = config.get(category1, "Camouflage armor fails on prior victim", true, "Whether the camouflage armor fails if a mob remembers being attacked by you").getBoolean();
        camouflageOverrideValue = config.get(category1, "Camouflage armor sight override", 6, "How much the camouflage armor should override mob sight range by").getDouble();
        camouflageDivisorValue = config.get(category1, "Camouflage armor sight division", 8, "How much the camouflage armor should divide mob sight range by").getDouble();
        chameleonHealth = config.get(category1, "Chameleon nontamed health", 20.0, "Base health of an untamed chameleon").getDouble(); //And then pass to type parser
        chameleonHealthTamed = config.get(category1, "Chameleon health tamed", 100.0, "Base health of a tamed chameleon").getDouble();
        chameleonBuffEnabled = config.get(category1, "Chameleon tamed buff enabled", true, "Whether tamed chameleons should buff their owners").getBoolean();
        chameleonBuffID = config.get(category1, "Chameleon tamed buff ID", "minecraft:invisibility", "ID of a tamed chameleon's buff").getString();
        chameleonBuffStrength = config.get(category1, "Chameleon tamed buff strength (from 0)", 0, "Potency of buff applied by a tamed chameleon (starts from 0)").getInt();
        chameleonBuffDistance = config.get(category1, "Chameleon tamed buff distance", 32, "Distance from which a tamed chameleon will buff").getDouble();
        chameleonShedCooldown = config.get(category1, "Chameleon dye shed cooldown", 60, "Cooldown in seconds for a chameleon to shed camo dye").getInt();
		dodoMycelium = config.get(category1, "Dodos convert Grass into Mycelium", true, "Enable/Disable if Dodos should convert Grass into Mycelium blocks overtime").getBoolean();
        dodoBreedingItems = config.get(category1, "Dodo breeding items", new String[]{"minecraft:red_flower", "minecraft:beetroot_seeds", "minecraft:brown_mushroom", "minecraft:red_mushroom", "minecraft:pumpkin_seeds", "minecraft:melon_seeds", "minecraft:wheat_seeds", "minecraft:apple", "minecraft:beetroot", "minecraft:carrot", "minecraft:melon", "minecraft:wheat", "minecraft:reeds", "minecraft:poisonous_potato", "minecraft:pumpkin", "minecraft:melon_block"}, "Dodo can be bred with one of these items at random").getStringList();
		minerInVillage = config.get(category1, "The Miner profession spawns in villages", false, "Enable/Disable if the Miner Villager profession should also spawn naturally in villages").getBoolean(); 
		festiveCreeperDestruction = config.get(category1, "Festive Creeper tnt harms terrain", true, "Enable/Disable if the Festive Creeper throws tnt that harms the terrain (this way you do not have to disable ALL Creeper explosions with mobGriefing)").getBoolean();
		festiveCreeperPowerBase = config.get(category1, "Festive Creeper explosion power (base)", 1.5, "Explosion power of TNT thrown by (base) Festive Creeper").getDouble();
		festiveCreeperPowerCharged = config.get(category1, "Festive Creeper explosion power (charged)", 3, "Explosion power of TNT thrown by (charged) Festive Creeper").getDouble();
		festiveCreeperRetreatDistance = config.get(category1, "Festive Creeper retreat distance", 7.5, "How close you have to be for a Festive Creeper to retreat").getDouble();
		festiveCreeperRange = config.get(category1, "Festive Creeper attack range", 12.0, "Max range festive creepers can attack from").getDouble();
		festiveCreeperRangeIgnore = config.get(category1, "Festive Creeper range ignore", false, "Whether festive creepers should ignore their max range").getBoolean();
		festiveCreeperRingAttackBase = config.get(category1, "Festive Creeper ring attack (base)", false, "Whether all festive creepers should gain a special attack where they periodically throw a ring of TNT around themselves").getBoolean();
		festiveCreeperRingAttackCharged = config.get(category1, "Festive Creeper ring attack (charged)", false, "Whether charged festive creepers should gain a special attack where they periodically throw a ring of TNT around themselves").getBoolean();
		festiveCreeperRingAttackAmount = config.get(category1, "Festive Creeper ring attack amount", 8, "Festive Creeper ring attack throws this many TNTs at once").getInt();
		filchLizardLootChance = config.get(category1, "Chance that a Filch Lizard spawns holding loot", 25, "Set the chance that a Filch Lizard spawns holding loot in percentage").getInt();
        filchStealLoot = config.get(category1, "Filch Lizard steal loot", new String[]{"minecraft:diamond", "minecraft:emerald", "minecraft:ender_pearl", "minecraft:flint", "minecraft:gold_ingot", "minecraft:iron_ingot", "minecraft:lapis_ore"}, "Items by ID which a Filch Lizard will steal").getStringList();
        filchSpawnLoot = config.get(category1, "Filch Lizard spawn loot", new String[]{"minecraft:diamond", "minecraft:emerald", "minecraft:ender_pearl", "minecraft:flint", "minecraft:gold_ingot", "minecraft:iron_ingot", "minecraft:lapis_ore"}, "Items by ID which a Filch Lizard can spawn with").getStringList();
		filchSpawnLootWeights = config.get(category1, "Filch Lizard spawn loot weights", new int[]{1, 1, 2, 8, 3, 6, 4}, "Probability weights for each Filch Lizard spawn loot item").getIntList();
        flameSpewerAttackStepMax = config.get(category1, "Flame Spewer attack shot max", 10, "Flame Spewer attack stops after shooting this many small fireballs").getInt();
        flameSpewerAttackTimeMax = config.get(category1, "Flame Spewer attack delay", 100, "Flame Spewer attack has this many ticks of wait time").getInt();
        flameSpewerInbetweenVulnerableTime = config.get(category1, "Flame Spewer inbetween attack vulnerability time", 50, "Flame Spewer spends this many ticks inbetween attacks vulnerable").getInt();
        flameSpewerSmallFireballPreparationMax = config.get(category1, "Flame Spewer small fireball attack preparation ticks", 30, "Flame Spewer spends this many ticks between igniting and doing small fireball attack").getInt();
        flameSpewerSmallFireballInterval = config.get(category1, "Flame Spewer small fireball interval", 3, "Flame Spewer small fireball interval in ticks").getInt();
        flameSpewerSmallFireballSpread = config.get(category1, "Flame Spewer small fireball spread", 0.02, "Flame Spewer small fireball spread factor").getDouble();
		maxSpiderFamilySize = config.get(category1, "Max Spider Family Size", 6, "Set the maximum amount of Baby Spiders that can potentially spawn with a Mother Spider").getInt();
        harpyAttackDamage = config.get(category1, "Harpy attack damage", 0.0, "How much damage does the Harpy do when grabbing a target").getDouble();
        harpyFlyingSpeed = config.get(category1, "Harpy flying speed", 0.5000000059604645, "Harpy base movement speed (when flying)").getDouble();
        harpyMoveSpeed = config.get(category1, "Harpy movement speed", 0.20000000298023224, "Harpy base movement speed (other)").getDouble();
        harpyLiftingSpeed = config.get(category1, "Harpy lifting speed", 1.5, "Harpy lifting speed").getDouble();
        harpyReleaseTimeNeeded = config.get(category1, "Harpy release time needed", 60, "Harpy releases targets after this many ticks").getInt();
        harpyGrabbingMinimumReleaseDamage = config.get(category1, "Harpy grabbing minimum release damage", 1.0, "Hits on Harpy need to do at least this much damage to count for it releasing target").getDouble();
        harpyGrabbingDamagedTimesNeeded = config.get(category1, "Harpy grabbing damaged times needed", 1, "Harpy has to be hit this many times to release target").getInt();     
		lostMinerSounds = config.get(category1, "Lost Miner makes villager sounds", true, "Enable/Disable if the Lost Miner should make villager sounds").getBoolean();
		tameableSlimeChance = config.get(category1, "Chance that a tameable Treasure Slime spawns", 5, "Set the chance that a tameable Treasure Slime can spawn in percentage").getInt();
		mimicGenerates = config.get(category1, "Mimic chests generate in caves", true, "Enable/Disable if the Mimic chests should generate in caves, instead of spawn like mobs").getBoolean();
		rocketCreeperAlwaysJump = config.get(category1, "Rocket Creepers always jump, even without adequate room", false, "Enable/Disable if rocket creepers should always jump at the player, even if they would hit the ceiling").getBoolean();
        rocketCreeperExplosionPower = config.get(category1, "Rocket Creeper explosion power", 3.0, "Rocket Creeper explodes with this much power").getDouble();
        rocketCreeperChargedMultiplier = config.get(category1, "Rocket Creeper charged multiplier", 2.0, "Rocket Creeper being charged multiplies explosion power this much").getDouble();
        rocketCreeperDetonationDistance = config.get(category1, "Rocket Creeper max explode distance", 5.0, "How close a Rocket Creeper has to be before swelling up to do a normal explosion").getDouble();
        rocketCreeperDetonationTimer = config.get(category1, "Rocket Creeper explode timer", 15, "Rocket Creeper spends this many ticks charging to explode normally").getInt();
        rocketCreeperSpecialEnabled = config.get(category1, "Rocket Creeper homing attack enabled", true, "Rocket Creeper special attack where it homes in like a missile enabled or not").getBoolean();
        rocketCreeperSpecialCooldownInitial = config.get(category1, "Rocket Creeper homing cooldown initial", 40, "Rocket Creeper spawns with this long of a cooldown in ticks before being able to first charge its special attack").getInt();
        rocketCreeperSpecialCooldownInterrupted = config.get(category1, "Rocket Creeper homing cooldown interrupted", 100, "Rocket Creeper special attack has this long of a cooldown in ticks when it loses line of sight to the target").getInt();
        rocketCreeperSpecialCooldownAttacked = config.get(category1, "Rocket Creeper homing cooldown attacked", 100, "Rocket Creeper special attack has this long of a cooldown in ticks when it gets hit by a melee attack or arrow").getInt();
        rocketCreeperSpecialCooldownFrustrated = config.get(category1, "Rocket Creeper homing cooldown frustrated", 400, "Rocket Creeper special attack has this long of a cooldown in ticks when it gets interrupted too many times doing it and gives up").getInt();
        rocketCreeperSpecialCooldownStunned = config.get(category1, "Rocket Creeper homing cooldown stunned", 400, "Rocket Creeper special attack has this long of a cooldown in ticks when it gets stunned mid-swelling").getInt();
        rocketCreeperSpecialIgnitedTimeMax = config.get(category1, "Rocket Creeper homing ignite time", 100, "Rocket Creeper spends this many ticks charging its special attack").getInt();
        rocketCreeperSpecialInterruptedDamage = config.get(category1, "Rocket Creeper special interruption damage", 1.0, "Rocket Creeper has to take this much damage to get its special attack interrupted").getDouble();
        rocketCreeperSpecialInterruptedMax = config.get(category1, "Rocket Creeper homing interruption max", 5, "If a Rocket Creeper is interrupted this many times while preparing its special attack it gives up temporarily").getInt();
        rocketCreeperSpecialStartSpeed = config.get(category1, "Rocket Creeper homing initial speed", 0.25, "Rocket Creeper starts at this many blocks per tick when homing").getDouble();
        rocketCreeperSpecialAcceleration = config.get(category1, "Rocket Creeper homing acceleration", 1.05, "Rocket Creeper accelerates this much each tick when homing").getDouble();
        rocketCreeperSpecialExplosionPower = config.get(category1, "Rocket Creeper homing explosion power", 6.0, "Rocket Creeper special attack has this much power").getDouble();
        rocketCreeperSpecialExplodeDistance = config.get(category1, "Rocket Creeper homing max explode distance", 2.0, "Max distance in blocks a Rocket Creeper, while homing, can be from the target before exploding").getDouble();
        rocketCreeperSpecialMaxTicks = config.get(category1, "Rocket Creeper homing max ticks", 60, "Max time in ticks a Rocket Creeper can spend homing before exploding").getInt();
        skeletonWarriorEffect = config.get(category1, "Skeleton Warrior effect", "", "ID of a Skeleton Warrior's arrow potion effect, leave empty for none").getString();
        skeletonWarriorEffectDuration = config.get(category1, "Skeleton Warrior effect duration", 600, "Skeleton Warrior potion effect lasts this many ticks").getInt();
        skeletonWarriorEffectAmplifier = config.get(category1, "Skeleton Warrior effect amplifier", 1, "Skeleton Warrior potion effect has this potency").getInt();
        skeletonWarriorStrafeThreshold = config.get(category1, "Skeleton Warrior strafe threshold", 16.0, "Skeleton Warrior alternates strafe pattern based on this distance").getDouble(); 
        skeletonWarriorSwitchToMelee = config.get(category1, "Skeleton Warrior switch to melee distance", 5.0, "Skeleton Warrior switches to melee at this distance").getDouble();
        skeletonWarriorSwitchToRanged = config.get(category1, "Skeleton Warrior switch to ranged distance", 6.0, "Skeleton Warrior switches to ranged at this distance").getDouble(); 
        skeletonWarriorShootDelayTime = config.get(category1, "Skeleton Warrior shoot delay time", 20, "Skeleton Warrior has this much of a delay in ticks of preparing before drawing its bow").getInt();
        skeletonWarriorShootDrawTime = config.get(category1, "Skeleton Warrior shoot draw time", 20, "Skeleton Warrior has this much of a delay in ticks of drawing its bow before shooting").getInt();
        skeletonWarriorShootDamage = config.get(category1, "Skeleton Warrior shoot damage", 4.0, "Skeleton Warrior arrows have this much damage").getDouble();  
        skeletonWarriorShootVelocity = config.get(category1, "Skeleton Warrior shoot velocity", 1.6, "Skeleton Warrior arrows have this much velocity").getDouble();
        skeletonWarriorShootInaccuracyFactor = config.get(category1, "Skeleton Warrior shoot inaccuracy", 1.0, "Skeleton Warrior shoot innacuracy multiplied by this factor").getDouble();
        supportCreeperBuffList = config.get(category1, "Support Creeper buff list", new String[]{"minecraft:fire_resistance", "minecraft:speed", "minecraft:strength"}, "Support Creeper applies these buffs").getStringList();
        supportCreeperBuffLengthList = config.get(category1, "Support Creeper buff length list", new int[]{60, 60, 60}, "Support Creeper buffs have these lengths in ticks").getIntList();
        supportCreeperBuffStrengthList = config.get(category1, "Support Creeper buff strength list (base)", new int[]{1, 1, 1}, "Support Creeper buffs have these amplifiers when not charged").getIntList();
        supportCreeperBuffStrengthListPowered = config.get(category1, "Support Creeper buff strength list (charged)", new int[]{2, 2, 2}, "Support Creeper buffs have these amplifiers when charged").getIntList();
        supportCreeperSpecialEnabled = config.get(category1, "Support Creeper special attack enabled", true, "Support Creeper special attack where it buffs mobs in a wide radius enabled or not").getBoolean();
        supportCreeperSpecialCooldownInitial = config.get(category1, "Support Creeper special cooldown initial", 40, "Support Creeper spawns with this long of a cooldown in ticks before being able to first charge its special attack").getInt();
        supportCreeperSpecialCooldownInterrupted = config.get(category1, "Support Creeper special cooldown interrupted", 100, "Support Creeper special attack has this long of a cooldown in ticks when it loses line of sight to the target").getInt();
        supportCreeperSpecialCooldownAttacked = config.get(category1, "Support Creeper special cooldown attacked", 100, "Support Creeper special attack has this long of a cooldown in ticks when it gets hit by a melee attack or arrow").getInt();
        supportCreeperSpecialCooldownFrustrated = config.get(category1, "Support Creeper special cooldown frustrated", 400, "Support Creeper special attack has this long of a cooldown in ticks when it gets interrupted too many times doing it and gives up").getInt();
        supportCreeperSpecialCooldownStunned = config.get(category1, "Support Creeper special cooldown stunned", 400, "Support Creeper special attack has this long of a cooldown in ticks when it gets stunned mid-swelling").getInt();
        supportCreeperSpecialIgnitedTimeMax = config.get(category1, "Support Creeper special ignite time", 100, "Support Creeper spends this many ticks charging its special attack").getInt();
        supportCreeperSpecialInterruptedDamage = config.get(category1, "Support Creeper special interruption damage", 1.0, "Support Creeper has to take this much damage to get its special attack interrupted").getDouble();
        supportCreeperSpecialInterruptedMax = config.get(category1, "Support Creeper special interruption max", 5, "If a Support Creeper is interrupted this many times while preparing its special attack it gives up temporarily").getInt();
        supportCreeperSpecialBuffList = config.get(category1, "Support Creeper special attack buff list", new String[]{"minecraft:fire_resistance", "minecraft:speed", "minecraft:strength"}, "Support Creeper special attack applies these buffs").getStringList();
        supportCreeperSpecialBuffLengthList = config.get(category1, "Support Creeper special attack buff length list", new int[]{60, 60, 60}, "Support Creeper special attack buffs have these lengths in ticks").getIntList();
        supportCreeperSpecialBuffStrengthList = config.get(category1, "Support Creeper special attack buff strength list (base)", new int[]{1, 1, 1}, "Support Creeper special attack buffs have these amplifiers when not charged").getIntList();
        supportCreeperSpecialBuffStrengthListPowered = config.get(category1, "Support Creeper special attack buff strength list (charged)", new int[]{2, 2, 2}, "Support Creeper special attack buffs have these amplifiers when charged").getIntList();
        treasureSlimeSpawnLoot = config.get(category1, "Treasure Slime spawn loot", new String[]{"minecraft:apple", "minecraft:cookie", "minecraft:gold_ingot", "minecraft:iron_ingot", "minecraft:redstone", "minecraft:coal", "minecraft:diamond", "minecraft:cake", "minecraft:carrot", "minecraft:golden_apple", "minecraft:ender_pearl", "minecraft:saddle", "minecraft:magma_cream", "minecraft:ender_eye", "minecraft:blaze_powder", "minecraft:record_wait"}, "Items by ID which a Treasure Slime can spawn with").getStringList();
		treasureSlimeSpawnLootWeights = config.get(category1, "Treasure Slime spawn loot weights", new int[]{10, 10, 5, 8, 5, 8, 1, 2, 6, 1, 4, 1, 2, 1, 3, 1}, "Probability weights for each Treasure Slime spawn loot item").getIntList();
        trollMeleeDistance = config.get(category1, "Trollager distance to melee", 2.5, "Trollager can melee from this far").getDouble();
        trollSmashDistance = config.get(category1, "Trollager distance to smash", 20.0, "Trollager can smash from this far").getDouble();
		trollCanSmashWhileRidden = config.get(category1, "Trollager can smash while ridden", false, "Enable/Disable if the Trollager can smash while ridden").getBoolean();
		trollCanThrowWhileRidden = config.get(category1, "Trollager can throw while ridden", false, "Enable/Disable if the Trollager can throw while ridden").getBoolean();
		trollDestruction = config.get(category1, "Trollager can destroy terrain", true, "Enable/Disable if the Trollager can destroy terrain with its attack").getBoolean();
		trollDestructionPower = config.get(category1, "Trollager explosion power", 3.0, "Trollager smash attack explosion power").getDouble();
		trollNondestructionPower = config.get(category1, "Trollager explosion power (non-destructive)", 0.0, "If higher than 0, Trollager explosion attack comes with an extra non-destructive explosion that can be used to extend the attack").getDouble();
		trollThrownScale = config.get(category1, "Trollager thrown block scale", 1.0, "Trollager block throw attack scale").getDouble();
	    trollTurnsToStone = config.get(category1, "Trollager turns to stone in the sun", true, "Whether the Trollager turns to stone in sunlight").getBoolean();
		lostMinerLootRange = config.get(category1, "Lost Miner emerald loot range", new int[]{2,2}, "Change the amount of emeralds you receive when saving a Lost Miner. First number = minimum. Second number = variable addition").getIntList();
		hauntedToolFullDurability = config.get(category1, "Haunted Tool drop full durability", false, "Enable/Disable if Haunted Tools should drop full durability items.").getBoolean();
		mimicSpawnRate = config.get(category1, "Spawn Rate of Mimics", 25, "How often a Mimic chest generates. Higher number = rarer. 0 = no mimics chests.").getInt();
		groveSpritesPlant = config.get(category1, "Grove Sprites plant saplings", true, "Enable/Disable if Grove Sprites should plant sapling they hold.").getBoolean();
		voidEyeIgnoresWalls = config.get(category1, "Void Eye ignores walls", false, "Enable/Disable if Void Eyes should attack through walls.").getBoolean();
        voidEyeAttackDuration = config.get(category1, "Void Eye attack duration", 75, "How long in ticks Void Eyes need for their attack to land.").getInt();
	}


    public static int getBlazingJuggernautMeleeCooldownMax()
    {
        return blazingJuggernautMeleeCooldownMax;
    }

    public static double getBlazingJuggernautMeleeDistanceMax()
    {
        return blazingJuggernautMeleeDistanceMax;
    }

    public static int getBlazingJuggernautChargeCooldownMax()
    {
        return blazingJuggernautChargeCooldownMax;
    }

    public static double getBlazingJuggernautChargeDistanceMax()
    {
        return blazingJuggernautChargeDistanceMax;
    }

    public static boolean getBlazingJuggernautChargeDistanceIgnore()
    {
        return blazingJuggernautChargeDistanceIgnore;
    }

    public static double getBlazingJuggernautChargePropelFactor()
    {
        return blazingJuggernautChargePropelFactor;
    }

    public static double getBlazingJuggernautChargeSpeedFactor()
    {
        return blazingJuggernautChargeSpeedFactor;
    }

    public static boolean getBrainSlimeIgnoresHelmets()
    {
        return brainSlimeIgnoresHelmets;    
    }

    public static boolean getCamouflageOverride()
    {
        return camouflageOverride;
    }

    public static boolean getCamouflageAttackerDeny()
    {
        return camouflageAttackerDeny;
    }
    
    public static boolean getCamouflageVictimDeny()
    {
        return camouflageVictimDeny;
    }

    public static double getCamouflageOverrideValue()
    {
        return camouflageOverrideValue;
    }

    public static double getCamouflageDivisorValue() 
    {
        return camouflageDivisorValue;
    }

    public static double getChameleonHealth() 
    {
        return chameleonHealth;
    }

    public static double getChameleonHealthTamed()
    {
        return chameleonHealthTamed;
    }
    
    public static boolean getChameleonBuffEnabled() 
    {
        return chameleonBuffEnabled;
    }

    public static String getChameleonBuffID() 
    {
        return chameleonBuffID;
    }

    public static int getChameleonBuffStrength() 
    {
        return chameleonBuffStrength;
    }

    public static double getChameleonBuffDistance() 
    {
        return chameleonBuffDistance;
    }

    public static int getChameleonShedCooldown() 
    {
        return chameleonShedCooldown;
    }

	public static boolean getDodoMycelium()
	{
		return dodoMycelium;
	}

    public static String[] getDodoBreedingItems()
    {
        return dodoBreedingItems;
    }
	
	public static String[] getHauntedToolLoot()
	{
		return hauntedToolLoot;
	}
	
	public static boolean getMinerInVillage()
	{
		return minerInVillage;
	}
	
	public static boolean getFestiveCreeperDestruction()
	{
		return festiveCreeperDestruction;
	}

	public static double getFestiveCreeperPowerBase()
	{
		return festiveCreeperPowerBase;
	}

	public static double getFestiveCreeperPowerCharged()
	{
		return festiveCreeperPowerCharged;
	}

	public static double getFestiveCreeperRetreatDistance()
	{
		return festiveCreeperRetreatDistance;
	}

	public static double getFestiveCreeperRange()
	{
		return festiveCreeperRange;
	}

	public static boolean getFestiveCreeperRangeIgnore()
	{
		return festiveCreeperRangeIgnore;
	}

	public static boolean getFestiveCreeperRingAttackBase()
	{
		return festiveCreeperRingAttackBase;
	}

	public static boolean getFestiveCreeperRingAttackCharged()
	{
		return festiveCreeperRingAttackCharged;
	}

	public static int getFestiveCreeperRingAttackAmount()
	{
		return festiveCreeperRingAttackAmount;
	}

	public static int getFilchLizardLootChance()
	{
		return filchLizardLootChance;
	}

	public static String[] getFilchStealLoot()
	{
		return filchStealLoot;
	}

	public static String[] getFilchSpawnLoot()
	{
		return filchSpawnLoot;
	}
	
	public static int[] getFilchSpawnLootWeights()
	{
		return filchSpawnLootWeights;
	}

    public static int getFlameSpewerAttackStepMax()
    {
        return flameSpewerAttackStepMax;
    }

    public static int getFlameSpewerAttackTimeMax()
    {
        return flameSpewerAttackTimeMax;
    }

    public static int getFlameSpewerInbetweenVulnerableTime()
    {
        return flameSpewerInbetweenVulnerableTime;
    }

    public static int getFlameSpewerSmallFireballPreparationMax()
    {
        return flameSpewerSmallFireballPreparationMax;
    }

    public static int getFlameSpewerSmallFireballInterval()
    {
        return flameSpewerSmallFireballInterval;
    }

    public static double getFlameSpewerSmallFireballSpread()
    {
        return flameSpewerSmallFireballSpread;
    }
	
	public static int getMaxSpiderFamilySize()
	{
		return maxSpiderFamilySize;
	}

    public static double getHarpyAttackDamage()
    {
        return harpyAttackDamage;
    }

    public static double getHarpyFlyingSpeed()
    {
        return harpyFlyingSpeed;
    }

    public static double getHarpyMoveSpeed()
    {
        return harpyMoveSpeed;
    }

    public static double getHarpyLiftingSpeed()
    {
        return harpyLiftingSpeed;
    }

    public static int getHarpyReleaseTimeNeeded()
    {
        return harpyReleaseTimeNeeded;
    }

    public static double getHarpyGrabbingMinimumReleaseDamage()
    {
        return harpyGrabbingMinimumReleaseDamage;
    }

    public static int getHarpyGrabbingDamagedTimesNeeded()
    {
        return harpyGrabbingDamagedTimesNeeded;
    }

	public static int getMimicRarity()
	{
		return mimicSpawnRate;
	}
	
	public static boolean getLostMinerSounds()
	{
		return lostMinerSounds;
	}
	
	public static int getTameableSlimeChance()
	{
		return tameableSlimeChance;
	}
	
	public static boolean getMimicGeneratesInCaves()
	{
		return mimicGenerates;
	}
	
	public static boolean getRocketCreeperAlwaysJump()
	{
		return rocketCreeperAlwaysJump;
	}

    public static double getRocketCreeperExplosionPower()
    {
        return rocketCreeperExplosionPower;
    }
	
    public static double getRocketCreeperChargedMultiplier()
    {
        return rocketCreeperChargedMultiplier;
    }

    public static double getRocketCreeperDetonationDistance()
    {
        return rocketCreeperDetonationDistance;
    }

    public static int getRocketCreeperDetonationTimer()
    {
        return rocketCreeperDetonationTimer;
    }

    public static boolean getRocketCreeperSpecialEnabled()
    {
        return rocketCreeperSpecialEnabled;
    }
    
    public static int getRocketCreeperSpecialCooldownInitial()
    {
        return rocketCreeperSpecialCooldownInitial;
    }
    
    public static int getRocketCreeperSpecialCooldownInterrupted()
    {
        return rocketCreeperSpecialCooldownInterrupted;
    }

    public static int getRocketCreeperSpecialCooldownAttacked()
    {
        return rocketCreeperSpecialCooldownAttacked;
    }

    public static int getRocketCreeperSpecialCooldownFrustrated()
    {
        return rocketCreeperSpecialCooldownFrustrated;
    }

    public static int getRocketCreeperSpecialCooldownStunned()
    {
        return rocketCreeperSpecialCooldownStunned;
    }

    public static int getRocketCreeperSpecialIgnitedTimeMax()
    {
        return rocketCreeperSpecialIgnitedTimeMax;
    }

    public static double getRocketCreeperSpecialInterruptedDamage()
    {
        return rocketCreeperSpecialInterruptedDamage;
    }

    public static int getRocketCreeperSpecialInterruptedMax()
    {
        return rocketCreeperSpecialInterruptedMax;
    }

    public static double getRocketCreeperSpecialStartSpeed()
    {
        return rocketCreeperSpecialStartSpeed;
    }

    public static double getRocketCreeperSpecialAcceleration()
    {
        return rocketCreeperSpecialAcceleration;
    }

    public static double getRocketCreeperSpecialExplosionPower()
    {
        return rocketCreeperSpecialExplosionPower;
    }

    public static double getRocketCreeperSpecialExplodeDistance()
    {
        return rocketCreeperSpecialExplodeDistance;
    }

    public static int getRocketCreeperSpecialMaxTicks()
    {
        return rocketCreeperSpecialMaxTicks;
    }

    public static String getSkeletonWarriorEffect()
    {
        return skeletonWarriorEffect;
    }

    public static int getSkeletonWarriorEffectDuration()
    {
        return skeletonWarriorEffectDuration;
    }

    public static int getSkeletonWarriorEffectAmplifier()
    {
        return skeletonWarriorEffectAmplifier;
    }

    public static double getSkeletonWarriorStrafeThreshold()
    {
        return skeletonWarriorStrafeThreshold;
    }
    
    public static double getSkeletonWarriorSwitchToMelee()
    {
        return skeletonWarriorSwitchToMelee;
    }
    
    public static double getSkeletonWarriorSwitchToRanged()
    {
        return skeletonWarriorSwitchToRanged;
    }
    
    public static int getSkeletonWarriorShootDelayTime()
    {
        return skeletonWarriorShootDelayTime;
    }

    public static int getSkeletonWarriorShootDrawTime()
    {
        return skeletonWarriorShootDrawTime;
    }
   
    public static double getSkeletonWarriorShootVelocity()
    {
        return skeletonWarriorShootVelocity;
    }

    public static double getSkeletonWarriorShootDamage()
    {
        return skeletonWarriorShootDamage;
    }

    public static double getSkeletonWarriorShootInaccuracyFactor()
    {
        return skeletonWarriorShootInaccuracyFactor;
    }

    public static String[] getSupportCreeperBuffList()
    {
        return supportCreeperBuffList;
    }
    
    public static int[] getSupportCreeperBuffLengthList()
    {
        return supportCreeperBuffLengthList;
    }

    public static int[] getSupportCreeperBuffStrengthList()
    {
        return supportCreeperBuffStrengthList;
    }

    public static int[] getSupportCreeperBuffStrengthListPowered()
    {
        return supportCreeperBuffStrengthListPowered;
    }

    public static boolean getSupportCreeperSpecialEnabled()
    {
        return supportCreeperSpecialEnabled;
    }

    public static int getSupportCreeperSpecialCooldownInitial()
    {
        return supportCreeperSpecialCooldownInitial;
    }

    public static int getSupportCreeperSpecialCooldownInterrupted()
    {
        return supportCreeperSpecialCooldownInterrupted;
    }

    public static int getSupportCreeperSpecialCooldownAttacked()
    {
        return supportCreeperSpecialCooldownAttacked;
    }

    public static int getSupportCreeperSpecialCooldownFrustrated()
    {
        return supportCreeperSpecialCooldownFrustrated;
    }

    public static int getSupportCreeperSpecialCooldownStunned()
    {
        return supportCreeperSpecialCooldownStunned;
    }
    
    public static int getSupportCreeperSpecialIgnitedTimeMax()
    {
        return supportCreeperSpecialIgnitedTimeMax;
    }

    public static double getSupportCreeperSpecialInterruptedDamage()
    {
        return supportCreeperSpecialInterruptedDamage;
    }

    public static int getSupportCreeperSpecialInterruptedMax()
    {
        return supportCreeperSpecialInterruptedMax;
    }
    public static String[] getSupportCreeperSpecialBuffList()
    {
        return supportCreeperSpecialBuffList;
    }
    public static int[] getSupportCreeperSpecialBuffLengthList()
    {
        return supportCreeperSpecialBuffLengthList;
    }

    public static int[] getSupportCreeperSpecialBuffStrengthList()
    {
        return supportCreeperSpecialBuffStrengthList;
    }

    public static int[] getSupportCreeperSpecialBuffStrengthListPowered()
    {
        return supportCreeperSpecialBuffStrengthListPowered;
    }

	public static String[] getTreasureSlimeSpawnLoot()
	{
		return treasureSlimeSpawnLoot;
	}
	
	public static int[] getTreasureSlimeSpawnLootWeights()
	{
		return treasureSlimeSpawnLootWeights;
	}

    public static double getTrollMeleeDistance()
    {
        return trollMeleeDistance;
    } 

    public static double getTrollSmashDistance()
    {
        return trollSmashDistance;
    }

    public static boolean getTrollCanSmashWhileRidden()
    {
        return trollCanSmashWhileRidden;
    }

    public static boolean getTrollCanThrowWhileRidden()
    {
        return trollCanThrowWhileRidden;
    }
	
	public static boolean getTrollDestruction()
	{
		return trollDestruction;
	}

	public static double getTrollDestructionPower()
	{
		return trollDestructionPower;
	}

	public static double getTrollNondestructionPower()
	{
		return trollNondestructionPower;
	}

	public static double getTrollThrownScale()
	{
		return trollThrownScale;
	}

    public static boolean getTrollTurnsToStone()
    {
        return trollTurnsToStone;
    }

	public static int[] getLostMinerLootRange()
	{
		return lostMinerLootRange;
	}
	
	public static boolean getHauntedToolDurability()
	{
		return hauntedToolFullDurability;
	}
	
	public static boolean getGroveSpritesPlant()
	{
		return groveSpritesPlant;
	}

    public static boolean getVoidEyeIgnoresWalls()
    {
        return voidEyeIgnoresWalls;
    }

    public static int getVoidEyeAttackDuration()
    {
        return voidEyeAttackDuration;
    }
}
