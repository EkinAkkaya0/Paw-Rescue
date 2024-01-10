package com.example.pawrescue;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class AdoptionCenter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adoption_center);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewAnimals);
        List<Animal> animalList = new ArrayList<>();
        animalList.add(new Animal("Dog", 2, R.drawable.pet_image2, "+905439541968"));
        animalList.add(new Animal("Cat", 1, R.drawable.pet_image1, "+905439541968"));

        AnimalAdapter animalAdapter = new AnimalAdapter(animalList);
        recyclerView.setAdapter(animalAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}