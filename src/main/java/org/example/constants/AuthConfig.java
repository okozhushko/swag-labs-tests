package org.example.constants;

public class AuthConfig {
    public static final String BASE_URL = "https://www.saucedemo.com/";
    public static final String USERNAME = System.getProperty("test.username", "standard_user");
    public static final String PASSWORD = System.getProperty("test.password", "secret_sauce");
}