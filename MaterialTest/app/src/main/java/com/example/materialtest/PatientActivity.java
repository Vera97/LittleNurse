package com.example.materialtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.materialtest.myPackage.Patient;
import com.example.materialtest.myPackage.PatientAdapter;

import java.util.ArrayList;
import java.util.List;

public class PatientActivity extends AppCompatActivity {

    private List<Patient> PatientList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_main);
        initPatients(); // 初始化数据
        PatientAdapter adapter = new PatientAdapter(PatientActivity.this, R.layout.patient_item, PatientList);

        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }

    private void initPatients() {
        for (int i = 0; i < 2; i++) {
            Patient apple = new Patient(1,"艾明", R.drawable.user1);
            PatientList.add(apple);
            Patient banana = new Patient(2,"班柯", R.drawable.user2);
            PatientList.add(banana);
            Patient orange = new Patient(3,"陈佳逸", R.drawable.user3);
            PatientList.add(orange);
            Patient watermelon = new Patient(4,"大黄", R.drawable.user4);
            PatientList.add(watermelon);
            Patient pear = new Patient(5,"亮亮", R.drawable.user5);
            PatientList.add(pear);
            Patient grape = new Patient(6,"多美欣", R.drawable.user6);
            PatientList.add(grape);
            Patient pineapple = new Patient(7,"燕子", R.drawable.user7);
            PatientList.add(pineapple);
            Patient strawberry = new Patient(8,"方浴沂", R.drawable.user8);
            PatientList.add(strawberry);
            Patient cherry = new Patient(9,"陈瑞", R.drawable.user9);
            PatientList.add(cherry);
            Patient mango = new Patient(10,"曼多多", R.drawable.user10);
            PatientList.add(mango);
        }
    }

}
