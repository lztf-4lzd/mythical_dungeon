package source.application.combat;

import source.domain.entity.character.Character;
import source.domain.entity.monster.Monster;
import source.domain.entity.skill.Skill;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Giao diện chiến lược và bộ đăng ký dùng để chọn kỹ năng chiến thuật đặc trưng cho từng loại quái vật.
 * Người phụ trách: Thành viên 2 (Combat Engine & AI Quái vật)
 */
@FunctionalInterface
interface MonsterTactics {

    /**
     * Đánh giá các điều kiện ưu tiên chiến thuật của quái vật để chọn ra một kỹ năng.
     *
     * @param monster quái vật đang tới lượt
     * @param target đối thủ của quái vật
     * @param usableSkills danh sách kỹ năng hiện đủ thể lực và khả dụng
     * @return kỹ năng ưu tiên được chọn, hoặc {@code null} nếu không có điều kiện ưu tiên nào kích hoạt
     */
    Skill selectTacticalSkill(Character monster, Character target, List<Skill> usableSkills);

    /**
     * Chiến thuật mặc định cho các quái vật không có quy tắc ưu tiên riêng theo loài.
     */
    class DefaultTactics implements MonsterTactics {
        @Override
        public Skill selectTacticalSkill(Character monster, Character target, List<Skill> usableSkills) {
            return null;
        }
    }

    /**
     * Bộ đăng ký ánh xạ lớp quái vật sang chiến lược {@link MonsterTactics} tương ứng.
     */
    class Registry {
        private static final Map<Class<? extends Character>, MonsterTactics> TACTICS_MAP;

        static {
            Map<Class<? extends Character>, MonsterTactics> map = new HashMap<>();
            map.put(Monster.class, new RoleTactics());
            TACTICS_MAP = Collections.unmodifiableMap(map);
        }

        private static final MonsterTactics DEFAULT = new DefaultTactics();

        /** Trả về chiến lược {@link MonsterTactics} phù hợp cho quái vật được truyền vào. */
        static MonsterTactics get(Character monster) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (monster == null) return DEFAULT;
        // 2. for (Map.Entry<Class<? extends Character>, MonsterTactics> entry : TACTICS_MAP.entrySet())
        // 3. if (entry.getKey().isInstance(monster))
        // 4. return entry.getValue();
        // 5. return DEFAULT;
        return null;
    }
    }
}
