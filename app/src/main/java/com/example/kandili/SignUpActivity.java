package com.example.kandili;

import android.content.Intent;
import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import androidx.annotation.NonNull;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class SignUpActivity extends AppCompatActivity {

    private UserPreferences userPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);

        // Initialize user preferences
        userPrefs = new UserPreferences(this);

        // Setup ViewFlipper (included via layout_background_slider)
        ViewFlipper viewFlipper = findViewById(R.id.viewFlipper);
        if (viewFlipper != null) {
            viewFlipper.startFlipping();
        }

        // Do not apply blur on the sign-up card to avoid making the form hard to read.

        // Back Arrow functionality
        ImageView backArrow = findViewById(R.id.backArrow);
        backArrow.setOnClickListener(v -> finish());

        // "login" clickable link functionality
        TextView loginLinkText = findViewById(R.id.loginLinkText);
        String fullText = "Welcome to KANDILI Disaster information mobile application guide, we’re glad you’re here. Already have an account? login";
        SpannableString ss = new SpannableString(fullText);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                finish();
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(true);
                ds.setFakeBoldText(true);
            }
        };

        int start = fullText.indexOf("login");
        int end = start + "login".length();
        ss.setSpan(clickableSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.button_orange)), start, end,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        loginLinkText.setText(ss);
        loginLinkText.setMovementMethod(LinkMovementMethod.getInstance());

        // Initialize form fields
        EditText firstNameInput = findViewById(R.id.firstNameInput);
        EditText lastNameInput = findViewById(R.id.lastNameInput);
        EditText signUpEmailInput = findViewById(R.id.signUpEmailInput);
        EditText signUpPasswordInput = findViewById(R.id.signUpPasswordInput);
        AppCompatButton signUpSubmitBtn = findViewById(R.id.signUpSubmitBtn);

        // Sign up form submission
        if (signUpSubmitBtn != null) {
            signUpSubmitBtn.setOnClickListener(v -> {
                String firstName = firstNameInput.getText().toString().trim();
                String lastName = lastNameInput.getText().toString().trim();
                String email = signUpEmailInput.getText().toString().trim();
                String password = signUpPasswordInput.getText().toString().trim();

                // Basic validation
                if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Store user data in preferences
                userPrefs.setName(firstName + " " + lastName);
                userPrefs.setEmail(email);
                userPrefs.setPassword(password);

                Toast.makeText(this, "Account created successfully!", Toast.LENGTH_SHORT).show();

                // Redirect to dashboard
                Intent intent = new Intent(SignUpActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish();
            });
        }
    }
}