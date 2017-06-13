package com.example.materialtest.myPackage;


public class Prescriptions {
    private int id;
    private String date;
    private int imageId;

    public Prescriptions(int id, String date, int imageId){
        this.id = id;
        this.date= date;
        this.imageId = imageId;
    }
    public int getId(){
        return id;
    }
    public String getDate(){
        return date;
    }
    public int getImageId(){
        return imageId;
    }
}
