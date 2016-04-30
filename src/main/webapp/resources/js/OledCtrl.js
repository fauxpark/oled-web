/**
 * A controller for calling the OLED API service.
 */
oled.controller('OledCtrl', ['$scope', 'AlertService', 'StateService', 'PreviewService', 'BufferService', 'OledService', function($scope, AlertService, StateService, PreviewService, BufferService, OledService) {
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
			AlertService.error('shutdown', 'The display is not initialised.', 'Please press the Startup button to begin.');
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

					$scope.state.displayOn = response.result;
				});
			} else {
				OledService.displayOn(function(response) {
					console.log('Turned display on.');

					$scope.state.displayOn = response.result;
				});
			}
		} else {
			AlertService.error('shutdown', 'The display is not initialised.', 'Please press the Startup button to begin.');
		}
	};

	$scope.clear = function() {
		if($scope.state.initialised) {
			BufferService.clear(function(response) {
				console.log('Cleared display.');

				PreviewService.clear();
			});
		} else {
			AlertService.error('shutdown', 'The display is not initialised.', 'Please press the Startup button to begin.');
		}
	};

	$scope.invert = function() {
		if($scope.state.initialised) {
			OledService.invert(function(response) {
				console.log('Inverted display.');

				$scope.state.inverted = response.result;
			});
		} else {
			AlertService.error('shutdown', 'The display is not initialised.', 'Please press the Startup button to begin.');
		}
	};

	$scope.horizontalFlip = function() {
		if($scope.state.initialised) {
			OledService.flip('h', function(response) {
				console.log('Flipped display horizontally.');

				$scope.state.hFlipped = response.result;
			});
		} else {
			AlertService.error('shutdown', 'The display is not initialised.', 'Please press the Startup button to begin.');
		}
	};

	$scope.verticalFlip = function() {
		if($scope.state.initialised) {
			OledService.flip('v', function(response) {
				console.log('Flipped display vertically.');

				$scope.state.vFlipped = response.result;
			});
		} else {
			AlertService.error('shutdown', 'The display is not initialised.', 'Please press the Startup button to begin.');
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

					$scope.state.scrolling = response.result;
				});
			} else {
				OledService.stopScroll(function(response) {
					console.log('Stopped scrolling.');

					$scope.state.scrolling = response.result;
				});
			}
		} else {
			AlertService.error('shutdown', 'The display is not initialised.', 'Please press the Startup button to begin.');
		}
	};

	$scope.setContrast = function() {
		if($scope.state.initialised) {
			var contrast = parseInt($('#input-contrast').val());

			OledService.setContrast(contrast, function(response) {
				console.log('Set contrast level to ' + contrast + '.');
			});
		} else {
			AlertService.error('shutdown', 'The display is not initialised.', 'Please press the Startup button to begin.');
		}
	};

	$scope.getState(function(response) {
		PreviewService.setSize($scope.state.width, $scope.state.height);

		if($scope.state.initialised) {
			$scope.getBuffer();
		}
	});
}]);
