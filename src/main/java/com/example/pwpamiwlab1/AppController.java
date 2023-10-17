package com.example.pwpamiwlab1;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AppController {

    private final WeatherService weatherService = new AccuWeatherService();

    @FXML
    private TextField cityField;

    @FXML
    private Label currentTemperatureLabel;

    @FXML
    private Label pastTemperatureLabel;

    @FXML
    private Label pastDayTemperatureLabel;

    @FXML
    private Label next12HoursTemperatureLabel;

    @FXML
    private Label nextDayTemperatureLabel;

    @FXML
    private Label next5DaysTemperatureLabel;

    @FXML
    private void onSearchButtonClick() {
        final var locationKey = getLocationKey();
        getTemperatureForLocation(locationKey);
        getPastTemperatureForLocation(locationKey);
        getPastDayTemperatureForLocation(locationKey);
        getNext12HoursTemperatureForLocation(locationKey);
        getNextDayTemperatureForLocation(locationKey);
        getNext5DaysTemperatureForLocation(locationKey);
    }

    private String getLocationKey() {
        final var city = cityField.getText();
        return weatherService.getLocationKeyByCity(city);
    }

    private void getTemperatureForLocation(String locationKey) {
        final var temperature = weatherService.getTemperatureByLocationKey(locationKey);
        currentTemperatureLabel.setText(temperature + " st. C");
    }

    private void getPastTemperatureForLocation(String locationKey) {
        final var temperature = weatherService.getPastTemperatureByLocationKey(locationKey);
        pastTemperatureLabel.setText(temperature + " st. C");
    }

    private void getPastDayTemperatureForLocation(String locationKey) {
        final var temperature = weatherService.getPastDayTemperatureByLocationKey(locationKey);
        pastDayTemperatureLabel.setText(temperature + " st. C");
    }

    private void getNext12HoursTemperatureForLocation(String locationKey) {
        final var temperature = weatherService.getNext12HoursTemperatureByLocationKey(locationKey);
        next12HoursTemperatureLabel.setText(temperature + " st. C");
    }

    private void getNextDayTemperatureForLocation(String locationKey) {
        final var temperature = weatherService.getNextDayTemperatureByLocationKey(locationKey);
        nextDayTemperatureLabel.setText(temperature + " st. C");
    }

    private void getNext5DaysTemperatureForLocation(String locationKey) {
        final var temperature = weatherService.getNext5DaysTemperatureByLocationKey(locationKey);
        next5DaysTemperatureLabel.setText(temperature + " st. C");
    }

}