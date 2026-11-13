package source.presentation.gui.audio;

/**
 * Giao diện trừu tượng hóa phần cứng phát âm thanh, hỗ trợ phát không chặn (non-blocking).
 * Người phụ trách: Thành viên 1 (Data Architect & Project Coordinator)
 */
interface AudioBackend {

    /**
     * Phát một hiệu ứng âm thanh (SFX) với hệ số âm lượng từ 0.0 đến 1.0.
     *
     * @param resourcePath đường dẫn tài nguyên âm thanh trên classpath
     * @param volume mức âm lượng từ 0.0f (tắt tiếng) đến 1.0f (tối đa)
     */
    void playSfx(String resourcePath, float volume);

    /**
     * Bắt đầu phát hoặc lặp lại một bản nhạc nền (BGM).
     *
     * @param resourcePath đường dẫn tài nguyên âm thanh trên classpath
     * @param volume mức âm lượng từ 0.0f đến 1.0f
     * @param loop {@code true} nếu muốn lặp lại liên tục
     */
    void playBgm(String resourcePath, float volume, boolean loop);

    /**
     * Dừng bản nhạc nền đang phát.
     */
    void stopBgm();

    /**
     * Cập nhật âm lượng của bản nhạc nền đang phát theo thời gian thực.
     *
     * @param volume mức âm lượng từ 0.0f đến 1.0f
     */
    void setBgmVolume(float volume);
}
