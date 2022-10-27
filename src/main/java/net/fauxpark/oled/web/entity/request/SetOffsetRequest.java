package net.fauxpark.oled.web.entity.request;

/**
 * A JSON request to set the display offset.
 *
 * @author fauxpark
 */
public class SetOffsetRequest {
    /**
     * The offset to set.
     */
    private int offset;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        return "SetOffsetRequest [offset=" + offset + "]";
    }
}
