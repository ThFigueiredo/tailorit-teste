package com.tailorit.core.config;

public class PasswordValidationService {

    public boolean isPasswordValid(String password) {
        return isLengthValid(password) &&
                containsUppercaseLetter(password) &&
                containsLowercaseLetter(password) &&
                containsNumber(password);
//                containsSymbol(password);
    }

    private boolean isLengthValid(String password) {
        return password.length() >= 8;
    }

    private boolean containsUppercaseLetter(String password) {
        return password.matches(".*[A-Z].*");
    }

    private boolean containsLowercaseLetter(String password) {
        return password.matches(".*[a-z].*");
    }

    private boolean containsNumber(String password) {
        return password.matches(".*\\d.*");
    }

//    private boolean containsSymbol(String password) {
//        String symbols = "!@#$%^&*()-_=+[]{}|;:'\",.<>/?";
//        return password.matches(".*[" + symbols + "].*");
//    }


}
