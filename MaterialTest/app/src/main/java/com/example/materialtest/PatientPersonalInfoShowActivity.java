package com.example.materialtest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.materialtest.db.Patient;

import org.litepal.crud.DataSupport;

import java.util.List;

import static com.example.materialtest.R.id.patientInfo_confirm;

public class PatientPersonalInfoShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_personal_info_show);

        SharedPreferences pref=getSharedPreferences("data",MODE_PRIVATE);
        String name=pref.getString("account","");
        Log.d("PatientPISActivity","The current user is "+ name);

        List<Patient>patients= DataSupport.where("name=?",name).find(Patient.class);
        for(Patient patient:patients){
            TextView nameText = (TextView) findViewById(R.id.myName);
            nameText.setText(patient.getName());

            if(patient.getGender()==null) {
                TextView genderText = (TextView) findViewById(R.id.myGender);
                genderText.setText("未知");
            }else{
                TextView genderText = (TextView) findViewById(R.id.myGender);
                genderText.setText(patient.getGender());
            }

            if(patient.getAge()==0) {
                TextView ageText = (TextView) findViewById(R.id.myAge);
                ageText.setText("未知");
            }else{
                TextView ageText = (TextView) findViewById(R.id.myAge);
                ageText.setText(""+patient.getAge());
            }

            if(patient.getPhone()==null){
                TextView phoneText=(TextView)findViewById(R.id.myPhone);
                phoneText.setText("未知");
            }else{
                TextView phoneText=(TextView)findViewById(R.id.myPhone);
                phoneText.setText(patient.getPhone());
            }

            if(patient.getHistory()==null){
                TextView introText=(TextView)findViewById(R.id.myHistory);
                introText.setText("未知");
            }else{
                TextView introText=(TextView)findViewById(R.id.myHistory);
                introText.setText(patient.getHistory());
            }

        }

        Button edit = (Button) findViewById(patientInfo_confirm);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientPersonalInfoShowActivity.this, PatientPersonalInfoActivity.class);
                startActivity(intent);
            }
        });
    }
}
