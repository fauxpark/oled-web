package net.fauxpark.oled.web.config;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class Initializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	private static final String SERVLET_NAME = "oled";

	private static final int MAX_UPLOAD_SIZE = 1 * 1024 * 1024; // 1MB

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {Config.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}

	@Override
	protected String getServletName() {
		return SERVLET_NAME;
	}

	@Override
	protected void customizeRegistration(Dynamic registration) {
		MultipartConfigElement multipartConfigElement = new MultipartConfigElement("", MAX_UPLOAD_SIZE, MAX_UPLOAD_SIZE * 2, MAX_UPLOAD_SIZE / 2);
		registration.setMultipartConfig(multipartConfigElement);
	}
}
