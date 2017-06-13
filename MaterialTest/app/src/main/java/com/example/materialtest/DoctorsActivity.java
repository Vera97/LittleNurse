package com.example.materialtest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.materialtest.db.Doctor;
import com.example.materialtest.myPackage.Doctors;
import com.example.materialtest.myPackage.DoctorsAdapter;
import com.example.materialtest.myPackage.Patient;
import com.example.materialtest.myPackage.PatientAdapter;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class DoctorsActivity extends AppCompatActivity {

    private List<Doctors> DoctorsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctors_main);

        SharedPreferences pref=getSharedPreferences("data",MODE_PRIVATE);
        String name=pref.getString("account","");
        Log.d("DoctorsInfoActivity","The current user is "+ name);

        initDoctors(); // 初始化医生数据
        DoctorsAdapter adapter = new DoctorsAdapter(DoctorsActivity.this, R.layout.patient_item, DoctorsList);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Doctors d = DoctorsList.get(position);
                String currentName = d.getName();
                Intent intent = new Intent(DoctorsActivity.this, DoctorsInfoActivity.class);
                intent.putExtra("extra_data",currentName);
                startActivity(intent);
            }
        });
    }

    private void initDoctors() {
        List<com.example.materialtest.db.Doctor>doctors= DataSupport.findAll(com.example.materialtest.db.Doctor.class);
        for(com.example.materialtest.db.Doctor doctor:doctors){
            int id =doctor.getId();
            String ImageName=doctor.getPhoto();
            String name=doctor.getName();
            int imageID= getImageID(ImageName);
            Doctors d = new Doctors(id,name,imageID);
            DoctorsList.add(d);
        }
    }
    public int getImageID(String ImageName){
        Resources res=getResources();
        int picid = res.getIdentifier(ImageName,"drawable",getPackageName());
        return picid;
    }


}
