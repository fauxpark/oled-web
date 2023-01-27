package net.fauxpark.oled.web.entity;

import lombok.Builder;
import lombok.Data;

/**
 * A JSON object containing the API status and version.
 *
 * @author fauxpark
 */
@Data
@Builder
public class HealthCheck {
    private String status;

    private String version;
}
