package net.fauxpark.oled.web.controller;

import java.io.IOException;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import net.fauxpark.oled.SSD1306;
import net.fauxpark.oled.font.Font;
import net.fauxpark.oled.font.CodePage1252;
import net.fauxpark.oled.font.CodePage437;
import net.fauxpark.oled.font.CodePage850;
import net.fauxpark.oled.web.entity.JsonResponse;
import net.fauxpark.oled.web.entity.request.DrawArcRequest;
import net.fauxpark.oled.web.entity.request.DrawCircleRequest;
import net.fauxpark.oled.web.entity.request.DrawImageRequest;
import net.fauxpark.oled.web.entity.request.DrawLineRequest;
import net.fauxpark.oled.web.entity.request.DrawRectangleRequest;
import net.fauxpark.oled.web.entity.request.DrawTextRequest;
import net.fauxpark.oled.web.service.MessageService;

/**
 * A controller which handles more advanced graphics-related requests to the SSD1306.
 *
 * @author fauxpark
 */
@RestController
@RequestMapping("/api/v1/graphics")
public class GraphicsController {
	private static final Logger log = LoggerFactory.getLogger(ApiController.class);

	@Autowired
	private MessageService messageService;

	@Autowired
	private SSD1306 ssd1306;

	/**
	 * Draw text onto the display.
	 *
	 * @param request A JSON request containing the position and text to draw.
	 *
	 * @return An empty JSON response.
	 */
	@RequestMapping(path="/text", method=RequestMethod.POST)
	public JsonResponse<Void> drawText(@RequestBody DrawTextRequest request) {
		log.info("======== drawText");
		log.info("request.getX()={}", request.getX());
		log.info("request.getY()={}", request.getY());
		log.info("request.getText()={}", request.getText());
		log.info("request.getFont()={}", request.getFont());

		JsonResponse<Void> response = new JsonResponse<>();

		if(ssd1306.isInitialised()) {
			Font font = null;

			// TODO: Find some way to not hardcode this!
			switch(request.getFont()) {
				case "cp437":
					font = new CodePage437();

					break;
				case "cp850":
					font = new CodePage850();

					break;
				case "cp1252":
					font = new CodePage1252();
			}

			if(font != null) {
				ssd1306.getGraphics().text(request.getX(), request.getY(), font, request.getText());
				ssd1306.display();
				response.setOk(true);
			} else {
				response.setMessage(messageService.getMessage(MessageService.DISPLAY_FONT_INVALID_FONT, request.getFont()));
			}
		} else {
			response.setMessage(messageService.getMessage(MessageService.DISPLAY_NOT_INITIALISED));
		}

		return response;
	}

	/**
	 * Upload and display an image.
	 *
	 * @param request A JSON request containing the position and dimensions of the image.
	 * @param file The image file to display.
	 *
	 * @return An empty JSON response.
	 */
	@RequestMapping(path="/image", method=RequestMethod.POST)
	public JsonResponse<Void> drawImage(@RequestPart("request") DrawImageRequest request, @RequestPart("file") MultipartFile file) {
		log.info("======== drawImage");
		log.info("request.getX()={}", request.getX());
		log.info("request.getY()={}", request.getY());
		log.info("request.getWidth()={}", request.getWidth());
		log.info("request.getHeight()={}", request.getHeight());
		log.info("file.getSize()={}", file.getSize());
		log.info("file.getOriginalFilename()={}", file.getOriginalFilename());

		JsonResponse<Void> response = new JsonResponse<>();

		if(ssd1306.isInitialised()) {
			if(request.getWidth() > 0 && request.getHeight() > 0) {
				try {
					ssd1306.getGraphics().image(ImageIO.read(file.getInputStream()), request.getX(), request.getY(), request.getWidth(), request.getHeight());
					ssd1306.display();
					response.setOk(true);
				} catch(IOException e) {
					response.setMessage(e.getMessage());
				}
			} else {
				response.setMessage(messageService.getMessage(MessageService.DISPLAY_IMAGE_INVALID_DIMENSIONS));
			}
		} else {
			response.setMessage(messageService.getMessage(MessageService.DISPLAY_NOT_INITIALISED));
		}

		return response;
	}

