package source.application.combat;

import source.domain.entity.character.Character;
import source.domain.util.GameConstants;

/**
 * Áp dụng lượng hồi phục thể lực (STA) cố định diễn ra vào đầu mỗi vòng đấu.
 * Người phụ trách: Thành viên 2 (Combat Engine & AI Quái vật)
 */
final class RecoveryService {
    /** Lượng thể lực hồi phục mặc định đầu mỗi vòng đấu. */
    private static final int ROUND_STAMINA_RECOVERY = GameConstants.ROUND_STAMINA_RECOVERY;

    private RecoveryService() {}

    /** Hồi lượng thể lực cố định của vòng đấu mà không vượt quá thể lực tối đa. */
    static void recoverRoundStamina(Character character) {
        // TODO: Hướng dẫn hiện thực:
        // 1. int maxSta = character.getStats().getMaxSta();
        // 2. int currentSta = character.getStats().getCurrentSta();
        // 3. character.getStats().setCurrentSta(
        // 4. Math.min(maxSta, currentSta + ROUND_STAMINA_RECOVERY));
    }
}
