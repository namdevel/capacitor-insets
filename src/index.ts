import {Capacitor, registerPlugin} from '@capacitor/core';

import type { InsetsPlugin, Rect } from './definitions';
import { InsetsType } from './definitions';

/**
 * Update safe area insets CSS variables.
 * @param {Rect} insets
 */
function updateSafeAreaInsets(insets: Rect): void {
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
function updateKeyboardInsets(insets: Rect): void {
  const prefix = 'keyb';
  const style = document.documentElement.style;
  style.setProperty(`--${prefix}-ins-bottom`, `${insets.bottom}px`);
}

// Plugin registration.
const Insets = registerPlugin<InsetsPlugin>('Insets');

if (Capacitor.getPlatform() !== 'web') {

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
    } else if (InsetsType.Keyboard === event.type) {
      updateKeyboardInsets(event.insets);
    }
  }).catch(err => console.error(err));

}

export { Insets };
