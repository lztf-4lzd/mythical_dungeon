# NHIỆM VỤ CHI TIẾT: THÀNH VIÊN 4
## VAI TRÒ: RETRO UI PIXEL ENGINE & CANVAS SÀN ĐẤU
### DỰ ÁN: MYTHICAL DUNGEON — 2D TURN-BASED RPG

> **Lời nhắn**: Bạn là "họa sĩ và kỹ sư đồ họa" của trò chơi. Bạn chịu trách nhiệm tạo nên vẻ đẹp hoài niệm Retro Pixel Art: nạp tài nguyên hình ảnh, xây dựng bảng màu giao diện, vẽ viền 9-slice, tùy biến các nút bấm và thanh máu kiểu cổ điển, và đặc biệt là vẽ nên sàn đấu ngục tối `StageCanvas` sống động.

---

## 1. TỔNG QUAN PHÂN CÔNG
* **Khối lượng công việc**: ~2.700 dòng code.
* **Số lượng tệp**: 14 files Java.
* **Các thư mục phụ trách**:
  1. `source/presentation/gui/asset/` (4 files Asset)
  2. `source/presentation/gui/ui/` (10 files UI & Canvas)
* **Danh sách tệp phụ trách**:
  1. `AssetLoader.java`
  2. `AssetProvider.java`
  3. `AssetRegistry.java`
  4. `SpriteResolver.java`
  5. `Theme.java`
  6. `PixelBorders.java`
  7. `NineSlicePainter.java`
  8. `PixelButtonUI.java`
  9. `PixelBarUI.java`
  10. `HtmlLabels.java`
  11. `BackdropRenderer.java`
  12. `CombatantRenderer.java`
  13. `StageGeometry.java`
  14. `StageCanvas.java`

---

## 2. QUY TẮC BẮT BUỘC KHI LÀM VIỆC
1. **API Freeze**: Giữ nguyên chữ ký hàm trong các class skeleton, đặc biệt là `AssetLoader.getImage(...)`, `AssetLoader.getHeroSprite(...)`, `AssetLoader.getMonsterSprite(...)`, và các phương thức vẽ của `StageCanvas`.
2. **Quản lý bộ nhớ hình ảnh (Caching)**: Nạp ảnh bằng `ImageIO.read(...)` là tác vụ nặng. Phải lưu vào bộ nhớ đệm `Map<String, BufferedImage>` trong `AssetRegistry`, không đọc lại đĩa mỗi khung hình.
3. **Thẩm mỹ Retro**: Không sử dụng gradient khử răng cưa mượt mà của Swing hiện đại. Mọi đường viền, thanh máu, nút bấm phải thể hiện đúng phong cách Pixel Art 16-bit.

---

## 3. LỘ TRÌNH THỰC HIỆN TỪNG BƯỚC (ROADMAP)

```
[Bước 1: Asset Loader] ──> [Bước 2: Theme & Border] ──> [Bước 3: Custom UI] ──> [Bước 4: Renderer & Canvas]
     AssetLoader                 Theme                      PixelButtonUI              CombatantRenderer
     SpriteResolver              NineSlicePainter           PixelBarUI                 StageCanvas
```

* **Bước 1**: Viết bộ nạp và cache hình ảnh `AssetLoader.java`, `AssetRegistry.java`, và bộ định vị sprite `SpriteResolver.java`.
* **Bước 2**: Định nghĩa bảng màu `Theme.java` và thuật toán vẽ co giãn 9 góc `NineSlicePainter.java`.
* **Bước 3**: Tùy biến giao diện Swing: nút bấm pixel `PixelButtonUI.java` và thanh máu pixel `PixelBarUI.java`.
* **Bước 4**: Viết các bộ vẽ nhân vật `CombatantRenderer.java`, vẽ nền `BackdropRenderer.java`, và ghép nối thành sàn đấu `StageCanvas.java`.

---

## 4. CHI TIẾT TỪNG CLASS VÀ HƯỚNG DẪN HIỆN THỰC

### 1. `AssetLoader.java` & `AssetRegistry.java` (Bộ nạp tài nguyên)
* **Mục đích**: Nạp ảnh từ thư mục `resources/` hoặc classpath và lưu vào cache.
* **`AssetRegistry.java`**:
  - Chứa bộ nhớ đệm: `private static final Map<String, BufferedImage> imageCache = new ConcurrentHashMap<>();`.
  - Cung cấp hàm `put(String path, BufferedImage img)` và `get(String path)`.
