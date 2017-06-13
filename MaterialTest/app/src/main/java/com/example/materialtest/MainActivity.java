package com.example.materialtest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    private List<Fruit> fruitList = new ArrayList<>();

    private FruitAdapter adapter;

    private SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
        final String name = pref.getString("account", "");
        Log.d("MainActivity", "The current user is " + name);
        WebView webView = (WebView) findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://ped.cmt.com.cn/");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        navView.setCheckedItem(R.id.nav_pi);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                List<Doctor> doctors = DataSupport.findAll(Doctor.class);
                for (Doctor doctor : doctors) {
                    if (doctor.getName().equals(name)) {
                        TextView nameText = (TextView) findViewById(R.id.mail);
                        nameText.setText(doctor.getName());
                        TextView phoneText = (TextView) findViewById((R.id.username));
                        phoneText.setText(doctor.getPhone());
                    }
                }
                int choice = item.getItemId();
                Intent intent;
                switch (choice) {
                    case R.id.nav_pi:
                        intent = new Intent(MainActivity.this, DoctorInfoShowActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_contact:
                        intent = new Intent(MainActivity.this, PatientActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_prescription:
                        intent = new Intent(MainActivity.this, PrescriptionsActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_notice:
                        intent = new Intent(MainActivity.this, AnnounceActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_task:
                        intent = new Intent(MainActivity.this, TaskActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "取消", Snackbar.LENGTH_SHORT)
                        .setAction("发布工作状态", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this, "您已发布当前状态为：工作", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });
       /* FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab_idle);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "取消", Snackbar.LENGTH_SHORT)
                        .setAction("发布空闲状态", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this, "您已发布当前状态为：空闲", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });*/
    }
    private void refreshFruits() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
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
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.register:
                intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            default:
        }
        return true;
    }

}
