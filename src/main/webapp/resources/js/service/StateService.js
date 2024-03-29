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

  this.scrolling = false;

  this.contrast = 0;

  this.offset = 0;

  this.getState = function() {
    return {
      width: this.width,
      height: this.height,
      initialised: this.initialised,
      displayOn: this.displayOn,
      inverted: this.inverted,
      hFlipped: this.hFlipped,
      vFlipped: this.vFlipped,
      scrolling: this.scrolling,
      contrast: this.contrast,
      offset: this.offset
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
    this.scrolling = state.scrolling;
    this.contrast = state.contrast;
    this.offset = state.offset;
  };
}]);
