package com.example.mp2022_group6;

import androidx.annotation.NonNull;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ConfirmationActivity extends AppCompatActivity {

    private EditText email, name , age , phone;
    private TextView date, from, destination, time, price, busname, seatname;
    String sdate, sfrom, sdestination, sprice, stime, snamebus, sstatusseat, sseatname, semail, sname, sage, sphone;

    String A1, A2, A3, A4, A5, A6, A7, A8, A9;
    String B1, B2, B3, B4, B5, B6, B7, B8, B9;
    String C1, C2, C3, C4, C5, C6, C7, C8, C9;
    String D1, D2, D3, D4, D5, D6, D7, D8, D9;

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
        databaseBook = firebaseDatabase.getReference("Booking Info").child(firebaseAuth.getUid());
        databaseSeat = firebaseDatabase.getReference("Seat Info");


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

        databaseSeat.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Seat seat = snapshot.getValue(Seat.class);
                A1 = seat.getA1();
                A2 = seat.getA2();
                A3 = seat.getA3();
                A4 = seat.getA4();
                A5 = seat.getA5();
                A6 = seat.getA6();
                A7 = seat.getA7();
                A8 = seat.getA8();
                A9 = seat.getA9();

                B1 = seat.getB1();
                B2 = seat.getB2();
                B3 = seat.getB3();
                B4 = seat.getB4();
                B5 = seat.getB5();
                B6 = seat.getB6();
                B7 = seat.getB7();
                B8 = seat.getB8();
                B9 = seat.getB9();

                C1 = seat.getC1();
                C2 = seat.getC2();
                C3 = seat.getC3();
                C4 = seat.getC4();
                C5 = seat.getC5();
                C6 = seat.getC6();
                C7 = seat.getC7();
                C8 = seat.getC8();
                C9 = seat.getC9();

                D1 = seat.getD1();
                D2 = seat.getD2();
                D3 = seat.getD3();
                D4 = seat.getD4();
                D5 = seat.getD5();
                D6 = seat.getD6();
                D7 = seat.getD7();
                D8 = seat.getD8();
                D9 = seat.getD9();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });









        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ConfirmationActivity.this, SeatActivity.class));
            }
        });

        confirmbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


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
            if (A1.equals(sstatusseat)){
                seat.setA1(sstatusseat);
            }else {
                seat.setA1(sstatusseat);
            }
        }else {
            if (A1.equals(sstatusseat)){
                seat.setA1(sstatusseat);
            }else{
                seat.setA1("available");
            }

        }

        if (sseatname.equals("A2")){
            if (A2.equals(sstatusseat)){
                seat.setA2(sstatusseat);
            }else {
                seat.setA2(sstatusseat);
            }
        }else {
            if (A2.equals(sstatusseat)){
                seat.setA2(sstatusseat);
            }else {
                seat.setA2("available");
            }

        }

        if (sseatname.equals("A3")){
            if (A3.equals(sstatusseat)){
                seat.setA3(sstatusseat);
            }else {
                seat.setA3(sstatusseat);
            }
        }else {
            if (A3.equals(sstatusseat)){
                seat.setA3(sstatusseat);
            }else {
                seat.setA3("available");
            }

        }

        if (sseatname.equals("A4")){
            if (A4.equals(sstatusseat)){
                seat.setA4(sstatusseat);
            }else {
                seat.setA4(sstatusseat);
            }
        }else {
            if (A4.equals(sstatusseat)){
                seat.setA4(sstatusseat);
            }else {
                seat.setA4("available");
            }

        }
        if (sseatname.equals("A5")){
            if (A5.equals(sstatusseat)){
                seat.setA5(sstatusseat);
            }else {
                seat.setA5(sstatusseat);
            }
        }else {
            if (A5.equals(sstatusseat)){
                seat.setA5(sstatusseat);
            }else {
                seat.setA5("available");
            }

        }
        if (sseatname.equals("A6")){
            if (A6.equals(sstatusseat)){
                seat.setA6(sstatusseat);
            }else {
                seat.setA6(sstatusseat);
            }
        }else {
            if (A6.equals(sstatusseat)){
                seat.setA6(sstatusseat);
            }else {
                seat.setA6("available");
            }

        }
        if (sseatname.equals("A7")){
            if (A7.equals(sstatusseat)){
                seat.setA7(sstatusseat);
            }else {
                seat.setA7(sstatusseat);
            }
        }else {
            if (A7.equals(sstatusseat)){
                seat.setA7(sstatusseat);
            }else {
                seat.setA7("available");
            }

        }
        if (sseatname.equals("A8")){
            if (A8.equals(sstatusseat)){
                seat.setA8(sstatusseat);
            }else {
                seat.setA8(sstatusseat);
            }
        }else {
            if (A8.equals(sstatusseat)){
                seat.setA8(sstatusseat);
            }else {
                seat.setA8("available");
            }

        }
        if (sseatname.equals("A9")){
            if (A9.equals(sstatusseat)){
                seat.setA9(sstatusseat);
            }else {
                seat.setA9(sstatusseat);
            }
        }else {
            if (A9.equals(sstatusseat)){
                seat.setA9(sstatusseat);
            }else {
                seat.setA9("available");
            }

        }
        if (sseatname.equals("B1")){
            if (B1.equals(sstatusseat)){
                seat.setB1(sstatusseat);
            }else {
                seat.setB1(sstatusseat);
            }
        }else {
            if (B1.equals(sstatusseat)){
                seat.setB1(sstatusseat);
            }else {
                seat.setB1("available");
            }

        }
        if (sseatname.equals("B2")){
            if (B2.equals(sstatusseat)){
                seat.setB2(sstatusseat);
            }else {
                seat.setB2(sstatusseat);
            }
        }else {
            if (B2.equals(sstatusseat)){
                seat.setB2(sstatusseat);
            }else {
                seat.setB2("available");
            }

        }
        if (sseatname.equals("B3")){
            if (B3.equals(sstatusseat)){
                seat.setB3(sstatusseat);
            }else {
                seat.setB3(sstatusseat);
            }
        }else {
            if (B3.equals(sstatusseat)){
                seat.setB3(sstatusseat);
            }else {
                seat.setB3("available");
            }

        }
        if (sseatname.equals("B4")){
            if (B4.equals(sstatusseat)){
                seat.setB4(sstatusseat);
            }else {
                seat.setB4(sstatusseat);
            }
        }else {
            if (B4.equals(sstatusseat)){
                seat.setB4(sstatusseat);
            }else {
                seat.setB4("available");
            }

        }
        if (sseatname.equals("B5")){
            if (B5.equals(sstatusseat)){
                seat.setB5(sstatusseat);
            }else {
                seat.setB5(sstatusseat);
            }
        }else {
            if (B5.equals(sstatusseat)){
                seat.setB5(sstatusseat);
            }else {
                seat.setB5("available");
            }

        }
        if (sseatname.equals("B6")){
            if (B6.equals(sstatusseat)){
                seat.setB6(sstatusseat);
            }else {
                seat.setB6(sstatusseat);
            }
        }else {
            if (B6.equals(sstatusseat)){
                seat.setB6(sstatusseat);
            }else {
                seat.setB6("available");
            }

        }
        if (sseatname.equals("B7")){
            if (B7.equals(sstatusseat)){
                seat.setB7(sstatusseat);
            }else {
                seat.setB7(sstatusseat);
            }
        }else {
            if (B7.equals(sstatusseat)){
                seat.setB7(sstatusseat);
            }else {
                seat.setB7("available");
            }

        }
        if (sseatname.equals("A1")){
            if (A1.equals(sstatusseat)){
                seat.setA1(sstatusseat);
            }else {
                seat.setB8(sstatusseat);
            }
        }else {
            if (A1.equals(sstatusseat)){
                seat.setA1(sstatusseat);
            }else {
                seat.setA1("available");
            }

        }
        if (sseatname.equals("B8")){
            if (B8.equals(sstatusseat)){
                seat.setB8(sstatusseat);
            }else {
                seat.setB8(sstatusseat);
            }
        }else {
            if (B8.equals(sstatusseat)){
                seat.setB8(sstatusseat);
            }else {
                seat.setB8("available");
            }

        }
        if (sseatname.equals("B9")){
            if (B9.equals(sstatusseat)){
                seat.setB9(sstatusseat);
            }else {
                seat.setB9(sstatusseat);
            }
        }else {
            if (B9.equals(sstatusseat)){
                seat.setB9(sstatusseat);
            }else {
                seat.setB9("available");
            }

        }
        if (sseatname.equals("C1")){
            if (C1.equals(sstatusseat)){
                seat.setC1(sstatusseat);
            }else {
                seat.setC1(sstatusseat);
            }
        }else {
            if (C1.equals(sstatusseat)){
                seat.setC1(sstatusseat);
            }else {
                seat.setC1("available");
            }

        }
        if (sseatname.equals("C2")){
            if (C2.equals(sstatusseat)){
                seat.setC2(sstatusseat);
            }else {
                seat.setC2(sstatusseat);
            }
        }else {
            if (C2.equals(sstatusseat)){
                seat.setC2(sstatusseat);
            }else {
                seat.setC2("available");
            }

        }
        if (sseatname.equals("C3")){
            if (C3.equals(sstatusseat)){
                seat.setC3(sstatusseat);
            }else {
                seat.setC3(sstatusseat);
            }
        }else {
            if (C3.equals(sstatusseat)){
                seat.setC3(sstatusseat);
            }else {
                seat.setC3("available");
            }

        }
        if (sseatname.equals("C4")){
            if (C4.equals(sstatusseat)){
                seat.setC4(sstatusseat);
            }else {
                seat.setC4(sstatusseat);
            }
        }else {
            if (C4.equals(sstatusseat)){
                seat.setC4(sstatusseat);
            }else {
                seat.setC4("available");
            }

        }
        if (sseatname.equals("C5")){
            if (C5.equals(sstatusseat)){
                seat.setC5(sstatusseat);
            }else {
                seat.setC5(sstatusseat);
            }
        }else {
            if (C5.equals(sstatusseat)){
                seat.setC5(sstatusseat);
            }else {
                seat.setC5("available");
            }

        }
        if (sseatname.equals("C6")){
            if (C6.equals(sstatusseat)){
                seat.setC6(sstatusseat);
            }else {
                seat.setC6(sstatusseat);
            }
        }else {
            if (C6.equals(sstatusseat)){
                seat.setC6(sstatusseat);
            }else {
                seat.setC6("available");
            }

        }
        if (sseatname.equals("C7")){
            if (C7.equals(sstatusseat)){
                seat.setC7(sstatusseat);
            }else {
                seat.setC7(sstatusseat);
            }
        }else {
            if (C7.equals(sstatusseat)){
                seat.setC7(sstatusseat);
            }else {
                seat.setC7("available");
            }

        }
        if (sseatname.equals("C8")){
            if (C8.equals(sstatusseat)){
                seat.setC8(sstatusseat);
            }else {
                seat.setC8(sstatusseat);
            }
        }else {
            if (C8.equals(sstatusseat)){
                seat.setC8(sstatusseat);
            }else {
                seat.setC8("available");
            }

        }
        if (sseatname.equals("C9")){
            if (C9.equals(sstatusseat)){
                seat.setC9(sstatusseat);
            }else {
                seat.setC9(sstatusseat);
            }
        }else {
            if (C9.equals(sstatusseat)){
                seat.setC9(sstatusseat);
            }else {
                seat.setC9("available");
            }

        }
        if (sseatname.equals("D1")){
            if (D1.equals(sstatusseat)){
                seat.setD1(sstatusseat);
            }else {
                seat.setD1(sstatusseat);
            }
        }else {
            if (D1.equals(sstatusseat)){
                seat.setD1(sstatusseat);
            }else {
                seat.setD1("available");
            }

        }
        if (sseatname.equals("D2")){
            if (D2.equals(sstatusseat)){
                seat.setD2(sstatusseat);
            }else {
                seat.setD2(sstatusseat);
            }
        }else {
            if (D2.equals(sstatusseat)){
                seat.setD2(sstatusseat);
            }else {
                seat.setD2("available");
            }

        }
        if (sseatname.equals("D3")){
            if (D3.equals(sstatusseat)){
                seat.setD3(sstatusseat);
            }else {
                seat.setD3(sstatusseat);
            }
        }else {
            if (D3.equals(sstatusseat)){
                seat.setD3(sstatusseat);
            }else {
                seat.setD3("available");
            }

        }
        if (sseatname.equals("D4")){
            if (D4.equals(sstatusseat)){
                seat.setD4(sstatusseat);
            }else {
                seat.setD4(sstatusseat);
            }
        }else {
            if (D4.equals(sstatusseat)){
                seat.setD4(sstatusseat);
            }else {
                seat.setD4("available");
            }

        }
        if (sseatname.equals("D5")){
            if (D5.equals(sstatusseat)){
                seat.setD5(sstatusseat);
            }else {
                seat.setD5(sstatusseat);
            }
        }else {
            if (D5.equals(sstatusseat)){
                seat.setD5(sstatusseat);
            }else {
                seat.setD5("available");
            }

        }
        if (sseatname.equals("D6")){
            if (D6.equals(sstatusseat)){
                seat.setD6(sstatusseat);
            }else {
                seat.setD6(sstatusseat);
            }
        }else {
            if (D6.equals(sstatusseat)){
                seat.setD6(sstatusseat);
            }else {
                seat.setD6("available");
            }

        }
        if (sseatname.equals("D7")){
            if (D7.equals(sstatusseat)){
                seat.setD7(sstatusseat);
            }else {
                seat.setD7(sstatusseat);
            }
        }else {
            if (D7.equals(sstatusseat)){
                seat.setD7(sstatusseat);
            }else {
                seat.setD7("available");
            }

        }
        if (sseatname.equals("D8")){
            if (D8.equals(sstatusseat)){
                seat.setD8(sstatusseat);
            }else {
                seat.setD8(sstatusseat);
            }
        }else {
            if (D8.equals(sstatusseat)){
                seat.setD8(sstatusseat);
            }else {
                seat.setD8("available");
            }

        }
        if (sseatname.equals("D9")){
            if (D9.equals(sstatusseat)){
                seat.setD9(sstatusseat);
            }else {
                seat.setD9(sstatusseat);
            }
        }else {
            if (D9.equals(sstatusseat)){
                seat.setD9(sstatusseat);
            }else {
                seat.setD9("available");
            }

        }
















