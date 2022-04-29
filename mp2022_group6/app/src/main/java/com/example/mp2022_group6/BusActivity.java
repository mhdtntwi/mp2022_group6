package com.example.mp2022_group6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class BusActivity extends AppCompatActivity {

    CardView expobus, redbus, rapidbus, citylinerbus, shuttlebus;

    private TextView date, from, destination;
    String sdate, sfrom, sdestination, snamebus, sprice, stime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_bus);

        date = findViewById(R.id.tvdate);
        from = findViewById(R.id.tvfrom);
        destination = findViewById(R.id.tvdestination);

        sdate = getIntent().getStringExtra("keydate");
        sfrom = getIntent().getStringExtra("keyfrom");
        sdestination = getIntent().getStringExtra("keydestination");


        date.setText(sdate);
        from.setText(sfrom);
        destination.setText(sdestination);


        expobus = findViewById(R.id.cardView1);
        redbus = findViewById(R.id.cardView2);
        rapidbus = findViewById(R.id.cardView3);
        citylinerbus = findViewById(R.id.cardView4);
        shuttlebus = findViewById(R.id.cardView5);

        expobus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snamebus = "Expo Bus Service";
                sprice = "40.00";
                stime = "9:00AM - 12:30PM";
                nextPage();
            }


        });

        redbus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snamebus = "Red Bus Service";
                sprice = "35.00";
                stime = "10:00AM - 2:00PM";
                nextPage();
            }
        });

        rapidbus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snamebus = "RapidKL Service";
                sprice = "27.00";
                stime = "12:15PM - 3:30PM";
                nextPage();
            }
        });

        citylinerbus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snamebus = "CityLiner Service";
                sprice = "38.00";
                stime = "3:00PM - 5:45PM";
                nextPage();
            }
        });

        shuttlebus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snamebus = "Shuttle Bus Service";
                sprice = "42.00";
                stime = "7:00PM - 10:00PM";
                nextPage();
            }
        });

    }

    private void nextPage() {
        Intent intent = new Intent(BusActivity.this, SeatActivity.class);
        intent.putExtra("keyfrom",sfrom);
        intent.putExtra("keydestination",sdestination);
        intent.putExtra("keydate",sdate);
        intent.putExtra("keynamebus",snamebus);
        intent.putExtra("keyprice",sprice);
        intent.putExtra("keytime",stime);
        startActivity(intent);
    }
}