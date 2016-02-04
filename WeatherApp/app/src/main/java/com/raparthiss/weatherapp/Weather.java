package com.raparthiss.weatherapp;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.raparthiss.weatherapp.data.Channel;
import com.raparthiss.weatherapp.service.CallBackInterface;
import com.raparthiss.weatherapp.service.YahooWeatherService;

public class Weather extends AppCompatActivity implements CallBackInterface {

    private ImageView weatherIcon;
    private TextView temperatureText;
    private TextView conditionText;
    private TextView locationText;

    private YahooWeatherService yahoo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        weatherIcon = (ImageView) findViewById(R.id.weatherIconImageView);
        temperatureText = (TextView) findViewById(R.id.temperatureTextView);
        conditionText = (TextView) findViewById(R.id.conditionTextView);
        locationText = (TextView) findViewById(R.id.locationTextView);

        yahoo = new YahooWeatherService(this);
        yahoo.refresher("Kansas City, MO");
    }

    //@TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void success(Channel channel) {
        int image = getResources().getIdentifier("drawable/icon_"+channel.getItem().getCondition().getCode(), null, getPackageName());
        @SuppressWarnings("deprecation")
        Drawable icon = getResources().getDrawable(image);

        weatherIcon.setImageDrawable(icon);
        temperatureText.setText(channel.getItem().getCondition().getTemp()+"\u00B0"+channel.getUnits().getTempUnit());
        conditionText.setText(channel.getItem().getCondition().getNotes());
        locationText.setText(yahoo.getLocation());
    }

    @Override
    public void failure(Exception exp) {
        Toast.makeText(this, exp.getMessage(), Toast.LENGTH_LONG).show();
    }
}
