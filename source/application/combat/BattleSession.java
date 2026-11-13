package source.application.combat;

import source.domain.entity.character.Character;
import source.domain.entity.skill.Skill;
import source.domain.calculator.DamageCalculator;
import source.domain.util.GameConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Phiên chiến đấu theo từng bước rời rạc quản lý thứ tự lượt, hồi phục đầu vòng,
 * thực thi kỹ năng và chuyển đổi trạng thái cho hoạt ảnh GUI.
 * Người phụ trách: Thành viên 2 (Combat Engine & AI Quái vật)
 */
public class BattleSession {

    /** Các trạng thái của phiên chiến đấu. */
    enum BattleState {
        /** Sẵn sàng bắt đầu trận đánh. */
        READY,
        /** Đang chờ người chơi chọn kỹ năng. */
        AWAITING_PLAYER_ACTION,
        /** Đang chờ thực thi hành động của quái vật. */
        AWAITING_MONSTER_ACTION,
        /** Người chơi giành chiến thắng. */
        VICTORY,
        /** Người chơi thất bại. */
        DEFEAT
    }

    /** Nhân vật anh hùng của người chơi. */
    private Character hero;
    /** Quái vật đối thủ trong trận đánh. */
    private Character monster;
    /** Số thứ tự vòng đấu hiện tại (bắt đầu từ 1 khi vào trận). */
    private int roundNumber;
    /** Trạng thái hiện tại của phiên chiến đấu. */
    private BattleState state;
    /** Cờ đánh dấu anh hùng được đi trước trong vòng hiện tại. */
    private boolean heroActsFirstInRound;
    /** Cờ đánh dấu quái vật đã hành động trong vòng hiện tại hay chưa. */
    private boolean monsterActedThisRound;
    /** Báo cáo kết quả của bước xử lý gần nhất. */
    private TurnReport lastReport;
    /** Kết quả xử lý hiệu ứng đầu lượt của anh hùng đang chờ gắn vào bản ghi hành động. */
    private EffectService.TurnStartResult pendingHeroTurnStart;
    /** Cờ đánh dấu đã ghi nhận thông báo trạng thái Cuồng Nộ (Enrage) hay chưa. */
    private boolean enrageLogged;

