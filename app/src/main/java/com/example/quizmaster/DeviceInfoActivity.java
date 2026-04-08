package com.example.quizmaster;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DeviceInfoActivity extends AppCompatActivity {

    TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_info);

        tvInfo = findViewById(R.id.tvDeviceInfo);

        String info =

                "📱 Device Name: " + Build.MODEL + "\n\n" +
                        "🏷 Brand: " + Build.BRAND + "\n\n" +
                        "🏭 Manufacturer: " + Build.MANUFACTURER + "\n\n" +
                        "🤖 Android Version: " + Build.VERSION.RELEASE + "\n\n" +
                        "⚙ SDK Version: " + Build.VERSION.SDK_INT;

        tvInfo.setText(info);
    }
}