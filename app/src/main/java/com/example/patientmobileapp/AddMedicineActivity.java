package com.example.patientmobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class AddMedicineActivity extends AppCompatActivity {

    private Button btnTimePicker, btnAddMedicine, btnBack;

    EditText freq, name_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine);

        btnTimePicker = findViewById(R.id.select_time);
        btnAddMedicine = findViewById(R.id.add_medicine);
        btnBack = findViewById(R.id.back_button);

        name_input = findViewById(R.id.med_name);
        freq = findViewById(R.id.med_freq);

    btnAddMedicine.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Database myDB = new Database(AddMedicineActivity.this);
            myDB.addMedicine(name_input.getText().toString().trim(),
                    freq.getText().toString().trim(),
                    btnTimePicker.getText().toString().trim());
            Intent intent = new Intent(AddMedicineActivity.this, MainActivity.class);
            intent.putExtra("previousDoctor", AddMedicineActivity.class.getSimpleName());
            startActivity(intent);
            finish();
        }
    });
        btnBack.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(AddMedicineActivity.this, MainActivity.class);
            intent.putExtra("previousDoctor", AddMedicineActivity.class.getSimpleName());
            startActivity(intent);
            finish();
        }
    });
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