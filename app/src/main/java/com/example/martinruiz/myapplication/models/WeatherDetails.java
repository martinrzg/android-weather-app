package com.example.martinruiz.myapplication.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class WeatherDetails implements Serializable{
    int id;
    @SerializedName("main")
    String shotDescription;
    @SerializedName("description")
    String longDescription;
    String icon;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShotDescription() {
        return shotDescription;
    }

    public void setShotDescription(String shotDescription) {
        this.shotDescription = shotDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
