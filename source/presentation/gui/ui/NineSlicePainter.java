package source.presentation.gui.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/**
 * Bộ vẽ 9-slice chuyên dụng cho giao diện pixel art 16-bit: giữ nguyên tỷ lệ 1:1 ở 4 góc
 * và co giãn 4 cạnh cùng phần tâm bằng nội suy nearest-neighbor để không bị nhòe.
 * Người phụ trách: Thành viên 4 (UI Pixel & Custom Components)
 */
public class NineSlicePainter {

    /** Ảnh nguồn dùng để cắt 9 phần. */
    private BufferedImage image;
    /** Độ dày lề cắt phía trên (pixel). */
    private int top;
    /** Độ dày lề cắt phía trái (pixel). */
    private int left;
    /** Độ dày lề cắt phía dưới (pixel). */
    private int bottom;
    /** Độ dày lề cắt phía phải (pixel). */
    private int right;

    /**
     * Khởi tạo bộ vẽ 9-slice với hệ số phóng đại mặc định bằng 1.
     */
    NineSlicePainter(BufferedImage image, int top, int left, int bottom, int right) {
        // TODO: Hướng dẫn hiện thực:
        // 1. this.image = image;
        // 2. this.top = Math.max(0, top);
        // 3. this.left = Math.max(0, left);
        // 4. this.bottom = Math.max(0, bottom);
        // 5. this.right = Math.max(0, right);
    }

    /** Trả về ảnh nguồn. */
    BufferedImage getImage() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return image;
        return null;
    }

    /** Trả về độ dày lề cắt trên. */
    int getTop() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return top;
        return 0;
    }

    /** Trả về độ dày lề cắt trái. */
    int getLeft() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return left;
        return 0;
    }

    /** Trả về đối tượng {@link Insets} biểu diễn 4 lề cắt. */
    Insets getInsets() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return new Insets(top, left, bottom, right);
        return null;
    }

    /**
     * Vẽ toàn bộ ảnh 9-slice (bao gồm cả phần tâm) lên ngữ cảnh đồ họa đích.
     *
     * @param g      ngữ cảnh đồ họa đích
     * @param x      tọa độ X đích
     * @param y      tọa độ Y đích
     * @param width  chiều rộng đích
     * @param height chiều cao đích
     */
    void paint(Graphics g, int x, int y, int width, int height) {
        // TODO: Hướng dẫn hiện thực:
        // 1. paint(g, x, y, width, height, true);
    }

    /**
     * Vẽ ảnh 9-slice lên ngữ cảnh đồ họa đích với tùy chọn bỏ qua phần tâm (khi dùng làm viền).
     *
     * @param g          ngữ cảnh đồ họa đích
     * @param x          tọa độ X đích
     * @param y          tọa độ Y đích
     * @param width      chiều rộng đích
     * @param height     chiều cao đích
     * @param drawCenter {@code true} nếu vẽ cả phần tâm, {@code false} nếu chỉ vẽ viền ngoài
     */
    void paint(Graphics g, int x, int y, int width, int height, boolean drawCenter) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (image == null || width <= 0 || height <= 0 || g == null)
        // 2. return;
        // 3. Graphics2D g2d = (Graphics2D) g.create();
        // 4. try
        // 5. g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        // 6. g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
    }
}
