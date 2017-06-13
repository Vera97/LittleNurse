package com.example.materialtest.db;

import org.litepal.crud.DataSupport;

public class Doctor extends DataSupport {
    private int id;
    private String name;
    private String password;
    private String phone;
    private String photo;
    private int starLevel;
    private String status;
    private String gender;
    private int age;
    private String title;
    private int workTime;
    private String intro;
    public Doctor(int id, String name, String password, String phone, String photo,
                       int starLevel, String status, String gender, int age, String title, int workTime, String intro){
        this.id = id; this.name = name; this.password = password; this.phone = phone; this.photo = photo; this.starLevel = starLevel; this.status = status;
        this.gender = gender; this.age = age; this.title = title; this.workTime = workTime; this.intro = intro;
    }
    public Doctor(){id=starLevel=age=workTime=0; name=password=phone=photo=status=gender=title=intro=null;}
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto() {return photo;}

    public void setPhoto(String photo) {this.photo = photo;}

    public int getStarLevel() {
        return starLevel;
    }

    public void setStarLevel(int starLevel) {
        this.starLevel = starLevel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getWorkTime() {
        return workTime;
    }

    public void setWorkTime(int workTime) {
        this.workTime = workTime;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}
