package com.example.patientmobileapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "PatientApp.db";
    private static final Integer DATABASE_VERSION = 1;

    private static final String DOCTOR_TABLE_NAME = "doctors";
    private static final String COLUMN_ID = "_id";
    private static final String DOCTOR_COLUMN_TITLE = "doctor_profession";
    private static final String DOCTOR_COLUMN_NAME = "doctor_name";

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + DOCTOR_TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        DOCTOR_COLUMN_TITLE + " TEXT, " +
                        DOCTOR_COLUMN_NAME + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DOCTOR_TABLE_NAME);
    }
}
