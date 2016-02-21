var oled = angular.module('oled', []);

/**
 * A service to provide access to the OLED API.
 */
oled.service('OledService', ['$http', function($http) {
	return {
		/**
		 * Start up the display.
		 *
		 * @param {Function} callback An optional callback function, with the response object passed as its only parameter.
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
		 * @param {Function} callback An optional callback function, with the response object passed as its only parameter.
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
		 * @param {Function} callback An optional callback function, with the response object passed as its only parameter.
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
		 * @param {Function} callback An optional callback function, with the response object passed as its only parameter.
		 */
		displayOff: function(callback) {
			$http.post('/oled/api/off').then(function(response) {
				if(callback) {
					callback(response);
				}
			});
		},
		/**
		 * Invert the display.
		 *
		 * @param {Function} callback An optional callback function, with the response object passed as its only parameter.
		 */
		invert: function(callback) {
			$http.post('/oled/api/invert').then(function(response) {
				if(callback) {
					callback(response);
				}
			});
		},
		/**
		 * Set the contrast level.
		 *
		 * @param {Integer} contrast The contrast level to set.
		 * @param {Function} callback An optional callback function, with the response object passed as its only parameter.
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
		 * @param {Function} callback An optional callback function, with the response object passed as its only parameter.
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

/**
 * A controller for keeping state and calling the OLED API service.
 */
oled.controller('OledCtrl', ['$scope', 'OledService', function($scope, OledService) {
	$scope.startup = function() {
		OledService.startup(function(response) {
			alert('Started up display.');
		});
	};

	$scope.shutdown = function() {
		OledService.shutdown(function(response) {
			alert('Shut down display.');
		});
	};

	$scope.displayOn = function() {
		OledService.displayOn(function(response) {
			alert('Turned display on.');
		});
	};

	$scope.displayOff = function() {
		OledService.displayOff(function(response) {
			alert('Turned display off.');
		});
	};

	$scope.invert = function() {
		OledService.invert(function(response) {
			alert('Inverted display.');
		});
	};

	$scope.setContrast = function(contrast) {
		OledService.setContrast(contrast, function(response) {
			alert('Set contrast.');
		});
	};

	$scope.setPixel = function(x, y, on) {
		OledService.setPixel(x, y, on, function(response) {
			alert('Set pixel.');
		});
	}
}]);
