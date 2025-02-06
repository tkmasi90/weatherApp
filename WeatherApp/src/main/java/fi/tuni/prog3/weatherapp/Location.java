/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fi.tuni.prog3.weatherapp;

import com.google.gson.annotations.Expose;
import java.util.Map;
import java.util.Objects;

/**
 * Class for Location object. Contains information about the location of the weather data.
 * The location object contains the name of the location, the local names of the location,
 * the latitude and longitude of the location and the country of the location.
 * 
 * @author janii
 * Javadocs by TottiK, Copilot
 */
public class Location {

    @Expose
    private String name;
    private Map<String, String> local_names;
    @Expose
    private double lat;
    @Expose
    private double lon;
    @Expose
    private String country;

    /**
     * Constructor for the Location object.
     * @return name as a string.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the location.
     * @param name, the name of the location.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the local names of the location (translations).
     * @return local_names as a map.
     */
    public Map<String, String> getLocalNames() {
        return local_names;
    }

    /**
     * Sets the local names of the location.
     * @param local_names, the local names of the location.
     */
    public void setLocalNames(Map<String, String> local_names) {
        this.local_names = local_names;
    }

    /**
     * Returns the latitude of the location.
     * @return lat as a double.
     */
    public double getLat() {
        return lat;
    }

    /**
     * Sets the latitude of the location.
     * @param lat, the latitude of the location.
     */
    public void setLat(double lat) {
        this.lat = lat;
    }

    /**
     * Returns the longitude of the location.
     * @return lon as a double.
     */
    public double getLon() {
        return lon;
    }

    /**
     * Sets the longitude of the location.
     * @param lon, the longitude of the location.
     */
    public void setLon(double lon) {
        this.lon = lon;
    }

    /**
     * Returns the country of the location.
     * @return country as a string.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the country of the location.
     * @param country, the country of the location.
     */
    public void setCountry(String country) {
        this.country = country;
    }
    
    /**
     * 
     * 
     * @return the name and country of the location.
     */
    @Override
    public int hashCode(){
        return Objects.hash(this.name, this.country);
    }
    
    /**
     * Compares this object to another object. The objects are equal if they are of the same class
     * 
     * @param obj, the object to compare to.
     * @return true if the objects are equal, false if not.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Location other = (Location) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.country, other.country);
    }
}
