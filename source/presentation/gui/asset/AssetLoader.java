package source.presentation.gui.asset;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Trung tâm tải và quản lý bộ nhớ đệm (cache) tài nguyên hình ảnh cho giao diện game.
 * Hỗ trợ nạp từ classpath/thư mục tệp cục bộ, co giãn nearest-neighbor giữ độ nét pixel art,
 * sinh ảnh dự phòng khi thiếu tệp và đọc siêu dữ liệu 9-slice từ manifest.json.
 */
public final class AssetLoader {

    /** Bộ nhớ đệm ảnh tĩnh theo đường dẫn đã chuẩn hóa. */
    private static final Map<String, BufferedImage> IMAGE_CACHE = new ConcurrentHashMap<>();

    /** Bộ nhớ đệm biểu tượng ImageIcon đã co giãn theo khóa [đường dẫn@kích thước]. */
    private static final Map<String, ImageIcon> ICON_CACHE = new ConcurrentHashMap<>();

    /** Bộ nhớ đệm danh sách khung hình cắt từ sprite sheet ngang. */
    private static final Map<String, List<BufferedImage>> SHEET_CACHE = new ConcurrentHashMap<>();

    /** Bộ nhớ đệm siêu dữ liệu tài nguyên UI đọc từ manifest.json. */
    private static final Map<String, UiAsset> UI_MANIFEST_CACHE = new ConcurrentHashMap<>();

    /** Bộ nhớ đệm ảnh thành phần UI theo mã định danh và trạng thái. */
    private static final Map<String, BufferedImage> UI_IMAGE_CACHE = new ConcurrentHashMap<>();

    /** Cờ đánh dấu tệp manifest.json đã được nạp hay chưa. */
    private static volatile boolean uiManifestLoaded = false;

    private AssetLoader() {}

    /**
     * Siêu dữ liệu và cấu hình lề cắt 9-slice của một thành phần UI.
     *
     * @param logicWidth chiều rộng gốc của thành phần (pixel)
     * @param logicHeight chiều cao gốc của thành phần (pixel)
     * @param scale hệ số phóng đại của thành phần
     * @param sliceTop lề cắt cạnh trên
     * @param sliceLeft lề cắt cạnh trái
     * @param sliceBottom lề cắt cạnh dưới
     * @param sliceRight lề cắt cạnh phải
     * @param filePath đường dẫn tệp ảnh mặc định
     * @param states bảng ánh xạ tên trạng thái (normal, hover, pressed...) sang đường dẫn ảnh
     */
    public record UiAsset(
            int logicWidth,
            int logicHeight,
            int scale,
            int sliceTop,
            int sliceLeft,
            int sliceBottom,
            int sliceRight,
            String filePath,
            Map<String, String> states
    ) {
        /** Kiểm tra thành phần UI có cấu hình viền 9-slice hay không. */
        public boolean hasSliceInsets() {
            return sliceTop > 0 || sliceLeft > 0 || sliceBottom > 0 || sliceRight > 0;
        }

        /** Tính độ rộng lề trên thực tế dựa trên kích thước ảnh nạp vào. */
        public int computeImageSliceTop(int imageWidth) {
            return (scale > 1 && imageWidth >= logicWidth * scale) ? sliceTop * scale : sliceTop;
        }

        /** Tính độ rộng lề trái thực tế dựa trên kích thước ảnh nạp vào. */
        public int computeImageSliceLeft(int imageWidth) {
            return (scale > 1 && imageWidth >= logicWidth * scale) ? sliceLeft * scale : sliceLeft;
        }

        /** Tính độ rộng lề dưới thực tế dựa trên kích thước ảnh nạp vào. */
        public int computeImageSliceBottom(int imageHeight) {
            return (scale > 1 && imageHeight >= logicHeight * scale) ? sliceBottom * scale : sliceBottom;
        }

        /** Tính độ rộng lề phải thực tế dựa trên kích thước ảnh nạp vào. */
        public int computeImageSliceRight(int imageWidth) {
            return (scale > 1 && imageWidth >= logicWidth * scale) ? sliceRight * scale : sliceRight;
        }
    }

