package net.fauxpark.oled.web.entity;

/**
 * A JSON request/response object that holds the OLED display buffer.
 *
 * @author fauxpark
 */
public class DisplayBuffer {
	/**
	 * The width of the buffer.
	 */
	private int width;

	/**
	 * The height of the buffer.
	 */
	private int height;

	/**
	 * The buffer contents, as an int array.
	 * Jackson will convert byte arrays to base64 strings, so it must be cast
	 * to/from a byte array in order to use it with the display.
	 */
	private int[] buffer;

	public DisplayBuffer(int width, int height, int[] buffer) {
		this.width = width;
		this.height = height;
		this.buffer = buffer;
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

	public int[] getBuffer() {
		return buffer;
	}

	public void setBuffer(int[] buffer) {
		this.buffer = buffer;
	}

	/**
	 * Convert an int array to a byte array.
	 *
	 * @param intArray The int array to cast.
	 *
	 * @return The resulting byte array.
	 */
	public static byte[] toByteArray(int[] intArray) {
		byte[] byteArray = new byte[intArray.length];

		for(int i = 0; i < intArray.length; i++) {
			byteArray[i] = (byte) intArray[i];
		}

		return byteArray;
	}

	/**
	 * Convert a byte array to an int array.
	 *
	 * @param byteArray The byte array to cast.
	 *
	 * @return The resulting int array.
	 */
	public static int[] toIntArray(byte[] byteArray) {
		int[] intArray = new int[byteArray.length];

		for(int i = 0; i < byteArray.length; i++) {
			intArray[i] = byteArray[i];
		}

		return intArray;
	}

	@Override
	public String toString() {
		return "DisplayBuffer [width=" + width + ", height=" + height + ", buffer.length=" + buffer.length + "]";
	}
}
