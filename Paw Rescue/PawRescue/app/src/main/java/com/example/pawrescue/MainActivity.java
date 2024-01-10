package com.example.pawrescue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.pawrescue.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        binding.btnLostAndFound.setOnClickListener(view -> {
            Intent intent = new Intent(this, LostAndFound.class);
            startActivity(intent);
        });

        binding.btnRescueAndFeed.setOnClickListener(view -> {
            Intent intent = new Intent(this, RescueAndFeed.class);
            startActivity(intent);
        });

        binding.btnAdoption.setOnClickListener(view -> {
            Intent intent = new Intent(this, AdoptionCenter.class);
            startActivity(intent);
        });


        binding.btnDonation.setOnClickListener(view -> {
            Intent intent = new Intent(this, DonationCenter.class);
            startActivity(intent);
        });

        binding.signOutButton.setOnClickListener(view -> {
            mAuth.signOut();
            Intent intent = new Intent(this, SignIn.class);
            startActivity(intent);
            finish();
        });

        binding.mainActivityUserTextView.setOnClickListener(view -> {
            Intent intent = new Intent(this, UserProfile.class);
            startActivity(intent);
        });

        binding.emergencyCallButton.setOnClickListener(view -> {

        });

    }
}