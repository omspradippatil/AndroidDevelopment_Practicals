package com.example.services;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button startServiceButton;
    private Button stopServiceButton;
    private TextView statusTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        startServiceButton = findViewById(R.id.startServiceButton);
        stopServiceButton = findViewById(R.id.stopServiceButton);
        statusTextView = findViewById(R.id.statusTextView);

        // Start service button
        startServiceButton.setOnClickListener(v -> {
            Intent serviceIntent = new Intent(MainActivity.this, MyService.class);
            startService(serviceIntent);
            statusTextView.setText("Service Started");
        });

        // Stop service button
        stopServiceButton.setOnClickListener(v -> {
            Intent serviceIntent = new Intent(MainActivity.this, MyService.class);
            stopService(serviceIntent);
            statusTextView.setText("Service Stopped");
        });
    }
}
