package net.fauxpark.oled.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.fauxpark.oled.SSD1306;
import net.fauxpark.oled.web.entity.DisplayBuffer;
import net.fauxpark.oled.web.entity.DisplayState;
import net.fauxpark.oled.web.entity.JsonResponse;
import net.fauxpark.oled.web.entity.request.FlipRequest;
import net.fauxpark.oled.web.entity.request.ScrollRequest;
import net.fauxpark.oled.web.entity.request.SetContrastRequest;
import net.fauxpark.oled.web.entity.request.SetOffsetRequest;
import net.fauxpark.oled.web.entity.request.SetPixelRequest;
import net.fauxpark.oled.web.service.MessageService;

/**
 * A controller which handles all of the API calls to interact with the SSD1306.
 *
 * @author fauxpark
 */
@RestController
@RequestMapping("/api")
public class ApiController {
	private static final Logger log = LoggerFactory.getLogger(ApiController.class);

	@Autowired
	private MessageService messageService;

	@Autowired
	private SSD1306 ssd1306;

	/**
	 * Get the display state.
	 *
	 * @return A JSON response containing the display state.
	 */
	@RequestMapping(path="/state", method=RequestMethod.GET)
	public JsonResponse<DisplayState> getState() {
		log.info("======== getState");

		JsonResponse<DisplayState> response = new JsonResponse<>();
		response.setOk(true);
		response.setResult(new DisplayState(ssd1306));

		return response;
	}

	/**
	 * Begin the startup procedure for the display.
	 *
	 * @return A JSON response containing the display state.
	 */
	@RequestMapping(path="/startup", method=RequestMethod.POST)
	public JsonResponse<DisplayState> startup() {
		log.info("======== startup");

		JsonResponse<DisplayState> response = new JsonResponse<>();

		if(!ssd1306.isInitialised()) {
			ssd1306.startup(false);
			response.setOk(true);
			response.setResult(new DisplayState(ssd1306));
		} else {
			response.setMessage(messageService.getMessage(MessageService.DISPLAY_ALREADY_INITIALISED));
		}

		return response;
	}

	/**
	 * Begin the shutdown procedure for the display.
	 *
	 * @return A JSON response containing the display state.
	 */
	@RequestMapping(path="/shutdown", method=RequestMethod.POST)
	public JsonResponse<DisplayState> shutdown() {
		log.info("======== shutdown");

		JsonResponse<DisplayState> response = new JsonResponse<>();

		if(ssd1306.isInitialised()) {
			ssd1306.shutdown();
			response.setOk(true);
			response.setResult(new DisplayState(ssd1306));
		} else {
			response.setMessage(messageService.getMessage(MessageService.DISPLAY_NOT_INITIALISED));
		}

		return response;
	}

	/**
	 * Turn the display on.
	 *
	 * @return A JSON response containing the new on state of the display.
	 */
	@RequestMapping(path="/on", method=RequestMethod.POST)
	public JsonResponse<Boolean> displayOn() {
		log.info("======== displayOn");

		JsonResponse<Boolean> response = new JsonResponse<>();

		if(ssd1306.isInitialised()) {
			ssd1306.setDisplayOn(true);
			response.setOk(true);
			response.setResult(ssd1306.isDisplayOn());
		} else {
			response.setMessage(messageService.getMessage(MessageService.DISPLAY_NOT_INITIALISED));
		}

		return response;
	}

	/**
	 * Turn the display off.
	 *
	 * @return A JSON response containing the new on state of the display.
	 */
	@RequestMapping(path="/off", method=RequestMethod.POST)
	public JsonResponse<Boolean> displayOff() {
		log.info("======== displayOff");

		JsonResponse<Boolean> response = new JsonResponse<>();

		if(ssd1306.isInitialised()) {
			ssd1306.setDisplayOn(false);
			response.setOk(true);
			response.setResult(ssd1306.isDisplayOn());
		} else {
			response.setMessage(messageService.getMessage(MessageService.DISPLAY_NOT_INITIALISED));
		}

		return response;
	}

	/**
	 * Clear the display.
	 *
	 * @return An empty JSON response.
	 */
	@RequestMapping(path="/clear", method=RequestMethod.POST)
	public JsonResponse<Void> clear() {
		log.info("======== clear");

		JsonResponse<Void> response = new JsonResponse<>();

		if(ssd1306.isInitialised()) {
			ssd1306.clear();
			ssd1306.display();
			response.setOk(true);
		} else {
			response.setMessage(messageService.getMessage(MessageService.DISPLAY_NOT_INITIALISED));
		}

		return response;
	}

