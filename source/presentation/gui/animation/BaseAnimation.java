package source.presentation.gui.animation;

import java.util.ArrayList;
import java.util.List;

/**
 * Lớp cơ sở trừu tượng cho các hoạt ảnh: quản lý vòng đời trạng thái, theo dõi thời gian trôi qua,
 * chuẩn hóa tiến độ trong đoạn [0.0, 1.0] và thực thi các hàm gọi lại (callback).
 * Người phụ trách: Thành viên 5 (Hệ thống Hoạt ảnh Animation & Màn hình Game)
 */
abstract class BaseAnimation implements Animation {

    /** Trạng thái vòng đời của một hoạt ảnh. */
    private enum State {
        READY,
        RUNNING,
        FINISHED,
        CANCELLED
    }

    /** Tổng thời lượng dự kiến của hoạt ảnh (mili-giây). */
    private long durationMillis;
    /** Trạng thái hiện tại của hoạt ảnh. */
    private State state = State.READY;
    /** Mốc thời gian bắt đầu hoạt ảnh (mili-giây). */
    private long startTimeMillis = -1;
    /** Danh sách các hàm gọi lại khi hoạt ảnh hoàn tất tự nhiên. */
    private final List<Runnable> completeCallbacks = new ArrayList<>();
    /** Danh sách các hàm gọi lại khi hoạt ảnh bị hủy giữa chừng. */
    private final List<Runnable> cancelCallbacks = new ArrayList<>();

    /**
     * Khởi tạo hoạt ảnh với thời lượng chỉ định.
     *
     * @param durationMillis tổng thời lượng hoạt ảnh (mili-giây, không âm)
     */
    protected BaseAnimation(long durationMillis) {
        // TODO: Hướng dẫn hiện thực:
        // 1. this.durationMillis = Math.max(0, durationMillis);
    }

    @Override
    public synchronized void start(long startTimeMillis) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (state != State.READY)
        // 2. return;
        // 3. this.startTimeMillis = startTimeMillis;
        // 4. this.state = State.RUNNING;
        // 5. Hoàn tất ngay lập tức một cách an toàn nếu thời lượng bằng 0
        // 6. if (durationMillis <= 0)
    }

    @Override
    public synchronized void update(long currentTimeMillis) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (state != State.RUNNING)
        // 2. return;
        // 3. if (startTimeMillis < 0)
        // 4. start(currentTimeMillis);
        // 5. if (state != State.RUNNING)
        // 6. return;
    }

    private synchronized void complete() {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (state == State.FINISHED || state == State.CANCELLED)
        // 2. return;
        // 3. state = State.FINISHED;
        // 4. onUpdate(1.0f);
        // 5. onComplete();
        // 6. for (Runnable callback : completeCallbacks)
    }

    @Override
    public synchronized void cancel() {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (state == State.FINISHED || state == State.CANCELLED)
        // 2. return;
        // 3. state = State.CANCELLED;
        // 4. onCancel();
        // 5. for (Runnable callback : cancelCallbacks)
        // 6. try
    }

    @Override
    public synchronized boolean isRunning() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return state == State.RUNNING;
        return false;
    }

    @Override
    public synchronized boolean isFinished() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return state == State.FINISHED || state == State.CANCELLED;
        return false;
    }

    public synchronized boolean isCancelled() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return state == State.CANCELLED;
        return false;
    }

    public synchronized Animation onComplete(Runnable callback) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (callback != null)
        // 2. if (state == State.FINISHED)
        // 3. callback.run();
        // 4. else if (state != State.CANCELLED)
        // 5. completeCallbacks.add(callback);
        // 6. return this;
        return null;
    }

    public synchronized Animation onCancel(Runnable callback) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (callback != null)
        // 2. if (state == State.CANCELLED)
        // 3. callback.run();
        // 4. else if (state != State.FINISHED)
        // 5. cancelCallbacks.add(callback);
        // 6. return this;
        return null;
    }

    /**
     * Được gọi ở mỗi nhịp cập nhật với tiến độ đã giới hạn trong đoạn [0.0, 1.0].
     *
     * @param progress tiến độ chuẩn hóa từ 0.0f đến 1.0f
     */
    protected abstract void onUpdate(float progress);

    /**
     * Được gọi khi hoạt ảnh kết thúc tự nhiên; lớp con có thể ghi đè để dọn dẹp trạng thái.
     */
    protected void onComplete() {
        // TODO: Hướng dẫn hiện thực:
        // - Viết xử lý logic tại đây theo yêu cầu thiết kế.
    }

    /**
     * Được gọi khi hoạt ảnh bị hủy; lớp con có thể ghi đè để khôi phục trạng thái gốc.
     */
    protected void onCancel() {
        // TODO: Hướng dẫn hiện thực:
        // - Viết xử lý logic tại đây theo yêu cầu thiết kế.
    }
}
