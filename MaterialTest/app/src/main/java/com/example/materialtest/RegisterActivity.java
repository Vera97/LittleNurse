package com.example.materialtest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Message;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.model.Headers;
import com.example.materialtest.db.*;
import com.example.materialtest.db.Patient;

import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.List;
import java.util.logging.Handler;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import okhttp3.Callback;
import okhttp3.Response;

import static com.mob.tools.utils.ResHelper.getStringRes;

public class RegisterActivity extends AppCompatActivity {

    private EditText mName;
    private EditText mPassword;
    private EditText mConfirmPwd;
    private EditText mPhone;
    private EditText cord;
    private TextView now;
    private Button getCord;
    private Button register;

    private String iPhone;
    private String iCord;
    private int time = 60;
    private boolean flag = true;

    private SharedPreferences.Editor editor;
    public static SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mName = (EditText) findViewById(R.id.register_username);
        mPassword = (EditText) findViewById(R.id.register_psd);
        mConfirmPwd=(EditText)findViewById(R.id.register_confirm_psd);
        mPhone = (EditText) findViewById(R.id.register_tel);
        cord = (EditText) findViewById(R.id.register_verify);
        now=(TextView)findViewById(R.id.now);
        getCord = (Button) findViewById(R.id.getcord);
        register=(Button)findViewById(R.id.register_btn);

        SMSSDK.initSDK(this, "1e91723e12560", "404ed00ac2262ffc0f87bac97b976353");
        EventHandler eh=new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {

                if (result == SMSSDK.RESULT_COMPLETE) {
                    //回调完成
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        //提交验证码成功
                        Log.i("EventHandler", "提交验证码成功");
                    }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                        //获取验证码成功
                        Log.i("EventHandler", "获取验证码成功");
                    }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
                        //返回支持发送验证码的国家列表
                        Log.i("EventHandler", "返回支持发送验证码的国家列表");
                    }
                }else{
                    ((Throwable)data).printStackTrace();
                    Log.i("EventHandler", "回调失败");
                }
            }
        };
        SMSSDK.registerEventHandler(eh);

        getCord.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){

                /////
                if(!TextUtils.isEmpty(mPhone.getText().toString().trim())){
                    if(mPhone.getText().toString().trim().length()==11){
                        iPhone = mPhone.getText().toString().trim();
                        SMSSDK.getVerificationCode("86",iPhone);
                        cord.requestFocus();
                        getCord.setVisibility(View.GONE);
                    }else{
                        Toast.makeText(RegisterActivity.this, "请输入完整电话号码", Toast.LENGTH_LONG).show();
                        mPhone.requestFocus();
                    }
                }else{
                    Toast.makeText(RegisterActivity.this, "请输入您的电话号码", Toast.LENGTH_LONG).show();
                    mPhone.requestFocus();
                }

            }
        });

        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (isUserNameAndPwdValid()) {
                    String userName = mName.getText().toString().trim();
                    String userPwd = mPassword.getText().toString().trim();
                    String ConfirmPwd=mConfirmPwd.getText().toString().trim();
                    String userPhone=mPhone.getText().toString().trim();

                    //check if user name is already exist
                    int count=nameTest(userName);
                    if(count>0){
                        Toast.makeText(RegisterActivity.this,"The name is already exist!",Toast.LENGTH_SHORT).show();
                        return ;
                    }
                    if(!(userPwd.equals(ConfirmPwd))) {
                        Toast.makeText(RegisterActivity.this, "The two passwords are different!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if(!TextUtils.isEmpty(cord.getText().toString().trim())){
                        if(cord.getText().toString().trim().length()==4){
                            iCord = cord.getText().toString().trim();
                            SMSSDK.submitVerificationCode("86", iPhone, iCord);
                            flag = false;
                        }else{
                            Toast.makeText(RegisterActivity.this, "请输入完整验证码", Toast.LENGTH_LONG).show();
                            cord.requestFocus();
                        }
                    }else {
                        Toast.makeText(RegisterActivity.this, "请输入验证码", Toast.LENGTH_LONG).show();
                        cord.requestFocus();
                    }

                    Patient new_patient = new Patient();
                    new_patient.setName(userName);
                    new_patient.setPassword(userPwd);
                    new_patient.setPhone(userPhone);
                    new_patient.setPhoto("NULL");
                    new_patient.save();

                    int flag;
                    flag=test(userName,userPwd);
                    if (flag == -1) {
                        Toast.makeText(RegisterActivity.this, "Fail: Register failed!",Toast.LENGTH_SHORT).show();
                    }else{
                        SharedPreferences.Editor editor=getSharedPreferences("data",MODE_PRIVATE).edit();
                        editor.putString("account",userName);
                        editor.putString("password",userPwd);
                        editor.apply();
                        Toast.makeText(RegisterActivity.this,"Success: Register succeed!",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(RegisterActivity.this,PatientMainActivity.class);
                        startActivity(intent);
                    }
                }

            }
        });
    }

