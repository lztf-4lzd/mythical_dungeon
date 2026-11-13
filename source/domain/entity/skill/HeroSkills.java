package source.domain.entity.skill;

import source.domain.entity.effect.EffectCatalogue;
import source.domain.entity.effect.EffectType;
import source.domain.entity.elemental.Elemental;
import source.domain.entity.hero.HeroId;

import java.util.ArrayList;
import java.util.List;

public final class HeroSkills {
    private HeroSkills() {}

    public static Skill createBasicAttack(HeroId heroId) {
        if (heroId == null) {
            throw new IllegalArgumentException("heroId must not be null.");
        }
        return switch (heroId) {
            case WARRIOR -> createWarriorBasicAttack();
            case MAGE -> createMageBasicAttack();
            case PALADIN -> createPaladinBasicAttack();
            case ARCHER -> createArcherBasicAttack();
        };
    }

    public static List<Skill> createSkillPool(HeroId heroId) {
        if (heroId == null) {
            throw new IllegalArgumentException("heroId must not be null.");
        }
        return switch (heroId) {
            case WARRIOR -> createWarriorSkillPool();
            case MAGE -> createMageSkillPool();
            case PALADIN -> createPaladinSkillPool();
            case ARCHER -> createArcherSkillPool();
        };
    }

    public static Skill createWarriorBasicAttack() {
        return new Skill(
                "Slash", 0, 10.0, 0.60, Elemental.NORMAL, false,
                new SkillMods().staminaRestoreAmount(5));
    }

    public static Skill createMageBasicAttack() {
        return new Skill(
                "Staff Strike", 0, 12.0, 0.65, Elemental.NORMAL, true,
                new SkillMods().staminaRestoreAmount(5));
    }

    public static Skill createPaladinBasicAttack() {
        return new Skill(
                "Mace Strike", 0, 12.0, 0.65, Elemental.NORMAL, false,
                new SkillMods().staminaRestoreAmount(5));
    }

    public static Skill createArcherBasicAttack() {
        return new Skill(
                "Quick Shot", 0, 12.0, 0.65, Elemental.NORMAL, false,
                new SkillMods().staminaRestoreAmount(5));
    }

    public static List<Skill> createWarriorSkillPool() {
        List<Skill> pool = new ArrayList<>();

        pool.add(new Skill(
                "Rending Slash", 8, 14.0, 0.77, Elemental.NORMAL, false,
                EffectCatalogue.createBleed(3, 8), 80.0, false));
        pool.add(new Skill(
                "Earthshaker", 12, 18.5, 0.81, Elemental.GROUND, false,
                EffectCatalogue.createDefenseDown(3, 20), 75.0, false));
        pool.add(new Skill(
                "Flame Cleave", 13, 19.3, 0.835, Elemental.FIRE, false,
                EffectCatalogue.createBurn(3, 8), 70.0, false));
        pool.add(new Skill(
                "Thunderous Bash", 14, 18.0, 0.85, Elemental.WIND, false,
                EffectCatalogue.createParalyze(1), 65.0, false,
                new SkillMods().crowdControlDamageBonus(0.35)));
        pool.add(new Skill(
                "Iron Bulwark", 10, 0.0, 0.0, Elemental.GROUND, false,
                EffectCatalogue.createShield(3, 45), 100.0, true,
                new SkillMods().hpRestoreRatio(0.15)));
        pool.add(new Skill(
                "Battle Cry", 8, 0.0, 0.0, Elemental.NORMAL, false,
                EffectCatalogue.createAttackBuff(3, 30), 100.0, true,
                new SkillMods().staminaRestoreAmount(25).cleanseSelf()));
        pool.add(new Skill(
                "Bloodthirst Strike", 14, 22.0, 1.00, Elemental.DARK, false,
                new SkillMods()
                        .selfSacrificeRatio(0.10)
                        .lifeStealRatio(0.35)
                        .detonateTargetEffect(EffectType.BLEED, 0.45)));
        pool.add(new Skill(
                "Unstoppable Maul", 16, 26.0, 1.10, Elemental.GROUND, false,
                EffectCatalogue.createWeaken(2, 20), 75.0, false,
                new SkillMods()
                        .shieldSynergyDamageBonus(0.30)
                        .damageBonusWhenSelfHpBelow(0.45, 0.35)));
        pool.add(new Skill(
                "Execute", 16, 34.0, 1.25, Elemental.DARK, false,
                EffectCatalogue.createBleed(3, 8), 100.0, false,
                new SkillMods()
                        .oncePerBattle()
                        .damageBonusWhenTargetHpBelow(0.38, 0.38)
                        .extraCritPercent(12.0)));
        pool.add(new Skill(
                "Cataclysmic Sundering", 20, 48.0, 1.50, Elemental.FIRE, false,
                EffectCatalogue.createDefenseDown(3, 35), 100.0, false,
                new SkillMods()
                        .oncePerBattle()
                        .lifeStealRatio(0.30)
                        .detonateTargetEffect(EffectType.BURN, 0.50)));

        return pool;
    }

