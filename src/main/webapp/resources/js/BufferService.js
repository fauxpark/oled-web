/**
 * A service that interacts with the preview canvas.
 */
oled.service('BufferService', [function() {
	var canvas = $('#canvas').get(0);
	var ctx = canvas.getContext('2d');
	var img;

	return {
		/**
		 * Setup the canvas to the correct size.
		 *
		 * @param {Integer} width The width to set the canvas to.
		 * @param {Integer} height The height to set the canvas to.
		 */
		setSize: function(width, height) {
			canvas.width = width;
			canvas.height = height;
			canvas.style.width = width;
			canvas.style.height = height;
			img = ctx.getImageData(0, 0, width, height);
		},
		/**
		 * Retrieve the display canvas buffer.
		 *
		 * @return {Buffer} A display buffer object.
		 */
		getBuffer: function() {
			var buffer = new Array(1024).fill(0);

			for(var i = 0; i < img.data.length; i += 4) {
				var x = (i / 4) % canvas.width;
				var y = parseInt((i / 4) / canvas.width);

				if(img.data[i] == 255) {
					var idx = x + parseInt(y / 8) * canvas.width;
					buffer[idx] |= (1 << (y & 7));
				}
			}

			return {
				width: canvas.width,
				height: canvas.height,
				buffer: buffer
			};
		},
		/**
		 * Set the display canvas buffer.
		 *
		 * @param {Buffer} buffer The buffer to set.
		 */
		setBuffer: function(buffer) {
			for(var i = 0; i < buffer.buffer.length; i++) {
				var x = i % buffer.width;
				var y = parseInt(i / buffer.width) * 8;

				for(var j = 0; j < 8; j++) {
					var yo = y + j % 8;
					var offset = 4 * (x + yo * buffer.width);
					var on = (buffer.buffer[i] & (1 << (yo & 7))) != 0;

					img.data[offset] = on ? 255 : 0;
					img.data[offset + 1] = on ? 255 : 0;
					img.data[offset + 2] = on ? 255 : 0;
					img.data[offset + 3] = on ? 255 : 0;
				}
			}

			ctx.putImageData(img, 0, 0);
		},
		/**
		 * Get a pixel on the canvas.
		 *
		 * @param {Integer} x The X position of the pixel to get.
		 * @param {Integer} y The Y position of the pixel to get.
		 *
		 * @return {Boolean} Whether the pixel is on or off.
		 */
		getPixel: function(x, y) {
			var offset = 4 * (x + y * canvas.width);

			if(img.data[i] == 255) {
				return true;
			}

			return false;
		},
		/**
		 * Set a pixel on the canvas.
		 *
		 * @param {Integer} x The X position of the pixel to set.
		 * @param {Integer} y The Y position of the pixel to set.
		 * @param {Boolean} on Whether to turn the pixel on or off.
		 */
		setPixel: function(x, y, on) {
			var offset = 4 * (x + y * canvas.width);
			img.data[offset] = on ? 255 : 0;
			img.data[offset + 1] = on ? 255 : 0;
			img.data[offset + 2] = on ? 255 : 0;
			img.data[offset + 3] = on ? 255 : 0;
			ctx.putImageData(img, 0, 0);
		},
		/**
		 * Clear the canvas.
		 */
		clear: function() {
			img = ctx.createImageData(img);
			ctx.putImageData(img, 0, 0);
		}
	};
}]);