    /**
     * Tải ảnh tĩnh từ bộ nhớ đệm, classpath hoặc thư mục tệp; trả về ảnh dự phòng nếu không tìm thấy.
     *
     * @param path đường dẫn tới tệp ảnh (ví dụ: "assets/heroes/warrior.png")
     * @return ảnh {@link BufferedImage} hợp lệ hoặc ảnh dự phòng nếu lỗi/thiếu file
     */
    public static BufferedImage getImage(String path) {
        if (path == null || path.trim().isEmpty()) {
            System.err.println("[AssetLoader] CẢNH BÁO: Đường dẫn rỗng, trả về ảnh dự phòng.");
            return createFallbackPlaceholder(32, 32, null, "?");
        }

        String cleanPath = normalizePath(path);
        BufferedImage cached = IMAGE_CACHE.get(cleanPath);
        if (cached != null) {
            return cached;
        }

        BufferedImage loaded = loadResource(cleanPath);
        if (loaded != null) {
            IMAGE_CACHE.put(cleanPath, loaded);
            return loaded;
        }

        System.err.println("[AssetLoader] CẢNH BÁO: Không tìm thấy tài nguyên: " + path + " - dùng ảnh dự phòng.");
        return createFallbackPlaceholder(32, 32, null, "?");
    }

    /**
     * Kiểm tra ảnh tại đường dẫn chỉ định đã có trong bộ nhớ đệm hay chưa.
     *
     * @param path đường dẫn tài nguyên
     * @return {@code true} nếu ảnh đã được nạp sẵn trong cache
     */
    public static boolean isImageCached(String path) {
        if (path == null) {
            return false;
        }
        return IMAGE_CACHE.containsKey(normalizePath(path));
    }

    /**
     * Tải sprite sheet ngang và cắt thành danh sách các khung hình (frames) có kích thước đồng đều.
     *
     * @param path đường dẫn tệp sprite sheet
     * @param frameWidth chiều rộng của từng khung hình (pixel)
     * @param frameHeight chiều cao của từng khung hình (pixel)
     * @return danh sách bất biến chứa các khung hình {@link BufferedImage}
     */
    public static List<BufferedImage> loadSpriteSheet(String path, int frameWidth, int frameHeight) {
        if (path == null || path.trim().isEmpty() || frameWidth <= 0 || frameHeight <= 0) {
            return Collections.emptyList();
        }

        String cleanPath = normalizePath(path);
        List<BufferedImage> cached = SHEET_CACHE.get(cleanPath);
        if (cached != null) {
            return cached;
        }

        BufferedImage sheetImage = loadResource(cleanPath);
        if (sheetImage == null) {
            return Collections.emptyList();
        }

        int sheetWidth = sheetImage.getWidth();
        int sheetHeight = sheetImage.getHeight();
        if (sheetWidth < frameWidth || sheetHeight < frameHeight) {
            return Collections.emptyList();
        }

        int frameCount = sheetWidth / frameWidth;
        List<BufferedImage> frames = new ArrayList<>(frameCount);
        for (int i = 0; i < frameCount; i++) {
            BufferedImage frame = sheetImage.getSubimage(i * frameWidth, 0, frameWidth, frameHeight);
            frames.add(frame);
        }

        List<BufferedImage> unmodifiable = Collections.unmodifiableList(frames);
        SHEET_CACHE.put(cleanPath, unmodifiable);
        return unmodifiable;
    }

