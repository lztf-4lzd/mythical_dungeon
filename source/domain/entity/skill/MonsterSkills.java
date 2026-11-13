package source.domain.entity.skill;

import source.domain.entity.effect.EffectCatalogue;
import source.domain.entity.effect.EffectType;
import source.domain.entity.elemental.Elemental;
import source.domain.entity.monster.MonsterId;

import java.util.ArrayList;
import java.util.List;

public final class MonsterSkills {
    private MonsterSkills() {}

    public static Skill createBasicAttack(MonsterId monsterId) {
        if (monsterId == null) {
            throw new IllegalArgumentException("monsterId must not be null.");
        }
        return switch (monsterId) {
            case SOLDIER -> new Skill("Short Sword Slash", 0, 6.0, 0.45, Elemental.NORMAL, false);
            case ORC -> new Skill("Handaxe Chop", 0, 6.0, 0.45, Elemental.NORMAL, false);
            case ELITE_ORC -> new Skill("Greataxe Hew", 0, 7.0, 0.50, Elemental.NORMAL, false);
            case ARMORED_ORC -> new Skill("Cleaver Strike", 0, 6.0, 0.45, Elemental.NORMAL, false);
            case SKELETON -> new Skill("Rusty Sword Slash", 0, 6.0, 0.45, Elemental.NORMAL, false);
            case ARMORED_SKELETON -> new Skill("Halberd Poke", 0, 6.5, 0.48, Elemental.NORMAL, false);
            case GREATSWORD_SKELETON -> new Skill("Heavy Greatsword Drag", 0, 7.5, 0.52, Elemental.NORMAL, false);
            case SKELETON_ARCHER -> new Skill("Bone Arrow", 0, 6.0, 0.45, Elemental.NORMAL, false);
            case SLIME -> new Skill("Slime Splash", 0, 6.0, 0.45, Elemental.NORMAL, true);
            case BAT -> new Skill("Swoop Bite", 0, 5.5, 0.45, Elemental.NORMAL, false);
            case ORC_RIDER -> new Skill("Mounted Cleaver", 0, 7.5, 0.52, Elemental.NORMAL, false);
            case WEREBEAR -> new Skill("Bear Claw Swipe", 0, 7.0, 0.50, Elemental.NORMAL, false);
            case WEREWOLF -> new Skill("Silver Claw Slash", 0, 6.5, 0.48, Elemental.NORMAL, false);
            case BLACK_KNIGHT_A -> new Skill("Ashen Blade Thrust", 0, 7.5, 0.52, Elemental.NORMAL, false);
            case BLACK_KNIGHT_B -> new Skill("Gunlance Thrust", 0, 7.0, 0.50, Elemental.NORMAL, false);
            case BLACK_KNIGHT_C -> new Skill("Nightmare Greatsword", 0, 8.0, 0.55, Elemental.NORMAL, false);
            case BLOOD_MONSTER_A -> new Skill("Tendril Whip", 0, 6.0, 0.45, Elemental.NORMAL, false);
            case BLOOD_MONSTER_B -> new Skill("Floating Bite", 0, 5.5, 0.45, Elemental.NORMAL, false);
            case DEMON_A -> new Skill("Machete Slash", 0, 6.0, 0.45, Elemental.NORMAL, false);
            case DEMON_B -> new Skill("Barbed Bow Dagger", 0, 6.5, 0.48, Elemental.NORMAL, false);
            case DEMON_C -> new Skill("Trident Jab", 0, 6.5, 0.48, Elemental.NORMAL, false);
            case DEMON_D -> new Skill("Maul Smash", 0, 8.0, 0.55, Elemental.NORMAL, false);
            case DEMON_E -> new Skill("Longblade Lunge", 0, 7.5, 0.52, Elemental.NORMAL, false);
            case DEMONESS_A -> new Skill("Scythe Reap", 0, 7.5, 0.52, Elemental.NORMAL, false);
            case DEMONESS_B -> new Skill("Crimson Whip Snap", 0, 6.0, 0.48, Elemental.NORMAL, true);
            case EYEBALL_MONSTER -> new Skill("Tentacle Prod", 0, 5.5, 0.45, Elemental.NORMAL, false);
            case FLAME_GOLEM -> new Skill("Obsidian Fist Slam", 0, 8.0, 0.55, Elemental.NORMAL, false);
            case GHOSTFIRE -> new Skill("Wisp Ram", 0, 5.5, 0.45, Elemental.NORMAL, true);
            case HELLBAT -> new Skill("Cinder Swoop", 0, 5.5, 0.45, Elemental.NORMAL, false);
            case HELLHOUND -> new Skill("Feral Pounce", 0, 6.5, 0.48, Elemental.NORMAL, false);
            case LAVA_SLIME -> new Skill("Molten Glob", 0, 6.5, 0.48, Elemental.NORMAL, true);
            case MINOTAUR -> new Skill("Minotaur Axe Chop", 0, 7.5, 0.52, Elemental.NORMAL, false);
            case WARLOCK -> new Skill("Eldritch Staff Bolt", 0, 7.0, 0.50, Elemental.NORMAL, true);
        };
    }

