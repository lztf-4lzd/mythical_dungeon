package source.application.view;

import source.application.dungeon.FloorCatalog;

/**
 * Mô hình hiển thị bất biến biểu diễn tiến độ hiện tại trong chuyến thám hiểm hầm ngục.
 * Người phụ trách: Thành viên 1 (Data Architect & Project Coordinator)
 */
public final class DungeonView {
    private FloorCatalog.DungeonType dungeonType;
    private int currentEncounterIndex;
    private CombatantView hero;
    private int totalExpEarned;
    private int totalBattlesWon;

    /** Khởi tạo mô hình hiển thị trạng thái thám hiểm hầm ngục. */
    public DungeonView(
            FloorCatalog.DungeonType dungeonType,
            int currentEncounterIndex,
            CombatantView hero,
            int totalExpEarned,
            int totalBattlesWon) {
        // TODO: Hướng dẫn hiện thực:
        // 1. this.dungeonType = dungeonType;
        // 2. this.currentEncounterIndex = currentEncounterIndex;
        // 3. this.hero = hero;
        // 4. this.totalExpEarned = totalExpEarned;
        // 5. this.totalBattlesWon = totalBattlesWon;
    }

    /** Trả về loại hầm ngục phe phái. */
    public FloorCatalog.DungeonType getDungeonType() {
        return dungeonType;
    }

    /** Trả về chỉ số cuộc chạm trán hiện tại (bắt đầu từ 0). */
    public int getCurrentEncounterIndex() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return currentEncounterIndex;
        return 0;
    }

    /** Trả về mô hình hiển thị của anh hùng. */
    public CombatantView getHero() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return hero;
        return null;
    }

    /** Trả về tổng điểm kinh nghiệm đã nhận. */
    public int getTotalExpEarned() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return totalExpEarned;
        return 0;
    }

    /** Trả về tổng số trận đã thắng. */
    public int getTotalBattlesWon() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return totalBattlesWon;
        return 0;
    }
}
