package com.example.martinruiz.myapplication.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.martinruiz.myapplication.R;
import com.example.martinruiz.myapplication.models.CityWeather;

public class WeatherDetails extends AppCompatActivity {
    private CityWeather cityWeather;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_details);
        Bundle bundle = getIntent().getExtras();
        if(! bundle.isEmpty()){
            cityWeather = (CityWeather) bundle.getSerializable("city");
        }

        System.out.println(cityWeather.getCity().getName());
        System.out.println(cityWeather.getWeeklyWeather().get(0).getTemp().getDay());

    }
}
