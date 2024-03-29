package com.example.patientmobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText doctor_name;
    Button date_up, time_up, update_button, back_button;
    String id, name, date, time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        doctor_name = findViewById(R.id.doctor_name_up);
        date_up = findViewById(R.id.select_date_up);
        time_up = findViewById(R.id.select_time_up);
        update_button = findViewById(R.id.update_appointment);
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        back_button = findViewById(R.id.back_button_up);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                intent.putExtra("previousDoctor", UpdateActivity.class.getSimpleName());
                startActivity(intent);
                finish();
            }
        });

        getAndSetIntentData();
    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("name") && getIntent().hasExtra("date") && getIntent().hasExtra("time")) {
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            date = getIntent().getStringExtra("date");
            time = getIntent().getStringExtra("time");

            doctor_name.setText(name);
            date_up.setText(date);
            time_up.setText(time);
        }else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }
}