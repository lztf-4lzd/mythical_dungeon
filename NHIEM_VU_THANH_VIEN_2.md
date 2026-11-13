# NHIỆM VỤ CHI TIẾT: THÀNH VIÊN 2
## VAI TRÒ: COMBAT ENGINE & TRÍ TUỆ NHÂN TẠO (AI) QUÁI VẬT
### DỰ ÁN: MYTHICAL DUNGEON — 2D TURN-BASED RPG

> **Lời nhắn**: Bạn là "trái tim" của hệ thống chiến đấu. Trách nhiệm của bạn là toàn bộ cơ chế tính toán đòn đánh, trừ máu, trừ khiên, bùa chú hiệu ứng và đặc biệt là trí tuệ nhân tạo (AI) giúp các loại quái vật biết tấn công, phòng thủ, buff khiên hoặc xả chiêu thức Ultimate thông minh.

---

## 1. TỔNG QUAN PHÂN CÔNG
* **Khối lượng công việc**: ~2.650 dòng code.
* **Số lượng tệp**: 9 files Java.
* **Vị trí thư mục**: Duy nhất trong gói `source/application/combat/`.
* **Danh sách tệp phụ trách**:
  1. `TurnReport.java`
  2. `CombatFormatter.java`
  3. `RecoveryService.java`
  4. `EffectService.java`
  5. `ActionService.java`
  6. `RoleTactics.java`
  7. `MonsterTactics.java`
  8. `DecisionService.java`
  9. `BattleSession.java`

---

## 2. QUY TẮC BẮT BUỘC KHI LÀM VIỆC
1. **API Freeze**: Giữ nguyên tên các phương thức public đã định nghĩa sẵn trong skeleton. GUI của Thành viên 4 và 5 đang gọi trực tiếp vào các hàm này (`BattleSession.executePlayerActionOnly`, `BattleSession.executeMonsterActionOnly`, `BattleSession.getHero()`, v.v.).
2. **Không phụ thuộc GUI**: Tầng Combat của bạn là tầng xử lý logic thuần túy (Pure Logic), **không được import `java.awt.*` hoặc `javax.swing.*`**. Toàn bộ dữ liệu hiển thị sẽ được Thành viên 3 ánh xạ sau.
3. **Thư viện chuẩn**: Chỉ dùng các class của tầng Domain (`source.domain.*`) và Java tiêu chuẩn (`java.util.*`).

---

## 3. LỘ TRÌNH THỰC HIỆN TỪNG BƯỚC (ROADMAP)

```
[Bước 1: Nhật ký] ──> [Bước 2: Hồi phục & Hiệu ứng] ──> [Bước 3: Đòn đánh] ──> [Bước 4: AI Quái] ──> [Bước 5: BattleSession]
  TurnReport            RecoveryService                   ActionService          RoleTactics            Nhạc trưởng
  CombatFormatter       EffectService                                            DecisionService        điều phối
```

* **Bước 1**: Hoàn thiện `TurnReport.java` và `CombatFormatter.java` để lưu trữ và định dạng nhật ký trận đấu.
* **Bước 2**: Hoàn thiện `RecoveryService.java` (hồi STA đầu hiệp) và `EffectService.java` (rút máu độc, thiêu đốt, giải bùa).
* **Bước 3**: Hoàn thiện `ActionService.java` (quan trọng nhất - thực thi kỹ năng, tính dame, trừ khiên, trừ máu).
* **Bước 4**: Hoàn thiện `RoleTactics.java`, `MonsterTactics.java` và `DecisionService.java` (trí tuệ nhân tạo chọn kỹ năng cho quái vật).
* **Bước 5**: Ghép toàn bộ vào `BattleSession.java` (máy trạng thái điều phối lượt đánh, kiểm tra thắng/thua).

---

## 4. CHI TIẾT TỪNG CLASS VÀ HƯỚNG DẪN HIỆN THỰC

