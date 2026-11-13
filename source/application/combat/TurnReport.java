package source.application.combat;

import source.domain.entity.effect.StatusEffect;
import source.domain.entity.character.Character;
import source.domain.entity.skill.Skill;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Báo cáo bất biến biểu diễn kết quả của một bước hoặc một lượt chiến đấu,
 * cung cấp ảnh chụp trạng thái chiến binh và danh sách thông điệp cho giao diện và bộ điều khiển.
 * Người phụ trách: Thành viên 2 (Combat Engine & AI Quái vật)
 */
public class TurnReport {
    /** Trạng thái của phiên chiến đấu tại thời điểm xuất báo cáo. */
    private BattleSession.BattleState battleState;
    /** Danh sách các dòng thông điệp chiến đấu phát sinh trong bước này. */
    private List<String> messages;
    /** Danh sách các bản ghi hành động chi tiết phát sinh trong bước này. */
    private List<ActionRecord> actionRecords;
    /** Tham chiếu nhân vật anh hùng. */
    private Character hero;
    /** Tham chiếu nhân vật quái vật. */
    private Character monster;

    // Các trường ảnh chụp (snapshot) được ghi lại ngay khi tạo báo cáo để đảm bảo tính bất biến.
    private int heroHp;
    private int heroMaxHp;
    private int heroSta;
    private int heroMaxSta;
    private int heroShield;
    private List<StatusEffect> heroActiveEffects;

    private int monsterHp;
    private int monsterMaxHp;
    private int monsterSta;
    private int monsterMaxSta;
    private int monsterShield;
    private List<StatusEffect> monsterActiveEffects;

    /** Khởi tạo báo cáo lượt đấu và chụp lại toàn bộ chỉ số HP/STA/Khiên/Hiệu ứng của hai bên. */
    TurnReport(
            BattleSession.BattleState battleState,
            List<String> messages,
            List<ActionRecord> actionRecords,
            Character hero,
            Character monster) {
        // TODO: Hướng dẫn hiện thực:
        // 1. this.battleState = battleState;
        // 2. this.messages = messages == null ? List.of() : List.copyOf(messages);
        // 3. this.actionRecords = actionRecords == null ? List.of() : List.copyOf(actionRecords);
        // 4. this.hero = hero;
        // 5. this.monster = monster;
        // 6. if (hero != null && hero.getStats() != null)
    }

