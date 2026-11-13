package source.presentation.gui.screen;

import source.application.flow.DungeonFlow;
import source.application.flow.GameFlow;
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
import java.awt.FlowLayout;

/**
 * Màn hình hiển thị kết quả cuối cùng của chuyến thám hiểm (Chiến thắng hoặc Thất bại) và thống kê tổng kết.
 * Người phụ trách: Thành viên 5 (Hệ thống Hoạt ảnh Animation & Màn hình Game)
 */
@SuppressWarnings("this-escape")
class ResultScreen extends Screen {
    private static final long serialVersionUID = 1L;

    /** Bộ quản lý điều hướng màn hình. */
    private transient ScreenManager screenManager;
    /** Luồng điều phối trò chơi trung tâm. */
    private transient GameFlow gameFlow;
    /** Luồng hầm ngục vừa kết thúc. */
    private transient DungeonFlow dungeonFlow;
    /** Cờ báo kết quả chiến thắng hay thất bại. */
    private boolean isVictory;

    /** Nhãn tiêu đề kết quả chuyến thám hiểm. */
    private JLabel outcomeLabel;
    /** Nhãn phụ mô tả chi tiết kết quả. */
    private JLabel subtitleLabel;
    /** Nhãn thống kê cấp độ, số trận thắng và tổng EXP nhận được. */
    private JLabel statsLabel;
    /** Nút quay lại màn hình chọn hầm ngục. */
    private JButton dungeonSelectButton;
    /** Nút quay về menu chính. */
    private JButton mainMenuButton;

    /**
     * Khởi tạo màn hình kết quả chuyến thám hiểm.
     *
     * @param screenManager bộ quản lý màn hình
     * @param gameFlow luồng trò chơi
     */
    ResultScreen(ScreenManager screenManager, GameFlow gameFlow) {
        // TODO: Hướng dẫn hiện thực:
        // 1. this.screenManager = screenManager;
        // 2. this.gameFlow = gameFlow;
        // 3. setBackground(Theme.BG_DARKEST);
        // 4. setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // 5. setBorder(new EmptyBorder(60, 40, 60, 40));
        // 6. outcomeLabel = new JLabel("EXPEDITION COMPLETED", JLabel.CENTER);
    }

    /**
     * Thiết lập dữ liệu luồng hầm ngục và kết quả thắng/thua trước khi hiển thị.
     *
     * @param dungeonFlow luồng hầm ngục vừa kết thúc
     * @param isVictory {@code true} nếu người chơi đã vượt qua hầm ngục
     */
    void setup(DungeonFlow dungeonFlow, boolean isVictory) {
        // TODO: Hướng dẫn hiện thực:
        // 1. this.dungeonFlow = dungeonFlow;
        // 2. this.isVictory = isVictory;
    }

    @Override
    void onEnter() {
        // TODO: Hướng dẫn hiện thực:
        // 1. dungeonSelectButton.setEnabled(true);
        // 2. mainMenuButton.setEnabled(true);
        // 3. if (dungeonFlow == null) return;
        // 4. DungeonView view = dungeonFlow.getDungeonView();
        // 5. if (view == null) return;
        // 6. if (isVictory)
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        // TODO: Hướng dẫn hiện thực:
        // 1. super.paintComponent(g);
        // 2. String bgId = isVictory ? "bg_victory" : "bg_defeat";
        // 3. java.awt.image.BufferedImage bg = source.presentation.gui.asset.AssetRegistry.getUiImage(bgId);
        // 4. if (bg != null)
        // 5. g.drawImage(bg, 0, 0, getWidth(), getHeight(), null);
    }
}
