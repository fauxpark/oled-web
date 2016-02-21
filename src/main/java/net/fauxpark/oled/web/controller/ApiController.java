package net.fauxpark.oled.web.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.fauxpark.oled.SSD1306;
import net.fauxpark.oled.web.entity.JsonResponse;
import net.fauxpark.oled.web.entity.request.SetContrastRequest;
import net.fauxpark.oled.web.entity.request.SetPixelRequest;
import net.fauxpark.oled.web.factory.SSD1306Factory;

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
	 * Turn the display on.
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
}
