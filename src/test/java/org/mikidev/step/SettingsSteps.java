package org.mikidev.step;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.mikidev.view.OptionsView;
import org.mikidev.view.SettingsView;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SettingsSteps {
    private AppiumDriver driver;
    private SettingsView settingsView;
    
    private void takeScreenshot(String fileName) {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(screenshot.toPath(), Paths.get("screenshots", fileName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearScreenshots() {
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

    private void waitForSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    @Given("el usuario esta en la aplicacion movil android")
    public void elUsuarioEstaEnLaAplicacionMovilAndroid() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Medium_Phone_API_35");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");
        capabilities.setCapability("appium:appPackage", "com.android.settings");

        URL appiumServerUrl = new URL("http://127.0.0.1:4723/");
        driver = new AppiumDriver(appiumServerUrl, capabilities);
        
        settingsView = new SettingsView(driver);

        clearScreenshots();
    }

    @When("el usuario ingresa a la opcion conexion")
    public void elUsuarioIngresaALaOpcionConexion() {
        waitForSeconds(3);
        takeScreenshot("android_settings_view");
        settingsView.clicNetworkButton();
    }

    @Then("el usuario deberia ver la opcion internet")
    public void elUsuarioDeberiaVerLaOpcionInternet() {
        assertEquals("Internet", settingsView.getInternetText());
        waitForSeconds(3);
        takeScreenshot("android_internet_view");
        driver.quit();
    }
}