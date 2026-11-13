package source.application.view;

import source.domain.entity.effect.StatusEffect;
import source.domain.entity.elemental.Elemental;
import source.domain.entity.character.Character;
import source.domain.entity.skill.Skill;
import source.domain.entity.skill.SkillMods;

import java.util.ArrayList;
import java.util.List;

/**
 * Mô hình hiển thị bất biến biểu diễn một ô kỹ năng cho nút hành động chiến đấu,
 * chú giải công cụ (tooltip) và bảng chọn kỹ năng tương tác.
 * Người phụ trách: Thành viên 1 (Data Architect & Project Coordinator)
 */
public final class SkillView {
    private int slotIndex;
    private String name;
    private Elemental element;
    private int staCost;
    private boolean isOncePerBattle;
    private boolean canUse;
    private String lockReason;

    private boolean isMagic;
    private boolean isSelfTarget;
    private String categoryLabel;
    private String powerFormulaText;
    private int estimatedDamage;
    private String effectSummaryText;
    private String passiveModsText;

    private SkillView(
            int slotIndex,
            String name,
            Elemental element,
            int staCost,
            boolean isOncePerBattle,
            boolean canUse,
            String lockReason,
            boolean isMagic,
            boolean isSelfTarget,
            String categoryLabel,
            String powerFormulaText,
            int estimatedDamage,
            String effectSummaryText,
            String passiveModsText) {
        this.slotIndex = slotIndex;
        this.name = name == null ? "" : name;
        this.element = element == null ? Elemental.NORMAL : element;
        this.staCost = staCost;
        this.isOncePerBattle = isOncePerBattle;
        this.canUse = canUse;
        this.lockReason = lockReason == null ? "" : lockReason;
        this.isMagic = isMagic;
        this.isSelfTarget = isSelfTarget;
        this.categoryLabel = categoryLabel == null
                ? (isOncePerBattle ? "Ultimate [1x]" : (slotIndex == 0 ? "Basic Attack" : "Regular Skill"))
                : categoryLabel;
        this.powerFormulaText = powerFormulaText == null ? "—" : powerFormulaText;
        this.estimatedDamage = estimatedDamage;
        this.effectSummaryText = effectSummaryText == null ? "None" : effectSummaryText;
        this.passiveModsText = passiveModsText == null ? "—" : passiveModsText;
    }

    /**
     * Tạo đối tượng {@link SkillView} bằng cách trích xuất và định dạng toàn bộ thông tin từ {@link Skill}.
     */
    public static SkillView fromSkill(
            int slotIndex,
            Skill skill,
            Character hero,
            boolean canUse,
            String lockReason,
            boolean isBasic) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (skill == null)
        // 2. return new SkillView(
        // 3. slotIndex, "", Elemental.NORMAL, 0, false, false, "Invalid skill",
        // 4. false, false, slotIndex == 0 ? "Basic Attack" : "Regular Skill", "—", 0, "None", "—");
        // 5. boolean isOnce = skill.getMods().isOncePerBattle();
        // 6. boolean isMagic = skill.isMagic();
        return null;
    }

    /** Trả về tên kỹ năng. */
    public String getName() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return name;
        return null;
    }

    /** Trả về nguyên tố của kỹ năng. */
    public Elemental getElement() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return element;
        return null;
    }

    /** Trả về chi phí thể lực (STA). */
    public int getStaCost() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return staCost;
        return 0;
    }

    /** Kiểm tra xem kỹ năng có giới hạn dùng 1 lần mỗi trận hay không. */
    public boolean isOncePerBattle() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return isOncePerBattle;
        return false;
    }

    /** Kiểm tra xem kỹ năng hiện có thể bấm sử dụng hay không. */
    public boolean canUse() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return canUse;
        return false;
    }

    /** Trả về lý do kỹ năng bị khóa (nếu không thể sử dụng). */
    public String getLockReason() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return lockReason;
        return null;
    }

    /** Kiểm tra xem đây có phải kỹ năng phép thuật hay không. */
    public boolean isMagic() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return isMagic;
        return false;
    }

    /** Kiểm tra xem kỹ năng có tự nhắm vào bản thân hay không. */
    public boolean isSelfTarget() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return isSelfTarget;
        return false;
    }

    /** Trả về nhãn phân loại kỹ năng (Basic Attack, Regular Skill, Ultimate [1x]). */
    public String getCategoryLabel() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return categoryLabel;
        return null;
    }

    /** Trả về chuỗi công thức sức mạnh hiển thị trên bảng kỹ năng. */
    public String getPowerFormulaText() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return powerFormulaText;
        return null;
    }

    /** Trả về lượng sát thương ước tính dựa trên chỉ số hiện tại của anh hùng. */
    public int getEstimatedDamage() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return estimatedDamage;
        return 0;
    }

    /** Trả về chuỗi tóm tắt hiệu ứng trạng thái đi kèm. */
    public String getEffectSummaryText() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return effectSummaryText;
        return null;
    }

    /** Trả về chuỗi tóm tắt các hệ số nội tại đặc biệt ({@link SkillMods}). */
    public String getPassiveModsText() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return passiveModsText;
        return null;
    }

    /** Trả về nhãn kiểu tác động (Self / Buff, Magic Attack, Physical Attack). */
    public String getTypeLabel() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return isSelfTarget ? "Self / Buff" : (isMagic ? "Magic Attack" : "Physical Attack");
        return null;
    }

    /** Trả về nhãn bậc kỹ năng viết hoa. */
    public String getTierLabel() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return isOncePerBattle ? "[ULTIMATE 1x]" : (slotIndex == 0 ? "[BASIC]" : "[REGULAR]");
        return null;
    }
}
