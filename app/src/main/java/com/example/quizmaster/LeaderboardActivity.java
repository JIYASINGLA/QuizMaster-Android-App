package com.example.quizmaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

public class LeaderboardActivity extends AppCompatActivity {

    ListView leaderboardList;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        leaderboardList = findViewById(R.id.leaderboardList);
        btnBack = findViewById(R.id.btnBack);

        SharedPreferences prefs = getSharedPreferences("QuizMaster", MODE_PRIVATE);
        int size = prefs.getInt("size", 0);

        ArrayList<String> players = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            String name = prefs.getString("name" + i, "");
            int score = prefs.getInt("score" + i, 0);

            players.add(name + " - " + score + "/10");
        }

        // Sort highest score first
        Collections.sort(players, Collections.reverseOrder());

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_list_item_1,
                        players);

        leaderboardList.setAdapter(adapter);

        // 🔙 Back button → HomeActivity
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(LeaderboardActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        });
    }
}