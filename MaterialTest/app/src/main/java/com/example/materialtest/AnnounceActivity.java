package com.example.materialtest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.materialtest.db.Doctor;
import com.example.materialtest.db.Notice;

import org.litepal.crud.DataSupport;

import java.util.Date;
import java.util.List;

import static com.example.materialtest.R.id.doctorInfo_confirm;

public class AnnounceActivity extends AppCompatActivity {

    private EditText newNotice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announce);

        SharedPreferences pref=getSharedPreferences("data",MODE_PRIVATE);
        final String currentName=pref.getString("account","");
        Log.d("AnnounceActivity","The current user is "+ currentName);

        int id=getDoctorID(currentName);
        //Notice lastNotice=DataSupport.where("doctor_id=?",""+id).findLast(Notice.class);
        List<Notice>notices=DataSupport.findAll(Notice.class);
        for(Notice notice:notices){
            if(notice.getDoctor_id()==id){
                String last=notice.getContent();
                TextView genderText = (TextView) findViewById(R.id.lastNoticeContent);
                genderText.setText(last);
            }else{
                TextView noticeText = (TextView) findViewById(R.id.lastNoticeContent);
                noticeText.setText("未曾发过公告");
            }

        }
       /* if(lastNotice.getContent()==null) {
            TextView noticeText = (TextView) findViewById(R.id.lastNoticeContent);
            noticeText.setText("未曾发过公告");
        }else{
            String last=lastNotice.getContent();
            TextView genderText = (TextView) findViewById(R.id.lastNoticeContent);
            genderText.setText(last);
        }*/

        Button edit = (Button) findViewById(R.id.button);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                newNotice=(EditText)findViewById(R.id.newNotice);
                String notice=newNotice.getText().toString();

                int doctorID=getDoctorID(currentName);

                Notice n=new Notice();
                n.setDoctor_id(doctorID);
                n.setTime((new Date()).toString());
                n.setContent(notice);
                n.save();

                Toast.makeText(AnnounceActivity.this, "新公告发布成功", Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(AnnounceActivity.this,AnnounceActivity.class);
                startActivity(intent);
            }
        });
    }

    public int getDoctorID(String name){
        List<Doctor>doctors= DataSupport.findAll(Doctor.class);
        for(Doctor doctor:doctors){
            if(doctor.getName().equals(name))
                return doctor.getId();
        }
        return 0;
    }
}

