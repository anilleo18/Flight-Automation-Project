package code.usa.tester;

import org.testng.annotations.Test;

import code.usa.WebPages.FlightResultPage;
import code.usa.WebPages.ElementVisibility;
import code.usa.myReport.LogStatus;
import code.usa.utils.Page_window;

public class number_of_flights {
	
    private static FlightResultPage pageObj = null;
    private int[] counts;
	
    // Logs and prints the number of flights available without any filters applied
    @Test(priority = 1)
    public void NoFilter_Frequency_flights() throws Exception {
        pageObj = Page_window.obj;
        counts = pageObj.getAllFlightCounts();
        
        System.out.println("***** Flight Count when No checkboxes are selected *****");
        System.out.println("Departure Flight count: " + counts[0] + "\nReturn Flight Count: " + counts[1]);
        
        LogStatus.pass("Departure Flight count: " + counts[0] + "\nReturn Flight Count: " + counts[1]);
    }
    
    // Logs and prints the number of flights available when the Non-Stop filter is applied
    @Test(priority = 2)
    public void NoStops_Frequency_flights() throws Exception {
        pageObj = Page_window.obj;
        
        System.out.println("***** Flight Count when Non-Stop checkbox is selected *****");
        counts = pageObj.getNonStopFlightCounts();
        System.out.println("Departure Flight count: " + counts[0] + "\nReturn Flight Count: " + counts[1]);
        
        LogStatus.pass("Departure Flight count: " + counts[0] + "\nReturn Flight Count: " + counts[1]);
    }
    
    // Logs and prints the number of flights available when the One-Stop filter is applied
    @Test(priority = 3)
    public void OnStop_Frequency_flights() throws Exception {
        pageObj = Page_window.obj;
        
        System.out.println("***** Flight Count when 1 Stop checkbox is selected *****");
        counts = pageObj.getOneStopFlightCounts();
        System.out.println("Departure Flight count: " + counts[0] + "\nReturn Flight Count: " + counts[1]);
        
        LogStatus.pass("Departure Flight count: " + counts[0] + "\nReturn Flight Count: " + counts[1]);
    }
}
