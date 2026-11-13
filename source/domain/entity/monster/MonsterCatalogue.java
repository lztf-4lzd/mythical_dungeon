package source.domain.entity.monster;

import source.domain.calculator.DamageCalculator;
import source.domain.entity.character.Attributes;
import source.domain.entity.elemental.Elemental;

public final class MonsterCatalogue {
    public enum MonsterTier {
        T1_COMMON,
        T2_REGULAR,
        T3_ELITE,
        T4_MINI_BOSS,
        T5_BOSS
    }

    private MonsterCatalogue() {}

    public static Monster createMonster(MonsterId monsterId, boolean isBoss) {
        if (monsterId == null) {
            throw new IllegalArgumentException("monsterId must not be null.");
        }
        String displayName = isBoss ? monsterId.getBossDisplayName() : monsterId.getDisplayName();
        Monster monster = new Monster(
                monsterId,
                displayName,
                getBaseAttributes(monsterId),
                getGrowthRate(monsterId, isBoss),
                getElemental(monsterId),
                getTier(monsterId),
                isBoss);
        DamageCalculator.recalculateStats(monster);
        monster.getStats().restoreFullHpAndStamina();
        return monster;
    }

    public static Attributes getBaseAttributes(MonsterId monsterId) {
        if (monsterId == null) {
            throw new IllegalArgumentException("monsterId must not be null.");
        }
        return switch (monsterId) {
            // Pack 01
            case SOLDIER -> new Attributes(9, 11, 3, 5, 12, 8);
            case ORC -> new Attributes(9, 13, 3, 4, 10, 6);
            case ELITE_ORC -> new Attributes(14, 9, 4, 6, 13, 11);
            case ARMORED_ORC -> new Attributes(12, 6, 3, 6, 18, 12);
            case SKELETON -> new Attributes(9, 9, 6, 7, 12, 7);
            case ARMORED_SKELETON -> new Attributes(13, 8, 5, 7, 16, 9);
            case GREATSWORD_SKELETON -> new Attributes(15, 10, 5, 7, 11, 10);
            case SKELETON_ARCHER -> new Attributes(8, 14, 5, 6, 10, 7);
            case SLIME -> new Attributes(5, 5, 10, 9, 14, 6);
            case BAT -> new Attributes(7, 16, 4, 5, 9, 5);
            case ORC_RIDER -> new Attributes(16, 11, 5, 8, 17, 13);
            case WEREBEAR -> new Attributes(14, 7, 3, 6, 15, 10);
            case WEREWOLF -> new Attributes(12, 17, 4, 5, 12, 8);

            // Pack 02
            case BLACK_KNIGHT_A -> new Attributes(16, 10, 11, 10, 15, 11);
            case BLACK_KNIGHT_B -> new Attributes(12, 7, 8, 10, 14, 13);
            case BLACK_KNIGHT_C -> new Attributes(17, 12, 13, 13, 18, 13);
            case BLOOD_MONSTER_A -> new Attributes(10, 8, 6, 6, 14, 7);
            case BLOOD_MONSTER_B -> new Attributes(6, 14, 9, 7, 9, 5);
            case DEMON_A -> new Attributes(9, 12, 5, 5, 11, 6);
            case DEMON_B -> new Attributes(10, 15, 6, 6, 11, 8);
            case DEMON_C -> new Attributes(11, 13, 10, 8, 9, 7);
            case DEMON_D -> new Attributes(17, 7, 9, 9, 17, 13);
            case DEMON_E -> new Attributes(14, 15, 7, 8, 10, 9);
            case DEMONESS_A -> new Attributes(16, 14, 11, 10, 13, 10);
            case DEMONESS_B -> new Attributes(9, 14, 11, 10, 11, 6);
            case EYEBALL_MONSTER -> new Attributes(6, 10, 14, 12, 11, 6);
            case FLAME_GOLEM -> new Attributes(17, 6, 12, 13, 21, 14);
            case GHOSTFIRE -> new Attributes(4, 14, 14, 11, 16, 5);
            case HELLBAT -> new Attributes(7, 16, 9, 6, 9, 5);
            case HELLHOUND -> new Attributes(13, 16, 6, 5, 12, 7);
            case LAVA_SLIME -> new Attributes(7, 5, 13, 11, 17, 7);
            case MINOTAUR -> new Attributes(16, 9, 4, 6, 14, 11);
            case WARLOCK -> new Attributes(5, 10, 16, 15, 8, 7);
        };
    }

    public static Elemental getElemental(MonsterId monsterId) {
        if (monsterId == null) {
            throw new IllegalArgumentException("monsterId must not be null.");
        }
        return switch (monsterId) {
            case SOLDIER, ORC, ELITE_ORC -> Elemental.NORMAL;
            case ARMORED_ORC, SLIME, ORC_RIDER, WEREBEAR, MINOTAUR -> Elemental.GROUND;
            case BAT, WEREWOLF, DEMON_E -> Elemental.WIND;
            case BLACK_KNIGHT_A, DEMON_A, DEMON_B, DEMON_C, DEMON_D,
                 FLAME_GOLEM, GHOSTFIRE, HELLBAT, HELLHOUND, LAVA_SLIME -> Elemental.FIRE;
            case SKELETON, ARMORED_SKELETON, GREATSWORD_SKELETON, SKELETON_ARCHER,
                 BLACK_KNIGHT_B, BLACK_KNIGHT_C, BLOOD_MONSTER_A, BLOOD_MONSTER_B,
                 DEMONESS_A, DEMONESS_B, EYEBALL_MONSTER, WARLOCK -> Elemental.DARK;
        };
    }

