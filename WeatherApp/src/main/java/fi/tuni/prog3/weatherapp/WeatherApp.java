package fi.tuni.prog3.weatherapp;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import org.controlsfx.control.ToggleSwitch;
import org.controlsfx.control.textfield.TextFields;
/**
 * JavaFX Weather Application.
 * @author Markus, TottiK, Janii
 * Javadocs by Markus, TottiK, ChatGPT, Copilot
 */
public class WeatherApp extends Application {
    
    /** File name for storing application state */
    public static final String STATE_FILENAME = "AppState.json";
    
    /** User object to manage user data */
    private User user;
    
    /** Root layout of the application */
    private final BorderPane root;
    
    private ComboBox<Location> historyCB;
    private ComboBox<Location> favoritesCB;
    
    private final Label cityName;
    private final Button favoriteButton;
    

    /**
    * Constructs a new instance of the WeatherApp.
    * Initializes the root BorderPane and user information.
    * Sets the default location to Tampere, Finland.
    */
    public WeatherApp() {
        this.root = new BorderPane();
        this.user = new User();
        updateUserLocation("Tampere", "FI");
        cityName = new Label();
        favoriteButton = new Button();
    }
    
    /**
    * Initializes and sets up the primary stage for the WeatherApp.
    * Attempts to load user state from file and sets the active user location accordingly.
    * If loading fails or no previous state exists, defaults to Tampere, Finland.
    * Constructs and configures the user interface components including the layout and scene.
    * Displays the primary stage with the WeatherApp interface.
    *
    * @param stage The primary stage of the JavaFX application.
    */
    @Override
    public void start(Stage stage){
        
        // Trying to load state
        readStateFromFile();
        
        //Creating a new BorderPane.
        root.setPadding(new Insets(10, 10, 10, 10));

        root.setTop(getTopVBox());
        updateHistory();  
        updateFavorites();

        //Adding HBox to the center of the BorderPane.
        root.setCenter(getCenterVBox());
        
        //BorderPane.setAlignment(quitButton, Pos.TOP_RIGHT);
        Scene scene = new Scene(root, 600, 800);
        URL stylesheetUrl = getClass().getResource("/style.css");

        // Check if the stylesheet exists
        if (stylesheetUrl != null) {
            // Add the stylesheet to the scene
            scene.getStylesheets().add(stylesheetUrl.toExternalForm());
        }
        else { root.setBottom(getBottomHBox("style.css not found")); }
        
        stage.setScene(scene);
        stage.setTitle("WeatherApp");
        stage.setMaxWidth(700);
        stage.setMinWidth(500);
        stage.setMinHeight(820);
        stage.setMaxHeight(1000);
        stage.show();
    }

    /**
    * Overrides the default stop method to handle the application shutdown process.
    * Saves the current user state to a file before exiting the application.
    * If an exception occurs during the file write operation, it is caught and printed to the console.
    */
    @Override
    public void stop() {
        writeStateToFile();
    }
    
    private void readStateFromFile() {
        FileData fd = new FileData();
        User tempUser = null;
        PauseTransition pause = new PauseTransition(javafx.util.Duration.seconds(10));
        pause.setOnFinished(event -> root.setBottom(new HBox()));
        
        try{
             tempUser = fd.readFromFile(STATE_FILENAME);
             if(tempUser != null &&
                    updateUserLocation(tempUser.getActiveLocation().getName(),
                    tempUser.getActiveLocation().getCountry())) {
                    this.user = tempUser;
             }
             else {
                root.setBottom(getBottomHBox("Could not read location history."
                        + " Showing default location 'Tampere'"));
                pause.play();
             }
        } catch(Exception e) {
            root.setBottom(getBottomHBox("Could not read location history."
                    + " Showing default location 'Tampere'"));
            pause.play();
        }
    }
    
    /**
     * Writes the current state to a file.
     * 
     * This method creates a FileData object and writes the current state to a file.
     */
    private void writeStateToFile() {
       FileData fd = new FileData();
        try {
            fd.writeToFile(STATE_FILENAME, this.user);
        } catch(Exception e){
            System.out.println(e);
        } 
    }
    
