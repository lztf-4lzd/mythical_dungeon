package source.application.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Mô hình hiển thị bất biến biểu diễn toàn bộ trạng thái màn hình chiến đấu tại một thời điểm.
 * Người phụ trách: Thành viên 1 (Data Architect & Project Coordinator)
 */
public final class BattleView {
    private int roundNumber;
    private CombatantView hero;
    private CombatantView monster;
    private List<SkillView> heroSkills;
    private List<String> eventLogs;

    /** Khởi tạo mô hình hiển thị trận đánh từ số vòng, hai chiến binh, kỹ năng và nhật ký. */
    public BattleView(
            int roundNumber,
            CombatantView hero,
            CombatantView monster,
            List<SkillView> heroSkills,
            List<String> eventLogs) {
        // TODO: Hướng dẫn hiện thực:
        // 1. this.roundNumber = roundNumber;
        // 2. this.hero = hero;
        // 3. this.monster = monster;
        // 4. this.heroSkills = heroSkills == null
        // 5. ? List.of()
        // 6. : Collections.unmodifiableList(new ArrayList<>(heroSkills));
    }

    /** Trả về số thứ tự vòng đấu hiện tại. */
    public int getRoundNumber() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return roundNumber;
        return 0;
    }

    /** Trả về mô hình hiển thị của anh hùng. */
    public CombatantView getHero() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return hero;
        return null;
    }

    /** Trả về mô hình hiển thị của quái vật. */
    public CombatantView getMonster() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return monster;
        return null;
    }

    /** Trả về danh sách mô hình hiển thị các kỹ năng đang trang bị của anh hùng. */
    public List<SkillView> getHeroSkills() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return heroSkills;
        return null;
    }

    /** Trả về danh sách nhật ký sự kiện chiến đấu. */
    public List<String> getEventLogs() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return eventLogs;
        return null;
    }
}
