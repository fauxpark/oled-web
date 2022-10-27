package net.fauxpark.oled.web.service;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    public static final String DISPLAY_ALREADY_INITIALISED = "display.alreadyinitialised";

    public static final String DISPLAY_NOT_INITIALISED = "display.notinitialised";

    public static final String DISPLAY_FLIP_INVALID_AXIS = "display.flip.invalidaxis";

    public static final String DISPLAY_CONTRAST_INVALID_LEVEL = "display.contrast.invalidlevel";

    public static final String DISPLAY_OFFSET_INVALID_OFFSET = "display.offset.invalidoffset";

    public static final String DISPLAY_PIXEL_INVALID_COORDINATES = "display.pixel.invalidcoordinates";

    public static final String DISPLAY_BUFFER_SIZE_MISMATCH = "display.buffer.sizemismatch";

    public static final String DISPLAY_SCROLL_ALREADY_ACTIVE = "display.scroll.alreadyactive";

    public static final String DISPLAY_SCROLL_NOT_ACTIVE = "display.scroll.notactive";

    public static final String DISPLAY_IMAGE_INVALID_DIMENSIONS = "display.image.invaliddimensions";

    public static final String DISPLAY_IMAGE_SIZE_EXCEEDED = "display.image.sizeexceeded";

    public static final String DISPLAY_FONT_INVALID_FONT = "display.font.invalidfont";

    public static final String DISPLAY_RECTANGLE_INVALID_DIMENSIONS = "display.rectangle.invaliddimensions";

    public static final String DISPLAY_ARC_INVALID_RADIUS = "display.arc.negativeradius";

    public static final String DISPLAY_CIRCLE_INVALID_RADIUS = "display.circle.negativeradius";

    private final MessageSource messageSource;

    public MessageService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String key, Object... args) {
        return messageSource.getMessage(key, args, Locale.getDefault());
    }
}
