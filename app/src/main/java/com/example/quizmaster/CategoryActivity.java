package com.example.quizmaster;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CategoryActivity extends AppCompatActivity {

    Button btnHtml, btnJs, btnReact, btnCpp, btnPython;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        btnHtml = findViewById(R.id.btnHTML);
        btnJs = findViewById(R.id.btnJs);
        btnReact = findViewById(R.id.btnReact);
        btnCpp = findViewById(R.id.btnCpp);
        btnPython = findViewById(R.id.btnPython);

        btnHtml.setOnClickListener(v -> openQuiz("HTML"));
        btnJs.setOnClickListener(v -> openQuiz("JAVASCRIPT"));
        btnReact.setOnClickListener(v -> openQuiz("REACT"));
        btnCpp.setOnClickListener(v -> openQuiz("CPP"));
        btnPython.setOnClickListener(v -> openQuiz("PYTHON"));
    }

    private void openQuiz(String category) {
        Intent intent = new Intent(CategoryActivity.this, QuizActivity.class);
        intent.putExtra("CATEGORY", category);
        startActivity(intent);
    }
}