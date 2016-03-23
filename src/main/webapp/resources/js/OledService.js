/**
 * A service for controlling the display.
 */
oled.service('OledService', ['ApiService', function(ApiService) {
	return {
		/**
		 * Retrieve the display state.
		 *
		 * @param {Function} callback A callback to pass the response object onto.
		 */
		getState: function(callback) {
			ApiService.get('api/state', callback);
		},
		/**
		 * Retrieve the display buffer.
		 *
		 * @param {Function} callback A callback to pass the response object onto.
		 */
		getBuffer: function(callback) {
			ApiService.get('api/buffer', callback);
		},
		/**
		 * Set the display buffer.
		 *
		 * @param {Buffer} buffer The buffer to set.
		 * @param {Function} [callback] A callback to pass the response object onto.
		 */
		setBuffer: function(buffer, callback) {
			ApiService.post('api/buffer', buffer, callback);
		},
		/**
		 * Start up the display.
		 *
		 * @param {Function} callback A callback to pass the response object onto.
		 */
		startup: function(callback) {
			ApiService.post('api/startup', null, callback);
		},
		/**
		 * Shut down the display.
		 *
		 * @param {Function} callback A callback to pass the response object onto.
		 */
		shutdown: function(callback) {
			ApiService.post('api/shutdown', null, callback);
		},
		/**
		 * Turn the display on.
		 *
		 * @param {Function} [callback] A callback to pass the response object onto.
		 */
		displayOn: function(callback) {
			ApiService.post('api/on', null, callback);
		},
		/**
		 * Turn the display off.
		 *
		 * @param {Function} [callback] A callback to pass the response object onto.
		 */
		displayOff: function(callback) {
			ApiService.post('api/off', null, callback);
		},
		/**
		 * Clear the display.
		 *
		 * @param {Function} [callback] A callback to pass the response object onto.
		 */
		clear: function(callback) {
			ApiService.post('api/clear', null, callback);
		},
		/**
		 * Invert the display.
		 *
		 * @param {Function} [callback] A callback to pass the response object onto.
		 */
		invert: function(callback) {
			ApiService.post('api/invert', null, callback);
		},
		/**
		 * Flip the display horizontally or vertically.
		 *
		 * @param {String} axis The axis to flip on. Must be either 'h' or 'v'.
		 * @param {Function} [callback] A callback to pass the response object onto.
		 */
		flip: function(axis, callback) {
			ApiService.post('api/flip', {
				axis: axis
			}, callback);
		},
		/**
		 * Set the contrast level.
		 *
		 * @param {Integer} contrast The contrast level to set.
		 * @param {Function} [callback] A callback to pass the response object onto.
		 */
		setContrast: function(contrast, callback) {
			ApiService.post('api/contrast', {
				contrast: contrast
			}, callback);
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
			ApiService.post('api/pixel', {
				x: x,
				y: y,
				on: on
			}, callback);
		}
	};
}]);