    /** Trả về danh sách thông điệp chiến đấu đã định dạng. */
    public List<String> getMessages() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return messages;
        return null;
    }

    /** Trả về danh sách các bản ghi hành động chi tiết. */
    public List<ActionRecord> getActionRecords() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return actionRecords;
        return null;
    }

    /** Trả về nhân vật anh hùng. */
    public Character getHero() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return hero;
        return null;
    }

    /** Trả về nhân vật quái vật. */
    public Character getMonster() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return monster;
        return null;
    }

    /** Kiểm tra xem trận đánh đã kết thúc với phần thắng thuộc về người chơi hay chưa. */
    public boolean isVictory() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return battleState == BattleSession.BattleState.VICTORY
        // 2. && (monster == null || monster.getStats() == null || monster.getStats().getCurrentHp() <= 0);
        return false;
    }

    /** Kiểm tra xem trận đánh đã kết thúc với thất bại của người chơi hay chưa. */
    public boolean isDefeat() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return battleState == BattleSession.BattleState.DEFEAT
        // 2. && (hero == null || hero.getStats() == null || hero.getStats().getCurrentHp() <= 0);
        return false;
    }

    /** Trả về lượng HP của anh hùng tại thời điểm chụp báo cáo. */
    public int getHeroHp() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return heroHp;
        return 0;
    }

    /** Trả về lượng HP tối đa của anh hùng tại thời điểm chụp báo cáo. */
    public int getHeroMaxHp() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return heroMaxHp;
        return 0;
    }

    /** Trả về lượng STA của anh hùng tại thời điểm chụp báo cáo. */
    public int getHeroSta() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return heroSta;
        return 0;
    }

    /** Trả về lượng STA tối đa của anh hùng tại thời điểm chụp báo cáo. */
    public int getHeroMaxSta() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return heroMaxSta;
        return 0;
    }

    /** Trả về giá trị khiên của anh hùng tại thời điểm chụp báo cáo. */
    public int getHeroShield() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return heroShield;
        return 0;
    }

    /** Trả về danh sách bản sao các hiệu ứng của anh hùng tại thời điểm chụp báo cáo. */
    public List<StatusEffect> getHeroActiveEffects() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return heroActiveEffects;
        return null;
    }

    /** Trả về lượng HP của quái vật tại thời điểm chụp báo cáo. */
    public int getMonsterHp() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return monsterHp;
        return 0;
    }

    /** Trả về lượng HP tối đa của quái vật tại thời điểm chụp báo cáo. */
    public int getMonsterMaxHp() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return monsterMaxHp;
        return 0;
    }

    /** Trả về lượng STA của quái vật tại thời điểm chụp báo cáo. */
    public int getMonsterSta() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return monsterSta;
        return 0;
    }

    /** Trả về lượng STA tối đa của quái vật tại thời điểm chụp báo cáo. */
    public int getMonsterMaxSta() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return monsterMaxSta;
        return 0;
    }

    /** Trả về giá trị khiên của quái vật tại thời điểm chụp báo cáo. */
    public int getMonsterShield() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return monsterShield;
        return 0;
    }

    /** Trả về danh sách bản sao các hiệu ứng của quái vật tại thời điểm chụp báo cáo. */
    public List<StatusEffect> getMonsterActiveEffects() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return monsterActiveEffects;
        return null;
    }

    /**
     * Bản ghi chi tiết của một hành động nhân vật đơn lẻ diễn ra trong bước lượt đấu này.
     */
    public static class ActionRecord {
        private String attackerName;
        private String targetName;
        private String attackerClass;
        private Skill skill;
        private EffectService.TurnStartResult turnStartResult;
        private ActionService.ActionResult actionResult;
        private EffectService.AppliedEffect appliedEffect;

        /** Khởi tạo bản ghi hành động từ thông tin người đánh, mục tiêu, kỹ năng và các kết quả xử lý. */
        ActionRecord(
                Character attacker,
                Character target,
                Skill skill,
                EffectService.TurnStartResult turnStartResult,
                ActionService.ActionResult actionResult,
                EffectService.AppliedEffect appliedEffect) {
        // TODO: Hướng dẫn hiện thực:
        // 1. this.attackerName = attacker != null ? attacker.getName() : "";
        // 2. this.targetName = target != null ? target.getName() : "";
        // 3. this.attackerClass = attacker != null ? attacker.getClass().getSimpleName() : "";
        // 4. this.skill = skill;
        // 5. this.turnStartResult = turnStartResult;
        // 6. this.actionResult = actionResult;
    }

        /** Trả về tên nhân vật thực hiện hành động. */
        public String getAttackerName() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return attackerName;
        return null;
    }

        /** Trả về tên nhân vật mục tiêu. */
        public String getTargetName() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return targetName;
        return null;
    }

        /** Trả về tên lớp của nhân vật thực hiện hành động. */
        public String getAttackerClass() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return attackerClass;
        return null;
    }

        /** Trả về kỹ năng được sử dụng. */
        public Skill getSkill() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return skill;
        return null;
    }

        /** Trả về kết quả xử lý hiệu ứng đầu lượt đi kèm hành động này. */
        public EffectService.TurnStartResult getTurnStartResult() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return turnStartResult;
        return null;
    }

        /** Trả về kết quả thực thi kỹ năng. */
        public ActionService.ActionResult getActionResult() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return actionResult;
        return null;
    }

        /** Trả về kết quả áp dụng hiệu ứng trạng thái của kỹ năng (nếu có). */
        public EffectService.AppliedEffect getAppliedEffect() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return appliedEffect;
        return null;
    }
    }
}
