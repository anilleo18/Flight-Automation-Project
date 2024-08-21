package com.usa.webBrowser;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import code.usa.Listner.Event_Handler;
import code.usa.myReport.LogStatus;
import code.usa.property.Reading.File_Reading;


public class Driver extends File_Reading
{
	public static WebDriver driver=null;
	
	private Driver()
	{
		String browser=File_Reading.get("Browser");
		String headless=File_Reading.get("HeadlessMode");
		String imageDisable=File_Reading.get("DisableImage");
		if(browser.equalsIgnoreCase("chrome")|| browser.toUpperCase().contains("CHROME"))
		{
			try{
				
				System.setProperty("webdriver.chrome.driver",OsPath.getPath(browser));
				ChromeOptions options=new ChromeOptions();
				options.addArguments("--incognito");
				if(imageDisable.equalsIgnoreCase("yes"))
				{
					new Image_disabilty().disableImg(options);
				}
				/*if(headless.equalsIgnoreCase("yes"))
				{
					new HeadlessMode().headless(options);
				}*/
				DesiredCapabilities capabilites=DesiredCapabilities.chrome();
				capabilites.setCapability(ChromeOptions.CAPABILITY, options);
				driver=new ChromeDriver(options);
				
				LogStatus.pass("Chrome drive launched with headless mode = "+headless.toUpperCase()+", Image Disable mode = "+imageDisable.toUpperCase());
								
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		else if (browser.equalsIgnoreCase("FF")|| browser.toUpperCase().contains("FIRE")) 
		{
			try
			{
				
				System.setProperty("webdriver.gecko.driver",OsPath.getPath(browser));
				FirefoxOptions FFoptions=new FirefoxOptions();
				if(imageDisable.equalsIgnoreCase("yes"))
				{
					new Image_disabilty().disableImg(FFoptions);
				}
				if(headless.equalsIgnoreCase("yes"))
				{
					new BrowserHeadlessMode().headless(FFoptions);
				}
				
				driver=new FirefoxDriver(FFoptions);
				
				
				LogStatus.pass("FF drive launched with headless mode = "+headless.toUpperCase()+", Image Disable mode = "+imageDisable.toUpperCase());
				
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
				LogStatus.fail(e);
			}
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		web_Event_HandlerInit();
		driver.get(File_Reading.get("url"));
		driver.manage().deleteAllCookies();
	}
	
	//Initializes browser instance
	public static void initialize()
	{
		new Driver();
	}
	//quits browser
	public static void quit()
	{
		driver.quit();
	}
	//initializes WebDriver EventListner
	public void web_Event_HandlerInit()
	{
		EventFiringWebDriver eventHandle=new EventFiringWebDriver(driver);
		Event_Handler handler=new Event_Handler();
		eventHandle.register(handler);
		driver=eventHandle;
	}
	
	
}