    public static List<Skill> createSkillPool(MonsterId monsterId) {
        if (monsterId == null) {
            throw new IllegalArgumentException("monsterId must not be null.");
        }
        return switch (monsterId) {
            case SOLDIER -> createSoldierSkillPool();
            case ORC -> createOrcSkillPool();
            case ELITE_ORC -> createEliteOrcSkillPool();
            case ARMORED_ORC -> createArmoredOrcSkillPool();
            case SKELETON -> createSkeletonSkillPool();
            case ARMORED_SKELETON -> createArmoredSkeletonSkillPool();
            case GREATSWORD_SKELETON -> createGreatswordSkeletonSkillPool();
            case SKELETON_ARCHER -> createSkeletonArcherSkillPool();
            case SLIME -> createSlimeSkillPool();
            case BAT -> createBatSkillPool();
            case ORC_RIDER -> createOrcRiderSkillPool();
            case WEREBEAR -> createWerebearSkillPool();
            case WEREWOLF -> createWerewolfSkillPool();
            case BLACK_KNIGHT_A -> createBlackKnightASkillPool();
            case BLACK_KNIGHT_B -> createBlackKnightBSkillPool();
            case BLACK_KNIGHT_C -> createBlackKnightCSkillPool();
            case BLOOD_MONSTER_A -> createBloodMonsterASkillPool();
            case BLOOD_MONSTER_B -> createBloodMonsterBSkillPool();
            case DEMON_A -> createDemonASkillPool();
            case DEMON_B -> createDemonBSkillPool();
            case DEMON_C -> createDemonCSkillPool();
            case DEMON_D -> createDemonDSkillPool();
            case DEMON_E -> createDemonESkillPool();
            case DEMONESS_A -> createDemonessASkillPool();
            case DEMONESS_B -> createDemonessBSkillPool();
            case EYEBALL_MONSTER -> createEyeballMonsterSkillPool();
            case FLAME_GOLEM -> createFlameGolemSkillPool();
            case GHOSTFIRE -> createGhostfireSkillPool();
            case HELLBAT -> createHellbatSkillPool();
            case HELLHOUND -> createHellhoundSkillPool();
            case LAVA_SLIME -> createLavaSlimeSkillPool();
            case MINOTAUR -> createMinotaurSkillPool();
            case WARLOCK -> createWarlockSkillPool();
        };
    }

