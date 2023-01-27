package net.fauxpark.oled.web.entity.request;

import lombok.Data;

/**
 * A JSON request to set the display offset.
 *
 * @author fauxpark
 */
@Data
public class SetOffsetRequest {
    /**
     * The offset to set.
     */
    private int offset;
}
