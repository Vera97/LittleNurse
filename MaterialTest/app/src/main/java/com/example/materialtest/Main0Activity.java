package com.example.materialtest;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.example.materialtest.R;
import com.example.materialtest.db.Doctor;
import com.example.materialtest.db.Notice;
import com.example.materialtest.db.Patient;
import com.example.materialtest.db.Prescription;
import com.example.materialtest.db.Treat;
import com.example.materialtest.myPackage.Fruit;
import com.example.materialtest.myPackage.FruitAdapter;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class Main0Activity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        setContentView(R.layout.activity_main0);
        WebView webView = (WebView) findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://news.cmt.com.cn/");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Data deleted", Snackbar.LENGTH_SHORT)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(Main0Activity.this, "Data restored", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.backup:
                Toast.makeText(this, "You clicked Backup", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this, "You clicked Delete", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logIn:
                Intent intent = new Intent(Main0Activity.this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.register:
                intent = new Intent(Main0Activity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            default:
        }
        return true;
    }

    public void init(){
        LitePal.getDatabase();

        DataSupport.deleteAll(Doctor.class);
        DataSupport.deleteAll(com.example.materialtest.myPackage.Patient.class);
        DataSupport.deleteAll(com.example.materialtest.myPackage.Prescription.class);
        DataSupport.deleteAll(Treat.class);
        DataSupport.deleteAll(Notice.class);

    /*----Test Sample---*/

        Doctor doctor1 = new Doctor(1, "d_tian","123456","11111111111","d1",5,"rest", "男",25,"主治医生",7,"专业成熟，学识渊博，值得信赖（男神）");
        doctor1.save();
        Doctor doctor2 = new Doctor(2, "d_ming","123456","22222222222","d2",5,"rest", "男",25,"主治医生",7,"专业成熟，学识渊博，值得信赖（男神）");
        doctor2.save();
        Doctor doctor3 = new Doctor(3, "d_wang","123456","33333333333","d3",5,"rest", "男",25,"主治医生",7,"专业成熟，学识渊博，值得信赖（男神）");
        doctor3.save();
        Doctor doctor4 = new Doctor(4, "d_huang","123456","44444444444","d4",5,"rest", "男",25,"主治医生",7,"专业成熟，学识渊博，值得信赖（男神）");
        doctor4.save();
        Doctor doctor5 = new Doctor(5, "d_jie","123456","555555555555","d5",5,"rest", "男",25,"主治医生",7,"专业成熟，学识渊博，值得信赖（男神）");
        doctor5.save();
        Doctor doctor6 = new Doctor(6, "d_zhang","123456","66666666666","d6",5,"rest", "男",25,"主治医生",7,"专业成熟，学识渊博，值得信赖（男神）");
        doctor6.save();
        Doctor doctor7 = new Doctor(7, "d_si","123456","77777777777","d7",5,"rest", "男",25,"主治医生",7,"专业成熟，学识渊博，值得信赖（男神）");
        doctor7.save();
        Doctor doctor8 = new Doctor(8, "d_yuan","123456","88888888888","d8",5,"rest", "男",25,"主治医生",7,"专业成熟，学识渊博，值得信赖（男神）");
        doctor8.save();
        Doctor doctor9 = new Doctor(9, "d_xin","123456","99999999999","d9",5,"rest", "男",25,"主治医生",7,"专业成熟，学识渊博，值得信赖（男神）");
        doctor9.save();
        Doctor doctor10 = new Doctor(10, "d_lei","123456","00000000000","d10",5,"rest", "男",25,"主治医生",7,"专业成熟，学识渊博，值得信赖（男神）");
        doctor10.save();
        Doctor doctor11 = new Doctor(11, "d_xing","123456","00000000001","d11",5,"rest", "男",25,"主治医生",7,"专业成熟，学识渊博，值得信赖（男神）");
        doctor11.save();
        Doctor doctor12 = new Doctor(12, "d_tang","123456","00000000002","d12",5,"rest", "男",25,"主治医生",7,"专业成熟，学识渊博，值得信赖（男神）");
        doctor12.save();

        Patient apple = new Patient(1,"Alex","123456","100000000001","p1","男",18,"青霉素过敏");
        apple.save();
        Patient banana = new Patient(2,"Brandon", "123456","100000000002","p2","男",18,"青霉素过敏");
        banana.save();
        Patient orange = new Patient(3,"Carl", "123456","100000000003","p3","男",18,"青霉素过敏");
        orange.save();
        Patient watermelon = new Patient(4,"Davie", "123456","100000000004","p4","男",18,"青霉素过敏");
        watermelon.save();
        Patient pear = new Patient(5,"Eve", "123456","100000000005","p5","男",18,"青霉素过敏");
        pear.save();
        Patient grape = new Patient(6,"Flansica","123456","100000000006","p6","女",18,"青霉素过敏");
        grape.save();
        Patient pineapple = new Patient(7,"Grace","123456","100000000007","p7","女",18,"青霉素过敏");
        pineapple.save();
        Patient strawberry = new Patient(8,"Honey", "123456","100000000008","p8","女",18,"青霉素过敏");
        strawberry.save();
        Patient cherry = new Patient(9,"Iren", "123456","100000000009","p9","女",18,"青霉素过敏");
        cherry.save();
        Patient mango = new Patient(10,"Jennie", "123456","100000000010","p10","女",18,"青霉素过敏");
        mango.save();

        Prescription p1=new Prescription(1, "2017-06-12", 1, 2, "NULL", "prescription");
        p1.save();
        Prescription p2=new Prescription(2, "2017-06-12", 1, 2, "NULL", "prescription");
        p2.save();
        Prescription p3=new Prescription(3, "2017-06-13", 1, 2, "NULL", "prescription");
        p3.save();
        Prescription p4=new Prescription(4, "2017-06-14", 1, 2, "NULL", "prescription");
        p4.save();
        Prescription p5=new Prescription(5, "2017-06-14", 1, 2, "NULL", "prescription");
        p5.save();
        Prescription p6=new Prescription(6, "2017-06-15", 1, 2, "NULL", "prescription");
        p6.save();
        Prescription p7=new Prescription(7, "2017-06-16", 1, 2, "NULL", "prescription");
        p7.save();
        Prescription p8=new Prescription(8, "2017-06-17", 1, 2, "NULL", "prescription");
        p8.save();
        Prescription p9=new Prescription(9, "2017-06-18", 1, 2, "NULL", "prescription");
        p9.save();
        Prescription p10=new Prescription(1, "2017-06-18", 1, 2, "NULL", "prescription");
        p10.save();

        Treat treat=new Treat();
        treat.setId(1);
        treat.setDoctor_id(1);
        treat.setPatient_id(1);
        treat.save();

        Notice notice=new Notice();
        notice.setId(1);
        notice.setDoctor_id(1);
        notice.setTime("2017.6.10 21:43");
        notice.setContent("今天空闲");
        notice.save();
    }
}
