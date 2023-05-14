package stepDefinitions;

import api.Endpoints;
import api.dto.Albums;
import api.dto.Comments;
import api.dto.Users;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import utils.Context;
import utils.ContextKeys;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class StepDefsAPI {
    private static final Logger logger = Logger.getLogger(StepDefsAPI.class.getName());
    private static Response response;
    private static final String REGEX_PATTERN = "^(.+)@(\\S+)$";

    @When("I get the list of users")
    public void iGetTheListOfUsers() {
        response = Endpoints.getUsers();
        response.getBody().as(Users[].class);
        logger.info("Here we have serialisation");
    }

    @When("I get userId for the user with name {string}")
    public void iGetUserIdForTheUserWithName(String username) {
        response = Endpoints.getUsers();
        Users[] users = response.getBody().as(Users[].class);
        logger.info("Here we have serialisation");
        Users userData = Arrays.stream(users)
                .filter(i -> i.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("There is no user with username '%s'", username)));
        Context.setValue(ContextKeys.VALUE, userData.getId());
    }

    @When("I get userId for the user with name {string} and check the response status code is {int}")
    public void iGetUserIdForTheUserWithNameAndCheckTheResponseStatusCodeIs(String userName, int expectedStatusCode) {
        response = Endpoints.getUsers();
        Users[] users = response.getBody().as(Users[].class);
        int responseStatusCode = response.getStatusCode();
        Assert.assertEquals(expectedStatusCode, responseStatusCode,
                String.format("Actual status code is '%s' doesn't equal to expected '%s'", responseStatusCode, expectedStatusCode));
        Users userData = Arrays.stream(users)
                .filter(i -> i.getUsername().equals(userName))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("There is no user with userName '%s'", userName)));
        Context.setValue(ContextKeys.VALUE, userData.getId());
    }

    @Then("I check that comment for the user with saved Id contain valid emails")
    public void iCheckThatCommentForTheUserWithSavedIdContainValidEmails() {
        response = Endpoints.getCommentsByUserId((Integer) Context.getValue(ContextKeys.VALUE));
        Comments[] comments = response.getBody().as(Comments[].class);
        SoftAssert softAssert = new SoftAssert();
        Arrays.stream(comments)
                .map(Comments::getEmail)
                .forEach(email ->
                        softAssert.assertTrue(patternMatches(email, REGEX_PATTERN),
                                String.format("Email '%s' does not matChes pattern", email)));
        softAssert.assertAll();
    }

    @Then("I check that list of users contains user names as below:")
    public void iCheckThatListOfUsersContainsUserNamesAsBelow(List<String> expectedNameList) {
        response = Endpoints.getUsers();
        Users[] users = response.getBody().as(Users[].class);
        SoftAssert softAssert = new SoftAssert();
        Arrays.stream(users)
                .map(Users::getUsername)
                .forEach(actualName ->
                        softAssert.assertTrue(expectedNameList.contains(actualName),
                                String.format("List does not contain user name '%s'", actualName)));
        softAssert.assertAll();
    }

    @Then("I check that albums for the user with saved Id contain titles")
    public void iCheckThatAlbumsForTheUserWithSavedIdContainTitles() {
        Integer id = (Integer) Context.getValue(ContextKeys.VALUE);
        response = Endpoints.getAlbumsByUserId(id);
        Albums[] albums = response.getBody().as(Albums[].class);
        SoftAssert softAssert = new SoftAssert();
        Arrays.stream(albums)
                .map(Albums::getTitle)
                .findAny()
                .orElseThrow(() -> new RuntimeException(String.format("There are no albums for the user with user Id '%s'", id)));
        softAssert.assertAll();
    }


    @When("I check that {string} header value from the response is {string}")
    public void iCheckThatValueFromTheResponseIs(String headerName, String expectedHeaderValue) {
        Integer id = (Integer) Context.getValue(ContextKeys.VALUE);
        String actualHeaderValue = Endpoints.getAlbumsByUserId(id).getHeader(headerName);
        Assert.assertEquals(actualHeaderValue, expectedHeaderValue,
                String.format("Actual header value '%s' doesn't equal to expected '%s'", actualHeaderValue, expectedHeaderValue));
    }

    //<editor-fold desc="private methods">
    private static boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }
    //</editor-fold>
}