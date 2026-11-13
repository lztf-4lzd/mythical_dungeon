package source.application.setup;

import source.domain.entity.character.Character;
import source.domain.calculator.DamageCalculator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Nhà máy khởi tạo các nhân vật anh hùng cho người chơi với chỉ số dẫn xuất đã tính toán
 * và đầy đủ máu/thể lực ban đầu.
 * Người phụ trách: Thành viên 1 (Data Architect & Project Coordinator)
 */
public final class HeroFactory {

    /** Các lớp nhân vật anh hùng có thể chọn. */
    public enum HeroClass {
        /** Chiến Binh. */
        WARRIOR("Hero"),
        /** Pháp Sư. */
        MAGE("Hero"),
        /** Hiệp Sĩ. */
        PALADIN("Hero"),
        /** Cung Thủ. */
        ARCHER("Hero");

        private String displayName;

        HeroClass(String displayName) {
            this.displayName = displayName;
        }

        /** Trả về tên hiển thị của lớp anh hùng. */
        public String getDisplayName() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return displayName;
        return null;
    }
    }

    private HeroFactory() {
        // TODO: Hướng dẫn hiện thực:
        // - Viết xử lý logic tại đây theo yêu cầu thiết kế.
    }

    /**
     * Khởi tạo một anh hùng thuộc lớp chỉ định, tính lại chỉ số dẫn xuất và hồi đầy HP/STA.
     *
     * @param heroClass enum lớp anh hùng
     * @param name tên hiển thị của anh hùng
     * @return cá thể anh hùng đã khởi tạo hoàn chỉnh
     * @throws IllegalArgumentException nếu {@code heroClass} là {@code null} hoặc tên rỗng
     */
    public static Character createHero(HeroClass heroClass, String name) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (heroClass == null)
        // 2. throw new IllegalArgumentException("HeroClass cannot be null.");
        // 3. if (name == null || name.trim().isEmpty())
        // 4. throw new IllegalArgumentException("Hero name cannot be null or blank.");
        // 5. String trimmedName = name.trim();
        // 6. Character hero;
        return null;
    }

    /**
     * Tạo danh sách gồm 4 cá thể anh hùng đại diện cho 4 lớp nhân vật để hiển thị trên màn hình chọn lớp.
     *
     * @param name tên hiển thị của anh hùng
     * @return danh sách bất biến gồm Hero, Hero, Hero, Hero
     * @throws IllegalArgumentException nếu tên là {@code null} hoặc rỗng
     */
    public static List<Character> createHeroChoices(String name) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (name == null || name.trim().isEmpty())
        // 2. throw new IllegalArgumentException("Hero name cannot be null or blank.");
        // 3. List<Character> choices = new ArrayList<>(HeroClass.values().length);
        // 4. for (HeroClass heroClass : HeroClass.values())
        // 5. choices.add(createHero(heroClass, name));
        // 6. return Collections.unmodifiableList(choices);
        return null;
    }

    /** Tính toán lại chỉ số dẫn xuất và hồi đầy máu cùng thể lực. */
    private static void initializeHero(Character hero) {
        // TODO: Hướng dẫn hiện thực:
        // 1. DamageCalculator.recalculateStats(hero);
        // 2. hero.getStats().restoreFullHealthAndStamina();
    }
}