    /** 
    * Main method of the WeatherApp application.
    * Launches the JavaFX application by calling the launch method with no arguments.
    * This method is automatically called when the application is started.
    *
    * @param args Command-line arguments (not used).
    */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Creates the center layout of the application.
     * @return VBox containing the center layout
     */
    private VBox getCenterVBox() {
        //Creating an HBox.
        VBox centerVBox = new VBox(0);
        centerVBox.setId("center-v-box");

        VBox topVBox = new VBox(0); 
        VBox bottomVBox = new VBox(0); 

        UICurrent locCurrent;
        UIForecast locForecast;

        try {
            locCurrent = new UICurrent(this.user);
            locForecast = new UIForecast(this.user);
            
            // Set background image for the centerVBox
            UIBackgroundImage backImg = new UIBackgroundImage();
            Background background = null;
            try {
                background = new Background
                        (backImg.getBackgroundImage(locCurrent.getWeatherType()));
            } catch (Exception ex) {
                root.setBottom(getBottomHBox("Background image not found!"));
            } 

            topVBox.setBackground(background);
            topVBox.setPrefHeight(250);
            topVBox.setAlignment(Pos.CENTER);

            //Adding two VBox to the HBox.
            topVBox.getChildren().addAll(locCurrent.getDateTime(),
                    locCurrent.getTemp(),
                    locCurrent.getFeelsLike(),
                    locCurrent.getSunsetRainWind());
            bottomVBox.getChildren().addAll(getTabs(locForecast), getUnitSwitch());
            centerVBox.getChildren().addAll(topVBox, bottomVBox);
        
        } catch (IOException | InterruptedException ex) {
            root.setBottom(getBottomHBox(ex.toString()));
        }

        return centerVBox;
    }
    
    /**
    * Generates a VBox containing both daily and hourly forecasts.
    *
    * @param locForecast The UIForecast object containing the forecasts.
    * @return A VBox containing the daily and hourly forecasts.
    */
    private VBox getForecastHBox(UIForecast locForecast) {

        VBox forecastVbox = new VBox();
        forecastVbox.getChildren().addAll(locForecast.getDailyForecast(),
                locForecast.getHourlyForecast());
        
        return forecastVbox;
    }
    
    /**
     * Returns a TabPane object that contains tabs for displaying temperature/rain graph and map.
     *
     * @param locForecast the UIForecast object containing the graph to be displayed
     * @return a TabPane object with tabs for temperature/rain graph and map
     */
    private TabPane getTabs(UIForecast locForecast) {

        TabPane tabpane = new TabPane();
        Tab forecast = new Tab("Forecast");
        Tab tempGraph = new Tab("Forecast Graph");
        Tab map = new Tab("Weather Map");
        
        forecast.setContent(getForecastHBox(locForecast));
        
        tempGraph.setContent(locForecast.getGraph());

        map.setContent(new LeafletMaps(user.getActiveLocation()).getMap());
        forecast.setClosable(false);
        tempGraph.setClosable(false);
        map.setClosable(false);

        URL styleUrl = this.getClass().getResource("/style.css");
        if(styleUrl != null) {
            tabpane.getStylesheets().add(styleUrl.toExternalForm());}

        tabpane.getTabs().addAll(forecast, tempGraph, map);

        return tabpane;
    }

    /**
     * Creates the top layout of the application.
     * @return VBox containing the top layout
     */
    private VBox getTopVBox() {
        VBox topVBox = new VBox();
        topVBox.setId("top-v-box");

        Region region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);
        
        HBox hBox = new HBox();
        hBox.setId("top-h-box");

        hBox.getChildren().addAll(setFavorites(), region, setHistory());
        topVBox.getChildren().addAll(setSearchBar(), hBox);

