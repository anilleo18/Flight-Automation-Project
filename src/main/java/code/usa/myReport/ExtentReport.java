package code.usa.myReport;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Supplier;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ExtentReport {
    
    public static ExtentReports report = null;
    public static ExtentTest logger = null;

    // Private constructor to limit initialization to one instance
    private ExtentReport() {
        String currentDate = getCurrentFormattedDate();
        
        report = new ExtentReports(".\\ExtentReports\\Test Report_" + currentDate + ".html");
        report.loadConfig(new File(".\\src//main//resources//extentreport.xml"));
    }

    // Initializes Report using lazy initialization with a Supplier
    public static void initialize() {
        Supplier<ExtentReport> reportSupplier = ExtentReport::new;
        reportSupplier.get();
    }

    // Utility method to get the current date formatted as a String
    private String getCurrentFormattedDate() {
        return new SimpleDateFormat("MMddyyyy_ hh_mm_ss").format(new Date());
    }
}
