/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fi.tuni.prog3.weatherapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/** Represents current weather at a location
 * 
 * @author janii, TottiK, Copilot
 * Javadocs by TottiK, Copilot
 */
public class Weather {
  
    @Expose
    private List<WeatherItem> weather;
    @Expose
    private Main main;
    @Expose
    private int visibility;
    @Expose
    private Wind wind;
    @Expose
    private Rain rain;
    @Expose
    private Snow snow;
    @Expose
    private Clouds clouds;
    @Expose
    private long dt;
    @Expose
    private Sys sys;
    @Expose
    private int timezone;
    @Expose
    private int id;
    @Expose
    private String name;
    @Expose
    private int cod;    


    /**
     * Weather class containing basic data, e.g., id, icon, and description.
     */  
    public class WeatherItem {

        @Expose
        private int id;
        @Expose
        private String main; //group of weather parameters (Rain, Snow, Clouds etc.)
        @Expose
        private String description; //weather condition within the group
        @Expose
        private String icon;

        /**
         * Get the weather id
         * @return weather id
         */
        public int getId() {
            return id;
        }

        /**
         * Set the weather id
         * @param id, int value of the weather id
         */
        public void setId(int id) {
            this.id = id;
        }

        /**
         * get group of weather parameters
         * @return main, group of weather parameters 
         */
        public String getMain() {
            return main;
        }

        /**
         * Set group of weather parameters
         * @param main, String value of the group of weather parameters
         */
        public void setMain(String main) {
            this.main = main;
        }

        /**
         * Get weather description
         * @return weather description
         */
        public String getDescription() {
            return description;
        }

        /**
         * Set weather description
         * @param description, String value of the weather description
         */
        public void setDescription(String description) {
            this.description = description;
        }

        /**
         * Get weather icon
         * @return weather icon
         */
        public String getIcon() {
            return icon;
        }

        /**
         * Set weather icon
         * @param icon, String value of the weather icon
         */
        public void setIcon(String icon) {
            this.icon = icon;
        }
    }

    /**
     * Main class containing weather data, e.g., temperatures and humidity.
     */ 
    public class Main {

        @Expose
        private double temp;
        @Expose
        private double feels_like;
        @Expose
        private double temp_min;
        @Expose
        private double temp_max;
        @Expose
        private int pressure;
        @Expose
        private int humidity;

        /**
         * Get temperature
         * @return double value of the temperature
         */
        public double getTemp() {
            return temp;
        }

        /**
         * Set temperature
         * @param temp, double value of the temperature
         */
        public void setTemp(double temp) {
            this.temp = temp;
        }

        /**
         * Get feels like temperature
         * @return double value of the feels like temperature
         */
        public double getFeels_like() {
            return feels_like;
        }

        /**
         * Set feels like temperature
         * @param feels_like, double value of the feels like temperature
         */
        public void setFeels_like(double feels_like) {
            this.feels_like = feels_like;
        }

        /**
         * Get minimum temperature
         * @return double value of the minimum temperature
         */
        public double getTemp_min() {
            return temp_min;
        }

        /**
         * Set minimum temperature
         * @param temp_min, double value of the minimum temperature
         */
        public void setTemp_min(double temp_min) {
            this.temp_min = temp_min;
        }

        /**
         * Get maximum temperature
         * @return double value of the maximum temperature
         */
        public double getTemp_max() {
            return temp_max;
        }

        /**
         * Set maximum temperature
         * @param temp_max, double value of the maximum temperature
         */
        public void setTemp_max(double temp_max) {
            this.temp_max = temp_max;
        }

        /**
         * Get pressure
         * @return int value of the pressure
         */
        public int getPressure() {
            return pressure;
        }

        /**
         * Set pressure
         * @param pressure, int value of the pressure
         */
        public void setPressure(int pressure) {
            this.pressure = pressure;
        }

        /**
         * Get humidity
         * @return int value of the humidity
         */
        public int getHumidity() {
            return humidity;
        }

        /**
         * Set humidity
         * @param humidity, int value of the humidity
         */
        public void setHumidity(int humidity) {
            this.humidity = humidity;
        }
    }

    /**
     * Wind class containing wind-related data, speed and direction.
     */ 
    public class Wind {

        @Expose
        private double speed;
        @Expose
        private int deg;

        /**
         * Get wind speed
         * @return double value of the wind speed
         */
        public double getSpeed() {
            return speed;
        }

        /**
         * Set wind speed
         * @param speed, double value of the wind speed
         */
        public void setSpeed(double speed) {
            this.speed = speed;
        }

        /**
         * Get wind direction
         * @return int value of the wind direction
         */
        public int getDeg() {
            return deg;
        }

        /**
         * Set wind direction
         * @param deg, int value of the wind direction (degrees).
         */
        public void setDeg(int deg) {
            this.deg = deg;
        }
    }

    /**
     * Clouds class containing cloud data.
     */ 
    public class Clouds {

        @Expose
        private int all;

        /**
         * Get all clouds
         * @return int value of all clouds
         */
        public int getAll() {
            return all;
        }

        /**
         * Set all clouds
         * @param all, int value of all clouds
         */
        public void setAll(int all) {
            this.all = all;
        }
    }

    /**
     * Rain class containing rain data.
     */
    public class Rain {

        @Expose
        @SerializedName("1h")
        private double _1h;
        @Expose
        @SerializedName("3h")
        private double _3h;
        
