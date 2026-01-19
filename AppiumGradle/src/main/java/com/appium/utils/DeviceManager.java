package com.appium.utils;

import jdk.javadoc.doclet.Reporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeviceManager {

    private static final Logger log = LogManager.getLogger(DeviceManager.class);
    public static List<String> getConnectedDevices() {

        List<String> deviceList = new ArrayList<>();
        try {
            log.info("Getting connected devices");
            log.info(System.getenv("ANDROID_HOME"));
            Process process = Runtime.getRuntime().exec("adb devices");
            log.info("waiting for the adb command to complete");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            log.info(reader.readLine());
            while ((line = reader.readLine()) != null) {
              log.info(line);
                if (line.endsWith("device")) {
                   String udid = line.split("\t")[0].trim();
                    deviceList.add(udid);
                }
            }
            log.info("Connected devices are :{}",deviceList);
        } catch (Exception e) {
            throw new RuntimeException(
                    e);
        }
        return deviceList;
    }
    public static boolean isEmulator(String deviceName)
{
   return deviceName.contains("Emulator");
}

public static boolean isDeviceAvailable(String udid)
{
    List<String> devices=getConnectedDevices();
    return devices.contains(udid);
}


}
