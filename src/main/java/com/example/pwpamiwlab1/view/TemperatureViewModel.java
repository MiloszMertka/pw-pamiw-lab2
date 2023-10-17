package com.example.pwpamiwlab1.view;

public record TemperatureViewModel(
        String currentTemperature,
        String pastTemperature,
        String pastDayTemperature,
        String next12HoursTemperature,
        String nextDayTemperature,
        String next5DaysTemperature
) {

}
