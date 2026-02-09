package com.example.intents;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText websiteEditText;
    private Button openWebsiteButton;
    private Button openActivityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        websiteEditText = findViewById(R.id.websiteEditText);
        openWebsiteButton = findViewById(R.id.openWebsiteButton);
        openActivityButton = findViewById(R.id.openActivityButton);

        // Implicit Intent - Open website
        openWebsiteButton.setOnClickListener(v -> {
            String website = websiteEditText.getText().toString().trim();
            if (!website.isEmpty()) {
                if (!website.startsWith("http://") && !website.startsWith("https://")) {
                    website = "https://" + website;
                }
                Intent implicitIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(website));
                startActivity(implicitIntent);
            }
        });

        // Explicit Intent - Open SecondActivity
        openActivityButton.setOnClickListener(v -> {
            Intent explicitIntent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(explicitIntent);
        });
    }
}
