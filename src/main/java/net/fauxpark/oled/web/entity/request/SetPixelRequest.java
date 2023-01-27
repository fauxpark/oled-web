package net.fauxpark.oled.web.entity.request;

import lombok.Data;

/**
 * A JSON request to turn a pixel on the display on or off.
 *
 * @author fauxpark
 */
@Data
public class SetPixelRequest {
    /**
     * The X position of the pixel to set.
     */
    private int x;

    /**
     * The Y position of the pixel to set.
     */
    private int y;

    /**
     * Whether to turn the pixel on or off.
     */
    private boolean on;
}
