/**
 * A controller for calling the OLED graphics API service.
 */
oled.controller('GraphicsCtrl', ['$scope', 'StateService', 'PreviewService', 'BufferService', 'GraphicsService', function($scope, StateService, PreviewService, BufferService, GraphicsService) {
	$scope.state = StateService;

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

	$scope.drawImage = function() {
		if($scope.state.initialised) {
			var file = $('#input-image-file').get(0).files[0];
			var x = parseInt($('#input-image-x').val());
			var y = parseInt($('#input-image-y').val());
			var width = parseInt($('#input-image-width').val());
			var height = parseInt($('#input-image-height').val());
			var formData = new FormData();
			formData.append('request', new Blob([angular.toJson({
				x: x,
				y: y,
				width: width,
				height: height
			})], {
				type: 'application/json'
			}));
			formData.append('file', file);

			GraphicsService.drawImage(formData, function(response) {
				console.log('Drew image ' + file.name + ' with dimensions ' + width + 'x' + height + ' at ' + x + ',' + y + '.');

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

	$scope.drawRectangle = function() {
		if($scope.state.initialised) {
			var x = parseInt($('#input-rect-x').val());
			var y = parseInt($('#input-rect-y').val());
			var width = parseInt($('#input-rect-width').val());
			var height = parseInt($('#input-rect-height').val());
			var filled = $('#input-rect-filled').hasClass('active');

			GraphicsService.drawRectangle(x, y, width, height, filled, function(response) {
				console.log('Drew ' + (filled ? 'filled' : '') + ' rectangle with dimensions ' + width + 'x' + height + ' at ' + x + ',' + y + '.');

				$scope.getBuffer();
			});
		} else {
			alert('Can\'t do anything while the display is not initialised.');
		}
	};

	$scope.drawArc = function() {
		if($scope.state.initialised) {
			var x = parseInt($('#input-arc-x').val());
			var y = parseInt($('#input-arc-y').val());
			var radius = parseInt($('#input-arc-radius').val());
			var startAngle = parseInt($('#input-arc-start').val());
			var endAngle = parseInt($('#input-arc-end').val());

			GraphicsService.drawArc(x, y, radius, startAngle, endAngle, function(response) {
				console.log('Drew arc from ' + startAngle + '\xB0 to ' + endAngle + '\xB0 with radius ' + radius + ' at ' + x + ',' + y + '.');

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

			GraphicsService.drawCircle(x, y, radius, function(response) {
				console.log('Drew circle with radius ' + radius + ' at ' + x + ',' + y + '.');

				$scope.getBuffer();
			});
		} else {
			alert('Can\'t do anything while the display is not initialised.');
		}
	};
}]);
