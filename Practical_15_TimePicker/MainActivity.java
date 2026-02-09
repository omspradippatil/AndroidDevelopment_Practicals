package com.example.timepicker;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TextView selectedTimeTextView;
    private Button pickTimeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        selectedTimeTextView = findViewById(R.id.selectedTimeTextView);
        pickTimeButton = findViewById(R.id.pickTimeButton);

        // Set button click listener
        pickTimeButton.setOnClickListener(v -> {
            showTimePickerDialog();
        });
    }

    private void showTimePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(
                MainActivity.this,
                (view, selectedHour, selectedMinute) -> {
                    String selectedTime = String.format("%02d:%02d", selectedHour, selectedMinute);
                    selectedTimeTextView.setText("Selected Time: " + selectedTime);
                },
                hour, minute, true
        );
        timePickerDialog.show();
    }
}
