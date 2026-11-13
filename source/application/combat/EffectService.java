package source.application.combat;

import source.domain.entity.effect.EffectType;
import source.domain.entity.effect.StatusEffect;
import source.domain.entity.character.Character;
import source.domain.entity.skill.Skill;
import source.domain.util.GameRandom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Xử lý các hiệu ứng trạng thái đầu lượt và áp dụng hiệu ứng đi kèm kỹ năng lên chiến binh.
 * Người phụ trách: Thành viên 2 (Combat Engine & AI Quái vật)
 */
public final class EffectService {
    /** Bộ sinh số ngẫu nhiên mặc định cho phép thử xác suất hiệu ứng. */
    private static final Random RNG = GameRandom.getDefault();

    private EffectService() {}

    /** Tính tốc độ hiệu dụng sau khi áp dụng Tăng tốc và Băng giá mà không sửa tốc độ gốc. */
    static int getEffectiveSpd(Character character) {
        // TODO: Hướng dẫn hiện thực:
        // 1. double effectiveSpeed = character.getStats().getSpd();
        // 2. for (StatusEffect effect : character.getActiveEffects())
        // 3. if (effect.getType() == EffectType.SPD_BUFF)
        // 4. int val = Math.max(0, effect.getValue());
        // 5. if (val >= 15)
        // 6. effectiveSpeed = Math.round(effectiveSpeed * (1.0 + val / 100.0));
        return 0;
    }

