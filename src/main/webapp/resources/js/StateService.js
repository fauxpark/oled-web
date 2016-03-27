/**
 * A service to provide state information.
 */
oled.service('StateService', [function() {
	this.width = 0;

	this.height = 0;

	this.initialised = false;

	this.displayOn = false;

	this.inverted = false;

	this.hFlipped = false;

	this.vFlipped = false;

	this.contrast = 0;

	this.getState = function() {
		return {
			width: this.width,
			height: this.height,
			initialised: this.initialised,
			displayOn: this.displayOn,
			inverted: this.inverted,
			hFlipped: this.hFlipped,
			vFlipped: this.vFlipped,
			contrast: this.contrast
		};
	};

	this.setState = function(state) {
		this.width = state.width;
		this.height = state.height;
		this.initialised = state.initialised;
		this.displayOn = state.displayOn;
		this.inverted = state.inverted;
		this.hFlipped = state.hFlipped;
		this.vFlipped = state.vFlipped;
		this.contrast = state.contrast;
	};
}]);
