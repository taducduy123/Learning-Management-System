package com.javaweb.training_center_lms.utils;

import com.mifmif.common.regex.Generex;

public class RandomGeneratorUtil {

    private static RandomGeneratorUtil instance;

    private static final String random_password_rex = "[A-Z][a-zA-Z0-9]{5,11}";

    //---------------------------------------------------------------------------
    private RandomGeneratorUtil() {}

    public static RandomGeneratorUtil getInstance() {
        if (instance == null) {
            instance = new RandomGeneratorUtil();
        }
        return instance;
    }

    public String generateValidPassword() {
        Generex generex = new Generex(random_password_rex);
        return generex.random();
    }



    public static void main(String[] args) {

    }
}
