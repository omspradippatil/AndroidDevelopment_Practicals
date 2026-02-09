package com.example.calculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView displayTextView;
    private StringBuilder expression;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayTextView = findViewById(R.id.displayTextView);
        expression = new StringBuilder();

        // Number buttons
        int[] numberButtonIds = {
                R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3,
                R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7,
                R.id.btn8, R.id.btn9
        };

        for (int buttonId : numberButtonIds) {
            Button button = findViewById(buttonId);
            button.setOnClickListener(v -> appendNumber(button.getText().toString()));
        }

        // Operation buttons
        findViewById(R.id.btnAdd).setOnClickListener(v -> appendOperator("+"));
        findViewById(R.id.btnSubtract).setOnClickListener(v -> appendOperator("-"));
        findViewById(R.id.btnMultiply).setOnClickListener(v -> appendOperator("*"));
        findViewById(R.id.btnDivide).setOnClickListener(v -> appendOperator("/"));
        findViewById(R.id.btnDecimal).setOnClickListener(v -> appendOperator("."));

        // Equal button
        findViewById(R.id.btnEqual).setOnClickListener(v -> calculateResult());

        // Clear button
        findViewById(R.id.btnClear).setOnClickListener(v -> {
            expression.setLength(0);
            displayTextView.setText("0");
        });
    }

    private void appendNumber(String number) {
        expression.append(number);
        displayTextView.setText(expression.toString());
    }

    private void appendOperator(String operator) {
        expression.append(operator);
        displayTextView.setText(expression.toString());
    }

    private void calculateResult() {
        try {
            double result = eval(expression.toString());
            displayTextView.setText(String.valueOf(result));
            expression.setLength(0);
            expression.append(result);
        } catch (Exception e) {
            displayTextView.setText("Error");
            expression.setLength(0);
        }
    }

    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char) ch);
                return x;
            }

            double parseExpression() {
                double x = parseTerm();
                while (true) {
                    if (eat('+')) x += parseTerm();
                    else if (eat('-')) x -= parseTerm();
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                while (true) {
                    if (eat('*')) x *= parseFactor();
                    else if (eat('/')) x /= parseFactor();
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor();
                if (eat('-')) return -parseFactor();
                double x;
                int startPos = this.pos;
                if (ch == '(') {
                    eat('(');
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') {
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }
                return x;
            }
        }.parse();
    }
}
