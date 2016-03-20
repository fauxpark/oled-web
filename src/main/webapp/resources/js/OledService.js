/**
 * A service to provide access to the OLED API.
 */
oled.service('OledService', ['$http', function($http) {
	return {
		/**
		 * Retrieve the display state.
		 *
		 * @param {Function} callback A callback to pass the response object onto.
		 */
		getState: function(callback) {
			$http.get('/oled/api/state').then(function(response) {
				if(callback) {
					callback(response);
				}
			});
		},
		/**
		 * Retrieve the display buffer.
		 *
		 * @param {Function} callback A callback to pass the response object onto.
		 */
		getBuffer: function(callback) {
			$http.get('/oled/api/buffer').then(function(response) {
				if(callback) {
					callback(response);
				}
			});
		},
		/**
		 * Set the display buffer.
		 *
		 * @param {Buffer} buffer The buffer to set.
		 * @param {Function} [callback] A callback to pass the response object onto.
		 */
		setBuffer: function(buffer, callback) {
			$http.post('/oled/api/buffer', buffer).then(function(response) {
				if(callback) {
					callback(response);
				}
			});
		},
		/**
		 * Start up the display.
		 *
		 * @param {Function} callback A callback to pass the response object onto.
		 */
		startup: function(callback) {
			$http.post('/oled/api/startup').then(function(response) {
				if(callback) {
					callback(response);
				}
			});
		},
		/**
		 * Shut down the display.
		 *
		 * @param {Function} callback A callback to pass the response object onto.
		 */
		shutdown: function(callback) {
			$http.post('/oled/api/shutdown').then(function(response) {
				if(callback) {
					callback(response);
				}
			});
		},
		/**
		 * Turn the display on.
		 *
		 * @param {Function} [callback] A callback to pass the response object onto.
		 */
		displayOn: function(callback) {
			$http.post('/oled/api/on').then(function(response) {
				if(callback) {
					callback(response);
				}
			});
		},
		/**
		 * Turn the display off.
		 *
		 * @param {Function} [callback] A callback to pass the response object onto.
		 */
		displayOff: function(callback) {
			$http.post('/oled/api/off').then(function(response) {
				if(callback) {
					callback(response);
				}
			});
		},
		/**
		 * Clear the display.
		 *
		 * @param {Function} [callback] A callback to pass the response object onto.
		 */
		clear: function(callback) {
			$http.post('/oled/api/clear').then(function(response) {
				if(callback) {
					callback(response);
				}
			});
		},
		/**
		 * Invert the display.
		 *
		 * @param {Function} [callback] A callback to pass the response object onto.
		 */
		invert: function(callback) {
			$http.post('/oled/api/invert').then(function(response) {
				if(callback) {
					callback(response);
				}
			});
		},
		/**
		 * Flip the display horizontally or vertically.
		 *
		 * @param {String} axis The axis to flip on. Must be either 'h' or 'v'.
		 * @param {Function} [callback] A callback to pass the response object onto.
		 */
		flip: function(axis, callback) {
			$http.post('/oled/api/flip', {
				axis: axis
			}).then(function(response) {
				if(callback) {
					callback(response);
				}
			});
		},
		/**
		 * Set the contrast level.
		 *
		 * @param {Integer} contrast The contrast level to set.
		 * @param {Function} [callback] A callback to pass the response object onto.
		 */
		setContrast: function(contrast, callback) {
			$http.post('/oled/api/contrast', {
				contrast: contrast
			}).then(function(response) {
				if(callback) {
					callback(response);
				}
			});
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
			$http.post('/oled/api/pixel', {
				x: x,
				y: y,
				on: on
			}).then(function(response) {
				if(callback) {
					callback(response);
				}
			});
		}
	};
}]);
