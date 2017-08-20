package com.example.martinruiz.myapplication.adapters;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.martinruiz.myapplication.R;
import com.example.martinruiz.myapplication.interfaces.onSwipeListener;
import com.example.martinruiz.myapplication.models.CityWeather;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by MartinRuiz on 8/19/2017.
 */

public class CityWeatherAdapter extends RecyclerView.Adapter<CityWeatherAdapter.ViewHolder> implements onSwipeListener {
    private List<CityWeather> cities;
    private int layoutReference;
    private OnItemClickListener onItemClickListener;
    private Activity activity;

    public CityWeatherAdapter(List<CityWeather> cities, int layoutReference,  Activity activity,OnItemClickListener onItemClickListener) {
        this.cities = cities;
        this.layoutReference = layoutReference;
        this.activity = activity;
        this.onItemClickListener = onItemClickListener;

    }

    @Override
    public CityWeatherAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(layoutReference,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CityWeatherAdapter.ViewHolder holder, int position) {
        holder.bind(cities.get(position), onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    @Override
    public void onItemDelete(final int position) {
        cities.remove(position);
        notifyItemRemoved(position);
        System.out.println("DELETEEEEEEE BORRAR");
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textViewCardCityName) TextView textViewCityName;
        @BindView(R.id.textViewCardWeatherDescription) TextView textViewWeatherDescription;
        @BindView(R.id.textViewCardCurrentTemp) TextView textViewCurrentTemp;
        @BindView(R.id.textViewCardMaxTemp) TextView textViewMaxTemp;
        @BindView(R.id.textViewCardMinTemp) TextView  textViewMinTemp;
        @BindView(R.id.imageViewCardWeatherIcon) ImageView imageViewWeatherIcon;
        @BindView(R.id.cardViewWeatherCard) CardView cardViewWeather;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        public void bind(final CityWeather cityWeather, final OnItemClickListener onItemClickListener){
            textViewCityName.setText(cityWeather.getCity().getName()+", "+cityWeather.getCity().getCountry());
            textViewWeatherDescription.setText(cityWeather.getWeeklyWeather().get(0).getWeatherDetails().get(0).getLongDescription());
            textViewCurrentTemp.setText((int) cityWeather.getWeeklyWeather().get(0).getTemp().getDay()+"°");
            textViewMaxTemp.setText((int) cityWeather.getWeeklyWeather().get(0).getTemp().getMax()+"°");
            textViewMinTemp.setText((int) cityWeather.getWeeklyWeather().get(0).getTemp().getMin()+"°");
            Picasso.with(activity).load(R.mipmap.ic_clear).into(imageViewWeatherIcon);
            cardViewWeather.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(cityWeather,getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(CityWeather cityWeather , int position);
    }

}
