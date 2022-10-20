package net.fauxpark.oled.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * A controller which serves the main page.
 *
 * @author fauxpark
 */
@RestController
@RequestMapping
public class MainController {
	private static final Logger log = LoggerFactory.getLogger(MainController.class);

	@Value("${build.version}")
	private String buildVersion;

	@Value("${build.sha}")
	private String buildSha;

	/**
	 * Show the index page.
	 */
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView index() {
		log.info("======== index");

		ModelAndView mav = new ModelAndView("index");
		mav.addObject("buildVersion", buildVersion);
		mav.addObject("buildSha", buildSha);

		return mav;
	}
}
