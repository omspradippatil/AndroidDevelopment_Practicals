package com.example.countdowntimer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText timeEditText;
    private TextView timerTextView;
    private Button startButton;
    private Button pauseButton;
    private CountDownTimer countDownTimer;
    private boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        timeEditText = findViewById(R.id.timeEditText);
        timerTextView = findViewById(R.id.timerTextView);
        startButton = findViewById(R.id.startButton);
        pauseButton = findViewById(R.id.pauseButton);

        // Start button listener
        startButton.setOnClickListener(v -> {
            startTimer();
        });

        // Pause button listener
        pauseButton.setOnClickListener(v -> {
            pauseTimer();
        });
    }

    private void startTimer() {
        if (isRunning) return;

        String timeStr = timeEditText.getText().toString().trim();
        if (timeStr.isEmpty()) {
            timerTextView.setText("Please enter time in seconds");
            return;
        }

        long timeInMillis = Long.parseLong(timeStr) * 1000;
        isRunning = true;
        timeEditText.setEnabled(false);
        startButton.setEnabled(false);
        pauseButton.setEnabled(true);

        countDownTimer = new CountDownTimer(timeInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long seconds = millisUntilFinished / 1000;
                timerTextView.setText(seconds + " seconds");
            }

            @Override
            public void onFinish() {
                timerTextView.setText("Time's Up!");
                isRunning = false;
                timeEditText.setEnabled(true);
                startButton.setEnabled(true);
                pauseButton.setEnabled(false);
            }
        }.start();
    }

    private void pauseTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            isRunning = false;
            timeEditText.setEnabled(true);
            startButton.setEnabled(true);
            pauseButton.setEnabled(false);
        }
    }
}
