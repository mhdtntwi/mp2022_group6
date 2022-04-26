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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

private TextView register, forgotPassword;
private EditText editTextEmail, editTextPassword;
private Button button_login;
private ImageView backButton;

private FirebaseAuth mAuth;
private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        register = (TextView) findViewById(R.id.registration);
        register.setOnClickListener(this);

        button_login = (Button) findViewById(R.id.button_confirm);
        button_login.setOnClickListener(this);

        backButton = (ImageView) findViewById(R.id.backButton);
        backButton.setOnClickListener(this);


        editTextEmail = (EditText) findViewById(R.id.re_email);
        editTextPassword = (EditText) findViewById(R.id.re_password);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();

        forgotPassword = (TextView) findViewById(R.id.forgotPassword);
        forgotPassword.setOnClickListener(this);

        FirebaseUser user = mAuth.getCurrentUser();
        if (user!= null){
            finish();
            startActivity(new Intent(LoginActivity.this, NavigationActivity.class));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backButton:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.registration:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.button_confirm:
                userLogin();
                break;
            case R.id.forgotPassword:
                startActivity(new Intent(this, ForgotActivity.class));
                break;
        }
    }

    private void userLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty()){
            editTextEmail.setError("Email is required!");
            editTextEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please enter a valid email!");
            editTextEmail.requestFocus();
            return;
        }
        if (password.isEmpty()){
            editTextPassword.setError("Password is required!");
            editTextPassword.requestFocus();
            return;
        }
        if (password.length() < 6){
            editTextPassword.setError("Min password length is 6 characters!");
            editTextPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    Boolean emailflag = user.isEmailVerified();
                    if (emailflag){

                        finish();
                        startActivity(new Intent(LoginActivity.this, NavigationActivity.class));
                    }else {
                        Toast.makeText(LoginActivity.this, "Check your email to verify your account!", Toast.LENGTH_SHORT).show();
                        mAuth.signOut();
                    }

                    //if (user.isEmailVerified()){
                        //redirect to user profile
                        //startActivity(new Intent(LoginActivity.this, NavigationActivity.class));
                    //}else {
                        //user.sendEmailVerification();
                        //Toast.makeText(LoginActivity.this, "Check your email to verify your account!", Toast.LENGTH_SHORT).show();
                    }

                //}else {
                    //Toast.makeText(LoginActivity.this, "Failed to login! Please check your credentials", Toast.LENGTH_SHORT).show();

                //}
            }
        });
    }
}