package com.javaweb.training_center_lms.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpression {
    // LookAhead:
    // Syntax: X(?=Y)Z
    // Explain:
    // - Entire string is Z
    // - Check if "first" characters of Z are "exactly" same as XY.

    private static final String password_regex = "^(?=(.*)[A-Z]).{6,}$";
    private static final String email_regex = "^(.*)@gmail\\.com$";
    private static final String phone_regex = "^[0-9]{9,11}$";


    //--------------------------------------------------------------------------------
    public RegularExpression() {
        System.out.println("Constructor of RegularExpression is called");
    }

    public static boolean checkPasswordValidation(String password) {
        Pattern pattern = Pattern.compile(password_regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }


    public static boolean checkEmailValidation(String email) {
        Pattern pattern = Pattern.compile(email_regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }


    public static boolean checkPhoneValidation(String phone) {
        Pattern pattern = Pattern.compile(phone_regex);
        Matcher matcher = pattern.matcher(phone);
        return matcher.find();
    }


    public static void main(String[] args) {
        System.out.println(checkPasswordValidation("D12334"));
    }
}

