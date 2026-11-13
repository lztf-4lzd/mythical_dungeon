# NHIỆM VỤ CHI TIẾT: THÀNH VIÊN 3
## VAI TRÒ: LUỒNG GAME, SINH HẦM NGỤC & DTO MAPPING
### DỰ ÁN: MYTHICAL DUNGEON — 2D TURN-BASED RPG

> **Lời nhắn**: Bạn là "cây cầu nối" quan trọng nhất của toàn bộ dự án. Nhiệm vụ của bạn là kết nối logic chiến đấu (của Thành viên 2) và giao diện đồ họa (của Thành viên 4 và 5) thông qua các lớp trung chuyển DTO (của Thành viên 1). Bạn phụ trách việc sinh quái vật theo tầng ngục, tính thưởng qua ải, và chuyển đổi dữ liệu thô thành các sự kiện hoạt ảnh mượt mà.

---

## 1. TỔNG QUAN PHÂN CÔNG
* **Khối lượng công việc**: ~2.500 dòng code.
* **Số lượng tệp**: 8 files Java.
* **Các thư mục phụ trách**:
  1. `source/application/dungeon/` (2 files Dungeon)
  2. `source/application/flow/` (6 files Flow & Mappers)
* **Danh sách tệp phụ trách**:
  1. `FloorCatalog.java`
  2. `DungeonRun.java`
  3. `MotionClassifier.java`
  4. `EventMapper.java`
  5. `ViewMapper.java`
  6. `GameFlow.java`
  7. `DungeonFlow.java`
  8. `BattleFlow.java`

---

## 2. QUY TẮC BẮT BUỘC KHI LÀM VIỆC
1. **API Freeze**: Tuân thủ tuyệt đối chữ ký hàm đã có sẵn trong skeleton. GUI (Thành viên 5) sẽ gọi trực tiếp các phương thức của `BattleFlow` (`executeHeroAction`, `executeMonsterAction`, `getCurrentBattleView`).
2. **Nguyên tắc đóng gói**: Không để lộ các thực thể nhạy cảm của Domain ra ngoài giao diện. Luôn dùng `ViewMapper` để bọc thành DTOs (`CombatantView`, `BattleView`, `DungeonView`) trước khi trả về cho UI.
3. **Phối hợp nhịp nhàng**:
   - Sử dụng các lớp DTO do **Thành viên 1** định nghĩa.
   - Nhận báo cáo `TurnReport` từ **Thành viên 2** để chuyển thành `AnimationEvent` cho **Thành viên 5**.

---

## 3. LỘ TRÌNH THỰC HIỆN TỪNG BƯỚC (ROADMAP)

```
[Bước 1: Sinh Hầm ngục] ──> [Bước 2: Tiến trình ải] ──> [Bước 3: Mappers & View] ──> [Bước 4: Flow State Machine]
     FloorCatalog                DungeonRun                 ViewMapper                 BattleFlow
                                                            EventMapper                DungeonFlow / GameFlow
```

* **Bước 1**: Viết cấu hình quái và lượng EXP cho các tầng hầm ngục trong `FloorCatalog.java`.
* **Bước 2**: Viết tiến trình thám hiểm và phần thưởng sau trận đánh trong `DungeonRun.java`.
* **Bước 3**: Viết bộ phân loại chuyển động `MotionClassifier.java`, bộ ánh xạ DTO `ViewMapper.java` và bộ tạo sự kiện hoạt ảnh `EventMapper.java`.
* **Bước 4**: Hoàn thiện các bộ máy điều phối luồng: `BattleFlow.java`, `DungeonFlow.java`, `GameFlow.java`.

---

## 4. CHI TIẾT TỪNG CLASS VÀ HƯỚNG DẪN HIỆN THỰC

### 1. `FloorCatalog.java` (Cấu hình Hầm ngục & Quái vật)
* **Mục đích**: Cung cấp danh sách 5 quái vật theo độ khó tăng dần cho mỗi hầm ngục.
* **Enum hầm ngục**: `DungeonType { UNDEAD_CRYPT, ORC_STRONGHOLD, INFERNAL_ABYSS }`.
* **Phương thức `public static int getEncounterExp(int encounterIndex)`**:
  - Trả về lượng EXP nhận được sau khi vượt qua từng ải (chỉ số 0 đến 4):
    - Ải 1: `100 EXP`
    - Ải 2: `150 EXP`
    - Ải 3: `200 EXP`
    - Ải 4: `300 EXP`
    - Ải 5 (Trùm Boss): `500 EXP`
