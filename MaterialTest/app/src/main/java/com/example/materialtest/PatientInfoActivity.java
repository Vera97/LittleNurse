package com.example.materialtest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.materialtest.db.Patient;

import org.litepal.crud.DataSupport;

import java.util.List;

import static java.sql.Types.NULL;

public class PatientInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_info);

        Intent intent=getIntent();
        String currentName=intent.getStringExtra("extra_data");

        List<Patient>patients= DataSupport.where("name=?",currentName).find(Patient.class);
        for(Patient patient:patients) {
                TextView nameText = (TextView) findViewById(R.id.nameText);
                nameText.setText(patient.getName());

                if(patient.getGender()==null) {
                    TextView genderText = (TextView) findViewById(R.id.genderText);
                    genderText.setText("未知");
                }else{
                    TextView genderText = (TextView) findViewById(R.id.genderText);
                    genderText.setText(patient.getGender());
                }

           if(patient.getAge()==0) {
                TextView ageText = (TextView) findViewById(R.id.ageText);
                ageText.setText("未知");
           }else{
                TextView ageText = (TextView) findViewById(R.id.ageText);
                String age=Integer.toString(patient.getAge());
                ageText.setText(age);
           }

            TextView phoneText = (TextView) findViewById(R.id.phoneText);
            phoneText.setText(patient.getPhone());

            if(patient.getHistory()==null) {
                TextView historyText = (TextView) findViewById(R.id.historyText);
                historyText.setText("此病人没有病史");
            }else{
                TextView historyText = (TextView) findViewById(R.id.historyText);
                historyText.setText(patient.getHistory());
            }
        }

    }
}
