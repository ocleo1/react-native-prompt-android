package com.ocleo1.dialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.ocleo1.R;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.NoSuchKeyException;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;

public class PromptModule extends ReactContextBaseJavaModule {

    public static final String REACT_CLASS = "PromptAndroid";

    public PromptModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @ReactMethod
    public void show(
        String title, String message, ReadableMap buttons,
        final Callback errorCallback, final Callback successCallback) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getCurrentActivity());

        LayoutInflater inflater = LayoutInflater.from(getReactApplicationContext());
        View promptView = inflater.inflate(R.layout.dialog_prompt, null);
        final EditText editor = (EditText) promptView.findViewById(R.id.inputContent);

        editor.setTextColor(Color.BLACK);

        alertDialogBuilder.setView(promptView).setTitle(title).setMessage(message);

        try {
            String positiveButton = Integer.toString(DialogInterface.BUTTON_POSITIVE);
            String positiveButtonText = buttons.getMap(positiveButton).getString("text");
            alertDialogBuilder.setPositiveButton(positiveButtonText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    successCallback.invoke(which, editor.getText().toString());
                }
            });
        } catch (NoSuchKeyException e) {
            Log.e("Positive Button", "No positive button is available");
        }

        try {
            String negativeButton = Integer.toString(DialogInterface.BUTTON_NEGATIVE);
            String negativeButtonText = buttons.getMap(negativeButton).getString("text");
            alertDialogBuilder.setNegativeButton(negativeButtonText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    successCallback.invoke(which, editor.getText().toString());
                }
            });
        } catch (NoSuchKeyException e) {
            Log.v("Negative Button", "No negative button is available");
        }

        try {
            String neutralButton = Integer.toString(DialogInterface.BUTTON_NEUTRAL);
            String neutralButtonText = buttons.getMap(neutralButton).getString("text");
            alertDialogBuilder.setNeutralButton(neutralButtonText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    successCallback.invoke(which, editor.getText().toString());
                }
            });
        } catch (NoSuchKeyException e) {
            Log.v("Neutral Button", "No neutral button is available");
        }

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
