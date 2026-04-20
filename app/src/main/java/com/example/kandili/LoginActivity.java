package com.example.kandili;

import android.content.Intent;
import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
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

        // 1. Setup Background Effects (Keeping your original work)
        ViewFlipper viewFlipper = findViewById(R.id.viewFlipper);
        if (viewFlipper != null) {
            viewFlipper.startFlipping();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            View loginCard = findViewById(R.id.loginCard);
            if (loginCard != null) {
                loginCard.setRenderEffect(RenderEffect.createBlurEffect(20f, 20f, Shader.TileMode.CLAMP));
            }
        }

        // 2. Initialize Login Views
        EditText emailInput = findViewById(R.id.emailInput);
        EditText passwordInput = findViewById(R.id.passwordInput);
        AppCompatButton loginBtn = findViewById(R.id.loginBtn);
        AppCompatButton signUpBtn = findViewById(R.id.signUpBtn);

        // 3. Login Logic
        if (loginBtn != null) {
            loginBtn.setOnClickListener(v -> {
                String email = emailInput.getText().toString().trim();
                String password = passwordInput.getText().toString().trim();

                // Hardcoded Credentials
                if (email.equals("johndoe@gmail.com") && password.equals("12345678")) {
                    Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show();

                    // Redirect to Dashboard
                    Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                    startActivity(intent);
                    finish(); // Prevents user from going back to login screen
                } else {
                    Toast.makeText(this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
                }
            });
        }

        // 4. Navigation to Sign Up screen (Keeping your original work)
        if (signUpBtn != null) {
            signUpBtn.setOnClickListener(v -> {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            });
        }
    }
}