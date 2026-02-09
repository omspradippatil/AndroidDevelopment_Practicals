package com.example.smsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText phoneNumberEditText;
    private EditText messageEditText;
    private Button sendSmsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phoneNumberEditText = findViewById(R.id.phoneNumberEditText);
        messageEditText = findViewById(R.id.messageEditText);
        sendSmsButton = findViewById(R.id.sendSmsButton);

        sendSmsButton.setOnClickListener(v -> {
            sendSms();
        });
    }

    private void sendSms() {
        String phoneNumber = phoneNumberEditText.getText().toString().trim();
        String message = messageEditText.getText().toString().trim();

        if (phoneNumber.isEmpty() || message.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:" + phoneNumber));
        intent.putExtra("sms_body", message);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "SMS app not available", Toast.LENGTH_SHORT).show();
        }
    }
}
