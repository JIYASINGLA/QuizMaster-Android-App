package com.example.quizmaster;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    TextView txtQuestion, txtProgress;
    RadioGroup radioGroup;
    RadioButton option1, option2, option3, option4;
    Button btnNext, btnPrevious;

    DatabaseReference databaseReference;

    List<QuestionModel> selectedQuestions = new ArrayList<>();
    List<String> userAnswers = new ArrayList<>();

    int currentQuestion = 0;
    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        category = getIntent().getStringExtra("CATEGORY");

        txtQuestion = findViewById(R.id.txtQuestion);
        txtProgress = findViewById(R.id.txtProgress);
        radioGroup = findViewById(R.id.radioGroup);

        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);

        btnNext = findViewById(R.id.btnNext);
        btnPrevious = findViewById(R.id.btnPrevious);

        databaseReference = FirebaseDatabase.getInstance()
                .getReference("categories")
                .child(category)
                .child("questions");

        loadQuestions();

        btnNext.setOnClickListener(v -> nextQuestion());
        btnPrevious.setOnClickListener(v -> previousQuestion());
    }

    private void loadQuestions() {

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                List<QuestionModel> allQuestions = new ArrayList<>();

                for (DataSnapshot ds : snapshot.getChildren()) {
                    QuestionModel q = ds.getValue(QuestionModel.class);
                    if (q != null) {
                        allQuestions.add(q);
                    }
                }

                if (allQuestions.size() == 0) {
                    Toast.makeText(QuizActivity.this,
                            "No Questions Found!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Collections.shuffle(allQuestions);

                int limit = Math.min(10, allQuestions.size());

                selectedQuestions.clear();
                userAnswers.clear();

                for (int i = 0; i < limit; i++) {
                    selectedQuestions.add(allQuestions.get(i));
                    userAnswers.add("");
                }

                showQuestion();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(QuizActivity.this,
                        "Failed to load questions", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showQuestion() {

        if (currentQuestion < selectedQuestions.size()) {

            QuestionModel q = selectedQuestions.get(currentQuestion);

            txtProgress.setText("Question "
                    + (currentQuestion + 1)
                    + "/" + selectedQuestions.size());

            txtQuestion.setText(q.getQuestion());

            option1.setText(q.getOption1());
            option2.setText(q.getOption2());
            option3.setText(q.getOption3());
            option4.setText(q.getOption4());

            radioGroup.clearCheck();

            resetOptionColors();

            String savedAnswer = userAnswers.get(currentQuestion);

            if (!savedAnswer.equals("")) {
                if (option1.getText().toString().equals(savedAnswer))
                    option1.setChecked(true);
                else if (option2.getText().toString().equals(savedAnswer))
                    option2.setChecked(true);
                else if (option3.getText().toString().equals(savedAnswer))
                    option3.setChecked(true);
                else if (option4.getText().toString().equals(savedAnswer))
                    option4.setChecked(true);
            }
        }
    }

    private void nextQuestion() {

        int selectedId = radioGroup.getCheckedRadioButtonId();

        if (selectedId == -1) {
            Toast.makeText(this,
                    "Please select an answer", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton selectedOption = findViewById(selectedId);
        String selectedText = selectedOption.getText().toString();

        QuestionModel current = selectedQuestions.get(currentQuestion);

        userAnswers.set(currentQuestion, selectedText);

        String correctAnswer = current.getAnswer();

        disableOptions();

        if (selectedText.equals(correctAnswer)) {

            selectedOption.setBackgroundColor(Color.parseColor("#A5D6A7"));

        } else {

            selectedOption.setBackgroundColor(Color.parseColor("#EF9A9A"));

            highlightCorrectAnswer(correctAnswer);
        }

        new Handler().postDelayed(() -> {

            resetOptionColors();

            currentQuestion++;

            if (currentQuestion < selectedQuestions.size()) {
                showQuestion();
            } else {
                calculateScore();
            }

        }, 1000);
    }

    private void previousQuestion() {

        if (currentQuestion > 0) {
            currentQuestion--;
            showQuestion();
        }
    }

    private void calculateScore() {

        int score = 0;

        for (int i = 0; i < selectedQuestions.size(); i++) {

            if (userAnswers.get(i)
                    .equals(selectedQuestions.get(i).getAnswer())) {
                score++;
            }
        }

        Intent intent = new Intent(this, ScoreActivity.class);
        intent.putExtra("score", score);
        intent.putExtra("total", selectedQuestions.size());
        startActivity(intent);
        finish();
    }

    private void highlightCorrectAnswer(String answer){

        if(option1.getText().toString().equals(answer))
            option1.setBackgroundColor(Color.parseColor("#A5D6A7"));

        if(option2.getText().toString().equals(answer))
            option2.setBackgroundColor(Color.parseColor("#A5D6A7"));

        if(option3.getText().toString().equals(answer))
            option3.setBackgroundColor(Color.parseColor("#A5D6A7"));

        if(option4.getText().toString().equals(answer))
            option4.setBackgroundColor(Color.parseColor("#A5D6A7"));
    }

    private void disableOptions(){

        option1.setEnabled(false);
        option2.setEnabled(false);
        option3.setEnabled(false);
        option4.setEnabled(false);
    }

    private void resetOptionColors(){

        option1.setBackgroundColor(Color.TRANSPARENT);
        option2.setBackgroundColor(Color.TRANSPARENT);
        option3.setBackgroundColor(Color.TRANSPARENT);
        option4.setBackgroundColor(Color.TRANSPARENT);

        option1.setEnabled(true);
        option2.setEnabled(true);
        option3.setEnabled(true);
        option4.setEnabled(true);
    }
}