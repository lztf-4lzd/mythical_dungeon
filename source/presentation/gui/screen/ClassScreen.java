package source.presentation.gui.screen;

import source.application.flow.GameFlow;
import source.presentation.gui.asset.AssetRegistry;
import source.presentation.gui.ui.HtmlLabels;
import source.presentation.gui.ui.Theme;
import source.domain.entity.character.Character;
import source.application.setup.HeroFactory;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Màn hình cho phép người chơi chọn 1 trong 4 lớp nhân vật anh hùng.
 * Hỗ trợ nhấp chuột trực tiếp vào bất kỳ phần nào trên thẻ lớp hoặc nhấp đúp để xác nhận nhanh.
 * Người phụ trách: Thành viên 5 (Hệ thống Hoạt ảnh Animation & Màn hình Game)
 */
@SuppressWarnings("this-escape")
class ClassScreen extends Screen {
    private static final long serialVersionUID = 1L;

    /** Bộ quản lý điều hướng màn hình. */
    private transient ScreenManager screenManager;
    /** Luồng điều phối trò chơi trung tâm. */
    private transient GameFlow gameFlow;
    /** Nhãn tiêu đề màn hình. */
    private JLabel headerLabel;
    /** Khung chứa 4 thẻ lớp nhân vật. */
    private JPanel cardsPanel;
    /** Nút xác nhận lớp nhân vật đã chọn. */
    private JButton confirmButton;

    /** Lớp nhân vật đang được chọn hiện tại. */
    private HeroFactory.HeroClass selectedClass = HeroFactory.HeroClass.WARRIOR;
    /** Mảng các khung thẻ ứng với 4 lớp nhân vật. */
    private final JPanel[] classCards = new JPanel[4];
    /** Mảng các nút chọn trên 4 thẻ lớp nhân vật. */
    private final JButton[] selectButtons = new JButton[4];

    /**
     * Khởi tạo màn hình chọn lớp nhân vật.
     *
     * @param screenManager bộ quản lý màn hình
     * @param gameFlow luồng trò chơi
     */
    ClassScreen(ScreenManager screenManager, GameFlow gameFlow) {
        // TODO: Hướng dẫn hiện thực:
        // 1. this.screenManager = screenManager;
        // 2. this.gameFlow = gameFlow;
        // 3. setBackground(Theme.BG_DARKEST);
        // 4. setLayout(new BorderLayout(0, 20));
        // 5. setBorder(new EmptyBorder(36, 36, 36, 36));
        // 6. headerLabel = new JLabel("Choose Your Hero Class", JLabel.CENTER);
    }

    /** Đặt lớp nhân vật được chọn và cập nhật hiệu ứng nổi bật trên thẻ. */
    private void selectClass(HeroFactory.HeroClass hc) {
        // TODO: Hướng dẫn hiện thực:
        // 1. this.selectedClass = hc;
        // 2. updateCardSelection();
    }

    /** Xác nhận lớp nhân vật đã chọn và chuyển sang màn hình chọn hầm ngục. */
    private void confirmClass() {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (!confirmButton.isEnabled()) return;
        // 2. confirmButton.setEnabled(false);
        // 3. this.gameFlow.selectHeroClass(selectedClass);
        // 4. this.screenManager.showDungeonSelect();
    }

    @Override
    void onEnter() {
        // TODO: Hướng dẫn hiện thực:
        // 1. confirmButton.setEnabled(true);
        // 2. headerLabel.setText("Choose Class for " + gameFlow.getHeroName());
        // 3. cardsPanel.removeAll();
        // 4. List<Character> choices = gameFlow.getHeroChoices();
        // 5. HeroFactory.HeroClass[] classes = HeroFactory.HeroClass.values();
        // 6. for (int i = 0; i < choices.size(); i++)
    }

    /** Tạo thẻ hiển thị thông tin, ảnh minh họa và bảng chỉ số của một lớp nhân vật. */
    private JPanel createClassCard(int index, HeroFactory.HeroClass hc, Character hero) {
        // TODO: Hướng dẫn hiện thực:
        // 1. JPanel card = new JPanel();
        // 2. card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        // 3. card.setBackground(Theme.BG_CARD_NORMAL);
        // 4. card.setBorder(Theme.createCardBorder(false));
        // 5. JLabel nameLbl = new JLabel(hc.getDisplayName());
        // 6. nameLbl.setFont(Theme.FONT_HEADER);
        return null;
    }

    /** Đính kèm bộ lắng nghe nhấp chuột đệ quy cho toàn bộ thành phần con trên thẻ (trừ nút bấm). */
    private void attachCardClickListener(Component comp, MouseAdapter handler) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (comp instanceof JButton) return;
        // 2. comp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        // 3. comp.addMouseListener(handler);
        // 4. if (comp instanceof Container cont)
        // 5. for (Component child : cont.getComponents())
        // 6. attachCardClickListener(child, handler);
    }

    /** Cập nhật màu nền, khung viền và chữ nút bấm theo lớp nhân vật đang được chọn. */
    private void updateCardSelection() {
        // TODO: Hướng dẫn hiện thực:
        // 1. HeroFactory.HeroClass[] classes = HeroFactory.HeroClass.values();
        // 2. for (int i = 0; i < classes.length; i++)
        // 3. boolean isSelected = (classes[i] == selectedClass);
        // 4. if (classCards[i] != null)
        // 5. classCards[i].setBackground(isSelected ? Theme.BG_CARD_SELECTED : Theme.BG_CARD_NORMAL);
        // 6. classCards[i].setBorder(Theme.createCardBorder(isSelected));
    }
}
