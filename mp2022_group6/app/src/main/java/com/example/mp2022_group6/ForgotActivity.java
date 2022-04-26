package com.example.mp2022_group6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText emailEditText;
    private Button resetPasswordButton;
    private ProgressBar progressBar;
    private ImageView backButton;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        backButton = (ImageView) findViewById(R.id.backButton);
        backButton.setOnClickListener(this);

        emailEditText = (EditText) findViewById(R.id.fo_email);
        resetPasswordButton = (Button) findViewById(R.id.button_reset);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        
        auth = FirebaseAuth.getInstance();
        
        resetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }

            private void resetPassword() {
                String email = emailEditText.getText().toString().trim();

                if (email.isEmpty()){
                    emailEditText.setError("Email is required");
                    emailEditText.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    emailEditText.setError("Please provide valid email");
                    emailEditText.requestFocus();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(ForgotActivity.this, "Check your email to reset your password!", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(ForgotActivity.this, "Try again! Something wrong happened!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backButton:
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }
}