    public static List<Skill> createMageSkillPool() {
        List<Skill> pool = new ArrayList<>();

        pool.add(new Skill(
                "Fireball", 10, 18.0, 0.84, Elemental.FIRE, true,
                EffectCatalogue.createBurn(3, 9), 80.0, false));
        pool.add(new Skill(
                "Frost Bolt", 10, 16.0, 0.82, Elemental.WATER, true,
                EffectCatalogue.createFrost(3, 25), 85.0, false));
        pool.add(new Skill(
                "Gale Burst", 12, 18.0, 0.82, Elemental.WIND, true,
                EffectCatalogue.createWeaken(2, 25), 80.0, false,
                new SkillMods().extraCritPercent(15.0)));
        pool.add(new Skill(
                "Glacial Prison", 14, 20.0, 0.85, Elemental.WATER, true,
                EffectCatalogue.createParalyze(1), 65.0, false,
                new SkillMods().crowdControlDamageBonus(0.40)));
        pool.add(new Skill(
                "Mana Barrier", 10, 0.0, 0.0, Elemental.WATER, true,
                EffectCatalogue.createShield(3, 50), 100.0, true,
                new SkillMods().cleanseSelf().hpRestoreRatio(0.15)));
        pool.add(new Skill(
                "Arcane Meditation", 8, 0.0, 0.0, Elemental.NORMAL, true,
                EffectCatalogue.createAttackBuff(3, 30), 100.0, true,
                new SkillMods().staminaRestoreAmount(30)));
        pool.add(new Skill(
                "Combustion Surge", 14, 26.0, 1.05, Elemental.FIRE, true,
                new SkillMods()
                        .detonateTargetEffect(EffectType.BURN, 0.50)
                        .extraCritPercent(20.0)));
        pool.add(new Skill(
                "Void Drain", 14, 24.0, 1.00, Elemental.DARK, true,
                EffectCatalogue.createDefenseDown(2, 25), 75.0, false,
                new SkillMods().lifeStealRatio(0.35)));
        pool.add(new Skill(
                "Meteor Swarm", 20, 42.0, 1.30, Elemental.FIRE, true,
                EffectCatalogue.createBurn(3, 10), 100.0, false,
                new SkillMods()
                        .oncePerBattle()
                        .detonateTargetEffect(EffectType.BURN, 0.38)
                        .extraCritPercent(12.0)));
        pool.add(new Skill(
                "Absolute Zero", 20, 46.0, 1.45, Elemental.WATER, true,
                EffectCatalogue.createParalyze(1), 100.0, false,
                new SkillMods()
                        .oncePerBattle()
                        .crowdControlDamageBonus(0.50)
                        .lifeStealRatio(0.25)));

        return pool;
    }

