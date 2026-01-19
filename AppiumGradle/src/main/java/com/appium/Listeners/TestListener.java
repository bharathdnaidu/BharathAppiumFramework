package com.appium.Listeners;

import com.appium.factory.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.screenrecording.CanRecordScreen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;


public class TestListener implements ITestListener {

    public static final Logger log=LogManager.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
        log.info("Test Started"+result.getMethod().getMethodName());
        AppiumDriver driver=DriverFactory.getDriver();
        if(driver!=null) {
            ((CanRecordScreen) driver).startRecordingScreen();
        }

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
        AppiumDriver driver=DriverFactory.getDriver();
        String base64video = ((CanRecordScreen) driver).stopRecordingScreen();
    }

    private String getTimeStamp(){
       return new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").format(new Date());
    }
    @Override
    public void onTestFailure(ITestResult result) {

        ITestListener.super.onTestFailure(result);
        AppiumDriver driver=DriverFactory.getDriver();
        File srcFile=null;
        if(driver!=null){
            srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        }
        File srcTarget=new File("TestOutPut/images/"+result.getName()+"_"+getTimeStamp()+".png");
        try {
            Files.createDirectories(srcTarget.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            Files.copy(srcFile.toPath(),srcTarget.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String base64video=((CanRecordScreen)driver).stopRecordingScreen();

        Path path =Paths.get("TestOutPut","videos");

        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        File videoPath=new File("TestOutPut/videos/"+result.getName()+"_"+getTimeStamp()+".mp4");
        byte[] data = Base64.getDecoder().decode(base64video);

        try(FileOutputStream fos=new FileOutputStream(videoPath)){
            fos.write(data);
            log.info("Video Created Successfully"+videoPath);

        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }


        log.info("Screenshot Created Successfully and saved in :"+srcTarget.toPath());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
        // 1. Log the skip status
        log.warn("⚠️ SKIPPED: " + result.getMethod().getMethodName());

        // 2. Extract and log the reason (the message you wrote in the SkipException)
        if (result.getThrowable() != null) {
            log.warn("Reason for Skip: " + result.getThrowable().getMessage());
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
    }
}
