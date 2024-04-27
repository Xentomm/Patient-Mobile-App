package com.example.patientmobileapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MedicineFragment extends Fragment {

    RecyclerView recyclerView;
    FloatingActionButton medicine_add_button;
    CustomAdapter customAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_medicine, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        medicine_add_button = view.findViewById(R.id.medicine_add_button);
        medicine_add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddMedicineActivity.class);
                startActivity(intent);
            }
        });

        Bundle bundle = getArguments();
        if (bundle != null) {
            ArrayList<String> medicine_id = bundle.getStringArrayList("medicine_id");
            ArrayList<String> medicine_name = bundle.getStringArrayList("medicine_name");
            ArrayList<String> medicine_freq = bundle.getStringArrayList("medicine_freq");
            ArrayList<String> medicine_time = bundle.getStringArrayList("medicine_time");

            customAdapter = new CustomAdapter(getActivity(), medicine_id, medicine_name, medicine_freq, medicine_time);
            recyclerView.setAdapter(customAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }

        return view;
    }
}