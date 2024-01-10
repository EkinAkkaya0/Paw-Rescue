package com.example.pawrescue;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.example.pawrescue.databinding.ActivitySignInBinding;
import com.example.pawrescue.databinding.ActivityUserProfileBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserProfile extends AppCompatActivity {

    private ActivityUserProfileBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        binding = ActivityUserProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String userID = mAuth.getCurrentUser().getUid();
        SQLite dbHelper = new SQLite(this);

        Cursor cursor = dbHelper.retrieveUserData(userID);

        if (cursor != null && cursor.moveToFirst()) {
            int nameColumnIndex = cursor.getColumnIndex(SQLite.COLUMN_NAME);
            int surnameColumnIndex = cursor.getColumnIndex(SQLite.COLUMN_SURNAME);

            if (nameColumnIndex >= 0 && surnameColumnIndex >= 0) {
                String retrievedName = cursor.getString(nameColumnIndex);
                String retrievedSurname = cursor.getString(surnameColumnIndex);
                binding.userNameTextview.setText(retrievedName.toUpperCase());
                binding.userSurnameTextview.setText(retrievedSurname.toUpperCase());
            } else {
                System.out.println("Error: Invalid column index.");
            }

            cursor.close();
        } else {
            System.out.println("Error retrieving user data.");
        }

        binding.deleteProfileButton.setOnClickListener(view -> {
            FirebaseUser currentUser = mAuth.getCurrentUser();
            if (currentUser != null) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Are you sure about deleting your account? This operation cannot be reversed.");

                builder.setPositiveButton("Yes", (dialog, which) -> {
                    currentUser.delete()
                            .addOnCompleteListener(this, task -> {
                                if (task.isSuccessful()) {
                                    System.out.println("User profile deleted from Firebase.");
                                    boolean success = dbHelper.deleteUserData(userID);
                                    if (success) {
                                        System.out.println("User data deleted successfully from SQLite.");
                                        Intent intent = new Intent(this, SignUp.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        System.out.println("Error deleting user data from SQLite.");
                                    }
                                } else {
                                    System.out.println("Error deleting user profile from Firebase.");
                                }
                            });
                });
                builder.setNegativeButton("No", (dialog, which) -> {
                    dialog.dismiss();
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }
}