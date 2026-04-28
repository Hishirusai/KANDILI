package com.example.kandili;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class LoginActivity extends AppCompatActivity {

    private UserPreferences userPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        // Initialize user preferences
        userPrefs = new UserPreferences(this);

        // 1. Setup Background Effects (Keeping your original work)
        ViewFlipper viewFlipper = findViewById(R.id.viewFlipper);
        if (viewFlipper != null) {
            viewFlipper.startFlipping();
        }

        // 2. Initialize Login Views
        EditText emailInput = findViewById(R.id.emailInput);
        EditText passwordInput = findViewById(R.id.passwordInput);
        AppCompatButton loginBtn = findViewById(R.id.loginBtn);
        AppCompatButton signUpBtn = findViewById(R.id.signUpBtn);
        android.widget.TextView forgotPasswordText = findViewById(R.id.forgotPasswordText);

        // 3. Login Logic
        if (loginBtn != null) {
            loginBtn.setOnClickListener(v -> {
                String email = emailInput.getText().toString().trim();
                String password = passwordInput.getText().toString().trim();

                // Validate credentials using UserPreferences
                if (userPrefs.validateCredentials(email, password)) {
                    Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show();

                    // Show Terms and Services Dialog
                    showTermsAndServicesDialog();
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

        // 5. Forgot Password Navigation
        if (forgotPasswordText != null) {
            forgotPasswordText.setOnClickListener(v -> {
                Intent intent = new Intent(LoginActivity.this, PasswordVerificationActivity.class);
                startActivity(intent);
            });
        }
    }

    /**
     * Show Terms and Services Dialog
     * User can agree and proceed to dashboard or disagree and exit the app
     */
    private void showTermsAndServicesDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Terms and Services");
        builder.setMessage(
                "KANDILI collects location data to enable real-time disaster notifications and proximity-based safety recommendations. Location data is used to check if facilities are closed or not in use. This ensures you are warned of hazards (like rains and floods) before you even open your phone.");

        // Agree Button - Navigate to Dashboard
        builder.setPositiveButton("I Agree", (dialog, which) -> {
            dialog.dismiss();
            // Redirect to Dashboard
            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
            startActivity(intent);
            finish(); // Prevents user from going back to login screen
        });

        // Disagree Button - Close the app
        builder.setNegativeButton("I Disagree", (dialog, which) -> {
            dialog.dismiss();
            // Close the application
            finishAffinity(); // Closes all activities in the task stack
        });

        // Prevent dismissal by clicking outside the dialog
        builder.setCancelable(false);

        // Show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}