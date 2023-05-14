package helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * #Summary:
 * #Author: Zarina_Bozhyk
 * #Author’s Email:
 * #Creation Date: 5/12/2023
 * #Comments:
 */
public class PropertiesLoader {
    private static final String pathToBrowserPropertyFile = "src/test/resources/browser.properties";
    private static final String pathToCondorPropertyFile = "src/test/resources/uitests.properties";

    public static String getBaseUrl() {
        return getProperty(pathToCondorPropertyFile, "baseUrl");
    }

    //<editor-fold desc="public methods">
    public static String getOutboundUrl() {
        return getProperty(pathToCondorPropertyFile, "outboundUrl");
    }

    public static String getBrowserName() {
        return getProperty(pathToBrowserPropertyFile, "browserName");
    }

    public static String getBrowserWidth() {
        return getProperty(pathToBrowserPropertyFile, "resolutionWidth");
    }

    public static String getBrowserHeight() {
        return getProperty(pathToBrowserPropertyFile, "resolutionHeight");
    }

    public static String getProperty(String pathToPropertyFile, String key) {
        return getValuePipeline(pathToPropertyFile, key);
    }

    public static Properties getPropertyFile(String pathToPropertyFile) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(pathToPropertyFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    private static String getPropertyValueFromFile(String pathToPropertyFile, String key) {
        return getPropertyFile(pathToPropertyFile).getProperty(key);
    }
    //</editor-fold>

    //<editor-fold desc="private methods">
    private static String getValuePipeline(String pathToPropertyFile, String key) {
        String localProperty = getPropertyValueFromFile(pathToPropertyFile, key);
        if (localProperty != null) {
            return localProperty;
        }
        return "Property Value is not defined";
    }
    //</editor-fold>
}