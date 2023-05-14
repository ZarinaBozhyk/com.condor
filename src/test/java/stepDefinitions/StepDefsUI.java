package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import web.components.Calender;
import web.components.SearchForFlightsComponent;
import web.pages.BasePage;
import web.pages.MainPage;
import web.pages.PageFactory;

import java.util.logging.Logger;

/**
 * #Summary:
 * #Author: Zarina_Bozhyk
 * #Author’s Email:
 * #Creation Date: 5/11/2023
 * #Comments:
 */
public class StepDefsUI {
    private MainPage mainPage = (MainPage) PageFactory.getPageByIdentifier(MainPage.PAGE_IDENTIFIER);
    private static final Logger logger = Logger.getLogger(StepDefsUI.class.getName());
    private SearchForFlightsComponent component = new SearchForFlightsComponent();
    private Calender calender = new Calender();

    @Given("I open Condor Main page")
    public void iOpenCondorMainPage() {
        mainPage.open();
    }

    @Then("^I check Condor \"?([^\"]*)\"? page is \"?(opened|closed)\"?$")
    public void iCheckCondorPageIs(String identifier, String state) {
        BasePage page = PageFactory.getPageByIdentifier(identifier);
        boolean isOpened = "opened".equals(state);
        Assert.assertEquals(page.verify(), isOpened, String.format("Page '%s' is not '%s'", identifier, state));
    }

    @When("I click on Customer Support button")
    public void IClicksOnCustomerSupportButton() {
        mainPage.clickOnCustomerSupportButton();
    }

    @And("I click on {string} input")
    public void iClickOnInput(String inputName) {
        mainPage.clickOnInput(inputName);
        logger.info("It could takes time....");
    }

    @And("I type {string} into departure input field")
    public void iTypeIntoInputField(String departure) {
        component.setDepartureAirport(departure);
    }

    @And("I type {string} into destination input field")
    public void iTypeIntoDestinationInputField(String destination) {
        component.setDestinationAirport(destination);
    }

    @And("I click on {string} button")
    public void iClickOnButton(String buttonName) {
        calender.clickOnCalenderButton(buttonName);
        logger.info("It could takes time....");
    }

    @And("I select available day")
    public void iSelectAvailableDay() {
        calender.selectAvailableDay();
    }

    @And("I click on Continue button")
    public void iClickOnContinueButton() {
        calender.clickContinueButton();
        logger.info("It could takes time....");
    }
}