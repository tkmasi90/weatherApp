package fi.tuni.prog3.weatherapp;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Make
 */
public class UIForecast {
    private final UIicons icons;
    private final String unitTemp;
    private final String unitWind;
    
    private final WeatherForecast forecast;
    private final WeatherForecast16 forecast16;

    /**
    * Constructs a UIForecast object for the provided user.
    *
    * @param user The user for whom the forecast is generated.
    * @throws IOException            If an I/O error occurs.
    * @throws InterruptedException   If the operation is interrupted.
    */
    public UIForecast(User user) throws IOException, InterruptedException {
        forecast = WeatherData.fetchForecast(user.getActiveLocation(),
                "hourly", user.getUnit());
        forecast16 = WeatherData.getForecast16d(user.getActiveLocation().getLat(),
                user.getActiveLocation().getLon(),
                "daily", user.getUnit());
        
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
     * Generates an area chart for displaying the temperature forecast.
     * 
     * @return AreaChart containing the temperature forecast.
     */
    private AreaChart<String, Number> tempChart() {

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setId("temp-x");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setId("temp-y");

        AreaChart<String, Number> tempchart = new AreaChart<>(xAxis, yAxis);
        tempchart.getStyleClass().add("temp-chart");
        
        XYChart.Series<String, Number> temperature = new XYChart.Series<>();

        for (int i = 0; i < forecast.getCnt(); i++) {
            String[] dateTime = dateTime(forecast.getList().get(i).getDt());
            double temp = forecast.getList().get(i).getMain().getTemp();
            temperature.getData().add(new XYChart.Data<>(dateTime[0] + " " + dateTime[1], temp));
        }
        yAxis.setLabel("Temperature" + unitTemp);
        temperature.setName("Temperature");
        tempchart.getData().add(temperature);

        return tempchart;
    }

    /**
     * Generates a bar chart for displaying the rain forecast.
     * 
     * @return BarChart containing the rain forecast.
     */
    private BarChart<String, Number> rainChart() {

        CategoryAxis rainX = new CategoryAxis();
        rainX.setId("rain-x");
        NumberAxis rainY = new NumberAxis();
        rainY.setId("rain-y");

        
        BarChart<String, Number> rainchart = new BarChart<>(rainX, rainY);
        rainchart.getStyleClass().add("rain-chart");
        
        XYChart.Series<String, Number> rain = new XYChart.Series<>();
        XYChart.Series<String, Number> snow = new XYChart.Series<>();

        for (int i = 0; i < forecast.getCnt(); i++) {
            String[] dateTime = dateTime(forecast.getList().get(i).getDt());
            double rainAmount = forecast.getList().get(i).getRain() != null ? forecast.getList().get(i).getRain().get_1h() : 0.0;
            double snowAmount = forecast.getList().get(i).getSnow() != null ? forecast.getList().get(i).getSnow().get_1h() : 0.0;
            rain.getData().add(new XYChart.Data<>(dateTime[0] + " " + dateTime[1], rainAmount));
            snow.getData().add(new XYChart.Data<>(dateTime[0] + " " + dateTime[1], snowAmount));
        }
        rainY.setLabel("Rain (mm)");
        rain.setName("Rain");
        snow.setName("Snow");

        rainchart.getData().add(rain);
        rainchart.getData().add(snow);

        return rainchart;
    }
        
        /**
     * Returns the HBox containing the temperature forecast graph.
     * @return The HBox containing the temperature forecast graph.
     */
    public StackPane getGraph() {

        // Create a StackPane to stack the temperature and rain charts
        StackPane stackPane = new StackPane();
        stackPane.getStyleClass().add("stack-pane");

        AreaChart<String, Number> tempChart = tempChart();
        BarChart<String, Number> rainChart = rainChart();
        StackPane.setMargin(rainChart, new Insets(0, 0, 0, 50));
        StackPane.setMargin(tempChart, new Insets(0, 50, 0, 0));
        StackPane.setAlignment(tempChart, Pos.CENTER);
        StackPane.setAlignment(rainChart, Pos.CENTER);
        rainChart.setViewOrder(-1);
      
        // Add temperature and rain charts to the StackPane
        stackPane.getChildren().addAll(tempChart, rainChart);
        
        return stackPane;
    }  
    
    /**
    * Generates and returns an HBox containing the daily weather forecast.
    * 
    * @return An HBox containing the daily weather forecast.
    */
    public HBox getDailyForecast(){
        
        HBox allDays = new HBox();
        HBox skrolleri = new HBox();
        allDays.getStyleClass().add("daily-hbox");
        
        for (int i = 0; i < forecast16.getCnt(); i++) {
            String[] dateTime = dateTime(forecast16.getList().get(i).getDt());
            Label dateLabel = new Label(dateTime[1]);   
            Label weekDay = 
                    new Label(weekDay(forecast16.getList().get(i).getDt(), "long"));
            
            VBox iconBox = new VBox();
            iconBox.setId("iconBox");
            
            String iconId = forecast16.getList().get(i).getWeather().get(0).getIcon();
            Image iconWeather = icons.getWeatherIcon(iconId);
            if(iconWeather != null) {
                iconBox.getChildren().add(icons.addIconImage(iconWeather, 20));
            } else { iconBox.getChildren().add(new Label(" ")); }
            
            Image iconRain = icons.getRain();
            if(iconRain != null) {
                iconBox.getChildren().add(icons.addIconImage(iconRain, 20));
            } else { iconBox.getChildren().add(new Label("Rain:")); }
            
            Image iconWind = icons.getWind();
            if(iconWind != null) {
                iconBox.getChildren().add(icons.addIconImage(iconWind, 20));
            } else { iconBox.getChildren().add(new Label("Wind:")); }
            
            Label percent = new Label(" %");
            iconBox.getChildren().add(percent);
            
            int tempMin = (int) forecast16.getList().get(i).getTemp().getMin();
            int tempMax = (int) forecast16.getList().get(i).getTemp().getMax();
            double rain = forecast16.getList().get(i).getRain();
            double wind = forecast16.getList().get(i).getSpeed();
            double pop = forecast16.getList().get(i).getPop();
            
            Label tempLabel = new Label(
                    Integer.toString(tempMin) +
                    "..." +
                    Integer.toString(tempMax) + unitTemp);
            
            Label rainLabel = new Label(Double.toString(rain) +" mm");
            Label windLabel = new Label(Double.toString(wind) + unitWind);
            double pop_percent = pop * 100;
            Label popLabel = new Label(Double.toString(pop_percent));
        
            VBox singleDay = new VBox();
            singleDay.setId("singleDay");
            singleDay.getChildren().addAll(tempLabel, rainLabel, windLabel, popLabel);
            
            HBox unitBox = new HBox();  
            unitBox.getChildren().addAll(iconBox, singleDay);
            
            Region region1 = new Region();
            VBox.setVgrow(region1, Priority.ALWAYS); 
            Region region2 = new Region();
            VBox.setVgrow(region2, Priority.ALWAYS);
            
            Separator separator_hor = new Separator(Orientation.HORIZONTAL);
            separator_hor.setPadding(new Insets(10, 0, 0, 0));
                
            VBox singleDayBox = new VBox();
            singleDayBox.setId("singleDayBox");
            singleDayBox.getChildren().addAll(weekDay, dateLabel, separator_hor,
                    region1, unitBox, region2 );
            
            skrolleri.getChildren().add(singleDayBox);
                        
            if (i < forecast16.getCnt() - 1) {
                Separator separator_vert = new Separator(Orientation.VERTICAL);
                skrolleri.getChildren().add(separator_vert);
            }
        }
        
        ScrollPane s1 = new ScrollPane();
        s1.setFitToHeight(true);
        s1.setContent(skrolleri);
        VBox.setVgrow(s1, Priority.ALWAYS);
        VBox.setVgrow(allDays, Priority.ALWAYS);
        allDays.getChildren().add(s1);
        return allDays;
    }
    
    /**
     * Generates and returns an HBox containing the hourly weather forecast.
     * 
     * @return An HBox containing the hourly weather forecast.
     */        
    public HBox getHourlyForecast() {
        HBox allHours = new HBox();
        HBox skrolleri = new HBox();
        allHours.getStyleClass().add("hourly-hbox");

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setMinHeight(190);
        scrollPane.setFitToHeight(true);
        scrollPane.setContent(skrolleri);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);
        HBox.setHgrow(allHours, Priority.ALWAYS);

        for (int i = 0; i < forecast.getCnt(); i++) {

            // Create labels for weekday and time
            String weekDay = weekDay(forecast.getList().get(i).getDt(), "short");
            Label weekdayLabel = new Label(weekDay);
            String[] dateTime = dateTime(forecast.getList().get(i).getDt());
            Label dateLabel = new Label(dateTime[0]);

            // Create labels for other forecast data
            double temp = forecast.getList().get(i).getMain().getTemp();
            Label tempLabel = new Label(Integer.toString((int) temp));

            double wind = forecast.getList().get(i).getWind().getSpeed();
            Label windLabel = new Label(Integer.toString((int) wind));

            double rain = forecast.getList().get(i).getRain() != null ? forecast.getList().get(i).getRain().get_1h() : 0.0;
            Label rainLabel = new Label(Integer.toString((int) rain));

            // Create a VBox to hold all labels for a single hour
            VBox singleHour = new VBox();
            singleHour.setId("singleHour");

            // Add weekday and time labels to the VBox
            singleHour.getChildren().addAll(weekdayLabel, dateLabel, tempLabel, windLabel, rainLabel);
            skrolleri.getChildren().add(singleHour);
            
            if (i < forecast.getCnt() - 1) {
                Separator separator = new Separator(Orientation.VERTICAL);
                skrolleri.getChildren().add(separator);
            }
        }

        VBox sidePanel = new VBox();
        sidePanel.setId("hourlySide");

        Image iconClock = icons.getClock();
        if(iconClock != null) {
            sidePanel.getChildren().add(icons.addIconImage(iconClock, 20));
        } else sidePanel.getChildren().add(new Label("Time:"));

        Label unit = new Label(unitTemp);
        unit.setId("dailyUnit");
        sidePanel.getChildren().add(unit);

        Image iconWind = icons.getWind();
        if(iconWind != null) {
            sidePanel.getChildren().add(icons.addIconImage(iconWind, 20));
        } else sidePanel.getChildren().add(new Label("Wind:"));

        Image iconRain = icons.getRain();
        if(iconRain != null) {
            sidePanel.getChildren().add(icons.addIconImage(iconRain, 20));
        } else sidePanel.getChildren().add(new Label("Rain:"));


        // Add the SplitPane to the main layout
        allHours.getChildren().addAll(sidePanel, scrollPane);

        return allHours;
    }
    
