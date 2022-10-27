package net.fauxpark.oled.web.entity.request;

/**
 * A JSON request to draw a rectangle on the display.
 *
 * @author fauxpark
 */
public class DrawRectangleRequest {
    /**
     * The X position of the rectangle.
     */
    private int x;

    /**
     * The Y position of the rectangle.
     */
    private int y;

    /**
     * The width of the rectangle.
     */
    private int width;

    /**
     * The height of the rectangle.
     */
    private int height;

    /**
     * Whether to fill in the rectangle.
     */
    private boolean filled;

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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    @Override
    public String toString() {
        return "DrawRectangleRequest [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + ", filled=" + filled + "]";
    }
}
