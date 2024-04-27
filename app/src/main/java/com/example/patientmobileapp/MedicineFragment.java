package com.example.patientmobileapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
    }
}