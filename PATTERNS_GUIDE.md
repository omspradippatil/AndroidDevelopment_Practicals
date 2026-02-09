# 🔧 Android Development - Common Patterns & Best Practices

## Standard Activity Pattern

Every practical follows this basic pattern:

```java
package com.example.projectname;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // 1. Declare UI components
    private EditText inputEditText;
    private Button submitButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // 2. Set layout
        setContentView(R.layout.activity_main);
        
        // 3. Initialize views
        inputEditText = findViewById(R.id.inputEditText);
        submitButton = findViewById(R.id.submitButton);
        resultTextView = findViewById(R.id.resultTextView);
        
        // 4. Set event listeners
        submitButton.setOnClickListener(v -> {
            handleButtonClick();
        });
    }
    
    // 5. Handle events
    private void handleButtonClick() {
        String input = inputEditText.getText().toString();
        resultTextView.setText(input);
    }
}
```

---

## Common Button Click Listeners

### Lambda Expression (Modern)
```java
button.setOnClickListener(v -> {
    Toast.makeText(MainActivity.this, "Clicked!", Toast.LENGTH_SHORT).show();
});
```

### Anonymous Class (Traditional)
```java
button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Toast.makeText(MainActivity.this, "Clicked!", Toast.LENGTH_SHORT).show();
    }
});
```

---

## Getting User Input

### From EditText
```java
String userInput = editText.getText().toString().trim();

if (userInput.isEmpty()) {
    Toast.makeText(this, "Please enter something", Toast.LENGTH_SHORT).show();
    return;
}
```

### From CheckBox
```java
if (checkBox.isChecked()) {
    // Checkbox is selected
}
```

### From RadioButton
```java
int selectedId = radioGroup.getCheckedRadioButtonId();
RadioButton selectedRadio = findViewById(selectedId);
String selectedText = selectedRadio.getText().toString();
```

---

## Displaying Output

### Toast Message
```java
Toast.makeText(this, "Message", Toast.LENGTH_SHORT).show();
Toast.makeText(this, "Message", Toast.LENGTH_LONG).show();
```

### Update TextView
```java
resultTextView.setText("Result: " + value);
```

### String Formatting
```java
String formatted = String.format("%.2f°C = %.2f°F", celsius, fahrenheit);
textView.setText(formatted);
```

---

## Working with Dialogs

### DatePickerDialog
```java
Calendar calendar = Calendar.getInstance();
int year = calendar.get(Calendar.YEAR);
int month = calendar.get(Calendar.MONTH);
int day = calendar.get(Calendar.DAY_OF_MONTH);

DatePickerDialog dialog = new DatePickerDialog(
    this,
    (view, y, m, d) -> {
        String date = d + "/" + (m + 1) + "/" + y;
        textView.setText(date);
    },
    year, month, day
);
dialog.show();
```

### TimePickerDialog
```java
Calendar calendar = Calendar.getInstance();
int hour = calendar.get(Calendar.HOUR_OF_DAY);
int minute = calendar.get(Calendar.MINUTE);

TimePickerDialog dialog = new TimePickerDialog(
    this,
    (view, h, m) -> {
        String time = String.format("%02d:%02d", h, m);
        textView.setText(time);
    },
    hour, minute, true
);
dialog.show();
```

---

## Working with Intents

### Explicit Intent (Open Another Activity)
```java
Intent intent = new Intent(MainActivity.this, SecondActivity.class);
startActivity(intent);
```

### Passing Data via Intent
```java
// Send data
Intent intent = new Intent(MainActivity.this, SecondActivity.class);
intent.putExtra("username", "John");
intent.putExtra("age", 25);
startActivity(intent);

// Receive data
Intent intent = getIntent();
String username = intent.getStringExtra("username");
int age = intent.getIntExtra("age", 0);
```

### Implicit Intent (Open Website)
```java
String url = "https://www.google.com";
Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
startActivity(intent);
```

### Implicit Intent (Send SMS)
```java
Intent intent = new Intent(Intent.ACTION_SENDTO);
intent.setData(Uri.parse("smsto:1234567890"));
intent.putExtra("sms_body", "Your message here");
startActivity(intent);
```

### Implicit Intent (Send Email)
```java
Intent intent = new Intent(Intent.ACTION_SEND);
intent.setType("message/rfc822");
intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"email@example.com"});
intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
intent.putExtra(Intent.EXTRA_TEXT, "Message body");
startActivity(Intent.createChooser(intent, "Send Email"));
```

---

## Working with Services

### Start a Service
```java
Intent serviceIntent = new Intent(this, MyService.class);
startService(serviceIntent);
```

### Stop a Service
```java
Intent serviceIntent = new Intent(this, MyService.class);
stopService(serviceIntent);
```

### Service Implementation
```java
public class MyService extends Service {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Do background work
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
```

---

## Working with Database (SQLite)

