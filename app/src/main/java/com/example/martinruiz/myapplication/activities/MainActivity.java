package com.example.martinruiz.myapplication.activities;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Toast;

import com.example.martinruiz.myapplication.API.API;
import com.example.martinruiz.myapplication.API.APIServices.WeatherServices;
import com.example.martinruiz.myapplication.R;
import com.example.martinruiz.myapplication.adapters.CityWeatherAdapter;
import com.example.martinruiz.myapplication.models.CityWeather;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private List<CityWeather> cities;
    @BindView(R.id.recyclerViewWeatherCards) RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    @BindView(R.id.swipeRefresh) SwipeRefreshLayout swipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        ButterKnife.bind(this);
        cities = getCities();
        layoutManager = new LinearLayoutManager(this);
        adapter = new CityWeatherAdapter(cities, R.layout.weather_card, this, new CityWeatherAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(CityWeather cityWeather, int position) {
                Toast.makeText(MainActivity.this,cityWeather.getCity().getName(),Toast.LENGTH_LONG).show();
            }
        });
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);




        WeatherServices weatherServices = API.getApi().create(WeatherServices.class);

        Call<CityWeather> cityWeather = weatherServices.getWeatherCity("Toluca", API.KEY, "metric");
        cityWeather.enqueue(new Callback<CityWeather>() {
            @Override
            public void onResponse(Call<CityWeather> call, Response<CityWeather> response) {
                CityWeather cityWeather = response.body();
                cities.add(cityWeather);
                adapter.notifyItemInserted(cities.size()-1);
                cities.add(cityWeather);
                adapter.notifyItemInserted(cities.size()-1);
                layoutManager.scrollToPosition(cities.size());

            }

            @Override
            public void onFailure(Call<CityWeather> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_LONG).show();
            }
        });
        Call<CityWeather> cityWeather2 = weatherServices.getWeatherCity("London", API.KEY, "metric");
        cityWeather2.enqueue(new Callback<CityWeather>() {
            @Override
            public void onResponse(Call<CityWeather> call, Response<CityWeather> response) {
                CityWeather cityWeather = response.body();
                cities.add(cityWeather);
                adapter.notifyItemInserted(cities.size()-1);
                cities.add(cityWeather);
                adapter.notifyItemInserted(cities.size()-1);
                layoutManager.scrollToPosition(cities.size());

            }

            @Override
            public void onFailure(Call<CityWeather> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_LONG).show();
            }
        });
        Call<CityWeather> cityWeather3 = weatherServices.getWeatherCity("Singapore", API.KEY, "metric");
        cityWeather3.enqueue(new Callback<CityWeather>() {
            @Override
            public void onResponse(Call<CityWeather> call, Response<CityWeather> response) {
                CityWeather cityWeather = response.body();
                cities.add(cityWeather);
                adapter.notifyItemInserted(cities.size()-1);
                cities.add(cityWeather);
                adapter.notifyItemInserted(cities.size()-1);
                layoutManager.scrollToPosition(cities.size());

            }

            @Override
            public void onFailure(Call<CityWeather> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_LONG).show();
            }
        });

    }

    private List<CityWeather> getCities() {
        return new ArrayList<CityWeather>(){
            {
            }
        };
    }
}
