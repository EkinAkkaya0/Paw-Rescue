package com.example.pawrescue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import java.util.ArrayList;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class LostAndFound extends AppCompatActivity {
    private ArrayList<LostPetItem> lostPetList = new ArrayList<>();
    private LostPetAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_and_found);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.pet_image1);
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.pet_image2);

        lostPetList.add(new LostPetItem(bitmap1, "İncir",
                "Abidinpaşa Mahallesi, Mamak/Ankara", "543-954-1968"));
        lostPetList.add(new LostPetItem(bitmap2, "Tarçın",
                "Etlik Mahallesi, Keçiören/Ankara", "505-770-2949"));


            RecyclerView recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new LostPetAdapter(lostPetList);
            recyclerView.setAdapter(adapter);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lost_and_found, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_create_post) {
            Intent intent = new Intent(LostAndFound.this, CreateLostPetPost.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}