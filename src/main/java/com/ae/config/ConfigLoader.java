package com.ae.config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {

    private static final Properties PROPS = new Properties();

    static {
        try (InputStream is = ConfigLoader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (is == null) {
                throw new RuntimeException("config.properties not found in src/main/resources");
            }
            PROPS.load(is);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    private ConfigLoader() {}

    public static String get(String key) {
        return PROPS.getProperty(key);
    }

    public static String getOrDefault(String key, String defaultValue) {
        return PROPS.getProperty(key, defaultValue);
    }

    // Backward-compatible
    public static String get() {
        throw new UnsupportedOperationException("Use ConfigLoader.get(\"key\")");
    }

}
