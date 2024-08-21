package code.usa.tester;

import org.testng.annotations.Test;
import code.usa.WebPages.ElementVisibility;

public class hashing_flights {

    // Method to search for flights using provided FROM, TO, DEPARTURE, and RETURN details
    @Test
    public void searchFlights() {
        // Create an instance of the Home_Page_flight class
    	ElementVisibility homePage = new ElementVisibility();
        
        // Execute the search functionality
        homePage.isVisble(null);
    }
}
