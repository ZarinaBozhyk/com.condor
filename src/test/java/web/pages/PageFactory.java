package web.pages;

import java.util.HashMap;
import java.util.Map;

/**
 * #Summary:
 * #Author: Zarina_Bozhyk
 * #Author’s Email:
 * #Creation Date: 5/12/2023
 * #Comments:
 */
public class PageFactory {
    private static final ThreadLocal<Map<String, Object>> pageMapper = new ThreadLocal<>();

    //<editor-fold desc="Public Methods">
    public static BasePage getPageByIdentifier(String identifier) {
        Object obj = getObjectByClassIdentifier(identifier);
        if (obj instanceof BasePage) {
            return (BasePage) obj;
        } else {
            throw new IllegalArgumentException(String.format("Page '%s' does not extend class BasePage", obj.getClass().getName()));
        }
    }
    //</editor-fold>

    //<editor-fold desc="Private Methods">
    private static Object getObjectByClassIdentifier(String classIdentifier) {
        if (pageMapper.get() == null) {
            pageMapper.set(new HashMap<>());
        }
        return pageMapper.get().computeIfAbsent(classIdentifier, PageFactory::initPage);
    }

    private static Object initPage(String identifier) {
        return switch (identifier) {
            case MainPage.PAGE_IDENTIFIER -> new MainPage();
            case CustomerSupportPage.PAGE_IDENTIFIER -> new CustomerSupportPage();
            case OutboundFlightPage.PAGE_IDENTIFIER -> new OutboundFlightPage();
            default ->
                    throw new IllegalArgumentException(String.format("Page identifier '%s' is not found", identifier));
        };
    }
    //</editor-fold>
}