    public static Skill createBossUltimate(MonsterId monsterId) {
        if (monsterId == null) {
            throw new IllegalArgumentException("monsterId must not be null.");
        }
        return switch (monsterId) {
            case SOLDIER -> new Skill(
                    "Volley of Discipline", 18, 9.0, 1.20, Elemental.NORMAL, false,
                    new SkillMods().oncePerBattle().ultimateWhenSelfHpBelow(0.35).extraCritPercent(30.0));
            case ORC -> new Skill(
                    "Desperate Cleave", 16, 9.0, 1.25, Elemental.NORMAL, false,
                    new SkillMods().oncePerBattle().ultimateWhenSelfHpBelow(0.35).damageBonusWhenSelfHpBelow(0.30, 0.35));
            case ELITE_ORC -> new Skill(
                    "Berserker Bloodrage", 20, 10.0, 1.20, Elemental.NORMAL, false,
                    EffectCatalogue.createAttackBuff(3, 25), 100.0, false,
                    new SkillMods().oncePerBattle().ultimateWhenSelfHpBelow(0.40).lifeStealRatio(0.35));
            case ARMORED_ORC -> new Skill(
                    "Unbreakable Bastion", 18, 0.0, 0.0, Elemental.GROUND, false,
                    EffectCatalogue.createShield(3, 50), 100.0, true,
                    new SkillMods().oncePerBattle().ultimateWhenSelfHpBelow(0.40).cleanseSelf().hpRestoreRatio(0.15));
            case SKELETON -> new Skill(
                    "Undying Ossuary", 16, 0.0, 0.0, Elemental.DARK, false,
                    EffectCatalogue.createUndying(3, 25), 100.0, true,
                    new SkillMods().oncePerBattle().ultimateWhenSelfHpBelow(0.35));
            case ARMORED_SKELETON -> new Skill(
                    "Impaling Phalanx", 18, 9.0, 1.20, Elemental.DARK, false,
                    new SkillMods().oncePerBattle().ultimateWhenSelfHpBelow(0.35).detonateTargetEffect(EffectType.DEF_DOWN, 0.35));
            case GREATSWORD_SKELETON -> new Skill(
                    "Guillotine Arc", 22, 10.0, 1.25, Elemental.DARK, false,
                    new SkillMods().oncePerBattle().ultimateWhenTargetHpBelow(0.45).damageBonusWhenTargetHpBelow(0.40, 0.45));
            case SKELETON_ARCHER -> new Skill(
                    "Soul-Piercing Volley", 18, 9.0, 1.20, Elemental.DARK, false,
                    new SkillMods().oncePerBattle().ultimateWhenSelfHpBelow(0.40).damageBonusWhenTargetHpBelow(0.35, 0.45));
            case SLIME -> new Skill(
                    "Core Split", 14, 0.0, 0.0, Elemental.GROUND, false,
                    EffectCatalogue.createUndying(4, 30), 100.0, true,
                    new SkillMods().oncePerBattle().ultimateWhenSelfHpBelow(0.40));
            case BAT -> new Skill(
                    "Nightfang Frenzy", 16, 8.0, 1.15, Elemental.DARK, false,
                    new SkillMods().oncePerBattle().ultimateWhenSelfHpBelow(0.40).lifeStealRatio(0.45).extraCritPercent(25.0));
            case ORC_RIDER -> new Skill(
                    "Stampede Overrun", 24, 11.0, 1.25, Elemental.GROUND, false,
                    EffectCatalogue.createDefenseDown(3, 30), 100.0, false,
                    new SkillMods().oncePerBattle().ultimateWhenSelfHpBelow(0.45).crowdControlDamageBonus(0.35));
            case WEREBEAR -> new Skill(
                    "Ursine Hibernation Rage", 18, 9.0, 1.15, Elemental.GROUND, false,
                    EffectCatalogue.createAttackBuff(3, 25), 100.0, false,
                    new SkillMods().oncePerBattle().ultimateWhenSelfHpBelow(0.40).hpRestoreRatio(0.20));
            case WEREWOLF -> new Skill(
                    "Full Moon Predator", 18, 9.5, 1.25, Elemental.WIND, false,
                    new SkillMods()
                            .oncePerBattle()
                            .ultimateWhenTargetHpBelow(0.45)
                            .damageBonusWhenTargetHpBelow(0.40, 0.45)
                            .lifeStealRatio(0.30));
            case BLACK_KNIGHT_A -> new Skill(
                    "Cataclysmic Spikes", 26, 11.0, 1.25, Elemental.FIRE, true,
                    new SkillMods().oncePerBattle().ultimateWhenSelfHpBelow(0.40).detonateTargetEffect(EffectType.BURN, 0.40));
            case BLACK_KNIGHT_B -> new Skill(
                    "Point-Blank Bombardment", 20, 10.0, 1.25, Elemental.FIRE, false,
                    EffectCatalogue.createDefenseDown(3, 30), 100.0, false,
                    new SkillMods().oncePerBattle().ultimateWhenSelfHpBelow(0.40).shieldSynergyDamageBonus(0.35));
            case BLACK_KNIGHT_C -> new Skill(
                    "Crimson Pillar of Doom", 28, 12.0, 1.25, Elemental.DARK, true,
                    EffectCatalogue.createInstantDeath(), 10.0, false,
                    new SkillMods()
                            .oncePerBattle()
                            .ultimateWhenSelfHpBelow(0.40)
                            .ultimateWhenTargetHpBelow(0.50)
                            .instantDeathWhenTargetHpBelow(0.15));
            case BLOOD_MONSTER_A -> new Skill(
                    "Gorging Feast", 16, 8.5, 1.15, Elemental.DARK, false,
                    new SkillMods()
                            .oncePerBattle()
                            .ultimateWhenSelfHpBelow(0.40)
                            .detonateTargetEffect(EffectType.BLEED, 0.30)
                            .lifeStealRatio(0.45));
            case BLOOD_MONSTER_B -> new Skill(
                    "Hemorrhagic Burst", 16, 8.0, 1.15, Elemental.DARK, true,
                    new SkillMods().oncePerBattle().ultimateWhenSelfHpBelow(0.40).detonateTargetEffect(EffectType.BLEED, 0.35));
            case DEMON_A -> new Skill(
                    "Frenzied Flurry", 16, 8.5, 1.20, Elemental.FIRE, false,
                    new SkillMods().oncePerBattle().ultimateWhenSelfHpBelow(0.35).detonateTargetEffect(EffectType.BURN, 0.30));
            case DEMON_B -> new Skill(
                    "Deadeye Hellshot", 18, 9.5, 1.25, Elemental.FIRE, false,
                    new SkillMods().oncePerBattle().ultimateWhenSelfHpBelow(0.40).extraCritPercent(35.0));
            case DEMON_C -> new Skill(
                    "Hellfork Skewering", 20, 9.5, 1.25, Elemental.FIRE, false,
                    EffectCatalogue.createBurn(3, 9), 85.0, false,
                    new SkillMods().oncePerBattle().ultimateWhenSelfHpBelow(0.40).crowdControlDamageBonus(0.35));
            case DEMON_D -> new Skill(
                    "Eighteen-Fold Cataclysm", 26, 11.5, 1.25, Elemental.FIRE, false,
                    new SkillMods().oncePerBattle().ultimateWhenSelfHpBelow(0.40).damageBonusWhenSelfHpBelow(0.35, 0.40));
            case DEMON_E -> new Skill(
                    "Archfiend Cross Execution", 22, 10.5, 1.30, Elemental.WIND, false,
                    new SkillMods()
                            .oncePerBattle()
                            .ultimateWhenTargetHpBelow(0.45)
                            .damageBonusWhenTargetHpBelow(0.40, 0.45)
                            .extraCritPercent(25.0));
            case DEMONESS_A -> new Skill(
                    "Death's Final Toll", 24, 10.5, 1.20, Elemental.DARK, false,
                    EffectCatalogue.createInstantDeath(), 12.0, false,
                    new SkillMods()
                            .oncePerBattle()
                            .ultimateWhenSelfHpBelow(0.40)
                            .ultimateWhenTargetHpBelow(0.45)
                            .instantDeathWhenTargetHpBelow(0.15)
                            .lifeStealRatio(0.40));
            case DEMONESS_B -> new Skill(
                    "Dominion Scourge", 18, 9.0, 1.20, Elemental.DARK, true,
                    EffectCatalogue.createDefenseDown(3, 25), 95.0, false,
                    new SkillMods()
                            .oncePerBattle()
                            .ultimateWhenSelfHpBelow(0.40)
                            .crowdControlDamageBonus(0.35)
                            .lifeStealRatio(0.30));
            case EYEBALL_MONSTER -> new Skill(
                    "Omega Void Ray", 20, 10.0, 1.30, Elemental.DARK, true,
                    new SkillMods()
                            .oncePerBattle()
                            .ultimateWhenSelfHpBelow(0.40)
                            .detonateTargetEffect(EffectType.DEF_DOWN, 0.40)
                            .extraCritPercent(25.0));
            case FLAME_GOLEM -> new Skill(
                    "Volcanic Core Meltdown", 28, 12.0, 1.25, Elemental.FIRE, true,
                    EffectCatalogue.createDefenseDown(3, 30), 100.0, false,
                    new SkillMods().oncePerBattle().ultimateWhenSelfHpBelow(0.40).detonateTargetEffect(EffectType.BURN, 0.40));
            case GHOSTFIRE -> new Skill(
                    "Cremation Flare", 18, 9.5, 1.25, Elemental.FIRE, true,
                    new SkillMods().oncePerBattle().ultimateWhenSelfHpBelow(0.40).detonateTargetEffect(EffectType.BURN, 0.40));
            case HELLBAT -> new Skill(
                    "Phoenix Wing Dive", 16, 8.5, 1.20, Elemental.FIRE, true,
                    new SkillMods().oncePerBattle().ultimateWhenSelfHpBelow(0.40).hpRestoreRatio(0.15));
            case HELLHOUND -> new Skill(
                    "Cerberus Jaws", 18, 9.5, 1.25, Elemental.FIRE, false,
                    new SkillMods()
                            .oncePerBattle()
                            .ultimateWhenTargetHpBelow(0.45)
                            .damageBonusWhenTargetHpBelow(0.40, 0.45)
                            .lifeStealRatio(0.30));
            case LAVA_SLIME -> new Skill(
                    "Pyroclastic Rebirth", 16, 0.0, 0.0, Elemental.FIRE, false,
                    EffectCatalogue.createUndying(4, 30), 100.0, true,
                    new SkillMods().oncePerBattle().ultimateWhenSelfHpBelow(0.40));
            case MINOTAUR -> new Skill(
                    "Unstoppable Rampage", 22, 10.5, 1.30, Elemental.GROUND, false,
                    EffectCatalogue.createAttackBuff(3, 25), 100.0, false,
                    new SkillMods().oncePerBattle().ultimateWhenSelfHpBelow(0.40).damageBonusWhenSelfHpBelow(0.35, 0.40));
            case WARLOCK -> new Skill(
                    "Cataclysmic Conflagration", 24, 11.0, 1.25, Elemental.FIRE, true,
                    new SkillMods().oncePerBattle().ultimateWhenSelfHpBelow(0.45).detonateTargetEffect(EffectType.BURN, 0.40));
        };
    }

