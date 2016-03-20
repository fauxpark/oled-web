var oled = angular.module('oled', []);

/**
 * A simple display buffer object that contains the pixel data for the entire display.
 *
 * @typedef {Object} Buffer
 * @property {Integer} width
 * @property {Integer} height
 * @property {Array.<Integer>} buffer The pixel data as an array of bytes, each representing columns of eight pixels.
 */
