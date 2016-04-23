/**
 * A service for drawing graphics onto the display.
 */
oled.service('GraphicsService', ['ApiService', function(ApiService) {
	return {
		/**
		 * Draw text on the display.
		 *
		 * @param {Integer} x The X position of the text.
		 * @param {Integer} y The Y position of the text.
		 * @param {String} text The text to draw.
		 * @param {Function} [callback] A callback to pass the response object onto.
		 */
		drawText: function(x, y, text, callback) {
			ApiService.post('graphics/text', {
				x: x,
				y: y,
				text: text
			}, callback);
		},
		/**
		 * Draw an image on the display.
		 *
		 * @param {FormData} formData The image form data.
		 * @param {Function} [callback] A callback to pass the response object onto.
		 */
		drawImage: function(formData, callback) {
			ApiService.postImage('graphics/image', formData, callback);
		},
		/**
		 * Draw a line on the display.
		 *
		 * @param {Integer} x0 The first X position of the line.
		 * @param {Integer} y0 The first Y position of the line.
		 * @param {Integer} x1 The second X position of the line.
		 * @param {Integer} y1 The second Y position of the line.
		 * @param {Function} [callback] A callback to pass the response object onto.
		 */
		drawLine: function(x0, y0, x1, y1, callback) {
			ApiService.post('graphics/line', {
				x0: x0,
				y0: y0,
				x1: x1,
				y1: y1
			}, callback);
		},
		/**
		 * Draw a rectangle on the display.
		 *
		 * @param {Integer} x The X position of the rectangle.
		 * @param {Integer} y The Y position of the rectangle.
		 * @param {Integer} width The width of the rectangle.
		 * @param {Integer} height The height of the rectangle.
		 * @param {Integer} filled Whether to fill in the rectangle.
		 * @param {Function} [callback] A callback to pass the response object onto.
		 */
		drawRectangle: function(x, y, width, height, filled, callback) {
			ApiService.post('graphics/rectangle', {
				x: x,
				y: y,
				width: width,
				height: height,
				filled: filled
			}, callback);
		},
		/**
		 * Draw an arc on the display.
		 *
		 * @param {Integer} x The X position of the arc.
		 * @param {Integer} y The Y position of the arc.
		 * @param {Integer} radius The radius of the arc.
		 * @param {Integer} startAngle The starting angle of the arc.
		 * @param {Integer} endAngle The ending angle of the arc.
		 * @param {Function} [callback] A callback to pass the response object onto.
		 */
		drawArc: function(x, y, radius, startAngle, endAngle, callback) {
			ApiService.post('graphics/arc', {
				x: x,
				y: y,
				radius: radius,
				startAngle: startAngle,
				endAngle: endAngle
			}, callback);
		},
		/**
		 * Draw a circle on the display.
		 *
		 * @param {Integer} x The X position of the circle.
		 * @param {Integer} y The Y position of the circle.
		 * @param {Integer} radius The radius of the circle.
		 * @param {Function} [callback] A callback to pass the response object onto.
		 */
		drawCircle: function(x, y, radius, callback) {
			ApiService.post('graphics/circle', {
				x: x,
				y: y,
				radius: radius
			}, callback);
		}
	};
}]);