### 1. `TurnReport.java` & `CombatFormatter.java`
* **Mục đích**: Lưu lại kết quả sau mỗi hành động (ai đánh, chiêu gì, dame bao nhiêu, ai dính bùa, máu còn lại bao nhiêu).
* **`TurnReport.java`**:
  - Chứa danh sách các dòng thông báo (`List<String> logs`), thông tin đòn đánh (`ActionRecord`), trạng thái trận đấu sau lượt (`BattleState`).
  - Cung cấp hàm `addLog(String message)` và `getLogs()`.
* **`CombatFormatter.java`**:
  - Định dạng chuỗi văn bản thông báo:
    - `"Arthur sử dụng [Chém Lửa] gây 145 sát thương lên Quái Orc (Chí mạng!)"`
    - `"Quái Orc đã bị trúng bùa [Thiêu Đốt] trong 3 lượt!"`
    - `"Arthur đã né tránh đòn đánh của Skeleton!"`

---

### 2. `RecoveryService.java` (Hồi phục đầu hiệp)
* **Mục đích**: Hồi phục một lượng thể lực (STA) nhất định cho cả Anh hùng và Quái vật khi bắt đầu một vòng đấu mới.
* **Hằng số**: `GameConstants.TURN_STAMINA_RECOVERY` (= 15 STA).
* **Phương thức `public static void processRoundStartRecovery(Character hero, Character monster)`**:
  1. Lấy STA hiện tại của hero: `int currentSta = hero.getStats().getCurrentSta();`.
  2. Cộng thêm 15 STA: `hero.getStats().setCurrentSta(Math.min(hero.getStats().getMaxSta(), currentSta + 15));`.
  3. Làm tương tự cho quái vật `monster`.

---

### 3. `EffectService.java` (Quản lý Bùa chú & Trạng thái)
* **Phương thức `public static List<String> processTurnStartEffects(Character character)`**:
  - Chạy ngay trước khi một nhân vật bắt đầu hành động:
  1. Khởi tạo danh sách thông báo: `List<String> logs = new ArrayList<>();`.
  2. Duyệt qua danh sách `character.getActiveEffects()`:
     - Nếu là `POISON`: Trừ máu trực tiếp theo `potency`. Thêm log: `"[Tên] chịu X sát thương từ Độc!"`.
     - Nếu là `BURN`: Trừ máu theo `potency`. Thêm log: `"[Tên] bị Thiêu Đốt mất X máu!"`.
     - Giảm thời gian hiệu lực: `effect.decrementDuration();`.
  3. Xóa các hiệu ứng có `duration <= 0` khỏi danh sách.
  4. Trả về `logs`.
* **Phương thức `public static boolean isStunnedOrFrozen(Character character)`**:
  - Trả về `true` nếu nhân vật đang dính hiệu ứng `STUN` hoặc `FREEZE` (mất lượt không thể hành động).

---

### 4. `ActionService.java` (Thực thi đòn đánh — Trọng tâm)
* **Phương thức `public static boolean canExecuteSkill(Character attacker, Skill skill)`**:
  - Kiểm tra xem thể lực hiện tại `attacker.getStats().getCurrentSta() >= skill.getStaCost()`.
  - Nếu chiêu thức có đánh dấu dùng 1 lần (`skill.isOncePerBattle()`), kiểm tra nhân vật chưa dùng chiêu đó trong trận này.
