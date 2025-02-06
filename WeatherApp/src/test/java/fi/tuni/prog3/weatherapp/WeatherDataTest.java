/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package fi.tuni.prog3.weatherapp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *JUnit test class for the WeatherData class.
 * 
 * @author janii
 */
public class WeatherDataTest {
    
    public WeatherDataTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of lookUpLocation method, of class WeatherData.
     * 
     * @throws java.lang.Exception
     */
    @Test
    public void testLookUpLocationCoordinates() throws Exception {
        System.out.println("lookUpLocation");
        String city = "Tampere";
        String country = "FI";
        Location expResult = new Location();
        expResult.setName("Tampere");
        expResult.setCountry("FI");
        expResult.setLat(61.4980214);
        expResult.setLon(23.7603118);
        
        Location result = WeatherData.lookUpLocation(city, country);
        assertEquals(expResult.getLat(), result.getLat());
        assertEquals(expResult.getLon(), result.getLon());
    }
    
    /**
     * Test of lookUpLocation method, of class WeatherData.
     * 
     * The parameter town does not exist and therefore the
     * result should be an exception.
     * @throws java.lang.Exception
     */
    @Test
    public void testLookUpLocationNoSuchTown() throws Exception {
        System.out.println("lookUpLocationNoSuchTown");
        String city = "XXXXXXXXXXXXXXXXXX";
        String country = "XX";
        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
          WeatherData.lookUpLocation(city, country);  
        });
        
        String exceptedMessage = "No such location!";
        String actualMessage = exception.getMessage();
        
        assertTrue(actualMessage.contains(exceptedMessage));
    }
    
    
    /**
     * Test of getCurrentWeather method, of class WeatherData.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetCurrentWeather() throws Exception {
        System.out.println("getCurrentWeather");
        double tampereLat = 61.498;
        double tampereLon = 23.7603;
        String unit = "metric";
        
        int expCode = 200;
        
        Weather expResult = null;
        Weather result = WeatherData.getCurrentWeather(tampereLat, tampereLon, unit);
        
       // assertEquals(expCode, result.getCod());

    }

    /**
     * Test of fetchCurrentWeather method, of class WeatherData.
     * @throws java.lang.Exception
     */
    @Test
    public void testFetchCurrentWeather() throws Exception {
        System.out.println("fetchCurrentWeather");
        
        Location testLocation = new Location();
        testLocation.setName("Tampere");
        testLocation.setCountry("FI");
        testLocation.setLat(61.498);
        testLocation.setLon(23.7603);
        
        String unit = "metric";
        
        int expCode = 200;
        
        Weather expResult = null;
        Weather result = WeatherData.fetchCurrentWeather(testLocation, unit);
        
        assertEquals(expCode, result.getCod());

    }
    
        
    /**
     * Test of getCurrentWeather method, of class WeatherData.
     * 
     * Test uses nonexistent coordinates.
     * 
     * @throws java.lang.Exception
     */
    @Test
    public void testGetCurrentWeatherWrongCoords() throws Exception {
        System.out.println("getCurrentWeatherWrongCoords");
        double tampereLat = 200.0;
        double tampereLon = 200.0;
        String unit = "metric";
        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
          WeatherData.getCurrentWeather(tampereLat, tampereLon, unit);  
        });
        
        String exceptedMessage = "wrong latitude";
        String actualMessage = exception.getMessage();
        
        assertTrue(actualMessage.contains(exceptedMessage));
    }
    
    /**
     * Test of fetchCurrentWeather method, of class WeatherData.
     * 
     * Test uses faulty Location latitude and longitude.
     * 
     * @throws java.lang.Exception
     */
    @Test
    public void testFetchCurrentWeatherWrongLocation() throws Exception {
        System.out.println("fetchCurrentWeatherWrongLocation");
        
        Location testLocation = new Location();
        testLocation.setName("Tampere");
        testLocation.setCountry("FI");
        testLocation.setLat(200.0);
        testLocation.setLon(200.0);
        
        String unit = "metric";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
          WeatherData.fetchCurrentWeather(testLocation, unit);  
        });
        
        String exceptedMessage = "wrong latitude";
        String actualMessage = exception.getMessage();
        
        assertTrue(actualMessage.contains(exceptedMessage));
    }
    
    
    /**
     * Test of getForecast method, of class WeatherData.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetForecast() throws Exception {
        System.out.println("getForecast");
        double tampereLat = 61.4980214;
        double tampereLon = 23.7603118;
        String type = "hourly";
        String unit = "metric";
        
        int expCode = 200;
    
        // hourly-type returns 96 individual weather items
        int individualForecasts = 96;
        
        WeatherForecast result = WeatherData.getForecast(tampereLat, tampereLon, type, unit);
        
        assertEquals(expCode, result.getCod());
        
        assertEquals(individualForecasts, result.getList().size());
    }

    /**
     * Test of getForecast16d method, of class WeatherData.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetForecast16d() throws Exception {
        System.out.println("getForecast16d");
        double tampereLat = 61.4980214;
        double tampereLon = 23.7603118;
        String type = "daily";
        String unit = "metric";
        
        int expCode = 200;
        
        // daily-type returns 16 individual weather items
        int individualForecasts = 16;
        
        WeatherForecast16 result = WeatherData.getForecast16d(tampereLat, tampereLon, type, unit);
        
        assertEquals(expCode, result.getCod());
        
        assertEquals(individualForecasts, result.getList().size());
    }

    /**
     * Test of fetchForecast method, of class WeatherData.
     * @throws java.lang.Exception
     */
    @Test
    public void testFetchForecast() throws Exception {
        System.out.println("fetchForecast");
        
        Location testLocation = new Location();
        testLocation.setName("Tampere");
        testLocation.setCountry("FI");
        testLocation.setLat(61.4980214);
        testLocation.setLon(23.7603118);
        
        String type = "hourly";
        String unit = "metric";
        int expCode = 200;
        
        // hourly-type returns 96 individual weather items
        int individualForecasts = 96;
        
        WeatherForecast result = WeatherData.fetchForecast(testLocation, type, unit);

        assertEquals(expCode, result.getCod());
        
        assertEquals(individualForecasts, result.getList().size());
    }

    
    /**
     * Test of checkRequestForErrors method, of class WeatherData.
     */
    @Test
    public void testCheckRequestForErrors_200() {
        System.out.println("checkRequestForErrors_200");
        String response = "{\n" +
"    \"cod\": 200,\n" +
"    \"message\": \"Everything is A OK!\"\n" +
"}";
        
        assertDoesNotThrow(() -> {
            WeatherData.checkRequestForErrors(response); 
        });
        
    }
    
    /**
    * Test of checkRequestForErrors method, of class WeatherData.
    */
    @Test
    public void testCheckRequestForErrors_400() {
        System.out.println("checkRequestForErrors_400");
        String response = "{\n" +
"    \"cod\": 400,\n" +
"    \"message\": \"Generic error\"\n" +
"}";
        
        Exception exception = assertThrows(IllegalArgumentException.class, 
                () -> {
            WeatherData.checkRequestForErrors(response); 
        });
    }
    
        /**
    * Test of checkRequestForErrors method, of class WeatherData.
    */
    @Test
    public void testCheckRequestForErrors_401() {
        System.out.println("checkRequestForErrors_401");
        String response = "{\n" +
"    \"cod\": 401,\n" +
"    \"message\": \"Invalid API key. Please see https://openweathermap.org/faq#error401 for more info.\"\n" +
"}";
        
        Exception exception = assertThrows(IllegalArgumentException.class, 
                () -> {
            WeatherData.checkRequestForErrors(response); 
        });
    }
    
    /**
    * Test of checkRequestForErrors method, of class WeatherData.
    */
    @Test
    public void testCheckRequestForErrors_404() {
        System.out.println("checkRequestForErrors_404");
        String response = "{\n" +
"    \"cod\": 404,\n" +
"    \"message\": \"Incorrect API request format.\"\n" +
"}";
        
        Exception exception = assertThrows(IllegalArgumentException.class, 
                () -> {
            WeatherData.checkRequestForErrors(response); 
        });
    }
    
    /**
    * Test of checkRequestForErrors method, of class WeatherData.
    */
    @Test
    public void testCheckRequestForErrors_429() {
        System.out.println("checkRequestForErrors_429");
        String response = "{\n" +
"    \"cod\": 429,\n" +
"    \"message\": \"Over 60 API calls per minute!\"\n" +
"}";
        
        Exception exception = assertThrows(IllegalArgumentException.class, 
                () -> {
            WeatherData.checkRequestForErrors(response); 
        });
    }
}
