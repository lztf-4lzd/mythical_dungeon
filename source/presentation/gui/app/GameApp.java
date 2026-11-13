package source.presentation.gui.app;

import source.application.flow.GameFlow;
import source.presentation.gui.screen.ScreenManager;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.Dimension;

/**
 * Cửa sổ ứng dụng chính và điểm khởi chạy cho giao diện đồ họa Swing.
 * Người phụ trách: Thành viên 5 (Hệ thống Hoạt ảnh Animation & Màn hình Game)
 */
public class GameApp {

    /** Tiêu đề cửa sổ trò chơi. */
    private static final String APP_TITLE = "Epic Turn-Based RPG";
    /** Chiều rộng cửa sổ mặc định (pixel). */
    private static final int DEFAULT_WIDTH = 960;
    /** Chiều cao cửa sổ mặc định (pixel). */
    private static final int DEFAULT_HEIGHT = 640;

    /**
     * Khởi tạo và hiển thị cửa sổ chính của trò chơi trên luồng sự kiện Swing (EDT).
     */
    private void start() {
        // TODO: Hướng dẫn hiện thực:
        // 1. JFrame frame = new JFrame(APP_TITLE);
        // 2. frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 3. frame.setMinimumSize(new Dimension(800, 560));
        // 4. frame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        // 5. frame.setLocationRelativeTo(null);
        // 6. GameFlow gameFlow = new GameFlow();
    }

    /**
     * Khởi chạy ứng dụng bất đồng bộ trên luồng sự kiện Swing (EDT).
     */
    public static void launch() {
        // TODO: Hướng dẫn hiện thực:
        // 1. SwingUtilities.invokeLater(() -> new GameApp().start());
    }
}
