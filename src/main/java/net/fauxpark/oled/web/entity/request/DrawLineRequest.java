package net.fauxpark.oled.web.entity.request;

import lombok.Data;

/**
 * A JSON request to draw a line on the display.
 *
 * @author fauxpark
 */
@Data
public class DrawLineRequest {
    /**
     * The first X position of the line.
     */
    private int x0;

    /**
     * The first Y position of the line.
     */
    private int y0;

    /**
     * The second X position of the line.
     */
    private int x1;

    /**
     * The second Y position of the line.
     */
    private int y1;
}
