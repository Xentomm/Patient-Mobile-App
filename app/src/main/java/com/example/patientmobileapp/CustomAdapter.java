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

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList appointment_id, doctor_name,  appointment_date, appointment_time;
    CustomAdapter(Context context, ArrayList appointment_id, ArrayList doctor_name, ArrayList appointment_date, ArrayList appointment_time){
        this.context = context;
        this.appointment_id = appointment_id;
        this.doctor_name = doctor_name;
        this.appointment_date = appointment_date;
        this.appointment_time = appointment_time;
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
        holder.appointment_id_text.setText(String.valueOf(appointment_id.get(position)));
        holder.doctor_name_text.setText(String.valueOf(doctor_name.get(position)));
        holder.appointment_date_text.setText(String.valueOf(appointment_date.get(position)));
        holder.appointment_time_text.setText(String.valueOf(appointment_time.get(position)));
        holder.appointmentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(appointment_id.get(position)));
                intent.putExtra("name", String.valueOf(doctor_name.get(position)));
                intent.putExtra("date", String.valueOf(appointment_date.get(position)));
                intent.putExtra("time", String.valueOf(appointment_time.get(position)));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return appointment_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView appointment_id_text, doctor_name_text, appointment_date_text, appointment_time_text;
        ConstraintLayout appointmentLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            appointment_id_text = itemView.findViewById(R.id.appointment_id_text);
            doctor_name_text = itemView.findViewById(R.id.doctor_name_text);
            appointment_date_text = itemView.findViewById(R.id.appointment_date_text);
            appointment_time_text = itemView.findViewById(R.id.appointment_time_text);
            appointmentLayout = itemView.findViewById(R.id.appointments_list);
        }
    }
}