        /**
         * Get rain data for the last hour
         * @return double value of rain data for the last hour
         */
        public double get_1h() {
            return _1h;
        }
        
        /**
         * Set rain data for the last hour
         * @param _1h, double value of rain data for the last hour
         */
        public void set_1h(double _1h) {
            this._1h = _1h;
        }
        
        /**
         * Get rain data for the last 3 hours
         * @return double value of rain data for the last 3 hours
         */
        public double get_3h() {
            return _3h;
        }
        
        /**
         * Set rain data for the last 3 hours
         * @param _3h, double value of rain data for the last 3 hours
         */
        public void set_3h(double _3h) {
            this._3h = _3h;
        }
    }
    /**
     * Snow class containing snow data.
     */
    public class Snow {

        @Expose
        @SerializedName("1h")
        private double _1h;
        @Expose
        @SerializedName("3h")
        private double _3h;
        
        /**
         * Get snow data for the last hour
         * @return double value of snow data for the last hour
         */
        public double get_1h() {
            return _1h;
        }
        
        /**
         * Set snow data for the last hour
         * @param _1h, double value of snow data for the last hour
         */
        public void set_1h(double _1h) {
            this._1h = _1h;
        }
        
        /**
         * Get snow data for the last 3 hours
         * @return double value of snow data for the last 3 hours
         */
        public double get_3h() {
            return _3h;
        }
        
        /**
         * Set snow data for the last 3 hours
         * @param _3h, double value of snow data for the last 3 hours
         */
        public void set_3h(double _3h) {
            this._3h = _3h;
        }
    }

    /**
     * Sys class containing numeric data, e.g., country and sunrise time.
     */ 
    public class Sys {

        @Expose
        private String country;
        @Expose
        private long sunrise;
        @Expose
        private long sunset;

        /**
         * Get country
         * @return country as a string
         */
        public String getCountry() {
            return country;
        }

        /**
         * Set country
         * @param country, string value of the country
         */
        public void setCountry(String country) {
            this.country = country;
        }

        /**
         * Get sunrise time
         * @return sunrise time
         */
        public long getSunrise() {
            return sunrise;
        }

        /**
         * Set sunrise time
         * @param sunrise, long value of the sunrise time, unix time
         */
        public void setSunrise(long sunrise) {
            this.sunrise = sunrise;
        }

        /**
         * Get sunset time
         * @return sunset time
         */
        public long getSunset() {
            return sunset;
        }

        /**
         * Set sunset time
         * @param sunset, long value of the sunset time, unix time
         */
        public void setSunset(long sunset) {
            this.sunset = sunset;
        }


    }

    /**
     * Get temperature
     * @return temperature
     */
    public double getTemp(){
        return this.main.temp;
    }

    /**
     * Get weather
     * @return list of weather objects
     */
    public List<WeatherItem> getWeather() {
        return weather;
    }

    /**
     * Set weather
     * @param weather, list of Weather objects
     */
    public void setWeather(List<WeatherItem> weather) {
        this.weather = weather;
    }

    /**
     * Get main
     * @return main
     */
    public Main getMain() {
        return main;
    }

    /**
     * Set main
     * @param main, Main object
     */
    public void setMain(Main main) {
        this.main = main;
    }

    /**
     * Get visibility
     * @return visibility
     */
    public int getVisibility() {
        return visibility;
    }

    /**
     * Set visibility
     * @param visibility, int value of the visibility
     */
    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    /**
     * Get wind
     * @return wind
     */
    public Wind getWind() {
        return wind;
    }
    
    /**
     * Set wind
     * @param wind, Wind object
     */
    public void setWind(Wind wind) {
        this.wind = wind;
    }
    
    /**
     * Get rain
     * @return rain object
     */
    public Rain getRain() {
        return rain;
    }
    
    /**
     * Set rain
     * @param rain, Rain object
     */
    public void setRain(Rain rain) {
        this.rain = rain;
    }

    /**
     * Get snow
     * @return snow object
     */
    public Snow getSnow() {
        return snow;
    }

    /**
     * Set snow
     * @param snow, Snow object
     */
    public void setSnow(Snow snow) {
        this.snow = snow;
    }

    /**
     * Get clouds
     * @return clouds
     */
    public Clouds getClouds() {
        return clouds;
    }

    /**
     * Set clouds
     * @param clouds, Clouds object
     */
    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    /**
     * Get dt
     * @return dt
     */
    public long getDt() {
        return dt;
    }

    /**
     * Set dt
     * @param dt, long value of the dt
     */
    public void setDt(long dt) {
        this.dt = dt;
    }

    /**
     * Get sys
     * @return sys
     */
    public Sys getSys() {
        return sys;
    }

    /**
     * Set sys
     * @param sys, Sys object
     */
    public void setSys(Sys sys) {
        this.sys = sys;
    }

    /**
     * Get timezone
     * @return timezone
     */
    public int getTimezone() {
        return timezone;
    }

    /**
     * Set timezone
     * @param timezone, int value of the timezone
     */
    public void setTimezone(int timezone) {
        this.timezone = timezone;
    }

    /**
     * Get Cod
     * @return cod
     */
    public int getCod() {
        return cod;
    }

    /**
     * Set cod
     * @param cod, int value of the cod
     */
    public void setCod(int cod) {
        this.cod = cod;
    }
}
