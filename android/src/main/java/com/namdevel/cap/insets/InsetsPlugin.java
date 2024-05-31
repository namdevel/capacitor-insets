package com.namdevel.cap.insets;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import com.getcapacitor.JSObject;
import com.getcapacitor.Logger;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.util.WebColor;


@CapacitorPlugin(name = "Insets")
public class InsetsPlugin extends Plugin {

    private static final String TAG = "Insets";

    private static final String TYPE_SAFE_AREA = "safe-area";
    private static final String TYPE_KEYBOARD  = "keyboard";

    private static final String STYLE_LIGHT = "light";

    private boolean statusBarVisible = true;

    private boolean navigationBarVisible = true;

    private boolean keyboardVisible = false;

    private Insets keyboardInsets = Insets.NONE;

    private Insets safeAreaInsets = Insets.NONE;


    @Override
    public void load() {
        Logger.debug(TAG, "load()");

        Window window = bridge.getActivity().getWindow();
        WindowCompat.setDecorFitsSystemWindows(window, false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            WindowManager.LayoutParams params = window.getAttributes();
            params.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }

        View decorView = window.getDecorView();
        WindowInsetsControllerCompat controller = new WindowInsetsControllerCompat(window, decorView);
        controller.setSystemBarsBehavior(WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);

        ViewCompat.setOnApplyWindowInsetsListener(decorView, (view, windowInsets) -> {
            Logger.debug(TAG, "onApplyWindowInsetsListener()");

            Insets ins;
            ins = computeSafeAreaInsets(windowInsets);
            if (!ins.equals(safeAreaInsets)) {
                notifyInsets(TYPE_SAFE_AREA, safeAreaInsets = ins);
            }
            ins = computeKeyboardInsets(windowInsets);
            if (!ins.equals(keyboardInsets)) {
                notifyInsets(TYPE_KEYBOARD, keyboardInsets = ins);
            }

            final boolean visible = windowInsets.isVisible(WindowInsetsCompat.Type.ime());
            if (keyboardVisible != visible) {
                if (!visible) {
                    // NOTE: Cuando se oculta el teclado nos aseguramos que las barras de estado y navegación recuperan
                    // el estado en el que se encontraban previamente.
                    if (!statusBarVisible) {
                        _hideStatusBar();
                    }
                    if (!navigationBarVisible) {
                        _hideNavigationBar();
                    }
                }
                notifyListeners(visible ? "keyboardshow" : "keyboardhide", insetsToJSObject(keyboardInsets));
                keyboardVisible = visible;
            }
            return windowInsets; // WindowInsetsCompat.CONSUMED?
        });
    }


    /* SAFE-AREA
     * ============================================================================================================== */
    @PluginMethod
    public void getSafeAreaInsets(PluginCall call) {
        call.resolve(insetsToJSObject(safeAreaInsets));
    }


    /* STATUS-BAR
     * ============================================================================================================== */
    @PluginMethod
    public void showStatusBar(PluginCall call) {
        _showStatusBar();
        statusBarVisible = true;
    }

    private void _showStatusBar() {
        bridge.executeOnMainThread(() -> {
            WindowInsetsControllerCompat controller = getController();
            controller.show(WindowInsetsCompat.Type.statusBars());
        });
    }

    @PluginMethod
    public void hideStatusBar(PluginCall call) {
        _hideStatusBar();
        statusBarVisible = false;
    }

    private void _hideStatusBar() {
        bridge.executeOnMainThread(() -> {
            WindowInsetsControllerCompat controller = getController();
            controller.hide(WindowInsetsCompat.Type.statusBars());
        });
    }

    @PluginMethod
    public void setStatusBarColor(PluginCall call) {
        final String color = call.getString("color");
        if (color == null) {
            call.reject("Parameter \"color\" is required.");
            return;
        }
        bridge.executeOnMainThread(() -> {
            final Window window = bridge.getActivity().getWindow();
            try {
                final int c = WebColor.parseColor(color.toUpperCase());
                window.setStatusBarColor(c);
                call.resolve();
            } catch (IllegalArgumentException ex) {
                call.reject("Invalid color format");
            }
        });
    }

    @PluginMethod
    public void setStatusBarStyle(PluginCall call) {
        final String style = call.getString("style");
        if (style == null) {
            call.reject("Parameter \"style\" is required.");
            return;
        }
        bridge.executeOnMainThread(() -> {
            getController().setAppearanceLightStatusBars(STYLE_LIGHT.equals(style));
        });
    }

    @PluginMethod
    public void isStatusBarVisible(PluginCall call) {
        JSObject data = new JSObject();
        data.put("visible", statusBarVisible);
        // NOTE: No funciona en algunos dispositivos, por eso necesitamos el flag "statusBarVisible".
        // data.put("visible", getWindowInsets().isVisible(WindowInsetsCompat.Type.statusBars()));
        call.resolve(data);
    }


    /* NAVIGATION-BAR
     * ============================================================================================================== */
    @PluginMethod
    public void showNavigationBar(PluginCall call) {
        bridge.executeOnMainThread(() -> {
            WindowInsetsControllerCompat controller = getController();
            controller.show(WindowInsetsCompat.Type.navigationBars());
        });
        navigationBarVisible = true;
    }

