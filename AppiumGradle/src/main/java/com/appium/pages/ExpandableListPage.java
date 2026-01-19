package com.appium.pages;

import com.appium.factory.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AndroidFindBys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ExpandableListPage extends BasePage{

    @AndroidFindBy(accessibility = "3. Simple Adapter")
   private WebElement simpleAdapter;

    public WebElement getGroup15Item() {
        return group15Item;
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\"Group 15\"]\n")
    private WebElement group15Item;

    public List<WebElement> getItems() {
        return items;
    }

    @AndroidFindBy(className = "android.widget.TwoLineListItem")
    private List<WebElement> items;
    public ExpandableListPage() {
        super(DriverFactory.getDriver());
    }

    public WebElement getSimpleAdapter() {
        return simpleAdapter;
    }
}
