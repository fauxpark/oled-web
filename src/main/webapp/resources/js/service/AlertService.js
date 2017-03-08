/**
 * A service that manages alert boxes.
 */
oled.service('AlertService', ['$rootScope', '$timeout', function($rootScope, $timeout) {
	$rootScope.alerts = [];

	/**
	 * Determine whether an alert with the given ID is already present in the list.
	 *
	 * @param {String} id The unique ID of the alert to check.
	 *
	 * @return {Boolean} Whether the alert is showing or not.
	 */
	this.isShowing = function(id) {
		return $rootScope.alerts.filter(function(alert) {
			return alert.id === id;
		}).length == 0;
	};

	/**
	 * Add an alert to the list.
	 *
	 * @param {String} id A unique ID that is used to determine whether the alert is already showing.
	 * @param {String} type The Bootstrap alert type, for setting the element class.
	 * @param {String} title The alert title.
	 * @param {String} message the alert message.
	 */
	this.add = function(id, type, title, message) {
		if(this.isShowing(id)) {
			$rootScope.alerts.push({
				id: id,
				type: type,
				title: title,
				message: message
			});

			$timeout(function() {
				$rootScope.alerts.shift();
			}, 5000);
		}
	};

	/**
	 * Remove an alert from the list.
	 *
	 * @param {Integer} index The array index of the alert to remove.
	 */
	this.remove = function(index) {
		$rootScope.alerts.splice(index, 1);
	};

	/**
	 * Add an error alert to the list.
	 *
	 * @param {String} id The unique ID of the alert.
	 * @param {String} title The alert title.
	 * @param {String} message The alert message.
	 */
	this.error = function(id, title, message) {
		this.add(id, 'danger', title, message);
	};

	/**
	 * Add a warning alert to the list.
	 *
	 * @param {String} id The unique ID of the alert.
	 * @param {String} title The alert title.
	 * @param {String} message The alert message.
	 */
	this.warn = function(id, title, message) {
		this.add(id, 'warning', title, message);
	};

	/**
	 * Add an info alert to the list.
	 *
	 * @param {String} id The unique ID of the alert.
	 * @param {String} title The alert title.
	 * @param {String} message The alert message.
	 */
	this.info = function(id, title, message) {
		this.add(id, 'info', title, message);
	};

	/**
	 * Add a success alert to the list.
	 *
	 * @param {String} id The unique ID of the alert.
	 * @param {String} title The alert title.
	 * @param {String} message The alert message.
	 */
	this.success = function(id, title, message) {
		this.add(id, 'success', title, message);
	};
}]);
