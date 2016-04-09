/**
 * A service for accessing the display buffer contents.
 */
oled.service('BufferService', ['ApiService', function(ApiService) {
	return {
		/**
		 * Retrieve the display buffer.
		 *
		 * @param {Function} callback A callback to pass the response object onto.
		 */
		getBuffer: function(callback) {
			ApiService.get('api/buffer', callback);
		},
		/**
		 * Set the display buffer.
		 *
		 * @param {Buffer} buffer The buffer to set.
		 * @param {Function} [callback] A callback to pass the response object onto.
		 */
		setBuffer: function(buffer, callback) {
			ApiService.post('api/buffer', buffer, callback);
		},
		/**
		 * Clear the display.
		 *
		 * @param {Function} [callback] A callback to pass the response object onto.
		 */
		clear: function(callback) {
			ApiService.post('api/clear', null, callback);
		},
	};
}]);
