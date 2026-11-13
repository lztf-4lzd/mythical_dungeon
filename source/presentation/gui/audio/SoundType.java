package source.presentation.gui.audio;

/**
 * Danh mục các loại hiệu ứng âm thanh (SFX) gắn với sự kiện giao diện và chiến đấu.
 * Người phụ trách: Thành viên 1 (Data Architect & Project Coordinator)
 */
enum SoundType {
    BUTTON_CLICK("sfx_button_click.wav"),
    MELEE_HIT("sfx_melee_hit.wav"),
    PROJECTILE_HIT("sfx_projectile_hit.wav"),
    CASTING_HIT("sfx_casting_hit.wav"),
    CRITICAL_HIT("sfx_critical_hit.wav"),
    SHIELD_ABSORB("sfx_shield_absorb.wav"),
    MISS("sfx_miss.wav"),
    HEAL("sfx_heal.wav"),
    BUFF("sfx_buff.wav"),
    DEBUFF("sfx_debuff.wav"),
    DEFEAT("sfx_defeat.wav");

    /** Tên tệp âm thanh WAV của hiệu ứng. */
    private String filename;

    SoundType(String filename) {
        this.filename = filename;
    }

    /** Lấy đường dẫn tài nguyên đầy đủ trên classpath. */
    String getResourcePath() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return "/assets/audio/sfx/" + filename;
        return null;
    }
}
