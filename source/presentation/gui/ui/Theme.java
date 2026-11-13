package source.presentation.gui.ui;

import source.domain.entity.effect.EffectType;
import source.domain.entity.elemental.Elemental;
import source.presentation.gui.asset.AssetProvider;
import source.presentation.gui.asset.AssetRegistry;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.Locale;

/**
 * Trung tâm quản lý hằng số thiết kế (design tokens), bảng màu, hệ thống phông chữ
 * và các hàm trang trí thành phần UI theo phong cách hầm ngục giả tưởng tối màu.
 * Người phụ trách: Thành viên 4 (UI Pixel & Custom Components)
 */
public final class Theme {

    /** Ngăn khởi tạo lớp tiện ích tĩnh. */
    private Theme() {
        // TODO: Hướng dẫn hiện thực:
        // - Viết xử lý logic tại đây theo yêu cầu thiết kế.
    }

    // =========================================================================
    // 1. Bảng màu (Dark Dungeon / Fantasy Slate)
    // =========================================================================

    /** Màu nền tối nhất cho cửa sổ chính. */
    public static final Color BG_DARKEST = new Color(14, 15, 20);
    /** Màu nền cho các khung bảng điều khiển. */
    public static final Color BG_PANEL = new Color(28, 30, 42);
    /** Màu nền thẻ ở trạng thái mặc định. */
    public static final Color BG_CARD_NORMAL = new Color(34, 38, 52);
    /** Màu nền thẻ khi đang được chọn hoặc tô sáng. */
    public static final Color BG_CARD_SELECTED = new Color(46, 52, 74);
    /** Màu nền khung nhật ký chiến đấu. */
    public static final Color BG_CONSOLE = new Color(12, 14, 18);

    /** Màu nền thẻ thông tin anh hùng. */
    public static final Color BG_HERO_CARD = new Color(26, 32, 48);
    /** Màu nền thẻ thông tin quái vật. */
    public static final Color BG_MONSTER_CARD = new Color(44, 28, 32);

    /** Màu đường viền thông thường. */
    public static final Color BORDER_NORMAL = new Color(54, 58, 78);
    /** Màu đường viền sáng nhẹ. */
    private static final Color BORDER_LIGHT = new Color(72, 78, 105);
    /** Màu đường viền vàng kim (nhấn mạnh/đang chọn). */
    public static final Color BORDER_GOLD = new Color(212, 175, 55);
    /** Màu đường viền khung anh hùng. */
    public static final Color BORDER_HERO = new Color(68, 114, 176);
    /** Màu đường viền khung quái vật. */
    public static final Color BORDER_MONSTER = new Color(176, 58, 58);

    /** Màu nhấn vàng kim chuẩn. */
    public static final Color GOLD = new Color(230, 200, 110);
    /** Màu nhấn vàng kim rực sáng. */
    public static final Color GOLD_BRIGHT = new Color(255, 225, 130);
    /** Màu đỏ hồng ngọc (sát thương/cảnh báo). */
    public static final Color RUBY = new Color(228, 76, 76);
    /** Màu đỏ tương phản cao trên nền tối (đạt chuẩn WCAG AA). */
    public static final Color RUBY_BRIGHT = new Color(255, 115, 115);
    /** Màu xanh lam ngọc bích. */
    public static final Color SAPPHIRE = new Color(74, 144, 226);
    /** Màu xanh lục bảo (hồi phục/tăng chỉ số). */
    public static final Color EMERALD = new Color(66, 184, 96);
    /** Màu xanh lục tương phản cao cho thông báo chiến thắng. */
    public static final Color EMERALD_BRIGHT = new Color(110, 255, 148);
    /** Màu vàng hổ phách. */
    public static final Color AMBER = new Color(240, 170, 50);
    /** Màu tím huyền bí (Boss/hiệu ứng đặc biệt). */
    public static final Color PURPLE = new Color(160, 115, 225);