    public static List<Skill> createSoldierSkillPool() {
        List<Skill> pool = new ArrayList<>();
        pool.add(new Skill(
                "Pinning Arrow", 10, 5.0, 0.55, Elemental.NORMAL, false,
                EffectCatalogue.createFrost(2, 6), 75.0, false));
        pool.add(new Skill(
                "Spotter Shot", 12, 5.0, 0.55, Elemental.NORMAL, false,
                EffectCatalogue.createDefenseDown(2, 20), 90.0, false));
        pool.add(new Skill(
                "Overhead Cleave", 14, 7.0, 0.75, Elemental.NORMAL, false,
                new SkillMods().crowdControlDamageBonus(0.30)));
        return pool;
    }

    public static List<Skill> createOrcSkillPool() {
        List<Skill> pool = new ArrayList<>();
        pool.add(new Skill(
                "Jagged Hack", 10, 6.0, 0.65, Elemental.NORMAL, false,
                EffectCatalogue.createBleed(2, 6), 75.0, false));
        pool.add(new Skill(
                "Sand Kick", 8, 4.0, 0.50, Elemental.GROUND, false,
                EffectCatalogue.createDefenseDown(2, 20), 85.0, false));
        pool.add(new Skill(
                "Raider Catch Breath", 8, 0.0, 0.0, Elemental.NORMAL, false,
                null, 0.0, true,
                new SkillMods().staminaRestoreAmount(12).hpRestoreRatio(0.08)));
        return pool;
    }

    public static List<Skill> createEliteOrcSkillPool() {
        List<Skill> pool = new ArrayList<>();
        pool.add(new Skill(
                "Crimson Whirlwind", 14, 8.0, 0.80, Elemental.NORMAL, false,
                EffectCatalogue.createBleed(3, 9), 80.0, false));
        pool.add(new Skill(
                "Skullsplitter Leap", 18, 9.0, 0.95, Elemental.NORMAL, false,
                new SkillMods().detonateTargetEffect(EffectType.BLEED, 0.40)));
        pool.add(new Skill(
                "Warhorn Howl", 12, 0.0, 0.0, Elemental.NORMAL, false,
                EffectCatalogue.createAttackBuff(3, 25), 100.0, true));
        return pool;
    }

