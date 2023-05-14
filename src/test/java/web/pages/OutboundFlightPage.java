package web.pages;

import helpers.PropertiesLoader;
import helpers.TimeOutConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import web.browser.Browser;

import static web.browser.BrowserInit.getDriver;

/**
 * #Summary:
 * #Author: Zarina_Bozhyk
 * #Author’s Email:
 * #Creation Date: 5/14/2023
 * #Comments:
 */
public class OutboundFlightPage extends BasePage {
    public static final String PAGE_IDENTIFIER = "Outbound Flight";
    private static final String PAGE_URL = PropertiesLoader.getOutboundUrl();
    private static final String OUTBOUND_FLIGHT_PAGE_LOGO_XPATH = "//vacancy-flight-filter-header//h2";

    //<editor-fold desc="public methods">
    @Override
    public boolean verify() {
        return getDriver().getCurrentUrl().contains(PAGE_URL);
    }

    @Override
    public void waitForPageLoaded() {
        Browser.waiter().waitForElementDisplayed(getOutboundFlightPageLogo(), TimeOutConstants.DEFAULT_TIMEOUT_30_000_MS);
    }

    public WebElement getOutboundFlightPageLogo() {
        return getDriver().findElement(getLogoXpath());
    }
    //</editor-fold>

    //<editor-fold desc="private methods">
    private By getLogoXpath() {
        return By.xpath(OUTBOUND_FLIGHT_PAGE_LOGO_XPATH);
    }
    //</editor-fold>
}