    /**
     * Lấy biểu tượng {@link ImageIcon} co giãn theo kích thước chỉ định bằng thuật toán nearest-neighbor.
     *
     * @param path đường dẫn tài nguyên hình ảnh
     * @param targetWidth chiều rộng mong muốn (pixel > 0)
     * @param targetHeight chiều cao mong muốn (pixel > 0)
     * @return biểu tượng {@link ImageIcon} đã co giãn chuẩn
     */
    public static ImageIcon getScaledIcon(String path, int targetWidth, int targetHeight) {
        if (targetWidth <= 0 || targetHeight <= 0) {
            throw new IllegalArgumentException("Kích thước targetWidth và targetHeight phải lớn hơn 0: "
                    + targetWidth + "x" + targetHeight);
        }

        String cleanPath = (path != null) ? normalizePath(path) : "";
        String cacheKey = cleanPath + "@" + targetWidth + "x" + targetHeight;
        ImageIcon cached = ICON_CACHE.get(cacheKey);
        if (cached != null) {
            return cached;
        }

        BufferedImage base = getImage(path);
        BufferedImage scaled;
        if (base.getWidth() == targetWidth && base.getHeight() == targetHeight) {
            scaled = base;
        } else {
            scaled = scaleNearestNeighbor(base, targetWidth, targetHeight);
        }

        ImageIcon icon = new ImageIcon(scaled);
        if (!cleanPath.isEmpty() && IMAGE_CACHE.containsKey(cleanPath)) {
            ICON_CACHE.put(cacheKey, icon);
        }
        return icon;
    }

    /**
     * Co giãn ảnh theo kích thước mới bằng nội suy Nearest-Neighbor để giữ độ sắc nét phong cách pixel art.
     *
     * @param sourceImage ảnh gốc cần phóng to/thu nhỏ
     * @param targetWidth chiều rộng đích (pixel > 0)
     * @param targetHeight chiều cao đích (pixel > 0)
     * @return ảnh {@link BufferedImage} mới sau khi co giãn
     */
    public static BufferedImage scaleNearestNeighbor(BufferedImage sourceImage, int targetWidth, int targetHeight) {
        if (targetWidth <= 0 || targetHeight <= 0) {
            throw new IllegalArgumentException("Kích thước targetWidth và targetHeight phải lớn hơn 0: "
                    + targetWidth + "x" + targetHeight);
        }
        if (sourceImage == null) {
            return createFallbackPlaceholder(targetWidth, targetHeight, null, "?");
        }

        BufferedImage dest = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = dest.createGraphics();
        try {
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
            g2d.drawImage(sourceImage, 0, 0, targetWidth, targetHeight, null);
        } finally {
            g2d.dispose();
        }
        return dest;
    }

