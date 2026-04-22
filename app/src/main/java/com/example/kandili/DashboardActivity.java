package com.example.kandili;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class DashboardActivity extends AppCompatActivity {

    TextView day1, day2, day3, day4;
    TextView tvWeatherType, tvTemp, tvFeelsLike;
    ImageView ivWeatherBg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        day1 = findViewById(R.id.day1);
        day2 = findViewById(R.id.day2);
        day3 = findViewById(R.id.day3);
        day4 = findViewById(R.id.day4);

        tvWeatherType = findViewById(R.id.tvWeatherType);
        tvTemp = findViewById(R.id.tvTemp);
        tvFeelsLike = findViewById(R.id.tvFeelsLike);
        ivWeatherBg = findViewById(R.id.ivWeatherBg);

        // Set the clicks
        day1.setOnClickListener(v -> {
            updateWeather("SUNNY", "32°", "36°", R.drawable.weather_sunny_bg);
            updateChipColors(day1);
        });

        day2.setOnClickListener(v -> {
            updateWeather("RAINY", "24°", "26°", R.drawable.weather_rainy_bg);
            updateChipColors(day2);
        });

        day3.setOnClickListener(v -> {
            updateWeather("RAINY", "23°", "25°", R.drawable.weather_rainy_bg);
            updateChipColors(day3);
        });

        day4.setOnClickListener(v -> {
            updateWeather("SUNNY", "31°", "34°", R.drawable.weather_sunny_bg);
            updateChipColors(day4);
        });

        // Set Day 1 as active by default when opening
        updateChipColors(day1);
    }

    private void updateWeather(String type, String temp, String feels, int bgResId) {
        tvWeatherType.setText(type);
        tvTemp.setText(temp);
        tvFeelsLike.setText(feels);
        ivWeatherBg.setImageResource(bgResId);
    }

    private void updateChipColors(TextView activeDay) {
        // First, set EVERY chip to the inactive (gray) background
        day1.setBackgroundResource(R.drawable.bg_chip_inactive);
        day2.setBackgroundResource(R.drawable.bg_chip_inactive);
        day3.setBackgroundResource(R.drawable.bg_chip_inactive);
        day4.setBackgroundResource(R.drawable.bg_chip_inactive);

        // Then, set only the clicked chip to the active (orange) background
        activeDay.setBackgroundResource(R.drawable.bg_chip_active);
    }
}