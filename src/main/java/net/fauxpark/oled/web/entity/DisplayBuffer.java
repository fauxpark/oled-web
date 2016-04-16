package net.fauxpark.oled.web.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	 *
	 * @see DisplayBuffer#getBufferAsBytes()
	 * @see DisplayBuffer#setBufferAsBytes(byte[])
	 */
	private int[] buffer;

	public DisplayBuffer() {}

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
	 * Retrieve the buffer's contents as a byte array.
	 *
	 * @return The display buffer downcast to a byte array.
	 */
	@JsonIgnore
	public byte[] getBufferAsBytes() {
		byte[] byteArray = new byte[buffer.length];

		for(int i = 0; i < buffer.length; i++) {
			byteArray[i] = (byte) buffer[i];
		}

		return byteArray;
	}

	/**
	 * Set the buffer's contents from a byte array.
	 *
	 * @param buffer The buffer to set.
	 */
	@JsonIgnore
	public void setBufferAsBytes(byte[] buffer) {
		int[] intArray = new int[buffer.length];

		for(int i = 0; i < buffer.length; i++) {
			intArray[i] = buffer[i];
		}

		this.buffer = intArray;
	}

	@Override
	public String toString() {
		return "DisplayBuffer [width=" + width + ", height=" + height + ", buffer.length=" + buffer.length + "]";
	}
}
