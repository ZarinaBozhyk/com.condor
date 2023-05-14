package web.components;

import exceptions.NotSupportedException;
import helpers.TimeOutConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import web.browser.Browser;

import java.util.List;

import static web.browser.BrowserInit.getDriver;

/**
 * #Summary:
 * #Author: Zarina_Bozhyk
 * #Author’s Email:
 * #Creation Date: 5/14/2023
 * #Comments:
 */
public class Calender implements BaseComponent {
    public static final String LIST_AVAILABLE_DATES_XPATH = "//span[@class='price hidden-xs']";
    public static final String CONTINUE_BUTTON = "//a[contains(@class,'btn--large')]";
    private static final String CALENDER_BUTTON_XPATH = "//div[contains(@class,'radio-btngroup')]//span[text()='%s']";

    //<editor-fold desc="public methods">
    @Override
    public void invoke() {
        throw new NotSupportedException("Please implement me if needed");
    }

    @Override
    public void close() {
        throw new NotSupportedException("Please implement me if needed");
    }

    public void clickOnCalenderButton(String buttonName) {
        getDriver().findElement(getCalenderButtonXpath(buttonName)).click();
        Browser.waiter().sleep(TimeOutConstants.DEFAULT_TIMEOUT_2_000_MS);
    }

    public void selectAvailableDay() {
        availableDates().stream().findFirst()
                .orElseThrow(() -> new IllegalStateException("There is no available days for the current month")).click();
    }

    public void clickContinueButton() {
        Browser.waiter().sleep(TimeOutConstants.DEFAULT_TIMEOUT_2_000_MS);
        getDriver().findElement(getContinueButtonXpath()).click();
    }
    //</editor-fold>

    //<editor-fold desc="private methods">
    private List<WebElement> availableDates() {
        return getDriver().findElements(getAvailableDatesListXpath());
    }

    private By getAvailableDatesListXpath() {
        return By.xpath(LIST_AVAILABLE_DATES_XPATH);
    }

    private By getContinueButtonXpath() {
        return By.xpath(CONTINUE_BUTTON);
    }

    private By getCalenderButtonXpath(String buttonName) {
        String xpath = String.format(CALENDER_BUTTON_XPATH, buttonName);
        return By.xpath(xpath);
    }
    //</editor-fold>
}