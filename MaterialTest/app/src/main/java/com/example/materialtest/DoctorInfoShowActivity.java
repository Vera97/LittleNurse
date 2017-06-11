package com.example.materialtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.materialtest.R.id.doctorInfo_confirm;

public class DoctorInfoShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_info_show);
        Button edit = (Button) findViewById(doctorInfo_confirm);

        TextView nameText = (TextView) findViewById(R.id.nameText);
        nameText.setText("黄思堂");

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorInfoShowActivity.this, DoctorInfoActivity.class);
                startActivity(intent);
            }
        });
    }
}
