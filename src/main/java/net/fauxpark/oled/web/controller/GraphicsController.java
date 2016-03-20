package net.fauxpark.oled.web.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.fauxpark.oled.Graphics;
import net.fauxpark.oled.SSD1306;
import net.fauxpark.oled.font.CodePage850;
import net.fauxpark.oled.web.entity.JsonResponse;
import net.fauxpark.oled.web.entity.request.DrawTextRequest;
import net.fauxpark.oled.web.factory.SSD1306Factory;

/**
 * A controller which handles graphics-related requests to the SSD1306.
 *
 * @author fauxpark
 */
@Controller
@RequestMapping("/graphics")
public class GraphicsController {
	private static final Logger log = LogManager.getLogger(ApiController.class);

	private final SSD1306 ssd1306 = SSD1306Factory.getInstance();

	private final Graphics graphics = new Graphics(ssd1306);

	@RequestMapping(value="/text", method=RequestMethod.POST)
	@ResponseBody
	public JsonResponse<Void> drawText(@RequestBody DrawTextRequest request) {
		log.info("======== drawText");
		log.info("request.getX()={}", request.getX());
		log.info("request.getY()={}", request.getY());
		log.info("request.getText()={}", request.getText());

		JsonResponse<Void> response = new JsonResponse<>();
		response.setOk(true);
		graphics.text(request.getX(), request.getY(), new CodePage850(), request.getText());
		ssd1306.display();

		return response;
	}
}
