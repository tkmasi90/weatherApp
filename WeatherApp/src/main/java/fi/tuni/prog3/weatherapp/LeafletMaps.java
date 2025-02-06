package fi.tuni.prog3.weatherapp;

import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * This class represents a Leaflet map for displaying weather data.
 * The Leaflet map is a web-based map that displays weather data from OpenWeatherMap.
 * Basemap is OpenStreetMap.
 * Map is embedded in a WebView, which is contained to HBox.
 * @author TottiK
 */
public class LeafletMaps extends WeatherApp {

    private Location location;
    private String html;
    private static final String API_KEY = "6ea75a73078b8f5a1982afa0de72c50f";
    private static final WebView webView = new WebView();
    private static final WebEngine webEngine = webView.getEngine();
    /**
     * Constructs a Leaflet map with the given location.
     *  
     * @param location the location to set
     */
    public LeafletMaps(Location location) {

        this.location = location;
        this.html = formatHTMLString(this.location);

    }

    /**
     * Sets up the HBox where the Leaflet map is displayed in a webviev.
     * 
     * @return the HBox containing the Leaflet map.
     */
    public HBox getMap() {
        HBox hBox = new HBox();
        hBox.setId("mapbox");
        webView.setId("mapview");
        webEngine.loadContent(html);
        hBox.getChildren().add(webView);
        return hBox;
    }

    /**
     * Sets a new location for the Leaflet map.
     * 
     * @param location the location to set
     */
    public void setLocation(Location location) {
        this.location = location;
        this.html = formatHTMLString(location);
        webEngine.loadContent(html);
    }
    
    /**
     * Formats the HTML string for the Leaflet map based on given location.
     * Needs also the OpenWeatherMap API key.
     * @param location the locaion to set
     * @return the formatted leafletmap configuration HTML as string.
     */
    private String formatHTMLString(Location location) {
        String html = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "<title>Leaflet Weather Map</title>\n" +
            "<meta charset=\"utf-8\" />\n" +
            "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "<link rel=\"stylesheet\" href=\"https://unpkg.com/leaflet/dist/leaflet.css\" />\n" +
            "<script src=\"https://unpkg.com/leaflet@1.9.4/dist/leaflet.js\"></script>\n" +
            "<style>\n" +
            "   body, html { height: 98%; width: 98%; margin: 1% auto auto auto;}\n" +
            "    #map { height: 98%; width: 98%; margin: auto auto auto auto; }\n" +
            "</style>\n" +
            "</head>\n" +
            "<body>\n" +
            "<div id=\"map\"></div>\n" +
            "<script>\n" +
            "    var map = L.map('map').setView([" + location.getLat() + ", " + location.getLon() + "], 7);\n" +
            "    var osm = L.tileLayer(\"https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png\", {\n" +
            "        attribution: '&copy; <a href=\"https://www.openstreetmap.org/copyright\">OpenStreetMap</a>'\n" +
            "    });\n" +
            "    var owm_c = L.tileLayer(\"http://{s}.tile.openweathermap.org/map/clouds_new/{z}/{x}/{y}.png?appid=" + API_KEY + "\", {\n" +
            "        maxZoom: 9,\n" +
            "        attribution: '&copy; <a href=\"http://openweathermap.org\">OpenWeatherMap</a>',\n" +
            "        opacity: 0.6\n" +
            "    });\n" +
            "    var owm_p = L.tileLayer(\"http://{s}.tile.openweathermap.org/map/precipitation_new/{z}/{x}/{y}.png?appid=" + API_KEY + "\", {\n" +
            "        maxZoom: 9,\n" +
            "        attribution: '&copy; <a href=\"http://openweathermap.org\">OpenWeatherMap</a>',\n" +
            "        opacity: 0.6\n" +
            "    });\n" +
            "    var owm_pr = L.tileLayer(\"http://{s}.tile.openweathermap.org/map/pressure_new/{z}/{x}/{y}.png?appid=" + API_KEY + "\", {\n" +
            "        maxZoom: 9,\n" +
            "        attribution: '&copy; <a href=\"http://openweathermap.org\">OpenWeatherMap</a>',\n" +
            "        opacity: 0.6\n" +
            "    });\n" +
            "    var owm_w = L.tileLayer(\"http://{s}.tile.openweathermap.org/map/wind_new/{z}/{x}/{y}.png?appid=" + API_KEY + "\", {\n" +
            "        maxZoom: 9,\n" +
            "        attribution: '&copy; <a href=\"http://openweathermap.org\">OpenWeatherMap</a>',\n" +
            "        opacity: 0.6\n" +
            "    });\n" +
            "    var owm_t = L.tileLayer(\"http://{s}.tile.openweathermap.org/map/temp_new/{z}/{x}/{y}.png?appid=" + API_KEY + "\", {\n" +
            "        maxZoom: 9,\n" +
            "        attribution: '&copy; <a href=\"http://openweathermap.org\">OpenWeatherMap</a>',\n" +
            "        opacity: 0.6\n" +
            "    });\n" +
            "    var baselayer = {\n" +
            "        \"OpenStreetMaps\": osm\n" +
            "    };\n" +
            "    const overlayMaps = {\n" +
            "        \"Clouds\": owm_c,\n" +
            "        \"Precipitation\": owm_p,\n" +
            "        \"Pressure\": owm_pr,\n" +
            "        \"Wind\": owm_w,\n" +
            "        \"Temperature\": owm_t\n" +
            "    };\n" +
            "    const layerControl = L.control.layers(overlayMaps).addTo(map);\n" +
            "    owm_t.addTo(map);\n" +
            "    osm.addTo(map);\n" +
            "    L.marker([" + location.getLat() + ", " + location.getLon() + "]).addTo(map)\n" +
            "</script>\n" +
            "</body>\n" +
            "</html>";

 
        return html;
    }
}
