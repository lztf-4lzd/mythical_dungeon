# NHIỆM VỤ CHI TIẾT: THÀNH VIÊN 5
## VAI TRÒ: ANIMATION SYSTEM & HỆ THỐNG MÀN HÌNH SCREEN UX LEAD
### DỰ ÁN: MYTHICAL DUNGEON — 2D TURN-BASED RPG

> **Lời nhắn**: Bạn là "đạo diễn điện ảnh và kiến trúc sư trải nghiệm người dùng (UX)". Bạn biến những con số khô khan thành những pha hành động mãn nhãn: hoạt ảnh lao lên chém, đạn ma thuật bay, chữ số dame nảy lên, rung lắc chấn động khi chí mạng. Đồng thời, bạn quản lý toàn bộ hệ thống chuyển đổi màn hình và cửa sổ game hoàn chỉnh (`GameApp`).

---

## 1. TỔNG QUAN PHÂN CÔNG
* **Khối lượng công việc**: ~2.900 dòng code.
* **Số lượng tệp**: 26 files Java.
* **Các thư mục phụ trách**:
  1. `source/presentation/gui/animation/` (14 files Hoạt ảnh)
  2. `source/presentation/gui/screen/` (11 files Màn hình)
  3. `source/presentation/gui/app/` (1 file GameApp)

### Danh sách tệp phụ trách:
* **Hoạt ảnh (`animation/`)**: `Animation.java`, `BaseAnimation.java`, `AnimationManager.java`, `AnimationStage.java`, `FrameAnimation.java`, `FloatingText.java`, `ShakeAnimation.java`, `LungeAnimation.java`, `ProjectileAnimation.java`, `ImpactAnimation.java`, `PulseAnimation.java`, `FlashAnimation.java`, `ValueAnimation.java`, `TurnSequencer.java`.
* **Màn hình (`screen/`)**: `Screen.java`, `ScreenManager.java`, `MenuScreen.java`, `NameScreen.java`, `ClassScreen.java`, `DungeonScreen.java`, `SkillScreen.java`, `BattleScreen.java`, `RewardScreen.java`, `ResultScreen.java`, `SettingsScreen.java`.
* **Cửa sổ Game (`app/`)**: `GameApp.java`.

---

## 2. QUY TẮC BẮT BUỘC KHI LÀM VIỆC
1. **API Freeze**: Giữ nguyên tên các phương thức trong `Screen` (`enter()`, `exit()`, `getScreenId()`) và `ScreenManager` (`showScreen(...)`, `registerScreen(...)`).
2. **Không khóa luồng Swing (UI Thread)**: Hoạt ảnh diễn ra bằng `javax.swing.Timer` chu kỳ 16ms (60 FPS). Tuyệt đối **không gọi `Thread.sleep(...)` bên trong luồng giao diện Swing**, vì sẽ làm đơ chuột và đứng hình toàn bộ ứng dụng!
3. **Tuân thủ quy trình TurnSequencer**: Người chơi ấn chiêu -> khóa tương tác nút bấm -> chạy hoạt ảnh -> va chạm trừ máu -> mở khóa nút bấm.

---

## 3. LỘ TRÌNH THỰC HIỆN TỪNG BƯỚC (ROADMAP)

```
[Bước 1: Timer & Manager] ──> [Bước 2: Diễn hoạt chi tiết] ──> [Bước 3: ScreenManager] ──> [Bước 4: Màn hình] ──> [Bước 5: GameApp]
    AnimationManager              FloatingText                    CardLayout                  BattleScreen          Cửa sổ 960x640
    TurnSequencer                 ShakeAnimation / Lunge                                      Menu / Name / v.v.    Hoàn tất Game
```

* **Bước 1**: Viết vòng lặp Timer 60 FPS trong `AnimationManager.java` và bộ lập trình sự kiện `TurnSequencer.java`.
* **Bước 2**: Hoàn thiện các class diễn hoạt: số dame bay `FloatingText`, rung màn hình `ShakeAnimation`, lao lên `LungeAnimation`, đạn bay `ProjectileAnimation`.
* **Bước 3**: Viết bộ quản lý chuyển cảnh `ScreenManager.java` sử dụng `CardLayout`.
* **Bước 4**: Viết các màn hình chức năng: `MenuScreen`, `NameScreen`, `ClassScreen`, `DungeonScreen`, `SkillScreen`, `RewardScreen`, `ResultScreen`, `SettingsScreen` và đặc biệt là màn hình chiến đấu lớn `BattleScreen.java`.
* **Bước 5**: Kết nối toàn bộ vào `GameApp.java`.

---

## 4. CHI TIẾT TỪNG CLASS VÀ HƯỚNG DẪN HIỆN THỰC

### PHẦN I: HỆ THỐNG HOẠT ẢNH (`animation/`)

#### 1. `AnimationManager.java` (Bộ điều phối thời gian 60 FPS)
* **Thuộc tính**:
  - `private final List<Animation> activeAnimations = new CopyOnWriteArrayList<>();`
  - `private final Timer timer;`
