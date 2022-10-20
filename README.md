# oled-web: SSD1306 JSON API and Web UI

This webapp provides a browser interface to manipulate the [Adafruit SSD1306 OLED display](https://www.adafruit.com/categories/98) on the Raspberry Pi.
It is built using Spring Boot to provide the backend JSON API, and AngularJS with Bootstrap for the UI.

## Prerequisites

Refer to the [oled-core readme](https://github.com/fauxpark/oled-core#readme) for details on how to connect up the display and install PiGpio and Java.

## Getting Started

Download the WAR file and run the following (note that `sudo` is required due to the use of PiGpio):

```
$ sudo java -jar oled-web-3.0.war --oled.transport=i2c
```

Then navigate to `localhost:8080/oled` (or your Raspberry Pi's IP address/hostname) to see the results.

To begin playing with the display, press the "Startup" button. From here, you can invert, scroll, set the contrast, and draw various shapes and text and even custom images on the screen. You can also draw freeform by pressing the mouse button down on the preview.

Three different character sets are supported for drawing text: Code pages 437, 850 and 1252. An on screen keyboard is provided on the text drawing tab with all supported characters. Some non-printing characters do not have glyphs, and any Unicode characters outside of the character set will be rendered as `?`s.

## Configuration

The default configuration can be changed with the following command line arguments:

| Flag                           | Default |
|--------------------------------|---------|
| `--oled.transport`             | `mock`  |
| `--oled.transport.i2c.rst`     | `14`    |
| `--oled.transport.i2c.bus`     | `1`     |
| `--oled.transport.i2c.address` | `0x3D`  |
| `--oled.transport.spi.channel` | `0`     |
| `--oled.transport.spi.rst`     | `14`    |
| `--oled.transport.i2c.dc`      | `15`    |

## The API

See the [Wiki](https://github.com/fauxpark/oled-web/wiki) for the API reference.
