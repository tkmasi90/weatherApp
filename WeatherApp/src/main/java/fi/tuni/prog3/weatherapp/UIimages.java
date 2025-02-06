package fi.tuni.prog3.weatherapp;

import java.net.URL;
import javafx.scene.image.Image;

/**
 * Abstract class representing a collection of utility methods for handling images in the UI.
 * Subclasses should implement specific methods for image handling.
 * 
 * @author Markus
 */
public abstract class UIimages {
    /**
     * Retrieves an image from the given URL.
     *
     * @param url The URL of the image to retrieve.
     * @return The image retrieved from the URL.
     */
    protected Image getFileImage(URL url) {
        if (url == null) {
            return null;
        } else {
            return new Image(url.toExternalForm());
        }
    }
}
