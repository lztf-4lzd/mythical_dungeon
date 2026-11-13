package source.domain.entity.skill;

import source.domain.entity.effect.EffectType;

public final class SkillMods {
    private double targetLowHpDamageBonus;
    private double targetLowHpThreshold;
    private double instantDeathHpThreshold = -1.0;
    private double selfLowHpDamageBonus;
    private double selfLowHpThreshold;
    private double lifeStealRatio;
    private double hpRestoreRatio;
    private int staminaRestoreAmount;
    private boolean cleanseSelf;
    private EffectType detonateEffectType;
    private double detonateDamageBonus;
    private double extraCritPercent;
    private double shieldSynergyDamageBonus;
    private double crowdControlDamageBonus;
    private boolean oncePerBattle;
    private double ultimateSelfHpThreshold = -1.0;
    private double ultimateTargetHpThreshold = -1.0;
    private double selfSacrificeRatio;

    public SkillMods copy() {
        SkillMods copy = new SkillMods();
        copy.targetLowHpDamageBonus = targetLowHpDamageBonus;
        copy.targetLowHpThreshold = targetLowHpThreshold;
        copy.instantDeathHpThreshold = instantDeathHpThreshold;
        copy.selfLowHpDamageBonus = selfLowHpDamageBonus;
        copy.selfLowHpThreshold = selfLowHpThreshold;
        copy.lifeStealRatio = lifeStealRatio;
        copy.hpRestoreRatio = hpRestoreRatio;
        copy.staminaRestoreAmount = staminaRestoreAmount;
        copy.cleanseSelf = cleanseSelf;
        copy.detonateEffectType = detonateEffectType;
        copy.detonateDamageBonus = detonateDamageBonus;
        copy.extraCritPercent = extraCritPercent;
        copy.shieldSynergyDamageBonus = shieldSynergyDamageBonus;
        copy.crowdControlDamageBonus = crowdControlDamageBonus;
        copy.oncePerBattle = oncePerBattle;
        copy.ultimateSelfHpThreshold = ultimateSelfHpThreshold;
        copy.ultimateTargetHpThreshold = ultimateTargetHpThreshold;
        copy.selfSacrificeRatio = selfSacrificeRatio;
        return copy;
    }

    public SkillMods damageBonusWhenTargetHpBelow(double damageBonus, double healthRatio) {
        this.targetLowHpDamageBonus = damageBonus;
        this.targetLowHpThreshold = healthRatio;
        return this;
    }

    public SkillMods instantDeathWhenTargetHpBelow(double healthRatio) {
        this.instantDeathHpThreshold = healthRatio;
        return this;
    }

    public SkillMods damageBonusWhenSelfHpBelow(double damageBonus, double healthRatio) {
        this.selfLowHpDamageBonus = damageBonus;
        this.selfLowHpThreshold = healthRatio;
        return this;
    }

    public SkillMods lifeStealRatio(double ratio) {
        this.lifeStealRatio = ratio;
        return this;
    }

    public SkillMods hpRestoreRatio(double healthRatio) {
        this.hpRestoreRatio = healthRatio;
        return this;
    }

    public SkillMods staminaRestoreAmount(int staminaAmount) {
        this.staminaRestoreAmount = staminaAmount;
        return this;
    }

    public SkillMods cleanseSelf() {
        this.cleanseSelf = true;
        return this;
    }

    public SkillMods detonateTargetEffect(EffectType effectType, double damageBonus) {
        this.detonateEffectType = effectType;
        this.detonateDamageBonus = damageBonus;
        return this;
    }

    public SkillMods extraCritPercent(double percent) {
        this.extraCritPercent = percent;
        return this;
    }

    public SkillMods shieldSynergyDamageBonus(double damageBonus) {
        this.shieldSynergyDamageBonus = damageBonus;
        return this;
    }

    public SkillMods crowdControlDamageBonus(double damageBonus) {
        this.crowdControlDamageBonus = damageBonus;
        return this;
    }

    public SkillMods oncePerBattle() {
        this.oncePerBattle = true;
        return this;
    }

    public SkillMods ultimateWhenSelfHpBelow(double healthRatio) {
        this.ultimateSelfHpThreshold = healthRatio;
        return this;
    }

    public SkillMods ultimateWhenTargetHpBelow(double healthRatio) {
        this.ultimateTargetHpThreshold = healthRatio;
        return this;
    }

    public SkillMods selfSacrificeRatio(double healthRatio) {
        this.selfSacrificeRatio = healthRatio;
        return this;
    }

    public double getTargetLowHpDamageBonus() {
        return targetLowHpDamageBonus;
    }

    public double getTargetLowHpThreshold() {
        return targetLowHpThreshold;
    }

    public double getInstantDeathHpThreshold() {
        return instantDeathHpThreshold;
    }

    public double getSelfLowHpDamageBonus() {
        return selfLowHpDamageBonus;
    }

    public double getSelfLowHpThreshold() {
        return selfLowHpThreshold;
    }

    public double getLifeStealRatio() {
        return lifeStealRatio;
    }

    public double getHpRestoreRatio() {
        return hpRestoreRatio;
    }

    public int getStaminaRestoreAmount() {
        return staminaRestoreAmount;
    }

    public boolean isCleanseSelf() {
        return cleanseSelf;
    }

    public EffectType getDetonateEffectType() {
        return detonateEffectType;
    }

    public double getDetonateDamageBonus() {
        return detonateDamageBonus;
    }

    public double getExtraCritPercent() {
        return extraCritPercent;
    }

    public double getShieldSynergyDamageBonus() {
        return shieldSynergyDamageBonus;
    }

    public double getCrowdControlDamageBonus() {
        return crowdControlDamageBonus;
    }

    public boolean isOncePerBattle() {
        return oncePerBattle;
    }

    public double getUltimateSelfHpThreshold() {
        return ultimateSelfHpThreshold;
    }

    public double getUltimateTargetHpThreshold() {
        return ultimateTargetHpThreshold;
    }

    public double getSelfSacrificeRatio() {
        return selfSacrificeRatio;
    }
}
