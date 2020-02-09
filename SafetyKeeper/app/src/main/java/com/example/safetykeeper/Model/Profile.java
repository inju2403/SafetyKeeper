package com.example.safetykeeper.Model;

public class Profile {
    String name;
    int image;

    public Profile(int image, String name){
        this.image = image;
        this.name = name;
    }
    public int getImage(){
        return image;
    }
    public String getName(){
        return name;
    }
}
