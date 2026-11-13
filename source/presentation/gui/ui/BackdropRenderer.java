package source.presentation.gui.ui;

import source.presentation.gui.asset.AssetRegistry;
import source.application.dungeon.FloorCatalog;
import source.application.view.CombatantView;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;

/**
 * Bộ kết xuất bối cảnh môi trường sàn đấu theo 4 chủ đề Hầm Ngục Phe Phái (Dark Fantasy):
 * {@code UNDEAD_CRYPT}, {@code ORC_STRONGHOLD}, {@code INFERNAL_ABYSS} và {@code DARK_CITADEL}.
 * Người phụ trách: Thành viên 4 (UI Pixel & Custom Components)
 */
class BackdropRenderer {

    // =========================================================================
    // Hằng số màu sắc mặc định (giữ tương thích)
    // =========================================================================

    /** Màu đỉnh phông nền sàn đấu mặc định. */
    static final Color STAGE_BG_TOP = new Color(13, 14, 21);
    /** Màu bóng đổ tiếp đất dưới chân chiến binh. */
    private static final Color SHADOW_COLOR = new Color(0, 0, 0, 140);

    /** Màu hào quang nền dưới chân anh hùng khi tới lượt. */
    private static final Color HERO_TURN_GLOW = new Color(74, 144, 226, 75);
    /** Màu vòng tròn chỉ báo dưới chân anh hùng khi tới lượt. */
    private static final Color HERO_TURN_RING = new Color(74, 144, 226, 190);
    /** Màu hào quang nền dưới chân quái vật khi tới lượt. */
    private static final Color MONSTER_TURN_GLOW = new Color(224, 86, 96, 75);
    /** Màu vòng tròn chỉ báo dưới chân quái vật khi tới lượt. */
    private static final Color MONSTER_TURN_RING = new Color(224, 86, 96, 190);

    // =========================================================================
    // Mã tài nguyên ảnh nền hầm ngục phe phái (resources/assets/ui/manifest.json)
    // =========================================================================

    /** Mã tài nguyên ảnh nền Hầm Mộ Bất Tử (Undead Crypt). */
    private static final String BG_DUNGEON_UNDEAD = "bg_dungeon_undead";
    /** Mã tài nguyên ảnh nền Pháo Đài Orc (Orc Stronghold). */
    private static final String BG_DUNGEON_ORC = "bg_dungeon_orc";
    /** Mã tài nguyên ảnh nền Vực Thẳm Địa Ngục (Infernal Abyss). */
    private static final String BG_DUNGEON_INFERNAL = "bg_dungeon_infernal";
    /** Mã tài nguyên ảnh nền Thành Nội Hắc Ám (Dark Citadel). */
    private static final String BG_DUNGEON_CITADEL = "bg_dungeon_citadel";

    /** Họa tiết dither 2x2 kiểu retro giúp phá vỡ các mảng màu phẳng đơn điệu. */
    private static final TexturePaint DITHER_TEXTURE;
    static {
        BufferedImage tile = new BufferedImage(2, 2, BufferedImage.TYPE_INT_ARGB);
        tile.setRGB(0, 0, 0x00000000);
        tile.setRGB(1, 1, 0x00000000);
        tile.setRGB(1, 0, 0x1A000000); // Điểm ảnh tối 10%
        tile.setRGB(0, 1, 0x1A000000);
        DITHER_TEXTURE = new TexturePaint(tile, new Rectangle(0, 0, 2, 2));
    }

    // =========================================================================
    // Chủ đề môi trường theo phe phái
    // =========================================================================

