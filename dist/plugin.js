var capacitorInsets = (function (exports, core) {
    'use strict';

    var Style;
    (function (Style) {
        Style["Dark"] = "dark";
        Style["Light"] = "light";
        Style["Default"] = "default";
    })(Style || (Style = {}));
    var InsetsType;
    (function (InsetsType) {
        InsetsType["SafeArea"] = "safe-area";
        InsetsType["Keyboard"] = "keyboard";
    })(InsetsType || (InsetsType = {}));

    /**
     * Update safe area insets CSS variables.
     * @param {Rect} insets
     */
    function updateSafeAreaInsets(insets) {
        const prefix = 'safe';
        const style = document.documentElement.style;
        style.setProperty(`--${prefix}-ins-top`, `${insets.top}px`);
        style.setProperty(`--${prefix}-ins-right`, `${insets.right}px`);
        style.setProperty(`--${prefix}-ins-bottom`, `${insets.bottom}px`);
        style.setProperty(`--${prefix}-ins-left`, `${insets.left}px`);
    }
    /**
     * Update keyboard insets CSS variables.
     * @param {Rect} insets
     */
    function updateKeyboardInsets(insets) {
        const prefix = 'keyb';
        const style = document.documentElement.style;
        style.setProperty(`--${prefix}-ins-bottom`, `${insets.bottom}px`);
    }
    // Plugin registration.
    const Insets = core.registerPlugin('Insets');
    if (core.Capacitor.getPlatform() !== 'web') {
        // Get safe area insets at the start.
        Insets.getSafeAreaInsets().then(insets => {
            updateSafeAreaInsets(insets);
        }, err => console.error(err));
        // Get keyboard insets at the start.
        Insets.getKeyboardInsets().then(insets => {
            updateKeyboardInsets(insets);
        }, err => console.error(err));
        // "insetschange" event.
        Insets.addListener('insets', event => {
            console.log(`[insets] ${event.type}: ${JSON.stringify(event.insets)}`);
            if (InsetsType.SafeArea === event.type) {
                updateSafeAreaInsets(event.insets);
            }
            else if (InsetsType.Keyboard === event.type) {
                updateKeyboardInsets(event.insets);
            }
        }).catch(err => console.error(err));
    }

    exports.Insets = Insets;

    Object.defineProperty(exports, '__esModule', { value: true });

    return exports;

})({}, capacitorExports);
//# sourceMappingURL=plugin.js.map
