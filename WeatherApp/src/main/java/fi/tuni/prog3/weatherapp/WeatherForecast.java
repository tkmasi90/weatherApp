package fi.tuni.prog3.weatherapp;

import com.google.gson.annotations.Expose;
import java.util.List;


/**
 * Class for OpenWeatherMap API forecast weather data.
 * @author TottiK, Copilot
 * Javadocs by TottiK, Copilot
 */
public class WeatherForecast extends Weather {
    
    @Expose
    private int cnt; // number of forecast items
    @Expose
    private List<Weather> list;
    
    /**
     * Get the forecast weather data count.
     * @return the count of forecast items.
     */
    public int getCnt() {
        return cnt;
    }
    
    /**
     * Set the forecast weather data count.
     * @param cnt the count of forecast items.
     */
    public void setCnt(int cnt) {
        this.cnt = cnt;
    }
    
    /**
     * Get the forecast weather data list.
     * @return the list of forecast items.
     */
    public List<Weather> getList() {
        return list;
    }
    
    /**
     * Set the forecast weather data list.
     * @param list the list of forecast items.
     */
    public void setList(List<Weather> list) {
        this.list = list;
    }
}



