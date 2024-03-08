package com.example.patientmobileapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView userIcon = findViewById(R.id.userIcon);
        TextView userName = findViewById(R.id.userName);
        TextView userEmail = findViewById(R.id.userEmail);
        TextView userInfo = findViewById(R.id.userInfo);

        String userNameText = "John Doe";
        String userEmailText = "john.doe@example.com";
        String userInfoText = "Software Developer";

        userName.setText(userNameText);
        userEmail.setText(userEmailText);
        userInfo.setText(userInfoText);

        userIcon.setImageResource(R.drawable.default_user_icon);
    }
}
