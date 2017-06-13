package com.example.materialtest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.DefaultDatabaseErrorHandler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.materialtest.db.*;
import com.example.materialtest.db.Patient;

import org.litepal.crud.DataSupport;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private EditText mName;
    private EditText mPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar=(Toolbar)findViewById(R.id.login_bar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setTitle("");
        }

        mName = (EditText) findViewById(R.id.login_tel);
        mPassword = (EditText) findViewById(R.id.login_psd);

        TextView newUser=(TextView)findViewById(R.id.newUser);
        newUser.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        Button login=(Button)findViewById(R.id.login_btn);
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (isUserNameAndPwdValid()) {
                    String userName = mName.getText().toString().trim();
                    String userPwd = mPassword.getText().toString().trim();
                    int result=verify(userName,userPwd);
                    if(result==1){
                        //login success
                        SharedPreferences.Editor editor=getSharedPreferences("data",MODE_PRIVATE).edit();
                        editor.putString("account",userName);
                        editor.putString("password",userPwd);
                        editor.apply();
                        List<Doctor>doctors=DataSupport.findAll(Doctor.class);
                        for(Doctor doctor:doctors){
                            if(doctor.getName().equals(userName)){
                                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(intent);
                            }
                        }
                        List<Patient>patients=DataSupport.findAll(Patient.class);
                        for(Patient patient:patients) {
                            if(patient.getName().equals(userName)) {
                                Intent intent = new Intent(LoginActivity.this, PatientMainActivity.class);
                                startActivity(intent);
                            }
                        }
                        /*if(userName.contains("d_")){
                            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(intent);
                        }else{
                            Intent intent=new Intent(LoginActivity.this,PatientMainActivity.class);
                            startActivity(intent);
                        }*/
                    }else if(result==0){
                        //login failed,user does't exist
                        Toast.makeText(LoginActivity.this,"Failed: UserName or Password Error",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public boolean isUserNameAndPwdValid() {
        if (mName.getText().toString().trim().equals("")) {
            Toast.makeText(LoginActivity.this, "用户名为空！",Toast.LENGTH_SHORT).show();
            return false;
        } else if (mPassword.getText().toString().trim().equals("")) {
            Toast.makeText(LoginActivity.this, "密码为空！",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public int verify(String Name,String Password){
        List<Doctor> doctors= DataSupport.findAll(Doctor.class);
        List<com.example.materialtest.db.Patient> patients=DataSupport.findAll(Patient.class);
        for(Patient patient:patients){
            if(patient.getName().equals(Name) && patient.getPassword().equals(Password))
                return 1;
        }
        for(Doctor doctor:doctors){
            if(doctor.getName().equals(Name) && doctor.getPassword().equals(Password))
                return 1;
        }
        return 0;
    }
}
