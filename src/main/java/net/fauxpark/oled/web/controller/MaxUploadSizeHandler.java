package net.fauxpark.oled.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import net.fauxpark.oled.web.entity.JsonResponse;
import net.fauxpark.oled.web.service.MessageService;

@ControllerAdvice
public class MaxUploadSizeHandler {
    private static final Logger log = LoggerFactory.getLogger(ApiController.class);

    private final MessageService messageService;

    public MaxUploadSizeHandler(MessageService messageService) {
        this.messageService = messageService;
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseBody
    public JsonResponse<Void> handleMaxSizeException(MaxUploadSizeExceededException ignoredEx) {
        log.info("======== handleMaxSizeException");

        JsonResponse<Void> response = new JsonResponse<>();
        response.setMessage(messageService.getMessage(MessageService.DISPLAY_IMAGE_SIZE_EXCEEDED));

        return response;
    }
}
