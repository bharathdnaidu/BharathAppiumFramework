package com.appium.factory;

import com.appium.utils.ConfigReader;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {

    public static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

    public static AppiumDriver getDriver()
    {
        return driver.get();
    }

    public static void createDriver(String platform,String devicename,int sysport)  {

        if(platform.equalsIgnoreCase("android")) {
            UiAutomator2Options options = new UiAutomator2Options();
            options.setCapability("platformName", platform);
            options.setCapability("platformVersion", ConfigReader.getProperty("platformVersion"));
            options.setCapability("udid", devicename);
            options.setSystemPort(sysport);
            options.setCapability("app", ConfigReader.getProperty("appPath"));
            options.setCapability("automationName", ConfigReader.getProperty("automationName"));

            try {
                driver.set(new AndroidDriver(new URL(ConfigReader.getProperty("URL")), options));
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
        else if(platform.equalsIgnoreCase("ios")) {
            XCUITestOptions options=new XCUITestOptions();
            options.setCapability("platformName", "iOS");
            options.setCapability("platformVersion", "16.0");
            options.setCapability("deviceName", "Emulator-pixel9a");
            options.setCapability("automationName", "XCUITest");

            try {
                driver.set(new IOSDriver(new URL(ConfigReader.getProperty("URL")),options));
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public static void quitDriver()
    {
        if(getDriver()!=null)
        {
            getDriver().quit();
            driver.remove();
        }
    }

}
