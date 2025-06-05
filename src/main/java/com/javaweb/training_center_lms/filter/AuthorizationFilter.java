package com.javaweb.training_center_lms.filter;

import com.javaweb.training_center_lms.controllers.BaseController;
import com.javaweb.training_center_lms.models.Account;
import com.javaweb.training_center_lms.models.Instructor;
import com.javaweb.training_center_lms.models.Student;
import com.javaweb.training_center_lms.models.User;
import com.javaweb.training_center_lms.utils.SessionUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


public class AuthorizationFilter implements Filter {

    private final SessionUtil sessionUtil = SessionUtil.getInstance();


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String url = request.getRequestURI();
        Account account = (Account) sessionUtil.getValue("account", request);
        User user = (User) sessionUtil.getValue("user", request);

        // ----------------------------------- For Debugging ----------------------------------
        System.out.println("[url, account, user] = " + "[" + url + ", " + account + ", " + user + "]");
        // ----------------------------------- For Debugging ----------------------------------


        if (url.startsWith("/controllers/manager")) {                   // Authentication & Authorization with manager
            // Check if the user is authenticated
            if (account != null) {
                if (account.getRole().equalsIgnoreCase("manager")) {
                    filterChain.doFilter(servletRequest, servletResponse);
                } else {
                    System.out.println("You are not permitted to access this page...");
                    BaseController.responseRedirectTo("/controllers/LoginController/login?action=login", request, response);
                }
            } else {
                System.out.println("You are not authenticated...");
                BaseController.responseRedirectTo("/controllers/LoginController/login?action=login", request, response);
            }
        }
        else if (url.startsWith("/controllers/instructor")) {         // Authentication & Authorization with instructor
            // Check if the user is authenticated
            if (account != null && user != null) {
                if (account.getRole().equalsIgnoreCase("instructor") && !((Instructor) user).isBlocked()) {     // check if user is blocked
                    filterChain.doFilter(servletRequest, servletResponse);
                } else {
                    System.out.println("You are not permitted to access this page...");
                    BaseController.responseRedirectTo("/controllers/LoginController/login?action=login", request, response);
                }
            } else {
                System.out.println("You are not authenticated...");
                BaseController.responseRedirectTo("/controllers/LoginController/login?action=login", request, response);
            }
        }
        else if (url.startsWith("/controllers/student")) {         // Authentication & Authorization with student
            // Check if the user is authenticated
            if (account != null && user != null) {
                if (account.getRole().equalsIgnoreCase("student") && !((Student) user).isBlocked()) {           // check if user is blocked
                    filterChain.doFilter(servletRequest, servletResponse);
                } else {
                    System.out.println("You are not permitted to access this page...");
                    BaseController.responseRedirectTo("/controllers/LoginController/login?action=login", request, response);
                }
            } else {
                System.out.println("You are not authenticated...");
                BaseController.responseRedirectTo("/controllers/LoginController/login?action=login", request, response);
            }
        }
        else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