* **Phương thức `public static ActionRecord executeSkill(Character attacker, Character target, Skill skill)`**:
  - **Quy trình chuẩn**:
    1. **Trừ thể lực**: `attacker.getStats().consumeStamina(skill.getStaCost());`.
    2. **Xử lý hồi phục nội tại**:
       - Nếu kỹ năng có hồi máu (`skill.getHealAmount() > 0`): Hồi máu cho `attacker`.
       - Nếu có buff khiên: Cộng thêm vào `attacker.getStats().addShield(...)`.
    3. **Kiểm tra Né đòn (Dodge)**:
       - Tỷ lệ né = `target.getStats().getDodgeRate() - attacker.getStats().getAccuracy()`.
       - Dùng `GameRandom.getDefault().nextPercent()`: Nếu né thành công, ghi nhận đòn đánh bị Hụt (`miss = true`), kết thúc tính dame.
    4. **Tính Sát thương**:
       - Gọi `DamageResult result = DamageCalculator.calculateDamage(attacker, target, skill);`.
       - Lấy lượng dame thực tế: `int rawDamage = result.getDamage();`.
    5. **Trừ vào Khiên trước, Máu sau**:
       - Lấy khiên của target: `int shield = target.getStats().getShield();`.
       - Nếu `shield > 0`:
         - Nếu `shield >= rawDamage`: `target.getStats().setShield(shield - rawDamage); rawDamage = 0;`.
         - Nếu `shield < rawDamage`: `rawDamage -= shield; target.getStats().setShield(0);`.
       - Trừ phần sát thương còn lại vào máu: `target.getStats().takeDamage(rawDamage);`.
    6. **Hút máu (Lifesteal)**: Nếu kỹ năng có thuộc tính hút máu, hồi lại cho attacker bằng `%` sát thương thực tế vừa gây ra.
    7. **Áp dụng hiệu ứng xấu lên mục tiêu**:
       - Nếu kỹ năng có kèm hiệu ứng (như Độc, Bỏng, Giảm giáp): Gắn `StatusEffect` mới vào `target.getActiveEffects()`.
    8. **Đóng gói trả về `ActionRecord`**: Chứa toàn bộ thông tin sát thương, bạo kích, né tránh, khiên vỡ, v.v.

---

### 5. `RoleTactics.java`, `MonsterTactics.java` & `DecisionService.java` (AI Quái)
* **Quy tắc AI theo vai trò quái vật (`MonsterRole`)**:
  - **DPS**: Ưu tiên chọn kỹ năng gây sát thương cao nhất hoặc có tỷ lệ bạo kích cao nhất mà đủ thể lực sử dụng.
  - **TANK**: Nếu máu của quái vật dưới 50%, ưu tiên chọn kỹ năng tạo Giáp/Khiên hoặc tăng kháng. Nếu máu an toàn, tấn công đòn gây Choáng (Stun).
  - **SUPPORT**: Ưu tiên hồi máu nếu máu dưới 60%, hoặc buff sức mạnh cho bản thân.
  - **BOSS**: Có cơ chế nộ (Enrage): Khi máu dưới 35%, bắt buộc xả chiêu Ultimate mạnh nhất nếu đủ thể lực.
* **Dự phòng (Fallback)**: Nếu không đủ thể lực dùng bất kỳ chiêu đặc biệt nào, AI luôn tự động chọn chiêu Đánh thường (Basic Attack - tốn 0 STA). Quái vật không bao giờ bị đứng yên do hết thể lực!

---

### 6. `BattleSession.java` (Bộ điều phối tổng thể trận đấu)
* **Trạng thái trận đấu (`BattleState`)**:
  - `READY` -> `AWAITING_PLAYER_ACTION` -> `AWAITING_MONSTER_ACTION` -> `VICTORY` / `DEFEAT`.
* **Phương thức `public TurnReport start()`**:
  - Bắt đầu trận chiến, so sánh Tốc độ (`hero.getStats().getSpeed()` vs `monster.getStats().getSpeed()`).
  - Bên nào tốc độ cao hơn sẽ được đi trước (đặt state tương ứng).
* **Phương thức `public TurnReport executePlayerActionOnly(int skillSlotIndex)`**:
  - Lấy kỹ năng tại ô slot tương ứng của Hero.
  - Gọi `ActionService.executeSkill(hero, monster, skill)`.
  - Kiểm tra xem quái vật đã chết chưa (`monster.isDead()`):
    - Nếu quái chết: Đặt state = `VICTORY`.
    - Nếu quái còn sống: Đặt state = `AWAITING_MONSTER_ACTION`.
