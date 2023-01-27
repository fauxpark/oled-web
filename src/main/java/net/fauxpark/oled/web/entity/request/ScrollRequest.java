package net.fauxpark.oled.web.entity.request;

import lombok.Data;

/**
 * A JSON request to scroll the display.
 *
 * @author fauxpark
 */
@Data
public class ScrollRequest {
    /**
     * Whether to scroll vertically as well.
     */
    private boolean vertical;

    /**
     * Whether to scroll to the left or the right.
     */
    private boolean left;

    /**
     * The topmost page to scroll.
     */
    private int startPage;

    /**
     * The bottommost page to scroll.
     */
    private int endPage;

    /**
     * The number of rows from the top to start the vertical scroll area at.
     */
    private int offset;

    /**
     * The number of rows in the vertical scroll area.
     */
    private int rows;

    /**
     * The scroll speed.
     */
    private int speed;

    /**
     * The number of rows to scroll vertically by.
     */
    private int step;
}
