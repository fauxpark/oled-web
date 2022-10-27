package net.fauxpark.oled.web.entity.request;

/**
 * A JSON request to draw a circle on the display.
 *
 * @author fauxpark
 */
public class DrawCircleRequest {
    /**
     * The X position of the circle.
     */
    private int x;

    /**
     * The Y position of the circle.
     */
    private int y;

    /**
     * The radius of the circle.
     */
    private int radius;

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

    @Override
    public String toString() {
        return "DrawCircleRequest [x=" + x + ", y=" + y + ", radius=" + radius + "]";
    }
}
