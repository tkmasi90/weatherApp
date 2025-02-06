package fi.tuni.prog3.weatherapp;

/**
 * Interface with methods to read from a file and write to a file.
 * @author ??
 */
public interface iReadAndWriteToFile {

    /**
     * Reads JSON from the given file.
     * @param fileName name of the file to read from.
     * @return true if the read was successful, otherwise false.
     * @throws Exception if the method e.g, cannot find the file.
     */
    public User readFromFile(String fileName) throws Exception;

    /**
     * Write the student progress as JSON into the given file.
     * @param fileName name of the file to write to.
     * @param user the User object being serialized
     * @return true if the write was successful, otherwise false.
     * @throws Exception if the method e.g., cannot write to a file.
     */
    public boolean writeToFile(String fileName, User user) throws Exception;
}
