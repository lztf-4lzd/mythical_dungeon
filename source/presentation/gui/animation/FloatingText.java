package source.presentation.gui.animation;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

/**
 * Hoạt ảnh chữ nổi bay lên hiển thị con số sát thương, chí mạng, hồi máu,
 * hấp thụ khiên, trượt đòn và sát thương hiệu ứng theo lượt (DoT).
 * Người phụ trách: Thành viên 5 (Hệ thống Hoạt ảnh Animation & Màn hình Game)
 */
public class FloatingText extends BaseAnimation {
    /** Phông chữ đậm chuẩn cho các chữ nổi thông thường. */
    private static final Font DEFAULT_FONT = new Font("SansSerif", Font.BOLD, 12);

    /** Nội dung chuỗi hiển thị. */
    private String text;
    /** Màu chữ hiển thị. */
    private Color color;
    /** Phông chữ hiển thị. */
    private Font font;
    /** Tọa độ X neo ban đầu. */
    private int anchorX;
    /** Tọa độ Y neo ban đầu. */
    private int anchorY;

    /** Tọa độ X hiện tại khi đang bay. */
    private float currentX;
    /** Tọa độ Y hiện tại khi đang bay. */
    private float currentY;
    /** Độ mờ đục (alpha) hiện tại từ 0.0f đến 1.0f. */
    private float alpha = 1.0f;

    /**
     * Khởi tạo chữ nổi với đầy đủ tham số tùy chỉnh.
     *
     * @param text nội dung chuỗi hiển thị
     * @param type phân loại chữ nổi
     * @param anchorX tọa độ X xuất phát
     * @param anchorY tọa độ Y xuất phát
     * @param durationMillis thời lượng bay (mili-giây)
     * @param color màu chữ tùy chọn
     * @param font phông chữ tùy chọn
     */
    public FloatingText(String text, int anchorX, int anchorY, long durationMillis, Color color, Font font) {
        super(durationMillis);
        // TODO: Hướng dẫn hiện thực:
        // 1. this.text = text != null ? text : "";
        // 2. this.anchorX = anchorX;
        // 3. this.anchorY = anchorY;
        // 4. this.currentX = anchorX;
        // 5. this.currentY = anchorY;
        // 6. this.color = color != null ? color : new Color(228, 76, 76);
    }

    @Override
    protected void onUpdate(float progress) {
        // TODO: Hướng dẫn hiện thực:
        // 1. Bay thẳng lên trên tổng cộng 24px
        // 2. currentY = anchorY - (progress * 24.0f);
        // 3. currentX = anchorX;
        // 4. Vòng đời độ đục: hiện dần (0..0.15), giữ nguyên (0.15..0.6), mờ dần (0.6..1.0)
        // 5. if (progress < 0.15f)
        // 6. alpha = Math.min(1.0f, progress / 0.15f);
    }

    @Override
    protected void onCancel() {
        // TODO: Hướng dẫn hiện thực:
        // 1. alpha = 0.0f;
    }

    /**
     * Vẽ chữ nổi có viền tối tương phản lên ngữ cảnh đồ họa.
     *
     * @param g2d ngữ cảnh đồ họa Graphics2D
     */
    public void render(Graphics2D g2d) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (alpha <= 0.01f || text.isEmpty())
        // 2. return;
        // 3. Graphics2D g = (Graphics2D) g2d.create();
        // 4. try
        // 5. g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        // 6. g.setFont(font);
    }
}
