package source.domain.entity.character;

import source.domain.entity.effect.EffectType;
import source.domain.entity.effect.StatusEffect;
import source.domain.entity.elemental.Elemental;
import source.domain.entity.skill.Skill;
import source.domain.util.GameConstants;

import java.util.ArrayList;
import java.util.List;

public abstract class Character {
    protected String name;
    protected int level = 1;
    protected Attributes attributes;
    protected Stats stats;
    protected Elemental elemental;
    protected List<Skill> equippedSkills;
    protected List<StatusEffect> activeEffects;
    protected boolean boss;
    private final List<String> usedOnceSkills = new ArrayList<>();

    protected Character(String name, Attributes attributes, Elemental elemental, Skill basicAttack) {
        if (name == null || attributes == null || elemental == null) {
            throw new IllegalArgumentException("name, attributes, and elemental must not be null.");
        }
        this.name = name;
        this.attributes = copyAttributes(attributes);
        this.elemental = elemental;
        this.stats = new Stats();
        this.equippedSkills = new ArrayList<>();
        this.activeEffects = new ArrayList<>();

        if (basicAttack != null) {
            this.equippedSkills.add(basicAttack);
        }
    }

    public abstract Skill createDefaultBasicAttack();

    public abstract Attributes getGrowthRate();

    public abstract List<Skill> createSkillPool();

    public Skill createBossUltimate() {
        return null;
    }

