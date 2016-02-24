package net.fauxpark.oled.web.entity.request;

/**
 * A JSON request to flip the display on a specified axis.
 *
 * @author fauxpark
 */
public class FlipRequest {
	/**
	 * The axis to flip on.
	 */
	private String axis;

	public String getAxis() {
		return axis;
	}

	public void setAxis(String axis) {
		this.axis = axis;
	}

	@Override
	public String toString() {
		return "FlipRequest [axis=" + axis + "]";
	}
}
