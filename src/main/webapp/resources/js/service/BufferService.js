/**
 * A service for accessing the display buffer contents.
 */
oled.service('BufferService', ['ApiV1Service', function(ApiV1Service) {
	return {
		/**
		 * Retrieve the display buffer.
		 *
		 * @param {Function} callback A callback to pass the response object onto.
		 */
		getBuffer: function(callback) {
			ApiV1Service.get('buffer', callback);
		},
		/**
		 * Set the display buffer.
		 *
		 * @param {Buffer} buffer The buffer to set.
		 * @param {Function} [callback] A callback to pass the response object onto.
		 */
		setBuffer: function(buffer, callback) {
			ApiV1Service.post('buffer', buffer, callback);
		},
		/**
		 * Clear the display.
		 *
		 * @param {Function} [callback] A callback to pass the response object onto.
		 */
		clear: function(callback) {
			ApiV1Service.post('clear', null, callback);
		},
		/**
		 * Turn a single pixel on or off.
		 *
		 * @param {Integer} x The X position of the pixel to set.
		 * @param {Integer} y The Y position of the pixel to set.
		 * @param {Boolean} on Whether to turn the pixel on or off.
		 * @param {Function} [callback] A callback to pass the response object onto.
		 */
		setPixel: function(x, y, on, callback) {
			ApiV1Service.post('pixel', {
				x: x,
				y: y,
				on: on
			}, callback);
		}
	};
}]);
