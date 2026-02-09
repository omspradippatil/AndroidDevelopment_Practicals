package com.example.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;


public class MyService extends Service {

    private static final String TAG = "MyService";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "Service Started");
        
        // Simulate background task
        new Thread(() -> {
            try {
                Thread.sleep(5000); // 5 seconds
                Log.d(TAG, "Background task completed");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Service Destroyed");
    }
}
