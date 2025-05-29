package com.javaweb.training_center_lms.utils;

import jakarta.servlet.http.HttpServletRequest;

public class SessionUtil {
    private static SessionUtil instance;

    private SessionUtil() {

    }

    public static SessionUtil getInstance() {
        if(instance == null) {
            instance = new SessionUtil();
        }
        return instance;
    }

    public void putValue(String key, Object value, HttpServletRequest request) {
        request.getSession().setAttribute(key, value);
    }

    public Object getValue(String key, HttpServletRequest request) {
        return request.getSession().getAttribute(key);
    }

    public void removeValue(String key, HttpServletRequest request) {
        request.getSession().removeAttribute(key);
    }
}
