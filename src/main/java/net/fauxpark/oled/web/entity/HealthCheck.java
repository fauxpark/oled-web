package net.fauxpark.oled.web.entity;

/**
 * A JSON object containing the API status and version.
 *
 * @author fauxpark
 */
public class HealthCheck {
    private String status;

    private String version;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "HealthCheck [status=" + status + ", version=" + version + "]";
    }
}
