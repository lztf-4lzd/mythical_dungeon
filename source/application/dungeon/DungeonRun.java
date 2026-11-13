package source.application.dungeon;

import source.domain.entity.character.Character;
import source.domain.entity.skill.Skill;
import source.domain.util.GameConstants;
import source.domain.calculator.DamageCalculator;
import source.application.combat.BattleSession;
import source.application.progression.LevelService;
import source.application.setup.LoadoutService;

import java.util.Collections;
import java.util.List;

/**
 * Máy trạng thái quản lý tiến trình khám phá hầm ngục phe phái, điều phối các cuộc chạm trán,
 * hồi phục giữa các trận, nghỉ ngơi tại lửa trại sau trận thứ 3, thưởng kinh nghiệm và kết thúc.
 * Người phụ trách: Thành viên 3 (Luồng Game & Sinh Ngục tối)
 */
public class DungeonRun {

    /** Các trạng thái của chuyến khám phá hầm ngục. */
    public enum DungeonState {
        /** Chưa bắt đầu. */
        READY,
        /** Đang chờ người chơi chọn bộ kỹ năng đầu chuyến đi. */
        AWAITING_LOADOUT,
        /** Đã sẵn sàng bước vào trận đánh tiếp theo. */
        AWAITING_BATTLE,
        /** Đang diễn ra trận đánh. */
        IN_BATTLE,
        /** Đang ở khoảng nghỉ giữa hai cuộc chạm trán. */
        BETWEEN_ENCOUNTERS,
        /** Đang nghỉ ngơi tại lửa trại (sau trận thứ 3), cho phép đổi bộ kỹ năng. */
        CAMPFIRE,
        /** Đã hoàn thành toàn bộ hầm ngục. */
        VICTORY,
        /** Anh hùng bị đánh bại trong hầm ngục. */
        DEFEAT
    }

    /** Nhân vật anh hùng của người chơi. */
    private Character hero;
    /** Loại hầm ngục phe phái được chọn. */
    private FloorCatalog.DungeonType dungeonType;
    /** Trạng thái hiện tại của chuyến khám phá. */
    private DungeonState state;
    /** Số thứ tự tầng hiện tại. */
    private int currentFloor;
    /** Chỉ số cuộc chạm trán hiện tại trên tầng (bắt đầu từ 0). */
    private int currentEncounterIndex;
    /** Danh sách các quái vật trên tầng hiện tại. */
    private List<? extends Character> currentFloorMonsters;
    /** Phiên chiến đấu đang diễn ra (nếu có). */
    private BattleSession currentBattleSession;

    /** Tổng kinh nghiệm đã tích lũy trong chuyến đi. */
    private int totalExpEarned;
    /** Tổng số trận đã thắng trong chuyến đi. */
    private int totalBattlesWon;

