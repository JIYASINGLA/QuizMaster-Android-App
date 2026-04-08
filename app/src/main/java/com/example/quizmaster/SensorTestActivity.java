package com.example.quizmaster;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class SensorTestActivity extends AppCompatActivity {

    TextView tvSensors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_test);

        // Enable back button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Sensor Test");
        }

        tvSensors = findViewById(R.id.tvSensors);

        SensorManager sensorManager =
                (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);

        StringBuilder data = new StringBuilder();
        data.append("Available Sensors:\n\n");

        for (Sensor sensor : sensorList) {
            data.append(sensor.getName()).append("\n\n");
        }

        tvSensors.setText(data.toString());
    }

    // Back button action
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}