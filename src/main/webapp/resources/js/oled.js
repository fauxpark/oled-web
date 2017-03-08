var oled = angular.module('oled', []);

/**
 * A simple display buffer object that contains the pixel data for the entire display.
 *
 * @typedef {Object} Buffer
 * @property {Integer} width The width of the buffer.
 * @property {Integer} height The height of the buffer.
 * @property {Array.<Integer>} buffer The pixel data as an array of bytes, each representing columns of eight pixels.
 */