        /**
     * Returns the formatted time and date from the given epoch timestamp.
     * @param dt The epoch timestamp.
     * @return An array of two strings, where the first element is the formatted time and the second element is the formatted date.
     */
    private String[] dateTime(long dt) {
        String[] dateTime = new String[2];
        Instant instant = Instant.ofEpochSecond(dt);
        ZoneId zoneId = ZoneId.ofOffset("", ZoneOffset.ofTotalSeconds(forecast16.getTimezone()));
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, zoneId);

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH");
        String formattedTime = zonedDateTime.format(timeFormatter);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM");
        String formattedDate = zonedDateTime.format(dateFormatter);

        dateTime[0] = formattedTime;
        dateTime[1] = formattedDate;

        return dateTime;
    }
    
    /**
     * Returns the formatted day of the week from the given epoch timestamp.
     * 
     * @param dt The epoch timestamp.
     * @return The formatted day of the week.
     */
    private String weekDay(long dt, String length) {
        String weekDay;
        Instant instant = Instant.ofEpochSecond(dt);
        ZoneId zoneId = ZoneId.ofOffset("", ZoneOffset.ofTotalSeconds(forecast16.getTimezone()));
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, zoneId);
        
        switch (length) {
            case "short":
                {
                    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("EEE");
                    weekDay = zonedDateTime.format(timeFormatter);
                    break;
                }
            case "long":
                {
                    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("EEEE");
                    weekDay = zonedDateTime.format(timeFormatter);
                    break;
                }
            default:
                // Handle invalid input
                weekDay = "Invalid length";
                break;
        }
        return weekDay;
    }
}
