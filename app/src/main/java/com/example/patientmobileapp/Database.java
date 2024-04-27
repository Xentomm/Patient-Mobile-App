package com.example.patientmobileapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "PatientApp.db";
    private static final Integer DATABASE_VERSION = 1;

    private static final String APPOINTMENTS_TABLE_NAME = "appointments";
    private static final String MEDICINE_TABLE_NAME = "medicine";
    private static final String COLUMN_ID = "_id";
    private static final String DOCTOR_COLUMN_NAME = "doctor_name";
    private static final String APPOINTMENT_DATE = "appointment_date";
    private static final String APPOINTMENT_TIME = "appointment_time";

    private static final String MEDICINE_COLUMN_NAME = "medicine_name";
    private static final String MEDICINE_FREQ = "medicine_freq";
    private static final String MEDICINE_TIME = "medicine_time";

    Database(@Nullable Context context) {
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

        String query2 = "CREATE TABLE " + MEDICINE_TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                MEDICINE_COLUMN_NAME + " TEXT, " +
                MEDICINE_FREQ + " TEXT, " +
                MEDICINE_TIME + " TIME);";

        db.execSQL(query);
        db.execSQL(query2);
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

    void addMedicine(String name, String freq, String time){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(MEDICINE_COLUMN_NAME, name);
        cv.put(MEDICINE_FREQ, freq);
        cv.put(MEDICINE_TIME, time);
        long result = db.insert(MEDICINE_TABLE_NAME, null, cv);
        if(result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllAppData() {
        String query = "SELECT * FROM " + APPOINTMENTS_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    Cursor readAllMedData() {
        String query = "SELECT * FROM " + MEDICINE_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String row_id, String name, String date, String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DOCTOR_COLUMN_NAME, name);
        cv.put(APPOINTMENT_DATE, date);
        cv.put(APPOINTMENT_TIME, time);

        long result = db.update(APPOINTMENTS_TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Failed to update", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteOneRow(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(APPOINTMENTS_TABLE_NAME, "_id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
        }
    }
}