* **Cơ chế hoạt động**:
  - Khởi tạo Timer chạy mỗi 16 mili-giây (~60 khung hình/giây):
    ```java
    timer = new Timer(16, e -> update(0.016f));
    timer.start();
    ```
  - Trong hàm `update(float deltaTime)`:
    1. Duyệt qua `activeAnimations`: gọi `anim.update(deltaTime)`.
    2. Xóa các hoạt ảnh đã hoàn thành: `activeAnimations.removeIf(Animation::isFinished);`.
    3. Yêu cầu vẽ lại sàn đấu: `stage.repaint();`.

#### 2. `FloatingText.java` (Chữ số sát thương bay lên)
* **Mục đích**: Hiện số dame đỏ khi đánh thường, số dame vàng khi chí mạng, số xanh lá khi hồi máu, hoặc chữ "MISS" màu xám khi đánh trượt.
* **Cơ chế**:
  - Nhận vị trí ban đầu `(x, y)`, nội dung chữ `text`, và màu sắc `color`.
  - Mỗi nhịp 16ms:
    - Tọa độ `y -= speed * deltaTime;` (chữ bay từ từ lên trời).
    - Độ trong suốt `alpha -= fadeSpeed * deltaTime;` (mờ dần theo thời gian).
  - Hàm `render(Graphics2D g)`: Dùng `AlphaComposite` để vẽ chữ với độ mờ `alpha`. Khi `alpha <= 0`, đánh dấu `finished = true`.

#### 3. `ShakeAnimation.java` (Rung lắc mục tiêu khi bị đánh)
* **Cơ chế**:
  - Khi có đòn đánh trúng (đặc biệt là chí mạng), kích hoạt rung lắc trong 200ms.
  - Sinh tọa độ ngẫu nhiên lệch từ `-6` đến `+6` pixel:
    ```java
    offsetX = (int) ((Math.random() * 12 - 6) * (1.0f - progress));
    offsetY = (int) ((Math.random() * 12 - 6) * (1.0f - progress));
    ```
  - Truyền độ lệch `offsetX, offsetY` này vào hàm vẽ của `CombatantRenderer`.

#### 4. `LungeAnimation.java` (Lao lên chém cận chiến)
* **Cơ chế**:
  - Giai đoạn 1 (0% -> 50% thời gian): Nhân vật lướt nhanh về phía mục tiêu (`currentX` tăng dần).
  - Điểm va chạm (50% thời gian): Kích hoạt rung quái, nảy số dame, phát âm thanh SFX.
  - Giai đoạn 2 (50% -> 100% thời gian): Nhân vật lùi về vị trí đứng ban đầu.

#### 5. `TurnSequencer.java` (Xếp hàng trình chiếu lượt đánh)
* **Quy trình kịch bản điện ảnh**:
  1. Khóa toàn bộ nút bấm: `inputLock.accept(true);`.
  2. Bắt đầu hoạt ảnh ra chiêu (Lunge hoặc Bắn đạn).
  3. Khi đạn chạm mục tiêu: Kích hoạt `FloatingText`, `ShakeAnimation`, phát âm thanh SFX qua `AudioService`.
  4. Cập nhật lại thanh máu mới trên giao diện.
  5. Đợi hoạt ảnh kết thúc -> Mở khóa nút bấm: `inputLock.accept(false);`.

---

### PHẦN II: HỆ THỐNG MÀN HÌNH (`screen/`)

#### 6. `Screen.java` & `ScreenManager.java`
* **`Screen.java`**: Lớp cơ sở kế thừa `JPanel`.
  - Khai báo các phương thức vòng đời:
    - `public abstract String getScreenId();`
    - `public void enter() {}` (Chạy khi màn hình vừa được hiển thị)
    - `public void exit() {}` (Chạy khi màn hình bị ẩn đi)
* **`ScreenManager.java`**:
  - Sử dụng bố cục `CardLayout`.
  - `registerScreen(String id, Screen screen)`: Thêm màn hình vào bộ thẻ.
  - `showScreen(String id)`: Ẩn màn hình cũ (gọi `exit()`), chuyển sang màn hình mới (gọi `enter()`), và yêu cầu vẽ lại.

#### 7. Chi tiết các màn hình cụ thể:
* **`MenuScreen.java`**:
  - Gồm logo tựa game "MYTHICAL DUNGEON".
  - 3 nút bấm pixel: "CHƠI MỚI" -> chuyển sang `"NAME_ENTRY"`, "CÀI ĐẶT" -> `"SETTINGS"`, "THOÁT" -> thoát ứng dụng.
* **`NameScreen.java`**:
  - Nhập tên nhân vật qua `JTextField`. Giới hạn 12 ký tự. Nút "TIẾP TỤC" -> chuyển sang `"CLASS_SELECTION"`.