    @PluginMethod
    public void hideNavigationBar(PluginCall call) {
        _hideNavigationBar();
        navigationBarVisible = false;
    }

    private void _hideNavigationBar() {
        bridge.executeOnMainThread(() -> {
            WindowInsetsControllerCompat controller = getController();
            controller.hide(WindowInsetsCompat.Type.navigationBars());
        });
    }

    @PluginMethod
    public void setNavigationBarColor(PluginCall call) {
        final String color = call.getString("color");
        if (color == null) {
            call.reject("Parameter \"color\" is required.");
            return;
        }
        bridge.executeOnMainThread(() -> {
            final Window window = bridge.getActivity().getWindow();
            try {
                final int c = WebColor.parseColor(color.toUpperCase());
                window.setNavigationBarColor(c);
                call.resolve();
            } catch (IllegalArgumentException ex) {
                call.reject("Invalid color format");
            }
        });
    }

    @PluginMethod
    public void setNavigationBarStyle(PluginCall call) {
        final String style = call.getString("style");
        if (style == null) {
            call.reject("Parameter \"style\" is required.");
            return;
        }
        bridge.executeOnMainThread(() -> {
            getController().setAppearanceLightNavigationBars(STYLE_LIGHT.equals(style));
        });
    }

    @PluginMethod
    public void isNavigationBarVisible(PluginCall call) {
        JSObject data = new JSObject();
        data.put("visible", navigationBarVisible);
        // NOTE: No funciona en algunos dispositivos, por eso necesitamos el flag "navigationBarVisible".
        // data.put("visible", getWindowInsets().isVisible(WindowInsetsCompat.Type.navigationBars()));
        call.resolve(data);
    }


    /* KEYBOARD
     * ============================================================================================================== */
    @PluginMethod
    public void showKeyboard(PluginCall call) {
        bridge.executeOnMainThread(() -> {
            getController().show(WindowInsetsCompat.Type.ime());
        });
    }

    @PluginMethod
    public void hideKeyboard(PluginCall call) {
        bridge.executeOnMainThread(() -> {
            getController().hide(WindowInsetsCompat.Type.ime());
        });
    }

    @PluginMethod
    public void isKeyboardVisible(PluginCall call) {
        JSObject data = new JSObject();
        data.put("visible", getWindowInsets().isVisible(WindowInsetsCompat.Type.ime()));
        call.resolve(data);
    }

    @PluginMethod
    public void getKeyboardInsets(PluginCall call) {
        call.resolve(insetsToJSObject(keyboardInsets));
    }

    @PluginMethod
    public void toggleAccessoryBar(PluginCall call) {
        call.unavailable("Not implemented on Android.");
    }

    @PluginMethod
    public void toggleScroll(PluginCall call) {
        call.unavailable("Not implemented on Android.");
    }


    /* UTIL
     * ============================================================================================================== */

    private Insets computeSafeAreaInsets(WindowInsetsCompat windowInsets) {
        Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.displayCutout());
        if (statusBarVisible || Build.VERSION.SDK_INT < Build.VERSION_CODES.P && keyboardVisible) {
            // NOTE: Para versiones inferiores a Android 9 (28) asumimos que mientras se visualice el teclado en
            // pantalla siempre se visualiza la barra de estado.
            // NOT WORKING! windowInsets.isVisible(WindowInsetsCompat.Type.statusBars())
            insets = Insets.max(insets, windowInsets.getInsets(WindowInsetsCompat.Type.statusBars()));
        }
        if (navigationBarVisible) {
            // NOT WORKING! windowInsets.isVisible(WindowInsetsCompat.Type.navigationBars())
            insets = Insets.max(insets, windowInsets.getInsets(WindowInsetsCompat.Type.navigationBars()));
        }
        return insets;
    }

    private Insets computeKeyboardInsets(WindowInsetsCompat windowInsets) {
        return windowInsets.getInsets(WindowInsetsCompat.Type.ime());
    }

    private void notifyInsets(String type, Insets insets) {
        final JSObject data = new JSObject();
        data.put("type", type);
        data.put("insets", insetsToJSObject(insets));
        notifyListeners("insets", data);
    }

    private JSObject insetsToJSObject(Insets insets) {
        final float density = getDensity();
        final JSObject obj = new JSObject();
        obj.put("top", Math.round(insets.top / density));
        obj.put("right", Math.round(insets.right / density));
        obj.put("bottom", Math.round(insets.bottom / density));
        obj.put("left", Math.round(insets.left / density));
        return obj;
    }

    private float getDensity() {
        return bridge.getActivity().getResources().getDisplayMetrics().density;
    }

    private WindowInsetsCompat getWindowInsets() {
        return WindowInsetsCompat.toWindowInsetsCompat(bridge.getWebView().getRootWindowInsets());
    }

    private WindowInsetsControllerCompat getController() {
        Window window = bridge.getActivity().getWindow();
        return new WindowInsetsControllerCompat(window, window.getDecorView());
    }

}
