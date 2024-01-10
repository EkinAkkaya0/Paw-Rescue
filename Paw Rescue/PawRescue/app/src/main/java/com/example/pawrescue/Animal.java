package com.example.pawrescue;

public class Animal {
    private String species;
    private int age;
    private String contactNumber;
    private int imageResourceId;

    public Animal(String species, int age, int imageResourceId, String contactNumber) {
        this.contactNumber = contactNumber;
        this.species = species;
        this.age = age;
        this.imageResourceId = imageResourceId;
    }

    public String getSpecies() {
        return species;
    }

    public int getAge() {
        return age;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public String getContactNumber(){
        return contactNumber;
    }
}