### DatabaseHelper Class
```java
public class DatabaseHelper extends SQLiteOpenHelper {
    
    private static final String DATABASE_NAME = "MyDatabase.db";
    private static final int VERSION = 1;
    
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }
    
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE users(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "email TEXT)";
        db.execSQL(createTable);
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }
}
```

### Insert Data
```java
SQLiteDatabase db = dbHelper.getWritableDatabase();
ContentValues values = new ContentValues();
values.put("name", "John");
values.put("email", "john@example.com");
long result = db.insert("users", null, values);

if (result != -1) {
    Toast.makeText(this, "Data inserted", Toast.LENGTH_SHORT).show();
}
```

### Read Data
```java
SQLiteDatabase db = dbHelper.getReadableDatabase();
Cursor cursor = db.query("users", null, null, null, null, null, null);

if (cursor.moveToFirst()) {
    do {
        String name = cursor.getString(cursor.getColumnIndex("name"));
        String email = cursor.getString(cursor.getColumnIndex("email"));
    } while (cursor.moveToNext());
}
cursor.close();
```

### Query with Condition
```java
SQLiteDatabase db = dbHelper.getReadableDatabase();
Cursor cursor = db.query("users", 
    null, 
    "name=?", 
    new String[]{"John"}, 
    null, null, null);

if (cursor.getCount() > 0) {
    // User found
}
cursor.close();
```

---

## Working with Permissions (Android 6.0+)

### Check Permission
```java
if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) 
        != PackageManager.PERMISSION_GRANTED) {
    // Permission not granted
}
```

### Request Permission
```java
ActivityCompat.requestPermissions(this, 
    new String[]{Manifest.permission.CAMERA}, 
    REQUEST_CODE);
```

### Handle Permission Result
```java
@Override
public void onRequestPermissionsResult(int requestCode, 
        String[] permissions, int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    
    if (requestCode == REQUEST_CODE && grantResults.length > 0 
            && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
        // Permission granted
    }
}
```

---

## Threading & Handlers

### Using Handler for Delayed Execution
```java
Handler handler = new Handler(Looper.getMainLooper());
handler.postDelayed(() -> {
    // This runs after 2 seconds
    textView.setText("Done!");
}, 2000); // 2000 milliseconds
```

### Using Thread for Background Task
```java
new Thread(() -> {
    try {
        Thread.sleep(1000); // Simulate work
        handler.post(() -> {
            // Update UI on main thread
            textView.setText("Task completed");
        });
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}).start();
```

### Using CountDownTimer
```java
CountDownTimer timer = new CountDownTimer(10000, 1000) {
    @Override
    public void onTick(long millisUntilFinished) {
        textView.setText(millisUntilFinished / 1000 + " seconds");
    }

    @Override
    public void onFinish() {
        textView.setText("Time's up!");
    }
}.start();
```

---

## Common XML Layout Patterns

### EditText with Label
```xml
<LinearLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical">
    
    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Label"
        android:textSize="16sp" />
    
    <EditText
        android:id="@+id/myEditText"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Hint text"
        android:inputType="text" />
        
</LinearLayout>
```

### Centered Button Layout
```xml
<LinearLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:gravity="center">
    
    <Button
        android:id="@+id/myButton"
        android:layout_width="200dp"
        android:layout_height="wrap_content"
        android:text="Click Me" />
        
</LinearLayout>
```

### Two Buttons Side by Side
```xml
<LinearLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="horizontal">
    
    <Button
        android:id="@+id/button1"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_weight="1"
        android:text="Button 1"
        android:layout_marginRight="8dp" />
    
    <Button
        android:id="@+id/button2"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_weight="1"
        android:text="Button 2"
        android:layout_marginLeft="8dp" />
        
</LinearLayout>
```

---

## Common Mistakes to Avoid

### ❌ WRONG
```java
// Forgetting toString()
String text = editText.getText(); // Returns Editable, not String

// Not checking empty
String input = editText.getText().toString();
int value = Integer.parseInt(input); // Crashes if empty
```

### ✅ CORRECT
```java
// Always use toString()
String text = editText.getText().toString();

// Always validate
String input = editText.getText().toString().trim();
if (input.isEmpty()) {
    Toast.makeText(this, "Please enter value", Toast.LENGTH_SHORT).show();
    return;
}
int value = Integer.parseInt(input);
```

---

## Resource Files

### Strings (strings.xml)
```xml
<resources>
    <string name="app_name">My App</string>
    <string name="hello">Hello World</string>
</resources>
```

### Dimensions (dimens.xml)
```xml
<resources>
    <dimen name="text_large">24sp</dimen>
    <dimen name="padding_normal">16dp</dimen>
</resources>
```

### Colors (colors.xml)
```xml
<resources>
    <color name="primary">#1976D2</color>
    <color name="accent">#FF5722</color>
</resources>
```

---

**Master these patterns and you'll be able to build any Android app!** 🚀
