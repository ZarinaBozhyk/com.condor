package utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * #Summary:
 * #Author: Zarina_Bozhyk
 * #Author’s Email:
 * #Creation Date: 5/10/2023
 * #Comments:
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Context {

    public static void initContext() {
        projectContext.set(new HashMap<>());
    }

    public static void destroyContext() {
        projectContext.remove();
    }

    private static InheritableThreadLocal<Map<String, Object>> projectContext = new InheritableThreadLocal<>();

    public static void setValue(Enum<?> key, Object value) {
        setValue(key.toString(), value);
    }

    public static void setValue(String key, Object value) {
        projectContext.get().put(key, value);
    }

    public static Object getValue(Enum<?> key) {
        return getValue(key.toString());
    }

    public static Object getValue(String key) {
        return projectContext.get().get(key);
    }
}