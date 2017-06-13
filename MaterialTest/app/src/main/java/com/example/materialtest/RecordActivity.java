package com.example.materialtest;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.materialtest.myPackage.Patient;

import org.litepal.crud.DataSupport;

import java.util.List;

public class RecordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        Intent intent=getIntent();
        final int currentId=intent.getIntExtra("extra_data",0);
        Log.d("RecordActivity","The current id is "+currentId);

        List<com.example.materialtest.db.Prescription> prescriptions= DataSupport.findAll(com.example.materialtest.db.Prescription.class);
        for(com.example.materialtest.db.Prescription prescription:prescriptions) {
            if(prescription.getId()==currentId){
                TextView recordNameText = (TextView) findViewById(R.id.record_name);
                recordNameText.setText(""+prescription.getPatient_id());
            }
        }

        Button bt = (Button) findViewById(R.id.learnMore);
        bt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                int patientID=getPatientID(currentId);
                Log.d("RecordActivity","patient id is "+patientID);
                String patientName=getPatientName(patientID);
                Log.d("RecordActivity","patient name is "+patientName);
                Intent intent = new Intent(RecordActivity.this, PatientInfoActivity.class);
                intent.putExtra("extra_data",patientName);
                startActivity(intent);
            }
        });
    }


    public int getPatientID(int current_id){
        List<com.example.materialtest.db.Prescription> prescriptions= DataSupport.findAll(com.example.materialtest.db.Prescription.class);
        for(com.example.materialtest.db.Prescription prescription:prescriptions) {
            if(prescription.getId()==current_id){
                return prescription.getPatient_id();
            }
        }
        return 0;
    }

    public String getPatientName(int id){
        List<com.example.materialtest.db.Patient>patients=DataSupport.findAll(com.example.materialtest.db.Patient.class);
        for(com.example.materialtest.db.Patient patient:patients){
            if(patient.getId()==id){
                return patient.getName();
            }
        }
        return null;
    }
}
