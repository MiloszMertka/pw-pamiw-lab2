package com.example.pwpamiwlab1;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;

public class AccuWeatherService implements WeatherService {

    private static final String BASE_URL = "http://dataservice.accuweather.com";
    private static final String API_KEY = "AAL3XyBGuXrBPAWLQyZHDg8tSGrrallb";
    private static final String LANGUAGE = "pl";
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String getLocationKeyByCity(String city) {
        final var url = BASE_URL + "/locations/v1/cities/autocomplete?apikey=" + API_KEY + "&q=" + city + "&language=" + LANGUAGE;
        final var json = getJsonResponseFromApi(url);
        return json.get(0).get("Key").asText();
    }

    @Override
    public String getTemperatureByLocationKey(String locationKey) {
        final var url = BASE_URL + "/currentconditions/v1/" + locationKey + "?apikey=" + API_KEY + "&language=" + LANGUAGE;
        final var json = getJsonResponseFromApi(url);
        return json.get(0).get("Temperature").get("Metric").get("Value").asText();
    }

    @Override
    public String getPastTemperatureByLocationKey(String locationKey) {
        final var url = BASE_URL + "/currentconditions/v1/" + locationKey + "/historical?apikey=" + API_KEY + "&language=" + LANGUAGE;
        final var json = getJsonResponseFromApi(url);
        return json.get(5).get("Temperature").get("Metric").get("Value").asText();
    }

    @Override
    public String getPastDayTemperatureByLocationKey(String locationKey) {
        final var url = BASE_URL + "/currentconditions/v1/" + locationKey + "/historical/24?apikey=" + API_KEY + "&language=" + LANGUAGE;
        final var json = getJsonResponseFromApi(url);
        return json.get(23).get("Temperature").get("Metric").get("Value").asText();
    }

    @Override
    public String getNext12HoursTemperatureByLocationKey(String locationKey) {
        final var url = BASE_URL + "/forecasts/v1/hourly/12hour/" + locationKey + "?apikey=" + API_KEY + "&language=" + LANGUAGE + "&metric=true";
        final var json = getJsonResponseFromApi(url);
        return json.get(11).get("Temperature").get("Value").asText();
    }

    @Override
    public String getNextDayTemperatureByLocationKey(String locationKey) {
        final var url = BASE_URL + "/forecasts/v1/daily/1day/" + locationKey + "?apikey=" + API_KEY + "&language=" + LANGUAGE + "&metric=true";
        final var json = getJsonResponseFromApi(url);
        final var temperatureJson = json.get("DailyForecasts").get(0).get("Temperature");
        final var minimumTemperature = temperatureJson.get("Minimum").get("Value").asDouble();
        final var maximumTemperature = temperatureJson.get("Maximum").get("Value").asDouble();
        final var averageTemperature = (minimumTemperature + maximumTemperature) / 2;
        return String.valueOf(averageTemperature);
    }

    @Override
    public String getNext5DaysTemperatureByLocationKey(String locationKey) {
        final var url = BASE_URL + "/forecasts/v1/daily/5day/" + locationKey + "?apikey=" + API_KEY + "&language=" + LANGUAGE + "&metric=true";
        final var json = getJsonResponseFromApi(url);
        System.out.println(json);
        final var temperatureJson = json.get("DailyForecasts").get(4).get("Temperature");
        final var minimumTemperature = temperatureJson.get("Minimum").get("Value").asDouble();
        final var maximumTemperature = temperatureJson.get("Maximum").get("Value").asDouble();
        final var averageTemperature = (minimumTemperature + maximumTemperature) / 2;
        return String.valueOf(averageTemperature);
    }

    private JsonNode getJsonResponseFromApi(String url) {
        try {
            final var uri = new URI(url);
            final var getLocationKeyRequest = HttpRequest.newBuilder()
                    .uri(uri)
                    .GET()
                    .build();
            final var response = httpClient.send(getLocationKeyRequest, BodyHandlers.ofString());
            final var body = response.body();
            return objectMapper.readTree(body);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

}