    /**
     * Khởi tạo một chuyến đi hầm ngục mới (chưa bắt đầu) cho anh hùng và loại hầm ngục chỉ định.
     *
     * @param hero nhân vật người chơi
     * @param dungeonType loại hầm ngục phe phái được chọn
     */
    public DungeonRun(Character hero, FloorCatalog.DungeonType dungeonType) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (hero == null)
        // 2. throw new IllegalArgumentException("Hero cannot be null.");
        // 3. if (dungeonType == null)
        // 4. throw new IllegalArgumentException("DungeonType cannot be null.");
        // 5. this.hero = hero;
        // 6. this.dungeonType = dungeonType;
    }

    /**
     * Bắt đầu chuyến đi hầm ngục: hồi đầy máu/thể lực, xóa hiệu ứng tồn đọng,
     * khởi tạo danh sách quái vật của hầm ngục và chuyển sang {@code AWAITING_LOADOUT}.
     */
    public void startRun() {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (state != DungeonState.READY)
        // 2. throw new IllegalStateException("Run has already been started.");
        // 3. restoreHeroForFloor();
        // 4. this.currentFloor = 1;
        // 5. this.currentFloorMonsters = FloorCatalog.createMonsters(dungeonType);
        // 6. this.currentEncounterIndex = 0;
    }

    /**
     * Trang bị danh sách kỹ năng chiến thuật cho anh hùng và chuyển sang {@code AWAITING_BATTLE}.
     * Chỉ cho phép khi đang chọn bộ kỹ năng ban đầu ({@code AWAITING_LOADOUT}) hoặc tại lửa trại ({@code CAMPFIRE}).
     *
     * @param tacticalSkills danh sách kỹ năng chiến thuật từ bể kỹ năng của anh hùng
     */
    public void setHeroLoadout(List<Skill> tacticalSkills) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (state != DungeonState.AWAITING_LOADOUT && state != DungeonState.CAMPFIRE)
        // 2. throw new IllegalStateException("Cannot change loadout in state: " + state);
        // 3. LoadoutService.applyTacticalSkills(hero, tacticalSkills);
        // 4. this.state = DungeonState.AWAITING_BATTLE;
    }

    /**
     * Xác nhận giữ nguyên bộ kỹ năng hiện tại của anh hùng và chuyển sang {@code AWAITING_BATTLE}.
     */
    public void confirmLoadout() {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (state != DungeonState.AWAITING_LOADOUT && state != DungeonState.CAMPFIRE)
        // 2. throw new IllegalStateException("Cannot confirm loadout in state: " + state);
        // 3. if (hero.getSkills().size() < GameConstants.HERO_BATTLE_SKILLS)
        // 4. LoadoutService.assignDefaultHeroLoadout(hero);
        // 5. this.state = DungeonState.AWAITING_BATTLE;
    }

    /**
     * Chuẩn bị và bước vào cuộc chạm trán tiếp theo trong chuỗi hầm ngục, chuyển sang {@code IN_BATTLE}.
     *
     * @return đối tượng {@link BattleSession} đã khởi tạo cho cuộc chạm trán
     */
    public BattleSession startNextBattle() {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (state == DungeonState.VICTORY || state == DungeonState.DEFEAT)
        // 2. throw new IllegalStateException("Cannot start battle: dungeon run is over (" + state + ").");
        // 3. if (state == DungeonState.CAMPFIRE || state == DungeonState.AWAITING_LOADOUT)
        // 4. confirmLoadout();
        // 5. if (state != DungeonState.AWAITING_BATTLE && state != DungeonState.BETWEEN_ENCOUNTERS)
        // 6. throw new IllegalStateException("Not currently awaiting battle. Current state: " + state);
        return null;
    }

    /**
     * Khép lại trận đánh hiện tại sau khi có kết quả: trao thưởng EXP, áp dụng hồi phục sau trận
     * hoặc nghỉ lửa trại sau trận thứ 3, và cập nhật tiến trình hầm ngục.
     */
    public void concludeBattle() {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (state != DungeonState.IN_BATTLE)
        // 2. throw new IllegalStateException("Cannot conclude battle: not currently in battle.");
        // 3. if (currentBattleSession == null || !currentBattleSession.isGameOver())
        // 4. throw new IllegalStateException("Current battle has not ended yet.");
        // 5. if (currentBattleSession.isDefeat() || hero.getStats().getCurrentHp() <= 0)
        // 6. this.state = DungeonState.DEFEAT;
    }

    // =========================================================================
    // Các phương thức hỗ trợ nội bộ
    // =========================================================================

    /** Hồi đầy máu và thể lực cho anh hùng, đồng thời xóa toàn bộ hiệu ứng đang kích hoạt. */
    private void restoreHeroForFloor() {
        // TODO: Hướng dẫn hiện thực:
        // 1. hero.getStats().setCurrentHp(hero.getStats().getMaxHp());
        // 2. hero.getStats().setCurrentSta(hero.getStats().getMaxSta());
        // 3. hero.clearActiveEffects();
    }

    /** Xóa hiệu ứng tồn đọng của anh hùng, đồng bộ cấp độ quái vật, tính lại chỉ số và trang bị kỹ năng cho quái. */
    private void prepareEncounter(Character monster) {
        // TODO: Hướng dẫn hiện thực:
        // 1. hero.clearActiveEffects();
        // 2. monster.setLevel(hero.getLevel());
        // 3. DamageCalculator.recalculateStats(monster);
        // 4. monster.getStats().restoreFullHealthAndStamina();
        // 5. LoadoutService.assignMonsterSkills(monster);
    }

    /** Hồi phục một phần máu và thể lực giữa hai cuộc chạm trán thường. */
    private void recoverAfterBattle() {
        // TODO: Hướng dẫn hiện thực:
        // 1. int maxHp = hero.getStats().getMaxHp();
        // 2. int maxSta = hero.getStats().getMaxSta();
        // 3. int recoveredHp = (int) (maxHp * FloorCatalog.AFTER_BATTLE_HP_RECOVERY_RATIO);
        // 4. int recoveredSta = (int) (maxSta * FloorCatalog.AFTER_BATTLE_STA_RECOVERY_RATIO);
        // 5. hero.getStats().setCurrentHp(Math.min(
        // 6. maxHp,
    }

    /** Áp dụng hồi phục tại lửa trại sau cuộc chạm trán thứ 3. */
    private void restAtCampfire() {
        // TODO: Hướng dẫn hiện thực:
        // 1. int maxHp = hero.getStats().getMaxHp();
        // 2. int maxSta = hero.getStats().getMaxSta();
        // 3. int recoveredHp = (int) (maxHp * FloorCatalog.CAMPFIRE_HP_RECOVERY_RATIO);
        // 4. int recoveredSta = (int) (maxSta * FloorCatalog.CAMPFIRE_STA_RECOVERY_RATIO);
        // 5. hero.getStats().setCurrentHp(Math.min(
        // 6. maxHp,
    }

    /** Trao thưởng kinh nghiệm và cộng dồn tổng EXP đạt được trong chuyến đi. */
    private void awardExperience(int expGained) {
        // TODO: Hướng dẫn hiện thực:
        // 1. LevelService.processExpGain(hero, expGained);
        // 2. this.totalExpEarned += expGained;
    }

    // =========================================================================
    // Các phương thức truy vấn (Getters)
    // =========================================================================

    /** Trả về nhân vật anh hùng của chuyến đi. */
    public Character getHero() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return hero;
        return null;
    }

    /** Trả về loại hầm ngục phe phái hiện tại. */
    public FloorCatalog.DungeonType getDungeonType() {
        return dungeonType;
    }

    /** Trả về trạng thái hiện tại của chuyến khám phá. */
    public DungeonState getState() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return state;
        return null;
    }

    /** Trả về số thứ tự tầng hiện tại. */
    public int getCurrentFloor() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return currentFloor;
        return 0;
    }

    /** Trả về chỉ số cuộc chạm trán hiện tại (0..4). */
    public int getCurrentEncounterIndex() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return currentEncounterIndex;
        return 0;
    }

    /** Trả về tổng số cuộc chạm trán trên tầng hiện tại. */
    public int getTotalEncounters() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return currentFloorMonsters != null ? currentFloorMonsters.size() : 0;
        return 0;
    }

    /** Trả về tổng điểm kinh nghiệm đã nhận trong chuyến đi. */
    public int getTotalExpEarned() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return totalExpEarned;
        return 0;
    }

    /** Trả về tổng số trận đã chiến thắng trong chuyến đi. */
    public int getTotalBattlesWon() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return totalBattlesWon;
        return 0;
    }

    /** Kiểm tra xem chuyến khám phá đã kết thúc (thắng hoặc thua) hay chưa. */
    public boolean isGameOver() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return state == DungeonState.VICTORY || state == DungeonState.DEFEAT;
        return false;
    }

    /** Kiểm tra xem người chơi đã hoàn thành hầm ngục hay chưa. */
    public boolean isVictory() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return state == DungeonState.VICTORY;
        return false;
    }

    /** Kiểm tra xem người chơi đã thất bại trong hầm ngục hay chưa. */
    public boolean isDefeat() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return state == DungeonState.DEFEAT;
        return false;
    }
}
