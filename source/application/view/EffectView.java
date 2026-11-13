package source.application.view;

import source.domain.entity.effect.EffectType;
import source.domain.entity.effect.StatusEffect;

/**
 * Mô hình hiển thị bất biến biểu diễn một hiệu ứng trạng thái đang kích hoạt trên chiến binh.
 * Người phụ trách: Thành viên 1 (Data Architect & Project Coordinator)
 */
public final class EffectView {
    private EffectType type;
    private String name;
    private int duration;
    private int value;

    /** Khởi tạo mô hình hiển thị hiệu ứng trạng thái. */
    private EffectView(EffectType type, String name, int duration, int value) {
        // TODO: Hướng dẫn hiện thực:
        // 1. this.type = type;
        // 2. this.name = name == null ? "" : name;
        // 3. this.duration = duration;
        // 4. this.value = value;
    }

    /** Chuyển đổi từ đối tượng {@link StatusEffect} của tầng domain sang {@link EffectView}. */
    public static EffectView fromDomain(StatusEffect effect) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (effect == null)
        // 2. return new EffectView(null, "", 0, 0);
        // 3. return new EffectView(
        // 4. effect.getType(),
        // 5. effect.getName(),
        // 6. effect.getDuration(),
        return null;
    }

    /** Trả về loại hiệu ứng trạng thái. */
    public EffectType getType() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return type;
        return null;
    }

    /** Trả về tên hiển thị của hiệu ứng. */
    public String getName() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return name;
        return null;
    }

    /** Trả về số lượt hiệu lực còn lại. */
    public int getDuration() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return duration;
        return 0;
    }

    /** Trả về độ lớn/cường độ của hiệu ứng. */
    public int getValue() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return value;
        return 0;
    }

    @Override
    public String toString() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return name + " (" + duration + " turns)";
        return null;
    }
}
