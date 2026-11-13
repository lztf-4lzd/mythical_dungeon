package source.presentation.gui.ui;

import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.plaf.basic.BasicHTML;
import javax.swing.text.View;

/**
 * Tiện ích tính chiều cao động cho JLabel chứa HTML và tự động điều chỉnh kích thước
 * nhằm tránh cắt chữ khi văn bản tự động xuống dòng.
 * Người phụ trách: Thành viên 4 (UI Pixel & Custom Components)
 */
public final class HtmlLabels {

    /** Ngăn khởi tạo lớp tiện ích tĩnh. */
    private HtmlLabels() {
        // TODO: Hướng dẫn hiện thực:
        // - Viết xử lý logic tại đây theo yêu cầu thiết kế.
    }

    /**
     * Tính chiều cao cần thiết cho JLabel hiển thị HTML ở một chiều rộng khả dụng cho trước.
     *
     * @param label          nhãn chứa văn bản HTML
     * @param availableWidth chiều rộng khả dụng tính bằng pixel
     * @return chiều cao ưu tiên tính bằng pixel
     */
    private static int calculateHtmlHeight(JLabel label, int availableWidth) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (label == null || availableWidth <= 0)
        // 2. return (label != null && label.getPreferredSize() != null) ? label.getPreferredSize().height : 0;
        // 3. String text = label.getText();
        // 4. if (text == null || !text.regionMatches(true, 0, "<html>", 0, 6))
        // 5. return (label.getPreferredSize() != null) ? label.getPreferredSize().height : 0;
        // 6. View view = (View) label.getClientProperty(BasicHTML.propertyKey);
        return 0;
    }

    /**
     * Tự động cập nhật preferred/minimum/maximum size của nhãn HTML để tránh cắt chữ trong bố cục hẹp.
     *
     * @param label          nhãn JLabel cần căn chỉnh
     * @param availableWidth chiều rộng khả dụng tính bằng pixel
     * @return chiều cao sau khi điều chỉnh tính bằng pixel
     */
    public static int autoFitHtml(JLabel label, int availableWidth) {
        // TODO: Hướng dẫn hiện thực:
        // 1. if (label == null || availableWidth <= 0)
        // 2. return 0;
        // 3. int neededHeight = calculateHtmlHeight(label, availableWidth);
        // 4. int currentHeight = (label.getPreferredSize() != null) ? label.getPreferredSize().height : 0;
        // 5. int targetHeight = Math.max(currentHeight, neededHeight);
        // 6. Dimension dim = new Dimension(availableWidth, targetHeight);
        return 0;
    }
}
