package com.edu.tce.tecuthsav17.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.edu.tce.tecuthsav17.R;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

public class Splash_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_splash__screen);
        Thread Splash = new Thread()
        {
            @Override
            public void run() {
                try
                {
                    sleep(3000);

                    Intent intent = new Intent(getApplicationContext(), Main_Screen.class);
                    startActivity(intent);
                    finish();
                }catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        };
        Splash.start();
    }
}
