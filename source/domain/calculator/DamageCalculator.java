package source.domain.calculator;

import source.domain.entity.character.Attributes;
import source.domain.entity.character.Character;
import source.domain.entity.effect.EffectType;
import source.domain.entity.effect.StatusEffect;
import source.domain.entity.skill.Skill;
import source.domain.entity.skill.SkillMods;
import source.domain.util.GameConstants;
import source.domain.util.GameRandom;

import java.util.Random;

public final class DamageCalculator {
    private static final double DEFENSE_SCALE_CONSTANT = 100.0;
    private static final double MIN_DAMAGE_VARIANCE = 0.95;
    private static final double DAMAGE_VARIANCE_RANGE = 0.10;

    private DamageCalculator() {}

    public static DamageResult calculateAttackDamage(Character attacker, Character defender, Skill skill) {
        return calculateAttackDamage(attacker, defender, skill, GameRandom.getDefaultRandom());
    }

    public static DamageResult calculateAttackDamage(
            Character attacker,
            Character defender,
            Skill skill,
            Random random) {
        Random rng = (random != null) ? random : GameRandom.getDefaultRandom();

        double rawDamage = calculateRawSkillDamage(attacker, defender, skill);
        rawDamage *= calculateAttackModifier(attacker);

        double elementalMultiplier = skill.getElemental().getDamageMultiplierAgainst(defender.getElemental());
        rawDamage *= elementalMultiplier;

        double effectiveDefense = calculateEffectiveDefense(defender, skill);
        double defenseMultiplier = DEFENSE_SCALE_CONSTANT / (effectiveDefense + DEFENSE_SCALE_CONSTANT);
        double preCritDamage = rawDamage * defenseMultiplier;

        double varianceMultiplier = MIN_DAMAGE_VARIANCE + (rng.nextDouble() * DAMAGE_VARIANCE_RANGE);
        preCritDamage *= varianceMultiplier;

        double critChancePercent = attacker.getStats().getCritChancePercent()
                + skill.getSkillMods().getExtraCritPercent();
        StatusEffect critBuff = findStrongestEffect(attacker, EffectType.CRIT_BUFF);
        if (critBuff != null) {
            critChancePercent += Math.max(0, critBuff.getValue());
        }
        StatusEffect critDown = findStrongestEffect(attacker, EffectType.CRIT_DOWN);
        if (critDown != null) {
            critChancePercent = Math.max(0.0, critChancePercent - Math.max(0, critDown.getValue()));
        }

        boolean isCriticalHit = (rng.nextDouble() * 100.0) < critChancePercent;
        if (isCriticalHit) {
            preCritDamage *= GameConstants.CRIT_MULTIPLIER;
        }

        StatusEffect damageReduction = findStrongestEffect(defender, EffectType.DAMAGE_REDUCTION);
        if (damageReduction != null) {
            preCritDamage *= Math.max(0.25, 1.0 - Math.max(0, damageReduction.getValue()) / 100.0);
        }
        StatusEffect vulnerable = findStrongestEffect(defender, EffectType.VULNERABLE);
        if (vulnerable != null) {
            preCritDamage *= 1.0 + Math.max(0, vulnerable.getValue()) / 100.0;
        }

        int finalDamage = Math.max(1, (int) Math.round(preCritDamage));
        String mechanicNote = detectTriggeredMechanicNote(attacker, defender, skill);
        return new DamageResult(finalDamage, isCriticalHit, elementalMultiplier, mechanicNote);
    }

    public static void recalculateStats(Character character) {
        Attributes attr = character.getAttributes();
        int strength = attr.getStrength();
        int dexterity = attr.getDexterity();
        int intelligence = attr.getIntelligence();
        int mind = attr.getMind();
        int vitality = attr.getVitality();
        int endurance = attr.getEndurance();

        double maxHp = Math.round(GameConstants.HP_BASE
                + vitality * GameConstants.HP_PER_VIT
                + strength * GameConstants.HP_PER_STR);
        if (character.isBoss()) {
            double bossMultiplier = character.isFinalBoss()
                    ? GameConstants.BOSS_HP_MULTIPLIER
                    : GameConstants.MINI_BOSS_HP_MULTIPLIER;
            maxHp = Math.round(maxHp * bossMultiplier);
        }

        double maxStamina = Math.round(GameConstants.STAMINA_BASE
                + endurance * GameConstants.STAMINA_PER_END
                + dexterity * GameConstants.STAMINA_PER_DEX);

        character.getStats().setMaxHp(maxHp);
        character.getStats().setMaxStamina(maxStamina);
        character.getStats().setBaseAccuracyPercent(GameConstants.ACCURACY_BASE);
        character.getStats().setBaseDodgePercent(GameConstants.DODGE_BASE);
        character.getStats().setBaseResistancePercent(0.0);

        character.getStats().setPhysicalAttack(Math.round(GameConstants.ATTACK_BASE
                + strength * GameConstants.ATTACK_PER_STR_OR_INT
                + dexterity * GameConstants.ATTACK_PER_DEX_OR_MND));
        character.getStats().setMagicAttack(Math.round(GameConstants.ATTACK_BASE
                + intelligence * GameConstants.ATTACK_PER_STR_OR_INT
                + mind * GameConstants.ATTACK_PER_DEX_OR_MND));
        character.getStats().setPhysicalDefense(Math.round(GameConstants.DEFENSE_BASE
                + vitality * GameConstants.DEFENSE_PER_VIT_OR_MND
                + endurance * GameConstants.DEFENSE_PER_END_OR_INT));
        character.getStats().setMagicDefense(Math.round(GameConstants.DEFENSE_BASE
                + mind * GameConstants.DEFENSE_PER_VIT_OR_MND
                + intelligence * GameConstants.DEFENSE_PER_END_OR_INT));
        character.getStats().setSpeed(Math.round(GameConstants.SPEED_BASE
                + dexterity * GameConstants.SPEED_PER_DEX));
        character.getStats().setCritChancePercent(Math.min(
                GameConstants.CRIT_MAX,
                GameConstants.CRIT_BASE + dexterity * GameConstants.CRIT_PER_DEX));
    }

