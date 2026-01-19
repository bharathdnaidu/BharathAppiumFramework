package com.appium;

import com.appium.factory.DriverFactory;
import com.appium.pages.ApiDemoPage;
import com.appium.pages.ExpandableListPage;
import com.appium.pages.viewsPage;
import com.appium.utils.DeviceManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class FirstTest extends BaseTest{

    public static final Logger log= LogManager.getLogger(FirstTest.class);

//    @Test(dataProvider = "deviceprovider")
//    public void firstTest(String udid,int sysport){
//
//        ApiDemoPage apiDemoPage=new ApiDemoPage();
//        viewsPage viewsPage=new viewsPage();
//        apiDemoPage.getViews().click();
//          apiDemoPage.scrollTillLast();
//        Assert.assertTrue(viewsPage.getWebView3().isDisplayed());
//
//    }

    @Test(dataProvider = "deviceprovider")
    public void Testexpandable(String udid,int sysport)
    {
        ApiDemoPage apiDemoPage=new ApiDemoPage();
        apiDemoPage.getViews().click();
        viewsPage viewsPage=new viewsPage();
        viewsPage.getExpandablelist().click();
        ExpandableListPage expandableListPage=new ExpandableListPage();
        expandableListPage.getSimpleAdapter().click();
        expandableListPage.scrollToText("Group 15");
        expandableListPage.getGroup15Item().click();
        Assert.assertFalse(true);
//        expandableListPage.scrollTillLastOfTheContainer(expandableListPage.getGroup15Item());
//        List<WebElement> item = expandableListPage.getItems();
//        item.get(item.size()-1).click();



    }
}
