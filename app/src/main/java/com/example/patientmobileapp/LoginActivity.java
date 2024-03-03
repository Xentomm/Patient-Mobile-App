package com.example.patientmobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private static final String CORRECT_PIN = "1234"; // Change this to your desired PIN
    private EditText pinEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pinEditText = findViewById(R.id.pinEditText);
        Button btnEnter = findViewById(R.id.btnEnter);
        Button btnClear = findViewById(R.id.btnClear);

        View.OnClickListener keypadClickListener = v -> {
            Button btn = (Button) v;
            String digit = btn.getText().toString();
            if (digit.equals("Clear")) {
                pinEditText.getText().clear();
            } else if (digit.equals("Enter")) {
                checkPIN();
            } else {
                // Append the digit only if the pin length is less than 4
                if (pinEditText.length() < 4) {
                    pinEditText.append(digit);
                }
            }
        };

        findViewById(R.id.btn0).setOnClickListener(keypadClickListener);
        findViewById(R.id.btn1).setOnClickListener(keypadClickListener);
        findViewById(R.id.btn2).setOnClickListener(keypadClickListener);
        findViewById(R.id.btn3).setOnClickListener(keypadClickListener);
        findViewById(R.id.btn4).setOnClickListener(keypadClickListener);
        findViewById(R.id.btn5).setOnClickListener(keypadClickListener);
        findViewById(R.id.btn6).setOnClickListener(keypadClickListener);
        findViewById(R.id.btn7).setOnClickListener(keypadClickListener);
        findViewById(R.id.btn8).setOnClickListener(keypadClickListener);
        findViewById(R.id.btn9).setOnClickListener(keypadClickListener);
        btnClear.setOnClickListener(keypadClickListener);
        btnEnter.setOnClickListener(keypadClickListener);
    }

    private void checkPIN() {
        String enteredPin = pinEditText.getText().toString();
        if (enteredPin.length() == 4 && enteredPin.equals(CORRECT_PIN)) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            System.out.println("Not correct");
        }
    }
}