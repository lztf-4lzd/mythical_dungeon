package source.application.combat;

import source.domain.entity.character.Character;
import source.domain.calculator.DamageResult;

import java.util.List;

/**
 * Dịch vụ tiện ích định dạng các thông điệp phản hồi chiến đấu cho hiệu ứng đầu lượt,
 * kết quả hành động, chi tiết sát thương, hồi phục hỗ trợ và hiệu ứng trạng thái
 * thành danh sách chuỗi cho {@link TurnReport}.
 * Người phụ trách: Thành viên 2 (Combat Engine & AI Quái vật)
 */
final class CombatFormatter {

    private CombatFormatter() {}

    /**
     * Định dạng các nhịp sát thương DoT và hiệu ứng hết hạn ở đầu lượt vào danh sách thông điệp.
     *
     * @param character chiến binh đang ở đầu lượt
     * @param turnStartResult kết quả xử lý hiệu ứng đầu lượt
     * @param messages danh sách nhận các chuỗi thông điệp
     */
    static void formatTurnStartEffects(
            Character character,
            EffectService.TurnStartResult turnStartResult,
            List<String> messages) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (turnStartResult == null || messages == null)
        // 2. return;
        // 3. for (EffectService.DamageTick damageTick : turnStartResult.getDamageTicks())
        // 4. messages.add(damageTick.getEffectName() + " deals "
        // 5. + damageTick.getDamage() + " damage to " + character.getName() + ".");
        // 6. if (!turnStartResult.getDamageTicks().isEmpty())
    }

    /**
     * Định dạng thông báo trượt, chi tiết sát thương, hồi phục hỗ trợ và trạng thái HP/STA vào danh sách.
     *
     * @param attacker nhân vật thực hiện kỹ năng
     * @param target nhân vật mục tiêu
     * @param actionResult kết quả thực thi kỹ năng
     * @param messages danh sách nhận thông điệp
     */
    static void formatActionResult(
            Character attacker,
            Character target,
            ActionService.ActionResult actionResult,
            List<String> messages) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (actionResult == null || messages == null)
        // 2. return;
        // 3. DamageResult damageResult = actionResult.getDamageResult();
        // 4. if (actionResult.wasMissed())
        // 5. messages.add("Miss!");
        // 6. if (damageResult != null)
    }

    /**
     * Định dạng ưu/nhược thế nguyên tố, sát thương gây ra, chí mạng, nội tại kích hoạt,
     * hấp thụ khiên, sát thương vào máu, hút máu và HP còn lại của mục tiêu.
     */
    private static void formatDamageDetails(
            Character attacker,
            Character target,
            ActionService.ActionResult actionResult,
            DamageResult damageResult,
            List<String> messages) {
        // TODO: Hướng dẫn hiện thực:
        // 1. double elementMultiplier = damageResult.getElementMultiplier();
        // 2. if (elementMultiplier > 1.0)
        // 3. messages.add(String.format(
        // 4. java.util.Locale.ROOT,
        // 5. "Elemental advantage! (%.2fx)",
        // 6. elementMultiplier));
    }

    /**
     * Định dạng thông tin hi sinh máu, hồi máu, hồi thể lực và thanh tẩy hiệu ứng xấu.
     */
    private static void formatSupportDetails(
            Character attacker,
            ActionService.ActionResult actionResult,
            List<String> messages) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (actionResult.getSelfSacrificedHp() > 0)
        // 2. messages.add(attacker.getName() + " sacrifices "
        // 3. + actionResult.getSelfSacrificedHp() + " HP.");
        // 4. if (actionResult.getHealthRestored() > 0)
        // 5. messages.add(attacker.getName() + " restores "
        // 6. + actionResult.getHealthRestored() + " HP.");
    }

    /**
     * Định dạng thông báo áp dụng hiệu ứng trạng thái hoặc kháng hiệu ứng thành công vào danh sách thông điệp.
     *
     * @param appliedEffect kết quả áp dụng hiệu ứng
     * @param messages danh sách nhận dòng thông điệp
     */
    static void formatAppliedEffect(
            EffectService.AppliedEffect appliedEffect,
            List<String> messages) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (appliedEffect == null || messages == null)
        // 2. return;
        // 3. String receiverName = appliedEffect.getReceiverName();
        // 4. if (appliedEffect.wasResisted())
        // 5. messages.add(receiverName + " resisted "
        // 6. + appliedEffect.getEffectName() + ".");
    }
}
