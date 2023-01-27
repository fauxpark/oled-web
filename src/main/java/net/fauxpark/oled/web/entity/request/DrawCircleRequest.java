package net.fauxpark.oled.web.entity.request;

import lombok.Data;

/**
 * A JSON request to draw a circle on the display.
 *
 * @author fauxpark
 */
@Data
public class DrawCircleRequest {
    /**
     * The X position of the circle.
     */
    private int x;

    /**
     * The Y position of the circle.
     */
    private int y;

    /**
     * The radius of the circle.
     */
    private int radius;
}
