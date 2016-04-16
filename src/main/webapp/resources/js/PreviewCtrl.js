oled.controller('PreviewCtrl', ['$scope', 'StateService', 'PreviewService', 'BufferService', function($scope, StateService, PreviewService, BufferService) {
	$scope.state = StateService;

	$scope.setBuffer = function($event) {
		if($scope.state.initialised) {
			BufferService.setBuffer(PreviewService.getBuffer(), function(response) {
				console.log('Set buffer.');
			});
		}
	};

	$scope.setPixel = function($event) {
		if($event.buttons == 1) {
			if($scope.state.initialised) {
				var rect = canvas.getBoundingClientRect();
				var x = $event.clientX - Math.floor(rect.left) - 1;
				var y = $event.clientY - Math.floor(rect.top) - 1;
				var on = $('#input-pixel-on').hasClass('active');

				PreviewService.setPixel(x, y, on, function(response) {
					console.log('Turned pixel at ' + x + ',' + y + ' ' + (on ? 'on' : 'off') + '.');
				});
			}
		}
	};
}]);
