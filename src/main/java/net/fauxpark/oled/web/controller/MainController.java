package net.fauxpark.oled.web.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * A controller which serves the main page.
 *
 * @author fauxpark
 */
@Controller
@RequestMapping
@Slf4j
public class MainController {
    @Value("${build.version}")
    private String buildVersion;

    @Value("${build.sha}")
    private String buildSha;

    /**
     * Show the index page.
     */
    @GetMapping
    public ModelAndView index() {
        log.info("======== index");

        ModelAndView mav = new ModelAndView("index");
        mav.addObject("buildVersion", buildVersion);
        mav.addObject("buildSha", buildSha);

        return mav;
    }
}