* **Phương thức `public static List<Monster> createMonsters(DungeonType type)`**:
  - Trả về danh sách đúng 5 con quái vật mới được tạo từ `MonsterCatalogue`:
    - Với `UNDEAD_CRYPT`:
      1. Tầng 1: `MonsterCatalogue.create(MonsterId.RESTLESS_SKELETON, false)`
      2. Tầng 2: `MonsterCatalogue.create(MonsterId.SKELETON, false)`
      3. Tầng 3: `MonsterCatalogue.create(MonsterId.ARMORED_SKELETON, false)`
      4. Tầng 4: `MonsterCatalogue.create(MonsterId.GREATSWORD_SKELETON, false)`
      5. Tầng 5 (BOSS): `MonsterCatalogue.create(MonsterId.DREAD_CAVALIER_LORD, true)`
    - Tương tự cho các hầm ngục khác.

---

### 2. `DungeonRun.java` (Tiến trình thám hiểm 5 ải)
* **Thuộc tính**:
  - `private final Character hero;`
  - `private final DungeonType dungeonType;`
  - `private final List<Monster> encounters;`
  - `private int currentEncounterIndex = 0;` // 0 đến 4
  - `private int totalExpEarned = 0;`
  - `private boolean isVictorious = false;`
* **Phương thức `public Monster getCurrentMonster()`**:
  - Trả về quái vật ở ải hiện tại: `encounters.get(currentEncounterIndex);`.
* **Phương thức `public void concludeBattle(boolean heroWon)`**:
  - Logic kết thúc 1 trận chiến:
    1. Nếu `heroWon == false`: Người chơi thua cuộc, giữ nguyên tiến trình, không thưởng.
    2. Nếu `heroWon == true`:
       - Lấy EXP thưởng của ải: `int exp = FloorCatalog.getEncounterExp(currentEncounterIndex);`.
       - Cộng dồn: `totalExpEarned += exp;`.
       - Thăng cấp cho nhân vật: `LevelService.processExpGain(hero, exp);`.
       - Hồi phục sau trận: Hồi 20% Max HP (`hero.getStats().getMaxHp() * 0.2`) và 40% Max STA (`hero.getStats().getMaxSta() * 0.4`).
       - Tăng chỉ số ải: `currentEncounterIndex++;`.
       - Nếu `currentEncounterIndex >= encounters.size()`: Đánh dấu đã phá đảo hầm ngục (`isVictorious = true;`).

---

### 3. `MotionClassifier.java` (Phân loại chuyển động)
* **Phương thức `public static MotionType classify(Skill skill)`**:
  - Phân tích kỹ năng để xác định loại hoạt ảnh hiển thị:
    - Nếu chiêu thức hồi máu/hỗ trợ -> trả về `MotionType.BUFF_HEAL`.
    - Nếu là phép thuật nguyên tố (Lửa, Băng, Sấm sét) -> trả về `MotionType.PROJECTILE_MAGIC`.
    - Nếu là bắn cung/ném phi tiêu -> trả về `MotionType.PROJECTILE_ARROW`.
    - Mặc định các đòn đánh cận chiến -> trả về `MotionType.MELEE_LUNGE`.

---

### 4. `ViewMapper.java` (Bộ biến đổi dữ liệu sang DTOs)
* **Phương thức `public static CombatantView toCombatantView(Character character)`**:
  - Đọc dữ liệu từ `character`:
    - `name = character.getName();`
    - `element = character.getElement();`
    - `currentHp = character.getStats().getCurrentHp();`
    - `maxHp = character.getStats().getMaxHp();`
    - `currentSta = character.getStats().getCurrentSta();`
    - `shield = character.getStats().getShield();`
    - Biến đổi danh sách bùa chú: Lặp qua `character.getActiveEffects()` tạo thành `List<EffectView>`.
    - Kiểm tra `isBoss`: `(character instanceof Monster) && ((Monster) character).isBoss()`.
  - Tạo mới và trả về `new CombatantView(...)`.
* **Phương thức `public static BattleView toBattleView(BattleSession session)`**:
  - Lấy `CombatantView` của hero và quái vật.
  - Lấy số vòng `roundNumber`, người đi trước, và trạng thái thắng/thua để đóng gói thành `BattleView`.

---

