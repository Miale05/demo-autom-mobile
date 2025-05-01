package org.mikidev.view;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OptionsView {
    private AppiumDriver driver;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"com.apple.settings.general\"]")
    private WebElement generalButton;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"About\"]")
    private WebElement informacionLabel;

    public OptionsView(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    public void clicGeneralButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(generalButton));
        generalButton.click();
    }

    public String getAboutText() {
        return informacionLabel.getText();
    }

}