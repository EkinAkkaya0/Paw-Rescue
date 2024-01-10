package com.example.pawrescue;

import android.graphics.Bitmap;

import java.io.Serializable;

public class LostPetItem implements Serializable {
    private Bitmap imageResource;
    private String petName;
    private String lastSeenLocation;
    private String contactNumber;

    public LostPetItem(Bitmap imageResource, String petName, String lastSeenLocation, String contactNumber) {
        this.imageResource = imageResource;
        this.petName = petName;
        this.lastSeenLocation = lastSeenLocation;
        this.contactNumber = contactNumber;
    }

    public Bitmap getImageBitmap() {
        return imageResource;
    }

    public String getPetName() {
        return petName;
    }

    public String getLastSeenLocation() {
        return lastSeenLocation;
    }

    public String getContactNumber() {
        return contactNumber;
    }
}
