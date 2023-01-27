package net.fauxpark.oled.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import net.fauxpark.oled.web.entity.JsonResponse;
import net.fauxpark.oled.web.service.MessageService;

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class MaxUploadSizeHandler {

    private final MessageService messageService;

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseBody
    public JsonResponse<Void> handleMaxSizeException(MaxUploadSizeExceededException ignoredEx) {
        log.info("======== handleMaxSizeException");

        return JsonResponse.<Void>builder()
            .message(messageService.getMessage(MessageService.DISPLAY_IMAGE_SIZE_EXCEEDED))
            .build();
    }
}
