/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fi.tuni.prog3.weatherapp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.*;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;

/**
 * Represents a utility class for fetching weather data from the OpenWeatherMap API.
 *
 * @author janii, TottiK
 */
public class WeatherData implements iAPI{
    
    private static final String API_KEY="your-api-key";
    
    private static final String LOCATION_URL="https://api.openweathermap.org/geo/1.0/direct?q=%s,%s&appid=%s";
    
    private static final String CURRENT_WEATHER_URL="https://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&units=%s&appid=%s";
    // 5 day forecast 3 hour intervals
    private static final String FORECAST_GENERAL_URL="https://api.openweathermap.org/data/3.0/onecall?lat=%s&lon=%s&units=%s&appid=%s";
    // Hourly forecast
    private static final String FORECAST_HOURLY_URL="https://pro.openweathermap.org/data/2.5/forecast/hourly?lat=%s&lon=%s&units=%s&appid=%s";
    // 16 day forecast daily
    private static final String FORECAST_DAILY_URL="https://api.openweathermap.org/data/2.5/forecast/daily?lat=%s&lon=%s&cnt=16&units=%s&appid=%s";
    
    /**
     * Handles HTTP requests and responses asynchronously.
     *
     * @param urlString    The URL string for the HTTP request.
     * @param objectClass  The class of the object to parse the response into.
     * @return Object      The parsed object from the HTTP response.
     * @throws IOException            If an I/O error occurs.
     * @throws InterruptedException   If the operation is interrupted.
     */
    private static Object HttpHandler(String urlString, Class<?> objectClass) throws IOException, InterruptedException {

        HttpClient httpClient = HttpClient.newHttpClient();
        
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(urlString))
        .build();
        
        HttpResponse<String> httpResponse = HttpClient.newBuilder()
        .build()
        .send(request, BodyHandlers.ofString());

        Gson gson = new GsonBuilder()
        .setPrettyPrinting()
        .excludeFieldsWithoutExposeAnnotation()
        .create();
         
        try{
        checkRequestForErrors(httpResponse.body());
        }catch(IllegalStateException e){
            
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
        
        return gson.fromJson(httpResponse.body(), objectClass);
    }

    /**
     * Looks up the location of the given city and country.
     *
     * @param city      The city for which to look up the location.
     * @param country   The country code for the city.
     * @return Location[]  An array of locations matching the provided city and country.
     * @throws IOException            If an I/O error occurs.
     * @throws InterruptedException   If the operation is interrupted.
     */
    public static Location lookUpLocation(String city, String country) throws IOException, InterruptedException{
           
        String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8);
        
        String locationLookupUrl = String.format(LOCATION_URL, encodedCity, country, API_KEY);
                
        Location[] locations = (Location[]) HttpHandler(locationLookupUrl, Location[].class);
        
        if (locations.length == 0){
            throw new IllegalArgumentException("No such location!");
        }
        
