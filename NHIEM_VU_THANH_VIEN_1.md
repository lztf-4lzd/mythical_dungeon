# NHIỆM VỤ CHI TIẾT: THÀNH VIÊN 1
## VAI TRÒ: DATA ARCHITECT, PROGRESSION & AUDIO SFX LEAD
### DỰ ÁN: MYTHICAL DUNGEON — 2D TURN-BASED RPG

> **Lời nhắn**: Bạn đảm nhiệm vai trò kiến trúc dữ liệu trung chuyển (DTOs), khởi tạo nhân vật ban đầu, hệ thống thăng cấp và toàn bộ hệ thống âm thanh SFX của game. Đây là phần việc nhẹ nhàng nhất (~1.350 dòng code, 17 files), độc lập và làm nền tảng truyền dữ liệu cho các thành viên khác.

---

## 1. TỔNG QUAN PHÂN CÔNG
* **Khối lượng công việc**: ~1.350 dòng code.
* **Số lượng tệp**: 17 files Java.
* **Các thư mục phụ trách**:
  1. `source/application/view/` (7 files DTO)
  2. `source/application/setup/` (2 files Setup)
  3. `source/application/progression/` (1 file Level)
  4. `source/presentation/gui/audio/` (6 files Audio SFX)
  5. `source/app/` (1 file Main entry point)

---

## 2. QUY TẮC BẮT BUỘC KHI LÀM VIỆC
1. **API Freeze**: Tất cả tên class, tên hàm, kiểu tham số và kiểu trả về đã được khai báo sẵn trong khung code (skeleton). Tuyệt đối **không đổi tên hoặc xóa các phương thức public/protected** có sẵn.
2. **Không sửa code của người khác**: Chỉ viết code bên trong 17 file thuộc danh sách phân công của mình.
3. **Thư viện chuẩn**: Chỉ sử dụng thư viện có sẵn của Java SE (`java.util.*`, `java.io.*`, `javax.sound.sampled.*`). Không cài thêm thư viện bên ngoài.
4. **Biên dịch xanh**: Khi code xong hàm nào, chạy kiểm tra biên dịch không được có bất kỳ lỗi hoặc cảnh báo nào (`javac -Xlint:all -Werror`).

---

## 3. LỘ TRÌNH THỰC HIỆN TỪNG BƯỚC (ROADMAP)

```
[Chặng 1: DTOs] ───> [Chặng 2: Setup] ───> [Chặng 3: Level] ───> [Chặng 4: Audio] ───> [Chặng 5: Main & Test]
  7 files view         2 files khởi tạo     1 file thăng cấp      6 files âm thanh      Kết nối toàn bộ
```

* **Chặng 1**: Hoàn thiện toàn bộ các DTOs trong `source/application/view/`. Đây là các lớp lưu trữ dữ liệu hiển thị (data-holder) chỉ có thuộc tính và getter/setter, rất dễ viết.
* **Chặng 2**: Hoàn thiện `HeroFactory` và `LoadoutService` trong `source/application/setup/`.
* **Chặng 3**: Hoàn thiện thuật toán tính EXP và thăng cấp nhân vật trong `LevelService.java`.
* **Chặng 4**: Hoàn thiện hệ thống âm thanh hiệu ứng SFX trong `source/presentation/gui/audio/` (dùng clip wav và bộ sóng tổng hợp Synth, không cần BGM).
* **Chặng 5**: Viết điểm kích hoạt game trong `Main.java` và chạy thử nghiệm.

---

## 4. CHI TIẾT TỪNG CLASS VÀ HƯỚNG DẪN HIỆN THỰC

### CHẶNG 1: GÓI DTOs HIỂN THỊ (`source/application/view/`)

#### 1. `CombatantView.java`
* **Mục đích**: Gói dữ liệu hiển thị của 1 chiến binh (Anh hùng hoặc Quái vật) để gửi sang GUI mà không để lộ thực thể Domain gốc.
* **Các thuộc tính cần có**:
  - `private final String name;`
  - `private final String className;` // Ví dụ: "Warrior", "Orc", "Skeleton"
  - `private final int level;`
  - `private final Elemental element;`
  - `private final int currentHp;`
  - `private final int maxHp;`
  - `private final int currentSta;`
  - `private final int maxSta;`
  - `private final int shield;`
  - `private final List<EffectView> activeEffects;`
  - `private final boolean isBoss;`