    /** Màu dải lấp đầy thanh máu anh hùng. */
    public static final Color HP_BAR = new Color(52, 180, 80);
    /** Màu nền rãnh thanh máu anh hùng. */
    public static final Color HP_BAR_BG = new Color(34, 46, 36);
    /** Màu dải lấp đầy thanh thể lực anh hùng. */
    public static final Color STA_BAR = new Color(54, 134, 224);
    /** Màu nền rãnh thanh thể lực anh hùng. */
    public static final Color STA_BAR_BG = new Color(26, 38, 58);
    /** Màu dải lấp đầy thanh máu quái vật. */
    public static final Color HP_BAR_MONSTER = RUBY;
    /** Màu nền rãnh thanh máu quái vật. */
    public static final Color HP_BAR_MONSTER_BG = new Color(55, 30, 34);
    /** Màu dải lấp đầy thanh thể lực quái vật. */
    public static final Color STA_BAR_MONSTER = AMBER;
    /** Màu nền rãnh thanh thể lực quái vật. */
    public static final Color STA_BAR_MONSTER_BG = new Color(55, 42, 30);

    /** Màu đại diện nguyên tố Hỏa (Fire). */
    private static final Color ELEM_FIRE = new Color(235, 87, 54);
    /** Màu đại diện nguyên tố Phong (Wind). */
    private static final Color ELEM_WIND = new Color(46, 204, 113);
    /** Màu đại diện nguyên tố Quang (Holy). */
    private static final Color ELEM_HOLY = new Color(241, 196, 15);
    /** Màu đại diện nguyên tố Ám (Dark). */
    private static final Color ELEM_DARK = new Color(155, 89, 182);
    /** Màu đại diện hệ Vô thuộc tính (Normal). */
    public static final Color ELEM_NORMAL = new Color(178, 190, 205);

    /** Màu dải lấp đầy thanh lá chắn (Shield). */
    public static final Color SHIELD_BAR = new Color(90, 165, 250);

    /** Màu đặc trưng phe Hầm Mộ Bất Tử. */
    public static final Color FACTION_UNDEAD = new Color(100, 170, 160);
    /** Màu đặc trưng phe Pháo Đài Orc. */
    public static final Color FACTION_ORC = new Color(210, 120, 60);
    /** Màu đặc trưng phe Vực Thẳm Địa Ngục. */
    public static final Color FACTION_INFERNAL = new Color(230, 70, 60);
    /** Màu đặc trưng phe Thành Nội Hắc Ám. */
    public static final Color FACTION_CITADEL = new Color(160, 100, 220);

    /** Màu nền nút kỹ năng khi khả dụng. */
    public static final Color BG_SKILL_ENABLED = new Color(42, 54, 78);
    /** Màu nền nút kỹ năng khi bị vô hiệu hóa. */
    public static final Color BG_SKILL_DISABLED = new Color(24, 26, 36);

    /** Màu chữ tiêu đề lớn. */
    public static final Color TEXT_TITLE = new Color(242, 242, 252);
    /** Màu chữ nội dung chính. */
    public static final Color TEXT_PRIMARY = new Color(225, 228, 240);
    /** Màu chữ nội dung phụ. */
    public static final Color TEXT_SECONDARY = new Color(165, 170, 195);
    /** Màu chữ làm mờ nhưng vẫn đảm bảo độ tương phản WCAG AA trên nền tối. */
    public static final Color TEXT_MUTED = new Color(158, 163, 190);
    /** Màu chữ hiển thị máu của quái vật. */
    public static final Color TEXT_MONSTER_HP = new Color(255, 170, 170);

    // =========================================================================
    // 2. Hệ thống phông chữ (Typography Hierarchy)
    // =========================================================================

