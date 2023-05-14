package web.pages;

/**
 * #Summary:
 * #Author: Zarina_Bozhyk
 * #Author’s Email:
 * #Creation Date: 5/12/2023
 * #Comments:
 */
public abstract class BasePage {
    public abstract boolean verify();

    public abstract void waitForPageLoaded();
}