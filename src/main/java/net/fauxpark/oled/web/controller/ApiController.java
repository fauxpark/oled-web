package net.fauxpark.oled.web.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.fauxpark.oled.SSD1306;
import net.fauxpark.oled.web.entity.JsonResponse;
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
	public @ResponseBody JsonResponse<Void> startup() {
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
	public @ResponseBody JsonResponse<Void> shutdown() {
		log.info("======== shutdown");

		JsonResponse<Void> response = new JsonResponse<>();
		response.setOk(true);
		ssd1306.shutdown();

		return response;
	}

	/**
	 * Invert the display.
	 */
	@RequestMapping(value="/invert", method=RequestMethod.POST)
	public @ResponseBody JsonResponse<Boolean> invert() {
		log.info("======== invert");

		JsonResponse<Boolean> response = new JsonResponse<>();
		response.setOk(true);
		ssd1306.setInverted(!ssd1306.isInverted());
		response.setResult(ssd1306.isInverted());

		return response;
	}
}
