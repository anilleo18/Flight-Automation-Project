package code.usa.WebPages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.usa.webBrowser.Driver;

import code.usa.WebPages.*;
import code.usa.utils.PageScroll;
import code.usa.utils.Wait;
import code.usa.myReport.LogStatus;

public class FlightResultPage extends ElementVisibility{

    // Web elements for various filters and flight details
    @FindBy(xpath="//span[text()='Non Stop']/preceding-sibling::span")
    WebElement nonStopFilterCheckbox;

    @FindBy(xpath="//span[text()='1 Stop']/preceding-sibling::span")
    WebElement oneStopFilterCheckbox;

    @FindBy(xpath="//a[@class='fli-clear inlineB']")
    WebElement clearFilterButton;

    @FindBy(xpath="//div[@class='splitVw-sctn pull-left']/child::div[2]/child::div")
    List<WebElement> departureFlightsList;

    @FindBy(xpath="//div[@class='splitVw-sctn pull-right']/child::div[2]/child::div")
    List<WebElement> returnFlightsList;

    @FindBy(xpath="//div[@class='pull-right marL5 text-right']/p/span")
    List<WebElement> individualFlightPrices;

    @FindBy(xpath="//span[@class='splitVw-total-fare']")
    WebElement totalFlightCost;

    @FindBy(xpath="//span[@class='slashed-price']")
    WebElement discountedTotalCost;

    By selectedFlightPrice = By.xpath("//p[@class='actual-price']");

    // Constructor to initialize web elements on the page
    public FlightResultPage() {
        PageFactory.initElements(Driver.driver, this);
    }

    // Method to count the number of available departure flights
    public int getDepartureFlightCount() throws Exception {
        Wait.toBeclickable(nonStopFilterCheckbox);
        PageScroll.toBottomOfPage();
        if (departureFlightsList.isEmpty()) {
            LogStatus.fail("No departure flights found.");
            throw new Exception("No available departure flights.");
        }
        return departureFlightsList.size();
    }

    // Method to count the number of available return flights
    public int getReturnFlightCount() throws Exception {
        PageScroll.toUP();
        if (returnFlightsList.isEmpty()) {
            LogStatus.fail("No return flights found.");
            throw new Exception("No available return flights.");
        }
        return returnFlightsList.size();
    }

    // Method to get the flight count without any filters applied
    public int[] getAllFlightCounts() throws Exception {
        clearAllFilters();
        int[] counts = new int[2];
        counts[0] = getDepartureFlightCount();
        counts[1] = getReturnFlightCount();
        return counts;
    }

    // Method to get the flight count with the Non-Stop filter applied
    public int[] getNonStopFlightCounts() throws Exception {
        clearAllFilters();
        nonStopFilterCheckbox.click();
        int[] counts = new int[2];
        counts[0] = getDepartureFlightCount();
        counts[1] = getReturnFlightCount();
        return counts;
    }

    // Method to get the flight count with the One Stop filter applied
    public int[] getOneStopFlightCounts() throws Exception {
        clearAllFilters();
        oneStopFilterCheckbox.click();
        int[] counts = new int[2];
        counts[0] = getDepartureFlightCount();
        counts[1] = getReturnFlightCount();
        return counts;
    }

    // Method to select flights and retrieve their prices
    public Map<String, String> selectFlightsAndGetPrices(int departureFlightIndex, int returnFlightIndex) {
        Map<String, String> priceDetails = new HashMap<>();
        JavascriptExecutor jsExecutor = (JavascriptExecutor) Driver.driver;

        jsExecutor.executeScript("arguments[0].click();", departureFlightsList.get(departureFlightIndex));
        jsExecutor.executeScript("arguments[0].click();", returnFlightsList.get(returnFlightIndex));

        String[] depFlightDetails = departureFlightsList.get(departureFlightIndex).getAttribute("innerText").split("\\r?\\n");
        priceDetails.put("Departure Flight", depFlightDetails[13]);

        String[] retFlightDetails = returnFlightsList.get(returnFlightIndex).getAttribute("innerText").split("\\r?\\n");
        priceDetails.put("Return Flight", retFlightDetails[13]);

        priceDetails.put("Departure Price", individualFlightPrices.get(0).getText());
        priceDetails.put("Return Price", individualFlightPrices.get(1).getText());

        if (ElementVisibility.isVisble(discountedTotalCost)) {
            priceDetails.put("Total Price", discountedTotalCost.getText());
        } else {
            priceDetails.put("Total Price", totalFlightCost.getText());
        }
        return priceDetails;
    }

    // Method to clear any applied filters on the flight results
    public void clearAllFilters() {
        if (ElementVisibility.isVisble(clearFilterButton)) {
            clearFilterButton.click();
        }
    }
}