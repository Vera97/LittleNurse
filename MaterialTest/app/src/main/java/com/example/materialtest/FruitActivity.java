package com.example.materialtest;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.materialtest.db.Doctor;

import org.litepal.crud.DataSupport;

import java.util.List;

public class FruitActivity extends AppCompatActivity {

    public static final String FRUIT_NAME = "fruit_name";

    public static final String FRUIT_IMAGE_ID = "fruit_image_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);
        Intent intent = getIntent();
        String fruitName = intent.getStringExtra(FRUIT_NAME);
        int fruitImageId = intent.getIntExtra(FRUIT_IMAGE_ID, 0);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        ImageView fruitImageView = (ImageView) findViewById(R.id.fruit_image_view);
        //TextView fruitContentText = (TextView) findViewById(R.id.fruit_content_text);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbar.setTitle(fruitName);
        Glide.with(this).load(fruitImageId).into(fruitImageView);
        String fruitContent = generateFruitContent(fruitName);
        //fruitContentText.setText(fruitContent);

        List<Doctor> doctors= DataSupport.where("name=?",fruitName).find(Doctor.class);
        for(Doctor doctor:doctors){
            TextView nameText = (TextView) findViewById(R.id.nameText);
            nameText.setText(doctor.getName());

            if(doctor.getGender()==null) {
                TextView genderText = (TextView) findViewById(R.id.genderText);
                genderText.setText("未知");
            }else{
                TextView genderText = (TextView) findViewById(R.id.genderText);
                genderText.setText(doctor.getGender());
            }

            if(doctor.getAge()==0) {
                TextView ageText = (TextView) findViewById(R.id.ageText);
                ageText.setText("未知");
            }else{
                TextView ageText = (TextView) findViewById(R.id.ageText);
                ageText.setText(""+doctor.getAge());
            }

            if(doctor.getTitle()==null){
                TextView titleText=(TextView)findViewById(R.id.titleText);
                titleText.setText("未知");
            }else{
                TextView titleText=(TextView)findViewById(R.id.titleText);
                titleText.setText(doctor.getTitle());
            }

            if(doctor.getWorkTime()==0){
                TextView workTimeText=(TextView)findViewById(R.id.workTimeText);
                workTimeText.setText("未知");
            }else{
                TextView workTimeText=(TextView)findViewById(R.id.workTimeText);
                workTimeText.setText(""+doctor.getWorkTime());
            }

            if(doctor.getPhone()==null){
                TextView phoneText=(TextView)findViewById(R.id.phoneText);
                phoneText.setText("未知");
            }else{
                TextView phoneText=(TextView)findViewById(R.id.phoneText);
                phoneText.setText(doctor.getPhone());
            }

            if(doctor.getIntro()==null){
                TextView introText=(TextView)findViewById(R.id.introText);
                introText.setText("未知");
            }else{
                TextView introText=(TextView)findViewById(R.id.introText);
                introText.setText(doctor.getIntro());
            }

            if(doctor.getStarLevel()==0){
                TextView starText=(TextView)findViewById(R.id.starText);
                starText.setText("未知");
            }else{
                TextView starText=(TextView)findViewById(R.id.starText);
                starText.setText(""+doctor.getStarLevel());
            }

        }
    }

    private String generateFruitContent(String fruitName) {
        StringBuilder fruitContent = new StringBuilder();
        for (int i = 0; i < 500; i++) {
            fruitContent.append(fruitName);
        }
        return fruitContent.toString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
