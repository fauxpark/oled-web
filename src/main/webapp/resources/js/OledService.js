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
		 * Setup and start scrolling the display.
		 *
		 * @param {Boolean} vertical Whether to scroll vertically as well.
		 * @param {Boolean} direction The direction to scroll in. To scroll to the left, set this to true.
		 * @param {Integer} startPage The topmost page to scroll.
		 * @param {Integer} endPage The bottommost page to scroll.
		 * @param {Integer} offset The number of rows from the top to start the vertical scroll area at.
		 * @param {Integer} rows The number of rows in the vertical scroll area.
		 * @param {Integer} speed The speed to scroll the display.
		 * @param {Integer} step The number of rows to scroll vertically by.
		 * @param {Function} [callback] A callback to pass the response object onto.
		 */
		startScroll: function(vertical, direction, startPage, endPage, offset, rows, speed, step, callback) {
			ApiService.post('api/scroll/start', {
				vertical: vertical,
				left: direction,
				startPage: startPage,
				endPage: endPage,
				offset: offset,
				rows: rows,
				speed: speed,
				step: step
			}, callback);
		},
		/**
		 * Stop scrolling the display.
		 *
		 * @param {Function} [callback] A callback to pass the response object onto.
		 */
		stopScroll: function(callback) {
			ApiService.post('api/scroll/stop', null, callback);
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
		 * Set the display offset.
		 *
		 * @param {Integer} offset The offset to set.
		 * @param {Function} [callback] A callback to pass the response object onto.
		 */
		setOffset: function(offset, callback) {
			ApiService.post('api/offset', {
				offset: offset
			}, callback);
		}
	};
}]);
