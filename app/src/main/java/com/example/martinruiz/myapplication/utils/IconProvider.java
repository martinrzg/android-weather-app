package com.example.martinruiz.myapplication.utils;

import com.example.martinruiz.myapplication.R;

/**
 * Created by MartinRuiz on 8/21/2017.
 */

public class IconProvider {

    public static int getImageIcon(String weatherDescription){
        int weatherIcon ;
        switch(weatherDescription) {
            case "Thunderstorm":
                weatherIcon = R.mipmap.ic_atmosphere;
                break;
            case "Drizzle":
                weatherIcon = R.mipmap.ic_drizzle;
                break;
            case "Rain":
                weatherIcon = R.mipmap.ic_rain;
                break;
            case "Snow":
                weatherIcon = R.mipmap.ic_snow;
                break;
            case "Atmosphere":
                weatherIcon = R.mipmap.ic_atmosphere;
                break;
            case "Clear":
                weatherIcon = R.mipmap.ic_clear;
                break;
            case "Clouds":
                weatherIcon = R.mipmap.ic_cloudy;
                break;
            case "Extreme":
                weatherIcon = R.mipmap.ic_extreme;
                break;
            default:
                weatherIcon = R.mipmap.ic_launcher;
        }
        return weatherIcon;

    }

}