* **Hướng dẫn code**:
  - Constructor gán toàn bộ các trường trên. Lưu ý: `this.activeEffects = activeEffects == null ? List.of() : List.copyOf(activeEffects);` để đảm bảo tính bất biến.
  - Viết đầy đủ các hàm getter tương ứng.

#### 2. `BattleView.java`
* **Mục đích**: Chứa toàn cảnh trạng thái của trận chiến tại một thời điểm.
* **Các thuộc tính cần có**:
  - `CombatantView hero;`
  - `CombatantView monster;`
  - `int roundNumber;`
  - `String currentTurn;` // "HERO" hoặc "MONSTER"
  - `boolean isGameOver;`
  - `boolean isVictory;`
* **Hướng dẫn code**:
  - Gán qua constructor và trả về qua các hàm getter `getHero()`, `getMonster()`, `getRoundNumber()`, `isVictory()`, v.v.

#### 3. `DungeonView.java`
* **Mục đích**: Chứa thông tin hiển thị của chuyến thám hiểm hầm ngục.
* **Các thuộc tính**: `dungeonName`, `currentFloor`, `totalFloors`, `battlesWon`, `isCampfireAvailable`.

#### 4. `SkillView.java`
* **Mục đích**: Chứa thông tin 1 ô kỹ năng trên thanh thao tác của người chơi.
* **Các thuộc tính**: `slotIndex`, `skillName`, `description`, `staCost`, `element`, `boolean isAvailable`, `String unavailableReason`.
* **Hàm tiện ích**: `public boolean canCast() { return isAvailable; }`.

#### 5. `EffectView.java`
* **Mục đích**: Chứa thông tin một huy hiệu hiệu ứng bùa chú (Buff/Debuff).
* **Các thuộc tính**: `EffectType type`, `String displayName`, `int remainingDuration`, `int potency`, `boolean isBuff`.

#### 6. `AnimationEvent.java` & `MotionType.java`
* **Mục đích**: Chuyển dữ liệu hoạt ảnh từ Engine sang cho Thành viên 5 diễn hoạt.
* **MotionType (Enum)**: `MELEE_LUNGE`, `PROJECTILE_ARROW`, `PROJECTILE_MAGIC`, `IMPACT_HIT`, `BUFF_HEAL`, `DEATH`.
* **AnimationEvent**: Lưu `motionType`, `attackerName`, `targetName`, `damageDealt`, `isCrit`, `isMiss`, `List<String> effectNamesApplied`.

---

### CHẶNG 2: GÓI THIẾT LẬP BAN ĐẦU (`source/application/setup/`)

#### 7. `HeroFactory.java`
* **Mục đích**: Khởi tạo nhân vật anh hùng theo lựa chọn của người chơi ở màn hình bắt đầu.
* **Enum bên trong**: `HeroClass { WARRIOR, MAGE, PALADIN, ARCHER }`.
* **Phương thức `public static Hero createHero(HeroClass heroClass, String name)`**:
  1. Kiểm tra tham số: Nếu `heroClass == null` thì ném `IllegalArgumentException`. Nếu `name == null || name.isBlank()`, đặt mặc định tên là `heroClass.name()`.
  2. Chuyển đổi sang `HeroId` trong domain: `HeroId heroId = HeroId.valueOf(heroClass.name());`.
  3. Gọi factory của Domain: `Hero hero = HeroCatalogue.createHero(heroId, name.trim());`.
  4. Trả về đối tượng `hero`.

#### 8. `LoadoutService.java`
* **Mục đích**: Kiểm tra và trang bị đúng 4 kỹ năng trước khi vào hầm ngục.
* **Phương thức `public static LoadoutValidationResult validateAndSetLoadout(Character hero, List<Skill> selectedSkills)`**:
  1. Kiểm tra `hero != null` và `selectedSkills != null`.
  2. Số lượng kỹ năng bắt buộc phải bằng 4: `if (selectedSkills.size() != 4) return new LoadoutValidationResult(false, "Phải chọn đúng 4 kỹ năng!");`.
  3. Kiểm tra trùng lặp: Duyệt kiểm tra các kỹ năng không được trùng tên nhau.
  4. Nếu hợp lệ: Gọi `hero.setEquippedSkills(selectedSkills);` và trả về kết quả thành công.

