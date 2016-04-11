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
		}
	};
}]);
