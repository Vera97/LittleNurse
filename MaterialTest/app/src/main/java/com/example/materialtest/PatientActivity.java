package com.example.materialtest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.materialtest.myPackage.Patient;
import com.example.materialtest.myPackage.PatientAdapter;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class PatientActivity extends AppCompatActivity {

    private List<Patient> PatientList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_main);

        SharedPreferences pref=getSharedPreferences("data",MODE_PRIVATE);
        String name=pref.getString("account","");
        Log.d("PatientInfoActivity","The current user is "+ name);

        initPatients(); // 初始化病人数据
        PatientAdapter adapter = new PatientAdapter(PatientActivity.this, R.layout.patient_item, PatientList);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Patient p = PatientList.get(position);
                String currentName = p.getName();
                Intent intent = new Intent(PatientActivity.this, PatientInfoActivity.class);
                intent.putExtra("extra_data",currentName);
                startActivity(intent);
            }
        });
    }

    private void initPatients() {
        List<com.example.materialtest.db.Patient>patients= DataSupport.findAll(com.example.materialtest.db.Patient.class);
        for(com.example.materialtest.db.Patient patient:patients){
            int id =patient.getId();
            String ImageName = patient.getPhoto();
            String name=patient.getName();
            int imageID= getImageID(ImageName);
            Patient p = new Patient(id,name,imageID);
            PatientList.add(p);
        }
    }
    public int getImageID(String ImageName){
        Resources res=getResources();
        int picid = res.getIdentifier(ImageName,"drawable",getPackageName());
        return picid;
    }


}
