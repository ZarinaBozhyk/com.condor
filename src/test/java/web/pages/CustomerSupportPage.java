package web.pages;

import exceptions.NotSupportedException;
import helpers.PropertiesLoader;

import static web.browser.BrowserInit.getDriver;

/**
 * #Summary:
 * #Author: Zarina_Bozhyk
 * #Author’s Email:
 * #Creation Date: 5/12/2023
 * #Comments:
 */
public class CustomerSupportPage extends BasePage {
    public static final String PAGE_IDENTIFIER = "Customer Support";
    private static final String PAGE_URL = PropertiesLoader.getBaseUrl() + "/help-contact/faq.jsp";

    //<editor-fold desc="public methods">
    @Override
    public boolean verify() {
        return getDriver().getCurrentUrl().equals(PAGE_URL);
    }

    @Override
    public void waitForPageLoaded() {
        throw new NotSupportedException("Please implement me if needed");
    }
    //</editor-fold>
}