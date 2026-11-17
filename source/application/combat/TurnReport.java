package source.application.combat;

import java.util.List;
import source.domain.entity.character.Character;
import source.domain.entity.effect.StatusEffect;
import source.domain.entity.skill.Skill;

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
        this.battleState = battleState;
        this.messages = messages == null ? List.of() : List.copyOf(messages);
        this.actionRecords = actionRecords == null ? List.of() : List.copyOf(actionRecords);
        this.hero = hero;
        this.monster = monster;
        if (hero != null && hero.getStats() != null){
            this.heroHp = (int) Math.ceil(hero.getStats().getCurrentHp());
            this.heroMaxHp = (int) Math.ceil(hero.getStats().getMaxHp());
            this.heroSta = hero.getStats().getCurrentSta();
            this.heroMaxSta = hero.getStats().getMaxSta();
            this.heroShield = hero.getStats().getShield();
            this.heroActiveEffects = List.copyOf(hero.getActiveEffects());
        }
        else{
            this.heroHp = 0;
            this.heroMaxHp = 0;
            this.heroSta = 0;
            this.heroShield = 0;
            this.heroMaxSta = 0;
            this.heroActiveEffects = List.of();
        }

        if (monster != null && monster.getStats() != null){
            this.monsterHp = (int) Math.ceil(monster.getStats().getCurrentHp());
            this.monsterMaxHp = (int) Math.ceil(monster.getStats().getMaxHp());
            this.monsterSta = monster.getStats().getCurrentSta();
            this.monsterMaxSta = monster.getStats().getMaxSta();
            this.monsterShield = monster.getStats().getShield();
            this.monsterActiveEffects = List.copyOf(monster.getActiveEffects());
        }
        else{
            this.monsterHp = 0;
            this.monsterMaxHp = 0;
            this.monsterSta = 0;
            this.monsterShield = 0;
            this.monsterMaxSta = 0;
            this.monsterActiveEffects = List.of();
        }
        
    }

    /** Trả về danh sách thông điệp chiến đấu đã định dạng. */
    public List<String> getMessages() {
        return messages;
    }

    /** Trả về danh sách các bản ghi hành động chi tiết. */
    public List<ActionRecord> getActionRecords() {
        return actionRecords;
    }

    /** Trả về nhân vật anh hùng. */
    public Character getHero() {
        return hero;
    }

    /** Trả về nhân vật quái vật. */
    public Character getMonster() {
        return monster;
    }

    /** Kiểm tra xem trận đánh đã kết thúc với phần thắng thuộc về người chơi hay chưa. */
    public boolean isVictory() {
        return battleState == BattleSession.BattleState.VICTORY && (monster == null || monster.getStats() == null || monster.getStats().getCurrentHp() <= 0);
    }

    /** Kiểm tra xem trận đánh đã kết thúc với thất bại của người chơi hay chưa. */
    public boolean isDefeat() {
        return battleState == BattleSession.BattleState.DEFEAT && (hero == null || hero.getStats() == null || hero.getStats().getCurrentHp() <= 0);
    }

    /** Trả về lượng HP của anh hùng tại thời điểm chụp báo cáo. */
    public int getHeroHp() {
        return heroHp;
    }

    /** Trả về lượng HP tối đa của anh hùng tại thời điểm chụp báo cáo. */
    public int getHeroMaxHp() {
        return heroMaxHp;
    }

    /** Trả về lượng STA của anh hùng tại thời điểm chụp báo cáo. */
    public int getHeroSta() {
        return heroSta;
    }

    /** Trả về lượng STA tối đa của anh hùng tại thời điểm chụp báo cáo. */
    public int getHeroMaxSta() {
        return heroMaxSta;
    }

    /** Trả về giá trị khiên của anh hùng tại thời điểm chụp báo cáo. */
    public int getHeroShield() {
        return heroShield;
    }

    /** Trả về danh sách bản sao các hiệu ứng của anh hùng tại thời điểm chụp báo cáo. */
    public List<StatusEffect> getHeroActiveEffects() {
        return heroActiveEffects;
    }

    /** Trả về lượng HP của quái vật tại thời điểm chụp báo cáo. */
    public int getMonsterHp() {
        return monsterHp;
    }

    /** Trả về lượng HP tối đa của quái vật tại thời điểm chụp báo cáo. */
    public int getMonsterMaxHp() {
        return monsterMaxHp;
    }

    /** Trả về lượng STA của quái vật tại thời điểm chụp báo cáo. */
    public int getMonsterSta() {
        return monsterSta;
    }

    /** Trả về lượng STA tối đa của quái vật tại thời điểm chụp báo cáo. */
    public int getMonsterMaxSta() {
        return monsterMaxSta;
    }

    /** Trả về giá trị khiên của quái vật tại thời điểm chụp báo cáo. */
    public int getMonsterShield() {
        return monsterShield;
    }

    /** Trả về danh sách bản sao các hiệu ứng của quái vật tại thời điểm chụp báo cáo. */
    public List<StatusEffect> getMonsterActiveEffects() {
        return monsterActiveEffects;
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
            this.attackerName = attacker != null ? attacker.getName() : "";
            this.targetName = target != null ? target.getName() : "";
            this.attackerClass = attacker != null ? attacker.getClass().getSimpleName() : "";
            this.skill = skill;
            this.turnStartResult = turnStartResult;
            this.actionResult = actionResult;
            this.appliedEffect = appliedEffect;
        }

        /** Trả về tên nhân vật thực hiện hành động. */
        public String getAttackerName() {
        return attackerName;
    }

        /** Trả về tên nhân vật mục tiêu. */
        public String getTargetName() {
        return targetName;
    }

        /** Trả về tên lớp của nhân vật thực hiện hành động. */
        public String getAttackerClass() {
        return attackerClass;
    }

        /** Trả về kỹ năng được sử dụng. */
        public Skill getSkill() {
        return skill;
    }

        /** Trả về kết quả xử lý hiệu ứng đầu lượt đi kèm hành động này. */
        public EffectService.TurnStartResult getTurnStartResult() {
        return turnStartResult;
    }

        /** Trả về kết quả thực thi kỹ năng. */
        public ActionService.ActionResult getActionResult() {
        return actionResult;
    }

        /** Trả về kết quả áp dụng hiệu ứng trạng thái của kỹ năng (nếu có). */
        public EffectService.AppliedEffect getAppliedEffect() {
        return appliedEffect;
    }
    }
}