    /** Phông tiêu đề cực lớn (32pt đậm). */
    public static final Font FONT_TITLE_LARGE = new Font("SansSerif", Font.BOLD, 32);
    /** Phông tiêu đề màn hình (24pt đậm). */
    public static final Font FONT_TITLE = new Font("SansSerif", Font.BOLD, 24);
    /** Phông tiêu đề mục/thẻ (17pt đậm). */
    public static final Font FONT_HEADER = new Font("SansSerif", Font.BOLD, 17);
    /** Phông phụ đề (15pt thường). */
    public static final Font FONT_SUBTITLE = new Font("SansSerif", Font.PLAIN, 15);
    /** Phông văn bản nội dung chính (13pt thường). */
    public static final Font FONT_BODY = new Font("SansSerif", Font.PLAIN, 13);
    /** Phông văn bản nội dung in đậm (13pt đậm). */
    public static final Font FONT_BODY_BOLD = new Font("SansSerif", Font.BOLD, 13);
    /** Phông chú thích nhỏ (12pt thường). */
    public static final Font FONT_CAPTION = new Font("SansSerif", Font.PLAIN, 12);
    /** Phông chú thích nhỏ in đậm (12pt đậm). */
    public static final Font FONT_CAPTION_BOLD = new Font("SansSerif", Font.BOLD, 12);
    /** Phông đẳng cách dùng cho nhật ký chiến đấu (13pt). */
    public static final Font FONT_MONO = new Font("Monospaced", Font.PLAIN, 13);
    /** Phông chữ trên nút bấm tiêu chuẩn (14pt đậm). */
    public static final Font FONT_BUTTON = new Font("SansSerif", Font.BOLD, 14);
    /** Phông chữ trên nút bấm lớn (15pt đậm). */
    public static final Font FONT_BUTTON_LARGE = new Font("SansSerif", Font.BOLD, 15);

    // =========================================================================
    // 3. Tạo đường viền khung & thẻ (Borders & Card Factories)
    // =========================================================================

    /** Tạo đường viền cho bảng điều khiển chung. */
    public static Border createPanelBorder() {
        // TODO: Hướng dẫn hiện thực:
        // 1. Border skinned = PixelBorders.createPanelBorder();
        // 2. if (skinned != null)
        // 3. return skinned;
        // 4. return BorderFactory.createCompoundBorder(
        // 5. new LineBorder(BORDER_NORMAL, 1, true),
        // 6. new EmptyBorder(12, 14, 12, 14));
        return null;
    }

    /** Tạo đường viền cho thẻ lựa chọn theo trạng thái chọn. */
    public static Border createCardBorder(boolean selected) {
        // TODO: Hướng dẫn hiện thực:
        // 1. Border skinned = PixelBorders.createCardBorder(selected);
        // 2. if (skinned != null)
        // 3. return skinned;
        // 4. return BorderFactory.createCompoundBorder(
        // 5. new LineBorder(selected ? BORDER_GOLD : BORDER_NORMAL, selected ? 2 : 1, true),
        // 6. new EmptyBorder(16, 14, 16, 14));
        return null;
    }

    /** Tạo đường viền cho thẻ thông tin chiến binh (anh hùng hoặc quái vật). */
    private static Border createCombatantCardBorder(boolean isHero) {
        // TODO: Hướng dẫn hiện thực:
        // 1. Border skinned = PixelBorders.createCombatantCardBorder(isHero);
        // 2. if (skinned != null)
        // 3. return skinned;
        // 4. Color border = isHero ? BORDER_HERO : BORDER_MONSTER;
        // 5. return BorderFactory.createCompoundBorder(
        // 6. new LineBorder(border, 2, true),
        return null;
    }

    /**
     * Tạo đường viền 9-slice cho ô kỹ năng dựa theo vị trí ô (0: Đánh thường, 1-3: Kỹ năng thường, 4: Tối thượng)
     * và trạng thái đang chọn.
     */
    public static Border createSkillSlotBorder(int slotIndex, boolean selected) {
        // TODO: Hướng dẫn hiện thực:
        // 1. String assetId;
        // 2. if (slotIndex == 0)
        // 3. assetId = "slot_basic";
        // 4. else if (slotIndex == 4)
        // 5. assetId = "slot_ultimate";
        // 6. else
        return null;
    }

