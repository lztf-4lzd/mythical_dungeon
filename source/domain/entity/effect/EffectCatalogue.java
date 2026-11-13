package source.domain.entity.effect;

public final class EffectCatalogue {

    private EffectCatalogue() {}

    public static StatusEffect createPoison(int duration, int damagePerTurn) {
        return new StatusEffect("Poison", EffectType.POISON, duration, damagePerTurn);
    }
    public static StatusEffect createBurn(int duration, int damagePerTurn) {
        return new StatusEffect("Burn", EffectType.BURN, duration, damagePerTurn);
    }
    public static StatusEffect createBleed(int duration, int damagePerTurn) {
        return new StatusEffect("Bleed", EffectType.BLEED, duration, damagePerTurn);
    }
    public static StatusEffect createRegen(int duration, int healPerTurn) {
        return new StatusEffect("Regen", EffectType.REGEN, duration, healPerTurn);
    }
    public static StatusEffect createParalyze(int duration) {
        return new StatusEffect("Paralyze", EffectType.PARALYZE, duration, 0);
    }
    public static StatusEffect createShield(int duration, int shieldAmount) {
        return new StatusEffect("Shield", EffectType.SHIELD, duration, shieldAmount);
    }
    public static StatusEffect createUndying(int duration, int revivePercent) {
        return new StatusEffect("Undying", EffectType.UNDYING, duration, revivePercent);
    }
    public static StatusEffect createDamageReduction(int duration, int percent) {
        return new StatusEffect("Damage Reduction", EffectType.DAMAGE_REDUCTION, duration, percent);
    }
    public static StatusEffect createAttackBuff(int duration, int percent) {
        return new StatusEffect("Attack Buff", EffectType.ATK_BUFF, duration, percent);
    }
    public static StatusEffect createDefenseBuff(int duration, int percent) {
        return new StatusEffect("Defense Buff", EffectType.DEF_BUFF, duration, percent);
    }
    public static StatusEffect createSpeedBuff(int duration, int percent) {
        return new StatusEffect("Speed Buff", EffectType.SPD_BUFF, duration, percent);
    }
    public static StatusEffect createDodgeBuff(int duration, int percent) {
        return new StatusEffect("Dodge Buff", EffectType.DODGE_BUFF, duration, percent);
    }
    public static StatusEffect createAccuracyBuff(int duration, int percent) {
        return new StatusEffect("Accuracy Buff", EffectType.ACCURACY_BUFF, duration, percent);
    }
    public static StatusEffect createCritBuff(int duration, int percent) {
        return new StatusEffect("Crit Buff", EffectType.CRIT_BUFF, duration, percent);
    }
    public static StatusEffect createResistanceBuff(int duration, int percent) {
        return new StatusEffect("Resistance Buff", EffectType.RESISTANCE_BUFF, duration, percent);
    }
    public static StatusEffect createWeaken(int duration, int percent) {
        return new StatusEffect("Weaken", EffectType.WEAKEN, duration, percent);
    }
    public static StatusEffect createDefenseDown(int duration, int percent) {
        return new StatusEffect("Defense Down", EffectType.DEF_DOWN, duration, percent);
    }
    public static StatusEffect createFrost(int duration, int percent) {
        return new StatusEffect("Frost", EffectType.FROST, duration, percent);
    }
    public static StatusEffect createBlind(int duration, int percent) {
        return new StatusEffect("Blind", EffectType.BLIND, duration, percent);
    }
    public static StatusEffect createDodgeDown(int duration, int percent) {
        return new StatusEffect("Dodge Down", EffectType.DODGE_DOWN, duration, percent);
    }
    public static StatusEffect createCritDown(int duration, int percent) {
        return new StatusEffect("Crit Down", EffectType.CRIT_DOWN, duration, percent);
    }
    public static StatusEffect createResistanceDown(int duration, int percent) {
        return new StatusEffect("Resistance Down", EffectType.RESISTANCE_DOWN, duration, percent);
    }
    public static StatusEffect createVulnerable(int duration, int percent) {
        return new StatusEffect("Vulnerable", EffectType.VULNERABLE, duration, percent);
    }
    public static StatusEffect createInstantDeath() {
        return new StatusEffect("Instant Death", EffectType.INSTANT_DEATH, 1, 9999);
    }
}
