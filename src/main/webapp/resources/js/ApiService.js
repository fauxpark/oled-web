/**
 * A service to provide access to the OLED API.
 */
oled.service('ApiService', ['$http', 'AlertService', function($http, AlertService) {
	return {
		/**
		 * Perform a GET to the OLED API.
		 *
		 * @param {String} url The API URL.
		 * @param {Function} [callback] A callback to pass the response object onto.
		 */
		get: function(url, callback) {
			$http.get(path + url).then(function(response) {
				if(response.data.ok) {
					if(callback) {
						callback(response.data);
					}
				} else {
					AlertService.error('api', 'Error', response.data.message);
				}
			});
		},
		/**
		 * Perform a GET request for a character set JSON file.
		 *
		 * @param {String} name The name of the character set, eg. 'cp850'.
		 * @param {Function} [callback] A callback to pass the response object onto.
		 */
		getCharset: function(name, callback) {
			$http.get(path + 'assets/js/charset/' + name + '.json').then(function(response) {
				if(callback) {
					callback(response.data);
				}
			});
		},
		/**
		 * Perform a POST to the OLED API.
		 *
		 * @param {String} url The API URL.
		 * @param {Object|null} data The JSON data to send.
		 * @param {Function} [callback] A callback to pass the response object onto.
		 */
		post: function(url, data, callback) {
			$http.post(path + url, data).then(function(response) {
				if(response.data.ok) {
					if(callback) {
						callback(response.data);
					}
				} else {
					AlertService.error('api', 'Error', response.data.message);
				}
			});
		},
		/**
		 * Perform a POST to the OLED API, for uploading image files.
		 *
		 * @param {String} url The API URL.
		 * @param {FormData} The form data to send.
		 * @param {Function} [callback] A callback to pass the response object onto.
		 */
		postImage: function(url, data, callback) {
			$http.post(path + url, data, {
				transformRequest: angular.identity,
				headers: {
					'content-type': undefined
				}
			}).then(function(response) {
				if(response.data.ok) {
					if(callback) {
						callback(response.data);
					}
				} else {
					AlertService.error('api', 'Error', response.data.message);
				}
			});
		}
	};
}]);
