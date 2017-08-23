package com.example.martinruiz.myapplication.activities;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.InputType;
import android.transition.Explode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.martinruiz.myapplication.API.API;
import com.example.martinruiz.myapplication.API.APIServices.WeatherServices;
import com.example.martinruiz.myapplication.R;
import com.example.martinruiz.myapplication.adapters.CityWeatherAdapter;
import com.example.martinruiz.myapplication.interfaces.onSwipeListener;
import com.example.martinruiz.myapplication.models.CityWeather;
import com.example.martinruiz.myapplication.utils.ItemTouchHelperCallback;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;

public class MainActivity extends AppCompatActivity {

    private List<CityWeather> cities;
    @BindView(R.id.recyclerViewWeatherCards) RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    @BindView(R.id.swipeRefresh) SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.fabAddCity) FloatingActionButton fabAddCity ;
    private WeatherServices weatherServices;
    private MaterialTapTargetPrompt mFabPrompt;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        ButterKnife.bind(this);



        cities = getCities();
        if(cities.size() == 0){
            showFabPrompt();
        }

        weatherServices = API.getApi().create(WeatherServices.class);

        layoutManager = new LinearLayoutManager(this);
        adapter = new CityWeatherAdapter(cities, R.layout.weather_card, this, new CityWeatherAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(CityWeather cityWeather, int position, View clickView) {
                Intent intent = new Intent(MainActivity.this,WeatherDetails.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                        MainActivity.this,clickView,
                        "weatherCardTransition");

                intent.putExtra("city",  cityWeather);
                startActivity(intent,options.toBundle());
            }
        });
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy >0) {
                    // Scroll Down
                    if (fabAddCity.isShown()) {
                        fabAddCity.hide();
                    }
                }
                else if (dy <0) {
                    // Scroll Up
                    if (!fabAddCity.isShown()) {
                        fabAddCity.show();
                    }
                }
            }
        });


        fabAddCity.setOnClickListener(view -> {
            showAlertAddCity("Add city","Type the city you want to add");
        });

        swipeRefreshLayout.setColorSchemeResources(R.color.google_blue, R.color.google_green, R.color.google_red, R.color.google_yellow);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            refreshData();
        });
        ItemTouchHelper.Callback callback = new ItemTouchHelperCallback((onSwipeListener) adapter);
        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);

    }
    //Still needs some fix
    public void recyclerScrollTo(int pos){
        recyclerView.scrollToPosition(pos);
    }
    public void showFabPrompt()
    {
        if (mFabPrompt != null)
        {
            return;
        }
        mFabPrompt = new MaterialTapTargetPrompt.Builder(MainActivity.this)
                .setTarget(findViewById(R.id.fabAddCity))
                .setFocalPadding(R.dimen.dp40)
                .setPrimaryText("Add your first City")
                .setSecondaryText("Tap the add button and add your favorites cities to get weather updates")
                .setBackButtonDismissEnabled(true)
                .setAnimationInterpolator(new FastOutSlowInInterpolator())
                .setPromptStateChangeListener((prompt, state) -> {
                    if (state == MaterialTapTargetPrompt.STATE_FOCAL_PRESSED || state == MaterialTapTargetPrompt.STATE_DISMISSING )
                    {
                        mFabPrompt = null;
                        //Do something such as storing a value so that this prompt is never shown again
                    }
                })
                .create();
        mFabPrompt.show();
    }



    private void refreshData() {
        for (int i = 0; i < cities.size(); i++) {
            updateCity(cities.get(i).getCity().getName(), i);
            System.out.println("CIUDAD #"+i);
        }
        System.out.println("TERMINE EL REFREHS!!!!");
        swipeRefreshLayout.setRefreshing(false);
    }

    private String cityToAdd ="";
    public void showAlertAddCity(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if(title!=null) builder.setTitle(title);
        if(message!=null) builder.setMessage(message);
        final View view = LayoutInflater.from(this).inflate(R.layout.dialog_add_city,null);
        builder.setView(view);
        final TextView editTextAddCityName = view.findViewById(R.id.editTextAddCityName);

        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cityToAdd = editTextAddCityName.getText().toString();
                addCity(cityToAdd);
                imm.toggleSoftInput(InputMethodManager.HIDE_NOT_ALWAYS,0);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                imm.toggleSoftInput(InputMethodManager.HIDE_NOT_ALWAYS,0);
                Toast.makeText(MainActivity.this,"Cancel",Toast.LENGTH_LONG).show();
            }
        });
        builder.create().show();
    }


    public void updateCity(String cityName, int index){
        Call<CityWeather> cityWeather = weatherServices.getWeatherCity(cityName, API.KEY, "metric",6);
        cityWeather.enqueue(new Callback<CityWeather>() {
            @Override
            public void onResponse(Call<CityWeather> call, Response<CityWeather> response) {
                if(response.code()==200){
                    CityWeather cityWeather = response.body();
                    cities.remove(index);
                    cities.add(index,cityWeather);
                    adapter.notifyItemChanged(index);
                }
            }

            @Override
            public void onFailure(Call<CityWeather> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Sorry, can't refresh right now.",Toast.LENGTH_LONG).show();
            }
        });
    }



    public void addCity(String cityName){
        Call<CityWeather> cityWeather = weatherServices.getWeatherCity(cityName, API.KEY, "metric",6);
        cityWeather.enqueue(new Callback<CityWeather>() {
            @Override
            public void onResponse(Call<CityWeather> call, Response<CityWeather> response) {
                if(response.code()==200){
                    CityWeather cityWeather = response.body();
                    cities.add(cityWeather);
                    adapter.notifyItemInserted(cities.size()-1);
                    recyclerView.scrollToPosition(cities.size()-1);

                }else{
                    Toast.makeText(MainActivity.this,"Sorry, city not found",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<CityWeather> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Sorry, weather services are currently unavailable",Toast.LENGTH_LONG).show();
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
