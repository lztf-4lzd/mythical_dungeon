package source.application.progression;

import source.domain.entity.character.Attributes;
import source.domain.entity.character.Character;
import source.domain.calculator.DamageCalculator;
import source.domain.util.GameConstants;

/**
 * Chuyển đổi kinh nghiệm nhận được thành các lần lên cấp và áp dụng tốc độ tăng trưởng thuộc tính của nhân vật.
 * Người phụ trách: Thành viên 1 (Data Architect & Project Coordinator)
 */
public final class LevelService {

    private LevelService() {}

    /**
     * Trả về lượng kinh nghiệm cần thiết để lên cấp tiếp theo từ cấp độ hiện tại (100 EXP mỗi cấp).
     */
    private static int getExpRequired(int level) {
        // TODO: Hướng dẫn hiện thực:
        // 1. return level * 100;
        return 0;
    }

    /**
     * Cộng kinh nghiệm và xử lý toàn bộ các lần lên cấp nếu đủ ngưỡng.
     * Khi lên cấp, cập nhật cấp độ, thuộc tính gốc, chỉ số dẫn xuất và hồi đầy HP/STA.
     *
     * @param hero nhân vật nhận kinh nghiệm
     * @param expGained lượng kinh nghiệm nhận được
     */
    public static void processExpGain(Character hero, int expGained) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (hero == null)
        // 2. return;
        // 3. Khi đã đạt cấp tối đa, mọi kinh nghiệm dư đều bị hủy bỏ.
        // 4. if (hero.getLevel() >= GameConstants.MAX_LEVEL)
        // 5. hero.getStats().setCurrentExp(0);
        // 6. return;
    }

    /** Tăng 1 cấp, cộng mức tăng trưởng thuộc tính của lớp nhân vật, tính lại chỉ số và hồi đầy HP/STA. */
    private static void levelUp(Character hero) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (hero.getLevel() >= GameConstants.MAX_LEVEL)
        // 2. return;
        // 3. hero.setLevel(hero.getLevel() + 1);
        // 4. Cộng mức tăng trưởng của từng thuộc tính gốc trước khi tính lại chỉ số dẫn xuất.
        // 5. Attributes growthRate = hero.getGrowthRate();
        // 6. Attributes attributes = hero.getAttributes();
    }
}
