package source.application.view;

/**
 * Các hình mẫu chuyển động hoạt ảnh cho đòn đánh và kỹ năng chiến đấu
 * (Lao tới cận chiến, Đạn đạo tầm xa, Xung nhịp niệm phép và Hào quang cường hóa).
 * Người phụ trách: Thành viên 1 (Data Architect & Project Coordinator)
 */
public enum MotionType {
    /** Đòn đánh cận chiến lao tới mục tiêu. */
    MELEE,
    /** Đòn bắn đạn đạo bay từ người thi triển tới mục tiêu. */
    PROJECTILE,
    /** Hoạt ảnh niệm phép tại chỗ kèm hiệu ứng nổ tại mục tiêu. */
    CASTING,
    /** Hoạt ảnh tự cường hóa hoặc hồi phục trên bản thân. */
    BUFF
}
