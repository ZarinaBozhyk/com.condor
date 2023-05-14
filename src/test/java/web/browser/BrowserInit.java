package web.browser;

import helpers.PropertiesLoader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.github.bonigarcia.wdm.config.DriverManagerType.CHROME;
import static io.github.bonigarcia.wdm.config.DriverManagerType.EDGE;
import static io.github.bonigarcia.wdm.config.DriverManagerType.FIREFOX;

/**
 * #Summary:
 * #Author: Zarina_Bozhyk
 * #Author’s Email:
 * #Creation Date: 5/12/2023
 * #Comments:
 */
public class BrowserInit {
    static final Logger logger = LoggerFactory.getLogger(Browser.class);
    private static String browserName;
    private static ThreadLocal<WebDriver> webdriver = new ThreadLocal<>();

    public enum BrowserName {
        EDGE("edge"),
        CHROME("chrome"),
        FIREFOX("firefox");

        private final String name;

        BrowserName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    //<editor-fold desc="public methods">
    public static WebDriver getDriver() {
        if (webdriver.get() == null) {
            browserName = PropertiesLoader.getBrowserName();
            init(browserName);
        }
        return webdriver.get();
    }

    public static void closeDriver() {
        if (webdriver.get() != null) {
            logger.info("======Closing browser======");
            try {
                getDriver().close();
                getDriver().quit();
            } catch (Exception e) {
                logger.warn(e.getMessage());
            } finally {
                webdriver.remove();
                browserName = null;
                logger.info("The driver has been closed.");
            }
        }
    }
    //</editor-fold>

    //<editor-fold desc="private methods">
    private static void init(String browser) {
        BrowserName name = BrowserName.valueOf(BrowserName.class, browser.toUpperCase());
        switch (name) {
            case FIREFOX:
                logger.info("======Setting up Firefox browser======");
                WebDriverManager.getInstance(FIREFOX).setup();
                webdriver.set(new FirefoxDriver());
                break;
            case CHROME:
                logger.info("======Setting up Chrome browser======");
                WebDriverManager.getInstance(CHROME).setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-notifications");
                webdriver.set(new ChromeDriver(options));
                break;
            case EDGE:
                logger.info("======Setting up Edge browser======");
                WebDriverManager.getInstance(EDGE).setup();
                webdriver.set(new EdgeDriver());
                break;
            default:
                logger.error(String.format("Can't find driver for '%s' browser", browser));
                throw new IllegalArgumentException(String.format("Can't initialize browser '%s'", browser));
        }
    }
    //</editor-fold>
}