package net.fauxpark.oled.web.entity.request;

import lombok.Data;

/**
 * A JSON request to set the contrast (brightness) of the display.
 *
 * @author fauxpark
 */
@Data
public class SetContrastRequest {
    /**
     * The contrast level to set.
     */
    private int contrast;
}