    /**
     * Tạo đường viền 9-slice cho ô nhập văn bản bằng tài nguyên {@code input_text_field}.
     */
    public static Border createInputFieldBorder() {
        // TODO: Hướng dẫn hiện thực:
        // 1. NineSlicePainter painter = getUiNineSlice("input_text_field");
        // 2. if (painter != null && painter.getImage() != null)
        // 3. return PixelBorders.createNineSliceBorder(painter, new java.awt.Insets(8, 12, 8, 12));
        // 4. return BorderFactory.createCompoundBorder(
        // 5. new LineBorder(BORDER_LIGHT, 1),
        // 6. new EmptyBorder(8, 12, 8, 12));
        return null;
    }

    // =========================================================================
    // 4. Trang trí nút bấm & thanh tiến trình (Button & ProgressBar Stylers)
    // =========================================================================

    /** Trang trí nút hành động chính (màu xanh lam). */
    public static void stylePrimaryButton(JButton btn) {
        // TODO: Hướng dẫn hiện thực:
        // 1. btn.setFont(FONT_BUTTON);
        // 2. btn.setForeground(Color.WHITE);
        // 3. btn.setBackground(new Color(56, 116, 204));
        // 4. btn.setFocusPainted(false);
        // 5. btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        // 6. btn.setBorder(BorderFactory.createCompoundBorder(
    }

    /** Trang trí nút xác nhận / hoàn tất (màu xanh lục). */
    public static void styleSuccessButton(JButton btn) {
        // TODO: Hướng dẫn hiện thực:
        // 1. btn.setFont(FONT_BUTTON);
        // 2. btn.setForeground(Color.WHITE);
        // 3. btn.setBackground(new Color(50, 135, 75));
        // 4. btn.setFocusPainted(false);
        // 5. btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        // 6. btn.setBorder(BorderFactory.createCompoundBorder(
    }

    /** Trang trí nút phụ / quay lại (màu xám đá). */
    public static void styleSecondaryButton(JButton btn) {
        // TODO: Hướng dẫn hiện thực:
        // 1. btn.setFont(FONT_BUTTON);
        // 2. btn.setForeground(TEXT_PRIMARY);
        // 3. btn.setBackground(new Color(60, 64, 84));
        // 4. btn.setFocusPainted(false);
        // 5. btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        // 6. btn.setBorder(BorderFactory.createCompoundBorder(
    }

    /** Trang trí nút nguy hiểm / thoát (màu đỏ sẫm). */
    public static void styleDangerButton(JButton btn) {
        // TODO: Hướng dẫn hiện thực:
        // 1. btn.setFont(FONT_BUTTON);
        // 2. btn.setForeground(Color.WHITE);
        // 3. btn.setBackground(new Color(165, 52, 52));
        // 4. btn.setFocusPainted(false);
        // 5. btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        // 6. btn.setBorder(BorderFactory.createCompoundBorder(
    }