    /**
     * Khởi tạo phiên chiến đấu, tính lại chỉ số của quái vật và hồi đầy HP/STA cho quái
     * trong khi giữ nguyên trạng thái hiện có của anh hùng.
     *
     * @param hero nhân vật người chơi
     * @param monster quái vật đối thủ
     */
    public BattleSession(Character hero, Character monster) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (hero == null || monster == null)
        // 2. throw new IllegalArgumentException("Combatants cannot be null.");
        // 3. this.hero = hero;
        // 4. this.monster = monster;
        // 5. this.roundNumber = 0;
        // 6. this.state = BattleState.READY;
    }

    /**
     * Bắt đầu phiên chiến đấu, mở vòng 1.
     * Nếu anh hùng đi trước, chuyển sang {@code AWAITING_PLAYER_ACTION}.
     * Nếu quái vật đi trước, dừng ở {@code AWAITING_MONSTER_ACTION} để chờ xử lý.
     *
     * @return báo cáo mở đầu trận đánh
     */
    public TurnReport start() {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (state != BattleState.READY)
        // 2. return lastReport != null
        // 3. ? lastReport
        // 4. : buildReport(List.of(), List.of());
        // 5. List<String> messages = new ArrayList<>();
        // 6. List<TurnReport.ActionRecord> actionRecords = new ArrayList<>();
        return null;
    }

    /**
     * Chỉ thực thi hành động của người chơi ở chế độ rời rạc.
     * Không tự động chạy lượt của quái vật để giao diện có thể phát hoạt ảnh riêng cho từng bên.
     *
     * @param skillSlotIndex chỉ số ô kỹ năng (bắt đầu từ 0)
     * @return báo cáo kết quả hành động của người chơi
     */
    public TurnReport executePlayerActionOnly(int skillSlotIndex) {
        // TODO: Hướng dẫn hiện thực:
        // 1. List<String> messages = new ArrayList<>();
        // 2. List<TurnReport.ActionRecord> actionRecords = new ArrayList<>();
        // 3. if (state == BattleState.VICTORY || state == BattleState.DEFEAT)
        // 4. return buildReport(List.of("Battle is already over."), List.of());
        // 5. if (state != BattleState.AWAITING_PLAYER_ACTION)
        // 6. return buildReport(List.of("Not awaiting player action."), List.of());
        return null;
    }

    /**
     * Thực thi riêng lượt của quái vật khi đang ở trạng thái {@code AWAITING_MONSTER_ACTION}.
     * Nếu quái vật đi trước trong vòng, chuyển sang {@code AWAITING_PLAYER_ACTION} sau khi xong.
     * Nếu quái vật đi sau, khép lại vòng hiện tại và mở vòng mới ở chế độ rời rạc.
     *
     * @return báo cáo kết quả hành động của quái vật
     */
    public TurnReport executeMonsterActionOnly() {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (state == BattleState.VICTORY || state == BattleState.DEFEAT)
        // 2. return buildReport(List.of("Battle is already over."), List.of());
        // 3. if (state != BattleState.AWAITING_MONSTER_ACTION)
        // 4. return buildReport(List.of("Not awaiting monster action."), List.of());
        // 5. List<String> messages = new ArrayList<>();
        // 6. List<TurnReport.ActionRecord> actionRecords = new ArrayList<>();
        return null;
    }

    /** Kiểm tra xem ô kỹ năng chỉ định của anh hùng hiện có thể sử dụng hay không. */
    public boolean canHeroUseSkill(int skillSlotIndex) {
        // TODO: Hướng dẫn hiện thực:
        // 1. List<Skill> skills = hero.getSkills();
        // 2. if (skillSlotIndex < 0 || skillSlotIndex >= skills.size())
        // 3. return false;
        // 4. Skill skill = skills.get(skillSlotIndex);
        // 5. if (skill.getMods().isOncePerBattle() && hero.hasConsumedOnceSkill(skill.getName()))
        // 6. return false;
        return false;
    }

    // =========================================================================
    // Máy trạng thái nội bộ & Xử lý chiến đấu
    // =========================================================================

    /**
     * Khởi tạo vòng đấu mới ở chế độ rời rạc mà không tự động thực thi hành động của quái vật.
     * Hồi phục thể lực đầu vòng, so sánh tốc độ hiệu dụng và chuyển sang trạng thái chờ tương ứng.
     */
    private void startRoundGranular(List<String> messages, List<TurnReport.ActionRecord> actionRecords) {
        // TODO: Hướng dẫn hiện thực:
        // 1. while (state != BattleState.VICTORY && state != BattleState.DEFEAT)
        // 2. roundNumber++;
        // 3. messages.add("Round " + roundNumber);
        // 4. checkAndLogEnrage(messages);
        // 5. RecoveryService.recoverRoundStamina(hero);
        // 6. RecoveryService.recoverRoundStamina(monster);
    }

    /** Xử lý hiệu ứng đầu lượt của anh hùng và trả về {@code true} nếu anh hùng có thể hành động. */
    private boolean processHeroTurnStart(List<String> messages, List<TurnReport.ActionRecord> actionRecords) {
        // TODO: Hướng dẫn hiện thực:
        // 1. messages.add(hero.getName() + "'s turn.");
        // 2. EffectService.TurnStartResult turnStartResult =
        // 3. EffectService.processTurnStartEffectsWithResult(hero);
        // 4. CombatFormatter.formatTurnStartEffects(hero, turnStartResult, messages);
        // 5. if (hero.getStats().getCurrentHp() <= 0)
        // 6. pendingHeroTurnStart = null;
        return false;
    }

    /** Xử lý trọn vẹn lượt của quái vật gồm hiệu ứng đầu lượt, chọn kỹ năng AI và thực thi. */
    private void processMonsterTurn(List<String> messages, List<TurnReport.ActionRecord> actionRecords) {
        // TODO: Hướng dẫn hiện thực:
        // 1. messages.add(monster.getName() + "'s turn.");
        // 2. EffectService.TurnStartResult turnStartResult =
        // 3. EffectService.processTurnStartEffectsWithResult(monster);
        // 4. CombatFormatter.formatTurnStartEffects(monster, turnStartResult, messages);
        // 5. if (monster.getStats().getCurrentHp() <= 0)
        // 6. endBattle(true, messages);
    }

    /** Thực thi kỹ năng đã chọn, áp dụng hiệu ứng đi kèm và lưu bản ghi hành động. */
    private void executeSkill(
            Character actingCharacter,
            Character targetCharacter,
            Skill chosenSkill,
            EffectService.TurnStartResult turnStartResult,
            List<String> messages,
            List<TurnReport.ActionRecord> actionRecords) {
        // TODO: Hướng dẫn hiện thực:
        // 1. double enrageMultiplier = getEnrageDamageMultiplier();
        // 2. ActionService.ActionResult actionResult =
        // 3. ActionService.executeSkillActionWithResult(
        // 4. actingCharacter,
        // 5. targetCharacter,
        // 6. chosenSkill,
    }

    /** Khép lại trận đánh, cập nhật trạng thái thắng/thua và xóa khiên tạm thời của hai bên. */
    private void endBattle(boolean heroWon, List<String> messages) {
        // TODO: Hướng dẫn hiện thực:
        // 1. try
        // 2. if (monster.getStats().getCurrentHp() <= 0)
        // 3. messages.add(monster.getName() + " has been defeated.");
        // 4. if (hero.getStats().getCurrentHp() <= 0)
        // 5. messages.add(hero.getName() + " has been defeated.");
        // 6. if (heroWon && hero.getStats().getCurrentHp() > 0 && monster.getStats().getCurrentHp() <= 0)
    }

    /** Đóng gói trạng thái hiện tại và danh sách thông điệp thành đối tượng {@link TurnReport}. */
    private TurnReport buildReport(
            List<String> messages,
            List<TurnReport.ActionRecord> actionRecords) {
        // TODO: Hướng dẫn hiện thực:
        // 1. return new TurnReport(
        // 2. state,
        // 3. messages,
        // 4. actionRecords,
        // 5. hero,
        // 6. monster);
        return null;
    }

    /** Kiểm tra và ghi thông điệp kích hoạt Cuồng Nộ khi số vòng vượt ngưỡng quy định. */
    private void checkAndLogEnrage(List<String> messages) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (roundNumber > GameConstants.ENRAGE_START_ROUND && !enrageLogged)
        // 2. enrageLogged = true;
        // 3. messages.add("Enrage is active! Direct damage is escalating!");
    }

    /**
     * Trả về hệ số nhân sát thương Cuồng Nộ cho vòng hiện tại
     * (vòng <= 60 là 1.0; vòng > 60 tăng thêm 0.10 mỗi vòng).
     */
    private double getEnrageDamageMultiplier() {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (roundNumber <= GameConstants.ENRAGE_START_ROUND)
        // 2. return 1.0;
        // 3. return 1.0 + GameConstants.ENRAGE_MULTIPLIER_STEP * (roundNumber - GameConstants.ENRAGE_START_ROUND);
        return 0.0;
    }

    // =========================================================================
    // Các phương thức truy vấn (Getters)
    // =========================================================================

    /** Trả về nhân vật anh hùng của người chơi. */
    public Character getHero() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return hero;
        return null;
    }

    /** Trả về quái vật đối thủ trong phiên. */
    public Character getMonster() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return monster;
        return null;
    }

    /** Trả về số thứ tự vòng đấu hiện tại. */
    public int getRoundNumber() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return roundNumber;
        return 0;
    }

    /** Trả về trạng thái hiện tại của phiên chiến đấu. */
    public BattleState getState() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return state;
        return null;
    }

    /** Kiểm tra xem phiên có đang chờ hành động của người chơi hay không. */
    public boolean isAwaitingPlayerAction() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return state == BattleState.AWAITING_PLAYER_ACTION;
        return false;
    }

    /** Kiểm tra xem phiên có đang chờ hành động của quái vật hay không. */
    public boolean isAwaitingMonsterAction() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return state == BattleState.AWAITING_MONSTER_ACTION;
        return false;
    }

    /** Kiểm tra xem trận đánh đã kết thúc (thắng, thua hoặc một bên hết HP) hay chưa. */
    public boolean isGameOver() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return isVictory() || isDefeat() || hero.getStats().getCurrentHp() <= 0 || monster.getStats().getCurrentHp() <= 0;
        return false;
    }

    /** Kiểm tra xem người chơi đã giành chiến thắng hợp lệ hay chưa. */
    public boolean isVictory() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return state == BattleState.VICTORY && monster.getStats().getCurrentHp() <= 0 && hero.getStats().getCurrentHp() > 0;
        return false;
    }

    /** Kiểm tra xem người chơi đã bị đánh bại hay chưa. */
    public boolean isDefeat() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return state == BattleState.DEFEAT && hero.getStats().getCurrentHp() <= 0;
        return false;
    }

    /** Trả về báo cáo kết quả của bước xử lý gần nhất. */
    public TurnReport getLastReport() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return lastReport;
        return null;
    }
}
