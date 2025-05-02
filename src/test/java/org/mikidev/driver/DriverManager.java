package org.mikidev.driver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class DriverManager {

    public DesiredCapabilities createDriver(String platformName) {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if ("android".equalsIgnoreCase(platformName)) {
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Medium_Phone_API_35");
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");
            capabilities.setCapability("appium:appPackage", "com.android.settings");
        } else if ("ios".equalsIgnoreCase(platformName)) {
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "18.4");
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 16 Pro Simulator");
            capabilities.setCapability(MobileCapabilityType.UDID, "1F96E1FA-BF43-4387-B1B6-FA9F9733ED3A");
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
            capabilities.setCapability("appium:bundleId", "com.apple.Preferences");
        } else {
            throw new IllegalArgumentException("Unsupported platform: " + platformName);
        }

        return capabilities;
    }

    public void takeScreenshot(String fileName, AppiumDriver driver) {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(screenshot.toPath(), Paths.get("screenshots", fileName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearScreenshots() {
        try {
            Files.createDirectories(Paths.get("screenshots"));
            Files.list(Paths.get("screenshots"))
                .filter(Files::isRegularFile)
                .forEach(file -> {
                    try {
                        Files.delete(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void waitForSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}
