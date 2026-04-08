package com.example.quizmaster;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText etUserName;
    TextInputLayout nameLayout;
    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUserName = findViewById(R.id.etUserName);
        nameLayout = findViewById(R.id.nameLayout);
        btnStart = findViewById(R.id.btnStart);

        btnStart.setOnClickListener(v -> {

            String name = etUserName.getText().toString().trim();

            if (TextUtils.isEmpty(name)) {

                nameLayout.setError("First fill your name");

            } else {

                nameLayout.setError(null);

                Intent intent = new Intent(LoginActivity.this, StartActivity.class);
                intent.putExtra("USERNAME", name);
                startActivity(intent);
                finish();
            }
        });
    }
}