        return locations[0];
        
    }

    /**
     * Fetches the current weather of given latitude and longitude.
     *
     * @param lat   The latitude of the location.
     * @param lon   The longitude of the location.
     * @param unit  The unit system for temperature measurement ("metric" or "imperial").
     * @return Weather  The current weather data.
     * @throws IOException            If an I/O error occurs.
     * @throws InterruptedException   If the operation is interrupted.
     */
    public static Weather getCurrentWeather(double lat, double lon, String unit) throws IOException, InterruptedException{
        
        String weatherUrl = String.format(CURRENT_WEATHER_URL, lat, lon, unit, API_KEY); 
        
        Weather weather = (Weather) HttpHandler(weatherUrl, Weather.class);
        
        return weather;
    }

    /**
     * Fetches the current weather of given location.
     *
     * @param location   The location.
     * @param unit  The unit system for temperature measurement ("metric" or "imperial").
     * @return Weather  The current weather data.
     * @throws IOException            If an I/O error occurs.
     * @throws InterruptedException   If the operation is interrupted.
     */
    public static Weather fetchCurrentWeather(Location location, String unit) throws IOException, InterruptedException{
        
        return getCurrentWeather(location.getLat(), location.getLon(), unit);
           
    }

    /**
     * Fetches the forecast based on given latitude and longitude.
     *
     * @param lat   The latitude of the location.
     * @param lon   The longitude of the location.
     * @param type  The type of forecast ("hourly", "daily", or "general").
     * @param unit  The unit system for measurement ("metric" or "imperial").
     * @return WeatherForecast  The forecast data.
     * @throws IOException            If an I/O error occurs.
     * @throws InterruptedException   If the operation is interrupted.
     */
    public static WeatherForecast getForecast(double lat, double lon, String type, String unit) throws IOException, InterruptedException {

        String forecastLookupUrl;
        switch (type) {
            case "hourly":
                forecastLookupUrl = String.format(FORECAST_HOURLY_URL, lat, lon, unit, API_KEY);
                break;
            case "daily":
                forecastLookupUrl = String.format(FORECAST_DAILY_URL, lat, lon, unit, API_KEY);
                break;
            default:
                forecastLookupUrl = String.format(FORECAST_GENERAL_URL, lat, lon, unit, API_KEY);
        }

         WeatherForecast forecastWeather = (WeatherForecast) HttpHandler(forecastLookupUrl, WeatherForecast.class);

        return forecastWeather;
    }
    
   /**
     * Fetches the 16 day forecast based on given latitude and longitude.
     *
     * @param lat   The latitude of the location.
     * @param lon   The longitude of the location.
     * @param type  The type of forecast ("hourly", "daily", or "general").
     * @param unit  The unit system for measurement ("metric" or "imperial").
     * @return WeatherForecast16  The 16 day forecast data.
     * @throws IOException            If an I/O error occurs.
     * @throws InterruptedException   If the operation is interrupted.
     */
    public static WeatherForecast16 getForecast16d(double lat, double lon, String type, String unit) throws IOException, InterruptedException {
        
        String forecastLookupUrl;
        switch (type) {
            case "hourly":
                forecastLookupUrl = String.format(FORECAST_HOURLY_URL, lat, lon, unit, API_KEY);
                break;
            case "daily":
                forecastLookupUrl = String.format(FORECAST_DAILY_URL, lat, lon, unit, API_KEY);
                break;
            default:
                forecastLookupUrl = String.format(FORECAST_GENERAL_URL, lat, lon, unit, API_KEY);
        }

        WeatherForecast16 forecastWeather = (WeatherForecast16) HttpHandler(forecastLookupUrl, WeatherForecast16.class);

        return forecastWeather;
    }

    /**
     * Fetches the forecast based on the given location.
     *
     * @param location  The location for which to fetch the forecast.
     * @param type      The type of forecast ("hourly", "daily", or "general").
     * @param unit      The unit system for measurement ("metric" or "imperial").
     * @return WeatherForecast  The forecast data.
     * @throws IOException            If an I/O error occurs.
     * @throws InterruptedException   If the operation is interrupted.
     */
    public static WeatherForecast fetchForecast(Location location, String type, String unit) throws IOException, InterruptedException{

        return getForecast(location.getLat(), location.getLon(), type, unit);          
    }
    
    /**
     * Handles the response from the API and throws an exception if the response contains an error.
     * 
     * At this stage, the HTTP request is OK. This method checks the response-JSON from the 
     * weather application logic point of view. 
     * 
     * @param reponse  The HTTP response body from the API.
     * @throws IllegalArgumentException if the response indicates an error.
     */
    public static void checkRequestForErrors(String reponse) throws IllegalArgumentException{
        
        JsonObject jsonObject;
        try {
            jsonObject = JsonParser.parseString​(reponse).getAsJsonObject();
        } catch(IllegalStateException e) {
            throw new IllegalStateException();
        }
         
        JsonElement errorCodeElement = jsonObject.get("cod");

        if (errorCodeElement.getAsString().equals("200")){
            return;
        }
        
        JsonElement errorMessageElement = jsonObject.get("message");
        
        switch (errorCodeElement.getAsInt()){
            case 400:
                throw new IllegalArgumentException(errorMessageElement.getAsString());
            case 401:
                throw new IllegalArgumentException(errorMessageElement.getAsString());
            case 404:
                throw new IllegalArgumentException(errorMessageElement.getAsString());
            case 429:
                throw new IllegalArgumentException(errorMessageElement.getAsString());
            default:
                break;
        }
    }   
}
