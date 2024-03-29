package com.example.patientmobileapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.TimePickerDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class AddAppointmentActivity extends AppCompatActivity {

    private Button btnShowDatePicker, btnTimePicker, btnAddAppointment, btnBack;

    EditText name_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        btnShowDatePicker = findViewById(R.id.select_date);
        btnTimePicker = findViewById(R.id.select_time);
        btnAddAppointment = findViewById(R.id.add_appointment);
        btnBack = findViewById(R.id.back_button);

        name_input = findViewById(R.id.doctor_name);

        btnAddAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database myDB = new Database(AddAppointmentActivity.this);
                myDB.addAppointment(name_input.getText().toString().trim(),
                        btnShowDatePicker.getText().toString().trim(),
                        btnTimePicker.getText().toString().trim());
                Intent intent = new Intent(AddAppointmentActivity.this, MainActivity.class);
                intent.putExtra("previousDoctor", AddAppointmentActivity.class.getSimpleName());
                startActivity(intent);
                finish();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddAppointmentActivity.this, MainActivity.class);
                intent.putExtra("previousDoctor", AddAppointmentActivity.class.getSimpleName());
                startActivity(intent);
                finish();
            }
        });
    }
    public void showDatePickerDialog(View v) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                        btnShowDatePicker.setText(selectedDate);
                    }
                }, year, month, dayOfMonth);

        datePickerDialog.show();
    }

    public void showTimePickerDialog(View v) {
        final Calendar calendar = Calendar.getInstance();
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,  new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String selectedTime = hourOfDay + ":" + minute;
                        btnTimePicker.setText(selectedTime);
                    }
                }, hourOfDay, minute, true);

        timePickerDialog.show();
    }

}