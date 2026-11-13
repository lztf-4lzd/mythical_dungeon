package source.application.dungeon;

import source.domain.entity.monster.Monster;
import source.domain.entity.monster.MonsterCatalogue;
import source.domain.entity.monster.MonsterId;

import java.util.ArrayList;
import java.util.List;

/**
 * Danh mục và nhà máy định nghĩa 4 Hầm Ngục Phe Phái (Faction Dungeons), danh sách 5 cuộc chạm trán
 * tăng tiến độ khó, phần thưởng kinh nghiệm và tỷ lệ hồi phục giữa các trận.
 * Luôn tạo mới cá thể {@link Monster} cho mỗi trận để tránh rò rỉ trạng thái.
 * Người phụ trách: Thành viên 3 (Luồng Game & Sinh Ngục tối)
 */
public final class FloorCatalog {

    /** Danh sách phần thưởng kinh nghiệm theo thứ tự 5 cuộc chạm trán. */
    private static final List<Integer> ENCOUNTER_EXP_REWARDS = List.of(100, 150, 200, 300, 500);

    /** Tỷ lệ hồi máu tự động sau mỗi trận thắng thường. */
    static final double AFTER_BATTLE_HP_RECOVERY_RATIO = 0.20;
    /** Tỷ lệ hồi thể lực tự động sau mỗi trận thắng thường. */
    static final double AFTER_BATTLE_STA_RECOVERY_RATIO = 0.40;
    /** Tỷ lệ hồi máu tại lửa trại sau trận chạm trán thứ 3. */
    static final double CAMPFIRE_HP_RECOVERY_RATIO = 0.50;
    /** Tỷ lệ hồi thể lực tại lửa trại sau trận chạm trán thứ 3. */
    static final double CAMPFIRE_STA_RECOVERY_RATIO = 0.70;

    /**
     * Các chế độ hầm ngục phe phái được hỗ trợ.
     */
    public enum DungeonType {
        /** Hầm Mộ Xác Sống. */
        UNDEAD_CRYPT("Undead Crypt"),
        /** Pháo Đài Orc. */
        ORC_STRONGHOLD("Orc Stronghold"),
        /** Vực Thẳm Địa Ngục. */
        INFERNAL_ABYSS("Infernal Abyss"),
        /** Thành Trì Hắc Ám. */
        DARK_CITADEL("Dark Citadel");

        private String displayName;

        DungeonType(String displayName) {
            this.displayName = displayName;
        }

