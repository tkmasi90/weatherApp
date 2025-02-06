/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fi.tuni.prog3.weatherapp;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * UIicons class provides methods to retrieve various icons used in the weather application UI.
 * It loads icons for search, clock, sunrise, sunset, rain, and wind, as well as weather condition icons.
 * Icons are retrieved from the resources directory.
 * @author Markus
 * Javadocs by Markus
 */
public final class UIicons extends UIimages{
    private Image searchIcon;
    private Image clock;
    private Image sunrise;
    private Image sunset;
    private Image wind;
    private Image rain;
    private Image snow;
    private Image star0;
    private Image star1;
    private final Map<String, Image> iconMap;
    private final String[] iconIds;
    private final int ICON_SIZE = 24;
    
    /**
     * Constructs a UIicons object and initializes iconMap with weather condition icon IDs.
     * It also loads the search icon, weather condition icons, and additional icons.
     *
     */
    public UIicons(){
        iconMap = new HashMap<>();
        iconIds = new String[]{"01d", "01n", "02d", "02n", "03d", "03n",
        "04d", "04n", "09d", "09n", "10d", "10n", "11d", "11n", "13d",
        "13n", "50d", "50n"};

        setSearchIcon();
        initializeWeatherIcons();
        setAdditionalIcons();
    }

    /**
     * Retrieves the clock icon.
     *
     * @return The clock icon image.
     */
    public Image getClock() {
        return clock;
    }

    /**
     * Retrieves the sunrise icon.
     *
     * @return The sunrise icon image.
     */
    public Image getSunrise() {
        return sunrise;
    }

    /**
     * Retrieves the sunset icon.
     *
     * @return The sunset icon image.
     */
    public Image getSunset() {
        return sunset;
    }

    /**
     * Retrieves the wind icon.
     *
     * @return The wind icon image.
     */
    public Image getWind() {
        return wind;
    }
    
    /**
     * Retrieves the rain icon.
     *
     * @return The rain icon image.
     */
    public Image getRain() {
        return rain;
    }
    
    /**
     * Retrieves the snow icon.
     *
     * @return The snow icon image.
     */
    public Image getSnow() {
        return snow;
    }
    
    /**
    * Retrieves the star0 icon.
    *
    * @return The snow icon image.
    */
    public Image getStar0() {
        return star0;
    }
    
    /**
    * Retrieves the star1 icon.
    *
    * @return The snow icon image.
    */
    public Image getStar1() {
        return star1;
    }
    
    /**
     * Loads the search icon.
     */

    private void setSearchIcon() {
        searchIcon = getFileImage(this.getClass().getResource("/icons/search.png"));
    }
    
    /**
     * Retrieves the search icon.
     *
     * @return The search icon image.
     */
    public Image getSearchIcon() {
        return searchIcon;
    }

    /**
     * Initializes the weather condition icons.
     */
    private void initializeWeatherIcons() {
        for (var id : iconIds) {
            iconMap.put(id, getFileImage(this.getClass().getResource("/icons/" + id + ".png")));
        }
    }

    /**
     * Retrieves the weather condition icon corresponding to the given key.
     *
     * @param key The key corresponding to the weather condition icon.
     * @return The weather condition icon image.
     */
    public Image getWeatherIcon(String key) {
    // Retrieve the icon corresponding to the key
    return iconMap.get(key);
    }
    
    /**
     * Loads additional icons such as clock, sunrise, sunset, rain, and wind.
     */
    private void setAdditionalIcons() {
        clock = getFileImage(this.getClass().getResource("/icons/clock.png"));
        sunrise = getFileImage(this.getClass().getResource("/icons/sunrise.png"));
        sunset = getFileImage(this.getClass().getResource("/icons/sunset.png"));
        wind = getFileImage(this.getClass().getResource("/icons/wind.png"));
        rain = getFileImage(this.getClass().getResource("/icons/rain.png"));
        snow = getFileImage(this.getClass().getResource("/icons/snow.png"));
        star0 = getFileImage(this.getClass().getResource("/icons/star0.png"));
        star1 = getFileImage(this.getClass().getResource("/icons/star1.png"));
    }
    
    /**
    * Creates an ImageView with the given image and sets its fit height and fit width
    * according to the predefined ICON_SIZE constant.
    *
    * @param image The image to be displayed in the ImageView.
     * @param size The size of the icon
    * @return The ImageView containing the provided image with fit height and fit width set.
    */
    public ImageView addIconImage(Image image, int size) {
        ImageView icon = new ImageView(image);
        icon.setId("icon-image");
        icon.setFitHeight(size);
        icon.setFitWidth(size);
        return icon;
    }
    
    /**
    * Creates an ImageView with the given image and sets its fit height and fit width
    * according to the predefined ICON_SIZE constant.
    *
    * @param image The image to be displayed in the ImageView.
    * @return The ImageView containing the provided image with fit height and fit width set.
    */
    public ImageView addIconImage(Image image) {
        ImageView icon = new ImageView(image);
        icon.setId("icon-image");
        icon.setFitHeight(ICON_SIZE);
        icon.setFitWidth(ICON_SIZE);
        return icon;
    }
}
