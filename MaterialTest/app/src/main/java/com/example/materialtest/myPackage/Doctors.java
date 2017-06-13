package com.example.materialtest.myPackage;


public class Doctors {
    private int id;
    private String name;
    private int imageId;

    public Doctors(int id, String name, int imageId){
        this.id = id;
        this.name = name;
        this.imageId = imageId;
    }
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public int getImageId(){
        return imageId;
    }
}
