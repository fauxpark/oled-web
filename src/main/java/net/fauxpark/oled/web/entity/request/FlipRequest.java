package net.fauxpark.oled.web.entity.request;

import lombok.Data;

/**
 * A JSON request to flip the display on a specified axis.
 *
 * @author fauxpark
 */
@Data
public class FlipRequest {
    /**
     * The axis to flip on.
     */
    private String axis;
}
