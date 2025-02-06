package fi.tuni.prog3.weatherapp;

import java.io.IOException;

/**
 * Interface for extracting data from the OpenWeatherMap API.
 * @author ??
 */
public interface iAPI {

    /**
     * Returns coordinates for a location.
     * @param city The city which weather we want
     * @param country The county where the city is located
     * @return String.
     * @throws IOException if an I/O error occurs while performing the operation.
     * @throws InterruptedException if the operation is interrupted.
     */
    static Location lookUpLocation(String city, String country) throws IOException, InterruptedException{
        return null;
    }

    /**
     * Returns the current weather for the given coordinates.
     * @param lat The latitude of the location.
     * @param lon The longitude of the location.
     * @return Weather.
     */
    static Weather getCurrentWeather(double lat, double lon){
        return null;
    }

    /**
     * Returns a forecast for the given coordinates.
     * @param lat The latitude of the location.
     * @param lon The longitude of the location.
     * @param type The type of forecast to get. (hourly, daily, general)
     * @return WeatherForecast.
     */
    static WeatherForecast getForecast(double lat, double lon, String type) {
        return null;
    }
}
