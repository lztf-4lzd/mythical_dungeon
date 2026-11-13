package source.presentation.gui.animation;

import java.util.function.Consumer;

/**
 * Hoạt ảnh chớp sáng trúng đòn điều biến cường độ alpha (0.0 -> 1.0 -> 0.0)
 * phủ lên hình vẽ nhân vật khi bị đánh trúng và trả về 0 khi kết thúc hoặc bị hủy.
 * Người phụ trách: Thành viên 5 (Hệ thống Hoạt ảnh Animation & Màn hình Game)
 */
public class FlashAnimation extends BaseAnimation {
    /** Hàm nhận giá trị alpha chớp sáng ở mỗi nhịp. */
    private Consumer<Float> alphaConsumer;
    /** Cường độ alpha chớp sáng hiện tại (0.0 đến 1.0). */
    private float flashAlpha = 0.0f;

    /**
     * Khởi tạo hoạt ảnh chớp sáng khi trúng đòn.
     *
     * @param durationMillis thời lượng chớp sáng (mili-giây)
     * @param forHero {@code true} nếu áp dụng cho anh hùng
     * @param alphaConsumer hàm nhận giá trị alpha mỗi nhịp
     */
    public FlashAnimation(long durationMillis, Consumer<Float> alphaConsumer) {
        super(durationMillis);
        // TODO: Hướng dẫn hiện thực:
        // 1. this.alphaConsumer = alphaConsumer != null ? alphaConsumer : a -> ;
    }

    @Override
    protected void onUpdate(float progress) {
        // TODO: Hướng dẫn hiện thực:
        // 1. Đường cong hình chuông theo hàm sin(progress * PI)
        // 2. flashAlpha = Math.max(0.0f, Math.min(1.0f, (float) Math.sin(progress * Math.PI)));
        // 3. alphaConsumer.accept(flashAlpha);
    }

    @Override
    protected void onComplete() {
        // TODO: Hướng dẫn hiện thực:
        // 1. reset();
    }

    @Override
    protected void onCancel() {
        // TODO: Hướng dẫn hiện thực:
        // 1. reset();
    }

    /** Đặt lại cường độ chớp sáng về 0. */
    private void reset() {
        // TODO: Hướng dẫn hiện thực:
        // 1. flashAlpha = 0.0f;
        // 2. alphaConsumer.accept(0.0f);
    }
}
