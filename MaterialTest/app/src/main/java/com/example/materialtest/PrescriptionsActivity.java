package com.example.materialtest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.materialtest.db.Prescription;
import com.example.materialtest.myPackage.Patient;
import com.example.materialtest.myPackage.PatientAdapter;
import com.example.materialtest.myPackage.Prescriptions;
import com.example.materialtest.myPackage.PrescriptionsAdapter;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class PrescriptionsActivity extends AppCompatActivity {

    private List<Prescriptions> PrescriptionsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prescriptions_main);

        SharedPreferences pref=getSharedPreferences("data",MODE_PRIVATE);
        String name=pref.getString("account","");
        Log.d("PrescriptionsActivity","The current user is "+ name);

        initPrescriptions(); // 初始化处方数据
        PrescriptionsAdapter adapter = new PrescriptionsAdapter(PrescriptionsActivity.this, R.layout.prescription_item, PrescriptionsList);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Prescriptions p = PrescriptionsList.get(position);
                int currentID = p.getId();
                Intent intent = new Intent(PrescriptionsActivity.this, RecordActivity.class);
                intent.putExtra("extra_data",currentID);
                startActivity(intent);
            }
        });
    }

    private void initPrescriptions() {
        List<com.example.materialtest.db.Prescription>prescriptions= DataSupport.findAll(com.example.materialtest.db.Prescription.class);
        for(com.example.materialtest.db.Prescription prescription:prescriptions){
            int id =prescription.getId();
            String ImageName = prescription.getPhoto();
            String date=prescription.getDate();
            int imageID= getImageID(ImageName);
            Prescriptions p = new Prescriptions(id,date,imageID);
            PrescriptionsList.add(p);
        }
    }
    public int getImageID(String ImageName){
        Resources res=getResources();
        int picid = res.getIdentifier(ImageName,"drawable",getPackageName());
        return picid;
    }


}
