package com.example.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "MyBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (action != null) {
            Log.d(TAG, "Received broadcast: " + action);

            if (action.equals(Intent.ACTION_AIRPLANE_MODE_CHANGED)) {
                boolean isAirplaneMode = intent.getBooleanExtra("state", false);
                Log.d(TAG, "Airplane Mode: " + (isAirplaneMode ? "ON" : "OFF"));
            }
        }
    }
}
