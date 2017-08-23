package com.example.martinruiz.myapplication.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.martinruiz.myapplication.R;
import com.example.martinruiz.myapplication.models.City;
import com.example.martinruiz.myapplication.models.CityWeather;
import com.example.martinruiz.myapplication.utils.IconProvider;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherDetails extends AppCompatActivity {
    @BindView(R.id.textViewCardCityName) TextView textViewCityName;
    @BindView(R.id.textViewCardWeatherDescription) TextView textViewWeatherDescription;
    @BindView(R.id.textViewCardCurrentTemp) TextView textViewCurrentTemp;
    @BindView(R.id.textViewCardMaxTemp) TextView textViewMaxTemp;
    @BindView(R.id.textViewCardMinTemp) TextView  textViewMinTemp;
    @BindView(R.id.imageViewCardWeatherIcon) ImageView imageViewWeatherIcon;
    @BindView(R.id.textViewDay1) TextView textViewDay1;
    @BindView(R.id.textViewDay2) TextView textViewDay2;
    @BindView(R.id.textViewDay3) TextView textViewDay3;
    @BindView(R.id.textViewDay4) TextView textViewDay4;
    @BindView(R.id.textViewDay5) TextView textViewDay5;
    @BindView(R.id.imageViewDay1) ImageView imageViewDay1;
    @BindView(R.id.imageViewDay2) ImageView imageViewDay2;
    @BindView(R.id.imageViewDay3) ImageView imageViewDay3;
    @BindView(R.id.imageViewDay4) ImageView imageViewDay4;
    @BindView(R.id.imageViewDay5) ImageView imageViewDay5;
    @BindView(R.id.textViewMaxTempDay1) TextView textViewMaxTempDay1;
    @BindView(R.id.textViewMaxTempDay2) TextView textViewMaxTempDay2;
    @BindView(R.id.textViewMaxTempDay3) TextView textViewMaxTempDay3;
    @BindView(R.id.textViewMaxTempDay4) TextView textViewMaxTempDay4;
    @BindView(R.id.textViewMaxTempDay5) TextView textViewMaxTempDay5;
    @BindView(R.id.textViewMinTempDay1) TextView textViewMinTempDay1;
    @BindView(R.id.textViewMinTempDay2) TextView textViewMinTempDay2;
    @BindView(R.id.textViewMinTempDay3) TextView textViewMinTempDay3;
    @BindView(R.id.textViewMinTempDay4) TextView textViewMinTempDay4;
    @BindView(R.id.textViewMinTempDay5) TextView textViewMinTempDay5;

    @BindView(R.id.textViewHumidity ) TextView textViewHumidity;
    @BindView(R.id.textViewWind) TextView textViewWind;
    @BindView(R.id.textViewCloudiness) TextView textViewCloudiness;
    @BindView(R.id.textViewPressure) TextView textViewPressure;

    private CityWeather cityWeather;
    String[] namesOfDays = {
            "SAT","SUN","MON", "TUE", "WED", "THU", "FRI",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_details);
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        if(! bundle.isEmpty()){
            cityWeather = (CityWeather) bundle.getSerializable("city");
        }
        setCardData();

    }

    private void setCardData() {
        textViewCityName.setText(cityWeather.getCity().getName()+", "+cityWeather.getCity().getCountry());
        textViewWeatherDescription.setText(cityWeather.getWeeklyWeather().get(0).getWeatherDetails().get(0).getLongDescription());
        textViewCurrentTemp.setText((int) cityWeather.getWeeklyWeather().get(0).getTemp().getDay()+"°");
        textViewMaxTemp.setText((int) cityWeather.getWeeklyWeather().get(0).getTemp().getMax()+"°");
        textViewMinTemp.setText((int) cityWeather.getWeeklyWeather().get(0).getTemp().getMin()+"°");

        textViewHumidity.setText((int) cityWeather.getWeeklyWeather().get(0).getHumidity() +"%");
        textViewWind.setText((int) cityWeather.getWeeklyWeather().get(0).getSpeed()+" m/s");
        textViewCloudiness.setText((int) cityWeather.getWeeklyWeather().get(0).getClouds()+"%");
        textViewPressure.setText((int) cityWeather.getWeeklyWeather().get(0).getPressure()+" hPa");


        String weatherDescription = cityWeather.getWeeklyWeather().get(0).getWeatherDetails().get(0).getShotDescription();
        Picasso.with(this).load(IconProvider.getImageIcon(weatherDescription)).into(imageViewWeatherIcon);
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        for (int i = 1; i < cityWeather.getWeeklyWeather().size(); i++) {
            calendar.setTime(date);
            String day = namesOfDays[(calendar.get(Calendar.DAY_OF_WEEK)+i)%7];
            String weatherWeekDescription ;

            switch (i){
                case 0:
                    break;
                case 1:
                    System.out.println("CASE 1 ----" +i);

                    textViewDay1.setText(day);
                    weatherWeekDescription = cityWeather.getWeeklyWeather().get(i).getWeatherDetails().get(0).getShotDescription();
                    Picasso.with(this).load(IconProvider.getImageIcon(weatherWeekDescription)).into(imageViewDay1);
                    textViewMaxTempDay1.setText((int) cityWeather.getWeeklyWeather().get(i).getTemp().getMax()+"°C");
                    textViewMinTempDay1.setText((int) cityWeather.getWeeklyWeather().get(i).getTemp().getMin()+"°C");
                    break;
                case 2:
                    System.out.println("CASE 2 ----" +i);
                    textViewDay2.setText(day);
                    weatherWeekDescription = cityWeather.getWeeklyWeather().get(i).getWeatherDetails().get(0).getShotDescription();
                    Picasso.with(this).load(IconProvider.getImageIcon(weatherWeekDescription)).into(imageViewDay2);
                    textViewMaxTempDay2.setText((int) cityWeather.getWeeklyWeather().get(i).getTemp().getMax()+"°C");
                    textViewMinTempDay2.setText((int) cityWeather.getWeeklyWeather().get(i).getTemp().getMin()+"°C");
                    break;
                case 3:
                    textViewDay3.setText(day);
                    weatherWeekDescription = cityWeather.getWeeklyWeather().get(i).getWeatherDetails().get(0).getShotDescription();
                    Picasso.with(this).load(IconProvider.getImageIcon(weatherWeekDescription)).into(imageViewDay3);
                    textViewMaxTempDay3.setText((int) cityWeather.getWeeklyWeather().get(i).getTemp().getMax()+"°C");
                    textViewMinTempDay3.setText((int) cityWeather.getWeeklyWeather().get(i).getTemp().getMin()+"°C");
                    break;
                case 4:
                    textViewDay4.setText(day);
                    weatherWeekDescription = cityWeather.getWeeklyWeather().get(i).getWeatherDetails().get(0).getShotDescription();
                    Picasso.with(this).load(IconProvider.getImageIcon(weatherWeekDescription)).into(imageViewDay4);
                    textViewMaxTempDay4.setText((int) cityWeather.getWeeklyWeather().get(i).getTemp().getMax()+"°C");
                    textViewMinTempDay4.setText((int) cityWeather.getWeeklyWeather().get(i).getTemp().getMin()+"°C");
                    break;
                case 5:
                    textViewDay5.setText(day);
                    weatherWeekDescription = cityWeather.getWeeklyWeather().get(i).getWeatherDetails().get(0).getShotDescription();
                    Picasso.with(this).load(IconProvider.getImageIcon(weatherWeekDescription)).into(imageViewDay5);
                    textViewMaxTempDay5.setText((int) cityWeather.getWeeklyWeather().get(i).getTemp().getMax()+"°C");
                    textViewMinTempDay5.setText((int) cityWeather.getWeeklyWeather().get(i).getTemp().getMin()+"°C");
                    break;
                default:
                    break;
            }

        }
        
    }
}
