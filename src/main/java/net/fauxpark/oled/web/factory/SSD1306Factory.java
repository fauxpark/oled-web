package net.fauxpark.oled.web.factory;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
public class SSD1306Factory {
	private static final Logger log = LogManager.getLogger(SSD1306Factory.class);

	private static SSD1306 ssd1306;

	public static SSD1306 getInstance() {
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
}
