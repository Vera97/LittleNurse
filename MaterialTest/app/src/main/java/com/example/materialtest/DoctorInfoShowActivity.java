package com.example.materialtest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.materialtest.db.Doctor;
import com.example.materialtest.db.Patient;

import org.litepal.crud.DataSupport;

import java.util.List;

import static com.example.materialtest.R.id.doctorInfo_confirm;

public class DoctorInfoShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_info_show);

        SharedPreferences pref=getSharedPreferences("data",MODE_PRIVATE);
        String name=pref.getString("account","");
        Log.d("DoctorInfoShowActivity","The current user is "+ name);

        List<Doctor>doctors= DataSupport.where("name=?",name).find(Doctor.class);
        for(Doctor doctor:doctors){
            TextView nameText = (TextView) findViewById(R.id.myName);
            nameText.setText(doctor.getName());

            if(doctor.getGender()==null) {
                TextView genderText = (TextView) findViewById(R.id.myGender);
                genderText.setText("未知");
            }else{
                TextView genderText = (TextView) findViewById(R.id.myGender);
                genderText.setText(doctor.getGender());
            }

            if(doctor.getAge()==0) {
                TextView ageText = (TextView) findViewById(R.id.myAge);
                ageText.setText("未知");
            }else{
                TextView ageText = (TextView) findViewById(R.id.myAge);
                ageText.setText(""+doctor.getAge());
            }

            if(doctor.getTitle()==null){
                TextView titleText=(TextView)findViewById(R.id.myTitle);
                titleText.setText("未知");
            }else{
                TextView titleText=(TextView)findViewById(R.id.myTitle);
                titleText.setText(doctor.getTitle());
            }

            if(doctor.getWorkTime()==0){
                TextView workTimeText=(TextView)findViewById(R.id.myWorkTime);
                workTimeText.setText("未知");
            }else{
                TextView workTimeText=(TextView)findViewById(R.id.myWorkTime);
                workTimeText.setText(""+doctor.getWorkTime());
            }

            if(doctor.getPhone()==null){
                TextView phoneText=(TextView)findViewById(R.id.myPhone);
                phoneText.setText("未知");
            }else{
                TextView phoneText=(TextView)findViewById(R.id.myPhone);
                phoneText.setText(doctor.getPhone());
            }

            if(doctor.getIntro()==null){
                TextView introText=(TextView)findViewById(R.id.myIntro);
                introText.setText("未知");
            }else{
                TextView introText=(TextView)findViewById(R.id.myIntro);
                introText.setText(doctor.getIntro());
            }

            if(doctor.getStarLevel()==0){
                TextView starText=(TextView)findViewById(R.id.myStar);
                starText.setText("未知");
            }else{
                TextView starText=(TextView)findViewById(R.id.myStar);
                starText.setText(""+doctor.getStarLevel());
            }

        }

        Button edit = (Button) findViewById(doctorInfo_confirm);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorInfoShowActivity.this, DoctorInfoActivity.class);
                startActivity(intent);
            }
        });
    }
}