* **`ClassScreen.java`**:
  - Hiển thị 4 hệ nhân vật kèm ảnh thẻ và thông số:
    - *Đấu sĩ (Warrior)*: Máu cao, cận chiến vật lý.
    - *Pháp sư (Mage)*: Thể lực lớn, sát thương phép thuật Lửa/Băng.
    - *Hiệp sĩ (Paladin)*: Phòng thủ vững chắc, có bùa hồi máu và khiên.
    - *Xạ thủ (Archer)*: Tốc độ cao, bạo kích mạnh.
  - Chọn xong chuyển sang `"DUNGEON_SELECT"`.
* **`DungeonScreen.java`**:
  - Chọn 1 trong 3 hầm ngục: "Hầm Mộ Bất Tử", "Căn Cứ Orc", "Vực Thẳm Dung Nham".
  - Chuyển sang màn hình chọn kỹ năng `"SKILL_SELECT"`.
* **`SkillScreen.java`**:
  - Hiển thị toàn bộ danh sách kỹ năng của hệ nhân vật.
  - Người chơi chọn đúng 4 kỹ năng mang theo. Khi đủ 4 chiêu, nút "XUẤT TRẬN" sáng lên -> bắt đầu vào `"BATTLE"`.
* **`BattleScreen.java` (Màn hình trung tâm)**:
  - Phía trên: Tiêu đề Hầm ngục, tầng hiện tại (Ải 1/5 đến 5/5).
  - Ở giữa: Đặt `StageCanvas` hiển thị hình ảnh 2 nhân vật giao chiến.
  - Góc dưới bên trái: 4 nút bấm kỹ năng hiển thị tên chiêu, lượng thể lực tiêu tốn (STA), màu nguyên tố. Tự động mờ đi nếu nhân vật không đủ STA để dùng.
  - Góc dưới bên phải: Bảng chữ `JTextArea` nhật ký hiển thị các đòn đánh theo thời gian thực.
* **`RewardScreen.java` (Lửa trại Campfire)**:
  - Xuất hiện sau khi thắng mỗi ải từ 1 đến 4.
  - Cho người chơi nghỉ ngơi hồi phục một phần máu và thể lực, sau đó ấn nút "TIẾP TỤC TIẾN LÊN" để vào ải kế tiếp.
* **`ResultScreen.java` (Tổng kết Thắng / Thua)**:
  - Nếu thắng ải 5: Chữ VÀNG KIM chúc mừng chiến thắng, hiển thị tổng EXP đạt được.
  - Nếu thua: Chữ ĐỎ báo tử sĩ hy sinh.
  - Có nút "VỀ MENU CHÍNH" để chơi lại từ đầu.

---

### PHẦN III: CỬA SỔ CHÍNH (`app/GameApp.java`)

#### 8. `GameApp.java`
* **Mục đích**: Là cửa sổ ứng dụng Swing hoàn chỉnh.
* **Cấu hình cửa sổ**:
  - Kích thước chuẩn: `960 x 640` pixel, không cho phép co giãn (`setResizable(false)`).
  - Tiêu đề: `"Mythical Dungeon - 2D Turn-Based RPG"`.
  - Tự động canh giữa màn hình: `setLocationRelativeTo(null);`.
* **Phương thức `public void start()`**:
  1. Khởi tạo `ScreenManager`.
  2. Đăng ký đầy đủ 10 màn hình vào ScreenManager.
  3. Gọi `screenManager.showScreen("MAIN_MENU");`.
  4. Gắn container của ScreenManager vào JFrame và gọi `setVisible(true);`.

---

## 5. CODE MẪU TỰ KIỂM THỬ ĐỘC LẬP CHO THÀNH VIÊN 5

Tạo file `TestMember5.java` tại thư mục gốc để mở một cửa sổ thử nghiệm hoạt ảnh và chuyển màn hình:

```java
import javax.swing.*;
import java.awt.*;
import source.presentation.gui.screen.ScreenManager;
import source.presentation.gui.screen.MenuScreen;
import source.presentation.gui.ui.Theme;

public class TestMember5 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("KIỂM THỬ SCREEN MANAGER & UX - THÀNH VIÊN 5");
            frame.setSize(960, 640);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);

            // 1. Khởi tạo ScreenManager
            ScreenManager screenManager = new ScreenManager();

            // 2. Đăng ký MenuScreen
            MenuScreen menu = new MenuScreen(screenManager);
            screenManager.registerScreen("MAIN_MENU", menu);
            screenManager.showScreen("MAIN_MENU");

            frame.add(screenManager.getContainer());
            frame.setVisible(true);

            System.out.println("[OK] Hệ thống màn hình ScreenManager đã chạy thành công!");
        });
    }
}
```

---

## 6. LỆNH BIÊN DỊCH VÀ CHẠY THỬ
```bash
javac -d bin -cp . source/presentation/gui/animation/*.java source/presentation/gui/screen/*.java source/presentation/gui/app/*.java
```
Đảm bảo biên dịch xanh sạch 100% không lỗi!
