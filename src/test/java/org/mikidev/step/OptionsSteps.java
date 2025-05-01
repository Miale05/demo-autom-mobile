package org.mikidev.step;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.mikidev.view.OptionsView;
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

public class OptionsSteps {
    private AppiumDriver driver;
    private OptionsView optionsView;
    
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

    @Given("el usuario esta en la aplicacion movil")
    public void elUsuarioEstaEnLaAplicacionMovil() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "18.4");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 16 Pro Simulator");
        capabilities.setCapability(MobileCapabilityType.UDID, "1F96E1FA-BF43-4387-B1B6-FA9F9733ED3A");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
        capabilities.setCapability("appium:bundleId", "com.apple.Preferences");

        URL appiumServerUrl = new URL("http://127.0.0.1:4723/");
        driver = new AppiumDriver(appiumServerUrl, capabilities);
        
        optionsView = new OptionsView(driver);

        clearScreenshots();
    }

    @When("el usuario ingresa a la opcion generales")
    public void elUsuarioIngresaALaOpcionGenerales() {
        takeScreenshot("general_button_clicked");
        optionsView.clicGeneralButton();
        
    }

    @Then("el usuario deberia ver otras opciones")
    public void elUsuarioDeberiaVerOtrasOpciones() {
        assertTrue(driver.getPageSource().contains("About"));
        assertEquals("Información", optionsView.getAboutText());
        takeScreenshot("about_text_visible");
        driver.quit();
    }
}