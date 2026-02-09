package com.example.intents;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView messageTextView = findViewById(R.id.messageTextView);
        messageTextView.setText("This is a Second Activity opened using Explicit Intent!");
    }
}
