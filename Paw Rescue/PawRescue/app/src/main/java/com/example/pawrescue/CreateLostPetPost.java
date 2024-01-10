package com.example.pawrescue;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class CreateLostPetPost extends AppCompatActivity {

    private static final int REQUEST_IMAGE_PICK = 1;
    private static final int REQUEST_PERMISSION = 2;

    private ImageView petImageView;
    private EditText nameEditText;
    private EditText locationEditText;
    private EditText contactEditText;
    private LostPetAdapter adapter;

    private ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_lost_pet_post);

        petImageView = findViewById(R.id.petImageView);
        nameEditText = findViewById(R.id.nameEditText);
        locationEditText = findViewById(R.id.locationEditText);
        contactEditText = findViewById(R.id.contactEditText);
        Button saveButton = findViewById(R.id.saveButton);

        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                this::handleImagePickResult
        );

        petImageView.setOnClickListener(v -> requestPermissionAndPickImage());

        saveButton.setOnClickListener(view -> {
            String petName = nameEditText.getText().toString();
            String lastSeenLocation = locationEditText.getText().toString();
            String contactNumber = contactEditText.getText().toString();
            Bitmap imageBitmap = ((BitmapDrawable) petImageView.getDrawable()).getBitmap();

            if(petName != null && lastSeenLocation != null && contactNumber != null && imageBitmap != null){
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();

                Intent intent = new Intent(CreateLostPetPost.this, LostAndFound.class);
                intent.putExtra("newPost", byteArray);
                intent.putExtra("petName", petName);
                intent.putExtra("lastSeenLocation", lastSeenLocation);
                intent.putExtra("contactNumber", contactNumber);
                startActivity(intent);

                System.out.println("Data sent successfully");
                finish();
            }else{
                System.out.println("something is null");
            }
        });
    }

    private void requestPermissionAndPickImage() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_PERMISSION);
        } else {
            openGallery();
        }
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activityResultLauncher.launch(intent);
    }

    private void handleImagePickResult(ActivityResult result) {
        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(
                        getContentResolver(), result.getData().getData());
                petImageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            } else {
                Toast.makeText(this, "Permission denied. Cannot pick image.", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
