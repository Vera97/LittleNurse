package com.example.materialtest.db;

/**
 * Created by whjth on 2017/6/13.
 */
import org.litepal.crud.DataSupport;

import java.sql.Blob;

public class Album extends DataSupport{
    String p_name;
    String d_name;
    int photo_id;
    Blob photo;
    public Album(String p_name, String d_name,int photo_id, Blob photo){
        this.p_name = p_name;
        this.d_name = d_name;
        this.photo_id = photo_id;
        this.photo = photo;
    };
    public Album(){}
    public String getP_name(){return p_name;}
    public String getD_name(){return d_name;}
    public int getPhoto_id(){return photo_id;}
    public Blob getPhoto(){return photo;}
}
