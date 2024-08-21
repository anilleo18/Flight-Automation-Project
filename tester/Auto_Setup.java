package code.usa.tester;

import code.usa.myReport.ExtentReport;

import org.testng.annotations.BeforeSuite;

import com.usa.webBrowser.Driver;

import org.testng.annotations.AfterSuite;

public class Auto_Setup {

    // Set up browser and reporting framework before the test suite execution
    @BeforeSuite
    public void beforeSuite() {
        // Initialize the reporting system
        ExtentReport.initialize();
        
        // Start logging the test details
        ExtentReport.logger = ExtentReport.report.startTest("SELENIUM EXPO");
        
        // Initialize the browser driver
        Driver.initialize();
    }

    // Tear down browser and reporting framework after the test suite execution
    @AfterSuite
    public void afterSuite() {
        // Close the browser session
        Driver.quit();
        
        // Write the report details and finalize the report
        ExtentReport.report.flush();
    }
}
