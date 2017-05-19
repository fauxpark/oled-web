package net.fauxpark.oled.web.factory;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.config.AbstractFactoryBean;

import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.spi.SpiChannel;
import com.pi4j.system.SystemInfo;
import com.pi4j.system.SystemInfo.BoardType;

import net.fauxpark.oled.SSD1306;
import net.fauxpark.oled.impl.SSD1306Impl;
import net.fauxpark.oled.impl.SSD1306MockImpl;

/**
 * A factory which determines whether the webapp is running on a Raspberry Pi,
 * and provides the appropriate SSD1306 implementation.
 *
 * @author fauxpark
 */
public class SSD1306Factory extends AbstractFactoryBean<SSD1306> {
	private static final Logger log = LogManager.getLogger(SSD1306Factory.class);

	/**
	 * The internal SSD1306 instance.
	 */
	private static SSD1306 ssd1306;

	/**
	 * Creates and returns an SSD1306 implementation.
	 *
	 * If one already exists, it is simply returned. If not, then either a dummy implementation
	 * which does not call Pi4J, or a fully-functioning implementation is created and returned.
	 *
	 * @return An SSD1306 instance.
	 */
	@Override
	public SSD1306 createInstance() {
		if(ssd1306 == null) {
			if(System.getProperty("os.name").contains("nux")) {
				try {
					if(SystemInfo.getBoardType() != BoardType.UNKNOWN) {
						ssd1306 = new SSD1306Impl(128, 64, SpiChannel.CS1, RaspiPin.GPIO_15, RaspiPin.GPIO_16);

						return ssd1306;
					}
				} catch(IOException | InterruptedException e) {
					e.printStackTrace();
				}
			}

			log.warn("We don't seem to be running on a Raspberry Pi!");
			log.warn("Providing you with a mock SSD1306 implementation.");

			ssd1306 = new SSD1306MockImpl(128, 64, SpiChannel.CS1, RaspiPin.GPIO_15, RaspiPin.GPIO_16);
		}

		return ssd1306;
	}

	@Override
	public Class<?> getObjectType() {
		return ssd1306 != null ? ssd1306.getClass() : null;
	}
}
