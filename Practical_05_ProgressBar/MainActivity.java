package com.example.progressbar;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView progressTextView;
    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        progressBar = findViewById(R.id.progressBar);
        progressTextView = findViewById(R.id.progressTextView);
        startButton = findViewById(R.id.startButton);

        // Set button click listener
        startButton.setOnClickListener(v -> {
            startProgressBar();
        });
    }

    private void startProgressBar() {
        progressBar.setProgress(0);
        startButton.setEnabled(false);

        Handler handler = new Handler(Looper.getMainLooper());

        new Thread(() -> {
            for (int progress = 0; progress <= 100; progress += 10) {
                final int currentProgress = progress;
                handler.post(() -> {
                    progressBar.setProgress(currentProgress);
                    progressTextView.setText(currentProgress + "%");
                });

                try {
                    Thread.sleep(500); // 500 milliseconds delay
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            handler.post(() -> {
                startButton.setEnabled(true);
                progressTextView.setText("Complete!");
            });
        }).start();
    }
}
