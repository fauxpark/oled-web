package net.fauxpark.oled.web.entity.request;

/**
 * A JSON request to turn a pixel on the display on or off.
 *
 * @author fauxpark
 */
public class SetPixelRequest {
	/**
	 * The X position of the pixel to set.
	 */
	private int x;

	/**
	 * The Y position of the pixel to set.
	 */
	private int y;

	/**
	 * Whether to turn the pixel on or off.
	 */
	private boolean on;

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

	public boolean isOn() {
		return on;
	}

	public void setOn(boolean on) {
		this.on = on;
	}

	@Override
	public String toString() {
		return "SetPixelRequest [x=" + x + ", y=" + y + ", on=" + on + "]";
	}
}
