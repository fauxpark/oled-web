package net.fauxpark.oled.web.entity;

import lombok.Builder;
import lombok.Data;

/**
 * A simple JSON response object.
 *
 * @author fauxpark
 *
 * @param <T> The type of result the response holds.
 */
@Data
@Builder
public class JsonResponse<T> {
    /**
     * Indicates the success or failure of the operation.
     */
    private boolean ok;

    /**
     * The result of the operation.
     */
    private T result;

    /**
     * The error message, if the operation failed.
     */
    private String message;
}
