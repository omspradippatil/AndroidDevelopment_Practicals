package com.example.textedittext;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText inputEditText;
    private TextView displayTextView;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        inputEditText = findViewById(R.id.inputEditText);
        displayTextView = findViewById(R.id.displayTextView);
        submitButton = findViewById(R.id.submitButton);

        // Set button click listener
        submitButton.setOnClickListener(v -> {
            String userInput = inputEditText.getText().toString();
            if (!userInput.isEmpty()) {
                displayTextView.setText("You entered: " + userInput);
            } else {
                displayTextView.setText("Please enter some text");
            }
        });
    }
}
