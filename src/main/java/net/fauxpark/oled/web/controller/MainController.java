package net.fauxpark.oled.web.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * A controller which handles serving webapp pages.
 *
 * @author fauxpark
 */
@Controller
@RequestMapping("/")
public class MainController {
	private static final Logger log = LogManager.getLogger(MainController.class);

	/**
	 * Show the index page.
	 */
	@RequestMapping("/")
	public String index() {
		log.info("======== index");

		return "index";
	}
}
