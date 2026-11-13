package source.domain.entity.hero;

import source.domain.calculator.DamageCalculator;
import source.domain.entity.character.Attributes;
import source.domain.entity.elemental.Elemental;

public final class HeroCatalogue {
    private HeroCatalogue() {}

    public static Hero createHero(HeroId heroId) {
        if (heroId == null) {
            throw new IllegalArgumentException("heroId must not be null.");
        }
        return createHero(heroId, heroId.getDisplayName());
    }

    public static Hero createHero(HeroId heroId, String name) {
        if (heroId == null) {
            throw new IllegalArgumentException("heroId must not be null.");
        }
        String heroName = (name == null || name.isBlank()) ? heroId.getDisplayName() : name.trim();
        Hero hero = new Hero(
                heroId,
                heroName,
                getBaseAttributes(heroId),
                getGrowthRate(heroId),
                getElemental(heroId));
        DamageCalculator.recalculateStats(hero);
        hero.getStats().restoreFullHpAndStamina();
        return hero;
    }

    public static Attributes getBaseAttributes(HeroId heroId) {
        if (heroId == null) {
            throw new IllegalArgumentException("heroId must not be null.");
        }
        return switch (heroId) {
            case WARRIOR -> new Attributes(24, 15, 14, 10, 18, 10);
            case MAGE -> new Attributes(8, 14, 24, 16, 14, 12);
            case PALADIN -> new Attributes(22, 14, 14, 14, 20, 14);
            case ARCHER -> new Attributes(18, 24, 8, 14, 16, 12);
        };
    }

    public static Attributes getGrowthRate(HeroId heroId) {
        if (heroId == null) {
            throw new IllegalArgumentException("heroId must not be null.");
        }
        return switch (heroId) {
            case WARRIOR -> new Attributes(3, 2, 1, 1, 2, 2);
            case MAGE -> new Attributes(1, 2, 5, 3, 2, 1);
            case PALADIN -> new Attributes(3, 1, 2, 2, 4, 2);
            case ARCHER -> new Attributes(2, 5, 1, 1, 2, 2);
        };
    }

    public static Elemental getElemental(HeroId heroId) {
        if (heroId == null) {
            throw new IllegalArgumentException("heroId must not be null.");
        }
        return Elemental.NORMAL;
    }
}
