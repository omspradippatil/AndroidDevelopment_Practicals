package com.example.librarymanagement;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private String[] bookNames = {
            "Java Programming",
            "Android Development",
            "Python Basics",
            "Web Development",
            "Database Design",
            "DevOps Guide",
            "Cloud Computing",
            "Machine Learning",
            "Data Science",
            "Blockchain",
            "AI & Neural Networks",
            "Cybersecurity"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup GridView
        GridView gridView = findViewById(R.id.gridView);
        ArrayAdapter<String> gridAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bookNames);
        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener((parent, view, position, id) -> {
            Toast.makeText(MainActivity.this, "Selected: " + bookNames[position], Toast.LENGTH_SHORT).show();
        });
    }
}
