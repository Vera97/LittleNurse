package com.example.materialtest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.materialtest.db.Doctor;

import static com.example.materialtest.R.id.doctorInfo_confirm;

public class DoctorInfoActivity extends AppCompatActivity {

    private EditText newName;
    private EditText newGender;
    private EditText newAge;
    private EditText newTitle;
    private EditText newWorkTime;
    private EditText newPhone;
    private EditText newIntro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_info);


        Button edit = (Button) findViewById(doctorInfo_confirm);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref=getSharedPreferences("data",MODE_PRIVATE);
                String currentName=pref.getString("account","");
                Log.d("DoctorInfoShowActivity","The current user is "+ currentName);

                newName=(EditText)findViewById(R.id.doctorInfo_username);
                newGender=(EditText)findViewById(R.id.doctorInfo_sex);
                newAge=(EditText)findViewById(R.id.doctorInfo_age);
                newTitle=(EditText)findViewById(R.id.doctorInfo_title);
                newWorkTime=(EditText)findViewById(R.id.doctorInfo_workTime);
                newPhone=(EditText)findViewById(R.id.doctorInfo_contact);
                newIntro=(EditText)findViewById(R.id.doctorInfo_intro);

                //String name=newName.getText().toString();
                String gender=newGender.getText().toString();
                String age0=newAge.getText().toString();
                String title=newTitle.getText().toString();
                String workTime0=newWorkTime.getText().toString();
                String phone=newPhone.getText().toString();
                String intro=newIntro.getText().toString();

                int age=Integer.parseInt(age0);
                int workTime=Integer.parseInt(workTime0);

                Doctor doctor=new Doctor();
                doctor.setAge(age);
                doctor.setGender(gender);
                doctor.setTitle(title);
                doctor.setWorkTime(workTime);
                doctor.setPhone(phone);
                doctor.setIntro(intro);
                doctor.updateAll("name=?",currentName);

                Toast.makeText(DoctorInfoActivity.this, "修改成功！", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(DoctorInfoActivity.this, DoctorInfoShowActivity.class);
                startActivity(intent);

            }
        });



    }
}
