/**
 * A controller for calling the OLED graphics API service.
 */
oled.controller('GraphicsCtrl', ['$scope', 'AlertService', 'StateService', 'PreviewService', 'BufferService', 'GraphicsService', function($scope, AlertService, StateService, PreviewService, BufferService, GraphicsService) {
	$scope.state = StateService;

	$scope.charset = [];

	$scope.text = {
		text: '',
		font: 'cp850',
		x: 0,
		y: 0
	};

	$scope.image = {
		x: 0,
		y: 0,
		width: 1,
		height: 1
	};

	$scope.line = {
		x0: 0,
		y0: 0,
		x1: 0,
		y1: 0
	};

	$scope.rect = {
		x: 0,
		y: 0,
		width: 1,
		height: 1,
		filled: false
	};

	$scope.arc = {
		x: 0,
		y: 0,
		radius: 0,
		startAngle: 0,
		endAngle: 0
	};

	$scope.circle = {
		x: 0,
		y: 0,
		radius: 0
	};

	$scope.getCharset = function(name) {
		GraphicsService.getCharset(name, function(response) {
			$scope.charset = response;
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

	$scope.drawText = function() {
		if($scope.state.initialised) {
			var x = $scope.text.x;
			var y = $scope.text.y;
			var text = $scope.text.text;
			var font = $scope.text.font;

			GraphicsService.drawText(x, y, text, font, function(response) {
				console.log('Drew text "%s" at %d,%d with font %s.', text, x, y, font);

				$scope.getBuffer();
			});
		} else {
			AlertService.error('shutdown', 'The display is not initialised.', 'Please press the Startup button to begin.');
		}
	};

	$scope.drawImage = function() {
		if($scope.state.initialised) {
			var file = $('#input-image-file').get(0).files[0];
			var x = $scope.image.x;
			var y = $scope.image.y;
			var width = $scope.image.width;
			var height = $scope.image.height;
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
				console.log('Drew image "%s" with dimensions %dx%d at %d,%d.', file.name, width, height, x, y);

				$scope.getBuffer();
			});
		} else {
			AlertService.error('shutdown', 'The display is not initialised.', 'Please press the Startup button to begin.');
		}
	};

	$scope.drawLine = function() {
		if($scope.state.initialised) {
			var x0 = $scope.line.x0;
			var y0 = $scope.line.y0;
			var x1 = $scope.line.x1;
			var y1 = $scope.line.y1;

			GraphicsService.drawLine(x0, y0, x1, y1, function(response) {
				console.log('Drew line from %d,%d to %d,%d.', x0, y0, x1, y1);

				$scope.getBuffer();
			});
		} else {
			AlertService.error('shutdown', 'The display is not initialised.', 'Please press the Startup button to begin.');
		}
	};

	$scope.drawRectangle = function() {
		if($scope.state.initialised) {
			var x = $scope.rect.x;
			var y = $scope.rect.y;
			var width = $scope.rect.width;
			var height = $scope.rect.height;
			var filled = $scope.rect.filled;

			GraphicsService.drawRectangle(x, y, width, height, filled, function(response) {
				console.log('Drew %srectangle with dimensions %dx%d at %d,%d.', (filled ? 'filled ' : ''), width, height, x, y);

				$scope.getBuffer();
			});
		} else {
			AlertService.error('shutdown', 'The display is not initialised.', 'Please press the Startup button to begin.');
		}
	};

	$scope.drawArc = function() {
		if($scope.state.initialised) {
			var x = $scope.arc.x;
			var y = $scope.arc.y;
			var radius = $scope.arc.radius;
			var startAngle = $scope.arc.startAngle;
			var endAngle = $scope.arc.endAngle;

			GraphicsService.drawArc(x, y, radius, startAngle, endAngle, function(response) {
				console.log('Drew arc from %d\xB0 to %d\xB0 with radius %d at %d,%d.', startAngle, endAngle, radius, x, y);

				$scope.getBuffer();
			});
		} else {
			AlertService.error('shutdown', 'The display is not initialised.', 'Please press the Startup button to begin.');
		}
	};

	$scope.drawCircle = function() {
		if($scope.state.initialised) {
			var x = $scope.circle.x;
			var y = $scope.circle.y;
			var radius = $scope.circle.radius;

			GraphicsService.drawCircle(x, y, radius, function(response) {
				console.log('Drew circle with radius %d at %d,%d.', radius, x, y);

				$scope.getBuffer();
			});
		} else {
			AlertService.error('shutdown', 'The display is not initialised.', 'Please press the Startup button to begin.');
		}
	};

	$scope.getCharset($scope.text.font);
}]);
