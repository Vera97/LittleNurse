package com.example.materialtest.db;

import org.litepal.crud.DataSupport;

public class Patient extends DataSupport {
    private int id;
    private String name;
    private String password;
    private String phone;
    private String photo;
    private String gender;
    private int age;
    private String history;

    public Patient(){id = age = 0; name = password = phone = photo = gender = history = null;}
    public Patient(int id, String name, String password, String phone, String photo, String gender, int age, String history){
        this.id = id; this.name = name; this.password = password; this.phone = phone; this.photo = photo;
        this.gender = gender; this.age = age; this.history = history;
    }
    public int getId() {
            return id;
        }

    public void setId(int id) {
            this.id = id;
        }

    public String getName() {
            return name;
        }

    public void setName(String name) {this.name = name;}

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

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }
}


