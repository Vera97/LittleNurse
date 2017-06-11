package com.example.materialtest.myPackage;

import android.app.Application;
import android.content.Context;

/**
 * Created by whjth on 2017/6/11.
 */

public class MyApplication extends Application{
    private static Context context;
    @Override
    public void onCreate(){
        context = getApplicationContext();
    }
    public static Context getContext(){
        return context;
    }
}
