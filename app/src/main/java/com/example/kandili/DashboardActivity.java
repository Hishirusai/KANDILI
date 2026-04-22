package com.example.kandili;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

public class DashboardActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private TextView day1, day2, day3, day4, tvWeatherType, tvTemp, tvFeelsLike;
    private ImageView ivWeatherBg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // --- 1. SIDEBAR & REDIRECTION LOGIC ---
        drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.setScrimColor(Color.parseColor("#40000000"));

        NavigationView navigationView = findViewById(R.id.nav_view);
        ImageView menuIcon = findViewById(R.id.menuIcon);

        menuIcon.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));
        navigationView.setCheckedItem(R.id.nav_dashboard);

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_news) {
                startActivity(new Intent(DashboardActivity.this, NewsActivity.class));
            }
            else if (id == R.id.nav_disasters) {

                Toast.makeText(this, "Opening Disasters...", Toast.LENGTH_SHORT).show();
            }
            else if (id == R.id.nav_hotlines) {
                // startActivity(new Intent(DashboardActivity.this, HotlinesActivity.class));
                Toast.makeText(this, "Opening Hotlines...", Toast.LENGTH_SHORT).show();
            }
            else if (id == R.id.nav_settings) {
                // startActivity(new Intent(DashboardActivity.this, SettingsActivity.class));
                Toast.makeText(this, "Opening Settings...", Toast.LENGTH_SHORT).show();
            }
            else if (id == R.id.nav_logout) {
                finish();
            }

            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        // --- 2. WEATHER LOGIC (Restored your original grid logic) ---
        day1 = findViewById(R.id.day1);
        day2 = findViewById(R.id.day2);
        day3 = findViewById(R.id.day3);
        day4 = findViewById(R.id.day4);
        tvWeatherType = findViewById(R.id.tvWeatherType);
        tvTemp = findViewById(R.id.tvTemp);
        tvFeelsLike = findViewById(R.id.tvFeelsLike);
        ivWeatherBg = findViewById(R.id.ivWeatherBg);

        day1.setOnClickListener(v -> updateUI("SUNNY", "32°", "36°", R.drawable.weather_sunny_bg, day1));
        day2.setOnClickListener(v -> updateUI("RAINY", "24°", "26°", R.drawable.weather_rainy_bg, day2));
        day3.setOnClickListener(v -> updateUI("RAINY", "23°", "25°", R.drawable.weather_rainy_bg, day3));
        day4.setOnClickListener(v -> updateUI("SUNNY", "31°", "34°", R.drawable.weather_sunny_bg, day4));

        updateUI("SUNNY", "32°", "36°", R.drawable.weather_sunny_bg, day1);
    }

    private void updateUI(String type, String temp, String feels, int bg, TextView active) {
        tvWeatherType.setText(type);
        tvTemp.setText(temp);
        tvFeelsLike.setText(feels);
        ivWeatherBg.setImageResource(bg);
        day1.setBackgroundResource(R.drawable.bg_chip_inactive);
        day2.setBackgroundResource(R.drawable.bg_chip_inactive);
        day3.setBackgroundResource(R.drawable.bg_chip_inactive);
        day4.setBackgroundResource(R.drawable.bg_chip_inactive);
        active.setBackgroundResource(R.drawable.bg_chip_active);
    }
}