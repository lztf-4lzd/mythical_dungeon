package source.presentation.gui.screen;

import source.application.flow.BattleFlow;
import source.application.flow.DungeonFlow;
import source.application.view.CombatantView;
import source.application.view.DungeonView;
import source.presentation.gui.ui.Theme;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Component;
import java.awt.Dimension;

/**
 * Màn hình phần thưởng xuất hiện giữa các trận chạm trán trong cùng tầng hầm ngục,
 * hiển thị lượng hồi phục sau trận, tổng EXP đạt được và nút tiến sang trận tiếp theo.
 * Người phụ trách: Thành viên 5 (Hệ thống Hoạt ảnh Animation & Màn hình Game)
 */
@SuppressWarnings("this-escape")
class RewardScreen extends Screen {
    private static final long serialVersionUID = 1L;

    /** Bộ quản lý điều hướng màn hình. */
    private transient ScreenManager screenManager;
    /** Luồng hầm ngục hiện hành. */
    private transient DungeonFlow dungeonFlow;

    /** Nhãn tiêu đề thông báo thắng trận chạm trán. */
    private JLabel titleLabel;
    /** Nhãn mô tả tỷ lệ hồi máu và thể lực sau trận. */
    private JLabel recoveryLabel;
    /** Nhãn hiển thị cấp độ, HP và STA hiện tại của anh hùng. */
    private JLabel heroStatsLabel;
    /** Nhãn tổng kết lượng EXP đã tích lũy trong chuyến đi. */
    private JLabel expSummaryLabel;
    /** Nút bắt đầu trận chạm trán kế tiếp. */
    private JButton nextEncounterButton;

    /**
     * Khởi tạo màn hình phần thưởng giữa các trận đấu.
     *
     * @param screenManager bộ quản lý màn hình
     */
    RewardScreen(ScreenManager screenManager) {
        // TODO: Hướng dẫn hiện thực:
        // 1. this.screenManager = screenManager;
        // 2. setBackground(Theme.BG_DARKEST);
        // 3. setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // 4. setBorder(new EmptyBorder(60, 40, 60, 40));
        // 5. titleLabel = new JLabel("Encounter Victory!", JLabel.CENTER);
        // 6. titleLabel.setFont(Theme.FONT_TITLE);
    }

    /**
     * Gán luồng hầm ngục hiện hành trước khi hiển thị màn hình phần thưởng.
     *
     * @param dungeonFlow luồng hầm ngục
     */
    void setup(DungeonFlow dungeonFlow) {
        // TODO: Hướng dẫn hiện thực:
        // 1. this.dungeonFlow = dungeonFlow;
    }

    @Override
    void onEnter() {
        // TODO: Hướng dẫn hiện thực:
        // 1. nextEncounterButton.setEnabled(true);
        // 2. if (dungeonFlow == null) return;
        // 3. DungeonView view = dungeonFlow.getDungeonView();
        // 4. if (view == null) return;
        // 5. CombatantView hero = view.getHero();
        // 6. if (hero != null)
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        // TODO: Hướng dẫn hiện thực:
        // 1. super.paintComponent(g);
        // 2. java.awt.image.BufferedImage bg = source.presentation.gui.asset.AssetRegistry.getUiImage("bg_reward");
        // 3. if (bg != null)
        // 4. g.drawImage(bg, 0, 0, getWidth(), getHeight(), null);
    }
}
