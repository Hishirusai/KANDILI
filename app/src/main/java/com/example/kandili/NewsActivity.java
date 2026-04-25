package com.example.kandili;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.core.view.GravityCompat;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

public class NewsActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ImageView menuIcon = findViewById(R.id.menuIcon);

        if (menuIcon != null) {
            menuIcon.setOnClickListener(v -> {
                drawerLayout.openDrawer(GravityCompat.START);
            });
        }

        if (navigationView != null) {
            navigationView.setCheckedItem(R.id.nav_news);

            navigationView.setNavigationItemSelectedListener(item -> {
                int id = item.getItemId();
                if (id == R.id.nav_dashboard) {
                    startActivity(new Intent(NewsActivity.this, DashboardActivity.class));
                    finish();
                }
                drawerLayout.closeDrawers();
                return true;
            });
        }

        Button btnViewMore1 = findViewById(R.id.btnViewMore1);
        Button btnViewMore2 = findViewById(R.id.btnViewMore2);
        Button btnViewMore3 = findViewById(R.id.btnViewMore3);
        if (btnViewMore1 != null) {
            btnViewMore1.setOnClickListener(v -> {
                String url = "https://pia.gov.ph/news/zamboanga-intensifies-water-conservation-efforts-amid-rising-demand/";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            });
        }
        if (btnViewMore2 != null) {
            btnViewMore2.setOnClickListener(v -> {
                String url = "https://mindanews.com/top-stories/2026/04/zamboanga-city-declares-state-of-calamity-due-to-oil-crisis/";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            });
        }
        if (btnViewMore3 != null) {
            btnViewMore3.setOnClickListener(v -> {
                String url = "https://www.sunstar.com.ph/zamboanga/kalis-brigade-troops-further-strengthens-disaster-preparedness";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            });
        }
    }
}