* **`AssetLoader.java`**:
  - `public static BufferedImage loadImage(String relativePath)`:
    1. Kiểm tra trong cache: Nếu đã có, trả về ngay lập tức.
    2. Nạp file từ đường dẫn dự án hoặc qua `ClassLoader.getResourceAsStream(...)`.
    3. Đọc ảnh qua `ImageIO.read(stream)`.
    4. Lưu vào cache và trả về kết quả.
    5. Nếu file không tồn tại hoặc lỗi: Sinh ra một ảnh màu tím dự phòng (`placeholder image`) kích thước 32x32 để không làm sập ứng dụng.

---

### 2. `SpriteResolver.java` (Bộ định vị đường dẫn ảnh)
* **Phương thức `public static String resolveHeroSpritePath(CombatantView hero)`**:
  - Trả về đường dẫn: `"assets/heroes/" + hero.getClassName().toLowerCase() + "_idle.png"`.
* **Phương thức `public static String resolveMonsterSpritePath(CombatantView monster)`**:
  - Chuẩn hóa tên quái vật thành tên file trong `assets/monsters/`:
    - Ví dụ: `"Dread Cavalier Lord"` -> `"assets/monsters/dread_cavalier_lord.png"`.
    - `"Skeleton"` -> `"assets/monsters/skeleton.png"`.

---

### 3. `Theme.java` & `PixelBorders.java` (Bảng màu & Đường viền Retro)
* **`Theme.java`**: Khai báo các hằng số màu sắc phong cách Pixel RPG:
  - Màu nền tối ngục: `public static final Color BG_DARK = new Color(24, 20, 37);`
  - Màu khung panel: `public static final Color PANEL_BG = new Color(38, 35, 58);`
  - Màu thanh máu HP: `public static final Color COLOR_HP = new Color(214, 58, 58);`
  - Màu thanh thể lực STA: `public static final Color COLOR_STA = new Color(58, 166, 214);`
  - Màu thanh khiên Shield: `public static final Color COLOR_SHIELD = new Color(180, 180, 200);`
  - Màu chữ vàng kim: `public static final Color TEXT_GOLD = new Color(245, 210, 80);`
  - Font chữ mặc định: `Font.MONOSPACED`, in đậm, cỡ chữ 12 - 16.

---

### 4. `NineSlicePainter.java` (Vẽ co giãn 9 góc khung viền)
* **Mục đích**: Vẽ khung cửa sổ/panel co giãn theo bất kỳ kích thước nào mà 4 góc không bị nhòe nét vỡ hạt.
* **Thuật toán 9-Slice**:
  - Chia tấm ảnh mẫu thành 9 phần:
    - 4 góc (Góc trên-trái, trên-phải, dưới-trái, dưới-phải): Vẽ nguyên kích thước cố định.
    - 4 cạnh (Cạnh trên, dưới, trái, phải): Co giãn theo chiều rộng/cao tương ứng.
    - 1 phần thân ở giữa: Lấp đầy toàn bộ diện tích còn lại.
  - Sử dụng hàm `Graphics.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null)`.

---

### 5. `PixelButtonUI.java` & `PixelBarUI.java` (Custom UI Swing)
* **`PixelButtonUI.java`**:
  - Kế thừa `BasicButtonUI`.
  - Override `paint(Graphics g, JComponent c)`:
    - Vẽ nền phẳng bằng màu retro (`Theme.PANEL_BG`).
    - Khi hover chuột (`model.isRollover()`): Làm sáng màu nền lên 10%.
    - Khi click chuột (`model.isPressed()`): Dịch chuyển chữ xuống dưới 1 pixel để tạo cảm giác nhấn thật.
    - Vẽ viền đen đôi 2 pixel quanh nút bấm.
* **`PixelBarUI.java`**:
  - Kế thừa `BasicProgressBarUI`.
  - Override `paintDeterminate(Graphics g, JComponent c)`:
    - Vẽ viền đôi pixel đen.
    - Vẽ phần ruột thể hiện tỷ lệ % máu/thể lực bằng các khối màu pixel đặc, không dùng hiệu ứng bóng mờ gradient.

---

### 6. `CombatantRenderer.java` & `BackdropRenderer.java` (Vẽ đồ họa sàn đấu)
* **`BackdropRenderer.java`**:
  - Vẽ ảnh nền ngục tối `assets/backgrounds/dungeon_stage.png` trải rộng toàn bộ kích thước sàn đấu.
