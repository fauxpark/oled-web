package net.fauxpark.oled.web.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.fauxpark.oled.SSD1306;
import net.fauxpark.oled.web.entity.DisplayBuffer;
import net.fauxpark.oled.web.entity.JsonResponse;
import net.fauxpark.oled.web.entity.request.SetContrastRequest;
import net.fauxpark.oled.web.entity.request.SetPixelRequest;
import net.fauxpark.oled.web.factory.SSD1306Factory;

/**
 * A controller which handles all of the API calls to interact with the SSD1306.
 *
 * @author fauxpark
 */
@Controller
@RequestMapping("/api")
public class ApiController {
	private static final Logger log = LogManager.getLogger(ApiController.class);

	private final SSD1306 ssd1306 = SSD1306Factory.getInstance();

	/**
	 * Begin the startup procedure for the display.
	 */
	@RequestMapping(value="/startup", method=RequestMethod.POST)
	@ResponseBody
	public JsonResponse<Void> startup() {
		log.info("======== startup");

		JsonResponse<Void> response = new JsonResponse<>();
		response.setOk(true);
		ssd1306.startup(false);

		return response;
	}

	/**
	 * Begin the shutdown procedure for the display.
	 */
	@RequestMapping(value="/shutdown", method=RequestMethod.POST)
	@ResponseBody
	public JsonResponse<Void> shutdown() {
		log.info("======== shutdown");

		JsonResponse<Void> response = new JsonResponse<>();
		response.setOk(true);
		ssd1306.shutdown();

		return response;
	}

	/**
	 * Turn the display on.
	 *
	 * @return A JSON object containing the new on state of the display.
	 */
	@RequestMapping(value="/on", method=RequestMethod.POST)
	@ResponseBody
	public JsonResponse<Boolean> displayOn() {
		log.info("======== displayOn");

		JsonResponse<Boolean> response = new JsonResponse<>();
		response.setOk(true);
		ssd1306.setDisplayOn(true);
		response.setResult(ssd1306.isDisplayOn());

		return response;
	}

	/**
	 * Turn the display off.
	 *
	 * @return A JSON object containing the new on state of the display.
	 */
	@RequestMapping(value="/off", method=RequestMethod.POST)
	@ResponseBody
	public JsonResponse<Boolean> displayOff() {
		log.info("======== displayOff");

		JsonResponse<Boolean> response = new JsonResponse<>();
		response.setOk(true);
		ssd1306.setDisplayOn(false);
		response.setResult(ssd1306.isDisplayOn());

		return response;
	}

	/**
	 * Invert the display.
	 *
	 * @return A JSON object containing the new inverted state of the display.
	 */
	@RequestMapping(value="/invert", method=RequestMethod.POST)
	@ResponseBody
	public JsonResponse<Boolean> invert() {
		log.info("======== invert");

		JsonResponse<Boolean> response = new JsonResponse<>();
		response.setOk(true);
		ssd1306.setInverted(!ssd1306.isInverted());
		response.setResult(ssd1306.isInverted());

		return response;
	}

	/**
	 * Set the display contrast.
	 *
	 * @param request A JSON object containing the contrast level to set.
	 *
	 * @return A JSON object containing the new contrast level.
	 */
	@RequestMapping(value="/contrast", method=RequestMethod.POST)
	@ResponseBody
	public JsonResponse<Integer> setContrast(@RequestBody SetContrastRequest request) {
		log.info("======== setContrast");
		log.info("request.getContrast()={}", request.getContrast());

		JsonResponse<Integer> response = new JsonResponse<>();
		response.setOk(true);
		ssd1306.setContrast(request.getContrast());
		response.setResult(ssd1306.getContrast());

		return response;
	}

	/**
	 * Turn a single pixel on or off.
	 *
	 * @param request A JSON object containing the X, Y, and state of the pixel to set.
	 *
	 * @return A JSON object containing the new state of the pixel.
	 */
	@RequestMapping(value="/pixel", method=RequestMethod.POST)
	@ResponseBody
	public JsonResponse<Boolean> setPixel(@RequestBody SetPixelRequest request) {
		log.info("======== setPixel");
		log.info("request.getX()={}", request.getX());
		log.info("request.getY()={}", request.getY());
		log.info("request.isOn()={}", request.isOn());

		JsonResponse<Boolean> response = new JsonResponse<>();
		response.setOk(true);
		ssd1306.setPixel(request.getX(), request.getY(), request.isOn());
		ssd1306.display();
		response.setResult(ssd1306.getPixel(request.getX(), request.getY()));

		return response;
	}

	/**
	 * Get the display buffer.
	 *
	 * @return A JSON response containing the width, height, and contents of the display buffer.
	 */
	@RequestMapping("/buffer")
	@ResponseBody
	public JsonResponse<DisplayBuffer> getBuffer() {
		log.info("======== getBuffer");

		JsonResponse<DisplayBuffer> response = new JsonResponse<>();
		response.setOk(true);
		DisplayBuffer buffer = new DisplayBuffer(ssd1306.getWidth(), ssd1306.getHeight(), null);
		buffer.setBuffer(DisplayBuffer.toIntArray(ssd1306.getBuffer()));
		response.setResult(buffer);

		return response;
	}

	/**
	 * Set the display buffer.
	 *
	 * @param buffer A JSON object containing the width, height, and contents of the buffer to set.
	 */
	@RequestMapping(value="/buffer", method=RequestMethod.POST)
	@ResponseBody
	public JsonResponse<Void> setBuffer(@RequestBody DisplayBuffer buffer) {
		log.info("======== setBuffer");
		log.info("buffer.getWidth()={}", buffer.getWidth());
		log.info("buffer.getHeight()={}", buffer.getHeight());
		log.info("buffer.getBuffer().length={}", buffer.getBuffer().length);

		JsonResponse<Void> response = new JsonResponse<>();
		response.setOk(true);
		ssd1306.setBuffer(DisplayBuffer.toByteArray(buffer.getBuffer()));

		return response;
	}
}
