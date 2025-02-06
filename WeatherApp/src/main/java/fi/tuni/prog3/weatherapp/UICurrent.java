
package fi.tuni.prog3.weatherapp;

import fi.tuni.prog3.weatherapp.Weather.Rain;
import fi.tuni.prog3.weatherapp.Weather.Snow;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.text.Text;


/**
 * UILocationCurrent class represents the UI elements for displaying current weather information.
 * It includes methods to generate HBox containers for various weather data components such as date, temperature,
 * feels-like temperature, sunset, sunrise, rain, and wind.
 * 
 * @author Markus, TottiK
 * Javadocs by Markus, TottiK, ChatGPT, Copilot
 */
public class UICurrent {
    private final Insets LABEL_PADDING = new Insets(0, 0, 0, 5);

   
    private final Weather current;

    private final UIicons icons;
    private final String unitTemp;
    private final String unitWind;

    /**
     * Constructs a UILocationCurrent object with the given location and user information.
     *
     * @param user     The user for whom the weather data is fetched, used for unit conversion.
     * @throws IOException        If an I/O error occurs during weather data retrieval.
     * @throws InterruptedException If the thread is interrupted during weather data retrieval.
     */

    public UICurrent(User user) throws IOException, InterruptedException {
        current = WeatherData.fetchCurrentWeather(user.getActiveLocation(), user.getUnit());

        icons = new UIicons();
        
        if(user.getUnit().equals("imperial")) {
            unitTemp = "°F";
            unitWind = "mph";
        } else {
            unitTemp = "°C";
            unitWind = "m/s";
        }
            
    }
    
    /**
     * Generates an HBox container for displaying date and time information.
     *
     * @return HBox container containing date and time information.
     */
    public HBox getDateTime() {
        
        HBox hBox = new HBox();
        hBox.getStyleClass().add("datetime-hbox");
       
        Instant utcInstant = Instant.now();
        ZoneId zoneId = ZoneId.ofOffset("", ZoneOffset.ofTotalSeconds(current.getTimezone()));
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(utcInstant, zoneId);

        // DATE
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEE dd.MM", Locale.ENGLISH);
        String formattedDate = zonedDateTime.format(dateFormatter);
        Label labelDate = new Label (formattedDate);

        // TIME
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String formattedTime = zonedDateTime.format(timeFormatter);
//        Image iconTime = icons.getClock();
//        ImageView ivTime = new ImageView(iconTime);

        Label labelTime = new Label (formattedTime);

        Region region1 = new Region();
        HBox.setHgrow(region1, Priority.ALWAYS);

        hBox.getChildren().addAll(labelDate, region1, labelTime);
        
        return hBox;
    }
    
    /**
     * Generates an HBox container for displaying current temperature and weather icon.
     *
     * @return HBox container containing temperature and weather icon.
     */
    public HBox getTemp() {

        HBox hBox = new HBox();
        hBox.getStyleClass().add("temp-hbox");
      
        int temp = (int)current.getTemp();
        Label temperature = new Label (Integer.toString(temp) + unitTemp);
        
        Region region1 = new Region();
        HBox.setHgrow(region1, Priority.ALWAYS);
        hBox.getChildren().add(region1);
        
        String iconId = current.getWeather().get(0).getIcon();
        Image icon = icons.getWeatherIcon(iconId);
        if(icon != null) {
            ImageView iconView = new ImageView(icon);
            hBox.getChildren().add(iconView);
        }

        Region region2 = new Region();
        HBox.setHgrow(region2, Priority.ALWAYS);

        hBox.getChildren().addAll(temperature, region2);
        return hBox;

    }
    
    /**
     * Generates an HBox container for displaying feels-like temperature.
     *
     * @return HBox container containing feels-like temperature.
     */
    public HBox getFeelsLike() {

        HBox hBox = new HBox();
        hBox.getStyleClass().add("feels-hbox");
        
        int feelTemp = (int) current.getMain().getFeels_like();
        String StrFeelTemp = Integer.toString(feelTemp);
        Label feelLike = new Label ("Feels like: " + StrFeelTemp + unitTemp);
        
        Region region1 = new Region();
        HBox.setHgrow(region1, Priority.ALWAYS);
        
        Region region2 = new Region();
        HBox.setHgrow(region2, Priority.ALWAYS);

        hBox.getChildren().addAll(region1, feelLike, region2);
        return hBox;
    }
    