* **Phương thức `public TurnReport executeMonsterActionOnly()`**:
  - Gọi `DecisionService.chooseSkill(monster, hero)` để AI chọn chiêu.
  - Gọi `ActionService.executeSkill(monster, hero, chosenSkill)`.
  - Kiểm tra xem hero đã chết chưa (`hero.isDead()`):
    - Nếu hero chết: Đặt state = `DEFEAT`.
    - Nếu hero còn sống: Tăng `roundNumber++`, gọi hồi thể lực đầu hiệp, đặt state = `AWAITING_PLAYER_ACTION`.

---

## 5. CODE MẪU TỰ KIỂM THỬ ĐỘC LẬP CHO THÀNH VIÊN 2

Tạo file `TestMember2.java` tại thư mục gốc để tự chạy một trận đấu Console hoàn chỉnh không cần giao diện:

```java
import source.domain.entity.hero.HeroCatalogue;
import source.domain.entity.hero.HeroId;
import source.domain.entity.hero.Hero;
import source.domain.entity.monster.MonsterCatalogue;
import source.domain.entity.monster.MonsterId;
import source.domain.entity.monster.Monster;
import source.application.combat.BattleSession;
import source.application.combat.TurnReport;

public class TestMember2 {
    public static void main(String[] args) {
        System.out.println("=== KIỂM THỬ HỆ THỐNG COMBAT & AI QUÁI VẬT ===");

        // 1. Tạo Đấu sĩ Arthur và Quái Orc
        Hero hero = HeroCatalogue.createHero(HeroId.WARRIOR, "Arthur");
        Monster monster = MonsterCatalogue.create(MonsterId.ORC, false);

        System.out.println("Anh hùng: " + hero.getName() + " (HP: " + hero.getStats().getCurrentHp() + ")");
        System.out.println("Quái vật: " + monster.getName() + " (HP: " + monster.getStats().getCurrentHp() + ")");

        // 2. Khởi tạo trận đấu
        BattleSession session = new BattleSession(hero, monster);
        TurnReport startReport = session.start();
        System.out.println("\n[Trận đấu bắt đầu] Ai đi trước: " + session.getState());

        // 3. Giả lập 3 vòng đấu
        for (int round = 1; round <= 3; round++) {
            System.out.println("\n--- VÒNG ĐẤU " + round + " ---");
            
            // Lượt người chơi đánh chiêu ô 0
            TurnReport playerReport = session.executePlayerActionOnly(0);
            for (String log : playerReport.getLogs()) {
                System.out.println(">> " + log);
            }
            System.out.println("Máu Quái còn: " + monster.getStats().getCurrentHp() + "/" + monster.getStats().getMaxHp());

            if (session.isBattleOver()) {
                System.out.println("Trận đấu kết thúc! Kết quả: " + session.getState());
                break;
            }

            // Lượt Quái vật phản công bằng AI
            TurnReport monsterReport = session.executeMonsterActionOnly();
            for (String log : monsterReport.getLogs()) {
                System.out.println("<< " + log);
            }
            System.out.println("Máu Hero còn: " + hero.getStats().getCurrentHp() + "/" + hero.getStats().getMaxHp());

            if (session.isBattleOver()) {
                System.out.println("Trận đấu kết thúc! Kết quả: " + session.getState());
                break;
            }
        }

        System.out.println("\n=== KIỂM THỬ COMBAT ENGINE THÀNH CÔNG! ===");
    }
}
```

---

## 6. LỆNH BIÊN DỊCH VÀ CHẠY THỬ
```bash
javac -d bin -cp . source/application/combat/*.java
```
Hãy chắc chắn rằng không có cảnh báo nào xuất hiện khi biên dịch!
