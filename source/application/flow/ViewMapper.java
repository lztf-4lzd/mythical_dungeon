package source.application.flow;

import source.application.view.CombatantView;
import source.application.view.BattleView;
import source.application.view.DungeonView;
import source.application.view.SkillView;
import source.application.view.EffectView;
import source.domain.entity.effect.StatusEffect;
import source.domain.entity.character.Character;
import source.domain.entity.skill.Skill;
import source.application.combat.BattleSession;
import source.application.combat.TurnReport;
import source.application.dungeon.DungeonRun;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Ánh xạ các thực thể domain, trạng thái phiên chiến đấu và báo cáo lượt đấu sang các mô hình hiển thị
 * (View Models) bất biến cho màn hình GUI, ngăn giao diện sửa đổi trực tiếp mô hình bên dưới.
 * Người phụ trách: Thành viên 3 (Luồng Game & Sinh Ngục tối)
 */
public final class ViewMapper {

    private ViewMapper() {}

    /**
     * Tạo {@link CombatantView} trực tiếp từ một thực thể {@link Character} đang hoạt động.
     */
    static CombatantView toCombatantView(Character character) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (character == null)
        // 2. return new CombatantView("", "", 1, null, 0, 1, 0, 1, 0, List.of(), false);
        // 3. List<EffectView> effectViews = new ArrayList<>();
        // 4. for (StatusEffect effect : character.getActiveEffects())
        // 5. effectViews.add(EffectView.fromDomain(effect));
        // 6. String typeId = character.getTypeIdentifier();
        return null;
    }

    /**
     * Tạo {@link CombatantView} của anh hùng từ ảnh chụp bất biến lưu trong {@link TurnReport}.
     */
    private static CombatantView toHeroView(TurnReport report) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (report == null || report.getHero() == null)
        // 2. return toCombatantView(null);
        // 3. Character hero = report.getHero();
        // 4. List<EffectView> effectViews = new ArrayList<>();
        // 5. for (StatusEffect effect : report.getHeroActiveEffects())
        // 6. effectViews.add(EffectView.fromDomain(effect));
        return null;
    }

    /**
     * Tạo {@link CombatantView} của quái vật từ ảnh chụp bất biến lưu trong {@link TurnReport}.
     */
    private static CombatantView toMonsterView(TurnReport report) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (report == null || report.getMonster() == null)
        // 2. return toCombatantView(null);
        // 3. Character monster = report.getMonster();
        // 4. List<EffectView> effectViews = new ArrayList<>();
        // 5. for (StatusEffect effect : report.getMonsterActiveEffects())
        // 6. effectViews.add(EffectView.fromDomain(effect));
        return null;
    }

    /**
     * Ánh xạ toàn bộ 10 kỹ năng trong bể kỹ năng của anh hùng sang danh sách {@link SkillView}
     * phục vụ bảng chọn kỹ năng tương tác.
     */
    public static List<SkillView> toPoolSkillViews(Character hero) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (hero == null)
        // 2. return List.of();
        // 3. List<Skill> pool = hero.getSkillPool();
        // 4. List<SkillView> views = new ArrayList<>(pool.size());
        // 5. for (int i = 0; i < pool.size(); i++)
        // 6. Skill skill = pool.get(i);
        return null;
    }

    /**
     * Ánh xạ các kỹ năng đang trang bị của anh hùng sang danh sách {@link SkillView}
     * kèm trạng thái khả dụng và lý do khóa theo quy tắc của phiên chiến đấu.
     */
    private static List<SkillView> toSkillViews(BattleSession session) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (session == null || session.getHero() == null)
        // 2. return List.of();
        // 3. Character hero = session.getHero();
        // 4. List<Skill> skills = hero.getSkills();
        // 5. List<SkillView> views = new ArrayList<>(skills.size());
        // 6. for (int i = 0; i < skills.size(); i++)
        return null;
    }

    /**
     * Xây dựng đối tượng {@link BattleView} tổng hợp cho trạng thái phiên chiến đấu hiện tại.
     */
    static BattleView toBattleView(BattleSession session, List<String> eventLogs) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (session == null)
        // 2. return new BattleView(0, null, null, List.of(), List.of());
        // 3. TurnReport lastReport = session.getLastReport();
        // 4. CombatantView heroView = lastReport != null
        // 5. ? toHeroView(lastReport)
        // 6. : toCombatantView(session.getHero());
        return null;
    }

    /**
     * Xây dựng đối tượng {@link DungeonView} biểu diễn tiến độ trong chuyến đi hầm ngục hiện tại.
     */
    static DungeonView toDungeonView(DungeonRun run) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (run == null)
        // 2. return null;
        // 3. CombatantView heroView = toCombatantView(run.getHero());
        // 4. return new DungeonView(
        // 5. run.getDungeonType(),
        // 6. run.getCurrentEncounterIndex(),
        return null;
    }
}
