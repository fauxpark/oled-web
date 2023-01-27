package net.fauxpark.oled.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import net.fauxpark.oled.SSD1306;
import net.fauxpark.oled.web.entity.DisplayBuffer;
import net.fauxpark.oled.web.entity.DisplayState;
import net.fauxpark.oled.web.entity.HealthCheck;
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
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class ApiController {
    private final MessageService messageService;

    private final SSD1306 ssd1306;

    @Value("${build.version}")
    private String buildVersion;

    @Value("${build.sha}")
    private String buildSha;

    /** API Healthcheck.
     *
     * @return A JSON response containing the API status and version.
     */
    @GetMapping
    public HealthCheck healthCheck() {
        log.info("======= healthCheck");

        return HealthCheck.builder()
            .status("running")
            .version(buildVersion + "-" + buildSha)
            .build();
    }

    /**
     * Get the display state.
     *
     * @return A JSON response containing the display state.
     */
    @GetMapping("/state")
    public JsonResponse<DisplayState> getState() {
        log.info("======== getState");

        return JsonResponse.<DisplayState>builder()
            .ok(true)
            .result(DisplayState.from(ssd1306))
            .build();
    }

    /**
     * Begin the startup procedure for the display.
     *
     * @return A JSON response containing the display state.
     */
    @PostMapping("/startup")
    public JsonResponse<DisplayState> startup() {
        log.info("======== startup");

        var builder = JsonResponse.<DisplayState>builder();

        if(!ssd1306.isInitialised()) {
            ssd1306.startup(false);
            builder
                .ok(true)
                .result(DisplayState.from(ssd1306));
        } else {
            builder.message(messageService.getMessage(MessageService.DISPLAY_ALREADY_INITIALISED));
        }

        return builder.build();
    }

    /**
     * Begin the shutdown procedure for the display.
     *
     * @return A JSON response containing the display state.
     */
    @PostMapping("/shutdown")
    public JsonResponse<DisplayState> shutdown() {
        log.info("======== shutdown");

        var builder = JsonResponse.<DisplayState>builder();

        if(ssd1306.isInitialised()) {
            ssd1306.shutdown();
            builder
                .ok(true)
                .result(DisplayState.from(ssd1306));
        } else {
            builder.message(messageService.getMessage(MessageService.DISPLAY_NOT_INITIALISED));
        }

        return builder.build();
    }

    /**
     * Turn the display on.
     *
     * @return A JSON response containing the new on state of the display.
     */
    @PostMapping("/on")
    public JsonResponse<Boolean> displayOn() {
        log.info("======== displayOn");

        var builder = JsonResponse.<Boolean>builder();

        if(ssd1306.isInitialised()) {
            ssd1306.setDisplayOn(true);
            builder
                .ok(true)
                .result(ssd1306.isDisplayOn());
        } else {
            builder.message(messageService.getMessage(MessageService.DISPLAY_NOT_INITIALISED));
        }

        return builder.build();
    }

    /**
     * Turn the display off.
     *
     * @return A JSON response containing the new on state of the display.
     */
    @PostMapping("/off")
    public JsonResponse<Boolean> displayOff() {
        log.info("======== displayOff");

        var builder = JsonResponse.<Boolean>builder();

        if(ssd1306.isInitialised()) {
            ssd1306.setDisplayOn(false);
            builder
                .ok(true)
                .result(ssd1306.isDisplayOn());
        } else {
            builder.message(messageService.getMessage(MessageService.DISPLAY_NOT_INITIALISED));
        }

        return builder.build();
    }

    /**
     * Clear the display.
     *
     * @return An empty JSON response.
     */
    @PostMapping("/clear")
    public JsonResponse<Void> clear() {
        log.info("======== clear");

        var builder = JsonResponse.<Void>builder();

        if(ssd1306.isInitialised()) {
            ssd1306.clear();
            ssd1306.display();
            builder.ok(true);
        } else {
            builder.message(messageService.getMessage(MessageService.DISPLAY_NOT_INITIALISED));
        }

        return builder.build();
    }

    /**
     * Invert the display.
     *
     * @return A JSON response containing the new inverted state of the display.
     */
    @PostMapping("/invert")
    public JsonResponse<Boolean> invert() {
        log.info("======== invert");

        var builder = JsonResponse.<Boolean>builder();

        if(ssd1306.isInitialised()) {
            ssd1306.setInverted(!ssd1306.isInverted());
            builder
                .ok(true)
                .result(ssd1306.isInverted());
        } else {
            builder.message(messageService.getMessage(MessageService.DISPLAY_NOT_INITIALISED));
        }

        return builder.build();
    }

    /**
     * Flip the display.
     *
     * @param request A JSON request containing the axis to flip.
     *
     * @return A JSON response containing the flip state of the specified axis.
     */
    @PostMapping("/flip")
    public JsonResponse<Boolean> flip(@RequestBody FlipRequest request) {
        log.info("======== flip");
        log.info("request={}", request);

        var builder = JsonResponse.<Boolean>builder();

        if(ssd1306.isInitialised()) {
            if(request.getAxis().equals("h")) {
                ssd1306.setHFlipped(!ssd1306.isHFlipped());
                builder
                    .ok(true)
                    .result(ssd1306.isHFlipped());
            } else if(request.getAxis().equals("v")) {
                ssd1306.setVFlipped(!ssd1306.isVFlipped());
                builder
                    .ok(true)
                    .result(ssd1306.isVFlipped());
            } else {
                builder.message(messageService.getMessage(MessageService.DISPLAY_FLIP_INVALID_AXIS, request.getAxis()));
            }
        } else {
            builder.message(messageService.getMessage(MessageService.DISPLAY_NOT_INITIALISED));
        }

        return builder.build();
    }

    /**
     * Set the display contrast.
     *
     * @param request A JSON request containing the contrast level to set.
     *
     * @return A JSON response containing the new contrast level.
     */
    @PostMapping("/contrast")
    public JsonResponse<Integer> setContrast(@RequestBody SetContrastRequest request) {
        log.info("======== setContrast");
        log.info("request={}", request);

        var builder = JsonResponse.<Integer>builder();

        if(ssd1306.isInitialised()) {
            if(request.getContrast() >= 0 && request.getContrast() < 256) {
                ssd1306.setContrast(request.getContrast());
                builder
                    .ok(true)
                    .result(ssd1306.getContrast());
            } else {
                builder.message(messageService.getMessage(MessageService.DISPLAY_CONTRAST_INVALID_LEVEL, request.getContrast()));
            }
        } else {
            builder.message(messageService.getMessage(MessageService.DISPLAY_NOT_INITIALISED));
        }

        return builder.build();
    }

    /**
     * Set the display offset.
     *
     * @param request A JSON request containing the offset to set.
     *
     * @return A JSON response containing the new offset.
     */
    @PostMapping("/offset")
    public JsonResponse<Integer> setOffset(@RequestBody SetOffsetRequest request) {
        log.info("======== setOffset");
        log.info("request={}", request);

        var builder = JsonResponse.<Integer>builder();

        if(ssd1306.isInitialised()) {
            if(request.getOffset() >= 0 && request.getOffset() < 64) {
                ssd1306.setOffset(request.getOffset());
                builder
                    .ok(true)
                    .result(ssd1306.getOffset());
            } else {
                builder.message(messageService.getMessage(MessageService.DISPLAY_OFFSET_INVALID_OFFSET, request.getOffset()));
            }
        } else {
            builder.message(messageService.getMessage(MessageService.DISPLAY_NOT_INITIALISED));
        }

        return builder.build();
    }

    /**
     * Turn a single pixel on or off.
     *
     * @param request A JSON request containing the X, Y, and state of the pixel to set.
     *
     * @return A JSON response containing the new state of the pixel.
     */
    @PostMapping("/pixel")
    public JsonResponse<Boolean> setPixel(@RequestBody SetPixelRequest request) {
        log.info("======== setPixel");
        log.info("request={}", request);

        var builder = JsonResponse.<Boolean>builder();

        if(ssd1306.isInitialised()) {
            if(request.getX() >= 0 && request.getX() < ssd1306.getWidth() && request.getY() >= 0 && request.getY() < ssd1306.getHeight()) {
                ssd1306.setPixel(request.getX(), request.getY(), request.isOn());
                ssd1306.display();
                builder
                    .ok(true)
                    .result(ssd1306.getPixel(request.getX(), request.getY()));
            } else {
                builder.message(messageService.getMessage(MessageService.DISPLAY_PIXEL_INVALID_COORDINATES, request.getX(), request.getY()));
            }
        } else {
            builder.message(messageService.getMessage(MessageService.DISPLAY_NOT_INITIALISED));
        }

        return builder.build();
    }

    /**
     * Get the display buffer.
     *
     * @return A JSON response containing the width, height, and contents of the display buffer.
     */
    @GetMapping("/buffer")
    public JsonResponse<DisplayBuffer> getBuffer() {
        log.info("======== getBuffer");

        var builder = JsonResponse.<DisplayBuffer>builder();

        if(ssd1306.isInitialised()) {
            var buffer = DisplayBuffer.builder()
                .width(ssd1306.getWidth())
                .height(ssd1306.getHeight())
                .build();
            buffer.setBufferAsBytes(ssd1306.getBuffer());
            builder
                .ok(true)
                .result(buffer);
        } else {
            builder.message(messageService.getMessage(MessageService.DISPLAY_NOT_INITIALISED));
        }

        return builder.build();
    }

    /**
     * Set the display buffer.
     *
     * @param buffer A JSON request containing the width, height, and contents of the buffer to set.
     */
    @PostMapping("/buffer")
    public JsonResponse<Void> setBuffer(@RequestBody DisplayBuffer buffer) {
        log.info("======== setBuffer");
        log.info("buffer.getWidth()={}", buffer.getWidth());
        log.info("buffer.getHeight()={}", buffer.getHeight());
        log.info("buffer.getBuffer().length={}", buffer.getBuffer().length);

        var builder = JsonResponse.<Void>builder();

        if(ssd1306.isInitialised()) {
            if(buffer.getBuffer().length == ssd1306.getBuffer().length) {
                builder.ok(true);
                ssd1306.setBuffer(buffer.getBufferAsBytes());
                ssd1306.display();
            } else {
                builder.message(messageService.getMessage(MessageService.DISPLAY_BUFFER_SIZE_MISMATCH, buffer.getBuffer().length, ssd1306.getBuffer().length));
            }
        } else {
            builder.message(messageService.getMessage(MessageService.DISPLAY_NOT_INITIALISED));
        }

        return builder.build();
    }

    /**
     * Setup and start scrolling the display.
     *
     * @param request A JSON request containing the scroll direction, area and speed.
     *
     * @return A JSON response containing the new scroll state.
     */
    @PostMapping("/scroll/start")
    public JsonResponse<Boolean> startScroll(@RequestBody ScrollRequest request) {
        log.info("======== startScroll");
        log.info("request={}", request);

        var builder = JsonResponse.<Boolean>builder();

        if(ssd1306.isInitialised()) {
            if(!ssd1306.isScrolling()) {
                if(request.isVertical()) {
                    ssd1306.scrollDiagonally(request.isLeft(), request.getStartPage(), request.getEndPage(), request.getOffset(), request.getRows(), request.getSpeed(), request.getStep());
                } else {
                    ssd1306.scrollHorizontally(request.isLeft(), request.getStartPage(), request.getEndPage(), request.getSpeed());
                }

                ssd1306.startScroll();
                builder
                    .ok(true)
                    .result(ssd1306.isScrolling());
            } else {
                builder.message(messageService.getMessage(MessageService.DISPLAY_SCROLL_ALREADY_ACTIVE));
            }
        } else {
            builder.message(messageService.getMessage(MessageService.DISPLAY_NOT_INITIALISED));
        }

        return builder.build();
    }

    /**
     * Stop scrolling the display.
     *
     * @return A JSON response containing the new scroll state.
     */
    @PostMapping("/scroll/stop")
    public JsonResponse<Boolean> stopScroll() {
        log.info("======== stopScroll");

        var builder = JsonResponse.<Boolean>builder();

        if(ssd1306.isInitialised()) {
            if(ssd1306.isScrolling()) {
                ssd1306.stopScroll();
                ssd1306.display();
                builder
                    .ok(true)
                    .result(ssd1306.isScrolling());
            } else {
                builder.message(messageService.getMessage(MessageService.DISPLAY_SCROLL_NOT_ACTIVE));
            }
        } else {
            builder.message(messageService.getMessage(MessageService.DISPLAY_NOT_INITIALISED));
        }

        return builder.build();
    }
}
