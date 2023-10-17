package com.example.pwpamiwlab1.service;

import com.example.pwpamiwlab1.model.Location;
import com.example.pwpamiwlab1.model.Temperature;

public interface WeatherService {

    Location getLocationByCity(String city);

    Temperature getTemperatureByLocation(Location location);

    Temperature getPastTemperatureByLocation(Location location);

    Temperature getPastDayTemperatureByLocation(Location location);

    Temperature getNext12HoursTemperatureByLocation(Location location);

    Temperature getNextDayTemperatureByLocation(Location location);

    Temperature getNext5DaysTemperatureByLocation(Location location);

}
