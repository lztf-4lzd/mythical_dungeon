package source.presentation.gui.animation;

import java.util.function.BiConsumer;

/**
 * Hoạt ảnh rung lắc màn hình sân khấu chiến đấu với biên độ hình sin tắt dần
 * và tự động đặt lại độ lệch (X, Y) về (0, 0) khi kết thúc hoặc bị hủy.
 * Người phụ trách: Thành viên 5 (Hệ thống Hoạt ảnh Animation & Màn hình Game)
 */
public class ShakeAnimation extends BaseAnimation {

    /** Biên độ rung tối đa ban đầu (pixel). */
    private float amplitude;
    /** Hàm nhận cặp độ lệch (offsetX, offsetY) mỗi nhịp. */
    private BiConsumer<Integer, Integer> offsetConsumer;

    /** Độ lệch trục X hiện tại (pixel). */
    private int offsetX = 0;
    /** Độ lệch trục Y hiện tại (pixel). */
    private int offsetY = 0;

    /**
     * Khởi tạo hoạt ảnh rung màn hình.
     *
     * @param durationMillis thời lượng rung (mili-giây)
     * @param amplitude biên độ rung ban đầu (pixel)
     * @param offsetConsumer hàm nhận độ lệch (X, Y) mỗi nhịp
     */
    public ShakeAnimation(long durationMillis, float amplitude, BiConsumer<Integer, Integer> offsetConsumer) {
        super(durationMillis);
        // TODO: Hướng dẫn hiện thực:
        // 1. this.amplitude = Math.max(1.0f, amplitude);
        // 2. this.offsetConsumer = offsetConsumer != null ? offsetConsumer : (x, y) -> ;
    }

    @Override
    protected void onUpdate(float progress) {
        // TODO: Hướng dẫn hiện thực:
        // 1. float damping = 1.0f - progress;
        // 2. Dao động tần số cao tắt dần
        // 3. offsetX = (int) Math.round(damping * amplitude * Math.sin(progress * 8.0 * Math.PI));
        // 4. offsetY = (int) Math.round(damping * (amplitude * 0.5f) * Math.cos(progress * 6.0 * Math.PI));
        // 5. offsetConsumer.accept(offsetX, offsetY);
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

    /** Đưa độ lệch rung màn hình về (0, 0). */
    private void reset() {
        // TODO: Hướng dẫn hiện thực:
        // 1. offsetX = 0;
        // 2. offsetY = 0;
        // 3. offsetConsumer.accept(0, 0);
    }
}