### 5. `EventMapper.java` (Chuyển đổi Nhật ký sang Hoạt ảnh)
* **Phương thức `public static List<AnimationEvent> toAnimationEvents(TurnReport report)`**:
  - Nhận báo cáo lượt đánh từ Thành viên 2.
  - Phân tích các thông tin: ai ra đòn, sát thương gây ra bao nhiêu, có chí mạng (`isCrit`) hay hụt (`isMiss`), hiệu ứng trúng phải.
  - Tạo ra các đối tượng `AnimationEvent` tương ứng để chuyển sang cho Thành viên 5 vẽ đạn bay, chữ nổi số dame, rung màn hình.

---

### 6. `BattleFlow.java` & `DungeonFlow.java` (Bộ máy điều phối trận đánh)
* **`BattleFlow.java`**:
  - Nhận tham số `BattleSession session`.
  - Cung cấp phương thức `executeHeroAction(int slotIndex)`:
    1. Gọi `TurnReport report = session.executePlayerActionOnly(slotIndex);`.
    2. Chuyển thành sự kiện diễn hoạt: `List<AnimationEvent> events = EventMapper.toAnimationEvents(report);`.
    3. Cập nhật góc nhìn mới: `BattleView view = ViewMapper.toBattleView(session);`.
    4. Đóng gói thành `BattleStepResult` trả về cho màn hình GUI cập nhật.
  - Tương tự với hàm `executeMonsterAction()`.

---

## 5. CODE MẪU TỰ KIỂM THỬ ĐỘC LẬP CHO THÀNH VIÊN 3

Tạo file `TestMember3.java` tại thư mục gốc để tự kiểm tra luồng hầm ngục và sinh quái vật:

```java
import source.application.dungeon.FloorCatalog;
import source.application.dungeon.DungeonRun;
import source.application.flow.ViewMapper;
import source.application.view.CombatantView;
import source.domain.entity.hero.HeroCatalogue;
import source.domain.entity.hero.HeroId;
import source.domain.entity.hero.Hero;
import source.domain.entity.monster.Monster;
import java.util.List;

public class TestMember3 {
    public static void main(String[] args) {
        System.out.println("=== KIỂM THỬ HỆ THỐNG HẦM NGỤC & DTO MAPPING ===");

        // 1. Kiểm tra sinh 5 quái trong Hầm ngục Undead Crypt
        System.out.println("\n--- Kiểm tra FloorCatalog ---");
        List<Monster> monsters = FloorCatalog.createMonsters(FloorCatalog.DungeonType.UNDEAD_CRYPT);
        System.out.println("Số lượng quái sinh ra: " + monsters.size() + " (Kỳ vọng: 5)");
        for (int i = 0; i < monsters.size(); i++) {
            Monster m = monsters.get(i);
            System.out.println("Ải " + (i + 1) + ": " + m.getName() + " | Boss? " + m.isBoss() + " | HP: " + m.getStats().getMaxHp());
        }

        // 2. Kiểm tra DungeonRun vượt ải
        System.out.println("\n--- Kiểm tra DungeonRun ---");
        Hero hero = HeroCatalogue.createHero(HeroId.WARRIOR, "Arthur");
        DungeonRun run = new DungeonRun(hero, FloorCatalog.DungeonType.UNDEAD_CRYPT);

        System.out.println("Bắt đầu tại Ải: " + (run.getCurrentEncounterIndex() + 1));
        System.out.println("Quái hiện tại: " + run.getCurrentMonster().getName());

        // Giả lập thắng ải 1
        run.concludeBattle(true);
        System.out.println("[Thắng ải 1] Đã chuyển sang Ải: " + (run.getCurrentEncounterIndex() + 1));
        System.out.println("Tổng EXP kiếm được: " + run.getTotalExpEarned());

        // 3. Kiểm tra ViewMapper sinh DTO
        System.out.println("\n--- Kiểm tra ViewMapper ---");
        CombatantView heroView = ViewMapper.toCombatantView(hero);
        System.out.println("[OK] DTO HeroView: " + heroView.getName() + " | HP: " + heroView.getCurrentHp() + "/" + heroView.getMaxHp());

        System.out.println("\n=== KIỂM THỬ THÀNH VIÊN 3 THÀNH CÔNG! ===");
    }
}
```

---

## 6. LỆNH BIÊN DỊCH VÀ CHẠY THỬ
```bash
javac -d bin -cp . source/application/dungeon/*.java source/application/flow/*.java
```
Kiểm tra đảm bảo 0 lỗi biên dịch!
