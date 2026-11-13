# TÀI LIỆU HƯỚNG DẪN DỰ ÁN, FLOW GAME VÀ PHÂN CÔNG VAI TRÒ
## ĐỒ ÁN: MYTHICAL DUNGEON (GAME NHẬP VAI CHIẾN ĐẤU THEO LƯỢT 2D)

---

## MỤC LỤC
1. [Quy tắc làm việc và Nguyên tắc phối hợp nhóm](#1-quy-tắc-làm-việc-và-nguyên-tắc-phối-hợp-nhóm)
2. [Flow toàn bộ trò chơi từ A - Z (Game Lifecycle)](#2-flow-toàn-bộ-trò-chơi-từ-a---z-game-lifecycle)
3. [Phân công vai trò chi tiết cho 5 thành viên](#3-phân-công-vai-trò-chi-tiết-cho-5-thành-viên)
   - [Thành viên 1 (Data Architect & Audio)](file:///d:/My%20Folder/Projects/mythical_dungeon/NHIEM_VU_THANH_VIEN_1.md)
   - [Thành viên 2 (Combat Engine & AI Quái)](file:///d:/My%20Folder/Projects/mythical_dungeon/NHIEM_VU_THANH_VIEN_2.md)
   - [Thành viên 3 (Game Flow & Dungeon)](file:///d:/My%20Folder/Projects/mythical_dungeon/NHIEM_VU_THANH_VIEN_3.md)
   - [Thành viên 4 (Retro UI & Canvas)](file:///d:/My%20Folder/Projects/mythical_dungeon/NHIEM_VU_THANH_VIEN_4.md)
   - [Thành viên 5 (Animation & Screen UX)](file:///d:/My%20Folder/Projects/mythical_dungeon/NHIEM_VU_THANH_VIEN_5.md)
4. [Hướng dẫn tự kiểm thử độc lập (Không phụ thuộc nhau)](#4-hướng-dẫn-tự-kiểm-thử-độc-lập-không-phụ-thuộc-nhau)
5. [Lệnh biên dịch và Chạy game](#5-lệnh-biên-dịch-và-chạy-game)

---

## 1. QUY TẮC LÀM VIỆC VÀ NGUYÊN TẮC PHỐI HỢP NHÓM

Để 5 thành viên có thể làm việc song song, **tuyệt đối không bị xung đột mã nguồn (merge conflict)** và **không phải chờ đợi nhau**, toàn bộ thành viên bắt buộc phải tuân thủ 4 nguyên tắc vàng sau:

### 🔒 Nguyên tắc 1: "Khóa cứng hợp đồng API" (API Contract Freeze)
- Toàn bộ 73 tệp `.java` ở các tầng `application` và `presentation` đã được khởi tạo sẵn **100% tên hàm, danh sách tham số và kiểu trả về**.
- **QUY ĐỊNH BẮT BUỘC**: Thành viên **không được tự ý đổi tên hàm, không xóa hàm, không đổi kiểu tham số đầu vào hay kiểu trả về**. 
- Thành viên chỉ viết nội dung logic bên trong cặp dấu ngoặc nhọn `{ ... }` dựa theo các bước hướng dẫn `// TODO:` có sẵn trong từng hàm.
- Nhờ nguyên tắc này, người gọi hàm và người viết hàm luôn ăn khớp với nhau 100%.

### 🏰 Nguyên tắc 2: "Phân quyền thư mục — Nước sông không phạm nước giếng"
- Mỗi thành viên được giao phụ trách một nhóm gói (package) riêng biệt.
- **Ai sở hữu gói nào chỉ được chỉnh sửa và commit code trong gói đó**. Tuyệt đối không tự ý mở file của bạn mình ra sửa.
- Nếu cần một hàm mới hoặc muốn trao đổi dữ liệu, hãy thống nhất với người sở hữu gói đó thay vì tự sửa.

### 🧪 Nguyên tắc 3: "Dữ liệu giả lập" (Mock Data Testing)
- **Đội Giao diện (GUI)** KHÔNG CẦN CHỜ Đội Logic viết xong. Khi thiết kế màn hình, bạn hãy tự tạo một đối tượng giả lập (ví dụ tạo 1 `CombatantView` có 100 HP, tên "Warrior") để đưa lên vẽ thử và kiểm tra hoạt ảnh ngay lập tức.
- **Đội Logic (Combat & Dungeon)** KHÔNG CẦN GIAO DIỆN. Bạn chỉ cần viết hàm `main` nhỏ hoặc dùng lệnh in ra Console (`System.out.println`) để kiểm tra xem trừ máu, tính dame và lên cấp đúng chưa.

### 🟢 Nguyên tắc 4: "Giữ lệnh build luôn xanh" (Green Build Policy)
- Trước khi push code lên Git hoặc chia sẻ cho nhóm, hãy đảm bảo lệnh biên dịch `javac -Xlint:all -Werror` chạy thành công với **0 lỗi, 0 cảnh báo**.
- Không bao giờ commit code dở dang làm vỡ build của toàn dự án.

---

## 2. FLOW TOÀN BỘ TRÒ CHƠI TỪ A - Z (GAME LIFECYCLE)

### Sơ đồ luồng tổng quan (Flowchart)

```
[Main.java]
     │
     ▼
[GameApp.launch()] ────► Mở cửa sổ Swing 960x640, khởi tạo CardLayout
     │
     ▼
[MenuScreen] ──────────► Bấm "Start Game"
     │
     ▼
[NameScreen] ──────────► Nhập tên Anh hùng (ví dụ: "Arthur")
     │
     ▼
[ClassScreen] ─────────► Chọn 1 trong 4 Hệ: Warrior, Mage, Paladin, Archer
     │                   Gọi HeroFactory.createHero(...) tạo cá thể tướng
     ▼
[DungeonScreen] ───────► Chọn 1 trong 4 Hầm ngục: Crypt, Stronghold, Abyss, Citadel
     │                   Khởi tạo DungeonRun: sinh 5 ải quái từ FloorCatalog
     ▼
[SkillScreen] ─────────► Chọn 4 / 10 kỹ năng mang vào trận đấu
     │                   LoadoutService.validateAndSetLoadout(...)
     ▼
┌────────────────────────────────────────────────────────────────────────┐
│ VÒNG LẶP HẦM NGỤC (DUNGEON LOOP: 5 ẢI CHẠM TRÁN)                       │
│                                                                        │
│   [BattleScreen] ◄── Khởi tạo BattleSession(hero, monster)             │
│        │                                                               │
│        ├─► [Đầu vòng]: Hồi STA, xét Tốc độ (Speed), trừ máu độc/cháy   │
│        │                                                               │
│        ├─► [Lượt Anh hùng]: Chọn skill ──► ActionService tính dame    │
│        │   TurnSequencer diễn hoạt: chém kiếm, số dame bay, rung màn   │
│        │   Kiểm tra: Quái chết? ──► Thắng trận!                        │
│        │                                                               │
│        └─► [Lượt Quái vật]: AI RoleTactics tự chọn chiêu tối ưu       │
│            Quái đánh trả ──► Hero trúng đòn, trừ máu                   │
│            Kiểm tra: Hero chết? ──► Thất bại (GameOver)!               │
│                                                                        │
│   [Thắng trận]: Cộng EXP, LevelService xét lên cấp, hồi 20% HP         │
│   [Ải 3]: Ghé Lửa trại (RewardScreen/Campfire) hồi 50% HP, đổi Skill   │
│   [Ải 5]: Đánh trùm cuối (Boss)!                                       │
└──────────────────────────────────┬─────────────────────────────────────┘
                                   │
                   ┌───────────────┴───────────────┐
                   ▼                               ▼
        [Thắng Ải 5 - Clear Game]          [Hero HP về 0 - Defeat]
                   │                               │
                   └───────────────┬───────────────┘
                                   │
                                   ▼
                            [ResultScreen]
              (Hiển thị Thống kê, Bấm "Play Again" hoặc "Exit")
```

---

### Chi tiết 5 giai đoạn chạy của game:

#### GIAI ĐOẠN 1: KHỞI CHẠY HỆ THỐNG (BOOTSTRAP)
1. Chương trình bắt đầu tại hàm `main` trong `source.app.Main`.
2. `Main` nạp tài nguyên tĩnh qua `AssetLoader` (đọc `manifest.json`, chuẩn bị bộ nhớ đệm cache ảnh, sprite sheets, font chữ, âm thanh SFX).
3. `GameApp.launch()` được đưa vào luồng giao diện Swing Event Dispatch Thread (`SwingUtilities.invokeLater`).
4. `GameApp` mở cửa sổ `JFrame` kích thước chuẩn `960x640 pixel`, khởi tạo bộ điều phối luồng `GameFlow` và bộ quản lý màn hình `ScreenManager` (sử dụng `CardLayout`).
5. Màn hình đầu tiên được hiển thị: `MenuScreen` ("MAIN_MENU").

#### GIAI ĐOẠN 2: THIẾT LẬP NHÂN VẬT & CHỌN HẦM NGỤC (SETUP PHASE)
1. **MenuScreen**: Người chơi bấm nút **"Start Game"** (âm thanh click kêu qua `AudioService.playButtonClick()`), `ScreenManager` chuyển sang thẻ `NameScreen`.
2. **NameScreen**: Người chơi nhập tên nhân vật vào ô `JTextField`. Bấm **"Continue"**, `GameFlow.setHeroName(name)` lưu tên và chuyển sang `ClassScreen`.
3. **ClassScreen**: Hiển thị 4 thẻ bài nhân vật (Chiến Binh, Pháp Sư, Hiệp Sĩ, Cung Thủ) kèm ảnh chân dung avatar, chỉ số cơ bản và nguyên tố:
   - Người chơi click chọn hệ -> gọi `HeroFactory.createHero(heroClass, name)`.
   - Đối tượng `Hero` được khởi tạo, tính toán các chỉ số phái sinh qua `DamageCalculator.recalculateStats(hero)`, hồi đầy máu/thể lực và chuyển sang `DungeonScreen`.
4. **DungeonScreen**: Hiển thị danh sách 4 Hầm Ngục Phe Phái:
   - *Undead Crypt* (Hầm Mộ Xác Sống)
   - *Orc Stronghold* (Pháo Đài Orc)
   - *Infernal Abyss* (Vực Thẳm Địa Ngục)
   - *Dark Citadel* (Thành Trì Hắc Ám)
   - Người chơi chọn 1 hầm ngục -> gọi `gameFlow.startDungeon(dungeonType)`.
   - Một tiến trình `DungeonRun` được tạo: sinh sẵn 5 cuộc chạm trán tương ứng từ `FloorCatalog.createMonsters(dungeonType)`.
5. **SkillScreen**: 
   - Hiển thị danh sách 10 kỹ năng của lớp nhân vật (lấy từ `HeroSkills`).
   - Người chơi chọn đúng 4 kỹ năng ưng ý để mang vào trận đấu.
   - Hàm `LoadoutService.validateAndSetLoadout(hero, skills)` kiểm tra tính hợp lệ và lưu vào danh sách kỹ năng trang bị của anh hùng.
   - Bấm **"Enter Dungeon"** -> chính thức bước vào hầm ngục!

#### GIAI ĐOẠN 3: VÒNG LẶP TRẬN ĐẤU THEO LƯỢT (COMBAT LOOP)
1. `DungeonFlow.startNextBattle()` lấy quái vật của ải hiện tại, khởi tạo `BattleSession(hero, monster)` và bọc vào `BattleFlow`.
2. `ScreenManager` chuyển sang `BattleScreen`.
3. **Bắt đầu trận (`battleSession.start()`):**
   - Hồi thể lực đầu vòng cho cả 2 bên.
   - So sánh Tốc độ (Speed): Bên nào nhanh hơn sẽ đi trước trong vòng hiện tại.
   - Xử lý các hiệu ứng DoT đầu lượt (nếu bị trúng độc/thiêu đốt thì trừ máu trước).
   - Nếu Anh hùng đi trước: Trạng thái chuyển thành `AWAITING_PLAYER_ACTION`, 4 nút bấm kỹ năng sáng lên.
4. **Lượt của Anh hùng (Hero Turn):**
   - Người chơi click vào 1 trong 4 nút kỹ năng.
   - Gọi `battleFlow.executeHeroAction(slotIndex)` -> chuyển tiếp tới `battleSession.executePlayerActionOnly(slotIndex)`.
   - `ActionService.executeSkillActionWithResult(...)` tính toán:
     - Trừ chi phí thể lực (Stamina).
     - Kiểm tra trúng/né dựa trên Accuracy và Dodge.
     - Tính sát thương qua `DamageCalculator`: xét tương khắc hệ (Elemental 1.25x / 0.75x), tỷ lệ bạo kích (Chí mạng 1.5x), trừ qua Khiên (Shield) rồi trừ vào Máu (HP).
     - Áp dụng các hiệu ứng Buff/Debuff/Choáng nếu có.
     - Trả về `TurnReport` và danh sách sự kiện hoạt ảnh `AnimationEvent`.
   - **Diễn hoạt hoạt ảnh (`TurnSequencer`):**
     - Khóa nút bấm giao diện để chống bấm spam.
     - Tướng lao lên chém hoặc bắn đạn phép (`LungeAnimation` / `ProjectileAnimation`).
     - Quái vật giật lùi trúng đòn (`ImpactAnimation`), màn hình rung lên nếu đánh chí mạng (`ShakeAnimation`).
     - Số sát thương và chữ bạo kích bay lên rồi mờ dần (`FloatingText`).
     - Phát âm thanh SFX chém/nổ chiêu (`AudioService.playSfx(...)`).
     - Cập nhật tụt thanh máu của quái vật trên giao diện.
     - Mở khóa lại nút bấm khi hoạt ảnh kết thúc.
   - **Kiểm tra trạng thái:**
     - Nếu Máu quái vật <= 0: Quái vật phát hoạt ảnh gục ngã (`FrameAnimation`), trận đấu kết thúc -> Anh hùng thắng trận!
     - Nếu Quái vật còn sống: Chuyển lượt sang Quái vật (`AWAITING_MONSTER_ACTION`).
5. **Lượt của Quái vật (Monster Turn):**
   - `battleFlow.executeMonsterAction()` gọi AI: `DecisionService` và `RoleTactics` (phân loại theo vai trò: DPS, Tank, Support, Boss).
   - Quái vật tự động phân tích máu của bản thân và người chơi để chọn chiêu thức nguy hiểm nhất.
   - `ActionService` thực thi đòn đánh của quái lên anh hùng.
   - Hoạt ảnh quái tấn công -> Anh hùng trúng đòn rung lắc -> Cập nhật thanh máu anh hùng.
   - Nếu Máu Anh hùng <= 0 -> Thất bại (GameOver)!
   - Nếu Anh hùng còn sống -> Kết thúc vòng hiện tại (Round + 1). Bắt đầu vòng mới!

#### GIAI ĐOẠN 4: TIẾN TRÌNH VƯỢT ẢI, THĂNG CẤP & NGHỈ CHÂN (PROGRESSION)
1. **Sau khi thắng một ải:**
   - Người chơi nhận điểm kinh nghiệm từ `FloorCatalog.getEncounterExp(encounterIndex)`.
   - `LevelService.processExpGain(hero, expGained)`: Nếu đủ điểm thì thăng cấp, cộng chỉ số thuộc tính và tính lại Max HP/STA.
   - Người hùng được hồi phục tự động 20% HP và 40% STA (`AFTER_BATTLE_HP_RECOVERY_RATIO`).
2. **Ải 3 (Điểm nghỉ chân Lửa trại - Campfire):**
   - Sau khi hạ gục quái ở ải 3, người chơi được chuyển tới `RewardScreen` (Lửa trại).
   - Được nghỉ ngơi hồi 50% HP và 70% STA.
   - Có thể mở lại bảng kỹ năng để thay đổi 4 kỹ năng mang theo cho phù hợp với Boss sắp tới.
3. **Ải 5 (Trận đấu Boss cuối cùng):**
   - Đối đầu với Boss hầm ngục (ví dụ: Dread Cavalier Lord hoặc Obsidian Flame Golem).
   - Boss có lượng máu khủng, nhiều tuyệt kỹ diện rộng và cơ chế Cuồng Nộ (Enrage) tăng mạnh sát thương nếu trận đánh kéo dài quá 60 vòng.

#### GIAI ĐOẠN 5: KẾT THÚC HÀNH TRÌNH (END OF RUN)
1. **Chiến thắng tuyệt đối (Victory):**
   - Hạ gục Boss ở ải 5.
   - `ScreenManager` chuyển tới `ResultScreen` màu vàng kim vinh danh người chiến thắng, hiển thị tổng EXP đạt được, cấp độ và số vòng đấu.
   - Âm thanh chiến thắng vang lên.
2. **Thất bại (Defeat):**
   - Người hùng bị cạn máu ở bất kỳ ải nào.
   - `ResultScreen` hiển thị thông báo tử trận u ám, số ải đã vượt qua và số quái đã tiêu diệt.
3. Người chơi có thể bấm **"Play Again"** để quay về Menu tạo nhân vật mới, hoặc **"Exit"** để đóng ứng dụng.

---

## 3. PHÂN CÔNG VAI TRÒ CHI TIẾT CHO 5 THÀNH VIÊN

```
┌────────────────────────────────────────────────────────────────────────┐
│ PHÂN CHIA VAI TRÒ DỰ ÁN 5 NGƯỜI (CHUẨN CÂN BẰNG TẢI)                   │
├───────────────┬───────────────────────────────┬────────────┬───────────┤
│ THÀNH VIÊN    │ TRÁCH NHIỆM CHÍNH             │ GÓI (PKG)  │ DÒNG CODE │
├───────────────┼───────────────────────────────┼────────────┼───────────┤
│ Thành viên 1  │ Data Architect & Audio Lead   │ view/audio │ ~1.350    │
│ Thành viên 2  │ Combat Engine & AI Quái vật   │ combat     │ ~2.650    │
│ Thành viên 3  │ Game Flow & Sinh Hầm ngục     │ flow/dunge │ ~2.500    │
│ Thành viên 4  │ Retro UI Engine & Canvas      │ ui/asset   │ ~2.700    │
│ Thành viên 5  │ Animation System & Screen UX  │ anim/scree │ ~2.900    │
└───────────────┴───────────────────────────────┴────────────┴───────────┘
```

---

### 👤 THÀNH VIÊN 1 (Data Architect, Progression & Audio SFX)
* **Các gói phụ trách**:
  - `source.application.view.*` (`CombatantView`, `BattleView`, `DungeonView`, `SkillView`, `EffectView`, `AnimationEvent`, `MotionType`)
  - `source.application.setup.*` (`HeroFactory`, `LoadoutService`)
  - `source.application.progression.*` (`LevelService`)
  - `source.presentation.gui.audio.*` (`AudioService`, `AudioBackend`, `SynthBackend`, `AudioSettings`, `AudioMapper`, `SoundType`)
  - `source.app.Main.java`
* **Nhiệm vụ cụ thể**:
  1. Viết các DTOs trong `view.*`: Đóng gói các thuộc tính và hàm getter/setter bất biến (immutable) để truyền dữ liệu giữa tầng Logic và Giao diện.
  2. Viết `HeroFactory` và `LoadoutService`: Tạo anh hùng ban đầu và xác thực danh sách 4 kỹ năng người chơi chọn.
  3. Viết `LevelService`: Tính công thức tăng EXP (`level * 100`) và vòng lặp thăng cấp, cộng điểm chỉ số tăng trưởng.
  4. Viết `AudioService`: Hệ thống phát 11 hiệu ứng âm thanh SFX chiến đấu (tiếng kiếm chém, bạo kích, hồi máu, buff/debuff).
* **Tác dụng & Hiệu quả đối với game**:
  - Thành viên 1 là **Kiến trúc sư nền tảng**: Cung cấp DTOs - "ngôn ngữ chung" duy nhất để nhóm Logic và nhóm Giao diện nói chuyện với nhau.
  - Cung cấp âm thanh SFX giúp game không bị câm lặng, tạo cảm giác đòn đánh có uy lực.
  - Đóng vai trò Trưởng nhóm điều phối, giữ cho toàn dự án đồng bộ và build không bị lỗi.

---

### 👤 THÀNH VIÊN 2: COMBAT ENGINE & AI QUÁI VẬT
* **Các gói phụ trách**:
  - `source.application.combat.*` (`BattleSession`, `ActionService`, `EffectService`, `MonsterTactics`, `RoleTactics`, `DecisionService`, `RecoveryService`, `TurnReport`, `CombatFormatter`)
* **Nhiệm vụ cụ thể**:
  1. Viết `BattleSession`: Quản lý vòng đời trận đấu, đếm vòng round, so sánh tốc độ ai đi trước, kiểm tra thắng/thua.
  2. Viết `ActionService`: Xử lý hành động xuất chiêu, trừ thể lực (Stamina), gọi `DamageCalculator` tính sát thương, trừ máu/khiên.
  3. Viết `EffectService`: Trừ máu theo lượt khi trúng độc/bỏng, giảm số lượt duy trì của Buff/Debuff, xóa hiệu ứng khi hết hạn.
  4. Viết `RoleTactics` & `DecisionService`: Lập trình trí tuệ nhân tạo (AI) cho quái vật (quái DPS biết dồn dame, quái Tank biết bật khiên khi gần hết máu, Boss biết tung Ultimate).
* **Tác dụng & Hiệu quả đối với game**:
  - Là **Trái tim tính toán** của trò chơi: Quyết định toàn bộ tính công bằng, cân bằng chỉ số và sự kịch tính của trận đấu.
  - Giúp quái vật thông minh, biết phản ứng chiến thuật thay vì chỉ đánh thường một cách vô tri.

---

### 👤 THÀNH VIÊN 3: LUỒNG GAME, SINH HẦM NGỤC & DTO MAPPING
* **Các gói phụ trách**:
  - `source.application.dungeon.*` (`DungeonRun`, `FloorCatalog`)
  - `source.application.flow.*` (`GameFlow`, `BattleFlow`, `DungeonFlow`, `ViewMapper`, `EventMapper`, `MotionClassifier`)
* **Nhiệm vụ cụ thể**:
  1. Viết `FloorCatalog`: Cấu hình danh sách quái cho 4 hầm ngục phe phái, lượng EXP thưởng và tỷ lệ hồi máu sau mỗi ải.
  2. Viết `DungeonRun`: Quản lý tiến trình vượt 5 ải, ghi nhận số trận thắng và lưu trữ trạng thái chuyến thám hiểm.
  3. Viết `GameFlow`, `DungeonFlow`, `BattleFlow`: Máy trạng thái (State Machine) điều phối dữ liệu từ lúc ở Menu, chọn tướng, vào ngục, đánh quái đến lúc kết thúc.
  4. Viết `ViewMapper` & `EventMapper`: Lấy dữ liệu thực tế từ `Hero`, `Monster`, `TurnReport` và chuyển đổi sang các DTOs (`BattleView`, `AnimationEvent`) để gửi sang cho GUI hiển thị.
* **Tác dụng & Hiệu quả đối với game**:
  - Là **Cột sống điều phối**: Đảm bảo game vận hành trơn tru qua từng màn hình mà không bao giờ bị mất dữ liệu nhân vật.
  - Phân tách độc lập tầng Domain với GUI, giúp giao diện không bao giờ can thiệp trực tiếp làm hỏng dữ liệu gốc của nhân vật.

---

### 👤 THÀNH VIÊN 4: RETRO UI PIXEL ENGINE & CUSTOM CANVAS
* **Các gói phụ trách**:
  - `source.presentation.gui.ui.*` (`Theme`, `PixelBorders`, `PixelButtonUI`, `PixelBarUI`, `NineSlicePainter`, `CombatantRenderer`, `BackdropRenderer`, `SpriteResolver`, `StageGeometry`, `StageCanvas`, `HtmlLabels`)
  - `source.presentation.gui.asset.*` (`AssetProvider`, `AssetRegistry`)
* **Nhiệm vụ cụ thể**:
  1. Viết `Theme` & `PixelBorders`: Định nghĩa bảng màu chuẩn RPG cổ điển, font chữ pixel và viền trang trí.
  2. Viết `PixelButtonUI` & `PixelBarUI`: Custom lại giao diện nút bấm Swing và thanh máu/thể lực theo phong cách Pixel Art retro.
  3. Viết `NineSlicePainter`: Hiện thực kỹ thuật cắt 9 góc (9-slice) co giãn khung viền linh hoạt dựa trên dữ liệu từ `manifest.json`.
  4. Viết `CombatantRenderer` & `BackdropRenderer`: Vẽ hình ảnh sàn đấu hầm ngục, vẽ ảnh tướng và quái vật lên tọa độ màn hình.
  5. Viết `StageCanvas`: Sân khấu đồ họa chính kết hợp nền, nhân vật và lắng nghe các lệnh vẽ hoạt ảnh.
* **Tác dụng & Hiệu quả đối với game**:
  - Là **Bộ mặt thẩm mỹ** của trò chơi: Biến giao diện Java Swing mặc định vốn thô cứng trở thành một tựa game Pixel Art 2D đẹp mắt, chuyên nghiệp và hoài niệm.
  - Cung cấp các Widget tùy biến để Thành viên 5 chỉ việc kéo thả vào các màn hình.

---

### 👤 THÀNH VIÊN 5: ANIMATION SEQUENCER & MÀN HÌNH SCREEN UX
* **Các gói phụ trách**:
  - `source.presentation.gui.animation.*` (`Animation`, `BaseAnimation`, `FrameAnimation`, `FloatingText`, `FlashAnimation`, `ShakeAnimation`, `ImpactAnimation`, `LungeAnimation`, `ProjectileAnimation`, `PulseAnimation`, `ValueAnimation`, `AnimationStage`, `AnimationManager`, `TurnSequencer`)
  - `source.presentation.gui.screen.*` (`Screen`, `ScreenManager`, `MenuScreen`, `NameScreen`, `ClassScreen`, `DungeonScreen`, `SkillScreen`, `BattleScreen`, `RewardScreen`, `ResultScreen`, `SettingsScreen`)
  - `source.presentation.gui.app.GameApp.java`
* **Nhiệm vụ cụ thể**:
  1. Viết hệ thống Animation:
     - `FrameAnimation`: Cắt và chạy hoạt ảnh từng khung hình từ sprite sheet (đứng thở, chém, trúng đòn, gục ngã).
     - `FloatingText`: Hiệu ứng số sát thương/hồi máu bay lên và mờ dần.
     - `ShakeAnimation` & `FlashAnimation`: Rung lắc màn hình khi đánh chí mạng và chớp sáng khi xuất chiêu.
     - `TurnSequencer`: Bộ xếp hàng tuần tự (Đợi tướng chém -> quái giật lùi -> số dame bay lên -> thanh máu tụt).
  2. Viết toàn bộ các màn hình trong `screen.*`:
     - Ghép nối các nút bấm của TV4 thành các giao diện hoàn chỉnh: Menu, Nhập tên, Chọn tướng, Chọn ải, Chiến đấu, Lửa trại, Kết quả.
     - `ScreenManager`: Dùng `CardLayout` chuyển đổi mượt mà giữa các màn hình khi người chơi click chuột.
* **Tác dụng & Hiệu quả đối với game**:
  - Là **Linh hồn trải nghiệm**: Giúp trận chiến trở nên sống động, mượt mà ở 60 FPS thay vì các con số khô khan.
  - Tạo nên một hành trình chơi game hoàn chỉnh từ đầu đến cuối cho người dùng.

---

## 4. HƯỚNG DẪN TỰ KIỂM THỬ ĐỘC LẬP (KHÔNG PHỤ THUỘC NHAU)

Mỗi thành viên có thể tự viết hàm kiểm tra phần việc của mình độc lập 100%:

### Cách kiểm thử cho TV2 (Combat Engine):
Tạo một file test tạm thời (hoặc hàm `main`) chạy trực tiếp trên Console:
```java
// Tạo thử 1 tướng và 1 quái vật từ Domain đã có sẵn
Hero hero = HeroCatalogue.createHero(HeroId.WARRIOR, "Arthur");
Monster monster = MonsterCatalogue.create(MonsterId.SOLDIER, false);

BattleSession session = new BattleSession(hero, monster);
TurnReport startReport = session.start();
System.out.println("Bắt đầu trận: " + startReport.getMessages());

// Cho tướng dùng chiêu số 0
TurnReport attackReport = session.executePlayerActionOnly(0);
System.out.println("Kết quả đòn đánh: " + attackReport.getMessages());
System.out.println("Máu quái còn lại: " + monster.getStats().getCurrentHp());
```
👉 **Kết quả**: Không cần giao diện Swing, bạn vẫn biết chính xác logic tính dame của mình đúng hay sai!

### Cách kiểm thử cho TV4 & TV5 (Giao diện & Hoạt ảnh):
Tự tạo dữ liệu giả lập (Mock View) để vẽ thử màn hình ngay lập tức:
```java
// Tạo dữ liệu giả lập DTO
CombatantView mockHero = new CombatantView("Warrior", "warrior", 1, Elemental.HOLY, 100, 100, 50, 50, 0, List.of(), false);
CombatantView mockMonster = new CombatantView("Goblin", "goblin", 1, Elemental.CHAOS, 80, 80, 20, 20, 0, List.of(), false);

// Mở JFrame kiểm tra giao diện và hoạt ảnh
JFrame frame = new JFrame("Test Screen");
frame.setSize(960, 640);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

StageCanvas canvas = new StageCanvas(mockHero, mockMonster);
frame.add(canvas);
frame.setVisible(true);

// Kích hoạt thử 1 hoạt ảnh số dame bay lên xem có mượt không
canvas.playFloatingText("-45 CRIT!", 400, 200, Color.YELLOW);
```
👉 **Kết quả**: Không cần đợi TV2 làm xong logic, bạn vẫn vẽ được màn hình và làm xong toàn bộ hoạt ảnh 60 FPS!

---

## 5. LỆNH BIÊN DỊCH VÀ CHẠY GAME

### Lệnh kiểm tra toàn bộ mã nguồn (Build Check)
Mở terminal tại thư mục gốc dự án (`mythical_dungeon`) và chạy lệnh PowerShell sau:
```powershell
Get-ChildItem -Path source -Recurse -Filter *.java | ForEach-Object { $_.FullName.Replace('\', '/') } | Out-File -Encoding utf8 sources.txt
javac -d bin -Xlint:all -Werror @sources.txt
```
Nếu terminal kết thúc mà không in ra dòng chữ đỏ nào, tức là dự án đạt chuẩn **0 lỗi, 0 cảnh báo**!

### Lệnh chạy game
Sau khi biên dịch vào thư mục `bin`:
```powershell
java -cp "bin;resources" source.app.Main
```

---
*Tài liệu được biên soạn phục vụ đồ án môn học Lập trình hướng đối tượng (OOP).*
