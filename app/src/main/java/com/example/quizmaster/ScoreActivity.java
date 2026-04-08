package com.example.quizmaster;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class ScoreActivity extends AppCompatActivity {

    TextView txtScore, txtMessage, txtCongrats;

    int score;
    int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        txtScore = findViewById(R.id.txtScore);
        txtMessage = findViewById(R.id.txtMessage);
        txtCongrats = findViewById(R.id.txtCongrats);

        // Get score from QuizActivity
        score = getIntent().getIntExtra("score", 0);
        total = getIntent().getIntExtra("total", 10);

        // Format score like 09/10
        String formattedScore = String.format("%02d/%02d", score, total);
        txtScore.setText(formattedScore);

        // Get user name
        String name = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();

        if(name == null || name.isEmpty()){
            name = "Player";
        }

        // Display congratulation message
        txtMessage.setText("Great job, " + name + "! You Did It");

        // Save result
        saveToLeaderboard(score);
    }

    // Next Quiz Button
    public void nextQuiz(View view){
        Intent intent = new Intent(ScoreActivity.this, QuizActivity.class);
        startActivity(intent);
        finish();
    }

    // Back to Home
    public void goHome(View view){
        Intent intent = new Intent(ScoreActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    // Save score to Firebase leaderboard
    private void saveToLeaderboard(int score){

        String uid = FirebaseAuth.getInstance().getUid();
        String name = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();

        if(name == null || name.isEmpty()){
            name = "Player";
        }

        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference("leaderboard")
                .child(uid);

        Map<String,Object> map = new HashMap<>();
        map.put("name", name);
        map.put("score", score);
        map.put("total", total);
        map.put("timestamp", System.currentTimeMillis());

        ref.setValue(map);
    }
}