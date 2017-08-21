package com.example.martinruiz.myapplication.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by MartinRuiz on 8/19/2017.
 */

public class CityWeather implements Serializable{
    private City city;
    @SerializedName("list")
    private List<Weather> weeklyWeather;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Weather> getWeeklyWeather() {
        return weeklyWeather;
    }

    public void setWeeklyWeather(List<Weather> weeklyWeather) {
        this.weeklyWeather = weeklyWeather;
    }
}


