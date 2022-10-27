package net.fauxpark.oled.web.entity.request;

/**
 * A JSON request to draw an image on the display.
 * This class only stores position and size information; the image data itself is elsewhere in the HTTP request.
 *
 * @author fauxpark
 */
public class DrawImageRequest {
    /**
     * The X position of the image.
     */
    private int x;

    /**
     * The Y position of the image.
     */
    private int y;

    /**
     * The desired width of the image.
     */
    private int width;

    /**
     * The desired height of the image.
     */
    private int height;

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

    @Override
    public String toString() {
        return "DrawImageRequest [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + "]";
    }
}
