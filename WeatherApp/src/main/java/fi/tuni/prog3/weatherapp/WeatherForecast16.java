/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fi.tuni.prog3.weatherapp;

import com.google.gson.annotations.Expose;
import java.util.List;

/**
 * Class for OpenWeatherMap API 16 day forecast weather data.
 * @author Make
 * Javadocs by TottiK, Copilot
 */
public class WeatherForecast16 {

    @Expose
    private int cod;
    @Expose
    private int timezone;
    @Expose
    private int cnt;
    @Expose
    private List<WeatherInfo> list;

    /**
     * Get the cod of the forecast.
     * @return the cod of the forecast.
     */
    public int getCod() {
        return cod;
    }

    /**
     * Set the cod of the forecast.
     * @param cod the cod of the forecast.
     */
    public void setCod(int cod) {
        this.cod = cod;
    }
    
    /**
     * Get the timezone of the forecast.
     * @return the timezone of the forecast.
     */
    public int getTimezone() {
        return timezone;
    }

    /**
     * Set the timezone of the forecast.
     * @param timezone the timezone of the forecast.
     */
    public void setTimezone(int timezone) {
        this.timezone = timezone;
    }

    /**
     * Get the count of forecast items.
     * @return the count of forecast items.
     */
    public int getCnt() {
        return cnt;
    }

    /**
     * Set the count of forecast items.
     * @param cnt the count of forecast items.
     */
    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    /**
     * Get the list of forecast items.
     * @return the list of forecast items.
     */
    public List<WeatherInfo> getList() {
        return list;
    }

    /**
     * Set the list of forecast items.
     * @param list the list of forecast items.
     */
    public void setList(List<WeatherInfo> list) {
        this.list = list;
    }

    /**
     * Class for the forecast weather data.
     */
    public static class WeatherInfo {

        @Expose
        private long dt;
        @Expose
        private long sunrise;
        @Expose
        private long sunset;
        @Expose
        private Temperature temp;
        @Expose
        private Temperature feels_like;
        @Expose
        private int pressure;
        @Expose
        private int humidity;
        @Expose
        private List<WeatherItem> weather;
        @Expose
        private double speed;
        @Expose
        private int deg;
        @Expose
        private double gust;
        @Expose
        private int clouds;
        @Expose
        private double pop;
        @Expose
        private double rain;

        /**
         * Get the date and time of the forecast.
         * @return the date and time of the forecast. unix utc.
         */
        public long getDt() {
            return dt;
        }

        /**
         * Set the date and time of the forecast.
         * @param dt the date and time of the forecast. unix utc.
         */
        public void setDt(long dt) {
            this.dt = dt;
        }

        /**
         * Get the sunrise time of the forecast.
         * @return the sunrise time of the forecast. unix utc.
         */
        public long getSunrise() {
            return sunrise;
        }

        /**
         * Set the sunrise time of the forecast.
         * @param sunrise the sunrise time of the forecast. unix utc.
         */
        public void setSunrise(long sunrise) {
            this.sunrise = sunrise;
        }

        /**
         * Get the sunset time of the forecast.
         * @return the sunset time of the forecast. unix utc.
         */
        public long getSunset() {
            return sunset;
        }

        /**
         * Set the sunset time of the forecast.
         * @param sunset the sunset time of the forecast. unix utc.
         */
        public void setSunset(long sunset) {
            this.sunset = sunset;
        }

        /**
         * Get the temperature of the forecast.
         * @return the temperature of the forecast.
         */
        public Temperature getTemp() {
            return temp;
        }

        /**
         * Set the temperature of the forecast.
         * @param temp the temperature of the forecast.
         */
        public void setTemp(Temperature temp) {
            this.temp = temp;
        }

        /**
         * Get the feels like temperature of the forecast.
         * @return the feels like temperature of the forecast.
         */
        public Temperature getFeels_like() {
            return feels_like;
        }

        /**
         * Set the feels like temperature of the forecast.
         * @param feels_like the feels like temperature of the forecast.
         */
        public void setFeels_like(Temperature feels_like) {
            this.feels_like = feels_like;
        }

