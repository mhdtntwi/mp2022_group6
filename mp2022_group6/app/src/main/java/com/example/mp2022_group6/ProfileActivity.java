package com.example.mp2022_group6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;

    private String userID;

    private TextView signOut;
    private ImageView backButton;
    private Button profileUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toast.makeText(ProfileActivity.this, "Sebelum back button", Toast.LENGTH_SHORT).show();

        backButton = (ImageView) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, NavigationActivity.class));
            }
        });

        Toast.makeText(ProfileActivity.this, "Sebelum sign out", Toast.LENGTH_SHORT).show();

        signOut = (TextView) findViewById(R.id.signOut);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(ProfileActivity.this, "tengah sign out", Toast.LENGTH_SHORT).show();
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        Toast.makeText(ProfileActivity.this, "tengah dptkan user", Toast.LENGTH_SHORT).show();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        final TextView greetingTextView = (TextView) findViewById(R.id.greeting);
        final TextView fullNameTextView = (TextView) findViewById(R.id.etName);
        final TextView emailTextView = (TextView) findViewById(R.id.etEmail);
        final TextView phoneTextView = (TextView) findViewById(R.id.etPhone);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                Toast.makeText(ProfileActivity.this, "tengah cek database", Toast.LENGTH_SHORT).show();

                if (userProfile != null){
                    Toast.makeText(ProfileActivity.this, "profile not null", Toast.LENGTH_SHORT).show();
                    String fullname = userProfile.fullname;
                    String email = userProfile.email;
                    String phone = userProfile.phone;

                    Toast.makeText(ProfileActivity.this, "dah dpt detail", Toast.LENGTH_SHORT).show();
                    Toast.makeText(ProfileActivity.this, "fullname "+fullname, Toast.LENGTH_SHORT).show();
                    Toast.makeText(ProfileActivity.this, "email "+email, Toast.LENGTH_SHORT).show();
                    Toast.makeText(ProfileActivity.this, "phone "+phone, Toast.LENGTH_SHORT).show();
                    greetingTextView.setText("Welcome, " + fullname + "!");
                    fullNameTextView.setText(fullname);
                    emailTextView.setText(email);
                    phoneTextView.setText(phone);
                    Toast.makeText(ProfileActivity.this, "dah set text", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfileActivity.this, "Something wrong happened!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}