package org.mikidev.step;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.mikidev.view.AndroidSettingsView;
import org.mikidev.driver.DriverManager;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertEquals;

public class AndroidSettingsSteps {
    private AppiumDriver driver;
    private DriverManager driverManager = new DriverManager();
    private AndroidSettingsView androidSettingsView;

    @Given("el usuario esta en la aplicacion movil android")
    public void elUsuarioEstaEnLaAplicacionMovilAndroid() throws MalformedURLException {

        DesiredCapabilities capabilities = driverManager.createDriver("android");
        URL appiumServerUrl = new URL("http://127.0.0.1:4723/");

        driver = new AppiumDriver(appiumServerUrl, capabilities);
        
        androidSettingsView = new AndroidSettingsView(driver);

        driverManager.clearScreenshots();
    }

    @When("el usuario ingresa a la opcion conexion")
    public void elUsuarioIngresaALaOpcionConexion() {
        driverManager.waitForSeconds(3);
        driverManager.takeScreenshot("android_settings_view", driver);
        androidSettingsView.clicNetworkButton();
    }

    @Then("el usuario deberia ver la opcion internet")
    public void elUsuarioDeberiaVerLaOpcionInternet() {
        assertEquals("Internet", androidSettingsView.getInternetText());
        driverManager.waitForSeconds(3);
        driverManager.takeScreenshot("android_internet_view", driver);
        driver.quit();
    }
}