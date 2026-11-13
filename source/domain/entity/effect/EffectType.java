package source.domain.entity.effect;

public enum EffectType {
    POISON,             // Độc: Gây sát thương theo lượt
    BURN,               // Đốt: Gây sát thương theo lượt
    BLEED,              // Máu: Gây sát thương theo lượt
    REGEN,              // Hồi phục: Hồi máu theo lượt

    PARALYZE,           // Tê liệt: Ngăn mục tiêu hành động vào đầu lượt

    SHIELD,             // Khiên chắn: Hấp thụ sát thương nhận vào trước khi trừ máu
    UNDYING,            // Bất tử tạm thời: Giữ lại mạng sống và hồi phần trăm máu tối đa khi nhận đòn kết liễu
    DAMAGE_REDUCTION,   // Giảm sát thương nhận vào

    ATK_BUFF,           // Tăng chỉ số tấn công
    DEF_BUFF,           // Tăng chỉ số phòng thủ
    SPD_BUFF,           // Tăng chỉ số tốc độ
    DODGE_BUFF,         // Tăng khả năng né
    ACCURACY_BUFF,      // Tăng độ chính xác
    CRIT_BUFF,          // Tăng tỷ lệ chí mạng
    RESISTANCE_BUFF,    // Tăng khả năng kháng

    WEAKEN,             // Làm yếu: Giảm sát thương đầu ra
    DEF_DOWN,           // Giảm phòng thủ
    FROST,              // Đông lạnh: Giảm tốc độ hành động
    BLIND,              // Mù: Giảm độ chính xác
    DODGE_DOWN,         // Giảm khả năng né
    CRIT_DOWN,          // Giảm tỷ lệ chí mạng
    RESISTANCE_DOWN,    // Giảm khả năng kháng
    VULNERABLE,         // Tổn thương: Tăng sát thương nhận vào

    INSTANT_DEATH;       // Gây chết ngay lập tức
}
