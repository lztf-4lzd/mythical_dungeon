package source.presentation.gui.screen;

import javax.swing.JPanel;

/**
 * Lớp cơ sở trừu tượng cho tất cả các màn hình giao diện đồ họa (GUI) trong ứng dụng.
 * Người phụ trách: Thành viên 5 (Hệ thống Hoạt ảnh Animation & Màn hình Game)
 */
abstract class Screen extends JPanel {
    private static final long serialVersionUID = 1L;

    /** Khởi tạo màn hình cơ sở. */
    Screen() {
        super();
        // TODO: Hướng dẫn hiện thực:
        // - Viết xử lý logic tại đây theo yêu cầu thiết kế.
    }

    /**
     * Được gọi bởi {@link ScreenManager} mỗi khi màn hình này trở thành màn hình đang hiển thị.
     * Các lớp con có thể ghi đè để làm mới dữ liệu, đặt lại trạng thái nút bấm hoặc cập nhật View Model.
     */
    void onEnter() {
        // TODO: Hướng dẫn hiện thực:
        // 1. Mặc định không làm gì; ghi đè ở lớp con khi cần
    }

    /**
     * Được gọi bởi {@link ScreenManager} ngay trước khi rời khỏi màn hình này.
     * Các lớp con có thể ghi đè để dừng bộ đếm thời gian, hủy hoạt ảnh hoặc dọn dẹp tài nguyên.
     */
    void onExit() {
        // TODO: Hướng dẫn hiện thực:
        // 1. Mặc định không làm gì; ghi đè ở lớp con khi cần
    }
}
