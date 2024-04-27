package com.example.patientmobileapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    Database myDB;
    ArrayList<String> appointment_id, doctor_name, appointment_date, appointment_time;
    ArrayList<String> medicine_id, medicine_name, medicine_freq, medicine_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tableswipe);
        viewPager = findViewById(R.id.viewpager);

        tabLayout.setupWithViewPager(viewPager);

        myDB = new Database(MainActivity.this);
        appointment_id = new ArrayList<>();
        doctor_name = new ArrayList<>();
        appointment_date = new ArrayList<>();
        appointment_time = new ArrayList<>();
        medicine_id = new ArrayList<>();
        medicine_name = new ArrayList<>();
        medicine_freq = new ArrayList<>();
        medicine_time = new ArrayList<>();

        storeDataInAppArray();

        Bundle bundle = new Bundle();
        bundle.putStringArrayList("appointment_id", appointment_id);
        bundle.putStringArrayList("doctor_name", doctor_name);
        bundle.putStringArrayList("appointment_date", appointment_date);
        bundle.putStringArrayList("appointment_time", appointment_time);

        DoctorFragment doctorFragment = new DoctorFragment();
        doctorFragment.setArguments(bundle);

        Bundle bundle2 = new Bundle();
        bundle2.putStringArrayList("medicine_id", medicine_id);
        bundle2.putStringArrayList("medicine_name", medicine_name);
        bundle2.putStringArrayList("medicine_freq", medicine_freq);
        bundle2.putStringArrayList("medicine_time", medicine_time);

        storeDataInMedArray();
//
        MedicineFragment medicineFragment = new MedicineFragment();
        medicineFragment.setArguments(bundle2);

        VPAdapter vpAdapter = new VPAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(doctorFragment, "Doctor"); // Add the fragment with arguments
        vpAdapter.addFragment(new ProfileFragment(), "Profile");
        vpAdapter.addFragment(medicineFragment, "Medicine");
        viewPager.setAdapter(vpAdapter);
        Intent intent = getIntent();
        String previousActivityName = intent.getStringExtra("previousDoctor");
//        Log.d("PreviousActivity", "Previous Activity: " + previousActivityName);
        if ("AddAppointmentActivity".equals(previousActivityName)) {
            viewPager.setCurrentItem(0);
        }
        if ("AddMedicineActivity".equals(previousActivityName)) {
            viewPager.setCurrentItem(2);
        }
        if (previousActivityName == null) {
            viewPager.setCurrentItem(1);
        }
    }
    void storeDataInAppArray() {
        Cursor cursor = myDB.readAllAppData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()) {
                appointment_id.add(cursor.getString(0));
                doctor_name.add(cursor.getString(1));
                appointment_date.add(cursor.getString(2));
                appointment_time.add(cursor.getString(3));
            }
        }
    }

    void storeDataInMedArray() {
        Cursor cursor = myDB.readAllMedData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()) {
                medicine_id.add(cursor.getString(0));
                medicine_name.add(cursor.getString(1));
                medicine_freq.add(cursor.getString(2));
                medicine_time.add(cursor.getString(3));
            }
        }
    }
}
