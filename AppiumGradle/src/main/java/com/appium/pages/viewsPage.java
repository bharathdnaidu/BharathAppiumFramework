package com.appium.pages;

import com.appium.factory.DriverFactory;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;


public class viewsPage extends BasePage {


    @AndroidFindBy(accessibility = "WebView3")
    private WebElement webView3;

    @AndroidFindBy(id = "android:id/list")
    private WebElement scrollContainer;

    public WebElement getExpandablelist() {
        return expandablelist;
    }

    @AndroidFindBy(accessibility = "Expandable Lists")
    private WebElement expandablelist;

    public viewsPage( ) {
        super(DriverFactory.getDriver());
    }

    public WebElement getWebView3() {
        return webView3;
    }

    public WebElement getScrollContainer() {
        return scrollContainer;
    }
}
