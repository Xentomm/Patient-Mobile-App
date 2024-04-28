package com.example.patientmobileapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomMedAdapter extends RecyclerView.Adapter<CustomMedAdapter.MyViewHolder> {

    private Context context;
    private ArrayList medicine_id, medicine_name,  medicine_freq, medicine_time;
    CustomMedAdapter(Context context, ArrayList medicine_id, ArrayList medicine_name, ArrayList medicine_freq, ArrayList medicine_time){
        this.context = context;
        this.medicine_id = medicine_id;
        this.medicine_name = medicine_name;
        this.medicine_freq = medicine_freq;
        this.medicine_time = medicine_time;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.medicine_id_text.setText(String.valueOf(medicine_id.get(position)));
        holder.medicine_name_text.setText(String.valueOf(medicine_name.get(position)));
        holder.medicine_freq_text.setText(String.valueOf(medicine_freq.get(position)));
        holder.medicine_time_text.setText(String.valueOf(medicine_time.get(position)));
        holder.medicineLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateMedActivity.class);
                intent.putExtra("id", String.valueOf(medicine_id.get(position)));
                intent.putExtra("name", String.valueOf(medicine_name.get(position)));
                intent.putExtra("freq", String.valueOf(medicine_freq.get(position)));
                intent.putExtra("time", String.valueOf(medicine_time.get(position)));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() { return medicine_id.size(); }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView medicine_id_text, medicine_name_text, medicine_freq_text, medicine_time_text;
        ConstraintLayout medicineLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            medicine_id_text = itemView.findViewById(R.id.appointment_id_text);
            medicine_name_text = itemView.findViewById(R.id.doctor_name_text);
            medicine_freq_text = itemView.findViewById(R.id.appointment_date_text);
            medicine_time_text = itemView.findViewById(R.id.appointment_time_text);
            medicineLayout = itemView.findViewById(R.id.appointments_list);
        }
    }
}
