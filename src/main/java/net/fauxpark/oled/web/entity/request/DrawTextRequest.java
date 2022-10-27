package net.fauxpark.oled.web.entity.request;

/**
 * A JSON request to draw text on the display.
 *
 * @author fauxpark
 */
public class DrawTextRequest {
    /**
     * The X position of the text.
     */
    private int x;

    /**
     * The Y position of the text.
     */
    private int y;

    /**
     * The text to draw.
     */
    private String text;

    /**
     * The font to draw the text with.
     */
    private String font;

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    @Override
    public String toString() {
        return "DrawTextRequest [x=" + x + ", y=" + y + ", text=" + text + ", font=" + font + "]";
    }
}
