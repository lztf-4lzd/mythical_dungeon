package source.presentation.gui.animation;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Hoạt ảnh vòng xung nhịp phép thuật lan rộng từ bệ đứng hoặc cơ thể người niệm phép rồi mờ dần.
 * Người phụ trách: Thành viên 5 (Hệ thống Hoạt ảnh Animation & Màn hình Game)
 */
public class PulseAnimation extends BaseAnimation {

    /** Màu vòng xung nhịp mặc định (xanh lam phép thuật). */
    private static final Color DEFAULT_PULSE_COLOR = new Color(74, 144, 226);

    /** Cờ xác định người niệm phép là anh hùng hay quái vật. */
    private boolean isAttackerHero;
    /** Màu sắc của vòng xung nhịp. */
    private Color color;
    /** Bán kính hiện tại của vòng xung nhịp (pixel). */
    private float currentRadius = 12.0f;
    /** Độ mờ đục (alpha) hiện tại của vòng xung nhịp. */
    private float alpha = 0.8f;

    /**
     * Khởi tạo hoạt ảnh vòng xung nhịp niệm phép.
     *
     * @param durationMillis thời lượng hoạt ảnh (mili-giây)
     * @param isAttackerHero {@code true} nếu người niệm là anh hùng
     * @param color màu vòng xung nhịp (dùng màu mặc định nếu null)
     */
    public PulseAnimation(long durationMillis, boolean isAttackerHero, Color color) {
        super(durationMillis);
        // TODO: Hướng dẫn hiện thực:
        // 1. this.isAttackerHero = isAttackerHero;
        // 2. this.color = color != null ? color : DEFAULT_PULSE_COLOR;
    }

    @Override
    protected void onUpdate(float progress) {
        // TODO: Hướng dẫn hiện thực:
        // 1. Mở rộng bán kính từ 12px lên 38px
        // 2. currentRadius = 12.0f + (progress * 26.0f);
        // 3. Giảm dần độ đục từ 0.8f về 0.0f
        // 4. alpha = Math.max(0.0f, (1.0f - progress) * 0.8f);
    }

    @Override
    protected void onCancel() {
        // TODO: Hướng dẫn hiện thực:
        // 1. alpha = 0.0f;
    }

    /**
     * Vẽ vòng xung nhịp phép thuật tại tâm chỉ định.
     *
     * @param g2d ngữ cảnh đồ họa Graphics2D
     * @param centerX tọa độ X tâm vòng xung
     * @param centerY tọa độ Y tâm vòng xung
     */
    public void render(Graphics2D g2d, int centerX, int centerY) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (alpha <= 0.01f || isFinished())
        // 2. return;
        // 3. Graphics2D g = (Graphics2D) g2d.create();
        // 4. try
        // 5. g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        // 6. g.setColor(color);
    }

    /** Kiểm tra người niệm phép có phải anh hùng hay không. */
    public boolean isAttackerHero() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return isAttackerHero;
        return false;
    }
}
