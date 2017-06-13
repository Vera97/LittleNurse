package com.example.materialtest.db;

import org.litepal.crud.DataSupport;

public class Prescription extends DataSupport {
    private int id;
    private String date;
    private int doctor_id;
    private int patient_id;
    private String content;
    private String photo;

    public Prescription(){id = patient_id = doctor_id = 0; date = content = photo = null;}

    public Prescription(int id, String date, int doctor_id, int patient_id, String content, String photo){
        this.id = id; this.date = date; this.doctor_id = doctor_id; this.patient_id = patient_id; this.content = content; this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPhoto() {return photo;}

    public void setPhoto(String photo) {this.photo = photo;}
}
