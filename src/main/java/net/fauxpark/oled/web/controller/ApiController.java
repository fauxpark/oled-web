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
import net.fauxpark.oled.web.entity.DisplayState;
import net.fauxpark.oled.web.entity.JsonResponse;
import net.fauxpark.oled.web.entity.request.FlipRequest;
import net.fauxpark.oled.web.entity.request.ScrollRequest;
import net.fauxpark.oled.web.entity.request.SetContrastRequest;
import net.fauxpark.oled.web.entity.request.SetOffsetRequest;
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
	 * Get the display state.
	 *
	 * @return A JSON response containing the display state.
	 */
	@RequestMapping("/state")
	@ResponseBody
	public JsonResponse<DisplayState> getState() {
		log.info("======== getState");

		JsonResponse<DisplayState> response = new JsonResponse<>();
		response.setOk(true);
		response.setResult(getDisplayState());

		return response;
	}

	/**
	 * Begin the startup procedure for the display.
	 *
	 * @return A JSON response containing the display state.
	 */
	@RequestMapping(value="/startup", method=RequestMethod.POST)
	@ResponseBody
	public JsonResponse<DisplayState> startup() {
		log.info("======== startup");

		JsonResponse<DisplayState> response = new JsonResponse<>();
		response.setOk(true);
		ssd1306.startup(false);
		response.setResult(getDisplayState());

		return response;
	}

	/**
	 * Begin the shutdown procedure for the display.
	 *
	 * @return A JSON response containing the display state.
	 */
	@RequestMapping(value="/shutdown", method=RequestMethod.POST)
	@ResponseBody
	public JsonResponse<DisplayState> shutdown() {
		log.info("======== shutdown");

		JsonResponse<DisplayState> response = new JsonResponse<>();
		response.setOk(true);
		ssd1306.shutdown();
		response.setResult(getDisplayState());

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
	 * @return A JSON response containing the new on state of the display.
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
	 * Clear the display.
	 *
	 * @return An empty JSON response.
	 */
	@RequestMapping(value="/clear", method=RequestMethod.POST)
	@ResponseBody
	public JsonResponse<Void> clear() {
		log.info("======== clear");

		JsonResponse<Void> response = new JsonResponse<>();
		response.setOk(true);
		ssd1306.clear();
		ssd1306.display();

		return response;
	}

	/**
	 * Invert the display.
	 *
	 * @return A JSON response containing the new inverted state of the display.
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
	 * Flip the display.
	 *
	 * @param request A JSON object containing the axis to flip.
	 *
	 * @return A JSON response containing the flip state of the specified axis.
	 */
	@RequestMapping(value="/flip", method=RequestMethod.POST)
	@ResponseBody
	public JsonResponse<Boolean> flip(@RequestBody FlipRequest request) {
		log.info("======== flip");
		log.info("request.getAxis()={}", request.getAxis());

		JsonResponse<Boolean> response = new JsonResponse<>();
		response.setOk(true);

		if(request.getAxis().equals("h")) {
			ssd1306.setHFlipped(!ssd1306.isHFlipped());
			response.setResult(ssd1306.isHFlipped());
		} else if(request.getAxis().equals("v")) {
			ssd1306.setVFlipped(!ssd1306.isVFlipped());
			response.setResult(ssd1306.isVFlipped());
		}

		return response;
	}

	/**
	 * Set the display contrast.
	 *
	 * @param request A JSON object containing the contrast level to set.
	 *
	 * @return A JSON response containing the new contrast level.
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
	 * Set the display offset.
	 *
	 * @param request A JSON object containing the offset to set.
	 *
	 * @return A JSON response containing the new offset.
	 */
	@RequestMapping(value="/offset", method=RequestMethod.POST)
	@ResponseBody
	public JsonResponse<Integer> setOffset(@RequestBody SetOffsetRequest request) {
		log.info("======== setOffset");
		log.info("request.getOffset()={}", request.getOffset());

		JsonResponse<Integer> response = new JsonResponse<>();
		response.setOk(true);
		ssd1306.setOffset(request.getOffset());
		response.setResult(ssd1306.getOffset());

		return response;
	}

	/**
	 * Turn a single pixel on or off.
	 *
	 * @param request A JSON object containing the X, Y, and state of the pixel to set.
	 *
	 * @return A JSON response containing the new state of the pixel.
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
		buffer.setBufferAsBytes(ssd1306.getBuffer());
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
		ssd1306.setBuffer(buffer.getBufferAsBytes());
		ssd1306.display();

		return response;
	}

	/**
	 * Setup and start scrolling the display.
	 *
	 * @param request A JSON request containing the scroll direction, area and speed.
	 *
	 * @return A JSON response containing the new scroll state.
	 */
	@RequestMapping(value="/scroll/start", method=RequestMethod.POST)
	@ResponseBody
	public JsonResponse<Boolean> startScroll(@RequestBody ScrollRequest request) {
		log.info("======== startScroll");
		log.info("request.isVertical()={}", request.isVertical());
		log.info("request.isLeft()={}", request.isLeft());
		log.info("request.getStartPage()={}", request.getStartPage());
		log.info("request.getEndPage()={}", request.getEndPage());
		log.info("request.getOffset()={}", request.getOffset());
		log.info("request.getRows()={}", request.getRows());
		log.info("request.getSpeed()={}", request.getSpeed());
		log.info("request.getStep()={}", request.getStep());

		JsonResponse<Boolean> response = new JsonResponse<>();
		response.setOk(true);

		if(request.isVertical()) {
			ssd1306.scrollDiagonally(request.isLeft(), request.getStartPage(), request.getEndPage(), request.getOffset(), request.getRows(), request.getSpeed(), request.getStep());
		} else {
			ssd1306.scrollHorizontally(request.isLeft(), request.getStartPage(), request.getEndPage(), request.getSpeed());
		}

		ssd1306.startScroll();
		response.setResult(ssd1306.isScrolling());

		return response;
	}

	/**
	 * Stop scrolling the display.
	 *
	 * @return An JSON response containing the new scroll state.
	 */
	@RequestMapping(value="/scroll/stop", method=RequestMethod.POST)
	@ResponseBody
	public JsonResponse<Boolean> stopScroll() {
		log.info("======== stopScroll");

		JsonResponse<Boolean> response = new JsonResponse<>();
		response.setOk(true);
		ssd1306.stopScroll();
		ssd1306.display();
		response.setResult(ssd1306.isScrolling());

		return response;
	}

	/**
	 * Constructs a DisplayState object.
	 *
	 * @return The current state of the OLED display.
	 */
	private DisplayState getDisplayState() {
		DisplayState state = new DisplayState();
		state.setWidth(ssd1306.getWidth());
		state.setHeight(ssd1306.getHeight());
		state.setInitialised(ssd1306.isInitialised());
		state.setDisplayOn(ssd1306.isDisplayOn());
		state.setInverted(ssd1306.isInverted());
		state.sethFlipped(ssd1306.isHFlipped());
		state.setvFlipped(ssd1306.isVFlipped());
		state.setScrolling(ssd1306.isScrolling());
		state.setContrast(ssd1306.getContrast());
		state.setOffset(ssd1306.getOffset());

		return state;
	}
}
