package source.application.flow;

import source.application.view.AnimationEvent;
import source.application.view.MotionType;
import source.domain.calculator.DamageResult;
import source.application.combat.ActionService;
import source.application.combat.EffectService;
import source.application.combat.TurnReport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Chuyển đổi kết quả có cấu trúc trong {@link TurnReport} và {@link TurnReport.ActionRecord}
 * thành chuỗi sự kiện hoạt ảnh {@link AnimationEvent} tuần tự cho hệ thống hoạt ảnh giao diện
 * (chữ nổi, số sát thương, rung màn hình, biểu tượng hiệu ứng).
 * Người phụ trách: Thành viên 3 (Luồng Game & Sinh Ngục tối)
 */
final class EventMapper {

    private EventMapper() {}

    /**
     * Chuyển đổi toàn bộ bản ghi hành động trong một {@link TurnReport} thành danh sách sự kiện hoạt ảnh.
     *
     * @param report báo cáo lượt đấu chứa các bản ghi hành động đã giải quyết
     * @return danh sách bất biến các sự kiện hoạt ảnh theo thứ tự thời gian
     */
    static List<AnimationEvent> toAnimationEvents(TurnReport report) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (report == null || report.getActionRecords() == null)
        // 2. return List.of();
        // 3. List<AnimationEvent> events = new ArrayList<>();
        // 4. for (TurnReport.ActionRecord record : report.getActionRecords())
        // 5. events.addAll(toAnimationEvents(record));
        // 6. Kiểm tra nếu báo cáo kết thúc bằng chiến thắng hoặc thất bại.
        return null;
    }

    /**
     * Chuyển đổi một bản ghi hành động đơn lẻ (nhịp DoT đầu lượt + kết quả kỹ năng + hiệu ứng áp dụng)
     * thành danh sách sự kiện hoạt ảnh theo đúng trình tự diễn ra.
     *
     * @param record bản ghi hành động chiến đấu đơn lẻ
     * @return danh sách bất biến các sự kiện hoạt ảnh
     */
    private static List<AnimationEvent> toAnimationEvents(TurnReport.ActionRecord record) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (record == null)
        // 2. return List.of();
        // 3. List<AnimationEvent> events = new ArrayList<>();
        // 4. String attacker = record.getAttackerName();
        // 5. String target = record.getTargetName();
        // 6. MotionType motionType = MotionClassifier.classify(
        return null;
    }
}