        /**
         * Get the pressure of the forecast.
         * @return the pressure of the forecast.
         */
        public int getPressure() {
            return pressure;
        }

        /**
         * Set the pressure of the forecast.
         * @param pressure the pressure of the forecast.
         */
        public void setPressure(int pressure) {
            this.pressure = pressure;
        }

        /**
         * Get the humidity of the forecast.
         * @return the humidity of the forecast.
         */
        public int getHumidity() {
            return humidity;
        }

        /**
         * Set the humidity of the forecast.
         * @param humidity the humidity of the forecast.
         */
        public void setHumidity(int humidity) {
            this.humidity = humidity;
        }

        /**
         * Get the weather of the forecast.
         * @return the weather of the forecast.
         */
        public List<WeatherItem> getWeather() {
            return weather;
        }

        /**
         * Set the weather of the forecast.
         * @param weather the weather of the forecast.
         */
        public void setWeather(List<WeatherItem> weather) {
            this.weather = weather;
        }

        /**
         * Get the wind speed of the forecast.
         * @return the wind speed of the forecast.
         */
        public double getSpeed() {
            return speed;
        }

        /**
         * Set the wind speed of the forecast.
         * @param speed the wind speed of the forecast.
         */
        public void setSpeed(double speed) {
            this.speed = speed;
        }

        /**
         * Get the wind direction of the forecast.
         * @return the wind direction of the forecast.
         */
        public int getDeg() {
            return deg;
        }

        /**
         * Set the wind direction of the forecast.
         * @param deg the wind direction of the forecast.
         */
        public void setDeg(int deg) {
            this.deg = deg;
        }

        /**
         * Get the wind gust of the forecast.
         * @return the wind gust of the forecast.
         */
        public double getGust() {
            return gust;
        }

        /**
         * Set the wind gust of the forecast.
         * @param gust the wind gust of the forecast.
         */
        public void setGust(double gust) {
            this.gust = gust;
        }

        /**
         * Get the cloudiness of the forecast.
         * @return the cloudiness of the forecast.
         */
        public int getClouds() {
            return clouds;
        }

        /**
         * Set the cloudiness of the forecast.
         * @param clouds the cloudiness of the forecast.
         */
        public void setClouds(int clouds) {
            this.clouds = clouds;
        }

        /**
         * Get the probability of precipitation of the forecast.
         * @return the probability of precipitation of the forecast.
         */
        public double getPop() {
            return pop;
        }

        /**
         * Set the probability of precipitation of the forecast.
         * @param pop the probability of precipitation of the forecast.
         */
        public void setPop(double pop) {
            this.pop = pop;
        }

        /**
         * Get the rain of the forecast.
         * @return the rain of the forecast.
         */
        public double getRain() {
            return rain;
        }

        /**
         * Set the rain of the forecast.
         * @param rain the rain of the forecast.
         */
        public void setRain(double rain) {
            this.rain = rain;
        }
    }

    /**
     * Class for the temperature data of the forecast.
     */
    public static class Temperature {

        @Expose
        private double day;
        @Expose
        private double min;
        @Expose
        private double max;
        @Expose
        private double night;
        @Expose
        private double eve;
        @Expose
        private double morn;

        /**
         * Get the day temperature of the forecast.
         * @return the day temperature of the forecast.
         */
        public double getDay() {
            return day;
        }

        /**
         * Set the day temperature of the forecast.
         * @param day the day temperature of the forecast.
         */
        public void setDay(double day) {
            this.day = day;
        }

        /**
         * Get the minimum temperature of the forecast.
         * @return the minimum temperature of the forecast.
         */
        public double getMin() {
            return min;
        }

        /**
         * Set the minimum temperature of the forecast.
         * @param min the minimum temperature of the forecast.
         */
        public void setMin(double min) {
            this.min = min;
        }

        /**
         * Get the maximum temperature of the forecast.
         * @return the maximum temperature of the forecast.
         */
        public double getMax() {
            return max;
        }

