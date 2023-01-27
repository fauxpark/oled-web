package net.fauxpark.oled.web.controller;

import java.io.IOException;

import javax.imageio.ImageIO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;
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
@RequiredArgsConstructor
@Slf4j
public class GraphicsController {
    private final MessageService messageService;

    private final SSD1306 ssd1306;

    /**
     * Draw text onto the display.
     *
     * @param request A JSON request containing the position and text to draw.
     *
     * @return An empty JSON response.
     */
    @PostMapping("/text")
    public JsonResponse<Void> drawText(@RequestBody DrawTextRequest request) {
        log.info("======== drawText");
        log.info("request={}", request);

        var builder = JsonResponse.<Void>builder();

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
                builder.ok(true);
            } else {
                builder.message(messageService.getMessage(MessageService.DISPLAY_FONT_INVALID_FONT, request.getFont()));
            }
        } else {
            builder.message(messageService.getMessage(MessageService.DISPLAY_NOT_INITIALISED));
        }

        return builder.build();
    }

    /**
     * Upload and display an image.
     *
     * @param request A JSON request containing the position and dimensions of the image.
     * @param file The image file to display.
     *
     * @return An empty JSON response.
     */
    @PostMapping("/image")
    public JsonResponse<Void> drawImage(@RequestPart("request") DrawImageRequest request, @RequestPart("file") MultipartFile file) {
        log.info("======== drawImage");
        log.info("request={}", request);
        log.info("file.getSize()={}", file.getSize());
        log.info("file.getOriginalFilename()={}", file.getOriginalFilename());

        var builder = JsonResponse.<Void>builder();

        if(ssd1306.isInitialised()) {
            if(request.getWidth() > 0 && request.getHeight() > 0) {
                try {
                    ssd1306.getGraphics().image(ImageIO.read(file.getInputStream()), request.getX(), request.getY(), request.getWidth(), request.getHeight());
                    ssd1306.display();
                    builder.ok(true);
                } catch(IOException e) {
                    builder.message(e.getMessage());
                }
            } else {
                builder.message(messageService.getMessage(MessageService.DISPLAY_IMAGE_INVALID_DIMENSIONS));
            }
        } else {
            builder.message(messageService.getMessage(MessageService.DISPLAY_NOT_INITIALISED));
        }

        return builder.build();
    }

    /**
     * Draw a line onto the display.
     *
     * @param request A JSON request containing the positions of the line points.
     *
     * @return An empty JSON response.
     */
    @PostMapping("/line")
    public JsonResponse<Void> drawLine(@RequestBody DrawLineRequest request) {
        log.info("======== drawLine");
        log.info("request={}", request);

        var builder = JsonResponse.<Void>builder();

        if(ssd1306.isInitialised()) {
            ssd1306.getGraphics().line(request.getX0(), request.getY0(), request.getX1(), request.getY1());
            ssd1306.display();
            builder.ok(true);
        } else {
            builder.message(messageService.getMessage(MessageService.DISPLAY_NOT_INITIALISED));
        }

        return builder.build();
    }

    /**
     * Draw a rectangle onto the display.
     *
     * @param request A JSON request containing the position and dimensions of the rectangle.
     *
     * @return An empty JSON response.
     */
    @PostMapping("/rectangle")
    public JsonResponse<Void> drawRectangle(@RequestBody DrawRectangleRequest request) {
        log.info("======== drawRectangle");
        log.info("request={}", request);

        var builder = JsonResponse.<Void>builder();

        if(ssd1306.isInitialised()) {
            if(request.getWidth() > 0 && request.getHeight() > 0) {
                ssd1306.getGraphics().rectangle(request.getX(), request.getY(), request.getWidth(), request.getHeight(), request.isFilled());
                ssd1306.display();
                builder.ok(true);
            } else {
                builder.message(messageService.getMessage(MessageService.DISPLAY_RECTANGLE_INVALID_DIMENSIONS));
            }
        } else {
            builder.message(messageService.getMessage(MessageService.DISPLAY_NOT_INITIALISED));
        }

        return builder.build();
    }

    /**
     * Draw an arc onto the display.
     *
     * @param request A JSON request containing the position, radius and angles of the arc.
     *
     * @return An empty JSON response.
     */
    @PostMapping("/arc")
    public JsonResponse<Void> drawArc(@RequestBody DrawArcRequest request) {
        log.info("======== drawArc");
        log.info("request={}", request);

        var builder = JsonResponse.<Void>builder();

        if(ssd1306.isInitialised()) {
            if(request.getRadius() >= 0) {
                ssd1306.getGraphics().arc(request.getX(), request.getY(), request.getRadius(), request.getStartAngle(), request.getEndAngle());
                ssd1306.display();
                builder.ok(true);
            } else {
                builder.message(messageService.getMessage(MessageService.DISPLAY_ARC_INVALID_RADIUS));
            }
        } else {
            builder.message(messageService.getMessage(MessageService.DISPLAY_NOT_INITIALISED));
        }

        return builder.build();
    }

    /**
     * Draw a circle onto the display.
     *
     * @param request A JSON request containing the position and radius of the circle.
     *
     * @return An empty JSON response.
     */
    @PostMapping("/circle")
    public JsonResponse<Void> drawCircle(@RequestBody DrawCircleRequest request) {
        log.info("======== drawCircle");
        log.info("request={}", request);

        var builder = JsonResponse.<Void>builder();

        if(ssd1306.isInitialised()) {
            if(request.getRadius() >= 0) {
                ssd1306.getGraphics().circle(request.getX(), request.getY(), request.getRadius());
                ssd1306.display();
                builder.ok(true);
            } else {
                builder.message(messageService.getMessage(MessageService.DISPLAY_CIRCLE_INVALID_RADIUS));
            }
        } else {
            builder.message(messageService.getMessage(MessageService.DISPLAY_NOT_INITIALISED));
        }

        return builder.build();
    }
}
