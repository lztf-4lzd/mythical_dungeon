package source.presentation.gui.animation;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Hoạt ảnh khung hình (sprite frames) chạy chuỗi ảnh {@link BufferedImage} theo chu kỳ cố định,
 * hỗ trợ lặp vô tận (ví dụ: tư thế nghỉ Idle) hoặc phát một lần (ví dụ: đòn tấn công, trúng đòn).
 * Người phụ trách: Thành viên 5 (Hệ thống Hoạt ảnh Animation & Màn hình Game)
 */
public class FrameAnimation implements Animation {

    /** Trạng thái vòng đời của hoạt ảnh khung hình. */
    private enum State {
        READY,
        RUNNING,
        FINISHED,
        CANCELLED
    }

    /** Danh sách bất biến các khung hình của hoạt ảnh. */
    private List<BufferedImage> frames;
    /** Thời lượng hiển thị mỗi khung hình (mili-giây). */
    private long frameDurationMillis;
    /** Cờ cho phép lặp lại vô tận. */
    private boolean looping;
    /** Tổng thời lượng của một chu kỳ đầy đủ (mili-giây). */
    private long totalDurationMillis;

    /** Trạng thái hiện tại của hoạt ảnh. */
    private State state = State.READY;
    /** Chỉ số khung hình đang hiển thị (tính từ 0). */
    private int currentFrameIndex = 0;
    /** Mốc thời gian bắt đầu hoạt ảnh (mili-giây). */
    private long startTimeMillis = -1;
    /** Danh sách callback khi hoàn tất. */
    private final List<Runnable> completeCallbacks = new ArrayList<>();
    /** Danh sách callback khi bị hủy. */
    private final List<Runnable> cancelCallbacks = new ArrayList<>();

    /**
     * Khởi tạo hoạt ảnh khung hình với danh sách ảnh, thời lượng mỗi khung và chế độ lặp.
     *
     * @param frames danh sách khung hình
     * @param frameDurationMillis thời lượng mỗi khung hình (mili-giây)
     * @param looping {@code true} nếu lặp liên tục, {@code false} nếu chỉ phát một lần
     */
    public FrameAnimation(List<BufferedImage> frames, long frameDurationMillis, boolean looping) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (frames != null && !frames.isEmpty())
        // 2. this.frames = Collections.unmodifiableList(new ArrayList<>(frames));
        // 3. else
        // 4. this.frames = Collections.emptyList();
        // 5. this.frameDurationMillis = Math.max(1, frameDurationMillis);
        // 6. this.looping = looping;
    }

    @Override
    public synchronized void start(long startTimeMillis) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (state != State.READY)
        // 2. return;
        // 3. this.startTimeMillis = startTimeMillis;
        // 4. this.state = State.RUNNING;
        // 5. this.currentFrameIndex = 0;
        // 6. if (frames.isEmpty())
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
        // 4. if (!frames.isEmpty())
        // 5. currentFrameIndex = frames.size() - 1;
        // 6. for (Runnable cb : completeCallbacks)
    }

    @Override
    public synchronized void cancel() {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (state == State.FINISHED || state == State.CANCELLED)
        // 2. return;
        // 3. state = State.CANCELLED;
        // 4. for (Runnable cb : cancelCallbacks)
        // 5. try
        // 6. cb.run();
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
     * Lấy ảnh của khung hình đang hoạt động hiện tại, hoặc {@code null} nếu danh sách khung trống.
     */
    public synchronized BufferedImage getCurrentFrame() {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (frames.isEmpty())
        // 2. return null;
        // 3. if (currentFrameIndex < 0 || currentFrameIndex >= frames.size())
        // 4. return frames.get(0);
        // 5. return frames.get(currentFrameIndex);
        return null;
    }
}
