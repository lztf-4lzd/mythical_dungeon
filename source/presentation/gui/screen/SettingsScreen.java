package source.presentation.gui.screen;

import source.presentation.gui.audio.AudioService;
import source.presentation.gui.audio.AudioSettings;
import source.presentation.gui.ui.Theme;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

/**
 * Màn hình cài đặt cho phép điều chỉnh thanh trượt âm lượng tổng, nhạc nền (BGM), hiệu ứng (SFX),
 * bật/tắt tiếng theo thời gian thực và khôi phục cài đặt gốc.
 * Người phụ trách: Thành viên 5 (Hệ thống Hoạt ảnh Animation & Màn hình Game)
 */
@SuppressWarnings("this-escape")
class SettingsScreen extends Screen {
    private static final long serialVersionUID = 1L;

    /** Bộ quản lý điều hướng màn hình. */
    private transient ScreenManager screenManager;
    /** Thanh trượt âm lượng tổng. */
    private JSlider masterSlider;
    /** Thanh trượt âm lượng nhạc nền. */
    private JSlider bgmSlider;
    /** Thanh trượt âm lượng hiệu ứng. */
    private JSlider sfxSlider;
    /** Hộp kiểm tắt tiếng nhạc nền. */
    private JCheckBox bgmMuteCheckbox;
    /** Hộp kiểm tắt tiếng hiệu ứng. */
    private JCheckBox sfxMuteCheckbox;
    /** Nhãn hiển thị phần trăm âm lượng tổng. */
    private JLabel masterValueLabel;
    /** Nhãn hiển thị phần trăm âm lượng nhạc nền. */
    private JLabel bgmValueLabel;
    /** Nhãn hiển thị phần trăm âm lượng hiệu ứng. */
    private JLabel sfxValueLabel;

    /**
     * Khởi tạo màn hình cài đặt âm thanh.
     *
     * @param screenManager bộ quản lý màn hình
     */
    SettingsScreen(ScreenManager screenManager) {
        // TODO: Hướng dẫn hiện thực:
        // 1. this.screenManager = screenManager;
        // 2. setBackground(Theme.BG_DARKEST);
        // 3. setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // 4. setBorder(new EmptyBorder(40, 60, 40, 60));
        // 5. 1. Tiêu đề
        // 6. JLabel titleLabel = new JLabel("SETTINGS");
    }

    @Override
    void onEnter() {
        // TODO: Hướng dẫn hiện thực:
        // 1. refreshUiFromSettings();
    }

    /** Đồng bộ lại giá trị các thanh trượt và hộp kiểm từ đối tượng {@link AudioSettings}. */
    private void refreshUiFromSettings() {
        // TODO: Hướng dẫn hiện thực:
        // 1. AudioSettings settings = AudioService.getInstance().getSettings();
        // 2. masterSlider.setValue((int) (settings.getMasterVolume() * 100));
        // 3. masterValueLabel.setText(masterSlider.getValue() + "%");
        // 4. bgmSlider.setValue((int) (settings.getBgmVolume() * 100));
        // 5. bgmValueLabel.setText(bgmSlider.getValue() + "%");
        // 6. sfxSlider.setValue((int) (settings.getSfxVolume() * 100));
    }

    /** Tạo nhãn tên trường cài đặt. */
    private JLabel createFieldLabel(String text) {
        // TODO: Hướng dẫn hiện thực:
        // 1. JLabel label = new JLabel(text);
        // 2. label.setFont(Theme.FONT_BODY);
        // 3. label.setForeground(Theme.TEXT_PRIMARY);
        // 4. return label;
        return null;
    }

    /** Tạo nhãn hiển thị con số phần trăm âm lượng. */
    private JLabel createValueLabel(String text) {
        // TODO: Hướng dẫn hiện thực:
        // 1. JLabel label = new JLabel(text, JLabel.RIGHT);
        // 2. label.setFont(Theme.FONT_BODY_BOLD);
        // 3. label.setForeground(Theme.GOLD);
        // 4. label.setPreferredSize(new Dimension(50, 24));
        // 5. return label;
        return null;
    }

    /** Tạo thanh trượt âm lượng từ 0 đến 100. */
    private JSlider createSlider(int initial) {
        // TODO: Hướng dẫn hiện thực:
        // 1. JSlider slider = new JSlider(0, 100, initial);
        // 2. slider.setOpaque(false);
        // 3. slider.setBackground(Theme.BG_DARKEST);
        // 4. slider.setForeground(Theme.GOLD);
        // 5. slider.setMajorTickSpacing(25);
        // 6. slider.setPaintTicks(false);
        return null;
    }

    /** Tạo hộp kiểm tắt/bật tiếng. */
    private JCheckBox createCheckbox(String text, boolean initial) {
        // TODO: Hướng dẫn hiện thực:
        // 1. JCheckBox box = new JCheckBox(text, initial);
        // 2. box.setOpaque(false);
        // 3. box.setFont(Theme.FONT_BODY);
        // 4. box.setForeground(Theme.TEXT_SECONDARY);
        // 5. box.setFocusPainted(false);
        // 6. return box;
        return null;
    }
}