    public String getTypeId() {
        return getClass().getSimpleName();
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = Math.max(1, Math.min(GameConstants.MAX_LEVEL, level));
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public Stats getStats() {
        return stats;
    }

    public Elemental getElemental() {
        return elemental;
    }

    public boolean isBoss() {
        return boss;
    }

    public void setBoss(boolean boss) {
        this.boss = boss;
    }

    public boolean isFinalBoss() {
        return false;
    }

    public boolean hasUsedOnceSkill(String skillName) {
        return usedOnceSkills.contains(skillName);
    }

    public void markOnceSkillAsUsed(String skillName) {
        if (skillName != null && !usedOnceSkills.contains(skillName)) {
            usedOnceSkills.add(skillName);
        }
    }

    public void resetUsedOnceSkills() {
        usedOnceSkills.clear();
    }

    public boolean hasActiveEffect(EffectType type) {
        if (type == null) {
            return false;
        }
        for (StatusEffect effect : activeEffects) {
            if (effect.getType() == type) {
                return true;
            }
        }
        return false;
    }

    private int getStrongestEffectValue(EffectType type) {
        int bestValue = 0;
        for (StatusEffect effect : activeEffects) {
            if (effect.getType() == type && effect.getValue() > bestValue) {
                bestValue = effect.getValue();
            }
        }
        return bestValue;
    }

    public double getEffectiveResistancePercent() {
        double resistance = stats.getBaseResistancePercent()
                + attributes.getVitality() * GameConstants.RESISTANCE_PER_VIT
                + attributes.getMind() * GameConstants.RESISTANCE_PER_MND;
        double baseClamped = Math.max(0.0, Math.min(GameConstants.RESISTANCE_MAX, resistance));
        int resBonus = getStrongestEffectValue(EffectType.RESISTANCE_BUFF);
        int resPenalty = getStrongestEffectValue(EffectType.RESISTANCE_DOWN);
        if (resBonus == 0 && resPenalty == 0) {
            return baseClamped;
        }
        return Math.max(0.0, Math.min(90.0, baseClamped + resBonus - resPenalty));
    }

    public double getEffectiveAccuracyPercent() {
        double accuracy = stats.getBaseAccuracyPercent()
                + attributes.getDexterity() * GameConstants.ACCURACY_PER_DEX;
        double baseClamped = Math.max(0.0, Math.min(GameConstants.ACCURACY_MAX, accuracy));
        int accBonus = getStrongestEffectValue(EffectType.ACCURACY_BUFF);
        int blindPenalty = getStrongestEffectValue(EffectType.BLIND);
        if (accBonus == 0 && blindPenalty == 0) {
            return baseClamped;
        }
        return Math.max(0.0, Math.min(GameConstants.ACCURACY_MAX, baseClamped + accBonus - blindPenalty));
    }

    public double getEffectiveDodgePercent() {
        double dodge = stats.getBaseDodgePercent()
                + attributes.getDexterity() * GameConstants.DODGE_PER_DEX;
        double baseClamped = Math.max(0.0, Math.min(GameConstants.DODGE_MAX, dodge));
        int dodgeBonus = getStrongestEffectValue(EffectType.DODGE_BUFF);
        int dodgePenalty = getStrongestEffectValue(EffectType.DODGE_DOWN);
        if (dodgeBonus == 0 && dodgePenalty == 0) {
            return baseClamped;
        }
        return Math.max(0.0, Math.min(85.0, baseClamped + dodgeBonus - dodgePenalty));
    }

    public void resetBattleShield() {
        setShieldAmount(0.0);
    }

    public void setShieldAmount(double shieldAmount) {
        double updatedShield = Math.max(0.0, shieldAmount);
        stats.setShieldAmount(updatedShield);

        for (int i = 0; i < activeEffects.size(); i++) {
            StatusEffect effect = activeEffects.get(i);
            if (effect.getType() == EffectType.SHIELD) {
                if (updatedShield <= 0.0) {
                    activeEffects.remove(i);
                } else {
                    effect.setValue((int) Math.round(updatedShield));
                }
                return;
            }
        }
    }

    public List<Skill> getEquippedSkills() {
        return new ArrayList<>(equippedSkills);
    }

    public void replaceEquippedSkills(List<Skill> replacementSkills) {
        List<Skill> copy = new ArrayList<>(replacementSkills);
        equippedSkills.clear();
        equippedSkills.addAll(copy);
    }

    public List<StatusEffect> getActiveEffects() {
        List<StatusEffect> copies = new ArrayList<>(activeEffects.size());
        for (StatusEffect effect : activeEffects) {
            copies.add(effect.copy());
        }
        return copies;
    }

    public void addActiveEffect(StatusEffect effect) {
        if (effect == null) {
            return;
        }
        StatusEffect effectCopy = effect.copy();
        EffectType type = effectCopy.getType();
        if (!isStackableTickEffect(type)) {
            for (int i = 0; i < activeEffects.size(); i++) {
                StatusEffect existing = activeEffects.get(i);
                if (existing.getType() == type) {
                    if (type == EffectType.SHIELD) {
                        activeEffects.set(i, effectCopy);
                        stats.setShieldAmount(effectCopy.getValue());
                    } else if (type == EffectType.PARALYZE) {
                        activeEffects.set(i, effectCopy);
                    } else {
                        int refreshedDuration = Math.max(existing.getDuration(), effectCopy.getDuration());
                        int refreshedValue = Math.max(existing.getValue(), effectCopy.getValue());
                        activeEffects.set(i, new StatusEffect(
                                effectCopy.getName(),
                                type,
                                refreshedDuration,
                                refreshedValue));
                    }
                    return;
                }
            }
        }
        activeEffects.add(effectCopy);
        if (type == EffectType.SHIELD) {
            stats.setShieldAmount(effectCopy.getValue());
        }
    }

    public void clearActiveEffects() {
        activeEffects.clear();
        stats.setShieldAmount(0.0);
    }

    public void replaceActiveEffect(int index, StatusEffect effect) {
        StatusEffect previous = activeEffects.get(index);
        StatusEffect replacement = effect.copy();
        activeEffects.set(index, replacement);
        if (previous.getType() == EffectType.SHIELD || replacement.getType() == EffectType.SHIELD) {
            stats.setShieldAmount(replacement.getType() == EffectType.SHIELD ? replacement.getValue() : 0.0);
        }
    }

    public void removeActiveEffect(int index) {
        StatusEffect removed = activeEffects.remove(index);
        if (removed.getType() == EffectType.SHIELD) {
            stats.setShieldAmount(0.0);
        }
    }

    private static boolean isStackableTickEffect(EffectType type) {
        return type == EffectType.POISON
                || type == EffectType.BURN
                || type == EffectType.BLEED
                || type == EffectType.REGEN;
    }

    protected static Attributes copyAttributes(Attributes source) {
        return new Attributes(
                source.getStrength(),
                source.getDexterity(),
                source.getIntelligence(),
                source.getMind(),
                source.getVitality(),
                source.getEndurance());
    }
}
