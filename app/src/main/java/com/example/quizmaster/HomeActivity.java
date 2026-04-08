package com.example.quizmaster;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    Button btnPlay;
    TextView tvUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnPlay = findViewById(R.id.btnPlay);
        tvUser = findViewById(R.id.tvUser);

        // Get username from LoginActivity
        String username = getIntent().getStringExtra("USERNAME");
        if (username != null) {
            tvUser.setText("Hi, " + username);
        }

        btnPlay.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, CategoryActivity.class);
            startActivity(intent);
        });
    }
}