package source.application.view;

/**
 * Sự kiện hoạt ảnh có cấu trúc được sinh ra từ kết quả hành động chiến đấu,
 * phục vụ bộ phát hoạt ảnh trên giao diện (số sát thương, chữ hồi máu nổi, khiên hấp thụ, trượt và biểu tượng hiệu ứng).
 * Người phụ trách: Thành viên 1 (Data Architect & Project Coordinator)
 */
public final class AnimationEvent {

    /** Các loại sự kiện hoạt ảnh chiến đấu. */
    public enum EventType {
        /** Thông báo tên kỹ năng vừa sử dụng. */
        ACTION_NAME,
        /** Sát thương trừ vào máu (HP). */
        DAMAGE_HP,
        /** Sát thương bị khiên hấp thụ. */
        DAMAGE_SHIELD,
        /** Hồi phục máu (HP). */
        HEAL_HP,
        /** Hồi phục thể lực (STA). */
        RESTORE_STA,
        /** Đòn đánh bị trượt. */
        MISS,
        /** Đòn đánh chí mạng. */
        CRITICAL_HIT,
        /** Nhịp sát thương theo lượt (DoT). */
        EFFECT_TICK,
        /** Áp dụng hiệu ứng trạng thái mới. */
        EFFECT_APPLIED,
        /** Kháng thành công hiệu ứng trạng thái. */
        EFFECT_RESISTED,
        /** Hiệu ứng trạng thái hết hạn. */
        EFFECT_EXPIRED,
        /** Một bên bị hạ gục. */
        DEFEAT
    }

    /** Loại sự kiện hoạt ảnh. */
    private EventType type;
    /** Tên nhân vật nguồn gây ra sự kiện. */
    private String sourceName;
    /** Tên nhân vật mục tiêu nhận sự kiện. */
    private String targetName;
    /** Giá trị số lượng (sát thương, hồi máu, khiên...) gắn với sự kiện. */
    private int value;
    /** Chuỗi mô tả ngắn gọn sự kiện. */
    private String description;
    /** Cờ đánh dấu sự kiện sát thương chí mạng. */
    private boolean isCrit;
    /** Kiểu chuyển động hoạt ảnh đi kèm (nếu có). */
    private MotionType motionType;

    /** Khởi tạo sự kiện hoạt ảnh không chỉ định kiểu chuyển động. */
    public AnimationEvent(
            EventType type,
            String sourceName,
            String targetName,
            int value,
            String description,
            boolean isCrit) {
        // TODO: Hướng dẫn hiện thực:
        // 1. this(type, sourceName, targetName, value, description, isCrit, null);
    }

    /** Khởi tạo sự kiện hoạt ảnh đầy đủ thông tin kèm kiểu chuyển động. */
    public AnimationEvent(
            EventType type,
            String sourceName,
            String targetName,
            int value,
            String description,
            boolean isCrit,
            MotionType motionType) {
        // TODO: Hướng dẫn hiện thực:
        // 1. this.type = type;
        // 2. this.sourceName = sourceName == null ? "" : sourceName;
        // 3. this.targetName = targetName == null ? "" : targetName;
        // 4. this.value = value;
        // 5. this.description = description == null ? "" : description;
        // 6. this.isCrit = isCrit;
    }

    /** Trả về loại sự kiện hoạt ảnh. */
    public EventType getType() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return type;
        return null;
    }

    /** Trả về tên nhân vật nguồn. */
    public String getSourceName() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return sourceName;
        return null;
    }

    /** Trả về tên nhân vật mục tiêu. */
    public String getTargetName() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return targetName;
        return null;
    }

    /** Trả về giá trị số lượng của sự kiện. */
    public int getValue() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return value;
        return 0;
    }

    /** Trả về chuỗi mô tả sự kiện. */
    public String getDescription() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return description;
        return null;
    }

    /** Kiểm tra xem sự kiện có phải đòn chí mạng hay không. */
    public boolean isCrit() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return isCrit;
        return false;
    }

    /** Trả về kiểu chuyển động hoạt ảnh đi kèm. */
    public MotionType getMotionType() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return motionType;
        return null;
    }

    @Override
    public String toString() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return "[" + type + "] " + sourceName + " -> " + targetName + ": " + value + " (" + description + ")";
        return null;
    }
}
