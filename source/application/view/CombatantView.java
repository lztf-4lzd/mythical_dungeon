package source.application.view;

import source.domain.entity.elemental.Elemental;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Mô hình hiển thị bất biến biểu diễn thông tin của một chiến binh (Anh hùng hoặc Quái vật) trên màn hình.
 * Người phụ trách: Thành viên 1 (Data Architect & Project Coordinator)
 */
public final class CombatantView {
    private String name;
    private String className;
    private int level;
    private Elemental element;
    private int currentHp;
    private int maxHp;
    private int currentSta;
    private int maxSta;
    private int shield;
    private List<EffectView> activeEffects;
    private boolean isBoss;

    /** Khởi tạo mô hình hiển thị chiến binh với đầy đủ thông tin chỉ số và hiệu ứng. */
    public CombatantView(
            String name,
            String className,
            int level,
            Elemental element,
            int currentHp,
            int maxHp,
            int currentSta,
            int maxSta,
            int shield,
            List<EffectView> activeEffects,
            boolean isBoss) {
        // TODO: Hướng dẫn hiện thực:
        // 1. this.name = name == null ? "" : name;
        // 2. this.className = className == null ? "" : className;
        // 3. this.level = Math.max(1, level);
        // 4. this.element = element == null ? Elemental.NORMAL : element;
        // 5. this.currentHp = Math.max(0, currentHp);
        // 6. this.maxHp = Math.max(1, maxHp);
    }

    /** Trả về tên gốc của chiến binh. */
    public String getName() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return name;
        return null;
    }

    /** Trả về tên lớp nhân vật hoặc định danh loại quái vật. */
    public String getClassName() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return className;
        return null;
    }

    /** Trả về chuỗi định danh loại ổn định của chiến binh trong source.domain. */
    public String getTypeIdentifier() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return className;
        return null;
    }

    /** Trả về cấp độ hiện tại. */
    public int getLevel() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return level;
        return 0;
    }

    /** Trả về nguyên tố của chiến binh. */
    public Elemental getElement() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return element;
        return null;
    }

    /** Trả về lượng HP hiện tại. */
    public int getCurrentHp() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return currentHp;
        return 0;
    }

    /** Trả về lượng HP tối đa. */
    public int getMaxHp() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return maxHp;
        return 0;
    }

    /** Trả về lượng STA hiện tại. */
    public int getCurrentSta() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return currentSta;
        return 0;
    }

    /** Trả về lượng STA tối đa. */
    public int getMaxSta() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return maxSta;
        return 0;
    }

    /** Trả về giá trị khiên hiện tại. */
    public int getShield() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return shield;
        return 0;
    }

    /** Trả về danh sách các hiệu ứng trạng thái đang tác dụng. */
    public List<EffectView> getActiveEffects() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return activeEffects;
        return null;
    }

    /** Kiểm tra xem chiến binh có phải Boss / Mini-Boss hay không. */
    public boolean isBoss() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return isBoss;
        return false;
    }

    /** Trả về tên hiển thị của quái vật đã gắn tối đa một huy hiệu phân bậc. */
    public String getDisplayName() {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (name == null || name.isEmpty())
        // 2. return "";
        // 3. String upper = name.toUpperCase();
        // 4. if (upper.contains("[MINI-BOSS]") || upper.contains("[BOSS]"))
        // 5. return name;
        // 6. return isBoss ? name + " [BOSS]" : name;
        return null;
    }
}