/*    private void getcode(final String a[])
    {
        final String[] getcode = {""};
        String phoneNum = mPhone.getText().toString();
        final String path="http://123.207.215.216/yangtuo.php";
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("phone",phoneNum);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("sign","ccc");
        }
        final String param=jsonObject.toString();


        HttpUtil.getUtilsInstance().doPost(path, null, param, new Callback() {


            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("SendCode","Wrong");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Headers headers = response.headers();
                List<String> cookies = headers.values("Set-Cookie");
                if (!cookies.isEmpty()) {
                    String session = cookies.get(0);
                    Log.d("info_cookies", "onResponse-size: " + cookies);

                    String s = session.substring(0, session.indexOf(";"));
                    Log.i("info_s", "session is  :" + s);
                    editor.putString("cookie", s);
                    editor.apply();
                }
                getcode[0] = response.body().string().trim();
                Log.d("Code",getcode[0]);
                a[0] = getcode[0].substring(1,7);

                response.body().close();
            }
        });
        Log.d("hah",getcode[0]);

        Log.d("hah","jaj");
    }*/

    /*
    //验证码送成功后提示文字
    private void reminderText() {
        now.setVisibility(View.VISIBLE);
        handlerText.sendEmptyMessageDelayed(1, 1000);
    }

    Handler handlerText =new Handler(){
        public void handleMessage(Message msg) {
            if(msg.what==1){
                if(time>0){
                    now.setText("验证码已发送"+time+"秒");
                    time--;
                    handlerText.sendEmptyMessageDelayed(1, 1000);
                }else{
                    now.setText("提示信息");
                    time = 60;
                    now.setVisibility(View.GONE);
                    getCord.setVisibility(View.VISIBLE);
                }
            }else{
                cord.setText("");
                now.setText("提示信息");
                time = 60;
                now.setVisibility(View.GONE);
                getCord.setVisibility(View.VISIBLE);
            }
        };
    };

    Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            int event = msg.arg1;
            int result = msg.arg2;
            Object data = msg.obj;
            Log.e("event", "event="+event);
            if (result == SMSSDK.RESULT_COMPLETE) {
                //短信注册成功后，返回MainActivity,然后提示新好友
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {//提交验证码成功,验证通过
                    Toast.makeText(getApplicationContext(), "验证码校验成功", Toast.LENGTH_SHORT).show();
                    handlerText.sendEmptyMessage(2);
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){//服务器验证码发送成功
                    reminderText();
                    Toast.makeText(getApplicationContext(), "验证码已经发送", Toast.LENGTH_SHORT).show();
                }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){//返回支持发送验证码的国家列表
                    Toast.makeText(getApplicationContext(), "获取国家列表成功", Toast.LENGTH_SHORT).show();
                }
            } else {
                if(flag){
                    getCord.setVisibility(View.VISIBLE);
                    Toast.makeText(RegisterActivity.this, "验证码获取失败，请重新获取", Toast.LENGTH_SHORT).show();
                    mPhone.requestFocus();
                }else{
                    ((Throwable) data).printStackTrace();
                    int resId = getStringRes(RegisterActivity.this, "smssdk_network_error");
                    Toast.makeText(RegisterActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
                    cord.selectAll();
                    if (resId > 0) {
                        Toast.makeText(RegisterActivity.this, resId, Toast.LENGTH_SHORT).show();
                    }
                }

            }

        }

    };
*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler();
    }

    public boolean isUserNameAndPwdValid() {
        if (mName.getText().toString().trim().equals("")) {
            Toast.makeText(RegisterActivity.this, "用户名为空！",Toast.LENGTH_SHORT).show();
            return false;
        } else if (mPassword.getText().toString().trim().equals("")) {
            Toast.makeText(RegisterActivity.this, "密码为空！",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public int nameTest(String username){
        int count=0;
        List<Patient> patients= DataSupport.findAll(Patient.class);
        for(Patient patient:patients){
            if(patient.getName().equals(username))
                count++;
        }
        return count;
    }

    public int test(String username,String password){
        List<Patient> patients= DataSupport.findAll(Patient.class);
        for(Patient patient:patients){
            if(patient.getName().equals(username) && patient.getPassword().equals(password))
                return 1;
        }
        return -1;
    }
}
