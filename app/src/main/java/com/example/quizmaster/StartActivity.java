package com.example.quizmaster;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;   // add this

import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity {

    Button startQuiz, chooseCategory, leaderboard, deviceInfo, sensorTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        TextView tvWelcome;

        startQuiz = findViewById(R.id.btnStartQuiz);
        chooseCategory = findViewById(R.id.btnChooseCategory);
        leaderboard = findViewById(R.id.btnLeaderboard);
        deviceInfo = findViewById(R.id.btnDeviceInfo);
        sensorTest = findViewById(R.id.btnSensorTest);
        tvWelcome = findViewById(R.id.tvWelcome);

// Get username from LoginActivity
        String username = getIntent().getStringExtra("USERNAME");

        if (username != null && !username.isEmpty()) {
            tvWelcome.setText("Welcome, " + username + "!");
        } else {
            tvWelcome.setText("Welcome!");
        }

        // Start Quiz → Home Page
        startQuiz.setOnClickListener(v -> {
            Intent intent = new Intent(StartActivity.this, HomeActivity.class);
            startActivity(intent);
        });

        // Choose Category → Category Page
        chooseCategory.setOnClickListener(v -> {
            Intent intent = new Intent(StartActivity.this, CategoryActivity.class);
            startActivity(intent);
        });

        // Leaderboard → Leaderboard Page
        leaderboard.setOnClickListener(v -> {
            Intent intent = new Intent(StartActivity.this, LeaderboardActivity.class);
            startActivity(intent);
        });
        // Device Info
        deviceInfo.setOnClickListener(v -> {
            Intent intent = new Intent(StartActivity.this, DeviceInfoActivity.class);
            startActivity(intent);
        });

// Sensor Test
        sensorTest.setOnClickListener(v -> {
            Intent intent = new Intent(StartActivity.this, SensorTestActivity.class);
            startActivity(intent);
        });
    }
}