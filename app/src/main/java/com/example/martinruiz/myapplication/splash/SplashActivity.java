package com.example.martinruiz.myapplication.splash;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.MainThread;
import android.support.annotation.RequiresApi;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.example.martinruiz.myapplication.R;
import com.example.martinruiz.myapplication.activities.MainActivity;
import com.mikhaellopez.circularfillableloaders.CircularFillableLoaders;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    //@BindView(R.id.circularFillableLoaders) CircularFillableLoaders loaders;
    private CircularFillableLoaders loader;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //ButterKnife.bind(this);
        loader = findViewById(R.id.circularFillableLoaders);

        loader.setProgress(0);
        ObjectAnimator animation = ObjectAnimator.ofInt(loader,"progress",0,100);
        animation.setDuration(1000);
        animation.start();

        Explode explode = new Explode();
        getWindow().setExitTransition(explode);

        Intent intentToMain = new Intent(SplashActivity.this,MainActivity.class);
        startActivity(intentToMain);


    }






}