    public static List<Skill> createArmoredOrcSkillPool() {
        List<Skill> pool = new ArrayList<>();
        pool.add(new Skill(
                "Iron Rim Guard", 12, 0.0, 0.0, Elemental.GROUND, false,
                EffectCatalogue.createShield(2, 34), 100.0, true));
        pool.add(new Skill(
                "Bulwark Spin Slash", 16, 7.0, 0.75, Elemental.GROUND, false,
                new SkillMods().shieldSynergyDamageBonus(0.35)));
        pool.add(new Skill(
                "Shielded Lunge", 14, 6.0, 0.65, Elemental.NORMAL, false,
                EffectCatalogue.createParalyze(1), 35.0, false));
        return pool;
    }

    public static List<Skill> createSkeletonSkillPool() {
        List<Skill> pool = new ArrayList<>();
        pool.add(new Skill(
                "Buckler Guard", 10, 0.0, 0.0, Elemental.DARK, false,
                EffectCatalogue.createShield(2, 24), 100.0, true));
        pool.add(new Skill(
                "Grave Chill Sweep", 12, 5.0, 0.60, Elemental.DARK, false,
                EffectCatalogue.createFrost(2, 8), 75.0, false));
        pool.add(new Skill(
                "Bone Reassembly", 12, 0.0, 0.0, Elemental.DARK, false,
                null, 0.0, true,
                new SkillMods().hpRestoreRatio(0.08).staminaRestoreAmount(10)));
        return pool;
    }

    public static List<Skill> createArmoredSkeletonSkillPool() {
        List<Skill> pool = new ArrayList<>();
        pool.add(new Skill(
                "Armor-Piercing Thrust", 12, 7.0, 0.70, Elemental.NORMAL, false,
                EffectCatalogue.createDefenseDown(3, 25), 95.0, false));
        pool.add(new Skill(
                "Crescent Poleaxe Sweep", 14, 7.5, 0.80, Elemental.DARK, false,
                EffectCatalogue.createBleed(2, 8), 75.0, false));
        pool.add(new Skill(
                "Necrotic Temper", 14, 0.0, 0.0, Elemental.DARK, false,
                EffectCatalogue.createAttackBuff(2, 20), 100.0, true));
        return pool;
    }

    public static List<Skill> createGreatswordSkeletonSkillPool() {
        List<Skill> pool = new ArrayList<>();
        pool.add(new Skill(
                "Spinning Zweihander", 16, 8.5, 0.90, Elemental.NORMAL, false,
                new SkillMods().extraCritPercent(25.0)));
        pool.add(new Skill(
                "Sundering Lunge", 14, 7.0, 0.75, Elemental.DARK, false,
                EffectCatalogue.createDefenseDown(2, 25), 90.0, false));
        pool.add(new Skill(
                "Cursed Edge", 14, 0.0, 0.0, Elemental.DARK, false,
                EffectCatalogue.createAttackBuff(2, 25), 100.0, true));
        return pool;
    }

    public static List<Skill> createSkeletonArcherSkillPool() {
        List<Skill> pool = new ArrayList<>();
        pool.add(new Skill(
                "Shadow Arrow", 10, 6.5, 0.65, Elemental.DARK, false,
                new SkillMods().extraCritPercent(20.0)));
        pool.add(new Skill(
                "Barbed Marrow Shot", 12, 5.5, 0.55, Elemental.NORMAL, false,
                EffectCatalogue.createBleed(3, 6), 80.0, false));
        pool.add(new Skill(
                "Gravechill Arrow", 10, 5.0, 0.50, Elemental.DARK, false,
                EffectCatalogue.createFrost(2, 8), 80.0, false));
        return pool;
    }

    public static List<Skill> createSlimeSkillPool() {
        List<Skill> pool = new ArrayList<>();
        pool.add(new Skill(
                "Acid Spit", 8, 5.0, 0.55, Elemental.GROUND, true,
                EffectCatalogue.createPoison(3, 7), 85.0, false));
        pool.add(new Skill(
                "Mucus Shield", 10, 0.0, 0.0, Elemental.GROUND, false,
                EffectCatalogue.createShield(2, 28), 100.0, true));
        pool.add(new Skill(
                "Crescent Engulf", 12, 6.0, 0.65, Elemental.GROUND, true,
                EffectCatalogue.createDefenseDown(3, 20), 90.0, false));
        return pool;
    }

    public static List<Skill> createBatSkillPool() {
        List<Skill> pool = new ArrayList<>();
        pool.add(new Skill(
                "Vampiric Nip", 10, 6.0, 0.65, Elemental.DARK, false,
                new SkillMods().lifeStealRatio(0.35)));
        pool.add(new Skill(
                "Wing Gust", 10, 5.0, 0.55, Elemental.WIND, false,
                EffectCatalogue.createFrost(2, 8), 80.0, false));
        pool.add(new Skill(
                "Screech Dive", 12, 6.0, 0.65, Elemental.WIND, false,
                EffectCatalogue.createDefenseDown(2, 20), 85.0, false));
        return pool;
    }

    public static List<Skill> createOrcRiderSkillPool() {
        List<Skill> pool = new ArrayList<>();
        pool.add(new Skill(
                "Tusk Trample", 14, 8.0, 0.80, Elemental.GROUND, false,
                EffectCatalogue.createParalyze(1), 40.0, false));
        pool.add(new Skill(
                "Boarhide Shield", 14, 0.0, 0.0, Elemental.GROUND, false,
                EffectCatalogue.createShield(2, 38), 100.0, true,
                new SkillMods().staminaRestoreAmount(10)));
        pool.add(new Skill(
                "Crushing Charge", 16, 8.5, 0.85, Elemental.GROUND, false,
                EffectCatalogue.createDefenseDown(2, 25), 90.0, false));
        return pool;
    }