	/**
	 * Invert the display.
	 *
	 * @return A JSON response containing the new inverted state of the display.
	 */
	@RequestMapping(path="/invert", method=RequestMethod.POST)
	public JsonResponse<Boolean> invert() {
		log.info("======== invert");

		JsonResponse<Boolean> response = new JsonResponse<>();

		if(ssd1306.isInitialised()) {
			ssd1306.setInverted(!ssd1306.isInverted());
			response.setOk(true);
			response.setResult(ssd1306.isInverted());
		} else {
			response.setMessage(messageService.getMessage(MessageService.DISPLAY_NOT_INITIALISED));
		}

		return response;
	}

	/**
	 * Flip the display.
	 *
	 * @param request A JSON request containing the axis to flip.
	 *
	 * @return A JSON response containing the flip state of the specified axis.
	 */
	@RequestMapping(path="/flip", method=RequestMethod.POST)
	public JsonResponse<Boolean> flip(@RequestBody FlipRequest request) {
		log.info("======== flip");
		log.info("request.getAxis()={}", request.getAxis());

		JsonResponse<Boolean> response = new JsonResponse<>();

		if(ssd1306.isInitialised()) {
			if(request.getAxis().equals("h")) {
				ssd1306.setHFlipped(!ssd1306.isHFlipped());
				response.setOk(true);
				response.setResult(ssd1306.isHFlipped());
			} else if(request.getAxis().equals("v")) {
				ssd1306.setVFlipped(!ssd1306.isVFlipped());
				response.setOk(true);
				response.setResult(ssd1306.isVFlipped());
			} else {
				response.setMessage(messageService.getMessage(MessageService.DISPLAY_FLIP_INVALID_AXIS, request.getAxis()));
			}
		} else {
			response.setMessage(messageService.getMessage(MessageService.DISPLAY_NOT_INITIALISED));
		}

		return response;
	}

	/**
	 * Set the display contrast.
	 *
	 * @param request A JSON request containing the contrast level to set.
	 *
	 * @return A JSON response containing the new contrast level.
	 */
	@RequestMapping(path="/contrast", method=RequestMethod.POST)
	public JsonResponse<Integer> setContrast(@RequestBody SetContrastRequest request) {
		log.info("======== setContrast");
		log.info("request.getContrast()={}", request.getContrast());

		JsonResponse<Integer> response = new JsonResponse<>();

		if(ssd1306.isInitialised()) {
			if(request.getContrast() >= 0 && request.getContrast() < 256) {
				ssd1306.setContrast(request.getContrast());
				response.setOk(true);
				response.setResult(ssd1306.getContrast());
			} else {
				response.setMessage(messageService.getMessage(MessageService.DISPLAY_CONTRAST_INVALID_LEVEL, request.getContrast()));
			}
		} else {
			response.setMessage(messageService.getMessage(MessageService.DISPLAY_NOT_INITIALISED));
		}

		return response;
	}

	/**
	 * Set the display offset.
	 *
	 * @param request A JSON request containing the offset to set.
	 *
	 * @return A JSON response containing the new offset.
	 */
	@RequestMapping(path="/offset", method=RequestMethod.POST)
	public JsonResponse<Integer> setOffset(@RequestBody SetOffsetRequest request) {
		log.info("======== setOffset");
		log.info("request.getOffset()={}", request.getOffset());

		JsonResponse<Integer> response = new JsonResponse<>();

		if(ssd1306.isInitialised()) {
			if(request.getOffset() >= 0 && request.getOffset() < 64) {
				ssd1306.setOffset(request.getOffset());
				response.setOk(true);
				response.setResult(ssd1306.getOffset());
			} else {
				response.setMessage(messageService.getMessage(MessageService.DISPLAY_OFFSET_INVALID_OFFSET, request.getOffset()));
			}
		} else {
			response.setMessage(messageService.getMessage(MessageService.DISPLAY_NOT_INITIALISED));
		}

		return response;
	}

	/**
	 * Turn a single pixel on or off.
	 *
	 * @param request A JSON request containing the X, Y, and state of the pixel to set.
	 *
	 * @return A JSON response containing the new state of the pixel.
	 */
	@RequestMapping(path="/pixel", method=RequestMethod.POST)
	public JsonResponse<Boolean> setPixel(@RequestBody SetPixelRequest request) {
		log.info("======== setPixel");
		log.info("request.getX()={}", request.getX());
		log.info("request.getY()={}", request.getY());
		log.info("request.isOn()={}", request.isOn());

		JsonResponse<Boolean> response = new JsonResponse<>();

		if(ssd1306.isInitialised()) {
			if(request.getX() >= 0 && request.getX() < ssd1306.getWidth() && request.getY() >= 0 && request.getY() < ssd1306.getHeight()) {
				ssd1306.setPixel(request.getX(), request.getY(), request.isOn());
				ssd1306.display();
				response.setOk(true);
				response.setResult(ssd1306.getPixel(request.getX(), request.getY()));
			} else {
				response.setMessage(messageService.getMessage(MessageService.DISPLAY_PIXEL_INVALID_COORDINATES, request.getX(), request.getY()));
			}
		} else {
			response.setMessage(messageService.getMessage(MessageService.DISPLAY_NOT_INITIALISED));
		}

		return response;
	}

