package net.fauxpark.oled.web.entity.request;

/**
 * A JSON request to set the contrast (brightness) of the display.
 */
public class SetContrastRequest {
	/**
	 * The contrast level to set.
	 */
	private int contrast;

	public int getContrast() {
		return contrast;
	}

	public void setContrast(int contrast) {
		this.contrast = contrast;
	}

	@Override
	public String toString() {
		return "SetContrastRequest [contrast=" + contrast + "]";
	}
}