---

### CHẶNG 3: GÓI TIẾN TRÌNH & CẤP ĐỘ (`source/application/progression/`)

#### 9. `LevelService.java`
* **Mục đích**: Quản lý EXP, tính toán thăng cấp và tăng chỉ số nhân vật sau mỗi trận thắng.
* **Hằng số**: `public static final int MAX_LEVEL = 20;`
* **Phương thức `public static int getExpRequired(int level)`**:
  - Trả về `level * 100` (Cấp 1 cần 100 EXP để lên cấp 2, Cấp 2 cần 200 EXP lên cấp 3...).
* **Phương thức `public static boolean processExpGain(Character hero, int expGained)`**:
  - Logic xử lý:
    1. Kiểm tra nếu `hero.getLevel() >= MAX_LEVEL` thì không nhận thêm EXP, trả về `false`.
    2. Lấy EXP hiện tại: `int currentExp = hero.getStats().getCurrentExp() + expGained;`.
    3. Biến cờ `boolean leveledUp = false;`.
    4. Vòng lặp:
       ```java
       while (hero.getLevel() < MAX_LEVEL) {
           int required = getExpRequired(hero.getLevel());
           if (currentExp >= required) {
               currentExp -= required;
               levelUp(hero);
               leveledUp = true;
           } else {
               break;
           }
       }
       hero.getStats().setCurrentExp(currentExp);
       return leveledUp;
       ```
* **Phương thức `public static void levelUp(Character hero)`**:
  - Logic tăng chỉ số:
    1. Tăng cấp: `hero.setLevel(hero.getLevel() + 1);`.
    2. Lấy tốc độ tăng trưởng: `Attributes growth = hero.getGrowthRate();`.
    3. Cộng vào thuộc tính nhân vật: `hero.getAttributes().add(growth);`.
    4. Gọi `DamageCalculator.recalculateStats(hero);` để tính toán lại các chỉ số Max HP, Max STA, Giáp, Kháng...
    5. Hồi phục toàn bộ máu và thể lực khi lên cấp: `hero.getStats().restoreFullHealthAndStamina();`.

---

### CHẶNG 4: GÓI ÂM THANH SFX (`source/presentation/gui/audio/`)

*Dự án đã lược bỏ hoàn toàn BGM để nhẹ máy, tập trung 100% vào các âm thanh hiệu ứng (SFX).*

#### 10. `SoundType.java`
* **Mục đích**: Enum khai báo danh mục các âm thanh SFX:
  ```java
  BUTTON_CLICK("assets/audio/sfx/sfx_button_click.wav"),
  ATTACK("assets/audio/sfx/sfx_attack.wav"),
  CRITICAL_HIT("assets/audio/sfx/sfx_critical_hit.wav"),
  CASTING_HIT("assets/audio/sfx/sfx_casting_hit.wav"),
  SHIELD_ABSORB("assets/audio/sfx/sfx_shield_absorb.wav"),
  HEAL("assets/audio/sfx/sfx_heal.wav"),
  BUFF("assets/audio/sfx/sfx_buff.wav"),
  DEBUFF("assets/audio/sfx/sfx_debuff.wav"),
  DODGE("assets/audio/sfx/sfx_dodge.wav"),
  VICTORY("assets/audio/sfx/sfx_victory.wav"),
  DEFEAT("assets/audio/sfx/sfx_defeat.wav");
  ```

#### 11. `AudioSettings.java`
* Quản lý trạng thái: `sfxVolume` (0 đến 100), `isSfxMuted` (true/false).
* Cung cấp các hàm `getEffectiveSfxVolume()`, `setSfxVolume(int vol)`, `toggleSfxMute()`.

#### 12. `AudioBackend.java` & `SynthBackend.java`
* Giao diện `AudioBackend` định nghĩa hàm `playSfx(String path, float volumeLinear)`.
* `SynthBackend.java`:
  - Dùng `AudioSystem.getAudioInputStream(...)` để load file WAV.
  - Sử dụng `Clip` của Java Sound.
  - Chuyển `volumeLinear` (0.0f đến 1.0f) sang decibel: `float dB = (float) (Math.log10(Math.max(volumeLinear, 0.0001f)) * 20.0f);`.
  - Điều khiển qua `FloatControl.Type.MASTER_GAIN`.
  - Chạy `clip.start()` trên một Thread riêng biệt (`ExecutorService`) để không chặn luồng vẽ giao diện Swing.

