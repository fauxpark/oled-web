package net.fauxpark.oled.web.entity.request;

import lombok.Data;

/**
 * A JSON request to draw text on the display.
 *
 * @author fauxpark
 */
@Data
public class DrawTextRequest {
    /**
     * The X position of the text.
     */
    private int x;

    /**
     * The Y position of the text.
     */
    private int y;

    /**
     * The text to draw.
     */
    private String text;

    /**
     * The font to draw the text with.
     */
    private String font;
}