    public static List<Skill> createWerebearSkillPool() {
        List<Skill> pool = new ArrayList<>();
        pool.add(new Skill(
                "Savage Maul", 12, 7.0, 0.75, Elemental.NORMAL, false,
                EffectCatalogue.createBleed(3, 8), 85.0, false));
        pool.add(new Skill(
                "Cross-Claw Rend", 16, 8.0, 0.85, Elemental.GROUND, false,
                new SkillMods().detonateTargetEffect(EffectType.BLEED, 0.35)));
        pool.add(new Skill(
                "Ground Pounce", 14, 7.0, 0.70, Elemental.GROUND, false,
                EffectCatalogue.createDefenseDown(2, 20), 85.0, false));
        return pool;
    }

    public static List<Skill> createWerewolfSkillPool() {
        List<Skill> pool = new ArrayList<>();
        pool.add(new Skill(
                "Flurry Leap", 12, 7.5, 0.80, Elemental.WIND, false,
                new SkillMods().extraCritPercent(25.0)));
        pool.add(new Skill(
                "Bloodthirsty Bite", 14, 7.0, 0.75, Elemental.NORMAL, false,
                new SkillMods().lifeStealRatio(0.35)));
        pool.add(new Skill(
                "Hamstring Swipe", 12, 6.0, 0.65, Elemental.WIND, false,
                EffectCatalogue.createFrost(2, 10), 80.0, false));
        return pool;
    }

    public static List<Skill> createBlackKnightASkillPool() {
        List<Skill> pool = new ArrayList<>();
        pool.add(new Skill(
                "Hellfire Sweep", 16, 8.5, 0.80, Elemental.FIRE, false,
                EffectCatalogue.createBurn(3, 10), 85.0, false));
        pool.add(new Skill(
                "Obsidian Spike Eruption", 18, 9.0, 0.90, Elemental.GROUND, true,
                EffectCatalogue.createDefenseDown(3, 25), 90.0, false));
        pool.add(new Skill(
                "Crimson Impalement", 14, 8.0, 0.80, Elemental.FIRE, false,
                new SkillMods().detonateTargetEffect(EffectType.BURN, 0.35)));
        return pool;
    }

    public static List<Skill> createBlackKnightBSkillPool() {
        List<Skill> pool = new ArrayList<>();
        pool.add(new Skill(
                "Tower Shield Barricade", 14, 0.0, 0.0, Elemental.DARK, false,
                EffectCatalogue.createShield(2, 42), 100.0, true));
        pool.add(new Skill(
                "Siege Cannon Shot", 16, 8.0, 0.85, Elemental.FIRE, false,
                new SkillMods().shieldSynergyDamageBonus(0.35)));
        pool.add(new Skill(
                "Fortress Shield Slam", 14, 7.0, 0.70, Elemental.GROUND, false,
                EffectCatalogue.createParalyze(1), 40.0, false));
        return pool;
    }

    public static List<Skill> createBlackKnightCSkillPool() {
        List<Skill> pool = new ArrayList<>();
        pool.add(new Skill(
                "Abyssal Vortex Cleave", 16, 8.5, 0.85, Elemental.DARK, false,
                EffectCatalogue.createBleed(3, 10), 80.0, false,
                new SkillMods().lifeStealRatio(0.25)));
        pool.add(new Skill(
                "Dreadsteed Charge", 18, 8.0, 0.80, Elemental.DARK, false,
                EffectCatalogue.createDefenseDown(3, 25), 95.0, false));
        pool.add(new Skill(
                "Dark Sovereign Aura", 16, 0.0, 0.0, Elemental.DARK, false,
                EffectCatalogue.createShield(2, 40), 100.0, true));
        return pool;
    }

    public static List<Skill> createBloodMonsterASkillPool() {
        List<Skill> pool = new ArrayList<>();
        pool.add(new Skill(
                "Ravenous Maw Bite", 10, 6.5, 0.65, Elemental.DARK, false,
                new SkillMods().lifeStealRatio(0.35)));
        pool.add(new Skill(
                "Arterial Lash", 10, 5.5, 0.60, Elemental.NORMAL, false,
                EffectCatalogue.createBleed(3, 6), 80.0, false));
        pool.add(new Skill(
                "Flesh Coagulation", 12, 0.0, 0.0, Elemental.DARK, false,
                null, 0.0, true,
                new SkillMods().hpRestoreRatio(0.15)));
        return pool;
    }

    public static List<Skill> createBloodMonsterBSkillPool() {
        List<Skill> pool = new ArrayList<>();
        pool.add(new Skill(
                "Bloodwave Spit", 10, 6.0, 0.65, Elemental.DARK, true,
                EffectCatalogue.createBleed(2, 6), 80.0, false));
        pool.add(new Skill(
                "Siphon Pulse", 12, 6.0, 0.65, Elemental.DARK, true,
                new SkillMods().lifeStealRatio(0.35)));
        pool.add(new Skill(
                "Vein Chill", 10, 5.0, 0.55, Elemental.DARK, true,
                EffectCatalogue.createFrost(2, 8), 75.0, false));
        return pool;
    }

