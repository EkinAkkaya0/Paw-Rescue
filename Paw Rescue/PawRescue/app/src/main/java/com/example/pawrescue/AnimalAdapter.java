package com.example.pawrescue;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalViewHolder> {
    private List<Animal> animalList;

    public AnimalAdapter(List<Animal> animalList) {
        this.animalList = animalList;
    }

    @NonNull
    @Override
    public AnimalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adoption_animal_item, parent, false);
        return new AnimalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalViewHolder holder, int position) {
        Animal animal = animalList.get(position);

        holder.imageViewAnimal.setImageResource(animal.getImageResourceId());
        holder.textAnimalSpecies.setText(animal.getSpecies());
        holder.textAnimalAge.setText(String.valueOf(animal.getAge()));
        holder.textContactNumber.setText(animal.getContactNumber());
    }

    @Override
    public int getItemCount() {
        return animalList.size();
    }
}