        /** Trả về tên hiển thị của hầm ngục. */
        public String getDisplayName() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return displayName;
        return null;
    }
    }

    private FloorCatalog() {
        // TODO: Hướng dẫn hiện thực:
        // - Viết xử lý logic tại đây theo yêu cầu thiết kế.
    }

    /**
     * Trả về lượng EXP thưởng cho cuộc chạm trán tại chỉ số {@code encounterIndex} (0..4).
     *
     * @param encounterIndex chỉ số cuộc chạm trán trong chuỗi 5 trận
     * @return điểm kinh nghiệm thưởng khi thắng trận đó
     */
    static int getEncounterExp(int encounterIndex) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (encounterIndex < 0 || encounterIndex >= ENCOUNTER_EXP_REWARDS.size())
        // 2. throw new IllegalArgumentException("Invalid encounter index: " + encounterIndex);
        // 3. return ENCOUNTER_EXP_REWARDS.get(encounterIndex);
        return 0;
    }

    /**
     * Khởi tạo danh sách 5 quái vật mới cho Hầm Mộ Xác Sống (Undead Crypt) theo thứ tự cố định:
     * 1. Skeleton (T1)
     * 2. Skeleton Hero (T2)
     * 3. Armored Skeleton (T2)
     * 4. Greatsword Skeleton [MINI-BOSS] (T3, isBoss = true)
     * 5. Ghostfire Skull [BOSS] (T4, isBoss = true)
     */
    private static List<Monster> createUndeadCryptMonsters() {
        // TODO: Hướng dẫn hiện thực:
        // 1. List<Monster> monsters = new ArrayList<>(5);
        // 2. monsters.add(MonsterCatalogue.create(MonsterId.SKELETON, false));
        // 3. monsters.add(MonsterCatalogue.create(MonsterId.SKELETON_ARCHER, false));
        // 4. monsters.add(MonsterCatalogue.create(MonsterId.ARMORED_SKELETON, false));
        // 5. monsters.add(MonsterCatalogue.create(MonsterId.GREATSWORD_SKELETON, true));
        // 6. monsters.add(MonsterCatalogue.create(MonsterId.GHOSTFIRE, true));
        return null;
    }

    /**
     * Khởi tạo danh sách 5 quái vật mới cho Pháo Đài Orc (Orc Stronghold) theo thứ tự cố định:
     * 1. Bat (T1)
     * 2. Orc Raider (T1)
     * 3. Armored Orc Bulwark (T2)
     * 4. Werebear [MINI-BOSS] (T3, isBoss = true)
     * 5. Elite Orc Berserker [BOSS] (T4, isBoss = true)
     */
    private static List<Monster> createOrcStrongholdMonsters() {
        // TODO: Hướng dẫn hiện thực:
        // 1. List<Monster> monsters = new ArrayList<>(5);
        // 2. monsters.add(MonsterCatalogue.create(MonsterId.BAT, false));
        // 3. monsters.add(MonsterCatalogue.create(MonsterId.ORC, false));
        // 4. monsters.add(MonsterCatalogue.create(MonsterId.ARMORED_ORC, false));
        // 5. monsters.add(MonsterCatalogue.create(MonsterId.WEREBEAR, true));
        // 6. monsters.add(MonsterCatalogue.create(MonsterId.ELITE_ORC, true));
        return null;
    }

    /**
     * Khởi tạo danh sách 5 quái vật mới cho Vực Thẳm Địa Ngục (Infernal Abyss) theo thứ tự cố định:
     * 1. Hellbat (T1)
     * 2. Infernal Imp / Demon A (T1)
     * 3. Hellbow Fiend / Demon B (T2)
     * 4. Winged Trident Fiend / Demon C [MINI-BOSS] (T3, isBoss = true)
     * 5. Obsidian Flame Golem [BOSS] (T5, isBoss = true)
     */
    private static List<Monster> createInfernalAbyssMonsters() {
        // TODO: Hướng dẫn hiện thực:
        // 1. List<Monster> monsters = new ArrayList<>(5);
        // 2. monsters.add(MonsterCatalogue.create(MonsterId.HELLBAT, false));
        // 3. monsters.add(MonsterCatalogue.create(MonsterId.DEMON_A, false));
        // 4. monsters.add(MonsterCatalogue.create(MonsterId.DEMON_B, false));
        // 5. monsters.add(MonsterCatalogue.create(MonsterId.DEMON_C, true));
        // 6. monsters.add(MonsterCatalogue.create(MonsterId.FLAME_GOLEM, true));
        return null;
    }

    /**
     * Khởi tạo danh sách 5 quái vật mới cho Thành Trì Hắc Ám (Dark Citadel) theo thứ tự cố định:
     * 1. Slime (T1)
     * 2. Eyeball Monster (T2)
     * 3. Ashen Spike Knight / Black Knight A (T3)
     * 4. Hellfire Warlock [MINI-BOSS] (T4, isBoss = true)
     * 5. Dread Cavalier Lord / Black Knight C [BOSS] (T5, isBoss = true)
     */
    private static List<Monster> createDarkCitadelMonsters() {
        // TODO: Hướng dẫn hiện thực:
        // 1. List<Monster> monsters = new ArrayList<>(5);
        // 2. monsters.add(MonsterCatalogue.create(MonsterId.SLIME, false));
        // 3. monsters.add(MonsterCatalogue.create(MonsterId.EYEBALL_MONSTER, false));
        // 4. monsters.add(MonsterCatalogue.create(MonsterId.BLACK_KNIGHT_A, false));
        // 5. monsters.add(MonsterCatalogue.create(MonsterId.WARLOCK, true));
        // 6. monsters.add(MonsterCatalogue.create(MonsterId.BLACK_KNIGHT_C, true));
        return null;
    }

    /**
     * Khởi tạo danh sách 5 quái vật mới tương ứng với loại Hầm Ngục Phe Phái được chọn.
     *
     * @param dungeonType loại hầm ngục mục tiêu
     * @return danh sách 5 cá thể {@link Monster} mới
     */
    static List<Monster> createMonsters(DungeonType dungeonType) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (dungeonType == null)
        // 2. throw new IllegalArgumentException("DungeonType cannot be null.");
        // 3. return switch (dungeonType)
        // 4. case UNDEAD_CRYPT -> createUndeadCryptMonsters();
        // 5. case ORC_STRONGHOLD -> createOrcStrongholdMonsters();
        // 6. case INFERNAL_ABYSS -> createInfernalAbyssMonsters();
        return null;
    }
}
