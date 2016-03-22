package net.fauxpark.oled.web.entity.request;

/**
 * A JSON request to draw a line on the display.
 *
 * @author fauxpark
 */
public class DrawLineRequest {
	/**
	 * The first X position of the line.
	 */
	private int x0;

	/**
	 * The first Y position of the line.
	 */
	private int y0;

	/**
	 * The second X position of the line.
	 */
	private int x1;

	/**
	 * The second Y position of the line.
	 */
	private int y1;

	public int getX0() {
		return x0;
	}

	public void setX0(int x0) {
		this.x0 = x0;
	}

	public int getY0() {
		return y0;
	}

	public void setY0(int y0) {
		this.y0 = y0;
	}

	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	@Override
	public String toString() {
		return "DrawLineRequest [x0=" + x0 + ", y0=" + y0 + ", x1=" + x1 + ", y1=" + y1 + "]";
	}
}
