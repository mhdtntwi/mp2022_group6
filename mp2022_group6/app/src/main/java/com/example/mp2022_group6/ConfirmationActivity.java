package com.example.mp2022_group6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfirmationActivity extends AppCompatActivity {

    private EditText email, name , age , phone;
    private TextView date, from, destination, time, price, busname, seatname;
    String sdate, sfrom, sdestination, sprice, stime, snamebus, sstatusseat, sseatname, semail, sname, sage, sphone;

    private Button confirmbook;
    private ImageView back;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseBook, databaseSeat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        email = (EditText) findViewById(R.id.tvcemail);
        name = (EditText) findViewById(R.id.tvcname);
        age = (EditText) findViewById(R.id.tvcage);
        phone = (EditText) findViewById(R.id.tvcphone);
        confirmbook = (Button) findViewById(R.id.button_confirm);
        back = (ImageView) findViewById(R.id.back);

        date = findViewById(R.id.tvcdate);
        from = findViewById(R.id.tvcfrom);
        destination = findViewById(R.id.tvcdestination);
        time = findViewById(R.id.tvctime);
        price = findViewById(R.id.tvcprice);
        busname = findViewById(R.id.tvcbusname);
        seatname = findViewById(R.id.tvcseatname);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();


        sdate = getIntent().getStringExtra("keydate");
        sfrom = getIntent().getStringExtra("keyfrom");
        sdestination = getIntent().getStringExtra("keydestination");
        sprice = getIntent().getStringExtra("keyprice");
        stime = getIntent().getStringExtra("keytime");
        snamebus = getIntent().getStringExtra("keynamebus");
        sstatusseat = getIntent().getStringExtra("keystatusseat");
        sseatname = getIntent().getStringExtra("keyseatname");
        semail = getIntent().getStringExtra("keyemail");
        sname = getIntent().getStringExtra("keyname");
        sage = getIntent().getStringExtra("keyage");
        sphone = getIntent().getStringExtra("keyphone");

        date.setText(sdate);
        from.setText(sfrom);
        destination.setText(sdestination);
        time.setText(stime);
        price.setText(sprice);
        busname.setText(snamebus);
        seatname.setText(sseatname);
        email.setText(semail);
        name.setText(sname);
        age.setText(sage);
        phone.setText(sphone);

        email.setEnabled(false);
        name.setEnabled(false);
        age.setEnabled(false);
        phone.setEnabled(false);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ConfirmationActivity.this, SeatActivity.class));
            }
        });

        confirmbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseBook = firebaseDatabase.getReference("Booking Info").child(firebaseAuth.getUid());
                databaseSeat = firebaseDatabase.getReference("Seat Info");

                Book book = new Book(sdate, sfrom, sdestination, sprice, stime, snamebus, sstatusseat, sseatname, semail, sname, sage, sphone);
                databaseBook.setValue(book);

                setstatus();
                finish();
                startActivity(new Intent(ConfirmationActivity.this, MainActivity.class));

            }


        });
    }

    private void setstatus() {
        Seat seat = new Seat();
        if (sseatname.equals("A1")){
            seat.setA1(sstatusseat);
        }else if (sseatname.equals("A2")){
            seat.setA2(sstatusseat);
        }else if (sseatname.equals("A3")){
            seat.setA3(sstatusseat);
        }else if (sseatname.equals("A4")){
            seat.setA4(sstatusseat);
        }else if (sseatname.equals("A5")){
            seat.setA5(sstatusseat);
        }else if (sseatname.equals("A6")){
            seat.setA6(sstatusseat);
        }else if (sseatname.equals("A7")){
            seat.setA7(sstatusseat);
        }else if (sseatname.equals("A8")){
            seat.setA8(sstatusseat);
        }else if (sseatname.equals("A9")){
            seat.setA9(sstatusseat);
        }else if (sseatname.equals("B1")){
            seat.setB1(sstatusseat);
        }else if (sseatname.equals("B2")){
            seat.setB2(sstatusseat);
        }else if (sseatname.equals("B3")){
            seat.setB3(sstatusseat);
        }else if (sseatname.equals("B4")){
            seat.setB4(sstatusseat);
        }else if (sseatname.equals("B5")){
            seat.setB5(sstatusseat);
        }else if (sseatname.equals("B6")){
            seat.setB6(sstatusseat);
        }else if (sseatname.equals("B7")){
            seat.setB7(sstatusseat);
        }else if (sseatname.equals("B8")){
            seat.setB8(sstatusseat);
        }else if (sseatname.equals("B9")){
            seat.setB9(sstatusseat);
        }else if (sseatname.equals("C1")){
            seat.setC1(sstatusseat);
        }else if (sseatname.equals("C2")){
            seat.setC2(sstatusseat);
        }else if (sseatname.equals("C3")){
            seat.setC3(sstatusseat);
        }else if (sseatname.equals("C4")){
            seat.setC4(sstatusseat);
        }else if (sseatname.equals("C5")){
            seat.setC5(sstatusseat);
        }else if (sseatname.equals("C6")){
            seat.setC6(sstatusseat);
        }else if (sseatname.equals("C7")){
            seat.setC7(sstatusseat);
        }else if (sseatname.equals("C8")){
            seat.setC8(sstatusseat);
        }else if (sseatname.equals("C9")){
            seat.setC9(sstatusseat);
        }else if (sseatname.equals("D1")){
            seat.setD1(sstatusseat);
        }else if (sseatname.equals("D2")){
            seat.setD2(sstatusseat);
        }else if (sseatname.equals("D3")){
            seat.setD3(sstatusseat);
        }else if (sseatname.equals("D4")){
            seat.setD4(sstatusseat);
        }else if (sseatname.equals("D5")){
            seat.setD5(sstatusseat);
        }else if (sseatname.equals("D6")){
            seat.setD6(sstatusseat);
        }else if (sseatname.equals("D7")){
            seat.setD7(sstatusseat);
        }else if (sseatname.equals("D8")){
            seat.setD8(sstatusseat);
        }else if (sseatname.equals("D9")){
            seat.setD9(sstatusseat);
        }



        databaseSeat.setValue(seat);

    }
}