package com.example.temperatureconvertor;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText temperatureEditText;
    private RadioGroup conversionRadioGroup;
    private TextView resultTextView;
    private Button convertButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        temperatureEditText = findViewById(R.id.temperatureEditText);
        conversionRadioGroup = findViewById(R.id.conversionRadioGroup);
        resultTextView = findViewById(R.id.resultTextView);
        convertButton = findViewById(R.id.convertButton);

        // Set button click listener
        convertButton.setOnClickListener(v -> {
            convertTemperature();
        });
    }

    private void convertTemperature() {
        String tempStr = temperatureEditText.getText().toString().trim();

        if (tempStr.isEmpty()) {
            resultTextView.setText("Please enter a temperature value");
            return;
        }

        double temperature = Double.parseDouble(tempStr);
        int selectedRadioButtonId = conversionRadioGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
        String conversionType = selectedRadioButton.getText().toString();

        double result;
        String resultMessage;

        if (conversionType.equals("Celsius to Fahrenheit")) {
            result = (temperature * 9/5) + 32;
            resultMessage = String.format("%.2f°C = %.2f°F", temperature, result);
        } else if (conversionType.equals("Fahrenheit to Celsius")) {
            result = (temperature - 32) * 5/9;
            resultMessage = String.format("%.2f°F = %.2f°C", temperature, result);
        } else {
            result = temperature + 273.15;
            resultMessage = String.format("%.2f°C = %.2f K", temperature, result);
        }

        resultTextView.setText(resultMessage);
    }
}
