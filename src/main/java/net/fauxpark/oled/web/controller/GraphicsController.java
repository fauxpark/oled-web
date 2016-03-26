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
import net.fauxpark.oled.web.entity.request.DrawArcRequest;
import net.fauxpark.oled.web.entity.request.DrawCircleRequest;
import net.fauxpark.oled.web.entity.request.DrawLineRequest;
import net.fauxpark.oled.web.entity.request.DrawRectangleRequest;
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

	/**
	 * Draw text onto the display.
	 *
	 * @param request A JSON request containing the position and text to draw.
	 *
	 * @return An empty JSON response.
	 */
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

	/**
	 * Draw a line onto the display.
	 *
	 * @param request A JSON request containing the positions of the line points.
	 *
	 * @return An empty JSON response.
	 */
	@RequestMapping(value="/line", method=RequestMethod.POST)
	@ResponseBody
	public JsonResponse<Void> drawLine(@RequestBody DrawLineRequest request) {
		log.info("======== drawLine");
		log.info("request.getX0()={}", request.getX0());
		log.info("request.getY0()={}", request.getY0());
		log.info("request.getX1()={}", request.getX1());
		log.info("request.getY1()={}", request.getY1());

		JsonResponse<Void> response = new JsonResponse<>();
		response.setOk(true);
		graphics.line(request.getX0(), request.getY0(), request.getX1(), request.getY1());
		ssd1306.display();

		return response;
	}

	/**
	 * Draw a rectangle onto the display.
	 *
	 * @param request A JSON request containing the position and dimensions of the rectangle.
	 *
	 * @return An empty JSON response.
	 */
	@RequestMapping(value="/rectangle", method=RequestMethod.POST)
	@ResponseBody
	public JsonResponse<Void> drawRectangle(@RequestBody DrawRectangleRequest request) {
		log.info("======== drawRectangle");
		log.info("request.getX()={}", request.getX());
		log.info("request.getY()={}", request.getY());
		log.info("request.getWidth()={}", request.getWidth());
		log.info("request.getHeight()={}", request.getHeight());
		log.info("request.isFilled()={}", request.isFilled());

		JsonResponse<Void> response = new JsonResponse<>();
		response.setOk(true);
		graphics.rectangle(request.getX(), request.getY(), request.getWidth(), request.getHeight(), request.isFilled());
		ssd1306.display();

		return response;
	}

	/**
	 * Draw an arc onto the display.
	 *
	 * @param request A JSON request containing the position, radius and angles of the arc.
	 *
	 * @return An empty JSON response.
	 */
	@RequestMapping(value="/arc", method=RequestMethod.POST)
	@ResponseBody
	public JsonResponse<Void> drawArc(@RequestBody DrawArcRequest request) {
		log.info("======== drawArc");
		log.info("request.getX()={}", request.getX());
		log.info("request.getY()={}", request.getY());
		log.info("request.getRadius()={}", request.getRadius());
		log.info("request.getStartAngle()={}", request.getStartAngle());
		log.info("request.getEndAngle()={}", request.getEndAngle());

		JsonResponse<Void> response = new JsonResponse<>();
		response.setOk(true);
		graphics.arc(request.getX(), request.getY(), request.getRadius(), request.getStartAngle(), request.getEndAngle());
		ssd1306.display();

		return response;
	}

	/**
	 * Draw a circle onto the display.
	 *
	 * @param request A JSON request containing the position and radius of the circle.
	 *
	 * @return An empty JSON response.
	 */
	@RequestMapping(value="/circle", method=RequestMethod.POST)
	@ResponseBody
	public JsonResponse<Void> drawCircle(@RequestBody DrawCircleRequest request) {
		log.info("======== drawCircle");
		log.info("request.getX()={}", request.getX());
		log.info("request.getY()={}", request.getY());
		log.info("request.getRadius()={}", request.getRadius());

		JsonResponse<Void> response = new JsonResponse<>();
		response.setOk(true);
		graphics.circle(request.getX(), request.getY(), request.getRadius());
		ssd1306.display();

		return response;
	}
}
