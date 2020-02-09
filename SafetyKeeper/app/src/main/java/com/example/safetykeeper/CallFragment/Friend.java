package com.example.safetykeeper.CallFragment;

public class Friend {
    private String name;
    private String id;
    private String imageUrl;
    private String phoneNumber;

    public Friend(String name, String id, String imageUrl, String phoneNumber) {
        this.name = name;
        this.id = id;
        this.imageUrl = imageUrl;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
