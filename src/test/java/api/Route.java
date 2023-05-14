package api;

/**
 * #Summary:
 * #Author: Zarina_Bozhyk
 * #Author’s Email:
 * #Creation Date: 5/11/2023
 * #Comments:
 */
public class Route {
    private static final String USERS = "/users";
    private static final String COMMENTS = "/comments";
    private static final String ALBUMS = "/albums";
    private static final String POSTS = "/posts";

    //<editor-fold desc="public methods">
    public static String users() {
        return USERS;
    }

    public static String comments(int id) {
        return POSTS + "/" + id + COMMENTS;
    }

    public static String albums(int id) {
        return USERS + "/" + id + ALBUMS;
    }
    //</editor-fold>

}