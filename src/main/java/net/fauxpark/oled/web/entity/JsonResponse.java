package net.fauxpark.oled.web.entity;

/**
 * A simple JSON response object.
 *
 * @param <T> The type of result the response holds.
 */
public class JsonResponse<T> {
	/**
	 * Indicates the success or failure of the operation.
	 */
	private boolean ok;

	/**
	 * The result of the operation.
	 */
	private T result;

	/**
	 * The error message, if the operation failed.
	 */
	private String message;

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "JsonResponse [ok=" + ok + ", result=" + result + ", message=" + message + "]";
	}
}
