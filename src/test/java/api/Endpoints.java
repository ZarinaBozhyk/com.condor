package api;

import helpers.PropertiesLoader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


/**
 * #Summary:
 * #Author: Zarina_Bozhyk
 * #Author’s Email:
 * #Creation Date: 5/10/2023
 * #Comments:
 */
public class Endpoints {
    private static final String PAGE_URL = PropertiesLoader.getBaseUri();

    //<editor-fold desc="public methods">
    public static Response getUsers() {
        RestAssured.baseURI = PAGE_URL;
        RequestSpecification request = RestAssured.given();
        return request.get(Route.users());
    }

    public static Response getCommentsByUserId(Integer id) {
        RestAssured.baseURI = PAGE_URL;
        RequestSpecification request = RestAssured.given();
        return request.get(Route.comments(id));
    }

    public static Response getAlbumsByUserId(Integer id) {
        RestAssured.baseURI = PAGE_URL;
        RequestSpecification request = RestAssured.given();
        return request.get(Route.albums(id));
    }
    //</editor-fold>
}