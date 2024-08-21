package com.usa.webBrowser;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

public class Image_disabilty{

    /**
     * Disables images in the Chrome browser by modifying the browser preferences.
     *
     * @param options The ChromeOptions object to modify.
     */
    public void disableImg(ChromeOptions options) {
        Map<String, Object> images = new HashMap<>();
        images.put("images", 2);

        Map<String, Object> preferences = new HashMap<>();
        preferences.put("profile.default_content_setting_values", images);

        options.setExperimentalOption("prefs", preferences);
    }

    /**
     * Disables images in the Firefox browser by setting preferences in the Firefox profile.
     *
     * @param options The FirefoxOptions object to modify.
     */
    public void disableImg(FirefoxOptions options) {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("permissions.default.image", 2);

        options.setProfile(profile);
        options.setCapability(FirefoxDriver.PROFILE, profile);
    }
}
