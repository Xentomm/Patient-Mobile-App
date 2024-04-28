package com.example.patientmobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class UpdateMedActivity extends AppCompatActivity {

    EditText medicine_name, medicine_freq;
    Button time_up, update_button, back_button, delete_button;
    String id, name, freq, time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_med);

        medicine_name = findViewById(R.id.medicine_name_up);
        medicine_freq = findViewById(R.id.medicine_freq_up);
        time_up = findViewById(R.id.select_time_up);
        update_button = findViewById(R.id.update_medicine);
        delete_button = findViewById(R.id.delete_medicine);

        getAndSetIntentData();

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database myDB = new Database(UpdateMedActivity.this);
//                Log.d("Data", "Data onClick: " + id + doctor_name.getText().toString() + date_up.getText().toString() + time_up.getText().toString());
                myDB.updateMedData(id, medicine_name.getText().toString(), medicine_freq.getText().toString(), time_up.getText().toString());
                Intent intent = new Intent(UpdateMedActivity.this, MainActivity.class);
                intent.putExtra("previousDoctor", UpdateMedActivity.class.getSimpleName());
                startActivity(intent);
                finish();
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });

        back_button = findViewById(R.id.back_button_up);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateMedActivity.this, MainActivity.class);
                intent.putExtra("previousDoctor", UpdateMedActivity.class.getSimpleName());
                startActivity(intent);
                finish();
            }
        });
    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("name") && getIntent().hasExtra("freq") && getIntent().hasExtra("time")) {
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            freq = getIntent().getStringExtra("freq");
            time = getIntent().getStringExtra("time");
            Log.d("Data", "Data in get and set: " + id + name + freq + time);

            medicine_name.setText(name);
            medicine_freq.setText(freq);
            time_up.setText(time);
        }else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + "?");
        builder.setMessage("Are you sure?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Database myDB = new Database(UpdateMedActivity.this);
                myDB.deleteOneMedRow(id);
                Intent intent = new Intent(UpdateMedActivity.this, MainActivity.class);
                intent.putExtra("previousDoctor", UpdateMedActivity.class.getSimpleName());
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }

    public void showTimePickerDialog(View v) {
        final Calendar calendar = Calendar.getInstance();
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,  new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String selectedTime = hourOfDay + ":" + minute;
                time_up.setText(selectedTime);
            }
        }, hourOfDay, minute, true);

        timePickerDialog.show();
    }
}