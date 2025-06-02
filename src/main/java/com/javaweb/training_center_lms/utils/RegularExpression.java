package com.javaweb.training_center_lms.utils;

import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpression {
    // Lấy thông tin từ một file .properties có trong folder main/resources
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("regex");


    // LookAhead:
    // Syntax: X(?=Y)Z
    // Explain:
    // - Entire string is Z
    // - Check if "first" characters of Z are "exactly" same as XY.
    private static final String password_regex = resourceBundle.getString("password.regex");
    private static final String email_regex = resourceBundle.getString("email.regex");;
    private static final String phone_regex = resourceBundle.getString("phone.regex");;


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

        System.out.println(checkPasswordValidation("12334"));
        System.out.println(checkEmailValidation("1@gmail.com"));
        System.out.println(checkPhoneValidation("0982587660"));
    }
}

