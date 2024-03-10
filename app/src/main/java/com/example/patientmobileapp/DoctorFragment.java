package com.example.patientmobileapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DoctorFragment extends Fragment {

    RecyclerView recyclerView;
    FloatingActionButton doctor_add_button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doctor, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        doctor_add_button = view.findViewById(R.id.doctor_add_button);

        // Set up your RecyclerView and FloatingActionButton here

        return view;
    }
}