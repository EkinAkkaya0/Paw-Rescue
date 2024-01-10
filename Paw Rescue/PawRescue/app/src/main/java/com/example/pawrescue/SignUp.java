package com.example.pawrescue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;
import android.util.Log;
import com.example.pawrescue.SQLite;

import com.example.pawrescue.databinding.ActivitySignUpBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUp extends AppCompatActivity {
    private ActivitySignUpBinding binding;
    private final String errorMessage = "Please enter all required fields and create a strong password.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        binding.alreadySignedUpBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, SignIn.class);
            startActivity(intent);
            finish();
        });


        binding.signUpButton.setOnClickListener(view -> {
            String name = binding.nameOfUser.getText().toString();
            String surname = binding.surnameOfUser.getText().toString();
            String email = binding.signUpEmail.getText().toString();
            String password = binding.signUpPassword.getText().toString();

            if(email.isEmpty() || password.isEmpty() || !isPasswordStrong(password) || name.isEmpty() || surname.isEmpty()){
                Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
            }else{
                try{
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(this, task -> {
                                if(task.isSuccessful()){

                                    Toast.makeText(getApplicationContext(), "User Signed Up!",
                                            Toast.LENGTH_SHORT).show();
                                    String userID = mAuth.getCurrentUser().getUid();
                                    SQLite dbHelper = new SQLite(this);
                                    boolean success = dbHelper.saveUserData(userID, name, surname);

                                    if (success) {
                                        System.out.println("User details saved to SQLite database successfully.");
                                        Cursor cursor = dbHelper.retrieveUserData(userID);

                                        if (cursor != null && cursor.moveToFirst()) {
                                            int nameColumnIndex = cursor.getColumnIndex(SQLite.COLUMN_NAME);
                                            int surnameColumnIndex = cursor.getColumnIndex(SQLite.COLUMN_SURNAME);

                                            if (nameColumnIndex >= 0 && surnameColumnIndex >= 0) {
                                                String retrievedName = cursor.getString(nameColumnIndex);
                                                String retrievedSurname = cursor.getString(surnameColumnIndex);

                                                System.out.println("Retrieved user data:");
                                                System.out.println("Name: " + retrievedName);
                                                System.out.println("Surname: " + retrievedSurname);
                                            } else {
                                                System.out.println("Error: Invalid column index.");
                                            }

                                            cursor.close();
                                        } else {
                                            System.out.println("Error retrieving user data.");
                                        }
                                    } else {
                                        System.out.println("Error saving user details to SQLite database.");
                                    }
                                    Intent intent = new Intent(SignUp.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }else{
                                    Toast.makeText(getApplicationContext(), "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                }catch(Exception e) {
                    Toast.makeText(getApplicationContext(), e.getLocalizedMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }
        });



    }

    protected void onStart(){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            System.out.println("User already signed in. Starting MainActivity from onStart.");
            Intent intent = new Intent(SignUp.this, MainActivity.class);
            startActivity(intent);
            finish();
        }else{
            System.out.println("No user signed in. Proceeding with SignUp activity.");
        }
        super.onStart();
    }
    private boolean isPasswordStrong(String password) {
        String uppercaseRegex = "[A-Z]";
        String digitRegex = "\\d";
        String symbolRegex = "[^A-Za-z0-9]";

        boolean lengthEnough = password.length() >= 8;
        boolean hasUppercase = password.matches(".*" + uppercaseRegex + ".*");
        boolean hasDigit = password.matches(".*" + digitRegex + ".*");
        boolean hasSymbol = password.matches(".*" + symbolRegex + ".*");

        return hasUppercase && hasDigit && hasSymbol && lengthEnough;
    }

}