package com.example.kandili;

import android.content.Intent;
import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ViewFlipper;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        // Setup ViewFlipper
        ViewFlipper viewFlipper = findViewById(R.id.viewFlipper);
        if (viewFlipper != null) {
            viewFlipper.startFlipping();
        }

        // Apply Blur Effect for API 31+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            View loginCard = findViewById(R.id.loginCard);
            if (loginCard != null) {
                loginCard.setRenderEffect(RenderEffect.createBlurEffect(20f, 20f, Shader.TileMode.CLAMP));
            }
        }

        // Navigation to Sign Up screen
        AppCompatButton signUpBtn = findViewById(R.id.signUpBtn);
        if (signUpBtn != null) {
            signUpBtn.setOnClickListener(v -> {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            });
        }
    }
}