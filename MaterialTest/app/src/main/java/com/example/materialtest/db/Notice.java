package com.example.materialtest.db;


import org.litepal.crud.DataSupport;

public class Notice extends DataSupport {
    private int id;
    private int doctor_id;
    private String time;
    private String content;

    public Notice(){id=doctor_id=0;time=content=null;}
    public Notice(int id, int doctor_id, String time, String content){this.id = id; this.doctor_id = doctor_id; this.time = time; this.content = content;}
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
