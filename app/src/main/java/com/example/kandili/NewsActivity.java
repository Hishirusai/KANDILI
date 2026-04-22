package com.example.kandili;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        ImageView menuIcon = findViewById(R.id.menuIcon);

        if (menuIcon != null) {
            menuIcon.setOnClickListener(v -> {
                Intent intent = new Intent(NewsActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish(); // Close News so it doesn't pile up in memory
            });
        }
    }
}