var oled = angular.module('oled', []);

/**
 * A simple display buffer object that contains the pixel data for the entire display.
 *
 * @typedef {Object} Buffer
 * @property {Integer} width
 * @property {Integer} height
 * @property {Array.<Integer>} buffer The pixel data as an array of bytes, each representing columns of eight pixels.
 */

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

/**
 * A service that interacts with the preview canvas.
 */
oled.service('BufferService', [function() {
	var canvas = $('#canvas').get(0);
	var ctx = canvas.getContext('2d');
	var img = ctx.getImageData(0, 0, canvas.width, canvas.height);

	return {
		/**
		 * Retrieve the display canvas buffer.
		 *
		 * @return {Buffer} A display buffer object.
		 */
		getBuffer: function() {
			var buffer = new Array(1024).fill(0);

			for(var i = 0; i < img.data.length; i += 4) {
				var x = (i / 4) % canvas.width;
				var y = parseInt((i / 4) / canvas.width);

				if(img.data[i] == 255) {
					var idx = x + parseInt(y / 8) * canvas.width;
					buffer[idx] |= (1 << (y & 7));
				}
			}

			return {
				width: canvas.width,
				height: canvas.height,
				buffer: buffer
			};
		},
		/**
		 * Set the display canvas buffer.
		 *
		 * @param {Buffer} buffer The buffer to set.
		 */
		setBuffer: function(buffer) {
			for(var i = 0; i < buffer.buffer.length; i++) {
				var x = i % buffer.width;
				var y = parseInt(i / buffer.width) * 8;

				for(var j = 0; j < 8; j++) {
					var yo = y + j % 8;
					var offset = 4 * (x + yo * buffer.width);
					var on = (buffer.buffer[i] & (1 << (yo & 7))) != 0;

					img.data[offset] = on ? 255 : 0;
					img.data[offset + 1] = on ? 255 : 0;
					img.data[offset + 2] = on ? 255 : 0;
					img.data[offset + 3] = on ? 255 : 0;
				}
			}

			ctx.putImageData(img, 0, 0);
		},
		/**
		 * Get a pixel on the canvas.
		 *
		 * @param {Integer} x The X position of the pixel to get.
		 * @param {Integer} y The Y position of the pixel to get.
		 *
		 * @return {Boolean} Whether the pixel is on or off.
		 */
		getPixel: function(x, y) {
			var offset = 4 * (x + y * canvas.width);

			if(img.data[i] == 255) {
				return true;
			}

			return false;
		},
		/**
		 * Set a pixel on the canvas.
		 *
		 * @param {Integer} x The X position of the pixel to set.
		 * @param {Integer} y The Y position of the pixel to set.
		 * @param {Boolean} on Whether to turn the pixel on or off.
		 */
		setPixel: function(x, y, on) {
			var offset = 4 * (x + y * canvas.width);
			img.data[offset] = on ? 255 : 0;
			img.data[offset + 1] = on ? 255 : 0;
			img.data[offset + 2] = on ? 255 : 0;
			img.data[offset + 3] = on ? 255 : 0;
			ctx.putImageData(img, 0, 0);
		},
		/**
		 * Clear the canvas.
		 */
		clear: function() {
			img = ctx.createImageData(img);
			ctx.putImageData(img, 0, 0);
		}
	};
}]);

/**
 * A controller for keeping state and calling the OLED API service.
 */
oled.controller('OledCtrl', ['$scope', 'BufferService', 'OledService', function($scope, BufferService, OledService) {
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
			console.log('Got state.');

			$scope.state = response.data.result;
		});
	};

	$scope.getBuffer = function() {
		OledService.getBuffer(function(response) {
			console.log('Got buffer.');

			BufferService.setBuffer(response.data.result);
		});
	};

	$scope.setBuffer = function() {
		OledService.setBuffer(BufferService.getBuffer(), function(response) {
			console.log('Set buffer.');
		});
	}

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

	$scope.clear = function() {
		if($scope.state.initialised) {
			OledService.clear(function(response) {
				console.log('Cleared display.');

				BufferService.clear();
			});
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
			var contrast = parseInt($('#input-contrast').val());

			OledService.setContrast(contrast, function(response) {
				console.log('Set contrast level to ' + contrast + '.');
			});
		} else {
			alert('Can\'t do anything while the display is not initialised.');
		}
	};

	$scope.setPixel = function() {
		if($scope.state.initialised) {
			var x = parseInt($('#input-pixel-x').val());
			var y = parseInt($('#input-pixel-y').val());
			var on = $('#input-pixel-on').hasClass('active');

			OledService.setPixel(x, y, on, function(response) {
				console.log('Turned pixel at ' + x + ',' + y + ' ' + (on ? 'on' : 'off') + '.');

				BufferService.setPixel(x, y, on);
			});
		} else {
			alert('Can\'t do anything while the display is not initialised.');
		}
	};

	$scope.getState();
	$scope.getBuffer();
}]);
