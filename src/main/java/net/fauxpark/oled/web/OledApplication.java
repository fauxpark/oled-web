package net.fauxpark.oled.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import net.fauxpark.oled.web.factory.SSD1306Factory;

@SpringBootApplication
@PropertySource(value="classpath:build.properties", ignoreResourceNotFound=true)
public class OledApplication {
    public static void main(String[] args) {
        SpringApplication.run(OledApplication.class, args);
    }

    @Bean
    public SSD1306Factory ssd1306Factory() {
        return new SSD1306Factory();
    }
}
