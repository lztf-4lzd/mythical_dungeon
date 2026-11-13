package source.presentation.gui.animation;

import java.util.function.Consumer;

/**
 * Hoạt ảnh cụ thể truyền giá trị tiến độ chuẩn hóa (từ 0.0 đến 1.0) vào một hàm tiêu thụ mỗi nhịp.
 * Người phụ trách: Thành viên 5 (Hệ thống Hoạt ảnh Animation & Màn hình Game)
 */
class ValueAnimation extends BaseAnimation {

    /** Hàm tiêu thụ nhận tiến độ chuẩn hóa mỗi nhịp cập nhật. */
    private Consumer<Float> progressConsumer;

    /**
     * Khởi tạo hoạt ảnh giá trị kèm callback hoàn tất.
     *
     * @param durationMillis thời lượng hoạt ảnh (mili-giây)
     * @param progressConsumer hàm nhận tiến độ (0.0 đến 1.0)
     * @param onComplete callback thực thi khi hoạt ảnh kết thúc
     */
    @SuppressWarnings("this-escape")
    ValueAnimation(long durationMillis, Consumer<Float> progressConsumer, Runnable onComplete) {
        super(durationMillis);
        // TODO: Hướng dẫn hiện thực:
        // 1. this.progressConsumer = progressConsumer != null ? progressConsumer : p -> ;
        // 2. if (onComplete != null)
        // 3. onComplete(onComplete);
    }

    @Override
    protected void onUpdate(float progress) {
        // TODO: Hướng dẫn hiện thực:
        // 1. progressConsumer.accept(progress);
    }
}
