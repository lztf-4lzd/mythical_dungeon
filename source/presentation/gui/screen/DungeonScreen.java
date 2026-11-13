package source.presentation.gui.screen;

import source.application.dungeon.FloorCatalog;
import source.application.flow.DungeonFlow;
import source.application.flow.GameFlow;
import source.application.view.CombatantView;
import source.presentation.gui.asset.AssetRegistry;
import source.presentation.gui.ui.HtmlLabels;
import source.presentation.gui.ui.Theme;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * Màn hình cho phép người chơi chọn một trong 4 hầm ngục phe phái để bắt đầu chuyến thám hiểm.
 * Hỗ trợ nhấp chuột trực tiếp trên thẻ hầm ngục hoặc nhấp đúp để tiến vào nhanh.
 * Người phụ trách: Thành viên 5 (Hệ thống Hoạt ảnh Animation & Màn hình Game)
 */
@SuppressWarnings("this-escape")
class DungeonScreen extends Screen {
    private static final long serialVersionUID = 1L;

    /** Bộ quản lý điều hướng màn hình. */
    private transient ScreenManager screenManager;
    /** Luồng điều phối trò chơi trung tâm. */
    private transient GameFlow gameFlow;
    /** Nhãn tóm tắt thông tin nhân vật anh hùng ở đầu màn hình. */
    private JLabel heroSummaryLabel;
    /** Nút xác nhận bước vào hầm ngục đã chọn. */
    private JButton enterButton;
    /** Loại hầm ngục đang được chọn hiện tại. */
    private FloorCatalog.DungeonType selectedType = FloorCatalog.DungeonType.UNDEAD_CRYPT;
    /** Mảng 4 thẻ hiển thị hầm ngục phe phái. */
    private final JPanel[] dungeonCards = new JPanel[4];
    /** Mảng 4 nút chọn trên các thẻ hầm ngục. */
    private final JButton[] selectButtons = new JButton[4];
    /** Màu chủ đề đặc trưng cho từng hầm ngục phe phái. */
    private final Color[] factionColors = {
            Theme.FACTION_UNDEAD,
            Theme.FACTION_ORC,
            Theme.FACTION_INFERNAL,
            Theme.FACTION_CITADEL
    };

    /**
     * Khởi tạo màn hình chọn hầm ngục phe phái.
     *
     * @param screenManager bộ quản lý màn hình
     * @param gameFlow luồng trò chơi
     */
    DungeonScreen(ScreenManager screenManager, GameFlow gameFlow) {
        // TODO: Hướng dẫn hiện thực:
        // 1. this.screenManager = screenManager;
        // 2. this.gameFlow = gameFlow;
        // 3. setBackground(Theme.BG_DARKEST);
        // 4. setLayout(new BorderLayout(0, 24));
        // 5. setBorder(new EmptyBorder(36, 36, 36, 36));
        // 6. JPanel topPanel = new JPanel(new GridLayout(0, 1, 0, 8));
    }

    /** Đặt hầm ngục đang chọn và cập nhật hiệu ứng nổi bật trên thẻ. */
    private void selectDungeon(FloorCatalog.DungeonType type) {
        // TODO: Hướng dẫn hiện thực:
        // 1. this.selectedType = type;
        // 2. updateCardHighlights();
    }

    /** Bắt đầu chuyến thám hiểm hầm ngục đã chọn và chuyển tới màn hình chọn kỹ năng. */
    private void enterExpedition() {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (!enterButton.isEnabled()) return;
        // 2. enterButton.setEnabled(false);
        // 3. DungeonFlow dungeonFlow = this.gameFlow.enterDungeon(selectedType);
        // 4. this.screenManager.showSkillSelect(dungeonFlow, false);
    }

    /** Tạo thẻ hiển thị thông tin, danh sách quái, trùm cuối và phần thưởng EXP của một hầm ngục. */
    private JPanel createDungeonCard(
            int index,
            FloorCatalog.DungeonType type,
            String title,
            String bossName,
            String monsterList,
            String expInfo) {
        // TODO: Hướng dẫn hiện thực:
        // 1. JPanel card = new JPanel();
        // 2. card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        // 3. card.setBackground(Theme.BG_CARD_NORMAL);
        // 4. card.setBorder(Theme.createFactionCardBorder(factionColors[index], false));
        // 5. JLabel titleLbl = new JLabel(title);
        // 6. titleLbl.setFont(Theme.FONT_HEADER);
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

    /** Cập nhật viền sáng và trạng thái nút bấm theo thẻ hầm ngục đang được chọn. */
    private void updateCardHighlights() {
        // TODO: Hướng dẫn hiện thực:
        // 1. FloorCatalog.DungeonType[] types = FloorCatalog.DungeonType.values();
        // 2. for (int i = 0; i < types.length; i++)
        // 3. boolean isSelected = (types[i] == selectedType);
        // 4. if (i < dungeonCards.length && dungeonCards[i] != null)
        // 5. dungeonCards[i].setBackground(isSelected ? Theme.BG_CARD_SELECTED : Theme.BG_CARD_NORMAL);
        // 6. dungeonCards[i].setBorder(Theme.createFactionCardBorder(factionColors[i], isSelected));
    }

    @Override
    void onEnter() {
        // TODO: Hướng dẫn hiện thực:
        // 1. enterButton.setEnabled(true);
        // 2. CombatantView hero = gameFlow.getHeroView();
        // 3. if (hero != null)
        // 4. heroSummaryLabel.setText(hero.getName() + " the " + hero.getClassName()
        // 5. + " | HP: " + hero.getCurrentHp() + "/" + hero.getMaxHp()
        // 6. + " | STA: " + hero.getCurrentSta() + "/" + hero.getMaxSta());
    }
}
