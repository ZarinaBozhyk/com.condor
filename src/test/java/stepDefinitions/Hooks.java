package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.Context;
import web.browser.Browser;

/**
 * #Summary:
 * #Author: Zarina_Bozhyk
 * #Author’s Email:
 * #Creation Date: 5/11/2023
 * #Comments:
 */
public class Hooks {

    @Before("@withContext")
    public void beforeWithContext() {
        Context.initContext();
    }

    @After("@withContext")
    public void afterWithContext() {
        Context.destroyContext();
    }

    @Before("@ui")
    public void setUp() {
        Browser.setBrowserPosition();
        Browser.setBrowserSize();
    }

    @After("@ui")
    public void tearDown() {
        Browser.closeDriver();
    }

    @Before
    public void beforeScenarioStart() {
        System.out.println("-----------------Start of Scenario-----------------");
    }

    @After
    public void afterScenarioFinish() {
        System.out.println("-----------------End of Scenario-----------------");
    }
}