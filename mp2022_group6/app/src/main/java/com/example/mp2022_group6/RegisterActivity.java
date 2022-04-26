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
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView button_register, createText;
    private EditText editTextFullName, editTextPhone, editTextEmail, editTextPassword;
    private ProgressBar progressBar;
    private ImageView backButton;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        button_register = (Button) findViewById(R.id.button_register);
        button_register.setOnClickListener(this);

        createText = (TextView) findViewById(R.id.createText);
        createText.setOnClickListener(this);

        backButton = (ImageView) findViewById(R.id.backButton);
        backButton.setOnClickListener(this);

        editTextFullName = (EditText) findViewById(R.id.re_fullname);
        editTextEmail = (EditText) findViewById(R.id.re_email);
        editTextPassword = (EditText) findViewById(R.id.re_password);
        editTextPhone = (EditText) findViewById(R.id.re_phone);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

    }

    private void sendEmailVerification() {
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        if(firebaseUser!=null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this,"Successful Registered, Verification Mail Sent!",Toast.LENGTH_SHORT).show();
                        mAuth.signOut();
                        finish();
                        startActivity(new Intent(RegisterActivity.this , LoginActivity.class));

                    }else{
                        Toast.makeText(RegisterActivity.this,"Verification Mail has'nt been Sent!",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backButton:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.createText:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.button_register:
                button_register();
                break;
        }
    }

    private void button_register() {
        String email= editTextEmail.getText().toString().trim();
        String fullname= editTextFullName.getText().toString().trim();
        String password= editTextPassword.getText().toString().trim();
        String phone= editTextPhone.getText().toString().trim();

        if(fullname.isEmpty()){
            editTextFullName.setError("Fullname is required!");
            editTextFullName.requestFocus();
            return;
        }
        if (email.isEmpty()){
            editTextEmail.setError("Email is required!");
            editTextEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please provide valid email!");
            editTextEmail.requestFocus();
            return;
        }
        if (password.isEmpty()){
            editTextPassword.setError("Password is required!");
            editTextPassword.requestFocus();
            return;
        }
        if (password.length() < 6){
            editTextPassword.setError("Min password length should be 6 characters!");
            editTextPassword.requestFocus();
            return;
        }
        if (phone.isEmpty()){
            editTextPhone.setError("Phone Number is required!");
            editTextPhone.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            User user = new User(fullname,phone,email);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        //Toast.makeText(RegisterActivity.this, "User has been registeres successfully", Toast.LENGTH_LONG).show();
                                        //mAuth.signOut();
                                        //finish();
                                        //startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

                                        sendEmailVerification();

                                    }else {
                                        Toast.makeText(RegisterActivity.this, "Failed to register! Try Again!", Toast.LENGTH_LONG).show();
                                    }
                                    progressBar.setVisibility(View.GONE);
                                }

                            });
                        }else {
                            Toast.makeText(RegisterActivity.this, "Failed to register! Try Again!", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }
}