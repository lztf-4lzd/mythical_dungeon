package source.application.combat;

import source.domain.entity.effect.EffectType;
import source.domain.entity.effect.StatusEffect;
import source.domain.entity.character.Character;
import source.domain.entity.skill.Skill;
import source.domain.entity.skill.SkillMods;
import source.domain.util.GameRandom;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Lựa chọn kỹ năng hành động cho quái vật dựa trên thể lực hiện có và thứ tự ưu tiên chiến thuật.
 * Người phụ trách: Thành viên 2 (Combat Engine & AI Quái vật)
 */
final class DecisionService {
    /** Bộ sinh số ngẫu nhiên mặc định cho quyết định của quái vật. */
    private static final Random RNG = GameRandom.getDefault();

    private DecisionService() {}

    /**
     * Chọn một kỹ năng khả dụng cho quái vật trong lượt hiện tại.
     *
     * @param monster quái vật đang tới lượt
     * @param target đối thủ của quái vật
     * @return kỹ năng được chọn
     */
    static Skill chooseMonsterSkill(Character monster, Character target) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (monster.getStats().getCurrentHp() <= 0)
        // 2. return monster.getDefaultBasicAttack();
        // 3. Tuyệt kỹ có điều kiện ngưỡng HP được ưu tiên ngay khi thỏa mãn toàn bộ điều kiện.
        // 4. for (Skill skill : monster.getSkills())
        // 5. if (isAvailable(skill, monster, target) && hasUltimateThreshold(skill))
        // 6. return skill;
        return null;
    }

    /** Kiểm tra thể lực, giới hạn 1 lần/trận và điều kiện ngưỡng HP của tuyệt kỹ trước khi chọn. */
    private static boolean isAvailable(Skill skill, Character monster, Character target) {
        // TODO: Hướng dẫn hiện thực:
        // 1. boolean alreadyUsed = skill.getMods().isOncePerBattle()
        // 2. && monster.hasConsumedOnceSkill(skill.getName());
        // 3. if (alreadyUsed || skill.getStaCost() > monster.getStats().getCurrentSta())
        // 4. return false;
        // 5. return !hasUltimateThreshold(skill) || meetsUltimateThresholds(skill, monster, target);
        return false;
    }

    /** Kiểm tra xem kỹ năng có khai báo điều kiện ngưỡng HP của bản thân hoặc mục tiêu hay không. */
    private static boolean hasUltimateThreshold(Skill skill) {
        // TODO: Hướng dẫn hiện thực:
        // 1. SkillMods mods = skill.getMods();
        // 2. return mods.getUltSelfHpMax() >= 0.0 || mods.getUltTargetHpMax() >= 0.0;
        return false;
    }

    /** Kiểm tra mọi điều kiện ngưỡng HP đã cấu hình; điều kiện âm nghĩa là không giới hạn. */
    private static boolean meetsUltimateThresholds(Skill skill, Character monster, Character target) {
        // TODO: Hướng dẫn hiện thực:
        // 1. SkillMods mods = skill.getMods();
        // 2. double selfHpMax = mods.getUltSelfHpMax();
        // 3. double targetHpMax = mods.getUltTargetHpMax();
        // 4. if (selfHpMax >= 0.0 && healthRatio(monster) > selfHpMax)
        // 5. return false;
        // 6. return targetHpMax < 0.0
        return false;
    }

    /** Tính tỷ lệ HP hiện tại trên HP tối đa để kiểm tra điều kiện kích hoạt tuyệt kỹ. */
    private static double healthRatio(Character character) {
        // TODO: Hướng dẫn hiện thực:
        // 1. return (double) character.getStats().getCurrentHp()
        // 2. / Math.max(1, character.getStats().getMaxHp());
        return 0.0;
    }

    /** Kiểm tra xem nhân vật có đang mang hiệu ứng trạng thái chỉ định hay không. */
    static boolean hasActiveEffect(Character character, EffectType type) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (character == null || character.getActiveEffects() == null) return false;
        // 2. for (StatusEffect effect : character.getActiveEffects())
        // 3. if (effect.getType() == type)
        // 4. return true;
        // 5. return false;
        return false;
    }

    /** Tìm kỹ năng trong danh sách khả dụng theo tên (không phân biệt hoa thường). */
    static Skill findSkillByName(List<Skill> skills, String name) {
        // TODO: Hướng dẫn hiện thực:
        // 1. for (Skill skill : skills)
        // 2. if (skill.getName().equalsIgnoreCase(name))
        // 3. return skill;
        // 4. return null;
        return null;
    }
}
