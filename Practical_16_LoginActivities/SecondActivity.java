package com.example.loginactivities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private TextView welcomeTextView;
    private Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Initialize views
        welcomeTextView = findViewById(R.id.welcomeTextView);
        logoutButton = findViewById(R.id.logoutButton);

        // Get data from intent
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        welcomeTextView.setText("Welcome, " + username + "!");

        // Set logout button click listener
        logoutButton.setOnClickListener(v -> {
            Intent logoutIntent = new Intent(SecondActivity.this, MainActivity.class);
            logoutIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(logoutIntent);
            finish();
        });
    }
}