    /**
     * Bảng màu môi trường tương ứng với 4 hầm ngục phe phái.
     */
    private enum EnvironmentTheme {
        /** Hầm Mộ Bất Tử: ánh trăng xanh lạnh, tường gạch cổ và sương mù mặt đất. */
        UNDEAD_CRYPT(
                new Color(10, 16, 20),
                new Color(18, 28, 32),
                new Color(30, 48, 52),
                new Color(42, 62, 68),
                new Color(22, 32, 36),
                new Color(12, 18, 22),
                new Color(70, 110, 115),
                new Color(80, 160, 150, 40)
        ),
        /** Pháo Đài Orc: ánh đuốc rực cháy, đá thô tán đinh sắt và khói chiến trường. */
        ORC_STRONGHOLD(
                new Color(22, 16, 14),
                new Color(36, 24, 18),
                new Color(54, 38, 28),
                new Color(72, 50, 36),
                new Color(38, 26, 20),
                new Color(24, 16, 12),
                new Color(130, 90, 60),
                new Color(220, 120, 50, 45)
        ),
        /** Vực Thẳm Địa Ngục: ánh dung nham đỏ rực, khe nứt hắc diện thạch và tàn lửa. */
        INFERNAL_ABYSS(
                new Color(26, 10, 12),
                new Color(44, 16, 18),
                new Color(64, 24, 26),
                new Color(82, 30, 32),
                new Color(42, 16, 18),
                new Color(26, 10, 12),
                new Color(150, 50, 50),
                new Color(240, 70, 50, 50)
        ),
        /** Thành Nội Hắc Ám: không khí tím hư không, cổ ngữ ma thuật và sàn đá bóng. */
        DARK_CITADEL(
                new Color(16, 12, 24),
                new Color(28, 20, 42),
                new Color(46, 32, 68),
                new Color(64, 44, 92),
                new Color(32, 22, 48),
                new Color(18, 12, 28),
                new Color(110, 80, 150),
                new Color(160, 90, 230, 45)
        );

        /** Màu đỉnh phông nền. */
        private Color bgTop;
        /** Màu đáy phông nền. */
        private Color bgBottom;
        /** Màu mạch vữa tường đá. */
        private Color wallMortar;
        /** Màu bề mặt sàn đá. */
        private Color floorSurface;
        /** Màu thân sàn đá. */
        private Color floorBody;
        /** Màu khe gạch lát sàn. */
        private Color floorTick;
        /** Màu viền sáng mép sàn. */
        private Color floorHighlight;
        /** Màu sương mù / ánh sáng môi trường sát mặt sàn. */
        private Color ambientAura;

        EnvironmentTheme(Color bgTop, Color bgBottom, Color wallMortar,
                         Color floorSurface, Color floorBody, Color floorTick,
                         Color floorHighlight, Color ambientAura) {
            this.bgTop = bgTop;
            this.bgBottom = bgBottom;
            this.wallMortar = wallMortar;
            this.floorSurface = floorSurface;
            this.floorBody = floorBody;
            this.floorTick = floorTick;
            this.floorHighlight = floorHighlight;
            this.ambientAura = ambientAura;
        }
    }

    /** Chủ đề môi trường đang được áp dụng. */
    private EnvironmentTheme currentTheme = EnvironmentTheme.UNDEAD_CRYPT;

