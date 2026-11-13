package source.presentation.gui.asset;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.List;
import javax.swing.ImageIcon;

/**
 * Hợp đồng cung cấp tài nguyên hình ảnh giúp tách biệt tầng trình diễn khỏi tầng hạ tầng (Clean Architecture).
 * Các thành phần giao diện truy xuất ảnh và cấu hình manifest UI thông qua giao diện này.
 * Người phụ trách: Thành viên 4 (UI Pixel & Custom Components)
 */
public interface AssetProvider {

    /**
     * Hợp đồng siêu dữ liệu của một tài nguyên UI trong manifest.
     */
    interface UiAsset {

        /** Kiểm tra tài nguyên có cấu hình biên cắt 9-slice hay không. */
        boolean hasSliceInsets();
        /** Tính lề cắt trên thực tế theo kích thước ảnh. */
        int computeImageSliceTop(int imageWidth);
        /** Tính lề cắt trái thực tế theo kích thước ảnh. */
        int computeImageSliceLeft(int imageWidth);
        /** Tính lề cắt dưới thực tế theo kích thước ảnh. */
        int computeImageSliceBottom(int imageHeight);
        /** Tính lề cắt phải thực tế theo kích thước ảnh. */
        int computeImageSliceRight(int imageWidth);
    }

    /** Tải ảnh tĩnh theo đường dẫn. */
    BufferedImage getImage(String path);

    /** Kiểm tra ảnh đã được lưu trong bộ nhớ đệm hay chưa. */
    boolean isCached(String path);

    /** Tải và cắt sprite sheet ngang thành danh sách các khung hình. */
    List<BufferedImage> loadSpriteSheet(String path, int frameWidth, int frameHeight);

    /** Lấy biểu tượng đã co giãn theo kích thước yêu cầu. */
    ImageIcon getScaledIcon(String path, int width, int height);

    /** Co giãn ảnh bằng thuật toán nearest-neighbor để giữ nét pixel art. */
    BufferedImage scaleNearestNeighbor(BufferedImage src, int targetWidth, int targetHeight);

    /** Tạo ảnh dự phòng độc lập khi thiếu tài nguyên. */
    BufferedImage createFallbackPlaceholder(int width, int height, Color color, String label);

    /** Lấy siêu dữ liệu tài nguyên UI theo mã định danh. */
    UiAsset getUiAsset(String assetId);

    /** Kiểm tra mã tài nguyên UI có tồn tại trong manifest hay không. */
    boolean hasUiAsset(String assetId);

    /** Lấy ảnh của tài nguyên UI theo mã định danh hoặc đường dẫn. */
    BufferedImage getUiImage(String assetIdOrPath);

    /** Lấy ảnh theo trạng thái cụ thể của một tài nguyên UI. */
    BufferedImage getUiImage(String assetId, String state);

    /**
     * Đăng ký bộ cung cấp tài nguyên đang hoạt động vào {@link AssetRegistry}.
     *
     * @param provider bộ cung cấp tài nguyên
     */
    static void setInstance(AssetProvider provider) {
        // TODO: Hướng dẫn hiện thực:
        // 1. AssetRegistry.setInstance(provider);
    }
}
