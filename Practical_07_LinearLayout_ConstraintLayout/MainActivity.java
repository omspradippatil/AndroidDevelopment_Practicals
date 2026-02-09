package com.example.registrationform;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;
    private EditText phoneEditText;
    private RadioGroup genderRadioGroup;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        firstNameEditText = findViewById(R.id.firstNameEditText);
        lastNameEditText = findViewById(R.id.lastNameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        genderRadioGroup = findViewById(R.id.genderRadioGroup);
        submitButton = findViewById(R.id.submitButton);

        // Set submit button click listener
        submitButton.setOnClickListener(v -> {
            submitForm();
        });
    }

    private void submitForm() {
        String firstName = firstNameEditText.getText().toString().trim();
        String lastName = lastNameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String phone = phoneEditText.getText().toString().trim();

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        int selectedGenderId = genderRadioGroup.getCheckedRadioButtonId();
        if (selectedGenderId == -1) {
            Toast.makeText(this, "Please select gender", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton selectedGender = findViewById(selectedGenderId);
        String genderText = selectedGender.getText().toString();

        String message = "Registration Successful!\n" +
                "Name: " + firstName + " " + lastName + "\n" +
                "Email: " + email + "\n" +
                "Phone: " + phone + "\n" +
                "Gender: " + genderText;

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
