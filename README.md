# oled-web: SSD1306 JSON API and Web UI
This webapp provides a browser interface to manipulate the [Adafruit SSD1306 OLED display](https://www.adafruit.com/categories/98) on the Raspberry Pi.
It is built using the Spring framework to provide the backend JSON API, and AngularJS with Bootstrap for the UI.

Refer to the [oled-core readme](https://github.com/fauxpark/oled-core#readme) for more information on how to connect up your OLED display.

## Getting Started
Set up an Apache Tomcat 8 installation on your Raspberry Pi and start it. Download the WAR file and copy it to the `webapps` directory as oled.war.
Navigate to `<your pi's address>:8080/oled` to see the results.

To begin playing with the display, press the "Startup" button. From here, you can invert, scroll, set the contrast, and draw various shapes and text
and even custom images on the screen. You can also draw freeform by pressing the mouse button down on the preview.

Three different character sets are supported for drawing text: Code pages 437, 850 and 1252. An on screen keyboard is provided on the text drawing tab with
all supported characters. Some non-printing characters do not have glyphs, and any Unicode characters outside of the character set will be rendered as `?`s.

## The API
Documentation for the JSON API is coming soon. Stay tuned!