    /**
     * Generates an HBox container for displaying sunset, sunrise, rain, and wind information.
     *
     * @return HBox container containing sunset, sunrise, rain, and wind information.
     */
    public HBox getSunsetRainWind() {

        HBox hBox = new HBox();
        hBox.getStyleClass().add("srw-hbox");

        SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm");
        
        // SUNRISE
        Instant instSunrise = Instant.ofEpochSecond(current.getSys().getSunrise());
        String formattedSunrise = formatterTime.format(Date.from(instSunrise));

        Image iconSunrise = icons.getSunrise();
        if(iconSunrise != null) {
            hBox.getChildren().add(icons.addIconImage(iconSunrise));
        } else {
            hBox.getChildren().add(addIconText("Sunrise:"));
        }
        Label labelSunrise = new Label (formattedSunrise);
        labelSunrise.setPadding(new Insets(0, 15, 0, 0));
        hBox.getChildren().add(labelSunrise);
        
        // SUNSET
        Instant instSunset = Instant.ofEpochSecond(current.getSys().getSunset());
        String formattedSunset = formatterTime.format(Date.from(instSunset));
        
        Image iconSunset = icons.getSunset();
        if(iconSunset != null) {
            ImageView ivSunset = icons.addIconImage(iconSunset);

            hBox.getChildren().add(ivSunset);
        } else {
            hBox.getChildren().add(addIconText("Sunset:"));
        }
        Label labelSunset = new Label (formattedSunset);

        hBox.getChildren().add(labelSunset);
        
        Region region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);
        hBox.getChildren().add(region);
        
        // RAIN / SNOW
        Rain rain = current.getRain();
        Snow snow = current.getSnow();
        double strRain = (rain == null) ? 0.0 : rain.get_1h();
        double strSnow = (snow == null) ? 0.0 : snow.get_1h();
        
        if(strRain >= strSnow) {
            Image iconRain = icons.getRain();
            if(iconRain != null) {
                hBox.getChildren().add(icons.addIconImage(iconRain));
            } else {
                hBox.getChildren().add(addIconText("Rain:"));
            } 
            
        } else {
            Image iconSnow = icons.getSnow();
            strRain = strSnow;
            if(iconSnow != null) {
                hBox.getChildren().add(icons.addIconImage(iconSnow));
            } else {
                hBox.getChildren().add(addIconText("Snow:"));
            }
            
        }          
        
        Label labelRain = new Label (strRain + "mm");
        labelRain.setPadding(new Insets(0, 15, 0, 0));
        hBox.getChildren().add(labelRain);
        
        // WIND
        Double windSpeed = current.getWind().getSpeed();
        String strWind = Double.toString(windSpeed);
        Image iconWind = icons.getWind();
        if(iconWind != null) {
            ImageView ivWind = icons.addIconImage(iconWind);

            hBox.getChildren().add(ivWind);
        } else {
            hBox.getChildren().add(addIconText("Wind:"));
        }
        Label labelWind = new Label(strWind + unitWind);

        hBox.getChildren().add(labelWind);
        
        return hBox;
    }
    
    /**
    * Creates a Text object with the provided text and sets margins using LABEL_PADDING.
    *
    * @param text The text content for the Text object.
    * @return The Text object containing the provided text with margins set using LABEL_PADDING.
    */
    private Text addIconText(String text) {
        Text txt = new Text(text);
        HBox.setMargin(txt, LABEL_PADDING);
        return txt;
    }
    
    /**
    * Returns the weather type of current weather.
    *
    * @return Weather type as a string.
    */
    public String getWeatherType() {
        return current.getWeather().get(0).getMain();
    }
}
