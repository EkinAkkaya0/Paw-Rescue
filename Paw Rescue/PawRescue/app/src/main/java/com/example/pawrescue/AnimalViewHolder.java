package com.example.pawrescue;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AnimalViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageViewAnimal;
    public TextView textAnimalSpecies;
    public TextView textAnimalAge;

    public TextView textContactNumber;

    public AnimalViewHolder(@NonNull View itemView) {
        super(itemView);
        imageViewAnimal = itemView.findViewById(R.id.imageAnimal);
        textAnimalSpecies = itemView.findViewById(R.id.textAnimalSpecies);
        textAnimalAge = itemView.findViewById(R.id.textAnimalAge);
        textContactNumber = itemView.findViewById(R.id.contactNumberAdoption);
    }
}
