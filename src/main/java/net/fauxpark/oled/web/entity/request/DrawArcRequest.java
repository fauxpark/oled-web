package net.fauxpark.oled.web.entity.request;

import lombok.Data;

/**
 * A JSON request to draw an arc on the display.
 *
 * @author fauxpark
 */
@Data
public class DrawArcRequest {
    /**
     * The X position of the arc.
     */
    private int x;

    /**
     * The Y position of the arc.
     */
    private int y;

    /**
     * The radius of the arc.
     */
    private int radius;

    /**
     * The starting angle of the arc.
     */
    private int startAngle;

    /**
     * The ending angle of the arc.
     */
    private int endAngle;
}
