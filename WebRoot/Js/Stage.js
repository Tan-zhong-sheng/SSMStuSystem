// Inlined Stage.js dependency: Ticker.js

/**
 * Ticker.js
 * -----------
 * requestAnimationFrame helper. Provides elapsed time between frames and a lag compensation multiplier to callbacks.
 *
 * Author: Caleb Miller
 *         caleb@caleb-miller.com
*/

/**
 * Stage.js
 * -----------
 * Super simple "stage" abstraction for canvas. Combined with Ticker.js, it helps simplify:
 *   - Preparing a canvas for drawing.
 *   - High resolution rendering.
 *   - Resizing the canvas.
 *   - Frame callbacks with useful timing data and calculated lag.
 *
 * Also provides a factory for a Sprite instance that simplfies rendering and transforming objects.
 * There is additionally a CachedSprite subclass that caches instance drawing using an internal canvas.
 * CachedSprites must be manually rerendered if changed but can offer a ~40% speedup for static objects.
 *
 *
 *
 * Author: Caleb Miller
 *         caleb@caleb-miller.com
*/


const Ticker = (function TickerFactory(window) {
	'use strict';

	const Ticker = {};


	// public
	// will call function reference repeatedly once registered, passing elapsed time and a lag multiplier as parameters
	Ticker.addListener = function addListener(callback) {
		if (typeof callback !== 'function') throw('Ticker.addListener() requires a function reference passed for a callback.');

		listeners.push(callback);

		// start frame-loop lazily
		if (!started) {
			started = true;
			queueFrame();
		}
	};

	// private
	let started = false;
	let lastTimestamp = 0;
	let listeners = [];

	// queue up a new frame (calls frameHandler)
	function queueFrame() {
		if (window.requestAnimationFrame) {
			requestAnimationFrame(frameHandler);
		} else {
			webkitRequestAnimationFrame(frameHandler);
		}
	}

	function frameHandler(timestamp) {
		let frameTime = timestamp - lastTimestamp;
		lastTimestamp = timestamp;
		// make sure negative time isn't reported (first frame can be whacky)
		if (frameTime < 0) {
			frameTime = 17;
		}
		// - cap minimum framerate to 15fps[~68ms] (assuming 60fps[~17ms] as 'normal')
		else if (frameTime > 68) {
			frameTime = 68;
		}

		// fire custom listeners
		listeners.forEach(listener => listener.call(window, frameTime, frameTime / 16.6667));

		// always queue another frame
		queueFrame();
	}


	return Ticker;

})(window);



