/**
 * A controller for keeping state and calling the OLED API service.
 */
oled.controller('OledCtrl', ['$scope', 'BufferService', 'OledService', 'GraphicsService', function($scope, BufferService, OledService, GraphicsService) {
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

	$scope.drawText = function() {
		if($scope.state.initialised) {
			var x = parseInt($('#input-text-x').val());
			var y = parseInt($('#input-text-y').val());
			var text = $('#input-text-text').val();

			GraphicsService.drawText(x, y, text, function(response) {
				console.log('Drew text "' + text + '" at ' + x + ',' + y + '.');

				$scope.getBuffer();
			});
		} else {
			alert('Can\'t do anything while the display is not initialised.');
		}
	};

	$scope.drawLine = function() {
		if($scope.state.initialised) {
			var x0 = parseInt($('#input-line-x0').val());
			var y0 = parseInt($('#input-line-y0').val());
			var x1 = parseInt($('#input-line-x1').val());
			var y1 = parseInt($('#input-line-y1').val());

			GraphicsService.drawLine(x0, y0, x1, y1, function(response) {
				console.log('Drew line from ' + x0 + ',' + y0 + ' to ' + x1 + ',' + y1 + '.');

				$scope.getBuffer();
			});
		} else {
			alert('Can\'t do anything while the display is not initialised.');
		}
	};

	$scope.drawCircle = function() {
		if($scope.state.initialised) {
			var x = parseInt($('#input-circle-x').val());
			var y = parseInt($('#input-circle-y').val());
			var radius = parseInt($('#input-circle-radius').val());

			GraphicsService.drawCircle(x, y, radius, function() {
				console.log('Drew circle with radius ' + radius + ' at ' + x + ',' + y + '.');

				$scope.getBuffer();
			});
		} else {
			alert('Can\'t do anything while the display is not initialised.');
		}
	};

	$scope.getState();
	$scope.getBuffer();
}]);