        /**
         * Set the maximum temperature of the forecast.
         * @param max the maximum temperature of the forecast.
         */
        public void setMax(double max) {
            this.max = max;
        }

        /**
         * Get the night temperature of the forecast.
         * @return the night temperature of the forecast.
         */
        public double getNight() {
            return night;
        }

        /**
         * Set the night temperature of the forecast.
         * @param night the night temperature of the forecast.
         */
        public void setNight(double night) {
            this.night = night;
        }

        /**
         * Get the evening temperature of the forecast.
         * @return the evening temperature of the forecast.
         */
        public double getEve() {
            return eve;
        }

        /**
         * Set the evening temperature of the forecast.
         * @param eve the evening temperature of the forecast.
         */
        public void setEve(double eve) {
            this.eve = eve;
        }

        /**
         * Get the morning temperature of the forecast.
         * @return the morning temperature of the forecast.
         */
        public double getMorn() {
            return morn;
        }

        /**
         * Set the morning temperature of the forecast.
         * @param morn the morning temperature of the forecast.
         */
        public void setMorn(double morn) {
            this.morn = morn;
        }
    }

    /**
     * Class for forecast weather feels like temperature items.
     */
    public class FeelsLike {

        @Expose
        private double day;
        @Expose
        private double night;
        @Expose
        private double eve;
        @Expose
        private double morn;

        /**
         * Get the day temperature of the forecast.
         * @return the day temperature of the forecast.
         */
        public double getDay() {
            return day;
        }

        /**
         * Set the day temperature of the forecast.
         * @param day the day temperature of the forecast.
         */
        public void setDay(double day) {
            this.day = day;
        }

        /**
         * Get the night temperature of the forecast.
         * @return the night temperature of the forecast.
         */
        public double getNight() {
            return night;
        }

        /**
         * Set the night temperature of the forecast.
         * @param night the night temperature of the forecast.
         */
        public void setNight(double night) {
            this.night = night;
        }

        /**
         * Get the evening temperature of the forecast.
         * @return the evening temperature of the forecast.
         */
        public double getEve() {
            return eve;
        }

        /**
         * Set the evening temperature of the forecast.
         * @param eve the evening temperature of the forecast.
         */
        public void setEve(double eve) {
            this.eve = eve;
        }

        /**
         * Get the morning temperature of the forecast.
         * @return the morning temperature of the forecast.
         */
        public double getMorn() {
            return morn;
        }

        /**
         * Set the morning temperature of the forecast.
         * @param morn the morning temperature of the forecast.
         */
        public void setMorn(double morn) {
            this.morn = morn;
        }
    
    }
    
    /**
    * Represents a weather item with forecast details.
    */
    public class WeatherItem {

        @Expose
        private int id;
        @Expose
        private String main;
        @Expose
        private String description;
        @Expose
        private String icon;

        /**
         * Get the weather id of the forecast.
         * @return the weather id of the forecast.
         */
        public int getId() {
            return id;
        }

        /**
         * Set the weather id of the forecast.
         * @param id the weather id of the forecast.
         */
        public void setId(int id) {
            this.id = id;
        }

        /**
         * Get the main weather of the forecast.
         * @return the main weather of the forecast.
         */
        public String getMain() {
            return main;
        }

        /**
         * Set the main weather of the forecast.
         * @param main the main weather of the forecast.
         */
        public void setMain(String main) {
            this.main = main;
        }

        /**
         * Get the description of the forecast.
         * @return the description of the forecast.
         */
        public String getDescription() {
            return description;
        }

        /**
         * Set the description of the forecast.
         * @param description the description of the forecast.
         */
        public void setDescription(String description) {
            this.description = description;
        }

        /**
         * Get the icon of the forecast.
         * @return the icon of the forecast.
         */    
        public String getIcon() {
            return icon;
        }

        /**
         * Set the icon of the forecast.
         * @param icon the icon of the forecast.
         */
        public void setIcon(String icon) {
            this.icon = icon;
        }
    }
}