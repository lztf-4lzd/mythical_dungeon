package source.domain.util;

public final class GameConstants {
    private GameConstants() {}

    /* Cấp độ tối đa của nhân vật */
    public static final int MAX_LEVEL = 10;
    /* Lượng kinh nghiệm cơ sở để lên cấp (cấp N cần N * BASE_EXP) */
    public static final int BASE_EXP = 100;
    /* Số ô kỹ năng tối đa */
    public static final int MAX_SKILL_SLOTS = 5;
    /* Số lượng kỹ năng thường người chơi bắt buộc chọn */
    public static final int REGULAR_SKILL_PICKS = 3;
    /* Số lượng tuyệt kỹ người chơi bắt buộc chọn */
    public static final int ULTIMATE_SKILL_PICKS = 1;
    /* Tổng số kỹ năng chiến thuật người chơi chọn (3 thường + 1 tuyệt kỹ) */
    public static final int TACTICAL_SKILL_PICKS = REGULAR_SKILL_PICKS + ULTIMATE_SKILL_PICKS;
    /* Tổng số kỹ năng Hero mang vào trận (1 đánh thường + 4 kỹ năng chiến thuật) */
    public static final int HERO_BATTLE_SKILLS = 1 + TACTICAL_SKILL_PICKS;
    /* Số lượng kỹ năng tiêu chuẩn của Monster */
    public static final int MONSTER_BATTLE_SKILLS = 4;
    /* Số lượng kỹ năng đặc trưng của Boss */
    public static final int BOSS_SIGNATURE_SKILLS = 2;

    /* Lượng stamina hồi phục mỗi lượt */
    public static final int ROUND_STAMINA_RECOVERY = 9;

    /* Vòng đấu bắt đầu kích hoạt cơ chế Enrage */
    public static final int ENRAGE_START_ROUND = 60;
    /* Bước tăng hệ số sát thương Enrage mỗi vòng sau mốc ENRAGE */
    public static final double ENRAGE_MULTIPLIER_STEP = 0.10;

    /* Hệ số nhân máu cho Boss */
    public static final double BOSS_HP_MULTIPLIER = 2.0;
    /* Hệ số nhân máu cho Mini-Boss */
    public static final double MINI_BOSS_HP_MULTIPLIER = 1.5;

    /* Hệ số sát thương khi khắc chế nguyên tố */
    public static final double MULTIPLIER_ADVANTAGE = 1.5;
    /* Hệ số sát thương khi bị khắc chế nguyên tố */
    public static final double MULTIPLIER_DISADVANTAGE = 0.75;
    /* Hệ số sát thương nguyên tố Normal */
    public static final double MULTIPLIER_NORMAL = 1.0;

    /* Hệ số sát thương chí mạng */
    public static final double CRIT_MULTIPLIER = 1.5;

    /* Lượng HP cơ bản */
    public static final double HP_BASE = 60.0;
    /* Lượng HP cộng thêm trên mỗi điểm VIT */
    public static final double HP_PER_VIT = 8.0;
    /* Lượng HP cộng thêm trên mỗi điểm STR */
    public static final double HP_PER_STR = 2.0;

    /* Lượng Stamina cơ bản */
    public static final double STAMINA_BASE = 30.0;
    /* Lượng Stamina cộng thêm trên mỗi điểm END */
    public static final double STAMINA_PER_END = 3.0;
    /* Lượng Stamina cộng thêm trên mỗi điểm DEX */
    public static final double STAMINA_PER_DEX = 0.5;

    /* Sức tấn công cơ bản */
    public static final double ATTACK_BASE = 8.0;
    /* Sức tấn công cộng thêm trên mỗi điểm STR hoặc INT */
    public static final double ATTACK_PER_STR_OR_INT = 1.2;
    /* Sức tấn công cộng thêm trên mỗi điểm DEX hoặc MND */
    public static final double ATTACK_PER_DEX_OR_MND = 0.25;

    /* Phòng thủ vật lý / phép thuật cơ bản */
    public static final double DEFENSE_BASE = 5.0;
    /* Phòng thủ cộng thêm trên mỗi điểm VIT hoặc MND */
    public static final double DEFENSE_PER_VIT_OR_MND = 0.8;
    /* Phòng thủ cộng thêm trên mỗi điểm END hoặc INT */
    public static final double DEFENSE_PER_END_OR_INT = 0.4;

    /* Tốc độ cơ bản */
    public static final double SPEED_BASE = 10.0;
    /* Tốc độ cộng thêm trên mỗi điểm DEX */
    public static final double SPEED_PER_DEX = 0.6;

    /* Tỉ lệ chí mạng cơ bản (%) */
    public static final double CRIT_BASE = 3.0;
    /* Tỉ lệ chí mạng cộng thêm trên mỗi điểm DEX (%) */
    public static final double CRIT_PER_DEX = 0.12;
    /* Tỉ lệ chí mạng tối đa từ chỉ số (%) */
    public static final double CRIT_MAX = 25.0;

    /* Kháng hiệu ứng cộng thêm trên mỗi điểm VIT (%) */
    public static final double RESISTANCE_PER_VIT = 0.25;
    /* Kháng hiệu ứng cộng thêm trên mỗi điểm MND (%) */
    public static final double RESISTANCE_PER_MND = 0.05;
    /* Kháng hiệu ứng tối đa (%) */
    public static final double RESISTANCE_MAX = 35.0;

    /* Độ chính xác cơ bản (%) */
    public static final double ACCURACY_BASE = 90.0;
    /* Độ chính xác cộng thêm trên mỗi điểm DEX (%) */
    public static final double ACCURACY_PER_DEX = 0.15;
    /* Độ chính xác tối đa (%) */
    public static final double ACCURACY_MAX = 100.0;

    /* Tỷ lệ né tránh cơ bản (%) */
    public static final double DODGE_BASE = 0.0;
    /* Tỷ lệ né tránh cộng thêm trên mỗi điểm DEX (%) */
    public static final double DODGE_PER_DEX = 0.20;
    /* Tỷ lệ né tránh tối đa (%) */
    public static final double DODGE_MAX = 30.0;
}