    private static double calculateRawSkillDamage(Character attacker, Character defender, Skill skill) {
        double offensivePower = skill.isMagicDamage()
                ? attacker.getStats().getMagicAttack()
                : attacker.getStats().getPhysicalAttack();
        double effectiveScale = skill.getDamageScale() + calculateBonusScale(attacker, defender, skill);
        return skill.getBasicDamage() + offensivePower * effectiveScale;
    }

    private static double calculateAttackModifier(Character attacker) {
        double modifier = 1.0;
        StatusEffect attackBuff = findStrongestEffect(attacker, EffectType.ATK_BUFF);
        if (attackBuff != null) {
            modifier += Math.max(0, attackBuff.getValue()) / 100.0;
        }
        StatusEffect weakenDebuff = findStrongestEffect(attacker, EffectType.WEAKEN);
        if (weakenDebuff != null) {
            modifier *= Math.max(0.40, 1.0 - Math.max(0, weakenDebuff.getValue()) / 100.0);
        }
        return modifier;
    }

    private static double calculateEffectiveDefense(Character defender, Skill skill) {
        double defenseValue = skill.isMagicDamage()
                ? defender.getStats().getMagicDefense()
                : defender.getStats().getPhysicalDefense();
        StatusEffect defenseBuff = findStrongestEffect(defender, EffectType.DEF_BUFF);
        if (defenseBuff != null) {
            defenseValue *= 1.0 + Math.max(0, defenseBuff.getValue()) / 100.0;
        }
        StatusEffect defenseDown = findStrongestEffect(defender, EffectType.DEF_DOWN);
        if (defenseDown != null) {
            defenseValue *= Math.max(0.40, 1.0 - Math.max(0, defenseDown.getValue()) / 100.0);
        }
        return defenseValue;
    }

    private static double calculateBonusScale(Character attacker, Character defender, Skill skill) {
        double bonusScale = 0.0;
        SkillMods mods = skill.getSkillMods();

        if (mods.getTargetLowHpDamageBonus() > 0
                && getCurrentHpRatio(defender) <= mods.getTargetLowHpThreshold()) {
            bonusScale += mods.getTargetLowHpDamageBonus();
        }
        if (mods.getSelfLowHpDamageBonus() > 0
                && getCurrentHpRatio(attacker) <= mods.getSelfLowHpThreshold()) {
            bonusScale += mods.getSelfLowHpDamageBonus();
        }
        if (mods.getDetonateDamageBonus() > 0
                && hasActiveEffect(defender, mods.getDetonateEffectType())) {
            bonusScale += mods.getDetonateDamageBonus();
        }
        if (mods.getShieldSynergyDamageBonus() > 0 && attacker.getStats().getShieldAmount() > 0) {
            bonusScale += mods.getShieldSynergyDamageBonus();
        }
        if (mods.getCrowdControlDamageBonus() > 0
                && (hasActiveEffect(defender, EffectType.PARALYZE)
                || hasActiveEffect(defender, EffectType.FROST))) {
            bonusScale += mods.getCrowdControlDamageBonus();
        }

        return bonusScale;
    }

    private static String detectTriggeredMechanicNote(Character attacker, Character defender, Skill skill) {
        SkillMods mods = skill.getSkillMods();
        if (mods.getTargetLowHpDamageBonus() > 0
                && getCurrentHpRatio(defender) <= mods.getTargetLowHpThreshold()) {
            return "EXECUTE";
        }
        if (mods.getSelfLowHpDamageBonus() > 0
                && getCurrentHpRatio(attacker) <= mods.getSelfLowHpThreshold()) {
            return "LAST STAND";
        }
        if (mods.getDetonateDamageBonus() > 0
                && hasActiveEffect(defender, mods.getDetonateEffectType())) {
            return "DETONATE";
        }
        if (mods.getShieldSynergyDamageBonus() > 0 && attacker.getStats().getShieldAmount() > 0) {
            return "AEGIS STRIKE";
        }
        if (mods.getCrowdControlDamageBonus() > 0
                && (hasActiveEffect(defender, EffectType.PARALYZE)
                || hasActiveEffect(defender, EffectType.FROST))) {
            return "PUNISH";
        }
        return "";
    }

    private static double getCurrentHpRatio(Character character) {
        return character.getStats().getCurrentHp() / Math.max(1.0, character.getStats().getMaxHp());
    }

    private static boolean hasActiveEffect(Character character, EffectType type) {
        return character != null && character.hasActiveEffect(type);
    }

    private static StatusEffect findStrongestEffect(Character character, EffectType type) {
        if (character == null || type == null) {
            return null;
        }
        StatusEffect strongest = null;
        for (StatusEffect effect : character.getActiveEffects()) {
            if (effect.getType() == type
                    && (strongest == null || effect.getValue() > strongest.getValue())) {
                strongest = effect;
            }
        }
        return strongest;
    }
}
