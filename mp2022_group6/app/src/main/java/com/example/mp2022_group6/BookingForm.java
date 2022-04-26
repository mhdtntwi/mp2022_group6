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
    private TextView date, from, destination, time, price, busname, seatname;
    String sdate, sfrom, sdestination, sprice, stime, snamebus, sstatusseat, sseatname, semail, sname, sage, sphone;

    private ProgressBar progressBar;

    private Button continue_book;
    private ImageView back;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookingform);

        email = (EditText) findViewById(R.id.etEmail);
        name = (EditText) findViewById(R.id.etName);
        age = (EditText) findViewById(R.id.etAge);
        phone = (EditText) findViewById(R.id.etPhone);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        continue_book = (Button) findViewById(R.id.button_book);
        back = (ImageView) findViewById(R.id.back);

        date = findViewById(R.id.tvcdate);
        from = findViewById(R.id.tvbookfrom);
        destination = findViewById(R.id.tvbookdestination);
        time = findViewById(R.id.tvctime);
        price = findViewById(R.id.tvcprice);
        busname = findViewById(R.id.tvcbusname);
        seatname = findViewById(R.id.tvcseatname);

        sdate = getIntent().getStringExtra("keydate");
        sfrom = getIntent().getStringExtra("keyfrom");
        sdestination = getIntent().getStringExtra("keydestination");
        sprice = getIntent().getStringExtra("keyprice");
        stime = getIntent().getStringExtra("keytime");
        snamebus = getIntent().getStringExtra("keynamebus");
        sstatusseat = getIntent().getStringExtra("keystatusseat");
        sseatname = getIntent().getStringExtra("keyseatname");

        date.setText(sdate);
        from.setText(sfrom);
        destination.setText(sdestination);
        time.setText(stime);
        price.setText(sprice);
        busname.setText(snamebus);
        seatname.setText(sseatname);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BookingForm.this, SeatActivity.class));
            }
        });

        continue_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                continue_book();
                Intent intent = new Intent(BookingForm.this, ConfirmationActivity.class);
                intent.putExtra("keyfrom",sfrom);
                intent.putExtra("keydestination",sdestination);
                intent.putExtra("keydate",sdate);
                intent.putExtra("keynamebus",snamebus);
                intent.putExtra("keyprice",sprice);
                intent.putExtra("keytime",stime);
                intent.putExtra("keystatusseat",sstatusseat);
                intent.putExtra("keyseatname",sseatname);
                intent.putExtra("keyemail",semail);
                intent.putExtra("keyname",sname);
                intent.putExtra("keyage",sage);
                intent.putExtra("keyphone",sphone);
                startActivity(intent);
            }
        });
    }

    private void continue_book() {
        semail= email.getText().toString().trim();
        sname= name.getText().toString().trim();
        sage= age.getText().toString().trim();
        sphone= phone.getText().toString().trim();

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
}