    /**
     * Tạo một ảnh dự phòng độc lập với màu nền và nhãn chữ ở giữa, tránh sập giao diện khi thiếu asset.
     *
     * @param width chiều rộng ảnh (pixel > 0)
     * @param height chiều cao ảnh (pixel > 0)
     * @param backgroundColor màu nền tùy chọn (mặc định xám đậm nếu null)
     * @param label nhãn chữ vẽ ở giữa ảnh
     * @return ảnh dự phòng {@link BufferedImage}
     */
    public static BufferedImage createFallbackPlaceholder(int width, int height, Color backgroundColor, String label) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Kích thước ảnh dự phòng phải lớn hơn 0: " + width + "x" + height);
        }

        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        try {
            Color bg = (backgroundColor != null) ? backgroundColor : new Color(38, 42, 58);
            g.setColor(bg);
            g.fillRect(0, 0, width, height);

            // Viền đôi
            g.setColor(new Color(90, 95, 120));
            g.drawRect(0, 0, width - 1, height - 1);
            if (width > 4 && height > 4) {
                g.setColor(new Color(60, 65, 85));
                g.drawRect(1, 1, width - 3, height - 3);
            }

            // Chữ hiển thị
            if (label != null && !label.trim().isEmpty() && width >= 16 && height >= 14) {
                g.setColor(Color.WHITE);
                int fontSize = Math.max(9, Math.min(14, height / 2));
                g.setFont(new Font("SansSerif", Font.BOLD, fontSize));
                FontMetrics fm = g.getFontMetrics();
                int textX = Math.max(2, (width - fm.stringWidth(label)) / 2);
                int textY = Math.max(fm.getAscent(), (height + fm.getAscent() - fm.getDescent()) / 2);
                g.drawString(label, textX, textY);
            } else if (width >= 8 && height >= 8) {
                g.setColor(new Color(75, 80, 105));
                g.drawLine(0, 0, width - 1, height - 1);
                g.drawLine(0, height - 1, width - 1, 0);
            }
        } finally {
            g.dispose();
        }
        return img;
    }

    /**
     * Tra cứu siêu dữ liệu của thành phần UI theo mã định danh trong manifest.json (ví dụ: "btn_primary").
     *
     * @param assetId mã thành phần UI
     * @return bản ghi {@link UiAsset} hoặc {@code null} nếu không tìm thấy
     */
    public static UiAsset getUiAsset(String assetId) {
        if (assetId == null) {
            return null;
        }
        ensureUiManifestLoaded();
        UiAsset asset = UI_MANIFEST_CACHE.get(assetId);
        if (asset == null) {
            asset = UI_MANIFEST_CACHE.get(assetId.toLowerCase(Locale.ROOT));
        }
        return asset;
    }

    /**
     * Kiểm tra một mã thành phần UI có tồn tại trong manifest hay không.
     *
     * @param assetId mã thành phần UI
     * @return {@code true} nếu có định nghĩa trong manifest
     */
    public static boolean hasUiAsset(String assetId) {
        if (assetId == null) {
            return false;
        }
        ensureUiManifestLoaded();
        return UI_MANIFEST_CACHE.containsKey(assetId)
                || UI_MANIFEST_CACHE.containsKey(assetId.toLowerCase(Locale.ROOT));
    }

    /**
     * Lấy ảnh gắn với mã tài nguyên UI hoặc đường dẫn tệp ảnh trực tiếp.
     *
     * @param assetIdOrPath mã tài nguyên UI hoặc đường dẫn ảnh
     * @return ảnh đã nạp hoặc ảnh dự phòng
     */
    public static BufferedImage getUiImage(String assetIdOrPath) {
        if (assetIdOrPath == null || assetIdOrPath.trim().isEmpty()) {
            return createFallbackPlaceholder(32, 32, null, "?");
        }
        String clean = normalizePath(assetIdOrPath);
        BufferedImage cached = UI_IMAGE_CACHE.get(clean);
        if (cached != null) {
            return cached;
        }

        UiAsset asset = getUiAsset(assetIdOrPath);
        BufferedImage loaded;
        if (asset != null && asset.filePath() != null) {
            loaded = getImage(asset.filePath());
        } else {
            loaded = getImage(assetIdOrPath);
        }

        if (loaded != null) {
            UI_IMAGE_CACHE.put(clean, loaded);
            return loaded;
        }
        return createFallbackPlaceholder(32, 32, null, "?");
    }

    /**
     * Lấy ảnh theo trạng thái cụ thể của thành phần UI (ví dụ: "normal", "hover", "pressed", "disabled").
     *
     * @param assetId mã tài nguyên UI
     * @param stateName tên trạng thái cần lấy
     * @return ảnh theo trạng thái chỉ định hoặc ảnh mặc định/dự phòng
     */
    public static BufferedImage getUiImage(String assetId, String stateName) {
        if (stateName == null || stateName.trim().isEmpty()) {
            return getUiImage(assetId);
        }
        if (assetId == null || assetId.trim().isEmpty()) {
            return createFallbackPlaceholder(32, 32, null, "?");
        }

        String cacheKey = normalizePath(assetId) + "#" + stateName.trim().toLowerCase(Locale.ROOT);
        BufferedImage cached = UI_IMAGE_CACHE.get(cacheKey);
        if (cached != null) {
            return cached;
        }

        UiAsset asset = getUiAsset(assetId);
        if (asset != null && asset.states() != null) {
            String statePath = asset.states().get(stateName.toLowerCase(Locale.ROOT));
            if (statePath != null) {
                BufferedImage loaded = getImage(statePath);
                if (loaded != null) {
                    UI_IMAGE_CACHE.put(cacheKey, loaded);
                    return loaded;
                }
            }
        }

        return getUiImage(assetId);
    }

    /** Xóa toàn bộ dữ liệu trong các bộ nhớ đệm. */
    public static void clearAllCaches() {
        IMAGE_CACHE.clear();
        ICON_CACHE.clear();
        SHEET_CACHE.clear();
        UI_IMAGE_CACHE.clear();
        UI_MANIFEST_CACHE.clear();
        uiManifestLoaded = false;
    }

    // =========================================================================
    // Các phương thức hỗ trợ nội bộ (Private Helpers)
    // =========================================================================

    private static String normalizePath(String path) {
        String p = path.trim().replace('\\', '/');
        while (p.startsWith("/")) {
            p = p.substring(1);
        }
        return p;
    }

    private static void ensureUiManifestLoaded() {
        if (!uiManifestLoaded) {
            synchronized (UI_MANIFEST_CACHE) {
                if (!uiManifestLoaded) {
                    loadUiManifest();
                    uiManifestLoaded = true;
                }
            }
        }
    }

    private static void loadUiManifest() {
        UI_MANIFEST_CACHE.clear();
        String manifestContent = readManifestResource("resources/assets/ui/manifest.json");
        if (manifestContent == null) {
            manifestContent = readManifestResource("assets/ui/manifest.json");
        }
        if (manifestContent == null) {
            return;
        }

        try {
            Object parsed = SimpleJsonParser.parse(manifestContent);
            if (parsed instanceof Map<?, ?> root) {
                Object assetsObj = root.get("assets");
                if (assetsObj instanceof List<?> list) {
                    for (Object item : list) {
                        if (item instanceof Map<?, ?> assetMap) {
                            String id = (String) assetMap.get("id");
                            if (id == null || id.trim().isEmpty()) {
                                continue;
                            }

                            String filePath = (String) assetMap.get("file_path");
                            int logicWidth = parseNumber(assetMap.get("logic_width"), 0);
                            int logicHeight = parseNumber(assetMap.get("logic_height"), 0);
                            int scale = parseNumber(assetMap.get("scale"), 1);

                            int sliceTop = 0;
                            int sliceLeft = 0;
                            int sliceBottom = 0;
                            int sliceRight = 0;
                            Object insetsObj = assetMap.get("slice_insets");
                            if (insetsObj instanceof Map<?, ?> insetsMap) {
                                sliceTop = parseNumber(insetsMap.get("top"), 0);
                                sliceLeft = parseNumber(insetsMap.get("left"), 0);
                                sliceBottom = parseNumber(insetsMap.get("bottom"), 0);
                                sliceRight = parseNumber(insetsMap.get("right"), 0);
                            }

                            Map<String, String> states = new LinkedHashMap<>();
                            Object statesObj = assetMap.get("states");
                            if (statesObj instanceof Map<?, ?> sm) {
                                for (Map.Entry<?, ?> e : sm.entrySet()) {
                                    if (e.getKey() != null && e.getValue() != null) {
                                        states.put(e.getKey().toString(), e.getValue().toString());
                                    }
                                }
                            }

                            UiAsset asset = new UiAsset(
                                    logicWidth, logicHeight, scale,
                                    sliceTop, sliceLeft, sliceBottom, sliceRight,
                                    filePath, Collections.unmodifiableMap(states)
                            );
                            UI_MANIFEST_CACHE.put(id, asset);
                            UI_MANIFEST_CACHE.put(id.toLowerCase(Locale.ROOT), asset);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("[AssetLoader] CẢNH BÁO: Lỗi phân tích cú pháp UI manifest: " + e.getMessage());
        }
    }

    private static int parseNumber(Object obj, int defaultVal) {
        if (obj instanceof Number n) {
            return n.intValue();
        }
        return defaultVal;
    }

    private static String readManifestResource(String path) {
        String normalized = normalizePath(path);
        String directCp = "/" + normalized;
        String altCp = normalized.startsWith("resources/") ? "/" + normalized.substring(10) : "/resources/" + normalized;

        for (String cp : new String[]{ directCp, altCp }) {
            try (InputStream in = AssetLoader.class.getResourceAsStream(cp)) {
                if (in != null) {
                    return new String(in.readAllBytes(), StandardCharsets.UTF_8);
                }
            } catch (IOException ignored) {}
        }

        File directFile = new File(normalized);
        if (directFile.exists() && directFile.isFile()) {
            try {
                return Files.readString(directFile.toPath(), StandardCharsets.UTF_8);
            } catch (IOException ignored) {}
        }

        File resFile = new File("resources", normalized);
        if (resFile.exists() && resFile.isFile()) {
            try {
                return Files.readString(resFile.toPath(), StandardCharsets.UTF_8);
            } catch (IOException ignored) {}
        }

        return null;
    }

    private static BufferedImage loadResource(String normalizedPath) {
        String directCp = "/" + normalizedPath;
        String altCp = normalizedPath.startsWith("resources/") ? "/" + normalizedPath.substring(10) : "/resources/" + normalizedPath;

        // 1. Tra cứu qua ClassLoader của AssetLoader
        for (String cp : new String[]{ directCp, altCp }) {
            try (InputStream in = AssetLoader.class.getResourceAsStream(cp)) {
                if (in != null) {
                    BufferedImage img = ImageIO.read(in);
                    if (img != null) {
                        return img;
                    }
                }
            } catch (IOException e) {
                System.err.println("[AssetLoader] Lỗi đọc tài nguyên classpath '" + cp + "': " + e.getMessage());
            }
        }

        // 2. Tra cứu qua ContextClassLoader
        try {
            ClassLoader ccl = Thread.currentThread().getContextClassLoader();
            if (ccl != null) {
                String altNoRes = normalizedPath.startsWith("resources/") ? normalizedPath.substring(10) : "resources/" + normalizedPath;
                for (String cp : new String[]{ normalizedPath, altNoRes }) {
                    try (InputStream in = ccl.getResourceAsStream(cp)) {
                        if (in != null) {
                            BufferedImage img = ImageIO.read(in);
                            if (img != null) {
                                return img;
                            }
                        }
                    }
                }
            }
        } catch (IOException ignored) {}

        // 3. Đọc từ hệ thống tệp cục bộ
        File directFile = new File(normalizedPath);
        if (directFile.exists() && directFile.isFile()) {
            try {
                return ImageIO.read(directFile);
            } catch (IOException e) {
                System.err.println("[AssetLoader] Lỗi đọc tệp cục bộ '" + directFile.getPath() + "': " + e.getMessage());
            }
        }

        File resFile = new File("resources", normalizedPath);
        if (resFile.exists() && resFile.isFile()) {
            try {
                return ImageIO.read(resFile);
            } catch (IOException e) {
                System.err.println("[AssetLoader] Lỗi đọc tệp resources '" + resFile.getPath() + "': " + e.getMessage());
            }
        }

        return null;
    }

    /**
     * Bộ phân tích cú pháp JSON nội bộ gọn nhẹ, tự viết để đọc manifest.json mà không cần thư viện ngoài (Jackson/Gson).
     */
    private static final class SimpleJsonParser {

        private static Object parse(String json) {
            if (json == null) {
                return null;
            }
            return new Parser(json).parseValue();
        }

        private static class Parser {
            private final String src;
            private int pos = 0;

            Parser(String src) {
                this.src = src;
            }

            private void skipWhitespace() {
                while (pos < src.length() && Character.isWhitespace(src.charAt(pos))) {
                    pos++;
                }
            }

            private char peek() {
                skipWhitespace();
                return pos < src.length() ? src.charAt(pos) : '\0';
            }

            private char next() {
                skipWhitespace();
                return pos < src.length() ? src.charAt(pos++) : '\0';
            }

            Object parseValue() {
                char c = peek();
                if (c == '{') return parseObject();
                if (c == '[') return parseArray();
                if (c == '"') return parseString();
                if (c == 't' || c == 'f') return parseBoolean();
                if (c == 'n') return parseNull();
                if (c == '-' || Character.isDigit(c)) return parseNumber();
                throw new IllegalArgumentException("Ký tự không hợp lệ tại vị trí " + pos + ": " + c);
            }

            Map<String, Object> parseObject() {
                Map<String, Object> map = new LinkedHashMap<>();
                next(); // bỏ qua '{'
                while (true) {
                    char c = peek();
                    if (c == '}') {
                        next();
                        break;
                    }
                    if (c == '\0') break;
                    if (c == ',') {
                        next();
                        continue;
                    }
                    String key = parseString();
                    skipWhitespace();
                    if (next() != ':') {
                        throw new IllegalArgumentException("Mong đợi dấu ':' tại vị trí " + pos);
                    }
                    Object val = parseValue();
                    map.put(key, val);
                    c = peek();
                    if (c == ',') {
                        next();
                    } else if (c == '}') {
                        next();
                        break;
                    }
                }
                return map;
            }

            List<Object> parseArray() {
                List<Object> list = new ArrayList<>();
                next(); // bỏ qua '['
                while (true) {
                    char c = peek();
                    if (c == ']') {
                        next();
                        break;
                    }
                    if (c == '\0') break;
                    if (c == ',') {
                        next();
                        continue;
                    }
                    list.add(parseValue());
                    c = peek();
                    if (c == ',') {
                        next();
                    } else if (c == ']') {
                        next();
                        break;
                    }
                }
                return list;
            }

            String parseString() {
                next(); // bỏ qua '"'
                StringBuilder sb = new StringBuilder();
                while (pos < src.length()) {
                    char c = src.charAt(pos++);
                    if (c == '"') {
                        return sb.toString();
                    }
                    if (c == '\\') {
                        if (pos >= src.length()) break;
                        char esc = src.charAt(pos++);
                        switch (esc) {
                            case '"' -> sb.append('"');
                            case '\\' -> sb.append('\\');
                            case '/' -> sb.append('/');
                            case 'b' -> sb.append('\b');
                            case 'f' -> sb.append('\f');
                            case 'n' -> sb.append('\n');
                            case 'r' -> sb.append('\r');
                            case 't' -> sb.append('\t');
                            case 'u' -> {
                                if (pos + 4 <= src.length()) {
                                    String hex = src.substring(pos, pos + 4);
                                    sb.append((char) Integer.parseInt(hex, 16));
                                    pos += 4;
                                }
                            }
                            default -> sb.append(esc);
                        }
                    } else {
                        sb.append(c);
                    }
                }
                return sb.toString();
            }

            Boolean parseBoolean() {
                if (src.startsWith("true", pos)) {
                    pos += 4;
                    return Boolean.TRUE;
                }
                if (src.startsWith("false", pos)) {
                    pos += 5;
                    return Boolean.FALSE;
                }
                throw new IllegalArgumentException("Giá trị boolean không hợp lệ tại vị trí " + pos);
            }

            Object parseNull() {
                if (src.startsWith("null", pos)) {
                    pos += 4;
                    return null;
                }
                throw new IllegalArgumentException("Giá trị null không hợp lệ tại vị trí " + pos);
            }

            Number parseNumber() {
                int start = pos;
                if (peek() == '-') pos++;
                while (pos < src.length() && (Character.isDigit(src.charAt(pos))
                        || src.charAt(pos) == '.' || src.charAt(pos) == 'e'
                        || src.charAt(pos) == 'E' || src.charAt(pos) == '+' || src.charAt(pos) == '-')) {
                    pos++;
                }
                String numStr = src.substring(start, pos);
                if (numStr.contains(".") || numStr.contains("e") || numStr.contains("E")) {
                    return Double.parseDouble(numStr);
                }
                return Long.parseLong(numStr);
            }
        }
    }
}