	/**
	 * Draw a line onto the display.
	 *
	 * @param request A JSON request containing the positions of the line points.
	 *
	 * @return An empty JSON response.
	 */
	@RequestMapping(path="/line", method=RequestMethod.POST)
	public JsonResponse<Void> drawLine(@RequestBody DrawLineRequest request) {
		log.info("======== drawLine");
		log.info("request.getX0()={}", request.getX0());
		log.info("request.getY0()={}", request.getY0());
		log.info("request.getX1()={}", request.getX1());
		log.info("request.getY1()={}", request.getY1());

		JsonResponse<Void> response = new JsonResponse<>();

		if(ssd1306.isInitialised()) {
			ssd1306.getGraphics().line(request.getX0(), request.getY0(), request.getX1(), request.getY1());
			ssd1306.display();
			response.setOk(true);
		} else {
			response.setMessage(messageService.getMessage(MessageService.DISPLAY_NOT_INITIALISED));
		}

		return response;
	}

	/**
	 * Draw a rectangle onto the display.
	 *
	 * @param request A JSON request containing the position and dimensions of the rectangle.
	 *
	 * @return An empty JSON response.
	 */
	@RequestMapping(path="/rectangle", method=RequestMethod.POST)
	public JsonResponse<Void> drawRectangle(@RequestBody DrawRectangleRequest request) {
		log.info("======== drawRectangle");
		log.info("request.getX()={}", request.getX());
		log.info("request.getY()={}", request.getY());
		log.info("request.getWidth()={}", request.getWidth());
		log.info("request.getHeight()={}", request.getHeight());
		log.info("request.isFilled()={}", request.isFilled());

		JsonResponse<Void> response = new JsonResponse<>();

		if(ssd1306.isInitialised()) {
			if(request.getWidth() > 0 && request.getHeight() > 0) {
				ssd1306.getGraphics().rectangle(request.getX(), request.getY(), request.getWidth(), request.getHeight(), request.isFilled());
				ssd1306.display();
				response.setOk(true);
			} else {
				response.setMessage(messageService.getMessage(MessageService.DISPLAY_RECTANGLE_INVALID_DIMENSIONS));
			}
		} else {
			response.setMessage(messageService.getMessage(MessageService.DISPLAY_NOT_INITIALISED));
		}

		return response;
	}

	/**
	 * Draw an arc onto the display.
	 *
	 * @param request A JSON request containing the position, radius and angles of the arc.
	 *
	 * @return An empty JSON response.
	 */
	@RequestMapping(path="/arc", method=RequestMethod.POST)
	public JsonResponse<Void> drawArc(@RequestBody DrawArcRequest request) {
		log.info("======== drawArc");
		log.info("request.getX()={}", request.getX());
		log.info("request.getY()={}", request.getY());
		log.info("request.getRadius()={}", request.getRadius());
		log.info("request.getStartAngle()={}", request.getStartAngle());
		log.info("request.getEndAngle()={}", request.getEndAngle());

		JsonResponse<Void> response = new JsonResponse<>();

		if(ssd1306.isInitialised()) {
			if(request.getRadius() >= 0) {
				ssd1306.getGraphics().arc(request.getX(), request.getY(), request.getRadius(), request.getStartAngle(), request.getEndAngle());
				ssd1306.display();
				response.setOk(true);
			} else {
				response.setMessage(messageService.getMessage(MessageService.DISPLAY_ARC_INVALID_RADIUS));
			}
		} else {
			response.setMessage(messageService.getMessage(MessageService.DISPLAY_NOT_INITIALISED));
		}

		return response;
	}

	/**
	 * Draw a circle onto the display.
	 *
	 * @param request A JSON request containing the position and radius of the circle.
	 *
	 * @return An empty JSON response.
	 */
	@RequestMapping(path="/circle", method=RequestMethod.POST)
	public JsonResponse<Void> drawCircle(@RequestBody DrawCircleRequest request) {
		log.info("======== drawCircle");
		log.info("request.getX()={}", request.getX());
		log.info("request.getY()={}", request.getY());
		log.info("request.getRadius()={}", request.getRadius());

		JsonResponse<Void> response = new JsonResponse<>();

		if(ssd1306.isInitialised()) {
			if(request.getRadius() >= 0) {
				ssd1306.getGraphics().circle(request.getX(), request.getY(), request.getRadius());
				ssd1306.display();
				response.setOk(true);
			} else {
				response.setMessage(messageService.getMessage(MessageService.DISPLAY_CIRCLE_INVALID_RADIUS));
			}
		} else {
			response.setMessage(messageService.getMessage(MessageService.DISPLAY_NOT_INITIALISED));
		}

		return response;
	}
}
