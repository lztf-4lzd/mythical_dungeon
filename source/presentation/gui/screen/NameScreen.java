package source.presentation.gui.screen;

import source.application.flow.GameFlow;
import source.presentation.gui.ui.Theme;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

/**
 * Màn hình cho phép người chơi nhập và kiểm tra tính hợp lệ của tên nhân vật anh hùng.
 * Người phụ trách: Thành viên 5 (Hệ thống Hoạt ảnh Animation & Màn hình Game)
 */
@SuppressWarnings("this-escape")
class NameScreen extends Screen {
    private static final long serialVersionUID = 1L;

    /** Bộ quản lý điều hướng màn hình. */
    private transient ScreenManager screenManager;
    /** Luồng điều phối trò chơi trung tâm. */
    private transient GameFlow gameFlow;
    /** Ô văn bản nhập tên nhân vật. */
    private JTextField nameField;
    /** Nhãn hiển thị thông báo lỗi khi tên không hợp lệ. */
    private JLabel errorLabel;
    /** Nút xác nhận chuyển sang bước tiếp theo. */
    private JButton nextButton;

    /**
     * Khởi tạo màn hình nhập tên nhân vật.
     *
     * @param screenManager bộ quản lý màn hình
     * @param gameFlow luồng trò chơi
     */
    NameScreen(ScreenManager screenManager, GameFlow gameFlow) {
        // TODO: Hướng dẫn hiện thực:
        // 1. this.screenManager = screenManager;
        // 2. this.gameFlow = gameFlow;
        // 3. setBackground(Theme.BG_DARKEST);
        // 4. setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // 5. setBorder(new EmptyBorder(80, 40, 80, 40));
        // 6. JLabel headerLabel = new JLabel("Create Your Character");
    }

    /** Kiểm tra tên đã nhập và chuyển sang màn hình chọn lớp nhân vật nếu hợp lệ. */
    private void handleSubmit() {
        // TODO: Hướng dẫn hiện thực:
        // 1. String name = nameField.getText().trim();
        // 2. if (name.isEmpty())
        // 3. errorLabel.setText("Hero name cannot be empty.");
        // 4. return;
        // 5. nextButton.setEnabled(false);
        // 6. try
    }

    @Override
    void onEnter() {
        // TODO: Hướng dẫn hiện thực:
        // 1. nextButton.setEnabled(true);
        // 2. errorLabel.setText(" ");
        // 3. nameField.requestFocusInWindow();
        // 4. nameField.selectAll();
    }
}
