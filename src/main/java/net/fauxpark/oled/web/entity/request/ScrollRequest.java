package net.fauxpark.oled.web.entity.request;

/**
 * A JSON request to scroll the display.
 *
 * @author fauxpark
 */
public class ScrollRequest {
    /**
     * Whether to scroll vertically as well.
     */
    private boolean vertical;

    /**
     * Whether to scroll to the left or the right.
     */
    private boolean left;

    /**
     * The topmost page to scroll.
     */
    private int startPage;

    /**
     * The bottommost page to scroll.
     */
    private int endPage;

    /**
     * The number of rows from the top to start the vertical scroll area at.
     */
    private int offset;

    /**
     * The number of rows in the vertical scroll area.
     */
    private int rows;

    /**
     * The scroll speed.
     */
    private int speed;

    /**
     * The number of rows to scroll vertically by.
     */
    private int step;

    public boolean isVertical() {
        return vertical;
    }

    public void setVertical(boolean vertical) {
        this.vertical = vertical;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    @Override
    public String toString() {
        return "ScrollRequest [vertical=" + vertical + ", left=" + left + ", startPage=" + startPage + ", endPage=" + endPage + ", offset=" + offset + ", rows=" + rows + ", speed=" + speed + ", step=" + step + "]";
    }
}
