package org.mikidev.view;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.AndroidFindBy;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SettingsView {
    private AppiumDriver driver;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"Network & internet\"]")
    private WebElement networkButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"Internet\"]")
    private WebElement internetButton;

    public SettingsView(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    public void clicNetworkButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(networkButton));
        networkButton.click();
    }

    public String getInternetText() {
        return internetButton.getText();
    }

}