package com.example.mycontacts;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText phoneEditText;
    private EditText emailEditText;
    private Button addButton;
    private ListView contactsListView;
    private DatabaseHelper dbHelper;
    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);

        nameEditText = findViewById(R.id.nameEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        emailEditText = findViewById(R.id.emailEditText);
        addButton = findViewById(R.id.addButton);
        contactsListView = findViewById(R.id.contactsListView);

        addButton.setOnClickListener(v -> addContact());
        loadContacts();
    }

    private void addContact() {
        String name = nameEditText.getText().toString().trim();
        String phone = phoneEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();

        if (name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("phone", phone);
        values.put("email", email);

        long result = db.insert("contacts", null, values);
        if (result != -1) {
            Toast.makeText(this, "Contact Added", Toast.LENGTH_SHORT).show();
            clearFields();
            loadContacts();
        } else {
            Toast.makeText(this, "Failed to add contact", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadContacts() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("contacts", null, null, null, null, null, null);

        if (adapter == null) {
            adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor,
                    new String[]{"name"}, new int[]{android.R.id.text1});
            contactsListView.setAdapter(adapter);
        } else {
            adapter.changeCursor(cursor);
        }
    }

    private void clearFields() {
        nameEditText.setText("");
        phoneEditText.setText("");
        emailEditText.setText("");
    }
}
