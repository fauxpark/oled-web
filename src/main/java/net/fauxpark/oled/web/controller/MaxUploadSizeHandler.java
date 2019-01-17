package net.fauxpark.oled.web.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import net.fauxpark.oled.web.entity.JsonResponse;
import net.fauxpark.oled.web.service.MessageService;

@ControllerAdvice
public class MaxUploadSizeHandler {
	private static final Logger log = LogManager.getLogger(ApiController.class);

	@Autowired
	private MessageService messageService;

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	@ResponseBody
	public JsonResponse<Void> handleMaxSizeException(MaxUploadSizeExceededException ex) {
		log.info("======== handleMaxSizeException");

		JsonResponse<Void> response = new JsonResponse<>();
		response.setMessage(messageService.getMessage(MessageService.DISPLAY_IMAGE_SIZE_EXCEEDED));

		return response;
	}
}
