package org.mikidev.step;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.mikidev.view.IOSOptionsView;
import org.mikidev.driver.DriverManager;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class IOSOptionsSteps {
    private AppiumDriver driver;
    private DriverManager driverManager = new DriverManager();
    private IOSOptionsView iosOptionsView;

    @Given("el usuario esta en la aplicacion movil ios")
    public void elUsuarioEstaEnLaAplicacionMovilIos() throws MalformedURLException {
        DesiredCapabilities capabilities = driverManager.createDriver("ios");
        URL appiumServerUrl = new URL("http://127.0.0.1:4723/");

        driver = new AppiumDriver(appiumServerUrl, capabilities);
        
        iosOptionsView = new IOSOptionsView(driver);

        driverManager.clearScreenshots();
    }

    @When("el usuario ingresa a la opcion generales")
    public void elUsuarioIngresaALaOpcionGenerales() {
        driverManager.waitForSeconds(3);
        driverManager.takeScreenshot("ios_general_button_clicked", driver);
        iosOptionsView.clicGeneralButton();
    }

    @Then("el usuario deberia ver otras opciones")
    public void elUsuarioDeberiaVerOtrasOpciones() {
        assertTrue(driver.getPageSource().contains("About"));
        assertEquals("Información", iosOptionsView.getAboutText());
        driverManager.waitForSeconds(3);
        driverManager.takeScreenshot("ios_about_text_visible", driver);
        driver.quit();
    }
}