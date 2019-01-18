package net.fauxpark.oled.web.factory;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.config.AbstractFactoryBean;

import com.pi4j.io.gpio.RaspiPin;
//import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.spi.SpiChannel;
import com.pi4j.system.SystemInfo;
import com.pi4j.system.SystemInfo.BoardType;

import net.fauxpark.oled.SSD1306;
import net.fauxpark.oled.transport.MockTransport;
//import net.fauxpark.oled.transport.I2CTransport;
import net.fauxpark.oled.transport.SPITransport;
import net.fauxpark.oled.transport.Transport;

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
	 * Creates and returns an SSD1306 instance.
	 *
	 * If we are running on a platform other than the Raspberry Pi, the SSD1306 instance is supplied with a mock {@link Transport}.
	 *
	 * @return An SSD1306 instance.
	 */
	@Override
	public SSD1306 createInstance() {
		if(ssd1306 == null) {
			Transport transport;

			if(isRaspberryPi()) {
				transport = new SPITransport(SpiChannel.CS1, RaspiPin.GPIO_15, RaspiPin.GPIO_16);
				//transport = new I2CTransport(RaspiPin.GPIO_15, I2CBus.BUS_1, 0x3D);
			} else {
				log.warn("We don't seem to be running on a Raspberry Pi!");
				log.warn("Providing you with a mock SSD1306 implementation.");

				transport = new MockTransport();
			}

			ssd1306 = new SSD1306(128, 64, transport);
		}

		return ssd1306;
	}

	@Override
	public Class<?> getObjectType() {
		return ssd1306 != null ? ssd1306.getClass() : null;
	}

	private boolean isRaspberryPi() {
		if(System.getProperty("os.name").contains("nux")) {
			try {
				if(SystemInfo.getBoardType() != BoardType.UNKNOWN) {
					return true;
				}
			} catch(IOException | InterruptedException e) {
				e.printStackTrace();
			}
		}

		return false;
	}
}
