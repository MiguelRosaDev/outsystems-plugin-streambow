package com.streambow;

import com.streambow.xperience.xperience.TestCallback;
import com.streambow.xperience.xperience.TestProgress;
import com.streambow.xperience.xperience.TestStatus;
import com.streambow.xperience.xperience.Xperience;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Streambow extends CordovaPlugin implements TestCallback {

    private Xperience xperience = Xperience.getInstance(cordova.getContext());

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("initializer")) {
            String message = args.getString(0);
            this.initializer(message, callbackContext);
            return true;
        } else if (action.equals("performTest")) {
            String message = args.getString(0);
            this.performTest(message, callbackContext);
            return true;
        }
        return false;
    }

    //@Override
    //public void initializer(CordovaInterface cordova, CordovaWebView webView) {
    //    super.initialize(cordova, webView);
    //    Log.i(TAG, "Streambow SDK initialized");
    //}

    private void initializer(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

    private void performTest(String message, CallbackContext callbackContext) {
        xperience.startTest(this);
    }

    @Override
    public void progressUpdate(TestProgress testProgress, TestStatus testStatus) {

    }
}