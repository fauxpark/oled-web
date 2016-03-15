var oled = angular.module('oled', []);

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

	$scope.getState = function() {
		OledService.getState(function(response) {
			$scope.state = response.data.result;
		});
	};

	$scope.initialise = function() {
		if($scope.state.initialised) {
			OledService.shutdown(function(response) {
				console.log('Shut down display.');

				$scope.state = response.data.result;
			});
		} else {
			OledService.startup(function(response) {
				console.log('Started up display.');

				$scope.state = response.data.result;
			});
		}
	};

	$scope.toggleDisplay = function() {
		if($scope.state.initialised) {
			if($scope.state.displayOn) {
				OledService.displayOff(function(response) {
					console.log('Turned display off.');

					$scope.state.displayOn = false;
				});
			} else {
				OledService.displayOn(function(response) {
					console.log('Turned display on.');

					$scope.state.displayOn = true;
				});
			}
		} else {
			alert('Can\'t do anything while the display is not initialised.');
		}
	};

	$scope.invert = function() {
		if($scope.state.initialised) {
			OledService.invert(function(response) {
				console.log('Inverted display.');

				$scope.state.inverted = !$scope.state.inverted;
			});
		} else {
			alert('Can\'t do anything while the display is not initialised.');
		}
	};

	$scope.horizontalFlip = function() {
		if($scope.state.initialised) {
			OledService.flip('h', function(response) {
				console.log('Flipped display horizontally.');

				$scope.state.hFlipped = !$scope.state.hFlipped;
			});
		} else {
			alert('Can\'t do anything while the display is not initialised.');
		}
	};

	$scope.verticalFlip = function() {
		if($scope.state.initialised) {
			OledService.flip('v', function(response) {
				console.log('Flipped display vertically.');

				$scope.state.vFlipped = !$scope.state.vFlipped;
			});
		} else {
			alert('Can\'t do anything while the display is not initialised.');
		}
	};

	$scope.setContrast = function() {
		if($scope.state.initialised) {
			OledService.setContrast($('#input-contrast').val(), function(response) {
				console.log('Set contrast.');
			});
		} else {
			alert('Can\'t do anything while the display is not initialised.');
		}
	};

	$scope.setPixel = function() {
		if($scope.state.initialised) {
			OledService.setPixel($('#input-pixel-x').val(), $('#input-pixel-y').val(), $('#input-pixel-on').hasClass('active'), function(response) {
				console.log('Set pixel.');
			});
		} else {
			alert('Can\'t do anything while the display is not initialised.');
		}
	};

	$scope.getState();
}]);
