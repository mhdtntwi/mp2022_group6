package com.example.mp2022_group6;

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

import com.google.firebase.auth.FirebaseAuth;

public class BookingForm extends AppCompatActivity {

    private EditText email, name , age , phone;
    private TextView date, from, destination, time, price, busname;
    String sdate, sfrom, sdestination, sprice, stime, snamebus, sstatusseat, sseatname;

    private ProgressBar progressBar;

    private Button continue_book;
    private ImageView back;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookingform);

        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BookingForm.this, SeatActivity.class));
            }
        });

        email = (EditText) findViewById(R.id.etEmail);
        name = (EditText) findViewById(R.id.etName);
        age = (EditText) findViewById(R.id.etAge);
        phone = (EditText) findViewById(R.id.etPhone);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        continue_book = (Button) findViewById(R.id.button_book);
        continue_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.button_book:
                        continue_book();
                        break;
                }
            }

            private void continue_book() {
                String semail= email.getText().toString().trim();
                String sname= name.getText().toString().trim();
                String sage= age.getText().toString().trim();
                String sphone= phone.getText().toString().trim();

                if (semail.isEmpty()){
                    email.setError("Email is required!");
                    email.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(semail).matches()){
                    email.setError("Please provide valid email!");
                    email.requestFocus();
                    return;
                }
                if(sname.isEmpty()){
                    name.setError("Name is required!");
                    name.requestFocus();
                    return;
                }
                if (sage.isEmpty()){
                    age.setError("Age is required!");
                    age.requestFocus();
                    return;
                }
                if (sphone.isEmpty()){
                    phone.setError("Phone Number is required!");
                    phone.requestFocus();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

            }
        });

    }
}