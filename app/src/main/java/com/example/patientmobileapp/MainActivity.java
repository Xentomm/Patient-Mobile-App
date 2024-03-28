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

        storeDataInArray();

        Bundle bundle = new Bundle();
        bundle.putStringArrayList("appointment_id", appointment_id);
        bundle.putStringArrayList("doctor_name", doctor_name);
        bundle.putStringArrayList("appointment_date", appointment_date);
        bundle.putStringArrayList("appointment_time", appointment_time);

        DoctorFragment doctorFragment = new DoctorFragment();
        doctorFragment.setArguments(bundle);

        VPAdapter vpAdapter = new VPAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(doctorFragment, "Doctor"); // Add the fragment with arguments
        vpAdapter.addFragment(new ProfileFragment(), "Profile");
        vpAdapter.addFragment(new MedicineFragment(), "Medicine");
        viewPager.setAdapter(vpAdapter);
        Intent intent = getIntent();
        String previousActivityName = intent.getStringExtra("previousDoctor");
//        Log.d("PreviousActivity", "Previous Activity: " + previousActivityName);
        if ("AddAppointmentActivity".equals(previousActivityName)) {
            viewPager.setCurrentItem(0);
        }
        if (previousActivityName == null) {
            viewPager.setCurrentItem(1);
        }
    }
    void storeDataInArray() {
        Cursor cursor = myDB.readAllData();
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
}
