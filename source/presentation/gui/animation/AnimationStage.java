package source.presentation.gui.animation;

import source.application.view.AnimationEvent;
import source.application.combat.TurnReport;

import java.util.List;

/**
 * Giao diện sân khấu tách biệt bộ tuần tự hoạt ảnh ({@link TurnSequencer}) khỏi lớp vẽ canvas cụ thể.
 * Giúp điều phối sát thương theo lượt (DoT), chuyển động tấn công và hiệu ứng va chạm mà không tạo phụ thuộc vòng giữa các gói.
 * Người phụ trách: Thành viên 5 (Hệ thống Hoạt ảnh Animation & Màn hình Game)
 */
public interface AnimationStage {

    /** Lấy bộ quản lý hoạt ảnh đang điều khiển sân khấu. */
    AnimationManager getAnimationManager();

    /**
     * Thực thi chuyển động tấn công trực quan (lao tới cận chiến, bắn đạn hoặc xung nhịp niệm phép)
     * cho hành động của nhân vật, đồng thời lập lịch gọi callback khi va chạm và khi hoàn tất chuyển động.
     *
     * @param actionEvent sự kiện hoạt ảnh xác định người tấn công, mục tiêu và kiểu chuyển động
     * @param report báo cáo lượt chiến đấu cung cấp ngữ cảnh kỹ năng và nguyên tố
     * @param onImpact callback thực thi ngay tại thời điểm đòn đánh chạm mục tiêu
     * @param onComplete callback thực thi khi chuyển động kết thúc và nhân vật về tư thế nghỉ
     */
    void playActionMotion(
            AnimationEvent actionEvent,
            TurnReport report,
            Runnable onImpact,
            Runnable onComplete);

    /**
     * Thực thi các phản ứng va chạm kèm ngữ cảnh báo cáo lượt để xác định màu hiệu ứng nguyên tố.
     *
     * @param impactEvents danh sách sự kiện va chạm, sát thương hoặc hồi phục
     * @param report báo cáo lượt chiến đấu cung cấp thông tin nguyên tố
     */
    void playImpactEvents(List<AnimationEvent> impactEvents, TurnReport report);

    /**
     * Phát hoạt ảnh một nhịp sát thương theo lượt (DoT) gồm chớp sáng trúng đòn và chữ nổi.
     *
     * @param dotEvent sự kiện hoạt ảnh đại diện cho nhịp sát thương hiệu ứng
     * @param onComplete callback thực thi khi hoạt ảnh nhịp DoT kết thúc
     */
    void playDotTick(AnimationEvent dotEvent, Runnable onComplete);

    /** Hủy và xóa sạch mọi hoạt ảnh trực quan đang chạy trên sân khấu. */
    void clearAnimations();
}