    public static List<Skill> createDemonASkillPool() {
        List<Skill> pool = new ArrayList<>();
        pool.add(new Skill(
                "Searing Cut", 10, 6.0, 0.65, Elemental.FIRE, false,
                EffectCatalogue.createBurn(2, 6), 80.0, false));
        pool.add(new Skill(
                "Cruel Backslash", 12, 6.5, 0.70, Elemental.NORMAL, false,
                new SkillMods().damageBonusWhenTargetHpBelow(0.30, 0.35)));
        pool.add(new Skill(
                "Impish Taunt", 8, 0.0, 0.0, Elemental.FIRE, false,
                EffectCatalogue.createAttackBuff(2, 18), 100.0, true));
        return pool;
    }

    public static List<Skill> createDemonBSkillPool() {
        List<Skill> pool = new ArrayList<>();
        pool.add(new Skill(
                "Hellfire Arrow", 12, 6.5, 0.70, Elemental.FIRE, false,
                EffectCatalogue.createBurn(3, 8), 85.0, false));
        pool.add(new Skill(
                "Explosive Bolt", 14, 7.5, 0.80, Elemental.FIRE, false,
                new SkillMods().detonateTargetEffect(EffectType.BURN, 0.35)));
        pool.add(new Skill(
                "Armor-Splitting Shot", 12, 6.0, 0.65, Elemental.NORMAL, false,
                EffectCatalogue.createDefenseDown(2, 22), 90.0, false));
        return pool;
    }

    public static List<Skill> createDemonCSkillPool() {
        List<Skill> pool = new ArrayList<>();
        pool.add(new Skill(
                "Pinning Prongs", 14, 6.5, 0.70, Elemental.NORMAL, false,
                EffectCatalogue.createParalyze(1), 35.0, false));
        pool.add(new Skill(
                "Crimson Arc Sweep", 14, 7.0, 0.75, Elemental.FIRE, true,
                EffectCatalogue.createFrost(2, 10), 80.0, false));
        pool.add(new Skill(
                "Molten Trident Thrust", 16, 7.5, 0.80, Elemental.FIRE, false,
                new SkillMods().crowdControlDamageBonus(0.35)));
        return pool;
    }

    public static List<Skill> createDemonDSkillPool() {
        List<Skill> pool = new ArrayList<>();
        pool.add(new Skill(
                "Armor-Shattering Tremor", 16, 8.5, 0.80, Elemental.GROUND, false,
                EffectCatalogue.createDefenseDown(3, 30), 95.0, false));
        pool.add(new Skill(
                "Hellfire Groundbreaker", 18, 9.0, 0.85, Elemental.FIRE, false,
                EffectCatalogue.createBurn(3, 10), 85.0, false));
        pool.add(new Skill(
                "Concussive Slam", 16, 7.5, 0.75, Elemental.GROUND, false,
                EffectCatalogue.createParalyze(1), 35.0, false));
        return pool;
    }

    public static List<Skill> createDemonESkillPool() {
        List<Skill> pool = new ArrayList<>();
        pool.add(new Skill(
                "Crescent Wind Cleave", 14, 8.0, 0.80, Elemental.WIND, false,
                EffectCatalogue.createBleed(3, 9), 80.0, false));
        pool.add(new Skill(
                "Sky-Splitting X-Slash", 16, 8.5, 0.90, Elemental.WIND, false,
                new SkillMods().extraCritPercent(30.0)));
        pool.add(new Skill(
                "Gale Stance", 12, 0.0, 0.0, Elemental.WIND, false,
                EffectCatalogue.createAttackBuff(2, 22), 100.0, true));
        return pool;
    }

    public static List<Skill> createDemonessASkillPool() {
        List<Skill> pool = new ArrayList<>();
        pool.add(new Skill(
                "Crimson Crescent Harvest", 14, 8.0, 0.80, Elemental.DARK, false,
                EffectCatalogue.createBleed(3, 10), 85.0, false));
        pool.add(new Skill(
                "Soul-Siphoning Guillotine", 16, 8.5, 0.85, Elemental.DARK, false,
                new SkillMods().lifeStealRatio(0.35)));
        pool.add(new Skill(
                "Blood Vortex Spin", 16, 8.5, 0.85, Elemental.DARK, false,
                new SkillMods().detonateTargetEffect(EffectType.BLEED, 0.35)));
        return pool;
    }

    public static List<Skill> createDemonessBSkillPool() {
        List<Skill> pool = new ArrayList<>();
        pool.add(new Skill(
                "Entangling Lash", 12, 6.0, 0.65, Elemental.DARK, true,
                EffectCatalogue.createFrost(2, 12), 85.0, false));
        pool.add(new Skill(
                "Nerve-Stinging Crack", 14, 6.5, 0.70, Elemental.DARK, true,
                EffectCatalogue.createParalyze(1), 35.0, false));
        pool.add(new Skill(
                "Tormenting Flurry", 14, 7.0, 0.75, Elemental.DARK, true,
                new SkillMods().crowdControlDamageBonus(0.35)));
        return pool;
    }

    public static List<Skill> createEyeballMonsterSkillPool() {
        List<Skill> pool = new ArrayList<>();
        pool.add(new Skill(
                "Evil Eye Gaze", 12, 6.0, 0.65, Elemental.DARK, true,
                EffectCatalogue.createDefenseDown(3, 25), 95.0, false));
        pool.add(new Skill(
                "Ocular Disintegration Beam", 16, 8.0, 0.85, Elemental.DARK, true,
                new SkillMods().detonateTargetEffect(EffectType.DEF_DOWN, 0.35)));
        pool.add(new Skill(
                "Petrifying Glare", 12, 5.5, 0.60, Elemental.DARK, true,
                EffectCatalogue.createParalyze(1), 35.0, false));
        return pool;
    }

