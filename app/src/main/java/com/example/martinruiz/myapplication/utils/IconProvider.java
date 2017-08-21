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
                weatherIcon = thunderstorm;
                break;
            case "Drizzle":
                weatherIcon = drizzle;
                break;
            case "Rain":
                weatherIcon = rain;
                break;
            case "Snow":
                weatherIcon = snow;
                break
            case "Atmosphere":
                weatherIcon = atmosphere;
                break;
            case "Clear":
                weatherIcon = clear;
                break
            case "Clouds":
                weatherIcon = clouds;
                break;
            case "Extreme":
                weatherIcon = extreme;
                break;
            default:
                weatherIcon = defaultIcon;
        }

    }

}
