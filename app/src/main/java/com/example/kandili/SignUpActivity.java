package com.example.kandili;

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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);

        // Setup ViewFlipper (included via layout_background_slider)
        ViewFlipper viewFlipper = findViewById(R.id.viewFlipper);
        if (viewFlipper != null) {
            viewFlipper.startFlipping();
        }

        // Apply Blur Effect for API 31+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            View signUpCard = findViewById(R.id.signUpCard);
            if (signUpCard != null) {
                signUpCard.setRenderEffect(RenderEffect.createBlurEffect(20f, 20f, Shader.TileMode.CLAMP));
            }
        }

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
        ss.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.button_orange)), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        loginLinkText.setText(ss);
        loginLinkText.setMovementMethod(LinkMovementMethod.getInstance());
    }
}