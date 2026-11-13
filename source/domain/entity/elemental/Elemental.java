package source.domain.entity.elemental;

import source.domain.util.GameConstants;

public enum Elemental {
    NORMAL,
    FIRE,
    WATER,
    WIND,
    GROUND,
    LIGHTNING,
    HOLY,
    DARK;

    public double getDamageMultiplierAgainst(Elemental targetElemental) {
        if (targetElemental == null || this == NORMAL || targetElemental == NORMAL) {
            return GameConstants.MULTIPLIER_NORMAL;
        }
        if ((this == HOLY && targetElemental == DARK) || (this == DARK && targetElemental == HOLY)) {
            return GameConstants.MULTIPLIER_ADVANTAGE;
        }
        return switch (this) {
            case FIRE -> {
                if (targetElemental == WIND) yield GameConstants.MULTIPLIER_ADVANTAGE;
                if (targetElemental == WATER) yield GameConstants.MULTIPLIER_DISADVANTAGE;
                yield GameConstants.MULTIPLIER_NORMAL;
            }
            case WIND -> {
                if (targetElemental == GROUND) yield GameConstants.MULTIPLIER_ADVANTAGE;
                if (targetElemental == FIRE) yield GameConstants.MULTIPLIER_DISADVANTAGE;
                yield GameConstants.MULTIPLIER_NORMAL;
            }
            case GROUND -> {
                if (targetElemental == WATER) yield GameConstants.MULTIPLIER_ADVANTAGE;
                if (targetElemental == WIND) yield GameConstants.MULTIPLIER_DISADVANTAGE;
                yield GameConstants.MULTIPLIER_NORMAL;
            }
            case WATER -> {
                if (targetElemental == FIRE) yield GameConstants.MULTIPLIER_ADVANTAGE;
                if (targetElemental == GROUND) yield GameConstants.MULTIPLIER_DISADVANTAGE;
                yield GameConstants.MULTIPLIER_NORMAL;
            }
            default -> GameConstants.MULTIPLIER_NORMAL;
        };
    }
}
