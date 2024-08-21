package com.usa.webBrowser;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserHeadlessMode {

    /**
     * Configures Chrome to run in headless mode with specified window size.
     *
     * @param options The ChromeOptions object to modify.
     */
    public void headless(ChromeOptions options) {
        options.addArguments("window-size=1400,800");
        options.addArguments("headless");
    }

    /**
     * Configures Firefox to run in headless mode.
     *
     * @param options The FirefoxOptions object to modify.
     */
    public void headless(FirefoxOptions options) {
        FirefoxBinary firefoxBinary = new FirefoxBinary();
        firefoxBinary.addCommandLineOptions("--headless");
        options.setBinary(firefoxBinary);
    }
}
