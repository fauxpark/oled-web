var oled = angular.module('oled', []);

/**
 * A service to provide access to the OLED API.
 */
oled.service('OledService', ['$http', function($http) {
	return {
		/**
		 * Retrieve the display status.
		 *
		 * @param {Function} callback An optional callback function, with the response object passed as its only parameter.
		 */
		status: function(callback) {
			$http.get('/oled/api/status').then(function(response) {
				if(callback) {
					callback(response);
				}
			});
		},
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
		 * Flip the display horizontally or vertically.
		 *
		 * @param {String} axis The axis to flip on. Must be either 'h' or 'v'.
		 * @param {Function} callback An optional callback function, with the response object passed as its only parameter.
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
	$scope.state = {
		initialised: false,
		displayOn: false,
		inverted: false,
		hFlipped: false,
		vFlipped: false,
		contrast: 0
	};

	$scope.initialise = function() {
		if($scope.state.initialised) {
			OledService.shutdown(function(response) {
				alert('Shut down display.');

				$scope.state.initialised = false;
				$('#btn-init').html('Startup');
			});
		} else {
			OledService.startup(function(response) {
				alert('Started up display.');

				$scope.state.initialised = true;
				$('#btn-init').html('Shutdown');
			});
		}
	};

	$scope.toggleDisplay = function() {
		if($scope.state.initialised) {
			if($scope.state.displayOn) {
				OledService.displayOff(function(response) {
					alert('Turned display off.');

					$scope.state.displayOn = false;
					$('#btn-display-on').html('Display On');
				});
			} else {
				OledService.displayOn(function(response) {
					alert('Turned display on.');

					$scope.state.displayOn = true;
					$('#btn-display-on').html('Display Off');
				});
			}
		} else {
			alert('Can\'t do anything while the display is not initialised.');
		}
	};

	$scope.invert = function() {
		if($scope.state.initialised) {
			OledService.invert(function(response) {
				alert('Inverted display.');

				$scope.state.inverted = !$scope.state.inverted;
			});
		} else {
			alert('Can\'t do anything while the display is not initialised.');
		}
	};

	$scope.horizontalFlip = function() {
		if($scope.state.initialised) {
			OledService.flip('h', function(response) {
				alert('Flipped display horizontally.');

				$scope.state.hFlipped = !$scope.state.hFlipped;
			});
		} else {
			alert('Can\'t do anything while the display is not initialised.');
		}
	};

	$scope.verticalFlip = function() {
		if($scope.state.initialised) {
			OledService.flip('v', function(response) {
				alert('Flipped display vertically.');

				$scope.state.vFlipped = !$scope.state.vFlipped;
			});
		} else {
			alert('Can\'t do anything while the display is not initialised.');
		}
	};

	$scope.setContrast = function(contrast) {
		if($scope.state.initialised) {
			OledService.setContrast(contrast, function(response) {
				alert('Set contrast.');
			});
		} else {
			alert('Can\'t do anything while the display is not initialised.');
		}
	};

	$scope.setPixel = function() {
		if($scope.state.initialised) {
			OledService.setPixel($('#input-pixel-x').val(), $('#input-pixel-y').val(), $('#input-pixel-on').hasClass('active'), function(response) {
				alert('Set pixel.');
			});
		} else {
			alert('Can\'t do anything while the display is not initialised.');
		}
	};

	OledService.status(function(response) {
		$scope.state = response.data;
	});
}]);
