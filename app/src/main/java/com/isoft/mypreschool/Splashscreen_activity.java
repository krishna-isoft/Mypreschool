package com.isoft.mypreschool;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Splashscreen_activity extends AppCompatActivity {
    Thread th;
    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        context=this;

        th = new Thread() {
            public void run() {
                try {

                    sleep(2000);

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {


                    Intent mIntent = new Intent(
                            context,
                            AdminloginActivity.class);
                    startActivity(mIntent);
                    finish();


                }
            }
        };
        th.start();
    }
}
