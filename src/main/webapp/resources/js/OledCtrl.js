/**
 * A controller for calling the OLED API service.
 */
oled.controller('OledCtrl', ['$scope', 'StateService', 'PreviewService', 'BufferService', 'OledService', function($scope, StateService, PreviewService, BufferService, OledService) {
	$scope.state = StateService;

	$scope.scrollDir = false;

	$scope.scrollVertical = false;

	$scope.getState = function(callback) {
		OledService.getState(function(response) {
			console.log('Got state.');

			$scope.state.setState(response.result);

			if(callback) {
				callback();
			}
		});
	};

	$scope.getBuffer = function() {
		if($scope.state.initialised) {
			BufferService.getBuffer(function(response) {
				console.log('Got buffer.');

				PreviewService.setBuffer(response.result);
			});
		} else {
			alert('Can\'t do anything while the display is not initialised.');
		}
	};

	$scope.initialise = function() {
		if($scope.state.initialised) {
			OledService.shutdown(function(response) {
				console.log('Shut down display.');

				$scope.state.setState(response.result);
			});
		} else {
			OledService.startup(function(response) {
				console.log('Started up display.');

				$scope.state.setState(response.result);
			});
		}

		PreviewService.clear();
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
			BufferService.clear(function(response) {
				console.log('Cleared display.');

				PreviewService.clear();
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

	$scope.toggleScroll = function() {
		if($scope.state.initialised) {
			if(!$scope.state.scrolling) {
				var vertical = $('#input-scroll-vertical').hasClass('active');
				var left = $('#input-scroll-direction').hasClass('active');
				var startPage = parseInt($('#input-scroll-start').val());
				var endPage = parseInt($('#input-scroll-end').val());
				var offset = parseInt($('#input-scroll-offset').val());
				var rows = parseInt($('#input-scroll-rows').val());
				var speed = parseInt($('#input-scroll-speed').val());
				var step = parseInt($('#input-scroll-step').val());

				OledService.startScroll(vertical, left, startPage, endPage, offset, rows, speed, step, function(response) {
					console.log('Started scrolling pages ' + startPage + ' to ' + endPage + ' ' + (left ? 'left' : 'right') + ' at speed ' + speed + '.');

					if(vertical) {
						console.log('Started scrolling rows ' + offset + ' to ' + (offset + rows) + ' vertically at ' + step + ' rows per step.');
					}

					$scope.state.scrolling = true;
				});
			} else {
				OledService.stopScroll(function(response) {
					console.log('Stopped scrolling.');

					$scope.state.scrolling = false;
				});
			}
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

			BufferService.setPixel(x, y, on, function(response) {
				console.log('Turned pixel at ' + x + ',' + y + ' ' + (on ? 'on' : 'off') + '.');

				PreviewService.setPixel(x, y, on);
			});
		} else {
			alert('Can\'t do anything while the display is not initialised.');
		}
	};

	$scope.getState(function(response) {
		PreviewService.setSize($scope.state.width, $scope.state.height);

		if($scope.state.initialised) {
			$scope.getBuffer();
		}
	});
}]);
