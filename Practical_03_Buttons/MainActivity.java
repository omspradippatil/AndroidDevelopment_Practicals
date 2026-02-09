package com.example.buttons;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView resultTextView;
    private Button normalButton;
    private ImageButton imageButton;
    private ToggleButton toggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        resultTextView = findViewById(R.id.resultTextView);
        normalButton = findViewById(R.id.normalButton);
        imageButton = findViewById(R.id.imageButton);
        toggleButton = findViewById(R.id.toggleButton);

        // Normal Button listener
        normalButton.setOnClickListener(v -> {
            resultTextView.setText("Normal Button Clicked!");
        });

        // Image Button listener
        imageButton.setOnClickListener(v -> {
            resultTextView.setText("Image Button Clicked!");
        });

        // Toggle Button listener
        toggleButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                resultTextView.setText("Toggle Button is ON");
            } else {
                resultTextView.setText("Toggle Button is OFF");
            }
        });
    }
}
