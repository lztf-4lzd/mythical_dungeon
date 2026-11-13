package source.domain.entity.monster;

import source.domain.calculator.DamageCalculator;
import source.domain.entity.character.Attributes;
import source.domain.entity.character.Character;
import source.domain.entity.elemental.Elemental;
import source.domain.entity.skill.MonsterSkills;
import source.domain.entity.skill.Skill;
import source.domain.util.GameConstants;

import java.util.ArrayList;
import java.util.List;

public class Monster extends Character {
    private final MonsterId monsterId;
    private final MonsterCatalogue.MonsterTier tier;
    private final Attributes baseAttributes;
    private final Attributes growthRate;

    public Monster(
            MonsterId monsterId,
            String displayName,
            Attributes baseAttributes,
            Attributes growthRate,
            Elemental elemental,
            MonsterCatalogue.MonsterTier tier,
            boolean boss) {
        super(
                resolveDisplayName(displayName, monsterId, boss),
                baseAttributes,
                elemental,
                MonsterSkills.createBasicAttack(monsterId));
        if (monsterId == null || growthRate == null || tier == null) {
            throw new IllegalArgumentException("monsterId, growthRate, and tier must not be null.");
        }
        this.monsterId = monsterId;
        this.tier = tier;
        this.baseAttributes = copyAttributes(baseAttributes);
        this.growthRate = copyAttributes(growthRate);
        this.boss = boss;
        this.equippedSkills.clear();
        this.equippedSkills.addAll(buildDefaultBattleSkills(monsterId, boss));
    }

    public MonsterId getMonsterId() {
        return monsterId;
    }

    public MonsterCatalogue.MonsterTier getTier() {
        return tier;
    }

    @Override
    public String getTypeId() {
        return monsterId.getId();
    }

    @Override
    public Skill createDefaultBasicAttack() {
        return MonsterSkills.createBasicAttack(monsterId);
    }

    @Override
    public Attributes getGrowthRate() {
        return copyAttributes(growthRate);
    }

    @Override
    public List<Skill> createSkillPool() {
        return MonsterSkills.createSkillPool(monsterId);
    }

    @Override
    public Skill createBossUltimate() {
        return MonsterSkills.createBossUltimate(monsterId);
    }

    @Override
    public boolean isFinalBoss() {
        if (!isBoss()) {
            return false;
        }
        String nameUpper = getName().toUpperCase();
        if (nameUpper.contains("[BOSS]") && !nameUpper.contains("[MINI-BOSS]")) {
            return true;
        }
        return tier == MonsterCatalogue.MonsterTier.T5_BOSS
                || monsterId == MonsterId.GHOSTFIRE
                || monsterId == MonsterId.ELITE_ORC
                || monsterId == MonsterId.FLAME_GOLEM
                || monsterId == MonsterId.BLACK_KNIGHT_C;
    }

    @Override
    public void setLevel(int level) {
        super.setLevel(level);
        applyLevelScaling();
    }

    @Override
    public void setBoss(boolean boss) {
        boolean changed = this.boss != boss;
        super.setBoss(boss);
        if (changed) {
            replaceEquippedSkills(buildDefaultBattleSkills(monsterId, boss));
            DamageCalculator.recalculateStats(this);
            getStats().restoreFullHpAndStamina();
        }
    }

    private void applyLevelScaling() {
        int extraLevels = Math.max(0, getLevel() - 1);
        this.attributes.setStrength(baseAttributes.getStrength() + growthRate.getStrength() * extraLevels);
        this.attributes.setDexterity(baseAttributes.getDexterity() + growthRate.getDexterity() * extraLevels);
        this.attributes.setIntelligence(baseAttributes.getIntelligence() + growthRate.getIntelligence() * extraLevels);
        this.attributes.setMind(baseAttributes.getMind() + growthRate.getMind() * extraLevels);
        this.attributes.setVitality(baseAttributes.getVitality() + growthRate.getVitality() * extraLevels);
        this.attributes.setEndurance(baseAttributes.getEndurance() + growthRate.getEndurance() * extraLevels);
        DamageCalculator.recalculateStats(this);
        getStats().restoreFullHpAndStamina();
    }

    private static List<Skill> buildDefaultBattleSkills(MonsterId monsterId, boolean boss) {
        List<Skill> loadout = new ArrayList<>();
        loadout.add(MonsterSkills.createBasicAttack(monsterId));
        List<Skill> pool = MonsterSkills.createSkillPool(monsterId);
        if (boss) {
            int signatureCount = Math.min(GameConstants.BOSS_SIGNATURE_SKILLS, pool.size());
            for (int i = 0; i < signatureCount; i++) {
                loadout.add(pool.get(i));
            }
            Skill bossUltimate = MonsterSkills.createBossUltimate(monsterId);
            if (bossUltimate != null) {
                loadout.add(bossUltimate);
            }
        } else {
            int tacticalCount = Math.min(GameConstants.MONSTER_BATTLE_SKILLS - 1, pool.size());
            for (int i = 0; i < tacticalCount; i++) {
                loadout.add(pool.get(i));
            }
        }
        return loadout;
    }

    private static String resolveDisplayName(String displayName, MonsterId monsterId, boolean boss) {
        if (displayName != null && !displayName.isBlank()) {
            return displayName.trim();
        }
        if (monsterId == null) {
            throw new IllegalArgumentException("monsterId must not be null.");
        }
        return boss ? monsterId.getBossDisplayName() : monsterId.getDisplayName();
    }
}
