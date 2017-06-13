package com.example.materialtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.materialtest.db.Doctor;
import com.example.materialtest.db.Patient;

import org.litepal.crud.DataSupport;

import java.util.List;

public class DoctorsInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_info);

        Intent intent=getIntent();
        String currentName=intent.getStringExtra("extra_data");

        List<Doctor>doctors= DataSupport.where("name=?",currentName).find(Doctor.class);
        for(Doctor doctor:doctors) {
                TextView nameText = (TextView) findViewById(R.id.nameText);
                nameText.setText(doctor.getName());

                if(doctor.getGender()==null) {
                    TextView genderText = (TextView) findViewById(R.id.genderText);
                    genderText.setText("未知");
                }else{
                    TextView genderText = (TextView) findViewById(R.id.genderText);
                    genderText.setText(doctor.getGender());
                }

           if(doctor.getAge()==0) {
                TextView ageText = (TextView) findViewById(R.id.ageText);
                ageText.setText("未知");
           }else{
                TextView ageText = (TextView) findViewById(R.id.ageText);
                String age=Integer.toString(doctor.getAge());
                ageText.setText(age);
           }

            TextView phoneText = (TextView) findViewById(R.id.phoneText);
            phoneText.setText(doctor.getPhone());

            if(doctor.getIntro()==null) {
                TextView historyText = (TextView) findViewById(R.id.historyText);
                historyText.setText("此医生没有简介");
            }else{
                TextView historyText = (TextView) findViewById(R.id.historyText);
                historyText.setText(doctor.getIntro());
            }
        }

    }
}
