package tests.pages.helpers;

public class CapitalizedString {

    public String capitalizedString(String value) {
        String capitalized = value.substring(0, 1).toUpperCase() + value.substring(1).toLowerCase();
        return capitalized;
    }
}