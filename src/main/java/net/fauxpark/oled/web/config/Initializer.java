package net.fauxpark.oled.web.config;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class Initializer implements WebApplicationInitializer {
	private static final String DISPLAY_NAME = "OLED Web UI";

	private static final int MAX_UPLOAD_SIZE = 5424880;

	@Override
	public void onStartup(ServletContext container) throws ServletException {
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.setDisplayName(DISPLAY_NAME);

		container.addListener(new ContextLoaderListener(rootContext));

		AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
		dispatcherContext.register(Config.class);

		ServletRegistration.Dynamic dispatcher = container.addServlet("oled", new DispatcherServlet(dispatcherContext));

		MultipartConfigElement multiPart = new MultipartConfigElement("", MAX_UPLOAD_SIZE, MAX_UPLOAD_SIZE * 2, MAX_UPLOAD_SIZE / 2);
		dispatcher.setMultipartConfig(multiPart);

		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
	}
}
