package com.example.pawrescue;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LostPetAdapter extends RecyclerView.Adapter<LostPetViewHolder> {
    private List<LostPetItem> lostPetList;

    public LostPetAdapter(List<LostPetItem> lostPetList) {
        this.lostPetList = lostPetList;
    }

    @NonNull
    @Override
    public LostPetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lost_pet_item, parent, false);
        return new LostPetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LostPetViewHolder holder, int position) {
        LostPetItem currentItem = lostPetList.get(position);
        holder.petImageView.setImageBitmap(currentItem.getImageBitmap());
        holder.nameTextView.setText(currentItem.getPetName());
        holder.locationTextView.setText(currentItem.getLastSeenLocation());
        holder.contactTextView.setText(currentItem.getContactNumber());
    }

    @Override
    public int getItemCount() {
        return lostPetList.size();
    }
}
