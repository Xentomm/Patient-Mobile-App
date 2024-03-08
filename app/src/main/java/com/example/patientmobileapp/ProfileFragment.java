package com.example.patientmobileapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        ImageView userIcon = view.findViewById(R.id.userIcon);
        TextView userName = view.findViewById(R.id.userName);
        TextView userEmail = view.findViewById(R.id.userEmail);
        TextView userInfo = view.findViewById(R.id.userInfo);

        String userNameText = "John Doe";
        String userEmailText = "john.doe@example.com";
        String userInfoText = "Software Developer";

        userName.setText(userNameText);
        userEmail.setText(userEmailText);
        userInfo.setText(userInfoText);

        userIcon.setImageResource(R.drawable.default_user_icon);

        return view;
    }
}
