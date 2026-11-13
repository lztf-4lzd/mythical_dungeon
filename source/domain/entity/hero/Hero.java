package source.domain.entity.hero;

import source.domain.calculator.DamageCalculator;
import source.domain.entity.character.Attributes;
import source.domain.entity.character.Character;
import source.domain.entity.elemental.Elemental;
import source.domain.entity.skill.HeroSkills;
import source.domain.entity.skill.Skill;

import java.util.List;

public class Hero extends Character {
    private final HeroId heroId;
    private final Attributes baseAttributes;
    private final Attributes growthRate;

    public Hero(HeroId heroId, String name) {
        this(
                heroId,
                name,
                HeroCatalogue.getBaseAttributes(heroId),
                HeroCatalogue.getGrowthRate(heroId),
                HeroCatalogue.getElemental(heroId));
    }

    public Hero(
            HeroId heroId,
            String name,
            Attributes baseAttributes,
            Attributes growthRate,
            Elemental elemental) {
        super(
                name,
                baseAttributes,
                elemental,
                HeroSkills.createBasicAttack(heroId));
        if (heroId == null || growthRate == null) {
            throw new IllegalArgumentException("heroId and growthRate must not be null.");
        }
        this.heroId = heroId;
        this.baseAttributes = copyAttributes(baseAttributes);
        this.growthRate = copyAttributes(growthRate);
    }

    public HeroId getHeroId() {
        return heroId;
    }

    @Override
    public String getTypeId() {
        return heroId.getId();
    }

    @Override
    public Skill createDefaultBasicAttack() {
        return HeroSkills.createBasicAttack(heroId);
    }

    @Override
    public Attributes getGrowthRate() {
        return copyAttributes(growthRate);
    }

    @Override
    public List<Skill> createSkillPool() {
        return HeroSkills.createSkillPool(heroId);
    }

    @Override
    public void setLevel(int level) {
        super.setLevel(level);
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
}