    public static List<Skill> createPaladinSkillPool() {
        List<Skill> pool = new ArrayList<>();

        pool.add(new Skill(
                "Holy Smite", 10, 18.0, 1.04, Elemental.HOLY, false,
                EffectCatalogue.createWeaken(2, 20), 80.0, false));
        pool.add(new Skill(
                "Radiant Brand", 10, 21.0, 0.92, Elemental.HOLY, true,
                EffectCatalogue.createBurn(3, 11), 80.0, false));
        pool.add(new Skill(
                "Consecrated Hammer", 12, 22.0, 0.95, Elemental.GROUND, false,
                EffectCatalogue.createDefenseDown(3, 25), 80.0, false));
        pool.add(new Skill(
                "Shield of Dawn", 10, 0.0, 0.0, Elemental.HOLY, false,
                EffectCatalogue.createShield(3, 58), 100.0, true,
                new SkillMods().cleanseSelf()));
        pool.add(new Skill(
                "Divine Grace", 12, 0.0, 0.0, Elemental.HOLY, false,
                EffectCatalogue.createDefenseBuff(3, 30), 100.0, true,
                new SkillMods()
                        .hpRestoreRatio(0.30)
                        .staminaRestoreAmount(20)));
        pool.add(new Skill(
                "Aegis Bash", 14, 22.0, 0.95, Elemental.HOLY, false,
                EffectCatalogue.createParalyze(1), 65.0, false,
                new SkillMods().shieldSynergyDamageBonus(0.45)));
        pool.add(new Skill(
                "Vow of Retribution", 14, 24.0, 1.00, Elemental.FIRE, true,
                new SkillMods()
                        .detonateTargetEffect(EffectType.BURN, 0.45)
                        .lifeStealRatio(0.30)));
        pool.add(new Skill(
                "Crusader's Verdict", 16, 26.0, 1.10, Elemental.HOLY, false,
                new SkillMods()
                        .shieldSynergyDamageBonus(0.35)
                        .damageBonusWhenSelfHpBelow(0.45, 0.40)));
        pool.add(new Skill(
                "Judgment of Light", 20, 50.0, 1.50, Elemental.HOLY, true,
                EffectCatalogue.createParalyze(1), 85.0, false,
                new SkillMods()
                        .oncePerBattle()
                        .damageBonusWhenTargetHpBelow(0.40, 0.55)
                        .hpRestoreRatio(0.25)));
        pool.add(new Skill(
                "Immortal Bastion", 18, 40.0, 1.35, Elemental.HOLY, false,
                EffectCatalogue.createShield(3, 75), 100.0, false,
                new SkillMods()
                        .oncePerBattle()
                        .cleanseSelf()
                        .hpRestoreRatio(0.35)));

        return pool;
    }

    public static List<Skill> createArcherSkillPool() {
        List<Skill> pool = new ArrayList<>();

        pool.add(new Skill(
                "Venom Arrow", 8, 16.0, 0.95, Elemental.DARK, false,
                EffectCatalogue.createPoison(3, 11), 85.0, false));
        pool.add(new Skill(
                "Piercing Gale", 10, 20.0, 1.05, Elemental.WIND, false,
                EffectCatalogue.createDefenseDown(3, 25), 80.0, false,
                new SkillMods().extraCritPercent(15.0)));
        pool.add(new Skill(
                "Frostbite Shot", 10, 18.0, 0.90, Elemental.WATER, false,
                EffectCatalogue.createFrost(3, 25), 85.0, false));
        pool.add(new Skill(
                "Pinning Volley", 14, 20.0, 0.90, Elemental.GROUND, false,
                EffectCatalogue.createParalyze(1), 60.0, false,
                new SkillMods().crowdControlDamageBonus(0.35)));
        pool.add(new Skill(
                "Windstep Focus", 8, 0.0, 0.0, Elemental.WIND, false,
                EffectCatalogue.createDodgeBuff(2, 35), 100.0, true,
                new SkillMods().staminaRestoreAmount(25).cleanseSelf()));
        pool.add(new Skill(
                "Hunter's Instinct", 10, 0.0, 0.0, Elemental.NORMAL, false,
                EffectCatalogue.createAttackBuff(3, 30), 100.0, true,
                new SkillMods().hpRestoreRatio(0.20)));
        pool.add(new Skill(
                "Toxic Detonation", 14, 24.0, 1.00, Elemental.FIRE, false,
                new SkillMods()
                        .detonateTargetEffect(EffectType.POISON, 0.50)
                        .extraCritPercent(20.0)));
        pool.add(new Skill(
                "Siphoning Bolt", 14, 22.0, 0.95, Elemental.DARK, false,
                EffectCatalogue.createBleed(3, 10), 75.0, false,
                new SkillMods().lifeStealRatio(0.35)));
        pool.add(new Skill(
                "Deadeye Snipe", 18, 46.0, 1.65, Elemental.WIND, false,
                EffectCatalogue.createDefenseDown(3, 25), 100.0, false,
                new SkillMods()
                        .oncePerBattle()
                        .damageBonusWhenTargetHpBelow(0.40, 0.50)
                        .extraCritPercent(30.0)));
        pool.add(new Skill(
                "Tempest Barrage", 20, 44.0, 1.45, Elemental.WATER, false,
                EffectCatalogue.createFrost(3, 35), 100.0, false,
                new SkillMods()
                        .oncePerBattle()
                        .detonateTargetEffect(EffectType.POISON, 0.50)
                        .lifeStealRatio(0.25)));

        return pool;
    }
}