        return topVBox;
    }

    /**
     * Creates the bottom layout of the application.
     * @param error Error message to be displayed
     * @return HBox containing the bottom layout
     */
    private HBox getBottomHBox(String error) {
        HBox errorBox = new HBox();
        Label errorMsg = new Label(error);
        errorBox.getChildren().add(errorMsg);
        errorBox.setBackground(new Background(new BackgroundFill(Color.TOMATO,
        CornerRadii.EMPTY,
        Insets.EMPTY)));
        
        return errorBox;  
    }

    /**
     * Creates the search bar layout.
     * @return HBox containing the search bar layout
     */
    private HBox setSearchBar() {
        HBox hBox = new HBox();
        hBox.setId("search-h-box");

        List<String> locList = null;
        try {
            locList = new FileData().readLocationList().stream()
                .map(s -> s.replace(";", ", "))
                .collect(Collectors.toList());
        } catch(IllegalArgumentException ex) {
            root.setBottom(getBottomHBox("City list not found. "
                    + "Location must be given in form: "
                    + "'City name, Country code(CC)'"));
        }
        
        Button searchButton = new Button();
        searchButton.setId("search-button");

        TextField searchField = new TextField();
        searchField.setPromptText("Search for location");
        searchField.setId("search-field");
        
        cityName.setId("city-name");
        
        if(locList != null) {
            TextFields.bindAutoCompletion(searchField, locList);
        }

        cityName.setText(user.getActiveLocation().getName());
        
        Region region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);
        Image image = new UIicons().getSearchIcon();
        if(image != null) {
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(22.0);
            imageView.setFitHeight(22.0);
            searchButton.setGraphic(imageView);
        } else {
            searchButton.setText("GO");
        }
        
        setSearchFunctionality(searchField, searchButton, locList);
        
        hBox.getChildren().addAll(cityName, favoriteButton, region, searchField, searchButton);
        return hBox;
    }
    
    /**
    * Sets up the search functionality for the weather application.
    * @param searchField The text field where the user enters the location to search for.
    * @param searchButton The button used to trigger the search action.
    * @param locList The list of locations to search within.
    * @param cityName The label to display the name of the city after searching.
    */
    private void setSearchFunctionality(TextField searchField, 
        Button searchButton,  List<String> locList) {
        
        // Search for location when button is pressed 
        searchButton.setOnAction((ActionEvent e) -> {
            searchForLocation(searchField);
        });

        // Search for location when 'enter' is pressed in the search field
        searchField.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)){
                searchForLocation(searchField);
            }
        });

        // Handle search action when an item is selected from the history combo box
        EventHandler<ActionEvent> eventHist =
         (ActionEvent e) -> {
             Location value = historyCB.getValue();
             if(value != null) {
                TextField histField = new TextField(String.format("%s, %s",
                        value.getName(),
                        value.getCountry()));
                searchForLocation(histField);
             }
        };
        historyCB.setOnAction(eventHist);

        // Handle search action when an item is selected from the favorites combo box
        EventHandler<ActionEvent> eventFav =
         (ActionEvent e) -> {
            Location value = favoritesCB.getValue();
            if(value != null) {
               TextField favField = new TextField(String.format("%s, %s",
                       value.getName(),
                       value.getCountry()));
               searchForLocation(favField);
            }
        };
        favoritesCB.setOnAction(eventFav);
    }
    
        /**
     * Searches for the location specified in the search field and updates the user's active location.
     * If the location is valid, it updates the city name label, refreshes the center layout, clears the search field,
     * adds the location to the user's history, and clears the bottom layout.
     * If the location is invalid or not found, it displays an error message in the bottom layout.
     * @param searchField TextField containing the location to search for
     * @param cityName Label displaying the name of the active city
     */
    private void searchForLocation(TextField locField) {
        
        String strLocation = locField.getText().trim();
        String[] locationArray = strLocation.split("\\s*,\\s*");
        if (locationArray. length != 2) {
            root.setBottom(getBottomHBox("Invalid location input. "
                    + "Location must be given in form: "
                    + "'City name, Country code(CC)'"));
            return;
        }
        if(updateUserLocation(locationArray[0], locationArray[1])) {
            cityName.setText(user.getActiveLocation().getName());
            root.setCenter(getCenterVBox());
            locField.clear();
            user.addHistory(user.getActiveLocation());
            updateFavoriteButtonIcon();
            root.setBottom(new HBox());
            updateHistory();
            updateFavorites();
        }
    }
    
    /**
    * Creates a layout containing a toggle switch for switching between temperature units.
    * The layout includes labels for Celsius and Fahrenheit units, and a toggle switch to select between them.
    * When the toggle switch state changes, it updates the user's preferred temperature unit accordingly
    * and refreshes the center layout of the application to reflect the change.
    *
    * @return The horizontal box (HBox) layout containing the temperature unit switch.
    */
    private HBox getUnitSwitch() {

        HBox hBox = new HBox();
        hBox.setId("unit-switch-hbox");

        ToggleSwitch toggleSwitch = new ToggleSwitch();
        toggleSwitch.setId("toggle-switch");

        if (user.getUnit().equals("imperial")) {
            toggleSwitch.setSelected(false);
        } else {
            toggleSwitch.setSelected(true);
        }
        
        Label labelCelsius = new Label("°C");
        labelCelsius.setId("celsius-label");

        Label labelFahrenheit = new Label("°F");
        labelFahrenheit.setId("fahrenheit-label");
        
        toggleSwitch.selectedProperty()
                .addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                user.setUnit("metric");
            } else {
                user.setUnit("imperial");
            }
            root.setCenter(getCenterVBox());
        });
        
        hBox.getChildren().addAll(labelFahrenheit, toggleSwitch, labelCelsius);
        return hBox;
    }
    
    /**
     * Updates the user's active location based on the provided city name and country code.
     * If the location is found, it sets the active location and returns true.
     * If the location is not found or an error occurs, it displays an error message in the bottom layout and returns false.
     * @param cityName The name of the city to update
     * @param countryCode The country code of the city to update
     * @return True if the location was successfully updated, false otherwise
     */
    private boolean updateUserLocation(String cityName, String countryCode) {
        try {
            Location location = WeatherData.lookUpLocation(cityName, countryCode);
            user.setActiveLocation(location);
            return true; 
        } catch (IOException | InterruptedException e) {
            root.setBottom(getBottomHBox(e.getMessage()));
            return false;
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            root.setBottom(getBottomHBox("Location not found. "
                    + "Location must be given in form: "
                    + "'City name, Country code(CC)'"));
            return false;
        }
    }
    
    /**
     * Creates a ComboBox layout for displaying the user's search history.
     * @return ComboBox layout containing the user's search history
     */    
    private HBox setHistory() {
        Label labelHistory = new Label("History:");
        labelHistory.setId("history-label");

        historyCB = new ComboBox<>();
        historyCB.setPrefWidth(150);
        historyCB.setPromptText("");
        historyCB.setConverter(new StringConverter<Location>() {
            @Override
            public String toString(Location object) {
                return String.format("%s, %s", object.getName(), object.getCountry());
            }
            @Override
            public Location fromString(String string) {
                return null;
            }
        });
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(0, 0, 0, 15));
        hBox.setSpacing(15);
        hBox.getChildren().addAll(labelHistory, historyCB);
        return hBox;
    }

    /**
     * Creates a layout containing a ComboBox for displaying the user's favorite locations.
     * The layout includes a ComboBox for selecting favorite locations and a button for managing favorites.
     * @return HBox layout containing the favorite locations ComboBox and button
     */
    private HBox setFavorites() {
        Label labelFavorites = new Label("Favorites:");
        labelFavorites.setId("favorite-label");
        favoritesCB = new ComboBox<>();
        favoritesCB.setPrefWidth(150);
        favoritesCB.setPromptText("");
        favoritesCB.setConverter(new StringConverter<Location>() {
            @Override
            public String toString(Location object) {
                return String.format("%s, %s", object.getName(), object.getCountry());
            }

            @Override
            public Location fromString(String string) {
                return null;
            }
        });
        
        favoriteButton.setId("favorite-button");
        updateFavoriteButtonIcon();
       
        // Adds location to users favoritelist
        favoriteButton.setOnAction((ActionEvent e) -> {
            if(user.getFavorites().contains(user.getActiveLocation())) {
                user.removeFavorite(user.getActiveLocation()); // Remove from favorites
            } else {
                user.addFavorite(user.getActiveLocation()); // Add to favorites
            }
            updateFavoriteButtonIcon();
            updateFavorites();
            });

        HBox hBox = new HBox();
        hBox.setId("favorites-hbox");
        hBox.getChildren().addAll(labelFavorites, favoritesCB);

        return hBox;
    }

    /**
     * Updates the history ComboBox with the user's search history.
     * This method is called whenever the user's search history is updated.
     */
    private void updateHistory(){
        Platform.runLater(() -> {
            historyCB.getItems().clear();
            historyCB.setValue(null);
            List<Location> history = new ArrayList<>(user.getHistory());
            Collections.reverse(history); // Reverse the order of history
            historyCB.getItems().addAll(history);

        });
    }
    
    /**
     * Updates the favorites ComboBox with the user's favorite locations.
     */
    private void updateFavorites() {
        Platform.runLater(() -> {
            favoritesCB.getItems().clear();
            favoritesCB.setValue(null);
            favoritesCB.getItems().addAll(user.getFavorites());
        });
    }
    
    /**
     * Method to update the favorite button's icon based on the current active location
     * and the user's favorites list.
     */
    private void updateFavoriteButtonIcon() {
        Image image = null;
        if(user.getFavorites().contains(user.getActiveLocation())) {
            image = new UIicons().getStar1(); // Use star1 icon if current location is in favorites
        } else {
            image = new UIicons().getStar0(); // Use star0 icon otherwise
        }

        if(image != null) {
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(22.0);
            imageView.setFitHeight(22.0);
            favoriteButton.setGraphic(imageView);
        } else {
            favoriteButton.setText("FAV");
        }
    }
}