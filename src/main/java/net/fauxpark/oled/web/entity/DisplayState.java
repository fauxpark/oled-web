package net.fauxpark.oled.web.entity;

/**
 * A JSON result object containing the state of the OLED display.
 *
 * @author fauxpark
 */
public class DisplayState {
	/**
	 * Indicates whether the display has been started up.
	 */
	private boolean initialised;

	/**
	 * Indicates whether the display is on.
	 */
	private boolean displayOn;

	/**
	 * Indicates whether the display is inverted.
	 */
	private boolean inverted;

	/**
	 * Indicates whether the display is horizontally flipped.
	 */
	private boolean hFlipped;

	/**
	 * Indicates whether the display is vertically flipped.
	 */
	private boolean vFlipped;

	/**
	 * The contrast level of the display.
	 */
	private int contrast;

	public boolean isInitialised() {
		return initialised;
	}

	public void setInitialised(boolean initialised) {
		this.initialised = initialised;
	}

	public boolean isDisplayOn() {
		return displayOn;
	}

	public void setDisplayOn(boolean displayOn) {
		this.displayOn = displayOn;
	}

	public boolean isInverted() {
		return inverted;
	}

	public void setInverted(boolean inverted) {
		this.inverted = inverted;
	}

	public boolean ishFlipped() {
		return hFlipped;
	}

	public void sethFlipped(boolean hFlipped) {
		this.hFlipped = hFlipped;
	}

	public boolean isvFlipped() {
		return vFlipped;
	}

	public void setvFlipped(boolean vFlipped) {
		this.vFlipped = vFlipped;
	}

	public int getContrast() {
		return contrast;
	}

	public void setContrast(int contrast) {
		this.contrast = contrast;
	}

	@Override
	public String toString() {
		return "DisplayState [initialised=" + initialised + ", displayOn=" + displayOn + ", inverted=" + inverted + ", hFlipped=" + hFlipped + ", vFlipped=" + vFlipped + ", contrast=" + contrast + "]";
	}
}
