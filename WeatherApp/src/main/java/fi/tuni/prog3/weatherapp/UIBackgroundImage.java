package fi.tuni.prog3.weatherapp;

import java.net.URL;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

/**
 * UIBackgroundImage class provides functionality to retrieve background images
 * based on weather conditions.
 * It extends the UIimages class to inherit image retrieval methods.
 * The background images are stored in the resources directory.
 * 
 * @author Markus, ChatGPT(Javadoc comments)
 */
public class UIBackgroundImage extends UIimages{
    private final URL[] imageUrls;

    /**
     * Constructs a UIBackgroundImage object and initializes the array of image URLs.
     */
    public UIBackgroundImage() {
        imageUrls = populateImageUrls();
    }
    
    /**
     * Populates an array of image URLs with paths to background images for different weather conditions.
     * 
     * @return An array of image URLs.
     */
    private URL[] populateImageUrls() {
        return new URL[] {
        this.getClass().getResource("/backgrounds/thunder.png"),
        this.getClass().getResource("/backgrounds/rainy.png"),
        this.getClass().getResource("/backgrounds/snowy.png"),
        this.getClass().getResource("/backgrounds/misty.png"),
        this.getClass().getResource("/backgrounds/cloudy.png"),
        this.getClass().getResource("/backgrounds/sunny.png")};
    }
    
    /**
     * Retrieves the background image based on the given weather condition type.
     * 
     * @param type The type of weather condition.
     * @return The background image corresponding to the weather condition.
     * @throws Exception If the specified weather condition type is not recognized.
     */
    public BackgroundImage getBackgroundImage(String type) throws Exception {
        switch(type) {
            case "Thunderstorm":
                return setUpBackgroundImage(getFileImage(imageUrls[0]));
            case "Rain":
                return setUpBackgroundImage(getFileImage(imageUrls[1]));
            case "Drizzle":
                return setUpBackgroundImage(getFileImage(imageUrls[1]));
            case "Snow":
                return setUpBackgroundImage(getFileImage(imageUrls[2]));
            case "Atmosphere":
                return setUpBackgroundImage(getFileImage(imageUrls[3]));
            case "Clouds":
                return setUpBackgroundImage(getFileImage(imageUrls[4]));
            case "Clear":
                return setUpBackgroundImage(getFileImage(imageUrls[5]));
            default:
                throw new Exception("Unsupported weather condition type: " + type);
        }
    }
        
    /**
     * Sets up a background image with the given image.
     * 
     * @param image The image to be used as the background.
     * @return The background image.
     */
    private BackgroundImage setUpBackgroundImage(Image image) {

        BackgroundImage backgroundImg = new BackgroundImage(
            image,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            new BackgroundSize(700, 250, false, false, false, false)
        );
        return backgroundImg;
    }
}
