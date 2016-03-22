/**
 * A service for drawing graphics onto the display.
 */
oled.service('GraphicsService', ['$http', function($http) {
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
			$http.post('/oled/graphics/text', {
				x: x,
				y: y,
				text: text
			}).then(function(response) {
				if(callback) {
					callback(response);
				}
			});
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
			$http.post('/oled/graphics/line', {
				x0: x0,
				y0: y0,
				x1: x1,
				y1: y1
			}).then(function(response) {
				if(callback) {
					callback(response);
				}
			});
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
			$http.post('/oled/graphics/circle', {
				x: x,
				y: y,
				radius: radius
			}).then(function(response) {
				if(callback) {
					callback(response);
				}
			});
		}
	};
}]);
