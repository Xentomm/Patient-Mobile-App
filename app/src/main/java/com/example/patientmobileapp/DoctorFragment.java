package com.example.patientmobileapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class DoctorFragment extends Fragment {

    RecyclerView recyclerView;
    FloatingActionButton doctor_add_button;
    CustomAdapter customAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doctor, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        doctor_add_button = view.findViewById(R.id.doctor_add_button);
        doctor_add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddAppointmentActivity.class);
                startActivity(intent);
            }
        });

        Bundle bundle = getArguments();
        if (bundle != null) {
            ArrayList<String> appointment_id = bundle.getStringArrayList("appointment_id");
            ArrayList<String> doctor_name = bundle.getStringArrayList("doctor_name");
            ArrayList<String> appointment_date = bundle.getStringArrayList("appointment_date");
            ArrayList<String> appointment_time = bundle.getStringArrayList("appointment_time");

            customAdapter = new CustomAdapter(getActivity(), appointment_id, doctor_name, appointment_date, appointment_time);
            recyclerView.setAdapter(customAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        return view;
    }

}