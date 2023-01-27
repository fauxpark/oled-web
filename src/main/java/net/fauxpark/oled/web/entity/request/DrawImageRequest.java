package net.fauxpark.oled.web.entity.request;

import lombok.Data;

/**
 * A JSON request to draw an image on the display.
 * This class only stores position and size information; the image data itself is elsewhere in the HTTP request.
 *
 * @author fauxpark
 */
@Data
public class DrawImageRequest {
    /**
     * The X position of the image.
     */
    private int x;

    /**
     * The Y position of the image.
     */
    private int y;

    /**
     * The desired width of the image.
     */
    private int width;

    /**
     * The desired height of the image.
     */
    private int height;
}
