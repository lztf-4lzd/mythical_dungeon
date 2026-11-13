package source.presentation.gui.animation;

/**
 * Hợp đồng đại diện cho một hoạt ảnh có vòng đời trạng thái dựa trên thời gian.
 * Người phụ trách: Thành viên 5 (Hệ thống Hoạt ảnh Animation & Màn hình Game)
 */
interface Animation {

    /**
     * Bắt đầu hoạt ảnh tại mốc thời gian chỉ định (mili-giây).
     *
     * @param startTimeMillis mốc thời gian bắt đầu
     */
    void start(long startTimeMillis);

    /**
     * Cập nhật tiến độ hoạt ảnh dựa trên mốc thời gian hiện tại (mili-giây).
     *
     * @param currentTimeMillis mốc thời gian hiện tại
     */
    void update(long currentTimeMillis);

    /**
     * Hủy hoạt ảnh sớm, dừng cập nhật và kích hoạt các callback hủy.
     */
    void cancel();

    /**
     * Trả về {@code true} nếu hoạt ảnh đang chạy.
     */
    boolean isRunning();

    /**
     * Trả về {@code true} nếu hoạt ảnh đã kết thúc (hoàn tất tự nhiên hoặc bị hủy).
     */
    boolean isFinished();
}
