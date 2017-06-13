package com.example.materialtest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.materialtest.db.Doctor;
import com.example.materialtest.db.Patient;

import static com.example.materialtest.R.id.doctorInfo_confirm;
import static com.example.materialtest.R.id.patientInfo_confirm;

public class PatientPersonalInfoActivity extends AppCompatActivity {

    private EditText newName;
    private EditText newGender;
    private EditText newAge;
    private EditText newPhone;
    private EditText newIntro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_personal_info);


        Button edit = (Button) findViewById(patientInfo_confirm);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref=getSharedPreferences("data",MODE_PRIVATE);
                String currentName=pref.getString("account","");
                Log.d("PatientPIActivity","The current user is "+ currentName);

                newName=(EditText)findViewById(R.id.patientInfo_username);
                newGender=(EditText)findViewById(R.id.patientInfo_sex);
                newAge=(EditText)findViewById(R.id.patientInfo_age);
                newPhone=(EditText)findViewById(R.id.patientInfo_contact);
                newIntro=(EditText)findViewById(R.id.patientInfo_intro);

                //String name=newName.getText().toString();
                String gender=newGender.getText().toString();
                String age0=newAge.getText().toString();
                String phone=newPhone.getText().toString();
                String intro=newIntro.getText().toString();

                int age=Integer.parseInt(age0);

                Patient patient=new Patient();
                patient.setAge(age);
                patient.setGender(gender);
                patient.setPhone(phone);
                patient.setHistory(intro);
                patient.updateAll("name=?",currentName);

                Toast.makeText(PatientPersonalInfoActivity.this, "修改成功！", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(PatientPersonalInfoActivity.this, PatientPersonalInfoShowActivity.class);
                startActivity(intent);

            }
        });



    }
}
