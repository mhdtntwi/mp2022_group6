package com.example.mp2022_group6;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;


public class SeatActivity extends AppCompatActivity {

    private TextView date, from, destination, time, price, busname;
    String sdate, sfrom, sdestination, sprice, stime, snamebus, sstatusseat, sseatname;

    private ImageButton A1, A2, A3, A4, A5, A6, A7, A8, A9;
    private ImageButton B1, B2, B3, B4, B5, B6, B7, B8, B9;
    private ImageButton C1, C2, C3, C4, C5, C6, C7, C8, C9;
    private ImageButton D1, D2, D3, D4, D5, D6, D7, D8, D9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_seat);

        date = findViewById(R.id.tvbookdate);
        from = findViewById(R.id.tvseatfrom);
        destination = findViewById(R.id.tvseatdestination);
        time = findViewById(R.id.tvbooktime);
        price = findViewById(R.id.tvbookprice);
        busname = findViewById(R.id.tvbookbusname);

        seatUIViews();


        sdate = getIntent().getStringExtra("keydate");
        sfrom = getIntent().getStringExtra("keyfrom");
        sdestination = getIntent().getStringExtra("keydestination");
        snamebus = getIntent().getStringExtra("keynamebus");
        sprice = getIntent().getStringExtra("keyprice");
        stime = getIntent().getStringExtra("keytime");

        date.setText(sdate);
        from.setText(sfrom);
        destination.setText(sdestination);
        busname.setText(snamebus);
        price.setText("RM "+sprice);
        time.setText(stime);

        A1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sstatusseat = "booked";
                sseatname = "A1";
                nextpage();
            }
        });
        A2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sstatusseat = "booked";
                sseatname = "A2";
                nextpage();
            }
        });
        A3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sstatusseat = "booked";
                sseatname = "A3";
                nextpage();
            }
        });
        A4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sstatusseat = "booked";
                sseatname = "A4";
                nextpage();
            }
        });
        A5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sstatusseat = "booked";
                sseatname = "A5";
                nextpage();
            }
        });
        A6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sstatusseat = "booked";
                sseatname = "A6";
                nextpage();
            }
        });
        A7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sstatusseat = "booked";
                sseatname = "A7";
                nextpage();
            }
        });
        A8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sstatusseat = "booked";
                sseatname = "A8";
                nextpage();
            }
        });
        A9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sstatusseat = "booked";
                sseatname = "A9";
                nextpage();
            }
        });

        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sstatusseat = "booked";
                sseatname = "B1";
                nextpage();
            }
        });
        B2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sstatusseat = "booked";
                sseatname = "B2";
                nextpage();
            }
        });
        B3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sstatusseat = "booked";
                sseatname = "B3";
                nextpage();
            }
        });
        B4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sstatusseat = "booked";
                sseatname = "B4";
                nextpage();
            }
        });
        B5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sstatusseat = "booked";
                sseatname = "B5";
                nextpage();
            }
        });
        B6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sstatusseat = "booked";
                sseatname = "B6";
                nextpage();
            }
        });
        B7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sstatusseat = "booked";
                sseatname = "B7";
                nextpage();
            }
        });
        B8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sstatusseat = "booked";
                sseatname = "B8";
                nextpage();
            }
        });
        B9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sstatusseat = "booked";
                sseatname = "B9";
                nextpage();
            }
        });

        C1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sstatusseat = "booked";
                sseatname = "C1";
                nextpage();
            }
        });
        C2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sstatusseat = "booked";
                sseatname = "C2";
                nextpage();
            }
        });
        C3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sstatusseat = "booked";
                sseatname = "C3";
                nextpage();
            }
        });
        C4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sstatusseat = "booked";
                sseatname = "C4";
                nextpage();
            }
        });
        C5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sstatusseat = "booked";
                sseatname = "C5";
                nextpage();
            }
        });
        C6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sstatusseat = "booked";
                sseatname = "C6";
                nextpage();
            }
        });
        C7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sstatusseat = "booked";
                sseatname = "C7";
                nextpage();
            }
        });
        C8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sstatusseat = "booked";
                sseatname = "C8";
                nextpage();
            }
        });
        C9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sstatusseat = "booked";
                sseatname = "C9";
                nextpage();
            }
        });

        D1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sstatusseat = "booked";
                sseatname = "D1";
                nextpage();
            }
        });
        D2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sstatusseat = "booked";
                sseatname = "D2";
                nextpage();
            }
        });
        D3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sstatusseat = "booked";
                sseatname = "D3";
                nextpage();
            }
        });
        D4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sstatusseat = "booked";
                sseatname = "D4";
                nextpage();
            }
        });
        D5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sstatusseat = "booked";
                sseatname = "D5";
                nextpage();
            }
        });
        D6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sstatusseat = "booked";
                sseatname = "D6";
                nextpage();
            }
        });
        D7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sstatusseat = "booked";
                sseatname = "D7";
                nextpage();
            }
        });
        D8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sstatusseat = "booked";
                sseatname = "D8";
                nextpage();
            }
        });
        D9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sstatusseat = "booked";
                sseatname = "D9";
                nextpage();
            }
        });


    }

    private void seatUIViews() {
        A1 = findViewById(R.id.A1);
        A2 = findViewById(R.id.A2);
        A3 = findViewById(R.id.A3);
        A4 = findViewById(R.id.A4);
        A5 = findViewById(R.id.A5);
        A6 = findViewById(R.id.A6);
        A7 = findViewById(R.id.A7);
        A8 = findViewById(R.id.A8);
        A9 = findViewById(R.id.A9);

        B1 = findViewById(R.id.B1);
        B2 = findViewById(R.id.B2);
        B3 = findViewById(R.id.B3);
        B4 = findViewById(R.id.B4);
        B5 = findViewById(R.id.B5);
        B6 = findViewById(R.id.B6);
        B7 = findViewById(R.id.B7);
        B8 = findViewById(R.id.B8);
        B9 = findViewById(R.id.B9);

        C1 = findViewById(R.id.C1);
        C2 = findViewById(R.id.C2);
        C3 = findViewById(R.id.C3);
        C4 = findViewById(R.id.C4);
        C5 = findViewById(R.id.C5);
        C6 = findViewById(R.id.C6);
        C7 = findViewById(R.id.C7);
        C8 = findViewById(R.id.C8);
        C9 = findViewById(R.id.C9);

        D1 = findViewById(R.id.D1);
        D2 = findViewById(R.id.D2);
        D3 = findViewById(R.id.D3);
        D4 = findViewById(R.id.D4);
        D5 = findViewById(R.id.D5);
        D6 = findViewById(R.id.D6);
        D7 = findViewById(R.id.D7);
        D8 = findViewById(R.id.D8);
        D9 = findViewById(R.id.D9);

    }

    private void nextpage() {
        Intent intent = new Intent(SeatActivity.this, BookingForm.class);
        intent.putExtra("keyfrom",sfrom);
        intent.putExtra("keydestination",sdestination);
        intent.putExtra("keydate",sdate);
        intent.putExtra("keynamebus",snamebus);
        intent.putExtra("keyprice",sprice);
        intent.putExtra("keytime",stime);
        intent.putExtra("keystatusseat",sstatusseat);
        intent.putExtra("keyseatname",sseatname);
        startActivity(intent);
    }
}