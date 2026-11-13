package source.domain.entity.skill;

import source.domain.entity.effect.StatusEffect;
import source.domain.entity.elemental.Elemental;

public class Skill {
    private final String name;
    private final int staminaCost;
    private final double basicDamage;
    private final double damageScale;
    private final Elemental elemental;
    private final boolean magicDamage;
    private final StatusEffect appliedEffect;
    private final double effectApplyChancePercent;
    private final boolean selfTarget;
    private final SkillMods skillMods;

    public Skill(
            String name,
            int staminaCost,
            double basicDamage,
            double damageScale,
            Elemental elemental,
            boolean magicDamage) {
        this(name, staminaCost, basicDamage, damageScale, elemental, magicDamage, null, 0.0, false, null);
    }

    public Skill(
            String name,
            int staminaCost,
            double basicDamage,
            double damageScale,
            Elemental elemental,
            boolean magicDamage,
            SkillMods skillMods) {
        this(name, staminaCost, basicDamage, damageScale, elemental, magicDamage, null, 0.0, false, skillMods);
    }

    public Skill(
            String name,
            int staminaCost,
            double basicDamage,
            double damageScale,
            Elemental elemental,
            boolean magicDamage,
            StatusEffect appliedEffect,
            double effectApplyChancePercent,
            boolean selfTarget) {
        this(name, staminaCost, basicDamage, damageScale, elemental, magicDamage,
                appliedEffect, effectApplyChancePercent, selfTarget, null);
    }

    public Skill(
            String name,
            int staminaCost,
            double basicDamage,
            double damageScale,
            Elemental elemental,
            boolean magicDamage,
            StatusEffect appliedEffect,
            double effectApplyChancePercent,
            boolean selfTarget,
            SkillMods skillMods) {
        this.name = name;
        this.staminaCost = staminaCost;
        this.basicDamage = basicDamage;
        this.damageScale = damageScale;
        this.elemental = elemental;
        this.magicDamage = magicDamage;
        this.appliedEffect = appliedEffect == null ? null : appliedEffect.copy();
        this.effectApplyChancePercent = effectApplyChancePercent;
        this.selfTarget = selfTarget;
        this.skillMods = skillMods == null ? new SkillMods() : skillMods.copy();
    }

    public boolean hasAppliedEffect() {
        return appliedEffect != null;
    }

    public String getName() {
        return name;
    }

    public int getStaminaCost() {
        return staminaCost;
    }

    public double getBasicDamage() {
        return basicDamage;
    }

    public double getDamageScale() {
        return damageScale;
    }

    public Elemental getElemental() {
        return elemental;
    }

    public boolean isMagicDamage() {
        return magicDamage;
    }

    public StatusEffect getAppliedEffect() {
        return appliedEffect == null ? null : appliedEffect.copy();
    }

    public double getEffectApplyChancePercent() {
        return effectApplyChancePercent;
    }

    public boolean isSelfTarget() {
        return selfTarget;
    }

    public SkillMods getSkillMods() {
        return skillMods.copy();
    }
}
