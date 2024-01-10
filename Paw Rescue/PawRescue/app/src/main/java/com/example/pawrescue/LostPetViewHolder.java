package com.example.pawrescue;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LostPetViewHolder extends RecyclerView.ViewHolder {
    ImageView petImageView;
    TextView nameTextView;
    TextView locationTextView;
    TextView contactTextView;

    public LostPetViewHolder(@NonNull View itemView) {
        super(itemView);
        petImageView = itemView.findViewById(R.id.petImageView);
        nameTextView = itemView.findViewById(R.id.nameTextView);
        locationTextView = itemView.findViewById(R.id.locationTextView);
        contactTextView = itemView.findViewById(R.id.contactTextView);
    }
}
