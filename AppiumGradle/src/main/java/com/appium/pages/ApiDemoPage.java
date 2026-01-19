package com.appium.pages;

import com.appium.factory.DriverFactory;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ApiDemoPage extends BasePage {

    WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
    public WebElement getViews() {
        wait.until(ExpectedConditions.visibilityOf(views));
        return views;
    }
    @AndroidFindBy(accessibility = "Views")
    private WebElement views;

    public ApiDemoPage()
    {
        super(DriverFactory.getDriver());
    }







}
