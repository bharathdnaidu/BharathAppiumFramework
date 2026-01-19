package com.appium.pages;

import com.appium.factory.DriverFactory;
import com.appium.utils.DeviceManager;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    public static final Logger log = LogManager.getLogger(DeviceManager.class);
    AppiumDriver driver;

    WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));

    public  BasePage(AppiumDriver driver) {
        this.driver=driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }


    public void scrollToText(String text) {
        log.info("scrollToElement");
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(text(\""+text+"\"))"));
    }

    public void scrollTillLast() {
        log.info("scrolling till the end of the container");
        boolean swipemore;
        do{
             swipemore = (boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of("left",100,"top",100,"height",200,"width",200,
                    "direction", "down", "percent", 0.75));
        }while(swipemore);

    }

    public void scrollTillLastOfTheContainer(WebElement container)
    {
        boolean scrollmore;

        do {
            scrollmore = (boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of("elementId", ((RemoteWebElement) container).getId(),
                    "direction", "down", "percent", 0.5));
        }while(scrollmore);

    }




}
