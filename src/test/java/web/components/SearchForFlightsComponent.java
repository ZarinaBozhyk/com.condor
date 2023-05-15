package web.components;

import exceptions.NotSupportedException;
import helpers.TimeOutConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import web.browser.Browser;

import static web.browser.BrowserInit.getDriver;

/**
 * #Summary:
 * #Author: Zarina_Bozhyk
 * #Author’s Email:
 * #Creation Date: 5/13/2023
 * #Comments:
 */
public class SearchForFlightsComponent implements BaseComponent {
    private static final String CLOSE_MODAL_BUTTON_CSS = "#modalNavBarId > div:nth-child(1) > div > a > i";
    private static final String DEPARTURE_INPUT_CSS = "#airportinput_id_origin";
    private static final String DESTINATION_INPUT_CSS = "#airportinput_id_destination";

    //<editor-fold desc="public methods">
    @Override
    public void invoke() {
        throw new NotSupportedException("Please implement me if needed");
    }

    @Override
    public void close() {
        clickXButton();
        Browser.waiter().sleep(TimeOutConstants.DEFAULT_TIMEOUT_5_000_MS);
    }

    public void clickXButton() {
        getDriver().findElement(getCloseButtonCss());
    }

    public void setDepartureAirport(String departureAirport) {
        WebElement departureElement = getDriver().findElement(getDepartureInputCss());
        departureElement.sendKeys(departureAirport);
        departureElement.sendKeys(Keys.ENTER);
    }

    public void setDestinationAirport(String destinationAirport) {
        WebElement departureElement = getDriver().findElement(getDestinationInputCss());
        departureElement.sendKeys(destinationAirport);
        departureElement.sendKeys(Keys.ENTER);
        Browser.waiter().sleep(TimeOutConstants.DEFAULT_TIMEOUT_2_000_MS);
    }
    //</editor-fold>

    //<editor-fold desc="private methods">
    private By getCloseButtonCss() {
        return By.cssSelector(CLOSE_MODAL_BUTTON_CSS);
    }

    private By getDepartureInputCss() {
        return By.cssSelector(DEPARTURE_INPUT_CSS);
    }

    private By getDestinationInputCss() {
        return By.cssSelector(DESTINATION_INPUT_CSS);
    }
    //</editor-fold>
}