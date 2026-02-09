package com.example.emailapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText recipientEditText;
    private EditText subjectEditText;
    private EditText bodyEditText;
    private Button sendEmailButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recipientEditText = findViewById(R.id.recipientEditText);
        subjectEditText = findViewById(R.id.subjectEditText);
        bodyEditText = findViewById(R.id.bodyEditText);
        sendEmailButton = findViewById(R.id.sendEmailButton);

        sendEmailButton.setOnClickListener(v -> {
            sendEmail();
        });
    }

    private void sendEmail() {
        String recipient = recipientEditText.getText().toString().trim();
        String subject = subjectEditText.getText().toString().trim();
        String body = bodyEditText.getText().toString().trim();

        if (recipient.isEmpty() || subject.isEmpty() || body.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{recipient});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, body);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send Email"));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "Email client not available", Toast.LENGTH_SHORT).show();
        }
    }
}
