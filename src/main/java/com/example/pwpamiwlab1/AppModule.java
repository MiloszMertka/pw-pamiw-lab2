package com.example.pwpamiwlab1;

import com.example.pwpamiwlab1.service.WeatherService;
import com.example.pwpamiwlab1.service.internal.AccuWeatherService;
import com.google.inject.AbstractModule;

public class AppModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(WeatherService.class).to(AccuWeatherService.class);
    }

}