    /**
     * Áp dụng sát thương/hồi máu đầu lượt và giảm thời lượng hiệu ứng, sau đó trả về kết quả chi tiết.
     * Sát thương theo lượt (DoT) bỏ qua khiên và phòng thủ, đồng thời kích hoạt trước khi giảm số lượt.
     */
    static TurnStartResult processTurnStartEffectsWithResult(Character character) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (character.getStats().getCurrentHp() <= 0)
        // 2. return new TurnStartResult(false, List.of(), List.of());
        // 3. List<StatusEffect> effectsToProcess = character.getActiveEffects();
        // 4. if (effectsToProcess.isEmpty())
        // 5. return new TurnStartResult(false, List.of(), List.of());
        // 6. boolean isParalyzed = false;
        return null;
    }

    /** Thực thi tác dụng đầu lượt của một hiệu ứng và cho biết nó có gây tê liệt hay không. */
    private static boolean applyTurnStartEffect(
            Character character,
            StatusEffect effect,
            List<DamageTick> damageTicks) {
        // TODO: Hướng dẫn hiện thực:
        // 1. switch (effect.getType())
        // 2. case POISON:
        // 3. case BURN:
        // 4. case BLEED:
        // 5. if (character.getStats().getCurrentHp() <= 0)
        // 6. return false;
        return false;
    }

    /** Giảm số lượt còn lại của các hiệu ứng và xóa những hiệu ứng đã hết hạn. */
    private static List<String> decrementEffectDurations(
            Character character,
            List<StatusEffect> remainingEffects) {
        // TODO: Hướng dẫn hiện thực:
        // 1. List<String> expiredEffects = new ArrayList<>();
        // 2. int activeEffectIndex = 0;
        // 3. for (StatusEffect effect : remainingEffects)
        // 4. effect.decrementDuration();
        // 5. if (effect.getDuration() <= 0)
        // 6. expiredEffects.add(effect.getName());
        return null;
    }

    /**
     * Trừ sát thương vào HP và kích hoạt hiệu ứng UNDYING đầu tiên nếu sát thương gây tử vong.
     *
     * @param character nhân vật nhận sát thương
     * @param damage lượng sát thương truyền vào HP (sau khi đã trừ khiên nếu có)
     * @return lượng máu thực tế bị mất, tối đa bằng HP hiện có trước khi nhận đòn
     */
    static int applyDamage(Character character, int damage) {
        // TODO: Hướng dẫn hiện thực:
        // 1. int currentHp = character.getStats().getCurrentHp();
        // 2. if (damage > 0 && currentHp > 0 && damage >= currentHp)
        // 3. List<StatusEffect> activeEffects = character.getActiveEffects();
        // 4. for (int effectIndex = 0; effectIndex < activeEffects.size(); effectIndex++)
        // 5. StatusEffect effect = activeEffects.get(effectIndex);
        // 6. if (effect.getType() == EffectType.UNDYING)
        return 0;
    }

    /**
     * Xóa toàn bộ hiệu ứng bất lợi trên nhân vật và trả về số lượng đã xóa.
     *
     * @param character nhân vật được thanh tẩy
     * @return số lượng hiệu ứng bất lợi đã bị loại bỏ
     */
    static int cleanseNegativeEffects(Character character) {
        // TODO: Hướng dẫn hiện thực:
        // 1. List<StatusEffect> activeEffects = character.getActiveEffects();
        // 2. int removed = 0;
        // 3. for (int effectIndex = 0; effectIndex < activeEffects.size(); effectIndex++)
        // 4. StatusEffect effect = activeEffects.get(effectIndex);
        // 5. if (isNegativeEffect(effect.getType()))
        // 6. character.removeActiveEffect(effectIndex - removed);
        return 0;
    }

    /** Xác định các hiệu ứng bất lợi có thể bị xóa bởi thanh tẩy; giữ nguyên các buff có lợi. */
    private static boolean isNegativeEffect(EffectType type) {
        // TODO: Hướng dẫn hiện thực:
        // 1. return type == EffectType.POISON
        // 2. || type == EffectType.BURN
        // 3. || type == EffectType.BLEED
        // 4. || type == EffectType.FROST
        // 5. || type == EffectType.PARALYZE
        // 6. || type == EffectType.WEAKEN
        return false;
    }

    /**
     * Kiểm tra điều kiện, kháng hiệu ứng và xác suất trước khi gắn hiệu ứng lên mục tiêu.
     *
     * @return kết quả áp dụng hiệu ứng, thông báo kháng thành công, hoặc {@code null} nếu không kích hoạt
     */
    static AppliedEffect applySkillEffectWithResult(
            Character attacker,
            Character target,
            Skill skill) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (!skill.hasEffect()) return null;
        // 2. StatusEffect effect = skill.getAppliedEffect();
        // 3. Character receiver = (skill.isSelfTarget() || !isHarmfulEffect(effect.getType()))
        // 4. ? attacker
        // 5. : target;
        // 6. if (receiver.getStats().getCurrentHp() <= 0)
        return null;
    }

    /** Gắn hiệu ứng sau khi quay số thành công; xử lý Tử Tức bằng sát thương chí tử. */
    private static AppliedEffect applySuccessfulEffect(
            Character receiver,
            StatusEffect effect) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (effect.getType() == EffectType.INSTANT_DEATH)
        // 2. applyDamage(receiver, receiver.getStats().getCurrentHp());
        // 3. return new AppliedEffect(receiver, effect.getName(), effect.getType(), false);
        // 4. receiver.addActiveEffect(new StatusEffect(
        // 5. effect.getName(),
        // 6. effect.getType(),
        return null;
    }

    /** Kiểm tra xem loại hiệu ứng có phải hiệu ứng có hại lên đối thủ hay không. */
    static boolean isHarmfulEffect(EffectType type) {
        // TODO: Hướng dẫn hiện thực:
        // 1. return isNegativeEffect(type)
        // 2. || type == EffectType.INSTANT_DEATH;
        return false;
    }

    /** Giảm xác suất gốc của hiệu ứng có hại theo phần trăm kháng hiệu ứng của mục tiêu. */
    private static double calculateEffectiveChance(double baseChance, double finalResistance) {
        // TODO: Hướng dẫn hiện thực:
        // 1. return baseChance * (1.0 - finalResistance / 100.0);
        return 0.0;
    }

    /** Tính tỷ lệ HP hiện tại trên HP tối đa để kiểm tra ngưỡng máu. */
    private static double healthRatio(Character character) {
        // TODO: Hướng dẫn hiện thực:
        // 1. return (double) character.getStats().getCurrentHp()
        // 2. / Math.max(1, character.getStats().getMaxHp());
        return 0.0;
    }

    /** Kết quả thu thập được khi xử lý các hiệu ứng trạng thái ở đầu lượt. */
    public static final class TurnStartResult {
        private boolean paralyzed;
        private List<DamageTick> damageTicks;
        private List<String> expiredEffects;

        private TurnStartResult(
                boolean paralyzed,
                List<DamageTick> damageTicks,
                List<String> expiredEffects) {
            this.paralyzed = paralyzed;
            this.damageTicks = Collections.unmodifiableList(new ArrayList<>(damageTicks));
            this.expiredEffects = Collections.unmodifiableList(new ArrayList<>(expiredEffects));
        }

        /** Kiểm tra xem nhân vật có bị tê liệt mất lượt hay không. */
        boolean isParalyzed() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return paralyzed;
        return false;
    }

        /** Trả về danh sách các nhịp sát thương theo lượt (DoT) đã kích hoạt. */
        public List<DamageTick> getDamageTicks() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return damageTicks;
        return null;
    }

        /** Trả về danh sách tên các hiệu ứng vừa hết hạn ở đầu lượt. */
        public List<String> getExpiredEffects() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return expiredEffects;
        return null;
    }
    }

    /** Thông tin tên hiệu ứng và lượng sát thương của một nhịp sát thương theo lượt (DoT). */
    public static final class DamageTick {
        private String effectName;
        private int damage;

        private DamageTick(String effectName, int damage) {
            this.effectName = effectName;
            this.damage = damage;
        }

        /** Trả về tên hiệu ứng gây sát thương. */
        public String getEffectName() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return effectName;
        return null;
    }

        /** Trả về lượng sát thương của nhịp DoT. */
        public int getDamage() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return damage;
        return 0;
    }
    }

    /** Kết quả áp dụng hoặc kháng một hiệu ứng trạng thái từ kỹ năng. */
    public static final class AppliedEffect {
        private String receiverName;
        private String effectName;
        private EffectType type;
        private boolean resisted;

        private AppliedEffect(Character receiver, String effectName, EffectType type, boolean resisted) {
            this.receiverName = receiver.getName();
            this.effectName = effectName;
            this.type = type;
            this.resisted = resisted;
        }

        /** Trả về tên nhân vật nhận hiệu ứng. */
        public String getReceiverName() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return receiverName;
        return null;
    }

        /** Trả về tên hiệu ứng trạng thái. */
        public String getEffectName() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return effectName;
        return null;
    }

        /** Trả về loại hiệu ứng trạng thái. */
        EffectType getType() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return type;
        return null;
    }

        /** Kiểm tra xem mục tiêu đã kháng thành công hiệu ứng này hay chưa. */
        public boolean wasResisted() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return resisted;
        return false;
    }
    }
}
