#import <Foundation/Foundation.h>
#import <Capacitor/Capacitor.h>

// Define the plugin using the CAP_PLUGIN Macro, and
// each method the plugin supports using the CAP_PLUGIN_METHOD macro.
CAP_PLUGIN(InsetsPlugin, "Insets",
           
           CAP_PLUGIN_METHOD(getSafeAreaInsets, CAPPluginReturnPromise);
           
           CAP_PLUGIN_METHOD(showStatusBar, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(hideStatusBar, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(setStatusBarColor, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(setStatusBarStyle, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(isStatusBarVisible, CAPPluginReturnPromise);
           
           CAP_PLUGIN_METHOD(showNavigationBar, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(hideNavigationBar, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(setNavigationBarColor, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(setNavigationBarStyle, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(isNavigationBarVisible, CAPPluginReturnPromise);
           
           CAP_PLUGIN_METHOD(showKeyboard, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(hideKeyboard, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(isKeyboardVisible, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(getKeyboardInsets, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(toggleAccessoryBar, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(toggleScroll, CAPPluginReturnPromise);
)
