package code.usa.utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.usa.webBrowser.Driver;

import code.usa.utils.pick_the_date;
import code.usa.utils.DynamicXpath;
import code.usa.utils.Wait;
import code.usa.property.Reading.File_Reading;
import code.usa.WebPages.ElementVisibility;
import code.usa.WebPages.FlightResultPage;

public class Page_window {

    @FindBy(xpath = "//a[contains(@href,'flights') and contains(@class,'hrtlCenter')]")
    private WebElement flightsTab;

    @FindBy(xpath = "//li[contains(text(),'Round Trip')]/child::span")
    private WebElement roundTrip;

    @FindBy(xpath = "//input[@id='fromCity']")
    private WebElement fromCity;

    @FindBy(xpath = "//input[@placeholder='From']")
    private WebElement fromCityInput;

    @FindBy(xpath = "//input[@placeholder='To']")
    private WebElement toCityInput;

    @FindBy(xpath = "//li[@class='react-autosuggest__suggestion react-autosuggest__suggestion--first']")
    private WebElement firstAutoSuggestOption;

    @FindBy(xpath = "//label[@for='departure']")
    private WebElement departureDateLabel;

    @FindBy(xpath = "//label[@for='return']")
    private WebElement returnDateLabel;

    @FindBy(xpath = "//a[@class='primaryBtn font24 latoBlack widgetSearchBtn ']")
    private WebElement searchButton;

    private String datePickerXpath = "//div[@aria-label='%replacable%' and @aria-disabled='false']";
    private String tripTypeXpath = "//li[contains(text(),'%replacable%')]/child::span";

    public static FlightResultPage obj = null;

    // Constructor to initialize web elements using PageFactory
    public Page_window() {
        PageFactory.initElements(Driver.driver, this);
    }

    // Searches for flights by entering FROM, TO, DEPARTURE, and RETURN details.
    // Clicks on search and returns the FlightResultPage object.
    public FlightResultPage web_search() {
        flightsTab.click();
        Driver.driver.findElement(DynamicXpath.get(tripTypeXpath, File_Reading.get("Trip"))).click();

        fromCity.click();
        fromCityInput.sendKeys(File_Reading.get("From"));

        Wait.toBeclickable2(firstAutoSuggestOption, File_Reading.get("From"));
        firstAutoSuggestOption.click();

        toCityInput.sendKeys(File_Reading.get("To"));

        departureDateLabel.click();
        pick_the_date date = pick_the_date.getDates();

        Driver.driver.findElement(DynamicXpath.get(datePickerXpath, date.getDepartureDate())).click();
        Driver.driver.findElement(DynamicXpath.get(datePickerXpath, date.getReturnDate())).click();

        searchButton.click();

        return new FlightResultPage();
    }
}
