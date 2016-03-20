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
		}
	};
}]);