* **`CombatantRenderer.java`**:
  - `renderHero(Graphics2D g, CombatantView hero, int x, int y, int offsetX, int offsetY)`:
    1. Vẽ bóng đen hình bầu dục dưới chân: `g.setColor(new Color(0, 0, 0, 100)); g.fillOval(x + 10, y + 80, 60, 20);`.
    2. Nạp ảnh sprite tướng qua `AssetLoader`.
    3. Vẽ ảnh tướng tại tọa độ `(x + offsetX, y + offsetY)`. Độ lệch `offsetX, offsetY` dùng để tạo hiệu ứng khi tướng bị đánh trúng rung lắc.
  - `renderMonster(...)`:
    - Vẽ tương tự cho Quái vật ở phía đối diện (bên phải). Lật ảnh quái theo chiều ngang (Flip Horizontal) nếu cần để quái luôn quay mặt nhìn về phía Anh hùng.

---

### 7. `StageCanvas.java` (Sân khấu sàn đấu chính)
* **Mục đích**: Là một `JPanel` hiển thị toàn bộ trận đấu ở giữa màn hình `BattleScreen`.
* **Phương thức `protected void paintComponent(Graphics g)`**:
  1. `super.paintComponent(g);`
  2. Ép kiểu sang `Graphics2D g2d = (Graphics2D) g;`.
  3. Bật chế độ vẽ pixel sắc nét: `g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);`.
  4. Vẽ hình nền ngục qua `BackdropRenderer`.
  5. Vẽ Anh hùng và Quái vật qua `CombatantRenderer`.
  6. Vẽ các hoạt ảnh bổ trợ (đạn bay, chữ nổi, tia sét do Thành viên 5 quản lý).

---

## 5. CODE MẪU TỰ KIỂM THỬ ĐỘC LẬP CHO THÀNH VIÊN 4

Tạo file `TestMember4.java` tại thư mục gốc để mở một cửa sổ giao diện kiểm tra các nút bấm và thanh máu phong cách Pixel:

```java
import javax.swing.*;
import java.awt.*;
import source.presentation.gui.ui.Theme;
import source.presentation.gui.ui.PixelButtonUI;
import source.presentation.gui.ui.PixelBarUI;

public class TestMember4 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("KIỂM THỬ GIAO DIỆN RETRO PIXEL - THÀNH VIÊN 4");
            frame.setSize(500, 350);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setBackground(Theme.BG_DARK);
            panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

            JLabel title = new JLabel("BẢNG ĐIỀU KHIỂN RETRO PIXEL");
            title.setForeground(Theme.TEXT_GOLD);
            title.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
            title.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(title);
            panel.add(Box.createVerticalStrut(20));

            // 1. Thanh máu HP Pixel
            JLabel hpLabel = new JLabel("MÁU (HP): 75/100");
            hpLabel.setForeground(Color.WHITE);
            hpLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
            panel.add(hpLabel);

            JProgressBar hpBar = new JProgressBar(0, 100);
            hpBar.setValue(75);
            hpBar.setUI(new PixelBarUI(Theme.COLOR_HP));
            hpBar.setPreferredSize(new Dimension(300, 20));
            panel.add(hpBar);
            panel.add(Box.createVerticalStrut(15));

            // 2. Thanh thể lực STA Pixel
            JLabel staLabel = new JLabel("THỂ LỰC (STA): 40/60");
            staLabel.setForeground(Color.WHITE);
            staLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
            panel.add(staLabel);

            JProgressBar staBar = new JProgressBar(0, 60);
            staBar.setValue(40);
            staBar.setUI(new PixelBarUI(Theme.COLOR_STA));
            staBar.setPreferredSize(new Dimension(300, 20));
            panel.add(staBar);
            panel.add(Box.createVerticalStrut(25));

            // 3. Nút bấm Pixel UI
            JButton attackBtn = new JButton("CHÉM KIẾM CƠ BẢN (0 STA)");
            attackBtn.setUI(new PixelButtonUI());
            attackBtn.setForeground(Theme.TEXT_GOLD);
            attackBtn.setFont(new Font(Font.MONOSPACED, Font.BOLD, 13));
            attackBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(attackBtn);

            frame.add(panel);
            frame.setVisible(true);
            System.out.println("[OK] Cửa sổ kiểm thử đồ họa Pixel đã khởi chạy thành công!");
        });
    }
}
```

---

## 6. LỆNH BIÊN DỊCH VÀ CHẠY THỬ
```bash
javac -d bin -cp . source/presentation/gui/asset/*.java source/presentation/gui/ui/*.java
```
Đảm bảo biên dịch thành công 100% không có lỗi!
