package com.example.materialtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static com.example.materialtest.R.id.doctorInfo_confirm;

public class DoctorInfoShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_info_show);
        Button edit = (Button) findViewById(doctorInfo_confirm);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorInfoShowActivity.this, DoctorInfoActivity.class);
                startActivity(intent);
            }
        });
    }
}
