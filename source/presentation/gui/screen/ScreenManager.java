package source.presentation.gui.screen;

import source.presentation.gui.audio.AudioService;
import source.application.flow.BattleFlow;
import source.application.flow.DungeonFlow;
import source.application.flow.GameFlow;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;

/**
 * Điều phối điều hướng và chuyển tiếp giữa các màn hình trong cửa sổ ứng dụng Swing bằng {@link CardLayout}.
 * Người phụ trách: Thành viên 5 (Hệ thống Hoạt ảnh Animation & Màn hình Game)
 */
public class ScreenManager {
    /** Luồng điều phối trò chơi trung tâm. */
    private GameFlow gameFlow;
    /** Khung chứa các thẻ màn hình. */
    private JPanel container;
    /** Bộ bố cục thẻ quản lý việc lật màn hình. */
    private CardLayout cardLayout;

    private MenuScreen mainMenuScreen;
    private NameScreen nameEntryScreen;
    private ClassScreen classSelectionScreen;
    private DungeonScreen dungeonSelectScreen;
    private SkillScreen skillSelectScreen;
    private BattleScreen battleScreen;
    private RewardScreen rewardScreen;
    private ResultScreen resultScreen;
    private SettingsScreen settingsScreen;
    /** Màn hình đang hiển thị hiện tại. */
    private Screen currentScreen;
    /** Cờ ghi nhớ kết quả thắng/thua gần nhất để phát nhạc nền kết quả phù hợp. */
    private boolean lastResultIsVictory;
    /** Luồng hầm ngục đang hoạt động (nếu người chơi đang trong chuyến thám hiểm). */
    private DungeonFlow activeDungeonFlow;

    /**
     * Khởi tạo bộ quản lý màn hình gắn vào cửa sổ {@link JFrame}.
     *
     * @param frame cửa sổ Swing chính
     * @param gameFlow luồng trò chơi
     */
    @SuppressWarnings("this-escape")
    public ScreenManager(JFrame frame, GameFlow gameFlow) {
        // TODO: Hướng dẫn hiện thực:
        // 1. java.awt.Container contentPane = frame != null ? frame.getContentPane() : new JPanel();
        // 2. if (gameFlow == null)
        // 3. throw new IllegalArgumentException("GameFlow cannot be null.");
        // 4. this.gameFlow = gameFlow;
        // 5. this.cardLayout = new CardLayout();
        // 6. this.container = new JPanel(cardLayout);
    }

    /** Khởi tạo toàn bộ các màn hình con và đăng ký vào {@link CardLayout}. */
    private void initScreens() {
        // TODO: Hướng dẫn hiện thực:
        // 1. mainMenuScreen = new MenuScreen(this, gameFlow);
        // 2. nameEntryScreen = new NameScreen(this, gameFlow);
        // 3. classSelectionScreen = new ClassScreen(this, gameFlow);
        // 4. dungeonSelectScreen = new DungeonScreen(this, gameFlow);
        // 5. skillSelectScreen = new SkillScreen(this, gameFlow);
        // 6. battleScreen = new BattleScreen(this);
    }

    /** Thực hiện chuyển tiếp vòng đời giữa màn hình cũ và màn hình mới, đồng thời đồng bộ nhạc nền. */
    private void transitionTo(Screen nextScreen, String cardName) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (currentScreen != null)
        // 2. currentScreen.onExit();
        // 3. currentScreen = nextScreen;
        // 4. nextScreen.onEnter();
        // 5. cardLayout.show(container, cardName);
        // 6. container.revalidate();
    }

    /** Hiển thị màn hình menu chính. */
    public void showMainMenu() {
        // TODO: Hướng dẫn hiện thực:
        // 1. this.activeDungeonFlow = null;
        // 2. transitionTo(mainMenuScreen, "MAIN_MENU");
    }

    /** Hiển thị màn hình nhập tên nhân vật. */
    void showNameEntry() {
        // TODO: Hướng dẫn hiện thực:
        // 1. this.activeDungeonFlow = null;
        // 2. transitionTo(nameEntryScreen, "NAME_ENTRY");
    }

    /** Hiển thị màn hình chọn lớp nhân vật. */
    void showClassSelection() {
        // TODO: Hướng dẫn hiện thực:
        // 1. this.activeDungeonFlow = null;
        // 2. transitionTo(classSelectionScreen, "CLASS_SELECTION");
    }

    /** Hiển thị màn hình chọn hầm ngục phe phái. */
    void showDungeonSelect() {
        // TODO: Hướng dẫn hiện thực:
        // 1. this.activeDungeonFlow = null;
        // 2. transitionTo(dungeonSelectScreen, "DUNGEON_SELECT");
    }

    /** Hiển thị màn hình chọn bộ kỹ năng (đầu chuyến đi hoặc tại lửa trại). */
    void showSkillSelect(DungeonFlow dungeonFlow, boolean isCampfire) {
        // TODO: Hướng dẫn hiện thực:
        // 1. this.activeDungeonFlow = dungeonFlow;
        // 2. skillSelectScreen.setup(dungeonFlow, isCampfire);
        // 3. transitionTo(skillSelectScreen, "SKILL_SELECT");
    }

    /** Hiển thị màn hình chiến đấu. */
    void showBattle(DungeonFlow dungeonFlow, BattleFlow battleFlow) {
        // TODO: Hướng dẫn hiện thực:
        // 1. this.activeDungeonFlow = dungeonFlow;
        // 2. battleScreen.setup(dungeonFlow, battleFlow);
        // 3. transitionTo(battleScreen, "BATTLE");
    }

    /** Hiển thị màn hình phần thưởng giữa các trận chạm trán. */
    void showReward(DungeonFlow dungeonFlow) {
        // TODO: Hướng dẫn hiện thực:
        // 1. this.activeDungeonFlow = dungeonFlow;
        // 2. rewardScreen.setup(dungeonFlow);
        // 3. transitionTo(rewardScreen, "REWARD");
    }

    /** Hiển thị màn hình kết quả cuối chuyến thám hiểm. */
    void showResult(DungeonFlow dungeonFlow, boolean isVictory) {
        // TODO: Hướng dẫn hiện thực:
        // 1. this.activeDungeonFlow = dungeonFlow;
        // 2. this.lastResultIsVictory = isVictory;
        // 3. resultScreen.setup(dungeonFlow, isVictory);
        // 4. transitionTo(resultScreen, "RESULT");
    }

    /** Hiển thị màn hình cài đặt âm thanh. */
    void showSettings() {
        // TODO: Hướng dẫn hiện thực:
        // 1. transitionTo(settingsScreen, "SETTINGS");
    }
}