const Stage = (function StageFactory(window, document, Ticker) {
	'use strict';

	/**
	 * Invariant helper throws error message if given condition is falsy.
	 * Prefixes message with "Stage Warning: " and throws as a new Error.
	 *
	 * @param  {bool}   condition - Condition to check.
	 * @param  {string} msg       - Warning message to display.
	 *
	 * @return {void}
	 */
	function invariant(condition, msg) {
		if (!condition) {
			throw new Error('Stage Warning: ' + msg);
		}
	}


	class Sprite {
		constructor(config) {
			const {
				x = 0,
				y = 0,
				width,
				height,
				rotation = 0,
				scaleX = 1,
				scaleY = 1,
				transformOriginX = 0.5,
				transformOriginY = 0.5,
				draw
			} = config;

			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
			// Radians
			this.rotation = rotation;
			// Percentage where 1 = 100%
			this.scaleX = scaleX;
			this.scaleY = scaleY;
			// Percentage where 1 = 100%
			this.transformOriginX = transformOriginX;
			this.transformOriginY = transformOriginY;
			this.userDraw = draw;
		}

		setTransformOrigin(x, y) {
			this.transformOriginX = x;
			this.transformOriginY = y;
		}

		// Copy internal canvas to external canvas.
		drawOnContext(ctx) {
			const {
				x,
				y,
				width,
				height,
				rotation,
				scaleX,
				scaleY,
				transformOriginX,
				transformOriginY
			} = this;

			const shouldRotate = rotation !== 0;
			const shouldScale = scaleX !== 1 || scaleY !== 1;
			const shouldTransform = shouldRotate || shouldScale;

			const offsetX = width * transformOriginX;
			const offsetY = height * transformOriginY;
			ctx.save()
			!shouldTransform && ctx.translate(x, y);
			shouldTransform && ctx.translate(x + offsetX, y + offsetY);
			shouldRotate && ctx.rotate(rotation);
			shouldScale && ctx.scale(scaleX, scaleY);
			shouldTransform && ctx.translate(-offsetX, -offsetY);
			this.userDraw(ctx, this);
			ctx.restore();
		}
	}


	class CachedSprite {
		// Note: CachedSprite requires a stage argument
		constructor(stage, config) {
			const {
				x = 0,
				y = 0,
				width,
				height,
				rotation = 0,
				scaleX = 1,
				scaleY = 1,
				transformOriginX = 0.5,
				transformOriginY = 0.5,
				draw,
				// The following properties are unique to CachedSprite.
				// -------------------------------------------------------
				// Performs initial internal draw at construction time.
				performFirstDraw = true,
				// Clears the internal canvas before each redraw.
				autoClear = true
			} = config;

			this.stage = stage;
			this.x = x;
			this.y = y;
			// Radians
			this.rotation = rotation;
			// Percentage where 1 = 100%
			this.scaleX = scaleX;
			this.scaleY = scaleY;
			// Percentage where 1 = 100%
			this.transformOriginX = transformOriginX;
			this.transformOriginY = transformOriginY;
			this.canvas = document.createElement('canvas');
			this.ctx = this.canvas.getContext('2d');
			this.userDraw = draw;
			this.autoClear = autoClear;

			// Apply initial dimensions.
			this.resize(width, height);
			// Perform initial draw on internal canvas.
			performFirstDraw && this.redraw();
		}

		// Set dimensions and resize internal canvas.
		// Note: No need to set width/height style because Sprite canvases should not be added to the DOM, only drawn on other canvases.
		resize(w, h) {
			this.width = w;
			this.height = h;
			this.naturalWidth = w * this.stage.dpr;
			this.naturalHeight = h * this.stage.dpr;
			this.canvas.width = this.naturalWidth;
			this.canvas.height = this.naturalHeight;
		}

		setTransformOrigin(x, y) {
			this.transformOriginX = x;
			this.transformOriginY = y;
		}

		// Draw sprite on internal canvas.
		// Defers to user-supplied draw function.
		redraw() {
			const { stage, ctx } = this;
			// Clear canvas if needed (using native canvas dimensions).
			this.autoClear && ctx.clearRect(0, 0, this.naturalWidth, this.naturalHeight);
			// To support high DPI screens, upscaling must happen on internal canvases manually.
			// To compensate, when copying to an external canvas the width/height should be the sprite's width/height,
			// not the internal canvas's width/height.
			stage.autoScale && ctx.scale(stage.dpr, stage.dpr);
			this.userDraw(ctx, this);
			ctx.setTransform(1, 0, 0, 1, 0, 0);
		}

		// Copy internal canvas to external canvas.
		drawOnContext(ctx) {
			const {
				canvas,
				x,
				y,
				width,
				height,
				rotation,
				scaleX,
				scaleY,
				transformOriginX,
				transformOriginY
			} = this;

			const shouldRotate = rotation !== 0;
			const shouldScale = scaleX !== 1 || scaleY !== 1;

			// It's much faster to avoid a save, transform, restore cycle on the canvas context, so if no transform is needed
			// the image should be copied as is. This has yielded a ~38% improvement in Chrome.
			if (!shouldRotate && !shouldScale) {
				ctx.drawImage(canvas, x, y, width, height);
			}
			else {
				const offsetX = width * transformOriginX;
				const offsetY = height * transformOriginY;
				ctx.save()
				ctx.translate(x + offsetX, y + offsetY);
				shouldRotate && ctx.rotate(rotation);
				shouldScale && ctx.scale(scaleX, scaleY);
				ctx.drawImage(canvas, -offsetX, -offsetY, width, height);
				ctx.restore();
			}
		}
	}

	/*
	   TODO:
		  - `destoy()` method that removes canvas and cleans up listeners.
			- Make fullscreen stages auto-mount to <body> (and not require `container`)?
	*/
	/**
	 * Stage constructor
	 *
	 * @param {HTMLElement|CSSSelector} options.container       - The container to append <canvas>.
	 * @param {string?}                 options.className       - Class string to add to <canvas>.
	 * @param {int?}                    options.width           - Initial width in DIPs. Defaults to viewport width.
	 * @param {int?}                    options.height          - Initial height in DIPs. Defaults to viewport height.
	 * @param {bool?}                   options.fullscreen=true - Sets canvas width/height to the viewport size (and uses display: block).
	 * @param {bool?}                   options.highDPI=true    - Multiplies internal canvas dimensions by the device pixel ratio.
	 * @param {bool?}                   options.autoScale=true  - On high DPI displays and if `highDPI` is `true`, performs a `scale` operation on the drawing
	                                                            - before calling `draw` event handlers. This allows high DPI drawing to work automatically,
																															- which is generally what is desired.
	 */
	class Stage {
		constructor({ container, className, width, height, fullscreen=true, highDPI=true, autoScale=true }) {
			let containerElement;

			// Resolve container element.
			if (container instanceof HTMLElement) {
				containerElement = container;
			}
			else if (typeof container === 'string') {
				containerElement = document.querySelector(container);
				invariant(containerElement, `container CSS selector "${container}" did not match any elements.`);
			}
			else {
				invariant(false, 'Invalid container type.');
			}

			// Create canvas and attach to given element.
			const canvas = document.createElement('canvas');
			containerElement.appendChild(canvas);

			// Add class if given.
			if (className) {
				invariant(typeof className === 'string', 'className must be a string.');
				canvas.classList.add(className);
			}

			// Enforce numeric dimensions, if given.
			invariant(!width || typeof width === 'number', 'width must be numeric.');
			invariant(!height || typeof height === 'number', 'height must be numeric.');

			// canvas and associated context references
			this.canvas = canvas;
			this.ctx = canvas.getContext('2d');

			// Set canvas styles
			// Prevent gestures on stages (scrolling, zooming, etc)
			this.canvas.style.touchAction = 'none';

			// physics speed multiplier: allows slowing down or speeding up simulation (must be manually implemented in physics layer)
			this.speed = 1;

			// devicePixelRatio alias (should only be used for rendering, physics shouldn't care)
			// avoids rendering unnecessary pixels that browser might handle natively via CanvasRenderingContext2D.backingStorePixelRatio
			this.dpr = highDPI ? ((window.devicePixelRatio || 1) / (this.ctx.backingStorePixelRatio || 1)) : 1;

			// Whether drawing performed in draw() calls should be automatically scaled for high resolution displays.
			// This is nice for crisp vector drawings, but kinda sucks for drawing prerendered canvases.
			// If high DPI drawing is disabled, autoScale is also disabled as it serves no purpose.
			// Note that this also affects Sprite instances created from the stage.
			this.autoScale = autoScale && highDPI;

			// Track event handlers. Only one listener is allowed per event, but that should be enough.
			this.onTick = null;
			this.onDraw = null;
			this.onResize = null;

			// Set initial canvas dimensions.
			this.resize(fullscreen ? window.innerWidth : width, fullscreen ? window.innerHeight : height);

			// Store Sprite factory on stage instance.
			// This allows seamlessly passing the current stage instance through to sprite.
			this.createSprite = config => new Sprite(config);
			this.createCachedSprite = config => new CachedSprite(this, config);

			// Special fullscreen treatment
			if (fullscreen) {
				// Use `display: block` for fullscreen stages to avoid scrollbars
				this.canvas.style.display = 'block';

				// Auto-resize fullscreen canvas
				window.addEventListener('resize', () => {
					this.resize(window.innerWidth, window.innerHeight);
				});
			}

			Stage.stages.push(this);
		}

		dispatchEventWithSelf(eventName) {
			const listener = this[eventName];
			listener && listener(this);
		}

		resize(w, h) {
			this.width = w;
			this.height = h;
			this.naturalWidth = w * this.dpr;
			this.naturalHeight = h * this.dpr;
			this.canvas.width = this.naturalWidth;
			this.canvas.height = this.naturalHeight;
			this.canvas.style.width = w + 'px';
			this.canvas.style.height = h + 'px';

			this.dispatchEventWithSelf('onResize');
		}
	}

	// track all Stage instances
	Stage.stages = [];

	// utility function for coordinate space conversion
	Stage.windowToCanvas = function windowToCanvas(canvas, x, y) {
		const bbox = canvas.getBoundingClientRect();
		return {
				x: (x - bbox.left) * (canvas.width / bbox.width),
				y: (y - bbox.top) * (canvas.height / bbox.height)
		};
	};

	// Start ticker
	Ticker.addListener(function handleTick(frameTime, lag) {
		// Set ticker arguments as properties on stage so they can be destructured from stage instance passed to event handlers.
		// Event handlers should not keep a reference to or modify the passed object in any way!
		// NOTE: Passing the full Stage instance to event handlers is a little sloppy, by means we can avoid creating new objects all the time.
		//       An alternative is to keep special event objects cached that are modified before each event, but this means copying most stage
		//       properties anyhow. Regardless, this should be invisible to handlers.
		Stage.stages.forEach(stage => {
			stage.simTime = stage.speed * frameTime;
			stage.simSpeed = stage.speed * lag;
			stage.lag = lag;
			stage.dispatchEventWithSelf('onTick');

			// Auto scale drawing if requested
			stage.autoScale && stage.ctx.scale(stage.dpr, stage.dpr);
			stage.dispatchEventWithSelf('onDraw');
			stage.autoScale && stage.ctx.setTransform(1, 0, 0, 1, 0, 0);
		});
	});

	return Stage;

})(window, document, Ticker);
