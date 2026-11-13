package source.application.flow;

import source.application.view.CombatantView;
import source.domain.entity.character.Character;
import source.application.dungeon.FloorCatalog;
import source.application.setup.HeroFactory;

import java.util.List;

/**
 * Bộ điều phối cấp cao quản lý chuyển đổi trạng thái giữa menu chính, nhập tên anh hùng,
 * chọn lớp nhân vật, chọn hầm ngục và chuyến thám hiểm đang diễn ra.
 * Người phụ trách: Thành viên 3 (Luồng Game & Sinh Ngục tối)
 */
public class GameFlow {

    /** Các trạng thái luồng nội bộ của trò chơi. */
    private enum FlowState {
        MAIN_MENU,
        NAME_ENTRY,
        CLASS_SELECTION,
        DUNGEON_SELECTION,
        EXPLORATION
    }

    /** Trạng thái luồng hiện tại. */
    private FlowState state;
    /** Tên anh hùng đã nhập. */
    private String heroName;
    /** Cá thể nhân vật anh hùng đã tạo. */
    private Character hero;
    /** Luồng hầm ngục đang hoạt động. */
    private DungeonFlow activeDungeonFlow;

    /** Khởi tạo luồng trò chơi ở trạng thái menu chính. */
    public GameFlow() {
        // TODO: Hướng dẫn hiện thực:
        // 1. this.state = FlowState.MAIN_MENU;
        // 2. this.heroName = "";
        // 3. this.hero = null;
        // 4. this.activeDungeonFlow = null;
    }

    /** Bắt đầu trò chơi mới, chuyển từ menu chính sang màn hình nhập tên. */
    public void startNewGame() {
        // TODO: Hướng dẫn hiện thực:
        // 1. this.state = FlowState.NAME_ENTRY;
    }

    /** Lưu tên anh hùng và chuyển sang màn hình chọn lớp nhân vật. */
    public void submitHeroName(String name) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (state != FlowState.NAME_ENTRY)
        // 2. throw new IllegalStateException("Not currently entering hero name. Current state: " + state);
        // 3. if (name == null || name.trim().isEmpty())
        // 4. throw new IllegalArgumentException("Hero name cannot be null or blank.");
        // 5. this.heroName = name.trim();
        // 6. this.state = FlowState.CLASS_SELECTION;
    }

    /** Trả về danh sách 4 cá thể anh hùng mẫu ứng với tên đã nhập để hiển thị xem trước. */
    public List<Character> getHeroChoices() {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (heroName.isEmpty())
        // 2. throw new IllegalStateException("Hero name has not been set yet.");
        // 3. return HeroFactory.createHeroChoices(heroName);
        return null;
    }

    /** Chọn lớp anh hùng, khởi tạo nhân vật người chơi và chuyển sang màn hình chọn hầm ngục. */
    public Character selectHeroClass(HeroFactory.HeroClass heroClass) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (state != FlowState.CLASS_SELECTION)
        // 2. throw new IllegalStateException("Not currently selecting hero class. Current state: " + state);
        // 3. this.hero = HeroFactory.createHero(heroClass, heroName);
        // 4. this.state = FlowState.DUNGEON_SELECTION;
        // 5. return hero;
        return null;
    }

    /** Bắt đầu chế độ thám hiểm hầm ngục với nhân vật anh hùng đã chọn. */
    public DungeonFlow enterDungeon(FloorCatalog.DungeonType dungeonType) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (state != FlowState.DUNGEON_SELECTION && state != FlowState.EXPLORATION)
        // 2. throw new IllegalStateException("Cannot enter dungeon in state: " + state);
        // 3. if (hero == null)
        // 4. throw new IllegalStateException("Hero has not been created yet.");
        // 5. this.activeDungeonFlow = new DungeonFlow(hero, dungeonType);
        // 6. this.activeDungeonFlow.startRun();
        return null;
    }

    /** Quay lại màn hình chọn lớp nhân vật từ màn hình chọn hầm ngục. */
    public void returnToClassSelection() {
        // TODO: Hướng dẫn hiện thực:
        // 1. this.activeDungeonFlow = null;
        // 2. this.hero = null;
        // 3. this.state = FlowState.CLASS_SELECTION;
    }

    /** Quay lại màn hình nhập tên từ màn hình chọn lớp nhân vật. */
    public void returnToNameEntry() {
        // TODO: Hướng dẫn hiện thực:
        // 1. this.activeDungeonFlow = null;
        // 2. this.hero = null;
        // 3. this.state = FlowState.NAME_ENTRY;
    }

    /** Quay lại màn hình chọn hầm ngục sau khi hoàn thành hoặc rời khỏi chuyến thám hiểm. */
    public void returnToDungeonSelection() {
        // TODO: Hướng dẫn hiện thực:
        // 1. this.activeDungeonFlow = null;
        // 2. this.state = FlowState.DUNGEON_SELECTION;
    }

    /** Quay về menu chính và xóa toàn bộ trạng thái lượt chơi hiện tại. */
    public void returnToMainMenu() {
        // TODO: Hướng dẫn hiện thực:
        // 1. this.state = FlowState.MAIN_MENU;
        // 2. this.heroName = "";
        // 3. this.hero = null;
        // 4. this.activeDungeonFlow = null;
    }

    /** Trả về tên anh hùng đã nhập. */
    public String getHeroName() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return heroName;
        return null;
    }

    /** Trả về đối tượng anh hùng hiện tại. */
    public Character getHero() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return hero;
        return null;
    }

    /** Trả về mô hình hiển thị của anh hùng hiện tại. */
    public CombatantView getHeroView() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return ViewMapper.toCombatantView(hero);
        return null;
    }

    /** Trả về luồng hầm ngục đang hoạt động (nếu có). */
    public DungeonFlow getActiveDungeonFlow() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return activeDungeonFlow;
        return null;
    }
}
