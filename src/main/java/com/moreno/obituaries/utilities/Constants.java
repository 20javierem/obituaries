package com.moreno.obituaries.utilities;

public class Constants {
    private static String URLPROJECT = "/.obituaries";

    public static String getUrlProject() {
        return System.getProperty("user.home") + URLPROJECT;
    }

}
