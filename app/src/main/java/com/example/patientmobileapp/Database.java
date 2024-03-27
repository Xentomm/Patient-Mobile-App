package com.example.patientmobileapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "PatientApp.db";
    private static final Integer DATABASE_VERSION = 1;

    private static final String APPOINTMENTS_TABLE_NAME = "appointments";
    private static final String COLUMN_ID = "_id";
    private static final String DOCTOR_COLUMN_NAME = "doctor_name";
    private static final String APPOINTMENT_DATE = "appointment_date";
    private static final String APPOINTMENT_TIME = "appointment_time";

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + APPOINTMENTS_TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DOCTOR_COLUMN_NAME + " TEXT, " +
                APPOINTMENT_DATE + " DATE, " +
                APPOINTMENT_TIME + " TIME);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + APPOINTMENTS_TABLE_NAME);
        onCreate(db);
    }

    void addAppointment(String name, String date, String time){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(DOCTOR_COLUMN_NAME, name);
        cv.put(APPOINTMENT_DATE, date);
        cv.put(APPOINTMENT_TIME, time);
        long result = db.insert(APPOINTMENTS_TABLE_NAME, null, cv);
        if(result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }
    }
}
