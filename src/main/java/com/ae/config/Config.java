package com.ae.config;

public class Config {

    private Config() {}

    public static String baseUrl() {
        String url = ConfigLoader.get("baseUrl");
        if (url == null || url.isBlank()) {
            throw new RuntimeException("baseUrl is missing in config.properties");
        }
        return url.trim();
    }

    public static int timeoutSeconds() {
        return Integer.parseInt(ConfigLoader.getOrDefault("timeoutSeconds", "10"));
    }

    // --- UI credentials ---
    public static String uiEmail() {
        return required("uiEmail");
    }

    public static String uiPassword() {
        return required("uiPassword");
    }

    public static String uiUsernameExpected() {
        return required("uiUsernameExpected");
    }

    private static String required(String key) {
        String v = ConfigLoader.get(key);
        if (v == null || v.isBlank()) {
            throw new RuntimeException(key + " is missing in config.properties");
        }
        return v.trim();
    }
}