    /**
     * Ánh xạ trực tiếp loại hầm ngục ({@link FloorCatalog.DungeonType}) sang chủ đề môi trường.
     */
    void setThemeFromDungeon(FloorCatalog.DungeonType dungeonType) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (dungeonType == null) return;
        // 2. switch (dungeonType)
        // 3. case UNDEAD_CRYPT:
        // 4. this.currentTheme = EnvironmentTheme.UNDEAD_CRYPT;
        // 5. break;
        // 6. case ORC_STRONGHOLD:
    }

    /**
     * Suy luận chủ đề môi trường phe phái dựa trên tên hoặc lớp của quái vật.
     */
    void setThemeFromMonster(CombatantView monster) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (monster == null) return;
        // 2. String name = monster.getDisplayName() != null ? monster.getDisplayName().toLowerCase() : "";
        // 3. String cls = monster.getClassName() != null ? monster.getClassName().toLowerCase() : "";
        // 4. if (name.contains("orc") || name.contains("werebear") || name.contains("werewolf") || cls.contains("orc"))
        // 5. this.currentTheme = EnvironmentTheme.ORC_STRONGHOLD;
        // 6. else if (name.contains("hell") || name.contains("flame") || name.contains("lava") || name.contains("imp")
    }

    /**
     * Phân giải mã tài nguyên ảnh nền trong manifest cho một chủ đề môi trường cho trước.
     */
    private static String getDungeonBackgroundAssetId(EnvironmentTheme theme) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (theme == null) return BG_DUNGEON_UNDEAD;
        // 2. switch (theme)
        // 3. case UNDEAD_CRYPT:
        // 4. return BG_DUNGEON_UNDEAD;
        // 5. case ORC_STRONGHOLD:
        // 6. return BG_DUNGEON_ORC;
        return null;
    }

    /**
     * Vẽ phông nền sàn đấu: ưu tiên ảnh nền hầm ngục phe phái, sau đó đến ảnh nền chung
     * hoặc vẽ kiến trúc đá bằng mã nguồn kèm lớp phủ dither và sương mù.
     */
    void renderBackground(Graphics2D g2d, int w, int groundY, BufferedImage backgroundSprite) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (g2d == null || w <= 0 || groundY <= 0)
        // 2. return;
        // 3. 1. Ưu tiên chính: vẽ ảnh nền hầm ngục phe phái từ manifest UI
        // 4. String factionBgId = getDungeonBackgroundAssetId(currentTheme);
        // 5. if (AssetRegistry.hasUiAsset(factionBgId))
        // 6. BufferedImage factionBg = AssetRegistry.getUiImage(factionBgId);
    }

    /** Phủ lớp họa tiết dither 2x2 lên toàn bộ vùng phông nền. */
    private void renderDitherOverlay(Graphics2D g2d, int w, int groundY) {
        // TODO: Hướng dẫn hiện thực:
        // 1. Paint oldPaint = g2d.getPaint();
        // 2. g2d.setPaint(DITHER_TEXTURE);
        // 3. g2d.fillRect(0, 0, w, groundY);
        // 4. g2d.setPaint(oldPaint);
    }

    /** Vẽ chi tiết tường gạch cổ, quầng trăng lạnh và cột đá cho Hầm Mộ Bất Tử. */
    private void renderUndeadCryptDetails(Graphics2D g2d, int w, int groundY) {
        // TODO: Hướng dẫn hiện thực:
        // 1. Tường gạch hầm mộ lạnh lẽo
        // 2. g2d.setColor(currentTheme.wallMortar);
        // 3. int rowHeight = 22;
        // 4. int brickWidth = 44;
        // 5. int row = 0;
        // 6. for (int y = rowHeight; y < groundY; y += rowHeight)
    }

    /** Vẽ khối đá thô tán đinh sắt và ánh đuốc treo tường cho Pháo Đài Orc. */
    private void renderOrcStrongholdDetails(Graphics2D g2d, int w, int groundY) {
        // TODO: Hướng dẫn hiện thực:
        // 1. Khối đá thô kèm đinh tán sắt
        // 2. g2d.setColor(currentTheme.wallMortar);
        // 3. int rowHeight = 26;
        // 4. int brickWidth = 52;
        // 5. int row = 0;
        // 6. for (int y = rowHeight; y < groundY; y += rowHeight)
    }

    /** Vẽ vách đá bazan núi lửa, ánh nhiệt dung nham và mạch nứt cho Vực Thẳm Địa Ngục. */
    private void renderInfernalAbyssDetails(Graphics2D g2d, int w, int groundY) {
        // TODO: Hướng dẫn hiện thực:
        // 1. Mạch đá bazan núi lửa
        // 2. g2d.setColor(currentTheme.wallMortar);
        // 3. int rowHeight = 24;
        // 4. int brickWidth = 48;
        // 5. int row = 0;
        // 6. for (int y = rowHeight; y < groundY; y += rowHeight)
    }

    /** Vẽ tường đá gothic, vòm cung ma thuật và cầu sáng tím cho Thành Nội Hắc Ám. */
    private void renderDarkCitadelDetails(Graphics2D g2d, int w, int groundY) {
        // TODO: Hướng dẫn hiện thực:
        // 1. Tường đá mịn kiểu kiến trúc gothic
        // 2. g2d.setColor(currentTheme.wallMortar);
        // 3. int rowHeight = 20;
        // 4. int brickWidth = 40;
        // 5. int row = 0;
        // 6. for (int y = rowHeight; y < groundY; y += rowHeight)
    }

    /** Vẽ ngọn đuốc treo tường và quầng sáng xung quanh. */
    private void renderTorchLight(Graphics2D g2d, int cx, int cy, Color glow) {
        // TODO: Hướng dẫn hiện thực:
        // 1. g2d.setColor(glow);
        // 2. g2d.fillOval(cx - 30, cy - 30, 60, 60);
        // 3. g2d.setColor(new Color(255, 200, 100, 160));
        // 4. g2d.fillRect(cx - 2, cy - 4, 4, 8);
        // 5. g2d.setColor(new Color(80, 50, 30));
        // 6. g2d.fillRect(cx - 3, cy + 4, 6, 8);
    }

    /** Vẽ lớp sương mù / hào quang môi trường sát mặt sàn. */
    private void renderAmbientLightingOverlay(Graphics2D g2d, int w, int groundY) {
        // TODO: Hướng dẫn hiện thực:
        // 1. GradientPaint mistGrad = new GradientPaint(
        // 2. 0, groundY - 28, new Color(0, 0, 0, 0),
        // 3. 0, groundY, currentTheme.ambientAura
        // 4. );
        // 5. g2d.setPaint(mistGrad);
        // 6. g2d.fillRect(0, groundY - 28, w, 28);
    }

    // =========================================================================
    // Kết xuất mặt sàn
    // =========================================================================

    /**
     * Vẽ mặt sàn đá của đấu trường từ tọa độ {@code groundY} xuống đáy khung vẽ.
     */
    void renderFloor(Graphics2D g2d, int w, int h, int groundY) {
        // TODO: Hướng dẫn hiện thực:
        // 1. 1. Đường viền sáng ở mép trên mặt sàn
        // 2. g2d.setColor(currentTheme.floorHighlight);
        // 3. g2d.drawLine(0, groundY, w, groundY);
        // 4. 2. Lớp bề mặt sàn đá
        // 5. g2d.setColor(currentTheme.floorSurface);
        // 6. g2d.fillRect(0, groundY + 1, w, 3);
    }

    /**
     * Vẽ bóng đổ tiếp đất và vòng hào quang chỉ báo lượt đánh theo kích thước hiển thị riêng của từng bên.
     */
    void renderPedestalsAndShadows(Graphics2D g2d, int heroCenterX, int monsterCenterX,
                                          int groundY, int heroDrawSize, int monsterDrawSize,
                                          boolean isGameOver, boolean isPlayerTurn) {
        // TODO: Hướng dẫn hiện thực:
        // 1. Bóng đổ tiếp đất co giãn theo kích thước sprite thực tế
        // 2. g2d.setColor(SHADOW_COLOR);
        // 3. int heroShadowW = Math.max(48, (int) (heroDrawSize * 0.72));
        // 4. int monsterShadowW = Math.max(48, (int) (monsterDrawSize * 0.72));
        // 5. g2d.fillOval(heroCenterX - (heroShadowW / 2), groundY - 5, heroShadowW, 10);
        // 6. g2d.fillOval(monsterCenterX - (monsterShadowW / 2), groundY - 5, monsterShadowW, 10);
    }
}