    public static MonsterTier getTier(MonsterId monsterId) {
        if (monsterId == null) {
            throw new IllegalArgumentException("monsterId must not be null.");
        }
        return switch (monsterId) {
            case SOLDIER, ORC, SKELETON, SKELETON_ARCHER, SLIME, BAT,
                 BLOOD_MONSTER_A, BLOOD_MONSTER_B, DEMON_A, HELLBAT -> MonsterTier.T1_COMMON;
            case ARMORED_ORC, ARMORED_SKELETON, WEREBEAR, WEREWOLF,
                 DEMON_B, DEMON_C, DEMONESS_B, EYEBALL_MONSTER,
                 GHOSTFIRE, HELLHOUND, LAVA_SLIME -> MonsterTier.T2_REGULAR;
            case ELITE_ORC, GREATSWORD_SKELETON, BLACK_KNIGHT_B,
                 DEMON_E, MINOTAUR, WARLOCK -> MonsterTier.T3_ELITE;
            case ORC_RIDER, BLACK_KNIGHT_A, DEMON_D, DEMONESS_A -> MonsterTier.T4_MINI_BOSS;
            case BLACK_KNIGHT_C, FLAME_GOLEM -> MonsterTier.T5_BOSS;
        };
    }

    public static MonsterRole getRole(MonsterId monsterId) {
        if (monsterId == null) {
            throw new IllegalArgumentException("monsterId must not be null.");
        }
        return switch (monsterId) {
            case SOLDIER, SKELETON_ARCHER, DEMON_B -> MonsterRole.RANGED_MARKSMAN;
            case ORC, BAT, WEREWOLF, BLOOD_MONSTER_B, DEMON_A, HELLBAT, HELLHOUND -> MonsterRole.SKIRMISHER;
            case ARMORED_ORC, SKELETON, SLIME, BLACK_KNIGHT_B, FLAME_GOLEM -> MonsterRole.VANGUARD_TANK;
            case ELITE_ORC, WEREBEAR, BLOOD_MONSTER_A, DEMON_D, MINOTAUR -> MonsterRole.BRUISER;
            case ARMORED_SKELETON, GREATSWORD_SKELETON, BLACK_KNIGHT_A, DEMON_E, DEMONESS_A -> MonsterRole.HEAVY_SWEEPER;
            case ORC_RIDER, BLACK_KNIGHT_C -> MonsterRole.CHARGE_CAVALRY;
            case EYEBALL_MONSTER, GHOSTFIRE, LAVA_SLIME, WARLOCK -> MonsterRole.ELEMENTAL_CASTER;
            case DEMON_C, DEMONESS_B -> MonsterRole.CONTROLLER_HEXER;
        };
    }

    public static Attributes getGrowthRate(MonsterId monsterId, boolean isBoss) {
        MonsterRole role = getRole(monsterId);
        MonsterTier tier = getTier(monsterId);
        boolean bossMode = isBoss || tier == MonsterTier.T4_MINI_BOSS || tier == MonsterTier.T5_BOSS;

        if (bossMode) {
            return switch (role) {
                case VANGUARD_TANK -> new Attributes(2, 1, 0, 1, 3, 2);
                case BRUISER, HEAVY_SWEEPER -> new Attributes(2, 1, 0, 1, 2, 2);
                case SKIRMISHER, CHARGE_CAVALRY, RANGED_MARKSMAN -> new Attributes(2, 2, 0, 1, 2, 2);
                case ELEMENTAL_CASTER, CONTROLLER_HEXER -> new Attributes(1, 1, 3, 2, 2, 1);
            };
        }

        if (tier == MonsterTier.T3_ELITE) {
            return switch (role) {
                case VANGUARD_TANK -> new Attributes(2, 1, 0, 1, 3, 2);
                case BRUISER, HEAVY_SWEEPER -> new Attributes(2, 1, 0, 1, 3, 1);
                case SKIRMISHER, CHARGE_CAVALRY, RANGED_MARKSMAN -> new Attributes(2, 2, 0, 1, 2, 1);
                case ELEMENTAL_CASTER, CONTROLLER_HEXER -> new Attributes(0, 1, 3, 2, 2, 1);
            };
        }

        return switch (role) {
            case VANGUARD_TANK -> new Attributes(1, 0, 0, 1, 3, 1);
            case BRUISER, HEAVY_SWEEPER, CHARGE_CAVALRY -> new Attributes(2, 1, 0, 0, 2, 1);
            case SKIRMISHER -> new Attributes(1, 2, 0, 0, 2, 1);
            case RANGED_MARKSMAN -> new Attributes(1, 2, 0, 1, 1, 1);
            case ELEMENTAL_CASTER, CONTROLLER_HEXER -> new Attributes(0, 1, 2, 2, 1, 0);
        };
    }
}
