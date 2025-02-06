/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package fi.tuni.prog3.weatherapp;

import java.io.FileNotFoundException;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author janii
 */
public class FileDataTest {
    
    private static final FileData fileData = new FileData(); 
    
    public FileDataTest() {
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
     * Test of readFromFile method, of class FileData.
     * 
     * Tests FileNotFoundException by trying to read a file that does not exist
     * 
     * @throws java.io.FileNotFoundException
     */
    @Test
    public void testReadFromFileFileNotFound() throws FileNotFoundException {
        System.out.println("readFromFileFileNotFound");
        String fileName = "THIS_FILE_SHOULD_NOT_EXIST";
        
        Exception exception = assertThrows(FileNotFoundException.class, () -> {
            fileData.readFromFile(fileName);
        });
        
        String exceptedMessage = "The system cannot find the file specified";
        String actualMessage = exception.getMessage();
        
        assertTrue(actualMessage.contains(exceptedMessage));
    }

    /**
     * Test of writeToFile method, of class FileData.
     * 
     * Creates a test User object and tries to serialize that onto the
     * disk.
     * @throws java.lang.Exception
     */
    @Test
    public void testWriteToFile() throws Exception {
        System.out.println("writeToFile");
        
        User testUser = new User();
        Location testLocation = new Location();
        testLocation.setName("Tampere");
        testLocation.setCountry("FI");
        testLocation.setLat(61.4980214);
        testLocation.setLon(23.7603118);
        
        testUser.setActiveLocation(testLocation);
        
        String fileName = "TEST_FILE";

        boolean expResult = true;
        
        boolean result = fileData.writeToFile(fileName, testUser);
        assertEquals(expResult, result);
    }

    /**
    * Test of readFromFile method, of class FileData.
    * 
    * Creates a test User object, serializes that onto the
    * disk and finally tries to read it.
    * 
    * @throws java.io.FileNotFoundException
    */
    @Test
    public void testReadFromFile() throws FileNotFoundException, Exception {
        System.out.println("readFromFile");
        
        User testUser = new User();
        Location testLocation = new Location();
        testLocation.setName("Tampere");
        testLocation.setCountry("FI");
        testLocation.setLat(61.4980214);
        testLocation.setLon(23.7603118);
        
        testUser.setActiveLocation(testLocation);
        
        String fileName = "TEST_FILE";
               
        boolean result = fileData.writeToFile(fileName, testUser);
        
        User newUser = fileData.readFromFile(fileName);
        
        assertTrue(newUser.getActiveLocation().getName().equals("Tampere"));
    }

    /**
     * Test of readLocationList method, of class FileData.
     * 
     * Reads the location list from the resources. Checks that the first item
     * is correct.
     */
    @Test
    public void testReadLocationList() {
        System.out.println("readLocationList");

        List<String> result = fileData.readLocationList();
        assertEquals("Moscow;RU", result.get(1));

    }
    
}