#### 13. `AudioService.java`
* **Mục đích**: Singleton duy nhất điều phối âm thanh toàn game.
* Cung cấp các hàm tiện lợi cho giao diện gọi:
  - `playButtonClick()` -> gọi `playSfx(SoundType.BUTTON_CLICK)`.
  - `playAttack()` -> `playSfx(SoundType.ATTACK)`.
  - `playVictory()` -> `playSfx(SoundType.VICTORY)`.

#### 14. `AudioMapper.java`
* Nhận vào `AnimationEvent` hoặc `SoundType`, tự động phân loại để gọi đúng âm thanh:
  - Nếu `event.isCrit()` -> gọi `SoundType.CRITICAL_HIT`.
  - Nếu `event.getDamageDealt() > 0` -> gọi `SoundType.ATTACK`.
  - Nếu hồi máu -> `SoundType.HEAL`.

---

### CHẶNG 5: ĐIỂM VÀO ỨNG DỤNG (`source/app/Main.java`)

#### 15. `Main.java`
* **Phương thức `public static void main(String[] args)`**:
  1. Cấu hình Look & Feel hoặc khởi tạo bộ nạp tài nguyên: `AssetLoader.initialize();`.
  2. Bật ứng dụng trên luồng Swing:
     ```java
     SwingUtilities.invokeLater(() -> {
         GameApp app = new GameApp();
         app.start();
     });
     ```

---

## 5. CODE MẪU TỰ KIỂM THỬ ĐỘC LẬP CHO THÀNH VIÊN 1

Tạo một file test tạm thời `TestMember1.java` tại thư mục gốc để tự kiểm tra phần việc của mình trước khi tích hợp:

```java
import source.application.setup.HeroFactory;
import source.application.progression.LevelService;
import source.presentation.gui.audio.AudioService;
import source.presentation.gui.audio.SoundType;
import source.domain.entity.hero.Hero;

public class TestMember1 {
    public static void main(String[] args) {
        System.out.println("=== KIỂM THỬ PHẦN VIỆC THÀNH VIÊN 1 ===");

        // 1. Kiểm tra HeroFactory
        Hero warrior = HeroFactory.createHero(HeroFactory.HeroClass.WARRIOR, "Arthur");
        System.out.println("[OK] Đã tạo tướng: " + warrior.getName() + " | Cấp: " + warrior.getLevel());
        System.out.println("     HP tối đa: " + warrior.getStats().getMaxHp() + " | Thể lực: " + warrior.getStats().getMaxSta());

        // 2. Kiểm tra LevelService
        System.out.println("\n--- Thử nghiệm nhận 350 EXP ---");
        boolean up = LevelService.processExpGain(warrior, 350);
        System.out.println("[OK] Đã lên cấp? " + up);
        System.out.println("     Cấp độ mới: " + warrior.getLevel() + " (Kỳ vọng: Cấp 3)");
        System.out.println("     EXP dư: " + warrior.getStats().getCurrentExp() + " / " + LevelService.getExpRequired(warrior.getLevel()));
        System.out.println("     HP sau thăng cấp: " + warrior.getStats().getMaxHp());

        // 3. Kiểm tra AudioService (SFX)
        System.out.println("\n--- Thử nghiệm phát âm thanh ---");
        AudioService.getInstance().playButtonClick();
        AudioService.getInstance().playSfx(SoundType.ATTACK);
        System.out.println("[OK] Gọi phát âm thanh thành công không bị crash!");

        System.out.println("\n=== HOÀN TẤT KIỂM THỬ THÀNH CÔNG! ===");
    }
}
```

---

## 6. LỆNH BIÊN DỊCH VÀ CHẠY THỬ
Chạy lệnh sau tại thư mục gốc `mythical_dungeon` để đảm bảo code của bạn không có lỗi:
```bash
javac -d bin -cp . source/app/Main.java source/application/view/*.java source/application/setup/*.java source/application/progression/*.java source/presentation/gui/audio/*.java
```
Nếu biên dịch không hiện thông báo lỗi nào là bạn đã hoàn thành xuất sắc nhiệm vụ!
