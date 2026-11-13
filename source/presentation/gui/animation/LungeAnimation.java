package source.presentation.gui.animation;

import java.util.function.Consumer;

/**
 * Hoạt ảnh đòn đánh cận chiến: lấy đà nhẹ, lao nhanh về phía mục tiêu rồi lùi mượt mà về vị trí gốc.
 * Người phụ trách: Thành viên 5 (Hệ thống Hoạt ảnh Animation & Màn hình Game)
 */
public class LungeAnimation extends BaseAnimation {

    /** Cờ xác định người tấn công là anh hùng (lao sang phải +X) hay quái vật (lao sang trái -X). */
    private boolean isAttackerHero;
    /** Quãng đường lao tới tối đa (pixel). */
    private int maxDistance;
    /** Hàm nhận độ lệch X hiện tại ở mỗi nhịp. */
    private Consumer<Integer> offsetConsumer;

    /** Độ lệch trục X hiện tại (pixel). */
    private int currentOffset = 0;

    /**
     * Khởi tạo hoạt ảnh lao tới cận chiến.
     *
     * @param durationMillis tổng thời lượng hoạt ảnh (mili-giây)
     * @param isAttackerHero {@code true} nếu người tấn công là anh hùng
     * @param maxDistance cự ly lao tới tối đa (pixel)
     * @param offsetConsumer hàm nhận độ lệch trục X mỗi nhịp
     */
    public LungeAnimation(long durationMillis, boolean isAttackerHero, int maxDistance, Consumer<Integer> offsetConsumer) {
        super(durationMillis);
        // TODO: Hướng dẫn hiện thực:
        // 1. this.isAttackerHero = isAttackerHero;
        // 2. this.maxDistance = Math.max(10, maxDistance);
        // 3. this.offsetConsumer = offsetConsumer != null ? offsetConsumer : o -> ;
    }

    @Override
    protected void onUpdate(float progress) {
        // TODO: Hướng dẫn hiện thực:
        // 1. Lấy đà (0.0 - 0.2): lùi nhẹ về sau
        // 2. Lao tới (0.2 - 0.5): phóng nhanh về phía mục tiêu
        // 3. Thu hồi (0.5 - 1.0): lùi mượt mà về tọa độ 0
        // 4. float rawOffset;
        // 5. if (progress < 0.20f)
        // 6. float t = progress / 0.20f;
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

    /** Đưa độ lệch vị trí về 0. */
    private void reset() {
        // TODO: Hướng dẫn hiện thực:
        // 1. currentOffset = 0;
        // 2. offsetConsumer.accept(0);
    }
}
