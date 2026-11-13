package source.presentation.gui.audio;

import source.application.view.AnimationEvent;
import source.application.view.MotionType;

import java.util.List;

/**
 * Chuyển đổi chuỗi sự kiện hoạt ảnh chiến đấu thành các hiệu ứng âm thanh (SFX) tương ứng.
 * Đảm bảo tính nhất quán âm thanh: trượt đòn không phát tiếng trúng/chí mạng, chí mạng dùng âm riêng,
 * hấp thụ khiên/hồi máu/buff/debuff/hạ gục phát đúng âm hiệu đặc trưng.
 * Người phụ trách: Thành viên 1 (Data Architect & Project Coordinator)
 */
public final class AudioMapper {

    private AudioMapper() {}

    /**
     * Ánh xạ danh sách sự kiện hoạt ảnh kèm kiểu chuyển động chỉ định tường minh sang hiệu ứng âm thanh.
     *
     * @param events chuỗi sự kiện hoạt ảnh sinh ra từ kết quả lượt đánh
     * @param explicitMotion kiểu chuyển động ghi đè (nếu null sẽ tự suy ra từ chuỗi sự kiện)
     * @param audioService dịch vụ âm thanh nhận lệnh phát SFX
     */
    public static void playCombatEvents(List<AnimationEvent> events, AudioService audioService) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (events == null || events.isEmpty() || audioService == null)
        // 2. return;
        // 3. boolean hasMiss = false;
        // 4. boolean hasCrit = false;
        // 5. boolean hasDamageHp = false;
        // 6. boolean hasDamageShield = false;
    }

    /** Chọn hiệu ứng âm thanh trúng đòn tương ứng với kiểu chuyển động của đòn đánh. */
    private static SoundType resolveHitSound(MotionType motionType) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (motionType == null)
        // 2. return SoundType.MELEE_HIT;
        // 3. switch (motionType)
        // 4. case PROJECTILE:
        // 5. return SoundType.PROJECTILE_HIT;
        // 6. case CASTING:
        return null;
    }
}
