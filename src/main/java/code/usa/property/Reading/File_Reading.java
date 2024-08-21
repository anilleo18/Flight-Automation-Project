package code.usa.property.Reading;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class File_Reading {

    // Reads the value of a property from the properties file
    public static String get(String PropertyName) {
        String returnProperty = "";
        Properties property = new Properties();
        
        try (FileInputStream file = new FileInputStream(
                System.getProperty("user.dir") + "\\src//main//resources//TestRunDetails.properties")) {
            
            // Load the properties from the file
            property.load(file);
            
            // Retrieve the property value
            returnProperty = property.getProperty(PropertyName);
            
            // If property is not found, throw an exception
            if (returnProperty == null) {
                throw new Exception("Property with name: " + PropertyName + " not found in " +
                        System.getProperty("user.dir") + "\\src//main//resources//TestRunDetails.properties. Please check again.");
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error loading properties file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        return returnProperty;
    }
}
