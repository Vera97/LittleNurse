package com.example.materialtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
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
        initPatients(); // 初始化水果数据
        PatientAdapter adapter = new PatientAdapter(PatientActivity.this, R.layout.patient_item, PatientList);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Patient Patient = PatientList.get(position);
                Intent intent = new Intent(PatientActivity.this, PatientInfoActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initPatients() {
        for (int i = 0; i < 2; i++) {
            Patient apple = new Patient(1,"Apple", R.drawable.apple_pic);
            PatientList.add(apple);
            Patient banana = new Patient(2,"Banana", R.drawable.banana_pic);
            PatientList.add(banana);
            Patient orange = new Patient(3,"Orange", R.drawable.orange_pic);
            PatientList.add(orange);
            Patient watermelon = new Patient(4,"Watermelon", R.drawable.watermelon_pic);
            PatientList.add(watermelon);
            Patient pear = new Patient(5,"Pear", R.drawable.pear_pic);
            PatientList.add(pear);
            Patient grape = new Patient(6,"Grape", R.drawable.grape_pic);
            PatientList.add(grape);
            Patient pineapple = new Patient(7,"Pineapple", R.drawable.pineapple_pic);
            PatientList.add(pineapple);
            Patient strawberry = new Patient(8,"Strawberry", R.drawable.strawberry_pic);
            PatientList.add(strawberry);
            Patient cherry = new Patient(9,"Cherry", R.drawable.cherry_pic);
            PatientList.add(cherry);
            Patient mango = new Patient(10,"Mango", R.drawable.mango_pic);
            PatientList.add(mango);
        }
    }

}
