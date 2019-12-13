package com.platzi.functional._10_chaining.data;

public class Phone {
    private String country;

    private char[] countryCode;

    private char[] digits;

    public Phone() {
    }

    public Phone(String phoneString) {
        //TODO: Descomponer los valores de un string
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public char[] getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(char[] countryCode) {
        this.countryCode = countryCode;
    }

    public char[] getDigits() {
        return digits;
    }

    public void setDigits(char[] digits) {
        this.digits = digits;
    }
}
