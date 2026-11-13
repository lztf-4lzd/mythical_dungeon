package source.application.flow;

import source.application.view.AnimationEvent;
import source.application.view.BattleView;
import source.application.combat.BattleSession;
import source.application.combat.TurnReport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Bộ điều phối luồng chiến đấu kết nối {@link BattleSession} với các màn hình giao diện
 * qua từng bước rời rạc kèm luồng sự kiện hoạt ảnh.
 * Người phụ trách: Thành viên 3 (Luồng Game & Sinh Ngục tối)
 */
public class BattleFlow {

    /** Kết quả có cấu trúc trả về sau mỗi bước hoặc hành động chiến đấu. */
    public static final class BattleStepResult {
        private TurnReport report;
        private List<AnimationEvent> animationEvents;
        private BattleView battleView;

        /** Khởi tạo kết quả bước chiến đấu gồm báo cáo lượt, danh sách sự kiện hoạt ảnh và mô hình hiển thị. */
        private BattleStepResult(
                TurnReport report,
                List<AnimationEvent> animationEvents,
                BattleView battleView) {
        // TODO: Hướng dẫn hiện thực:
        // 1. this.report = report;
        // 2. this.animationEvents = animationEvents == null
        // 3. ? List.of()
        // 4. : Collections.unmodifiableList(new ArrayList<>(animationEvents));
        // 5. this.battleView = battleView;
    }

        /** Trả về báo cáo lượt đấu chi tiết. */
        public TurnReport getReport() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return report;
        return null;
    }

        /** Trả về danh sách các sự kiện hoạt ảnh phát sinh trong bước này. */
        public List<AnimationEvent> getAnimationEvents() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return animationEvents;
        return null;
    }

        /** Trả về mô hình hiển thị trạng thái trận đánh sau bước này. */
        public BattleView getBattleView() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return battleView;
        return null;
    }
    }

    /** Phiên chiến đấu cơ sở. */
    private BattleSession session;
    /** Danh sách toàn bộ các dòng nhật ký chiến đấu đã tích lũy từ đầu trận. */
    private List<String> accumulatedLogs;

    /** Khởi tạo luồng chiến đấu bọc quanh một {@link BattleSession} có sẵn. */
    BattleFlow(BattleSession session) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (session == null)
        // 2. throw new IllegalArgumentException("BattleSession cannot be null.");
        // 3. this.session = session;
        // 4. this.accumulatedLogs = new ArrayList<>();
    }

    /**
     * Bắt đầu phiên chiến đấu, mở vòng 1 và trả về kết quả bước đầu tiên.
     */
    public BattleStepResult startBattle() {
        // TODO: Hướng dẫn hiện thực:
        // 1. TurnReport report = session.start();
        // 2. recordReportLogs(report);
        // 3. List<AnimationEvent> events = EventMapper.toAnimationEvents(report);
        // 4. return new BattleStepResult(report, events, getView());
        return null;
    }

    /**
     * Thực thi riêng hành động của người chơi ở chế độ rời rạc (không tự động chạy lượt phản công của quái vật).
     *
     * @param skillSlotIndex chỉ số ô kỹ năng được chọn (bắt đầu từ 0)
     * @return kết quả bước chiến đấu kèm danh sách sự kiện hoạt ảnh
     */
    public BattleStepResult executePlayerAction(int skillSlotIndex) {
        // TODO: Hướng dẫn hiện thực:
        // 1. TurnReport report = session.executePlayerActionOnly(skillSlotIndex);
        // 2. recordReportLogs(report);
        // 3. List<AnimationEvent> events = EventMapper.toAnimationEvents(report);
        // 4. return new BattleStepResult(report, events, getView());
        return null;
    }

    /**
     * Thực thi riêng hành động của quái vật ở chế độ rời rạc khi đang ở trạng thái {@code AWAITING_MONSTER_ACTION}.
     *
     * @return kết quả bước chiến đấu của quái vật kèm danh sách sự kiện hoạt ảnh
     */
    public BattleStepResult executeMonsterAction() {
        // TODO: Hướng dẫn hiện thực:
        // 1. TurnReport report = session.executeMonsterActionOnly();
        // 2. recordReportLogs(report);
        // 3. List<AnimationEvent> events = EventMapper.toAnimationEvents(report);
        // 4. return new BattleStepResult(report, events, getView());
        return null;
    }

    /**
     * Tạo ảnh chụp {@link BattleView} mới phản ánh trạng thái hiện tại và nhật ký tích lũy.
     */
    public BattleView getView() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return ViewMapper.toBattleView(session, accumulatedLogs);
        return null;
    }

    /** Kiểm tra xem phiên có đang chờ hành động của người chơi hay không. */
    public boolean isAwaitingPlayerAction() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return session.isAwaitingPlayerAction();
        return false;
    }

    /** Kiểm tra xem phiên có đang chờ hành động của quái vật hay không. */
    public boolean isAwaitingMonsterAction() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return session.isAwaitingMonsterAction();
        return false;
    }

    /** Kiểm tra xem trận đánh đã kết thúc hay chưa. */
    public boolean isGameOver() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return session.isGameOver();
        return false;
    }

    /** Kiểm tra xem người chơi đã chiến thắng hay chưa. */
    public boolean isVictory() {
        // TODO: Hướng dẫn hiện thực:
        // 1. return session.isVictory();
        return false;
    }

    /** Ghi nối các dòng thông điệp từ báo cáo lượt vào danh sách nhật ký tích lũy. */
    private void recordReportLogs(TurnReport report) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (report != null && report.getMessages() != null)
        // 2. accumulatedLogs.addAll(report.getMessages());
    }
}
