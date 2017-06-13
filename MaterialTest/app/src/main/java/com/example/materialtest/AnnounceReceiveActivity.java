package com.example.materialtest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.materialtest.db.Doctor;
import com.example.materialtest.db.Notice;

import org.litepal.crud.DataSupport;

import java.util.Date;
import java.util.List;

public class AnnounceReceiveActivity extends AppCompatActivity {

    private EditText newNotice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announce_receive);

        SharedPreferences pref=getSharedPreferences("data",MODE_PRIVATE);
        final String currentName=pref.getString("account","");
        Log.d("AnnounceReceiveActivity","The current user is "+ currentName);

        //int id=getDoctorID(currentName);
        //Notice lastNotice=DataSupport.where("doctor_id=?",""+id).findLast(Notice.class);
        Notice lastNotice=DataSupport.findLast(Notice.class);
        String last=lastNotice.getContent();
        if(last==null) {
            TextView noticeText = (TextView) findViewById(R.id.lastNoticeContent);
            noticeText.setText("当前没有公告");
        }else{
            TextView genderText = (TextView) findViewById(R.id.lastNoticeContent);
            genderText.setText(last);
        }

    }

    /*public int getDoctorID(String name){
        List<Doctor>doctors= DataSupport.findAll(Doctor.class);
        for(Doctor doctor:doctors){
            if(doctor.getName().equals(name))
                return doctor.getId();
        }
        return 0;
    }*/
}

