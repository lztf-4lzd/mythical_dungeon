# CẨM NANG HƯỚNG DẪN CHI TIẾT NHIỆM VỤ TỪNG THÀNH VIÊN
## DỰ ÁN: MYTHICAL DUNGEON — 2D TURN-BASED RPG

> **Mục tiêu của tài liệu này**: Cung cấp lộ trình thực hiện từng bước (Step-by-step Roadmap), danh sách phương thức cần hoàn thiện, thuật toán xử lý chi tiết, các lỗi thường gặp cần tránh và code mẫu kiểm thử độc lập cho từng thành viên từ 1 đến 5.

---

## MỤC LỤC
1. [Thành viên 1: Data Architect, Progression & Audio SFX](#phần-1-thành-viên-1-data-architect-progression--audio-sfx)
2. [Thành viên 2: Combat Engine & Trí tuệ nhân tạo (AI) Quái vật](#phần-2-thành-viên-2-combat-engine--ai-quái-vật)
3. [Thành viên 3: Luồng Game, Sinh Hầm ngục & DTO Mapping](#phần-3-thành-viên-3-luồng-game-sinh-hầm-ngục--dto-mapping)
4. [Thành viên 4: Retro UI Pixel Engine & Canvas Sàn đấu](#phần-4-thành-viên-4-retro-ui-pixel-engine--canvas-sàn-đấu)
5. [Thành viên 5: Animation System & Hệ thống Màn hình Screen UX](#phần-5-thành-viên-5-animation-system--hệ-thống-màn-hình-screen-ux)

---

## PHẦN 1: THÀNH VIÊN 1 (DATA ARCHITECT, PROGRESSION & AUDIO SFX)

* **Tổng khối lượng**: ~1.350 dòng code (17 tệp).
* **Vị trí thư mục**: `source/application/view/`, `source/application/setup/`, `source/application/progression/`, `source/presentation/gui/audio/`, `source/app/`.

### 1.1. Lộ trình thực hiện từng bước (Roadmap)
1. **Bước 1**: Hoàn thiện toàn bộ các DTOs trong gói `application.view` (Dễ nhất - Chỉ toàn fields và getter/setter).
2. **Bước 2**: Hoàn thiện `HeroFactory` và `LoadoutService` trong gói `application.setup`.
3. **Bước 3**: Hoàn thiện `LevelService` trong gói `application.progression`.
4. **Bước 4**: Hoàn thiện tầng âm thanh SFX trong gói `presentation.gui.audio`.
5. **Bước 5**: Viết điểm vào `Main.java` kết nối ứng dụng.

---

### 1.2. Hướng dẫn chi tiết từng lớp (Class Guide)

#### A. Gói DTOs (`source.application.view.*`)
Các lớp DTO (Data Transfer Object) dùng để gửi dữ liệu hiển thị sang GUI mà không làm lộ đối tượng gốc:
* **`CombatantView.java`**: Biểu diễn trạng thái của 1 chiến binh (Anh hùng hoặc Quái vật).
  - Thuộc tính: `name`, `className`, `level`, `Elemental element`, `currentHp`, `maxHp`, `currentSta`, `maxSta`, `shield`, `List<EffectView> activeEffects`, `boolean isBoss`.
  - Nhiệm vụ: Gán các thuộc tính trong constructor, trả về giá trị qua các getter. Đảm bảo `activeEffects` là danh sách bất biến (`Collections.unmodifiableList`).
* **`BattleView.java`**: Chứa toàn bộ góc nhìn của một trận đánh.
  - Thuộc tính: `CombatantView hero`, `CombatantView monster`, `int roundNumber`, `String currentTurn`, `boolean isGameOver`, `boolean isVictory`.
* **`DungeonView.java`**: Chứa thông tin tổng quan của hầm ngục hiện tại.
  - Thuộc tính: `String dungeonName`, `int currentFloor`, `int totalFloors`, `int battlesWon`, `boolean isCampfireAvailable`.
* **`SkillView.java`**: Thông tin hiển thị của 1 ô kỹ năng trên thanh thao tác của người chơi.
  - Thuộc tính: `int slotIndex`, `String skillName`, `String description`, `int staCost`, `Elemental element`, `boolean isAvailable`, `String unavailableReason`.
* **`EffectView.java`**: Thông tin một huy hiệu hiệu ứng (Độc, Bỏng, Choáng, Khiên...).
  - Thuộc tính: `EffectType type`, `String displayName`, `int remainingDuration`, `int potency`, `boolean isBuff`.
* **`AnimationEvent.java`**: Sự kiện diễn hoạt hoạt ảnh gửi cho giao diện.
  - Thuộc tính: `MotionType motionType`, `String attackerName`, `String targetName`, `int damageDealt`, `boolean isCrit`, `boolean isMiss`, `List<String> effectNamesApplied`.
* **`MotionType.java`**: Enum định nghĩa loại chuyển động (`MELEE_LUNGE`, `PROJECTILE_ARROW`, `PROJECTILE_MAGIC`, `IMPACT_HIT`, `BUFF_HEAL`, `DEATH`).

#### B. Gói Khởi tạo & Trang bị (`source.application.setup.*`)
* **`HeroFactory.java`**:
  - `HeroClass`: Enum 4 hệ (`WARRIOR`, `MAGE`, `PALADIN`, `ARCHER`).
  - `createHero(HeroClass heroClass, String name)`:
    1. Kiểm tra `heroClass != null` và `name` không rỗng.
    2. Chuyển `HeroClass` sang `HeroId`: `HeroId heroId = HeroId.valueOf(heroClass.name())`.
    3. Gọi `HeroCatalogue.createHero(heroId, name.trim())` để sinh cá thể nhân vật.
    4. Trả về đối tượng `Hero` hoàn chỉnh.
* **`LoadoutService.java`**:
  - `validateAndSetLoadout(Character hero, List<Skill> selectedSkills)`:
    1. Kiểm tra `hero != null` và `selectedSkills != null`.
    2. Kiểm tra số lượng kỹ năng: Bắt buộc người chơi phải chọn đúng 4 kỹ năng (`GameConstants.HERO_BATTLE_SKILLS = 4`).
    3. Kiểm tra trùng lặp: Không cho phép mang 2 kỹ năng cùng tên.
    4. Gán danh sách kỹ năng hợp lệ vào `hero.setEquippedSkills(selectedSkills)`.

#### C. Gói Thăng cấp (`source.application.progression.*`)
* **`LevelService.java`**:
  - `getExpRequired(int level)`: Trả về `level * 100` (mỗi cấp cần thêm 100 EXP).
  - `processExpGain(Character hero, int expGained)`:
    1. Cộng dồn EXP: `totalExp = hero.getStats().getCurrentExp() + expGained`.
    2. Vòng lặp `while (level < MAX_LEVEL && totalExp >= reqExp)`:
       - Trừ EXP cần thiết: `totalExp -= reqExp`.
       - Gọi hàm `levelUp(hero)`.
       - Cập nhật lại `reqExp` cho cấp mới.
    3. Lưu lại phần EXP dư còn lại: `hero.getStats().setCurrentExp(totalExp)`.
  - `levelUp(hero)`:
    1. `hero.setLevel(hero.getLevel() + 1)`.
    2. Lấy thuộc tính tăng trưởng `Attributes growth = hero.getGrowthRate()`.
    3. Cộng dồn vào thuộc tính gốc: `hero.getAttributes().add(growth)`.
    4. Gọi `DamageCalculator.recalculateStats(hero)` để tính lại Max HP, Max STA, Giáp, Kháng...
    5. Hồi đầy máu và thể lực: `hero.getStats().restoreFullHpAndStamina()`.

#### D. Gói Âm thanh SFX (`source.presentation.gui.audio.*`)
* **`SoundType.java`**: Enum danh sách 11 file âm thanh:
  - `BUTTON_CLICK("sfx_button_click.wav")`
  - `ATTACK("assets/audio/sfx/sfx_attack.wav")`
  - `CRITICAL_HIT("assets/audio/sfx/sfx_critical_hit.wav")`
  - `CASTING_HIT("assets/audio/sfx/sfx_casting_hit.wav")`
  - `SHIELD_ABSORB("assets/audio/sfx/sfx_shield_absorb.wav")`
  - `HEAL("assets/audio/sfx/sfx_heal.wav")`
  - `BUFF("assets/audio/sfx/sfx_buff.wav")`
  - `DEBUFF("assets/audio/sfx/sfx_debuff.wav")`
  - `DODGE("assets/audio/sfx/sfx_dodge.wav")`
  - `VICTORY("assets/audio/sfx/sfx_victory.wav")`
  - `DEFEAT("assets/audio/sfx/sfx_defeat.wav")`
* **`SynthBackend.java`**:
  - Nạp tệp `.wav` bằng `AudioSystem.getAudioInputStream(...)`.
  - Lấy đối tượng `Clip`, điều chỉnh âm lượng qua `FloatControl.Type.MASTER_GAIN`.
  - Gọi `clip.start()` trong một `ExecutorService` (đa luồng) để không làm đơ giao diện Swing.
* **`AudioService.java`**: Singleton trung tâm.
  - `playSfx(SoundType sound)`: Kiểm tra `settings.isSfxMuted() == false`, tính âm lượng hiệu dụng `volume = settings.getEffectiveSfxVolume()`, gọi `backend.playSfx(sound.getResourcePath(), volume)`.

---

### 1.3. Code mẫu tự kiểm thử độc lập cho Thành viên 1
Tạo file `TestMember1.java` chạy trên Console để kiểm tra:
```java
// 1. Kiểm tra tạo Hero và thăng cấp
Character hero = HeroFactory.createHero(HeroFactory.HeroClass.WARRIOR, "Arthur");
System.out.println("Tướng: " + hero.getName() + " | Level: " + hero.getLevel() + " | HP: " + hero.getStats().getMaxHp());

// Thử cộng 350 EXP (sẽ nhảy lên cấp 3)
LevelService.processExpGain(hero, 350);
System.out.println("Sau khi nhận 350 EXP -> Level: " + hero.getLevel() + " | EXP dư: " + hero.getStats().getCurrentExp());

// 2. Kiểm tra phát âm thanh SFX
AudioService.getInstance().playButtonClick();
AudioService.getInstance().playSfx(SoundType.CRITICAL_HIT);
System.out.println("Phát âm thanh thành công!");
```

---

## PHẦN 2: THÀNH VIÊN 2 (COMBAT ENGINE & AI QUÁI VẬT)

* **Tổng khối lượng**: ~2.650 dòng code (9 tệp).
* **Vị trí thư mục**: `source/application/combat/`.

### 2.1. Lộ trình thực hiện từng bước (Roadmap)
1. **Bước 1**: Hoàn thiện `TurnReport.java` và `CombatFormatter.java` (Khung lưu trữ nhật ký lượt).
2. **Bước 2**: Hoàn thiện `RecoveryService.java` (Hồi thể lực đầu mỗi vòng đấu).
3. **Bước 3**: Hoàn thiện `EffectService.java` (Xử lý hiệu ứng độc, thiêu đốt, trừ lượt buff/debuff).
4. **Bước 4**: Hoàn thiện `ActionService.java` (Xử lý tấn công, trừ STA, tính dame, trừ khiên và máu).
5. **Bước 5**: Hoàn thiện `DecisionService.java` và `RoleTactics.java` (Trí tuệ nhân tạo AI quái vật).
6. **Bước 6**: Ghép nối tất cả vào nhạc trưởng điều phối `BattleSession.java`.

---

### 2.2. Hướng dẫn chi tiết từng lớp (Class Guide)

* **`ActionService.java` (Quan trọng nhất)**:
  - `canExecuteSkill(attacker, skill)`: Kiểm tra xem thể lực hiện tại `currentSta >= skill.getStaCost()`, và nếu chiêu thức đánh dấu `isOncePerBattle()` thì kiểm tra nhân vật chưa dùng lần nào trong trận.
  - `executeSkillActionWithResult(attacker, target, skill, damageMultiplier)`:
    1. Trừ thể lực người thi triển: `attacker.getStats().setCurrentSta(sta - skill.getStaCost())`.
    2. Tính hồi máu bản thân (nếu kỹ năng có heal): `applyHealing(attacker, mods)`.
    3. Tính hồi thể lực bản thân: `applyStaminaRestoration(attacker, mods)`.
    4. Kiểm tra trúng/né: Dùng `GameRandom`, lấy `accuracy` của attacker trừ `dodge` của target. Nếu né thành công, ghi nhận `wasMissed = true`.
    5. Nếu trúng đòn: Gọi `DamageCalculator.calculateDamage(attacker, target, skill)`.
    6. Trừ sát thương vào Khiên trước (`shieldAmount`). Nếu sát thương vượt quá khiên, trừ phần dư còn lại vào Máu (`currentHp`).
    7. Tính hút máu (Lifesteal): Hồi máu cho attacker theo tỷ lệ phần trăm sát thương thực tế gây ra.
    8. Áp dụng hiệu ứng: Gọi `EffectService.applySkillEffects(...)` để gắn bùa độc/choáng/buff lên mục tiêu.
    9. Đóng gói toàn bộ thông số vào đối tượng `ActionResult`.
* **`EffectService.java`**:
  - `processTurnStartEffects(character)`: Chạy ở đầu lượt của nhân vật.
    1. Lặp qua danh sách `character.getActiveEffects()`.
    2. Nếu là `POISON` hoặc `BURN`: Trừ máu trực tiếp theo chỉ số `potency`.
    3. Giảm thời gian hiệu lực: `effect.decrementDuration()`.
    4. Xóa các hiệu ứng có `duration <= 0`.
    5. Kiểm tra nếu có hiệu ứng `STUN` hoặc `FREEZE`: Đánh dấu nhân vật bị mất lượt (`canAct = false`).
* **`RoleTactics.java` & `DecisionService.java` (AI Quái vật)**:
  - Phân loại theo vai trò quái `MonsterRole`:
    - **DPS**: Luôn ưu tiên dùng chiêu gây sát thương mạnh nhất hoặc chiêu có tỷ lệ bạo kích cao.
    - **TANK**: Nếu máu dưới 50%, ưu tiên dùng kỹ năng tăng giáp hoặc buff Khiên (Shield).
    - **SUPPORT**: Ưu tiên giải trừ hiệu ứng xấu hoặc hồi máu khi đồng đội/bản thân thấp máu.
    - **BOSS**: Kiểm tra ngưỡng máu; nếu máu dưới 30% hoặc đến vòng quy định, bắt buộc xả chiêu Ultimate tối thượng.
  - Nếu thể lực không đủ dùng kỹ năng đặc biệt: AI tự động chuyển sang dùng kỹ năng đánh thường (Basic Attack tốn 0 STA).
* **`BattleSession.java` (Điều phối trận đấu)**:
  - Quản lý `BattleState`: `READY` -> `AWAITING_PLAYER_ACTION` -> `AWAITING_MONSTER_ACTION` -> `VICTORY` / `DEFEAT`.
  - `start()`: Bắt đầu vòng 1, so sánh Tốc độ `hero.getStats().getSpeed()` với `monster.getStats().getSpeed()`. Ai nhanh hơn được chuyển sang trạng thái hành động trước.
  - `executePlayerActionOnly(skillSlotIndex)`: Chạy đòn đánh của người chơi, kiểm tra nếu quái chết thì set `VICTORY`, nếu quái còn sống thì set `AWAITING_MONSTER_ACTION`.
  - `executeMonsterActionOnly()`: Cho AI quái ra chiêu, kiểm tra nếu hero chết thì set `DEFEAT`, nếu hero còn sống thì tăng `roundNumber++` và bắt đầu vòng mới.

---

### 2.3. Code mẫu tự kiểm thử độc lập cho Thành viên 2
Tạo file `TestMember2.java` chạy trận đấu Console không cần GUI:
```java
Hero hero = HeroCatalogue.createHero(HeroId.WARRIOR, "Tester");
Monster monster = MonsterCatalogue.create(MonsterId.ORC, false);

BattleSession session = new BattleSession(hero, monster);
TurnReport r1 = session.start();
System.out.println("Vòng 1 bắt đầu. Ai đi trước? " + session.getState());

// Giả sử Hero đi trước, cho Hero đánh chiêu slot 0
TurnReport r2 = session.executePlayerActionOnly(0);
System.out.println("Hero vừa đánh! Máu quái còn: " + monster.getStats().getCurrentHp());

// Quái phản công
TurnReport r3 = session.executeMonsterActionOnly();
System.out.println("Quái vừa đánh! Máu Hero còn: " + hero.getStats().getCurrentHp());
```

---

## PHẦN 3: THÀNH VIÊN 3 (LUỒNG GAME, SINH HẦM NGỤC & DTO MAPPING)

* **Tổng khối lượng**: ~2.500 dòng code (8 tệp).
* **Vị trí thư mục**: `source/application/dungeon/`, `source/application/flow/`.

### 3.1. Lộ trình thực hiện từng bước (Roadmap)
1. **Bước 1**: Hoàn thiện `FloorCatalog.java` (Cấu hình quái cho 4 hầm ngục và lượng EXP).
2. **Bước 2**: Hoàn thiện `DungeonRun.java` (Tiến trình thám hiểm 5 ải).
3. **Bước 3**: Hoàn thiện `MotionClassifier.java` và `EventMapper.java` (Tạo sự kiện hoạt ảnh).
4. **Bước 4**: Hoàn thiện `ViewMapper.java` (Ánh xạ Entity sang DTOs hiển thị).
5. **Bước 5**: Hoàn thiện `GameFlow`, `DungeonFlow` và `BattleFlow` (State Machine điều phối).

---

### 3.2. Hướng dẫn chi tiết từng lớp (Class Guide)

* **`FloorCatalog.java`**:
  - `getEncounterExp(int encounterIndex)`: Trả về EXP tăng dần cho 5 ải: `100, 150, 200, 300, 500`.
  - `createMonsters(DungeonType dungeonType)`: Dùng `switch (dungeonType)` để tạo danh sách 5 quái vật mới tinh cho mỗi chuyến đi:
    - *Undead Crypt*: Skeleton, Armored Skeleton, Greatsword Skeleton, Restless Skeleton, Boss: Dread Cavalier Lord.
    - *Orc Stronghold*: Orc, Elite Orc, Armored Orc, Orc Rider, Boss: Orc Berserker Lord.
    - *Infernal Abyss*: Hellhound, Hellbat, Infernal Imp, Succubus, Boss: Obsidian Flame Golem.
* **`DungeonRun.java`**:
  - Thuộc tính: `Character hero`, `DungeonType dungeonType`, `List<Monster> encounters`, `int currentEncounterIndex` (0 đến 4), `int totalExpEarned`.
  - `concludeBattle(boolean heroWon)`: 
    - Nếu thắng: Cộng EXP qua `LevelService.processExpGain`, hồi phục 20% HP và 40% STA cho nhân vật (`AFTER_BATTLE_HP_RECOVERY_RATIO`).
    - Tăng `currentEncounterIndex++`. Nếu vượt qua ải thứ 5 (index == 5) -> Đánh dấu chiến thắng toàn bộ hầm ngục (`isVictory = true`).
* **`ViewMapper.java`**:
  - `toCombatantView(Character c)`: Đọc thông tin từ `c.getName()`, `c.getLevel()`, `c.getStats().getCurrentHp()`... để tạo ra một đối tượng `CombatantView` bất biến gửi cho giao diện.
  - `toBattleView(BattleSession session)`: Tổng hợp thông tin cả hai bên thành `BattleView`.
* **`EventMapper.java`**:
  - `toAnimationEvents(TurnReport report)`: Đọc danh sách `ActionRecord` từ kết quả lượt đánh của TV2, phân tích xem đòn đánh là cận chiến hay bắn cung/phép (dùng `MotionClassifier`), sinh ra danh sách `AnimationEvent` (gồm đạn bay, số dame bay, rung màn) để gửi cho TV5 diễn hoạt.
* **`DungeonFlow.java` & `BattleFlow.java`**:
  - Quản lý cầu nối giữa GUI và Engine. Cung cấp các hàm sạch như `battleFlow.executeHeroAction(slotIndex)` trả về `BattleStepResult` để màn hình chỉ việc gọi mà không cần quan tâm logic bên trong.

---

### 3.3. Code mẫu tự kiểm thử độc lập cho Thành viên 3
Tạo file `TestMember3.java`:
```java
// 1. Kiểm tra sinh 5 quái trong Crypt
List<Monster> monsters = FloorCatalog.createMonsters(FloorCatalog.DungeonType.UNDEAD_CRYPT);
System.out.println("Số quái sinh ra: " + monsters.size());
for (int i = 0; i < monsters.size(); i++) {
    System.out.println("Ải " + (i + 1) + ": " + monsters.get(i).getName() + " (HP: " + monsters.get(i).getStats().getMaxHp() + ")");
}

// 2. Kiểm tra DungeonRun qua ải
Hero hero = HeroCatalogue.createHero(HeroId.WARRIOR, "Arthur");
DungeonRun run = new DungeonRun(hero, FloorCatalog.DungeonType.UNDEAD_CRYPT);
run.startNextBattle();
System.out.println("Đang ở ải: " + run.getCurrentEncounterIndex());
run.concludeBattle(true);
System.out.println("Thắng ải 1 -> Chuyển sang ải: " + run.getCurrentEncounterIndex());
```

---

## PHẦN 4: THÀNH VIÊN 4 (RETRO UI PIXEL ENGINE & CANVAS SÀN ĐẤU)

* **Tổng khối lượng**: ~2.700 dòng code (13 tệp).
* **Vị trí thư mục**: `source/presentation/gui/ui/`, `source/presentation/gui/asset/`.

### 4.1. Lộ trình thực hiện từng bước (Roadmap)
1. **Bước 1**: Hoàn thiện `Theme.java` và `PixelBorders.java` (Bảng màu RGB retro, font chữ pixel).
2. **Bước 2**: Hoàn thiện `SpriteResolver.java` (Bộ phân giải đường dẫn ảnh tướng/quái từ assets).
3. **Bước 3**: Hoàn thiện `NineSlicePainter.java` (Vẽ co giãn 9 góc khung viền từ `manifest.json`).
4. **Bước 4**: Hoàn thiện `PixelButtonUI.java` và `PixelBarUI.java` (Custom giao diện nút và thanh máu).
5. **Bước 5**: Hoàn thiện `BackdropRenderer.java` và `CombatantRenderer.java` (Vẽ nền và vẽ nhân vật).
6. **Bước 6**: Ghép nối thành sân khấu đồ họa `StageCanvas.java`.

---

### 4.2. Hướng dẫn chi tiết từng lớp (Class Guide)

* **`Theme.java`**:
  - Khai báo các mã màu chuẩn Pixel RPG:
    - Nền tối: `BG_DARKEST = new Color(20, 22, 34)`
    - Màu máu (HP): `COLOR_HP = new Color(214, 58, 58)`
    - Màu thể lực (STA): `COLOR_STA = new Color(58, 166, 214)`
    - Màu khiên: `COLOR_SHIELD = new Color(180, 180, 200)`
    - Màu chữ vàng kim: `TEXT_GOLD = new Color(245, 210, 80)`
* **`SpriteResolver.java`**:
  - `resolveHeroSpritePath(CombatantView hero)`: Trả về `"assets/heroes/" + hero.getClassName() + "_idle.png"`.
  - `resolveMonsterSpritePath(CombatantView monster)`: Ánh xạ chuẩn từ tên quái sang ảnh `"assets/monsters/" + monsterId + ".png"`.
* **`NineSlicePainter.java`**:
  - Đọc siêu dữ liệu lề cắt 9-slice từ `AssetLoader.getUiAsset("frame_panel")`.
  - Cắt ảnh gốc thành 9 phần: 4 góc cố định không bị mờ nét, 4 cạnh co giãn theo chiều ngang/dọc, và 1 phần thân giữa lấp đầy diện tích panel.
* **`PixelBarUI.java` (Thanh máu/thể lực retro)**:
  - Kế thừa `BasicProgressBarUI`.
  - Vẽ viền đôi pixel đen xung quanh.
  - Vẽ thanh máu giật từng khấc pixel art (không dùng gradient hiện đại) tạo phong cách hoài niệm cổ điển.
* **`CombatantRenderer.java`**:
  - Vẽ hình ảnh nhân vật lên tọa độ sàn đấu: Anh hùng đứng bên trái quay mặt sang phải, Quái vật đứng bên phải quay mặt sang trái.
  - Hỗ trợ vẽ độ lệch tọa độ (`offsetX`, `offsetY`) khi bị trúng đòn rung lắc.
* **`StageCanvas.java` (Sân khấu chính)**:
  - Kế thừa `JPanel`, triển khai giao diện `AnimationStage`.
  - `paintComponent(Graphics g)`:
    1. Vẽ nền ngục tối qua `BackdropRenderer`.
    2. Vẽ bóng chân nhân vật.
    3. Vẽ nhân vật và quái vật qua `CombatantRenderer`.
    4. Vẽ các hiệu ứng hoạt ảnh đang diễn ra (đạn bay, chữ nổi bay lên).

---

### 4.3. Code mẫu tự kiểm thử độc lập cho Thành viên 4
Tạo file `TestMember4.java` mở cửa sổ Swing kiểm tra đồ họa:
```java
JFrame frame = new JFrame("Test UI Components");
frame.setSize(400, 300);
frame.setLayout(new FlowLayout());
frame.getContentPane().setBackground(Theme.BG_DARKEST);

// Tạo nút bấm pixel test
JButton btn = new JButton("Attack Skill");
btn.setUI(new PixelButtonUI());
frame.add(btn);

// Tạo thanh máu HP pixel test
JProgressBar hpBar = new JProgressBar(0, 100);
hpBar.setValue(75);
hpBar.setStringPainted(true);
hpBar.setUI(new PixelBarUI(Theme.COLOR_HP));
frame.add(hpBar);

frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setVisible(true);
```

---

## PHẦN 5: THÀNH VIÊN 5 (ANIMATION SYSTEM & HỆ THỐNG MÀN HÌNH SCREEN UX)

* **Tổng khối lượng**: ~2.900 dòng code (26 tệp).
* **Vị trí thư mục**: `source/presentation/gui/animation/`, `source/presentation/gui/screen/`, `source/presentation/gui/app/`.

### 5.1. Lộ trình thực hiện từng bước (Roadmap)
1. **Bước 1**: Hoàn thiện `AnimationManager.java` (Vòng lặp Timer 60 FPS cập nhật thời gian hoạt ảnh).
2. **Bước 2**: Hoàn thiện các class diễn hoạt: `FrameAnimation`, `FloatingText`, `ShakeAnimation`, `LungeAnimation`, `ProjectileAnimation`.
3. **Bước 3**: Hoàn thiện `TurnSequencer.java` (Xếp hàng trình diễn đòn đánh).
4. **Bước 4**: Hoàn thiện `ScreenManager.java` (Quản lý CardLayout chuyển đổi màn hình).
5. **Bước 5**: Viết các màn hình tĩnh: `MenuScreen`, `NameScreen`, `ClassScreen`, `DungeonScreen`, `RewardScreen`, `ResultScreen`, `SettingsScreen`.
6. **Bước 6**: Hoàn thiện màn hình lớn `BattleScreen.java` và `SkillScreen.java`.
7. **Bước 7**: Kết nối `GameApp.java`.

---

### 5.2. Hướng dẫn chi tiết từng lớp (Class Guide)

* **`AnimationManager.java`**:
  - Dùng một `javax.swing.Timer` chạy chu kỳ 16 mili-giây (tương đương 60 khung hình/giây - 60 FPS).
  - Mỗi nhịp đếm: Lặp qua danh sách các hoạt ảnh đang chạy, gọi `anim.update(deltaTime)`, xóa các hoạt ảnh đã kết thúc (`isFinished()`), và gọi `stage.repaint()` để vẽ lại màn hình mượt mà.
* **`FloatingText.java` (Chữ số bay lên)**:
  - Khi đòn đánh trúng mục tiêu, sinh ra một chữ nổi tại tọa độ trúng đòn (ví dụ: `"-85"`, `"CRIT! -140"`, `"HEAL +40"`).
  - Tọa độ Y dịch chuyển dần lên trên theo thời gian (`y -= speed * dt`), độ trong suốt alpha mờ dần từ `1.0f` về `0.0f`.
* **`ShakeAnimation.java` (Rung lắc màn hình)**:
  - Khi có đòn chí mạng (Critical hit): Sinh ra độ lệch tọa độ ngẫu nhiên `(randomX, randomY)` trong khoảng `-6..+6 pixel`, giảm dần về 0 để tạo cảm giác chấn động mạnh.
* **`TurnSequencer.java` (Điều phối kịch bản lượt)**:
  - Đảm bảo đòn đánh diễn ra theo đúng thứ tự logic điện ảnh:
    1. Khóa nút bấm (Input Lock: `inputLockCallback.accept(true)`).
    2. Chạy đòn tấn công của người ra chiêu (Tướng lao lên hoặc bắn tên).
    3. Điểm va chạm (Impact Point): Kích hoạt hoạt ảnh rung quái, chữ số dame bay lên, phát âm thanh SFX, cập nhật lại thanh máu mới.
    4. Tướng rút lui về tư thế đứng nghỉ (Idle).
    5. Mở khóa nút bấm (Input Lock: `inputLockCallback.accept(false)`).
* **`ScreenManager.java` & Các màn hình (`Screen.java`)**:
  - Kế thừa `JPanel`, sử dụng bố cục `CardLayout`.
  - Cung cấp hàm `showScreen("SCREEN_NAME")` chuyển đổi nhanh chóng giữa các màn hình:
    - `"MAIN_MENU"` -> `MenuScreen`
    - `"NAME_ENTRY"` -> `NameScreen`
    - `"CLASS_SELECTION"` -> `ClassScreen`
    - `"DUNGEON_SELECT"` -> `DungeonScreen`
    - `"SKILL_SELECT"` -> `SkillScreen`
    - `"BATTLE"` -> `BattleScreen`
    - `"REWARD"` -> `RewardScreen` (Campfire)
    - `"RESULT"` -> `ResultScreen`
    - `"SETTINGS"` -> `SettingsScreen`
* **`BattleScreen.java`**:
  - Màn hình trung tâm: Bên trên hiển thị thông tin ải; ở giữa đặt `StageCanvas` vẽ trận đấu; góc dưới bên trái là 4 nút bấm kỹ năng kèm chỉ số thể lực; góc dưới bên phải là khu vực nhật ký `JTextArea` hiển thị chi tiết dòng chữ các đòn đánh.

---

### 5.3. Code mẫu tự kiểm thử độc lập cho Thành viên 5
Tạo file `TestMember5.java` kiểm tra hoạt ảnh chữ số bay và chuyển màn hình:
```java
JFrame frame = new JFrame("Test Screen & Animation");
frame.setSize(960, 640);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

ScreenManager screenManager = new ScreenManager();
MenuScreen menu = new MenuScreen(screenManager);
screenManager.registerScreen("MAIN_MENU", menu);
screenManager.showScreen("MAIN_MENU");

frame.add(screenManager.getContainer());
frame.setVisible(true);
System.out.println("Giao diện ScreenManager đã chạy thành công!");
```

---
*Tài liệu được biên soạn phục vụ đồ án môn học Lập trình hướng đối tượng (OOP).*
