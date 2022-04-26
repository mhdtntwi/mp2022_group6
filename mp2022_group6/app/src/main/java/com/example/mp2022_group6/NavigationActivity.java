package com.example.mp2022_group6;

import android.app.DatePickerDialog;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class NavigationActivity extends AppCompatActivity {

    EditText etDate;

    private FirebaseUser user;
    private DatabaseReference reference;

    private String userID, sdate, sfrom, sdestination;
    private Button userProfileButton, search_buses;

    String [] destination;

    Spinner spinner_From, spinner_Destination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_navigation);

        destination = getResources().getStringArray(R.array.destination_array);
        spinner_From = findViewById(R.id.spinnerFrom);
        spinner_Destination = findViewById(R.id.spinnerDestination);

        ArrayAdapter<String> adapterfrom = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, destination);
        ArrayAdapter<String> adapterdestination = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, destination);

        spinner_From.setAdapter(adapterfrom);
        spinner_Destination.setAdapter(adapterdestination);

        spinner_From.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int index = adapterView.getSelectedItemPosition();
                sfrom = destination[index];
                Toast.makeText(getBaseContext(), "From "+sfrom, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner_Destination.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int index = adapterView.getSelectedItemPosition();
                sdestination = destination[index];
                Toast.makeText(getBaseContext(), "Destination "+sdestination, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        etDate = findViewById(R.id.etDate);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        NavigationActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        sdate = day+"/"+month+"/"+year;
                        etDate.setText(sdate);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        final TextView greetingTextView = (TextView) findViewById(R.id.textView);


        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null){
                    String fullname = userProfile.fullname;

                    greetingTextView.setText("Hey, " + fullname + "");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(NavigationActivity.this, "Something wrong happened!", Toast.LENGTH_SHORT).show();
            }
        });

        //Open ProfileActivity
        userProfileButton = (Button) findViewById(R.id.userProfileButton);
        userProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(NavigationActivity.this, "Dalam button profile", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(NavigationActivity.this, ProfileActivity.class));
                Toast.makeText(NavigationActivity.this, "dah tekan", Toast.LENGTH_SHORT).show();
            }
        });

        //To BusActivity
        search_buses = (Button) findViewById(R.id.search_buses);
        search_buses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NavigationActivity.this, BusActivity.class);
                intent.putExtra("keyfrom",sfrom);
                intent.putExtra("keydestination",sdestination);
                intent.putExtra("keydate",sdate);
                startActivity(intent);
            }
        });

       //Spinner spinner1 = findViewById(R.id.spinner1);
        //spinner1.setOnItemSelectedListener(this);

    }

    //@Override
    //public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        //Toast.makeText(this, adapterView.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
    //}

    //@Override
    //public void onNothingSelected(AdapterView<?> adapterView) {

    //}
}