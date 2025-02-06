package fi.tuni.prog3.weatherapp;

import com.google.gson.annotations.Expose;
import java.security.SecureRandom;
import java.util.HashSet;
import java.util.LinkedHashSet;

/**
 * 
 * Represents a user of the WeatherApp.
 * 
 * @author TottiK, janii
 * Javadocs by TottiK, Copilot
 */
public class User {
    
    private String username;
    private String password;
    private byte[] salt;
    @Expose
    private String email;
    @Expose
    private LinkedHashSet<Location> history = new LinkedHashSet<Location>();
    @Expose
    private HashSet<Location> favorites = new HashSet<Location>();
    @Expose
    private Location activeLocation = new Location();
    @Expose
    private String unit = "metric";

    /**
     * Generates a hash for the given password using SHA-512 algorithm.
     *
     * @param password the password to be hashed
     * @return the hashed password as a string
     */
//    private byte[] generateSalt() {
//        SecureRandom random = new SecureRandom();
//        byte[] salt = new byte[16];
//        random.nextBytes(salt);
//        return salt;
//    }

    /**
     * Generates a hash for the given password using SHA-512 algorithm.
     *
     * @param password the password to be hashed
     * @return the hashed password as a string
     */
//    private String generateHash(String password, byte[] salt) {
//        try {
//            MessageDigest md = MessageDigest.getInstance("SHA-512");
//            md.update(salt);
//            byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
//            StringBuilder sb = new StringBuilder();
//            for (byte b : hashedPassword) {
//                sb.append(String.format("%02x", b));
//            }
//            return sb.toString();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    /**
     * Constructs a User object with the specified username, password, and email.
     *
     * @param username the username of the user
     * @param password the password of the user
     * @param email    the email of the user
     */
//    public User(String username, String password, String email) {
//        this.username = username;
//        this.salt = generateSalt();
//        this.password = generateHash(password, this.salt);
//        this.email = email;
//    }

    /**
     * Constructs a User object with the specified username and password.
     *
     * @param username the username of the user
     * @param password the password of the user
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Constructs a User object with the specified username.
     *
     * @param username the username of the user
     */
    public User(String username) {
        this.username = username;
    }

    /**
     * Constructs an empty User object.
     */
    public User() {
        this.username = "Visitor";
    }

    /**
     * Returns the username of the user.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the email of the user.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Checks if the provided password matches the user's password.
     *
     * @param password the password to be checked
     * @return true if the password is correct, false otherwise
     */
//    public boolean checkPassword(String password) {
//        return this.password.equals(generateHash(password, this.salt));
//    }

    /**
     * Sets a new password for the user if the provided password is correct.
     *
     * @param password     the current password
     * @param newPassword the new password to be set
     */
//    public void setPassword(String password, String newPassword) {
//        if (checkPassword(password)) {
//            this.salt = generateSalt();
//            this.password = generateHash(newPassword, this.salt);
//        }
//    }

    /**
     * Sets a new email for the user if the provided password is correct.
     *
     * @param password the current password
     * @param email    the new email to be set
     */
    public void setEmail(String password, String email) {
        //if (checkPassword(password)) {
            this.email = email;
        //}
    }

    /**
     * Sets a new username for the user if the provided password is correct.
     *
     * @param password the current password
     * @param username the new username to be set
     */
    public void setUsername(String password, String username) {
        //if (checkPassword(password)) {
            this.username = username;
        //}
    }

    /**
     * Adds a location to the user's history.
     *
     * @param location the location to be added
     */
    public void addHistory(Location location) {
        if(history.contains(location)) {
            history.remove(location);
        }
        history.add(location);
    }

    /**
     * Adds a location to the user's favorites.
     *
     * @param location the location to be added
     */
    public void addFavorite(Location location) {
        favorites.add(location);
    }

    /**
     * Removes a location from the user's favorites.
     *
     * @param location the location to be removed
     */
    public void removeFavorite(Location location) {
        favorites.remove(location);
    }

    /**
     * Returns the user's history.
     *
     * @return the history as an ArrayList of Location objects
     */
    public HashSet<Location> getHistory() {
        return history;
    }

    /**
     * Returns the user's favorites.
     *
     * @return the favorites as an ArrayList of Location objects
     */
    public HashSet<Location> getFavorites() {
        return favorites;
    }
    
    /**
     * Sets the active location.
     *
     * @param location the location that should be currently selected in the UI
     */    
    public void setActiveLocation(Location location){
        this.activeLocation = location;
    }

    /**
     * Gets the active location.
     *
     * @return the location that should be currently selected in the UI
     */    
    public Location getActiveLocation(){
        return this.activeLocation;
    }    
 
    /**
     * Sets the active unit.
     *
     * @param unit Measurement unit (metric or imperial)
     */ 
    public void setUnit(String unit){
        
        //TODO: Tarkista, että arvo on joko imperial tai metric?
        this.unit = unit;
    }

    /**
     * Gets the active unit.
     *
     * @return the location that should be currently selected in the UI
     */     
    public String getUnit(){
        return this.unit;
    }
}