    /**
     * Khởi tạo bộ vẽ {@link NineSlicePainter} cho mã tài nguyên UI đã đăng ký.
     */
    public static NineSlicePainter getUiNineSlice(String assetId) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (assetId == null) return null;
        // 2. AssetProvider.UiAsset asset = AssetRegistry.getUiAsset(assetId);
        // 3. BufferedImage img = AssetRegistry.getUiImage(assetId);
        // 4. if (img == null) return null;
        // 5. int top = 4;
        // 6. int left = 4;
        return null;
    }

    /**
     * Áp dụng giao diện pixel art cho thanh máu (HP) của anh hùng.
     */
    public static void styleHpBar(JProgressBar bar) {
        // TODO: Hướng dẫn hiện thực:
        // 1. bar.setUI(new PixelBarUI("bar_hp_frame", "bar_hp_fill", HP_BAR, HP_BAR_BG));
        // 2. bar.setBackground(HP_BAR_BG);
        // 3. bar.setForeground(HP_BAR);
        // 4. bar.setBorderPainted(false);
    }

    /**
     * Áp dụng giao diện pixel art cho thanh máu (HP) của quái vật (màu đỏ hồng ngọc).
     */
    public static void styleMonsterHpBar(JProgressBar bar) {
        // TODO: Hướng dẫn hiện thực:
        // 1. bar.setUI(new PixelBarUI("bar_hp_frame", null, HP_BAR_MONSTER, HP_BAR_MONSTER_BG));
        // 2. bar.setBackground(HP_BAR_MONSTER_BG);
        // 3. bar.setForeground(HP_BAR_MONSTER);
        // 4. bar.setBorderPainted(false);
    }

    /**
     * Áp dụng giao diện pixel art cho thanh thể lực (Stamina).
     */
    public static void styleStaminaBar(JProgressBar bar) {
        // TODO: Hướng dẫn hiện thực:
        // 1. bar.setUI(new PixelBarUI("bar_stamina_frame", "bar_stamina_fill", STA_BAR, STA_BAR_BG));
        // 2. bar.setBackground(STA_BAR_BG);
        // 3. bar.setForeground(STA_BAR);
        // 4. bar.setBorderPainted(false);
    }

    /**
     * Trả về màu sắc đặc trưng cho từng loại hiệu ứng trạng thái trong chiến đấu.
     */
    public static Color getEffectColor(EffectType effectType) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (effectType == null) return TEXT_SECONDARY;
        // 2. switch (effectType)
        // 3. case ATK_BUFF:
        // 4. return EMERALD;
        // 5. case DEF_BUFF:
        // 6. return SAPPHIRE;
        return null;
    }

    /** Tạo đường viền thẻ hầm ngục theo màu phe phái và trạng thái đang chọn. */
    public static Border createFactionCardBorder(Color factionColor, boolean selected) {
        // TODO: Hướng dẫn hiện thực:
        // 1. NineSlicePainter painter = getUiNineSlice("card_combatant_frame");
        // 2. if (painter == null || painter.getImage() == null)
        // 3. painter = getUiNineSlice("panel_main");
        // 4. if (painter != null && painter.getImage() != null)
        // 5. Border nineSlice = PixelBorders.createNineSliceBorder(painter, new EmptyBorder(12, 10, 12, 10).getBorderInsets());
        // 6. Color border = selected ? BORDER_GOLD : (factionColor != null ? factionColor : BORDER_NORMAL);
        return null;
    }

    /**
     * Tải và co giãn biểu tượng huy hiệu cho một nguyên tố.
     */
    public static ImageIcon getElementBadgeIcon(Elemental element, int size) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (element == null) element = Elemental.NORMAL;
        // 2. String elemName = element.name().toLowerCase(Locale.ROOT);
        // 3. String path = "resources/assets/ui/icons/elements/elem_" + elemName + ".png";
        // 4. java.io.File f = new java.io.File(path);
        // 5. if (!f.exists())
        // 6. path = "resources/assets/ui/icons/elements/icon_element_" + elemName + ".png";
        return null;
    }

    /**
     * Tải và co giãn biểu tượng cho một loại hiệu ứng trạng thái.
     */
    public static ImageIcon getEffectIcon(EffectType effectType, int size) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (effectType == null) return null;
        // 2. String typeName = effectType.name().toLowerCase(Locale.ROOT);
        // 3. String path = "resources/assets/ui/icons/effects/eff_" + typeName + ".png";
        // 4. java.io.File f = new java.io.File(path);
        // 5. if (!f.exists())
        // 6. path = "resources/assets/ui/icons/effects/icon_effect_" + typeName + ".png";
        return null;
    }
}
