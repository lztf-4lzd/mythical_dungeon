package source.presentation.gui.screen;

import source.presentation.gui.audio.AudioService;
import source.application.flow.GameFlow;
import source.presentation.gui.ui.Theme;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import java.awt.Component;
import java.awt.Dimension;

/**
 * Màn hình tiêu đề chính (Main Menu) của trò chơi với phong cách hầm ngục giả tưởng tối.
 * Người phụ trách: Thành viên 5 (Hệ thống Hoạt ảnh Animation & Màn hình Game)
 */
@SuppressWarnings("this-escape")
class MenuScreen extends Screen {
    private static final long serialVersionUID = 1L;

    /** Bộ quản lý điều hướng màn hình. */
    private transient ScreenManager screenManager;
    /** Luồng điều phối trò chơi trung tâm. */
    private transient GameFlow gameFlow;

    /**
     * Khởi tạo màn hình menu chính với các nút Bắt đầu mới, Cài đặt và Thoát.
     *
     * @param screenManager bộ quản lý màn hình
     * @param gameFlow luồng trò chơi
     */
    MenuScreen(ScreenManager screenManager, GameFlow gameFlow) {
        // TODO: Hướng dẫn hiện thực:
        // 1. this.screenManager = screenManager;
        // 2. this.gameFlow = gameFlow;
        // 3. setBackground(Theme.BG_DARKEST);
        // 4. setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // 5. setBorder(new EmptyBorder(80, 40, 80, 40));
        // 6. JLabel titleLabel = new JLabel("EPIC TURN-BASED RPG");
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        // TODO: Hướng dẫn hiện thực:
        // 1. super.paintComponent(g);
        // 2. java.awt.image.BufferedImage bg = source.presentation.gui.asset.AssetRegistry.getUiImage("bg_main_menu");
        // 3. if (bg != null)
        // 4. g.drawImage(bg, 0, 0, getWidth(), getHeight(), null);
    }
}
