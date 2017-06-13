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

public class PatientMainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    private Fruit[] fruits = {new Fruit("d_tian", R.drawable.d1), new Fruit("d_ming", R.drawable.d2),
            new Fruit("d_wang", R.drawable.d3), new Fruit("d_huang", R.drawable.d4),
            new Fruit("d_jie", R.drawable.d5), new Fruit("d_zhang", R.drawable.d6),
            new Fruit("d_si", R.drawable.d7), new Fruit("d_yuan", R.drawable.d8),
            new Fruit("d_xin", R.drawable.d9), new Fruit("d_lei", R.drawable.d10),
            new Fruit("d_tang", R.drawable.d11)
    };

    private List<Fruit> fruitList = new ArrayList<>();

    private FruitAdapter adapter;

    private SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_p);

        SharedPreferences pref=getSharedPreferences("data",MODE_PRIVATE);
        final String name=pref.getString("account","");
        Log.d("PatientMainActivity","The current user is "+ name);

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
            public boolean onNavigationItemSelected(MenuItem item)
            {
                List<Patient> patients=DataSupport.findAll(Patient.class);
                for(Patient patient:patients){
                    if(patient.getName().equals(name)){
                        TextView nameText = (TextView) findViewById(R.id.mail);
                        nameText.setText(patient.getName());
                        TextView phoneText=(TextView)findViewById((R.id.username));
                        phoneText.setText(patient.getPhone());
                    }
                }
                int choice = item.getItemId();
                Intent intent;
                switch (choice){
                    case R.id.nav_pi:
                        intent = new Intent(PatientMainActivity.this, PatientPersonalInfoShowActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_contact:
                        intent = new Intent(PatientMainActivity.this, DoctorsActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_prescription:
                        intent = new Intent(PatientMainActivity.this, PrescriptionsActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_notice:
                        intent = new Intent(PatientMainActivity.this, AnnounceReceiveActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        intent = new Intent(PatientMainActivity.this, RecordActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });

        initFruits();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFruits();
            }
        });

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
                        initFruits();
                        adapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    private void initFruits() {
        fruitList.clear();
        for (int i = 0; i < 50; i++) {
            Random random = new Random();
            int index = random.nextInt(fruits.length);
            fruitList.add(fruits[index]);
        }
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
                Intent intent = new Intent(PatientMainActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.register:
                intent = new Intent(PatientMainActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            default:
        }
        return true;
    }

}

