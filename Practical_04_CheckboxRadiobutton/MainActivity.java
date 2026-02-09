package com.example.checkboxradiobutton;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private CheckBox checkBoxJava;
    private CheckBox checkBoxPython;
    private CheckBox checkBoxCpp;
    private RadioGroup genderRadioGroup;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        checkBoxJava = findViewById(R.id.checkBoxJava);
        checkBoxPython = findViewById(R.id.checkBoxPython);
        checkBoxCpp = findViewById(R.id.checkBoxCpp);
        genderRadioGroup = findViewById(R.id.genderRadioGroup);
        resultTextView = findViewById(R.id.resultTextView);

        // Update result when checkboxes are checked/unchecked
        checkBoxJava.setOnCheckedChangeListener((buttonView, isChecked) -> updateResult());
        checkBoxPython.setOnCheckedChangeListener((buttonView, isChecked) -> updateResult());
        checkBoxCpp.setOnCheckedChangeListener((buttonView, isChecked) -> updateResult());

        // Update result when radio button is selected
        genderRadioGroup.setOnCheckedChangeListener((group, checkedId) -> updateResult());
    }

    private void updateResult() {
        StringBuilder selectedLanguages = new StringBuilder();
        
        if (checkBoxJava.isChecked()) {
            selectedLanguages.append("Java ");
        }
        if (checkBoxPython.isChecked()) {
            selectedLanguages.append("Python ");
        }
        if (checkBoxCpp.isChecked()) {
            selectedLanguages.append("C++ ");
        }

        int selectedGenderId = genderRadioGroup.getCheckedRadioButtonId();
        RadioButton selectedGender = findViewById(selectedGenderId);
        String genderText = "";
        if (selectedGender != null) {
            genderText = "\nGender: " + selectedGender.getText().toString();
        }

        resultTextView.setText("Languages: " + selectedLanguages.toString() + genderText);
    }
}
