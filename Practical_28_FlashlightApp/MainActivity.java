package com.example.flashlight;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private Button toggleFlashlightButton;
    private CameraManager cameraManager;
    private String cameraId;
    private boolean isFlashlightOn = false;
    private static final int PERMISSION_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toggleFlashlightButton = findViewById(R.id.toggleFlashlightButton);
        cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        try {
            cameraId = cameraManager.getCameraIdList()[0];
        } catch (Exception e) {
            Toast.makeText(this, "Flashlight not available", Toast.LENGTH_SHORT).show();
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                    PERMISSION_REQUEST_CODE);
        }

        toggleFlashlightButton.setOnClickListener(v -> {
            toggleFlashlight();
        });
    }

    private void toggleFlashlight() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cameraManager.setTorchMode(cameraId, !isFlashlightOn);
                isFlashlightOn = !isFlashlightOn;
                toggleFlashlightButton.setText(isFlashlightOn ? "Turn Off" : "Turn On");
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error toggling flashlight", Toast.LENGTH_SHORT).show();
        }
    }
}
