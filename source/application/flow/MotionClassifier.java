package source.application.flow;

import source.application.view.MotionType;
import source.domain.entity.skill.Skill;

import java.util.Locale;

/**
 * Phân loại kỹ năng chiến đấu vào các nhóm chuyển động hoạt ảnh trình diễn
 * (Cận chiến, Đạn đạo tầm xa, Thi triển phép, Cường hóa) mà không làm thay đổi mô hình source.domain.
 * Người phụ trách: Thành viên 3 (Luồng Game & Sinh Ngục tối)
 */
public final class MotionClassifier {

    private MotionClassifier() {}

    /**
     * Xác định kiểu chuyển động hoạt ảnh cho một đòn đánh dựa trên thuộc tính kỹ năng và lớp nhân vật.
     */
    public static MotionType classify(Skill skill, String attackerClass) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (skill == null)
        // 2. return MotionType.MELEE;
        // 3. if (skill.isSelfTarget())
        // 4. return MotionType.BUFF;
        // 5. String skillName = skill.getName() != null ? skill.getName().trim().toLowerCase(Locale.ROOT) : "";
        // 6. String cls = attackerClass != null ? attackerClass.trim().toLowerCase(Locale.ROOT) : "";
        return null;
    }
}
