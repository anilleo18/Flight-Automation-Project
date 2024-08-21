package code.usa.tester;

import org.testng.annotations.Test;
import code.usa.WebPages.FlightResultPage;
import code.usa.myReport.LogStatus;
import code.usa.utils.Page_window;

public class number_of_carriers {
    
    private static FlightResultPage flightResultPage = null;
    private int[] flightCounts;
    
    // Test to log and print the number of available flights without any filters applied
    @Test(priority = 1)
    public void logFlightCountsWithoutFilters() throws Exception {
        flightResultPage = Page_window.obj;
        flightCounts = flightResultPage.getAllFlightCounts();
        
        System.out.println("***** Flight Count when no filters are applied *****");
        System.out.println("Departure Flight count: " + flightCounts[0] + "\nReturn Flight Count: " + flightCounts[1]);
        
        LogStatus.pass("Departure Flight count: " + flightCounts[0] + "\nReturn Flight Count: " + flightCounts[1]);
    }
    
   
}