//        if (sseatname.equals("A1")){
//            seat.setA1(sstatusseat);
//        }else if (sseatname.equals("A2")){
//            seat.setA2(sstatusseat);
//        }else if (sseatname.equals("A3")){
//            seat.setA3(sstatusseat);
//        }else if (sseatname.equals("A4")){
//            seat.setA4(sstatusseat);
//        }else if (sseatname.equals("A5")){
//            seat.setA5(sstatusseat);
//        }else if (sseatname.equals("A6")){
//            seat.setA6(sstatusseat);
//        }else if (sseatname.equals("A7")){
//            seat.setA7(sstatusseat);
//        }else if (sseatname.equals("A8")){
//            seat.setA8(sstatusseat);
//        }else if (sseatname.equals("A9")){
//            seat.setA9(sstatusseat);
//        }else if (sseatname.equals("B1")){
//            seat.setB1(sstatusseat);
//        }else if (sseatname.equals("B2")){
//            seat.setB2(sstatusseat);
//        }else if (sseatname.equals("B3")){
//            seat.setB3(sstatusseat);
//        }else if (sseatname.equals("B4")){
//            seat.setB4(sstatusseat);
//        }else if (sseatname.equals("B5")){
//            seat.setB5(sstatusseat);
//        }else if (sseatname.equals("B6")){
//            seat.setB6(sstatusseat);
//        }else if (sseatname.equals("B7")){
//            seat.setB7(sstatusseat);
//        }else if (sseatname.equals("B8")){
//            seat.setB8(sstatusseat);
//        }else if (sseatname.equals("B9")){
//            seat.setB9(sstatusseat);
//        }else if (sseatname.equals("C1")){
//            seat.setC1(sstatusseat);
//        }else if (sseatname.equals("C2")){
//            seat.setC2(sstatusseat);
//        }else if (sseatname.equals("C3")){
//            seat.setC3(sstatusseat);
//        }else if (sseatname.equals("C4")){
//            seat.setC4(sstatusseat);
//        }else if (sseatname.equals("C5")){
//            seat.setC5(sstatusseat);
//        }else if (sseatname.equals("C6")){
//            seat.setC6(sstatusseat);
//        }else if (sseatname.equals("C7")){
//            seat.setC7(sstatusseat);
//        }else if (sseatname.equals("C8")){
//            seat.setC8(sstatusseat);
//        }else if (sseatname.equals("C9")){
//            seat.setC9(sstatusseat);
//        }else if (sseatname.equals("D1")){
//            seat.setD1(sstatusseat);
//        }else if (sseatname.equals("D2")){
//            seat.setD2(sstatusseat);
//        }else if (sseatname.equals("D3")){
//            seat.setD3(sstatusseat);
//        }else if (sseatname.equals("D4")){
//            seat.setD4(sstatusseat);
//        }else if (sseatname.equals("D5")){
//            seat.setD5(sstatusseat);
//        }else if (sseatname.equals("D6")){
//            seat.setD6(sstatusseat);
//        }else if (sseatname.equals("D7")){
//            seat.setD7(sstatusseat);
//        }else if (sseatname.equals("D8")){
//            seat.setD8(sstatusseat);
//        }else if (sseatname.equals("D9")){
//            seat.setD9(sstatusseat);
//        }



        databaseSeat.setValue(seat);

    }
}