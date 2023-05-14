package web.pages;

import helpers.PropertiesLoader;
import helpers.TimeOutConstants;
import io.github.sukgu.Shadow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import web.browser.Browser;

import static web.browser.BrowserInit.getDriver;

/**
 * #Summary:
 * #Author: Zarina_Bozhyk
 * #Author’s Email:
 * #Creation Date: 5/12/2023
 * #Comments:
 */
public class MainPage extends BasePage {
    public static final String PAGE_IDENTIFIER = "Main";
    private static final String PAGE_URL = PropertiesLoader.getBaseUrl();
    private static final String MAIN_PAGE_LOGO_XPATH = "//h1/a[@class='condor-logo']";
    private static final String MAIN_PAGE_SEARCH_MODUL_XPATH = "//div[@class='tca-search-module']";
    private static final String CUSTOMER_SUPPORT_BUTTON_XPATH = "//*[text()=' Customer Support ']";
    private static final String INPUT_NAME_XPATH = "//div[text()='%s']//ancestor::div[@class='input-link']/a";

    //<editor-fold desc="public methods">
    public void open() {
        getDriver().navigate().to(PAGE_URL);
        clickOnShadowElement();
    }

    @Override
    public boolean verify() {
        return getMainPageLogo().isDisplayed()
                && getDriver().getCurrentUrl().equals(PAGE_URL);
    }

    @Override
    public void waitForPageLoaded() {
        Browser.waiter().waitForElementDisplayed(getMainPageSearchModule(), TimeOutConstants.DEFAULT_TIMEOUT_30_000_MS);
    }

    public WebElement getMainPageLogo() {
        return getDriver().findElement(getLogoXpath());
    }

    public WebElement getMainPageSearchModule() {
        return getDriver().findElement(getSearchModulXpath());
    }

    public void clickOnCustomerSupportButton() {
        getDriver().findElement(getCustomerButtonXpath()).click();
    }

    public void clickOnInput(String inputName) {
        getDriver().findElement(getInputByName(inputName)).click();
        Browser.waiter().sleep(TimeOutConstants.DEFAULT_TIMEOUT_5_000_MS);
    }
    //</editor-fold>

    //<editor-fold desc="private methods">
    private static void clickOnShadowElement() {
        Browser.waiter().sleep(TimeOutConstants.DEFAULT_TIMEOUT_5_000_MS);
        Shadow shadow = new Shadow(getDriver());
        WebElement shadowElement = shadow.findElement("#uc-center-container > div.sc-cCjUiG.gHlwwJ > div > div.sc-lllmON.fjvxqY > div > button.sc-eDvSVe.gXvQbG");
        shadowElement.click();
    }

    private By getLogoXpath() {
        return By.xpath(MAIN_PAGE_LOGO_XPATH);
    }

    private By getSearchModulXpath() {
        return By.xpath(MAIN_PAGE_SEARCH_MODUL_XPATH);
    }

    private By getCustomerButtonXpath() {
        return By.xpath(CUSTOMER_SUPPORT_BUTTON_XPATH);
    }

    private By getInputByName(String inputName) {
        String xpath = String.format(INPUT_NAME_XPATH, inputName);
        return By.xpath(xpath);
    }
    //</editor-fold>
}