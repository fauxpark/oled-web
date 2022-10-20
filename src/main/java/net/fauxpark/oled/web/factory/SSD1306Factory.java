package net.fauxpark.oled.web.factory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AbstractFactoryBean;

import net.fauxpark.oled.SSD1306;
import net.fauxpark.oled.transport.I2CTransport;
import net.fauxpark.oled.transport.MockTransport;
import net.fauxpark.oled.transport.SPITransport;
import net.fauxpark.oled.transport.Transport;

/**
 * A factory which determines whether the webapp is running on a Raspberry Pi,
 * and provides the appropriate SSD1306 implementation.
 *
 * @author fauxpark
 */
public class SSD1306Factory extends AbstractFactoryBean<SSD1306> {
	private static final Logger log = LoggerFactory.getLogger(SSD1306Factory.class);

	@Value("${oled.transport:mock}")
	private String oledTransport;

	@Value("${oled.transport.i2c.rst:14}")
	private int oledTransportI2cRst;

	@Value("${oled.transport.i2c.bus:1}")
	private int oledTransportI2cBus;

	@Value("${oled.transport.i2c.address:0x3D}")
	private int oledTransportI2cAddress;

	@Value("${oled.transport.spi.channel:0}")
	private int oledTransportSpiChannel;

	@Value("${oled.transport.spi.rst:14}")
	private int oledTransportSpiRst;

	@Value("${oled.transport.spi.rst:15}")
	private int oledTransportSpiDc;

	/**
	 * Creates and returns an SSD1306 instance.
	 * If we are running on a platform other than the Raspberry Pi, the SSD1306 instance is supplied with a mock {@link Transport}.
	 *
	 * @return An SSD1306 instance.
	 */
	@Override
	protected SSD1306 createInstance() {
		Transport transport;

		if(isRaspberryPi()) {
			switch(oledTransport) {
				case "i2c":
					transport = new I2CTransport(oledTransportI2cRst, oledTransportI2cBus, oledTransportI2cAddress);
					log.info("Using I2C transport: RST {}, bus {}, address {}", oledTransportI2cRst, oledTransportI2cBus, String.format("0x%02X", oledTransportI2cAddress));
					break;
				case "spi":
					transport = new SPITransport(oledTransportSpiChannel, oledTransportSpiRst, oledTransportSpiDc);
					log.info("Using SPI transport: channel {}, RST {}, DC {}", oledTransportSpiChannel, oledTransportSpiRst, oledTransportSpiDc);
					break;
				case "mock":
					transport = new MockTransport();
					log.info("Using mock transport");
					break;
				default:
					throw new IllegalArgumentException("oled.transport must be one of i2c, spi or mock!");
			}

			log.info("Using configured OLED transport: {}", oledTransport);
		} else {
			log.warn("We don't seem to be running on a Raspberry Pi!");
			log.warn("Falling back to mock transport.");

			transport = new MockTransport();
		}

		return new SSD1306(128, 64, transport);
	}

	@Override
	public Class<?> getObjectType() {
		return SSD1306.class;
	}

	private boolean isRaspberryPi() {
		if(System.getProperty("os.name").contains("nux")) {
			try {
				Process pb = new ProcessBuilder("bash", "-c", "cat /proc/device-tree/model").start();
				BufferedReader reader = new BufferedReader(new InputStreamReader(pb.getInputStream()));
				String line;
				while((line = reader.readLine()) != null) {
					if(line.toLowerCase().contains("raspberry")) {
						return true;
					}
				}
			} catch(Exception ignored) {}
		}

		return false;
	}
}
