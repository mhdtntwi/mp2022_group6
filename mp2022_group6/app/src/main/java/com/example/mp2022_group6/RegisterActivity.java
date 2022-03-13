package com.example.mp2022_group6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.DatabaseMetaData;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextRegisterFullname, editTextRegisterEmail, editTextRegisterPwd, editTextRegisterPhone;
    private static final String TAG= "RegisterActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextRegisterFullname = findViewById(R.id.re_fullname);
        editTextRegisterEmail = findViewById(R.id.re_email);
        editTextRegisterPwd = findViewById(R.id.re_password);
        editTextRegisterPhone = findViewById(R.id.re_phone);

        Button buttonRegister = findViewById(R.id.button_register);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Obtain entered data
                String textFullName =  editTextRegisterFullname.getText().toString();
                String textEmail = editTextRegisterEmail.getText().toString();
                String textPwd = editTextRegisterPwd.getText().toString();
                String textPhone = editTextRegisterPhone.getText().toString();

                //Validate Mobile Phone using Matcher and Pattern
                String mobileRegex = "[0-9][0-9]{9}";
                Matcher mobileMatcher;
                Pattern mobilePattern = Pattern.compile(mobileRegex);
                mobileMatcher = mobilePattern.matcher(textPhone);

                if (TextUtils.isEmpty(textFullName)) {
                    Toast.makeText(RegisterActivity.this, "Please enter your full name", Toast.LENGTH_LONG).show();
                    editTextRegisterFullname.setError("Full Name is required");
                    editTextRegisterFullname.requestFocus();
                }
                else if (TextUtils.isEmpty(textEmail)) {
                    Toast.makeText(RegisterActivity.this, "Please enter your email", Toast.LENGTH_LONG).show();
                    editTextRegisterEmail.setError("Email is required");
                    editTextRegisterEmail.requestFocus();
                }
                else if (!Patterns.EMAIL_ADDRESS.matcher(textEmail).matches()) {
                    Toast.makeText(RegisterActivity.this, "Please re-enter your email", Toast.LENGTH_LONG).show();
                    editTextRegisterEmail.setError("Valid email is required");
                    editTextRegisterEmail.requestFocus();
                }
                else if (TextUtils.isEmpty(textPwd)) {
                    Toast.makeText(RegisterActivity.this, "Please enter your password", Toast.LENGTH_LONG).show();
                    editTextRegisterPwd.setError("Password is required");
                    editTextRegisterPwd.requestFocus();
                }
                else if (textPwd.length() < 6) {
                    Toast.makeText(RegisterActivity.this, "Password should be at least 6 digits", Toast.LENGTH_LONG).show();
                    editTextRegisterPwd.setError("Password too weak");
                    editTextRegisterPwd.requestFocus();
                }
                else if (TextUtils.isEmpty(textPhone)) {
                    Toast.makeText(RegisterActivity.this, "Please enter your mobile phone", Toast.LENGTH_LONG).show();
                    editTextRegisterPhone.setError("Mobile Phone is required");
                    editTextRegisterPhone.requestFocus();
                }
                else if (textPhone.length() != 10) {
                    Toast.makeText(RegisterActivity.this, "Please re-enter your mobile phone", Toast.LENGTH_LONG).show();
                    editTextRegisterPhone.setError("Mobile No. should be 10 digits");
                    editTextRegisterPhone.requestFocus();
                }
                else if (!mobileMatcher.find()) {
                    Toast.makeText(RegisterActivity.this, "Please re-enter your mobile phone", Toast.LENGTH_LONG).show();
                    editTextRegisterPhone.setError("Mobile No. is not Valid");
                    editTextRegisterPhone.requestFocus();
                }
                else {
                    registerUser(textFullName, textEmail, textPwd, textPhone);
                }
            }
        });

    }

    //Register User using the credentials given
    private void registerUser(String textFullName, String textEmail, String textPwd, String textPhone) {
        FirebaseAuth auth = FirebaseAuth.getInstance();

        //Create User Profile
        auth.createUserWithEmailAndPassword(textEmail, textPwd).addOnCompleteListener(RegisterActivity.this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            FirebaseUser firebaseUser = auth.getCurrentUser();

                            //Update Display Name of user
                            UserProfileChangeRequest profileChangeRequest = new  UserProfileChangeRequest.Builder().setDisplayName(textFullName).build();
                            firebaseUser.updateProfile(profileChangeRequest);
                            //Enter User Data Into the Firebase Realtime Database
                            ReadWriteUserDetails writeUserDetails = new ReadWriteUserDetails(textPhone);

                            //Extracting User reference from Database for "Registered Users"
                            DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("Registered Users");

                            referenceProfile.child(firebaseUser.getUid()).setValue(writeUserDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()){
                                        //Send Verification Email
                                        firebaseUser.sendEmailVerification();

                                        Toast.makeText(RegisterActivity.this, "User registered successfully. Please verify your email",
                                                Toast.LENGTH_LONG).show();

                                        /*//Open User Profile after successful registration
                                        Intent intent = new Intent(RegisterActivity.this, MainmenuActivity.class);
                                        //To prevent User from returning back to register Activity on pressing back button after registration
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK
                                                | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish(); //To close RegisterActivity */

                                    } else {

                                        Toast.makeText(RegisterActivity.this, "User registered failed. Please try again",
                                                Toast.LENGTH_LONG).show();

                                    }

                                }
                            });
                        } else {
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthWeakPasswordException e){
                                editTextRegisterPwd.setError("Your password is too weak. Kindly use a mix of alphabets, number and special characters");
                                editTextRegisterPwd.requestFocus();
                            } catch (FirebaseAuthInvalidCredentialsException e){
                                editTextRegisterEmail.setError("Your email is invalid or already in use. Kindly re-enter.");
                                editTextRegisterEmail.requestFocus();
                            } catch (FirebaseAuthUserCollisionException e){
                                editTextRegisterEmail.setError("User is already registered with this email. Use another email.");
                                editTextRegisterEmail.requestFocus();
                            } catch (Exception e){
                                Log.e(TAG, e.getMessage());
                                Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
    }

}