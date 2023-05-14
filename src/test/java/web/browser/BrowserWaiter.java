package web.browser;

import helpers.TimeOutConstants;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.function.Function;
import java.util.logging.Logger;

/**
 * #Summary:
 * #Author: Zarina_Bozhyk
 * #Author’s Email:
 * #Creation Date: 5/12/2023
 * #Comments:
 */
public class BrowserWaiter extends Browser {
    private static final Logger logger = Logger.getLogger(BrowserWaiter.class.getName());

    public void waitForElementDisplayed(final WebElement element, final long... msToWait) {
        long msToWaitLoc = msToWait.length > 0 ? msToWait[0] : TimeOutConstants.DEFAULT_TIMEOUT_10_000_MS;
        try {
            Waiter waiter = () -> waitUntilExpected(WebDriver -> {
                try {
                    if (element.isDisplayed()) {
                        logger.info(String.format("The element '%s' has been displayed.", element));
                        return true;
                    } else {
                        logger.info(String.format("The element '%s' isn't displayed on the page. Waiting...", element));
                        return false;
                    }
                } catch (NoSuchElementException e) {
                    logger.info(String.format("No such element '%s' exception: %s.", element, e));
                    return false;
                } catch (StaleElementReferenceException e) {
                    logger.info(String.format("Stale Element Reference Exception for element '%s': %s.", element, e));
                    return false;
                }
            }, msToWaitLoc);
            waiter.applyWait();
        } catch (TimeoutException e) {
            throw new TimeoutException(String.format("The element '%s' is not displayed after timeout '%d' millisec(s).", element, msToWaitLoc));
        }
    }

    public <T> void waitUntilExpected(Function<WebDriver, T> function, final long... msToWait) {
        long msToWaitLoc = msToWait.length > 0 ? msToWait[0] : TimeOutConstants.DEFAULT_TIMEOUT_10_000_MS;
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(msToWaitLoc));
        long pollingInterval = TimeOutConstants.DEFAULT_POLLING_INTERVAL_500_MS;
        wait.pollingEvery(Duration.of(pollingInterval, ChronoUnit.MILLIS));
        wait.until(function);
    }

    public void sleep(long... msToWait) {
        long msToWaitLoc = msToWait.length > 0 ? msToWait[0] : 1;
        try {
            Thread.sleep(msToWaitLoc);
        } catch (InterruptedException e) {
            logger.info(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}