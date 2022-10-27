package net.fauxpark.oled.web.entity.request;

/**
 * A JSON request to draw an arc on the display.
 *
 * @author fauxpark
 */
public class DrawArcRequest {
    /**
     * The X position of the arc.
     */
    private int x;

    /**
     * The Y position of the arc.
     */
    private int y;

    /**
     * The radius of the arc.
     */
    private int radius;

    /**
     * The starting angle of the arc.
     */
    private int startAngle;

    /**
     * The ending angle of the arc.
     */
    private int endAngle;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getStartAngle() {
        return startAngle;
    }

    public void setStartAngle(int startAngle) {
        this.startAngle = startAngle;
    }

    public int getEndAngle() {
        return endAngle;
    }

    public void setEndAngle(int endAngle) {
        this.endAngle = endAngle;
    }

    @Override
    public String toString() {
        return "DrawArcRequest [x=" + x + ", y=" + y + ", radius=" + radius + ", startAngle=" + startAngle + ", endAngle=" + endAngle + "]";
    }
}
