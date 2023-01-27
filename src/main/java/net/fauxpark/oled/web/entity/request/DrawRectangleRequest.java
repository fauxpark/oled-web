package net.fauxpark.oled.web.entity.request;

import lombok.Data;

/**
 * A JSON request to draw a rectangle on the display.
 *
 * @author fauxpark
 */
@Data
public class DrawRectangleRequest {
    /**
     * The X position of the rectangle.
     */
    private int x;

    /**
     * The Y position of the rectangle.
     */
    private int y;

    /**
     * The width of the rectangle.
     */
    private int width;

    /**
     * The height of the rectangle.
     */
    private int height;

    /**
     * Whether to fill in the rectangle.
     */
    private boolean filled;
}