    public static List<Skill> createFlameGolemSkillPool() {
        List<Skill> pool = new ArrayList<>();
        pool.add(new Skill(
                "Magma Vent Eruption", 18, 9.0, 0.90, Elemental.FIRE, true,
                EffectCatalogue.createBurn(3, 12), 85.0, false));
        pool.add(new Skill(
                "Seismic Rock Spikes", 14, 7.5, 0.75, Elemental.GROUND, false,
                EffectCatalogue.createParalyze(1), 35.0, false));
        pool.add(new Skill(
                "Molten Carapace", 14, 0.0, 0.0, Elemental.FIRE, false,
                EffectCatalogue.createShield(2, 42), 100.0, true));
        return pool;
    }

    public static List<Skill> createGhostfireSkillPool() {
        List<Skill> pool = new ArrayList<>();
        pool.add(new Skill(
                "Soulflame Sear", 12, 6.5, 0.70, Elemental.FIRE, true,
                EffectCatalogue.createBurn(3, 9), 85.0, false));
        pool.add(new Skill(
                "Ghostfire Scorch Ray", 16, 8.0, 0.85, Elemental.FIRE, true,
                new SkillMods().detonateTargetEffect(EffectType.BURN, 0.35)));
        pool.add(new Skill(
                "Flickering Shroud", 10, 0.0, 0.0, Elemental.FIRE, false,
                EffectCatalogue.createShield(2, 22), 100.0, true));
        return pool;
    }

    public static List<Skill> createHellbatSkillPool() {
        List<Skill> pool = new ArrayList<>();
        pool.add(new Skill(
                "Blazing Wing Nova", 12, 6.5, 0.65, Elemental.FIRE, true,
                EffectCatalogue.createBurn(2, 7), 80.0, false));
        pool.add(new Skill(
                "Scorching Fang", 10, 6.0, 0.65, Elemental.FIRE, false,
                new SkillMods().detonateTargetEffect(EffectType.BURN, 0.30)));
        pool.add(new Skill(
                "Thermal Updraft", 10, 0.0, 0.0, Elemental.FIRE, false,
                EffectCatalogue.createAttackBuff(2, 18), 100.0, true));
        return pool;
    }

    public static List<Skill> createHellhoundSkillPool() {
        List<Skill> pool = new ArrayList<>();
        pool.add(new Skill(
                "Hellfang Crunch", 12, 7.5, 0.75, Elemental.FIRE, false,
                EffectCatalogue.createDefenseDown(2, 25), 90.0, false));
        pool.add(new Skill(
                "Flame-Mane Tear", 14, 7.0, 0.75, Elemental.FIRE, false,
                EffectCatalogue.createBurn(2, 8), 80.0, false));
        pool.add(new Skill(
                "Bloodhound Pursuit", 14, 7.5, 0.80, Elemental.NORMAL, false,
                new SkillMods().damageBonusWhenTargetHpBelow(0.35, 0.45)));
        return pool;
    }

    public static List<Skill> createLavaSlimeSkillPool() {
        List<Skill> pool = new ArrayList<>();
        pool.add(new Skill(
                "Magma Spear", 10, 6.5, 0.65, Elemental.FIRE, true,
                EffectCatalogue.createBurn(3, 8), 85.0, false));
        pool.add(new Skill(
                "Volcanic Rock Spikes", 14, 7.5, 0.80, Elemental.GROUND, true,
                new SkillMods().detonateTargetEffect(EffectType.BURN, 0.35)));
        pool.add(new Skill(
                "Basalt Crust Shield", 12, 0.0, 0.0, Elemental.FIRE, false,
                EffectCatalogue.createShield(2, 32), 100.0, true));
        return pool;
    }

    public static List<Skill> createMinotaurSkillPool() {
        List<Skill> pool = new ArrayList<>();
        pool.add(new Skill(
                "Bull Horn Gore", 14, 7.5, 0.75, Elemental.GROUND, false,
                EffectCatalogue.createParalyze(1), 40.0, false));
        pool.add(new Skill(
                "Labyrinth Guillotine", 18, 9.0, 0.95, Elemental.GROUND, false,
                new SkillMods().crowdControlDamageBonus(0.40)));
        pool.add(new Skill(
                "Earth-Stomping Snort", 12, 6.5, 0.65, Elemental.GROUND, false,
                EffectCatalogue.createFrost(2, 12), 85.0, false));
        return pool;
    }

    public static List<Skill> createWarlockSkillPool() {
        List<Skill> pool = new ArrayList<>();
        pool.add(new Skill(
                "Pillar of Hellfire", 16, 8.5, 0.85, Elemental.FIRE, true,
                EffectCatalogue.createBurn(3, 10), 85.0, false));
        pool.add(new Skill(
                "Hex of Frailty", 14, 6.5, 0.65, Elemental.DARK, true,
                EffectCatalogue.createDefenseDown(3, 30), 95.0, false));
        pool.add(new Skill(
                "Soul Pact Drain", 16, 7.5, 0.80, Elemental.DARK, true,
                new SkillMods().lifeStealRatio(0.35)));
        return pool;
    }
}
