package com.example.pwpamiwlab1;

public interface WeatherService {

    String getLocationKeyByCity(String city);

    String getTemperatureByLocationKey(String locationKey);

    String getPastTemperatureByLocationKey(String locationKey);

    String getPastDayTemperatureByLocationKey(String locationKey);

    String getNext12HoursTemperatureByLocationKey(String locationKey);

    String getNextDayTemperatureByLocationKey(String locationKey);

    String getNext5DaysTemperatureByLocationKey(String locationKey);

}
