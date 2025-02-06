/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fi.tuni.prog3.weatherapp;

import java.io.FileWriter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Writer;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class provides methods for reading and writing data to/from files.
 * @author janii
 * Javadocs by Copilot, TottiK
 */
public class FileData implements iReadAndWriteToFile{
    
    private static final String CITY_FILE = "owm_city_list.csv";

    /**
     * Reads user data from a file.
     * 
     * @param fileName the name of the file to read from
     * @return the User object to be deserialized from the file
     * @throws FileNotFoundException if the specified file is not found
     * @throws Exception if an error occurs while reading the file
     */
    @Override
    public User readFromFile(String fileName) throws FileNotFoundException, Exception {
        
        Gson gson = new GsonBuilder()
        .setPrettyPrinting()
        .excludeFieldsWithoutExposeAnnotation()
        .create();
        
        return gson.fromJson(new FileReader(fileName), User.class);

    }

    /**
     * Writes user data to a file.
     * 
     * @param fileName the name of the file to write to
     * @param user the User object to be serialized into the file
     * @return true if the write operation is successful, false otherwise
     * @throws Exception if an error occurs while writing the file
     */
    @Override
    public boolean writeToFile(String fileName, User user) throws Exception {
        Gson gson = new GsonBuilder()
        .setPrettyPrinting()
        .excludeFieldsWithoutExposeAnnotation()
        .create();
        
        Writer userWriter = new FileWriter(fileName);

        try{
            gson.toJson(user, userWriter);
            userWriter.flush();
            userWriter.close(); 
        }catch(Exception e){
            
        }
        
        return true;
    }
    
    /**
     * Reads the list of locations from a file.
     * 
     * @return a list of strings representing the locations
     */
    public List<String> readLocationList(){
        
        List<String> cityList = new ArrayList<>();       
        File locFile = null;
        
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(CITY_FILE);
        
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + CITY_FILE);
        } else {
            try{
                locFile = new File(resource.toURI());
            }catch(URISyntaxException e){
                // TODO
            }         
        }
        
        try {
            
            Scanner myReader = new Scanner(locFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                cityList.add(data);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        return cityList;
        
    }
    
}
