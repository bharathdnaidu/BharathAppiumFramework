package com.appium;

import com.appium.factory.DriverFactory;
import com.appium.utils.ConfigReader;
import com.appium.utils.DeviceManager;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.SkipException;
import org.testng.annotations.*;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class BaseTest {

    Logger log= LogManager.getLogger(BaseTest.class);
    AppiumDriverLocalService appiumDriverLocalService;


    @DataProvider(name="deviceprovider",parallel = true)
    public Object[][] deviceSupplier()
    {
        List<String> deviceList=DeviceManager.getConnectedDevices();
        Object[][] data=new Object[deviceList.size()][2];
        for(int i=0;i<deviceList.size();i++)
        {
            data[i][0]=deviceList.get(i);
            data[i][1]=8200;
        }
        return data;
    }

    @BeforeSuite
    public void setup() throws MalformedURLException {

       appiumDriverLocalService= new AppiumServiceBuilder().
               withAppiumJS(new File(ConfigReader.getProperty("appiumJs"))).
              usingDriverExecutable(new File(ConfigReader.getProperty("node"))).usingPort(ConfigReader.getIntProperty("portNumber")).withIPAddress(ConfigReader.getProperty("ipAddress")).build();
      appiumDriverLocalService.start();
    }

    @BeforeMethod
    public void setMethod(Object[] data)  {
        String udid=(String)data[0];
        int sysport=(Integer)data[1];
       if(!DeviceManager.isDeviceAvailable(udid))
        {

                log.error("❌ ERROR: Device " +udid + " is not connected via ADB!");
                log.info("skip the test instead of crashing with a complicated error");
                throw new SkipException("Skipping test because device was not found.");

        }
        DriverFactory.createDriver(ConfigReader.getProperty("platformName"),udid,sysport);

    }

    @AfterMethod
    public void teardownMethod() {

        DriverFactory.quitDriver();
    }

    @AfterSuite
    public void teardown() {
        appiumDriverLocalService.stop();
    }
}