	/**
	 * Get the display buffer.
	 *
	 * @return A JSON response containing the width, height, and contents of the display buffer.
	 */
	@RequestMapping(path="/buffer", method=RequestMethod.GET)
	public JsonResponse<DisplayBuffer> getBuffer() {
		log.info("======== getBuffer");

		JsonResponse<DisplayBuffer> response = new JsonResponse<>();

		if(ssd1306.isInitialised()) {
			DisplayBuffer buffer = new DisplayBuffer(ssd1306.getWidth(), ssd1306.getHeight(), null);
			buffer.setBufferAsBytes(ssd1306.getBuffer());
			response.setOk(true);
			response.setResult(buffer);
		} else {
			response.setMessage(messageService.getMessage(MessageService.DISPLAY_NOT_INITIALISED));
		}

		return response;
	}

	/**
	 * Set the display buffer.
	 *
	 * @param buffer A JSON request containing the width, height, and contents of the buffer to set.
	 */
	@RequestMapping(path="/buffer", method=RequestMethod.POST)
	public JsonResponse<Void> setBuffer(@RequestBody DisplayBuffer buffer) {
		log.info("======== setBuffer");
		log.info("buffer.getWidth()={}", buffer.getWidth());
		log.info("buffer.getHeight()={}", buffer.getHeight());
		log.info("buffer.getBuffer().length={}", buffer.getBuffer().length);

		JsonResponse<Void> response = new JsonResponse<>();

		if(ssd1306.isInitialised()) {
			if(buffer.getBuffer().length == ssd1306.getBuffer().length) {
				response.setOk(true);
				ssd1306.setBuffer(buffer.getBufferAsBytes());
				ssd1306.display();
			} else {
				response.setMessage(messageService.getMessage(MessageService.DISPLAY_BUFFER_SIZE_MISMATCH, buffer.getBuffer().length, ssd1306.getBuffer().length));
			}
		} else {
			response.setMessage(messageService.getMessage(MessageService.DISPLAY_NOT_INITIALISED));
		}

		return response;
	}

	/**
	 * Setup and start scrolling the display.
	 *
	 * @param request A JSON request containing the scroll direction, area and speed.
	 *
	 * @return A JSON response containing the new scroll state.
	 */
	@RequestMapping(path="/scroll/start", method=RequestMethod.POST)
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

		if(ssd1306.isInitialised()) {
			if(!ssd1306.isScrolling()) {
				if(request.isVertical()) {
					ssd1306.scrollDiagonally(request.isLeft(), request.getStartPage(), request.getEndPage(), request.getOffset(), request.getRows(), request.getSpeed(), request.getStep());
				} else {
					ssd1306.scrollHorizontally(request.isLeft(), request.getStartPage(), request.getEndPage(), request.getSpeed());
				}

				ssd1306.startScroll();
				response.setOk(true);
				response.setResult(ssd1306.isScrolling());
			} else {
				response.setMessage(messageService.getMessage(MessageService.DISPLAY_SCROLL_ALREADY_ACTIVE));
			}
		} else {
			response.setMessage(messageService.getMessage(MessageService.DISPLAY_NOT_INITIALISED));
		}

		return response;
	}

	/**
	 * Stop scrolling the display.
	 *
	 * @return A JSON response containing the new scroll state.
	 */
	@RequestMapping(path="/scroll/stop", method=RequestMethod.POST)
	public JsonResponse<Boolean> stopScroll() {
		log.info("======== stopScroll");

		JsonResponse<Boolean> response = new JsonResponse<>();

		if(ssd1306.isInitialised()) {
			if(ssd1306.isScrolling()) {
				ssd1306.stopScroll();
				ssd1306.display();
				response.setOk(true);
				response.setResult(ssd1306.isScrolling());
			} else {
				response.setMessage(messageService.getMessage(MessageService.DISPLAY_SCROLL_NOT_ACTIVE));
			}
		} else {
			response.setMessage(messageService.getMessage(MessageService.DISPLAY_NOT_INITIALISED));
		}

		return response;
	}
}
