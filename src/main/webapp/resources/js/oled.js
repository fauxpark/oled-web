var oled = angular.module('oled', []);

oled.service('OledService', ['$http', function($http) {
	return {
		/**
		 * Start up the display.
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
		 */
		shutdown: function(callback) {
			$http.post('/oled/api/shutdown').then(function(response) {
				if(callback) {
					callback(response);
				}
			});
		},
		/**
		 * Invert the display.
		 */
		invert: function(callback) {
			$http.post('/oled/api/invert').then(function(response) {
				if(callback) {
					callback(response);
				}
			});
		},
	};
}]);

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

	$scope.invert = function() {
		OledService.invert(function(response) {
			alert('Inverted display.');
		});
	};
}]);
