package source.application.flow;

import source.application.view.DungeonView;
import source.domain.entity.character.Character;
import source.domain.entity.skill.Skill;
import source.application.combat.BattleSession;
import source.application.dungeon.DungeonRun;
import source.application.dungeon.FloorCatalog;

import java.util.List;

/**
 * Bộ điều phối luồng hầm ngục kết nối tiến trình {@link DungeonRun} với các màn hình giao diện,
 * quản lý các cuộc chạm trán, nghỉ ngơi tại lửa trại và vòng đời của {@link BattleFlow}.
 * Người phụ trách: Thành viên 3 (Luồng Game & Sinh Ngục tối)
 */
public class DungeonFlow {

    /** Tiến trình hầm ngục cơ sở. */
    private DungeonRun dungeonRun;

    /** Khởi tạo luồng hầm ngục mới cho anh hùng và loại hầm ngục được chọn. */
    DungeonFlow(Character hero, FloorCatalog.DungeonType dungeonType) {
        // TODO: Hướng dẫn hiện thực:
        // 1. this.dungeonRun = new DungeonRun(hero, dungeonType);
    }

    /** Bắt đầu chuyến thám hiểm hầm ngục. */
    void startRun() {
        // TODO: Hướng dẫn hiện thực:
        // 1. dungeonRun.startRun();
    }

    /** Trang bị danh sách kỹ năng chiến thuật mới cho anh hùng. */
    public void setHeroLoadout(List<Skill> tacticalSkills) {
        // TODO: Hướng dẫn hiện thực:
        // 1. dungeonRun.setHeroLoadout(tacticalSkills);
    }

    /** Xác nhận giữ nguyên bộ kỹ năng hiện tại của anh hùng. */
    public void confirmLoadout() {
        // TODO: Hướng dẫn hiện thực:
        // 1. dungeonRun.confirmLoadout();
    }

    /**
     * Bước vào cuộc chạm trán tiếp theo trên tầng hiện tại, khởi tạo và trả về {@link BattleFlow} mới.
     */
    public BattleFlow startNextBattle() {
        // TODO: Hướng dẫn hiện thực:
        // 1. BattleSession session = dungeonRun.startNextBattle();
        // 2. return new BattleFlow(session);
        return null;
    }

    /**
     * Khép lại trận đánh hiện tại sau khi có kết quả, trao thưởng EXP, hồi phục và đẩy tiến trình hầm ngục.
     */
    public void concludeCurrentBattle() {
        // TODO: Hướng dẫn hiện thực:
        // 1. dungeonRun.concludeBattle();
    }

    /** Trả về mô hình hiển thị bất biến của trạng thái thám hiểm hầm ngục hiện tại. */
    public DungeonView getDungeonView() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return ViewMapper.toDungeonView(dungeonRun);
        return null;
    }

    /** Trả về đối tượng {@link DungeonRun} cơ sở. */
    public DungeonRun getDungeonRun() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return dungeonRun;
        return null;
    }

    /** Trả về nhân vật anh hùng. */
    public Character getHero() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return dungeonRun.getHero();
        return null;
    }

    /** Kiểm tra xem người chơi có đang ở trạm nghỉ lửa trại hay không. */
    public boolean isCampfire() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return dungeonRun.getState() == DungeonRun.DungeonState.CAMPFIRE;
        return false;
    }

    /** Kiểm tra xem người chơi đã hoàn thành hầm ngục hay chưa. */
    public boolean isVictory() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return dungeonRun.isVictory();
        return false;
    }

    /** Kiểm tra xem hiện có đang ở khoảng nghỉ giữa hai cuộc chạm trán hay không. */
    public boolean isBetweenEncounters() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return dungeonRun.getState() == DungeonRun.DungeonState.BETWEEN_ENCOUNTERS;
        return false;
    }
}
