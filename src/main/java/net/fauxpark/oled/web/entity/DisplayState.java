package net.fauxpark.oled.web.entity;

import lombok.Data;

import net.fauxpark.oled.SSD1306;

/**
 * A JSON result object containing the state of the OLED display.
 *
 * @author fauxpark
 */
@Data
public class DisplayState {
    /**
     * The width in pixels of the display.
     */
    private int width;

    /**
     * The height in pixels of the display.
     */
    private int height;

    /**
     * Indicates whether the display has been started up.
     */
    private boolean initialised;

    /**
     * Indicates whether the display is on.
     */
    private boolean displayOn;

    /**
     * Indicates whether the display is inverted.
     */
    private boolean inverted;

    /**
     * Indicates whether the display is horizontally flipped.
     */
    private boolean hFlipped;

    /**
     * Indicates whether the display is vertically flipped.
     */
    private boolean vFlipped;

    /**
     * Indicates whether the display is currently scrolling.
     */
    private boolean scrolling;

    /**
     * The contrast level of the display.
     */
    private int contrast;

    /**
     * The display offset.
     */
    private int offset;

    private DisplayState(SSD1306 ssd1306) {
        width = ssd1306.getWidth();
        height = ssd1306.getHeight();
        initialised = ssd1306.isInitialised();
        displayOn = ssd1306.isDisplayOn();
        inverted = ssd1306.isInverted();
        hFlipped = ssd1306.isHFlipped();
        vFlipped = ssd1306.isVFlipped();
        scrolling = ssd1306.isScrolling();
        contrast = ssd1306.getContrast();
        offset = ssd1306.getOffset();
    }

    public static DisplayState from(SSD1306 ssd1306) {
        return new DisplayState(ssd1306);
    }
}
